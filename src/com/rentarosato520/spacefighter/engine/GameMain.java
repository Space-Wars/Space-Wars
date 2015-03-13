package com.rentarosato520.spacefighter.engine;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

import com.rentarosato520.spacefighter.Camera;
import com.rentarosato520.spacefighter.GameScene;
import com.rentarosato520.spacefighter.GameSession;
import com.rentarosato520.spacefighter.StartUp;
import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.asset.animation.Animator;
import com.rentarosato520.spacefighter.asset.animation.Assets;
import com.rentarosato520.spacefighter.asset.sound.SoundLoader;
import com.rentarosato520.spacefighter.entity.ID;
import com.rentarosato520.spacefighter.entity.misc.Meteor;
import com.rentarosato520.spacefighter.entity.ships.Player;
import com.rentarosato520.spacefighter.listeners.KeyInput;
import com.rentarosato520.spacefighter.listeners.MouseInput;


public class GameMain extends Canvas implements Runnable{
	private static final long serialVersionUID = -4652247415397892972L;
	public static String Title = "Space Fighter Pre-Alpha";
	public int blueValue = 155;

	private GameScene scene;
	private Thread thread;
	private Handler h;
	private HUD hud;
	private Boolean isRunning = false;
	private MouseInput mouse;
	private Random r;
	private Animator a;
	private SoundLoader l;
	private Camera c;
	private Player p;
	public static int SceneChange, pDeaths;
	public static final int pTarget = 1, eTarget = 0, fTarget = 2, nTarget = 3, gTarget = 4;
	public static int numFrames;
	
	public GameMain(){
		r = new Random();
		a = new Animator();
		h = new Handler();
		l = new SoundLoader();
		c = new Camera(0, 0);
		
		//random spawn later
		
		p = new Player(Window.screensize.width/2, Window.screensize.height/2, 128, 128, ID.Player, h, c);
		
		for(int i = 0; i < r.nextInt(20) + 5; i++){
			if(r.nextInt(2) == 0){
				h.addObject(new Meteor(r.nextInt(Window.screensize.width + 1100), r.nextInt(Window.screensize.height + 1200), r.nextInt(300)+300, r.nextInt(300)+300, ID.Meteor, h, 7));
			}else{
				h.addObject(new Meteor(r.nextInt(Window.screensize.width + 1100), -r.nextInt(Window.screensize.height + 1200), r.nextInt(300)+300, r.nextInt(300)+300, ID.Meteor, h, 7));
			}
		}
		
		SceneChange = 0;
		
		scene = new StartUp(true, a, l);
	
		GameScene.setCurrentScene(scene);
		
		mouse = new MouseInput(scene);
		
		Assets.load();
		
		hud = new HUD(h, p);
		
		this.addKeyListener(new KeyInput(h));
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		
		
		new Window(this);
	}
	
	public void run() {
		this.requestFocus();
		long lasTime = System.nanoTime();
		double amountOfTicks = 60.0;
		//9 zeros
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lasTime) / ns;
			lasTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(isRunning)
				render();
			frames++;
			numFrames = frames;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	public void start(){
		if(isRunning){
			return;
		}
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop(){
		try{
			thread.join();
			isRunning = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void tick(){
		if(GameScene.getCurrentScene() != null){
			if(GameScene.getCurrentScene() instanceof StartUp){
				scene.tick();
				if(!scene.isActive()){
					scene = new GameSession(true, h, hud, c, p, mouse);
					GameScene.setCurrentScene(scene);
				}
			}
			if(GameScene.getCurrentScene() instanceof GameSession){
				scene.tick();
			}
		}
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(new Color(0, 0, blueValue));
		g.fillRect(0, 0, Window.screensize.width, Window.screensize.height);
		
		if(GameScene.getCurrentScene() != null){
			if(GameScene.getCurrentScene() instanceof StartUp){
				scene.render(g, g2d);
				if(!scene.isActive()){
					scene = new GameSession(true, h, hud, c, p, mouse);
					GameScene.setCurrentScene(scene);
				}
			}
			if(GameScene.getCurrentScene() instanceof GameSession){
				scene.render(g, g2d);
			}
		}
		g.dispose();
		bs.show();
	}
	
	public static boolean objectCollision(int ox, int ow,int varX){
		if(varX >= ox && varX <= ox + ow){
			return true;
		}else{
			return false;
		}
	}
	
	public static int clamp(int min, int max, int var){
		if(var < min){
			return var = min;
		}
		if(var > max){
			return var = max;
		}
		return var;
	}

	public static void main(String args[]){
		new GameMain();
	}
}
