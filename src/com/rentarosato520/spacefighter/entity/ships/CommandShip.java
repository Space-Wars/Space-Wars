package com.rentarosato520.spacefighter.entity.ships;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.rentarosato520.spacefighter.asset.animation.Assets;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.EntityObject;

public class CommandShip extends EntityObject{
	private Handler h;
	private Random r;
	private boolean isPlayer;
	
	public CommandShip(float x, float y, int width, int height, Handler h, int ps) {
		super(x, y, width, height, h);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.h = h;
		this.r = new Random();
		
		if(ps == 1){
			//IS player
			isPlayer = true;
		}else{
			isPlayer = false;
		}
	}

	public void setMainTarget(){
		
	}

	public void tick() {
		x += velX;
		y += velY;
	}


	public void render(Graphics g) {
		g.drawImage(Assets.com,(int) x,(int) y, null);
	}


	public Rectangle getBounds() {
		return new Rectangle((int) x,(int) y, width, height);
	}

}
