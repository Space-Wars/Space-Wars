package com.rentarosato520.spacefighter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import com.rentarosato520.spacefighter.asset.animation.Animator;
import com.rentarosato520.spacefighter.asset.sound.SoundLoader;
import com.rentarosato520.spacefighter.engine.GameMain;
import com.rentarosato520.spacefighter.engine.HUD;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.engine.Spawner;
import com.rentarosato520.spacefighter.entity.ID;
import com.rentarosato520.spacefighter.entity.ships.Player;
import com.rentarosato520.spacefighter.listeners.KeyInput;
import com.rentarosato520.spacefighter.listeners.MouseInput;

public class GameSession extends GameScene{
	private Handler h;
	private Player p;
	private HUD hud;
	private Random r;     
	private Animator a;
	private Spawner s;
	private SoundLoader l;
	private Camera c;
	private int x, y;
	private int reloadTime;
	public static int rectX, rectY, rectW, rectH;
	private boolean isReloading; 
	public static boolean statChoice, isShop;
	public static String stats, items, ships, upgrades, yourArmy;
	
	public GameSession(boolean active, Handler h, HUD hud, Camera c, Player p, MouseInput m){
		super(active);
		GameSession.statChoice = true;
		
		y = 0;
		this.isActive = active;
		this.hud = hud;
		this.h = h;
		this.isReloading = false;
		this.l = new SoundLoader();
		this.c = c;
		this.p = p;
		GameSession.rectX = (int)Window.screensize.getWidth()/2 + 100 + 75;
		GameSession.rectY = (int)Window.screensize.getHeight() - 100;
		GameSession.rectW = 75;
		GameSession.rectH = 25;
		
		GameMain.SceneChange = 1;
		GameMain.pDeaths = 0;
		
		a = new Animator();
		
		this.h.addEntity(p);
		
		r = new Random();
		s = new Spawner(h, hud, p);
		
		int rand = r.nextInt(20);
		
		if(rand <= 5){
			new Thread(new Runnable(){
				public void run(){
					l.load("/Sound/TheEndlesSpace.wav");
				}
			}).start();
		}if(rand <= 10){
			new Thread(new Runnable(){
				public void run(){
					l.load("/Sound/TheSpaceArmada.wav");
				}
			}).start();
		}if(rand <= 15){
			new Thread(new Runnable(){
				public void run(){
					l.load("/Sound/BeautifulTerra.wav");
				}
			}).start();
		}else{
			new Thread(new Runnable(){
				public void run(){
					l.load("/Sound/SpaceSong.wav");
				}
			}).start();
		}
	}
	
	public void render(Graphics g, Graphics2D g2d) {
		if(!statChoice || !isShop){
			g.setColor(Color.white);
			g2d.translate(c.getX(), c.getY());
			
			a.AnimateStars(g, 200, 200);
			h.render(g);
			a.AnimateBarrier(g, 64, 64);
			
			g2d.translate(-c.getX(), -c.getY());
			
			hud.render(g);
			if(isReloading){
				g.setColor(Color.red);
				g.drawString("Reloading...", 90, 75);
			}
		}
		if(statChoice){
			g.setColor(Color.darkGray);
			g.fillRect(0, 0, Window.screensize.width, Window.screensize.height);
			g.setColor(Color.white);
			g.setFont(new Font(null, Font.PLAIN, 40));
			g.drawString("What stat do you value the most?", Window.screensize.width/2 - 300, 40);
			g.setColor(Color.blue);
			g.fillRect(rectX - 400, rectY, rectW, rectH);
			g.fillRect(rectX - 300, rectY, rectW, rectH);
			g.fillRect(rectX - 200, rectY, rectW, rectH);
			g.fillRect(rectX - 100, rectY, rectW, rectH);
			g.fillRect(rectX, rectY, rectW + 50, rectH);
			g.setFont(new Font(null, Font.PLAIN, 15));
			g.setColor(Color.black);
			g.drawString("Attack", rectX - 400 + 15, rectY + 15);
			g.drawString("Defense", rectX - 300 + 15, rectY + 15);
			g.drawString("Speed", rectX - 200 + 15, rectY + 15);
			g.drawString("Shealth", rectX - 100 + 15, rectY + 15);
			g.drawString("Commanding", rectX + 15, rectY + 15);
		}
		if(isShop){
			g.setColor(Color.gray);
			g.fillRect(0, 0, Window.screensize.width, Window.screensize.height);
			g.setColor(Color.DARK_GRAY);
			for(x = 0; x < Window.screensize.getWidth(); x += 150){
				for(y = 0; y < Window.screensize.getHeight(); y += 150){
					g.fillRect(x, y, 150, 100);
				}
			}
			g.setColor(Color.red);
			g.drawString("Stats: "+stats, 25, 25);
			g.drawString("Items: "+items, 25, 175);
			g.drawString("Ships: "+ships, 25, 325);
			g.drawString("Upgrades: "+upgrades, 25, 475);
			g.drawString("Your Army: "+yourArmy, 25, 625);
		}
	}
	
	public void tick() {
		if(!statChoice || !isShop){
			r.nextInt(3);
			
			if(KeyInput.Ammo == 0){
				isReloading = true;
			}
			if(isReloading){
				reloadTime++;
			}
			if(reloadTime >= 300){
				if(p.getLevel() == 1){
					KeyInput.Ammo = 20;
					reloadTime = 0;
				}else{
					KeyInput.Ammo = Player.Ammo;
					reloadTime = 0;
				}
				isReloading = false;
			}
			
			s.tick();
			h.tick();
			hud.tick();
			
			/*for(int i = 0; i < h.faction.size(); i++){
				Faction f = h.faction.get(i);
				
				if(f instanceof Mercenary){
					for(int u = 0; u < f.getMembers().size(); u++){
						EntityObject mem = (EntityObject) f.getMembers().get(i);
					}
				}
				if(f instanceof SpaceMarauder){
					
				}
				if(f instanceof Watchmen){
					
				}
			}*/
			
			for(int i = 0; i < h.entity.size(); i++){
				if(h.entity.get(i).getId() == ID.Player){
					c.tick(h.entity.get(i));
				}
			}	
			
			if(KeyInput.isSpawn){
				this.h.addEntity(p);
				p.setHealth(HUD.preHealth);
				p.setDeath(false);
				KeyInput.isSpawn = false;
			}
		}
		if(isShop){
			
		}
	}
	
	public Player getPlayer(){
		return p;
	} 
}
