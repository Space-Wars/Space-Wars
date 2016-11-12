package com.rentarosato520.spacefighter.entity.ships;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.asset.animation.Assets;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.EntityObject;
import com.rentarosato520.spacefighter.entity.ID;

public class SupplyShip extends EntityObject{
	private Handler h;
	private Random r;
	private int directionSpawn;
	private int healthBonus, cashBonus, shipBonus;
	
	public SupplyShip(float x, float y, int width, int height, ID id, Handler h, int d) {
		super(x, y, width, height, h);
		this.x = x;
		this.y = y;
		this.width = width * 2;
		this.height = height * 2;
		this.id = id;
		this.h = h;
		this.directionSpawn = d;
		this.r = new Random();
		
		healthBonus = r.nextInt(100)+50;
		cashBonus = r.nextInt(500)+500;
		shipBonus = r.nextInt(3)+1;
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		if(directionSpawn == 0){
			velX = 7;
		}
		if(directionSpawn == 1){
			velX = -7;
		}
		if(directionSpawn == 2){
			velY = 7;
		}
		if(directionSpawn == 3){
			velY = -7;
		}
		
		if(x > Window.screensize.getWidth() + 200 || x < 0 - 200){
			h.removeObject(this);
		}
		if(y > Window.screensize.getHeight() + 200 || y < 0 - 200){
			h.removeObject(this);
		}
	}

	
	public void render(Graphics g) {
		g.drawImage(Assets.SupplyShip,(int) x,(int) y, null);
	}

	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, width, height);
	}


	public int getHealthBonus() {
		return healthBonus;
	}


	public void setHealthBonus(int healthBonus) {
		this.healthBonus = healthBonus;
	}


	public int getCashBonus() {
		return cashBonus;
	}


	public void setCashBonus(int cashBonus) {
		this.cashBonus = cashBonus;
	}


	public int getShipBonus() {
		return shipBonus;
	}


	public void setShipBonus(int shipBonus) {
		this.shipBonus = shipBonus;
	}
	
	
}
