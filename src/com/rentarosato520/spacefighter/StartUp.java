package com.rentarosato520.spacefighter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.rentarosato520.spacefighter.asset.animation.Animator;
import com.rentarosato520.spacefighter.asset.sound.SoundLoader;

public class StartUp extends GameScene{
	public static int buttonColor = 255;
	int colorValue = 255;
	int redValue = 0;
	boolean isMim = true;
	boolean upMode = true;
	boolean sideMode = true;
	private Animator a;
	private int scale;

	
	public StartUp(boolean active, Animator a, final SoundLoader l){
		super(active);
		isMim = false;
		
		this.a = a;
		this.scale = 40;
		
		this.isActive = active;
		
		new Thread(new Runnable(){
			public void run(){
				l.load("/Sound/SpaceBack.wav");
			}
		}).start();
	}

	public void render(Graphics g, Graphics2D g2d) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Window.screensize.width, Window.screensize.height);
		a.AnimateStart(g);
		g.setFont(new Font(null, Font.PLAIN, scale));
		g.setColor(new Color(redValue, 0, colorValue));
		g.drawString("Space Fighter", Window.screensize.width/2 - 145, Window.screensize.height/8);
		g.setFont(new Font(null, Font.PLAIN, 40));
		g.setColor(Color.YELLOW);
		g.fillRect(Window.screensize.width/2 - 115, Window.screensize.height - 215, 270, 90);
		g.setColor(new Color(0, 0, buttonColor));
		g.fillRect(Window.screensize.width/2 - 105, Window.screensize.height - 205, 250, 70);
		g.setColor(Color.gray);
		g.drawString("Start", Window.screensize.width/2 - 30, Window.screensize.height - 155);
	}

	public void tick(){

	}
}
