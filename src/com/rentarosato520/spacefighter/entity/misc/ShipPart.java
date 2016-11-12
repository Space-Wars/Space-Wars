package com.rentarosato520.spacefighter.entity.misc;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.rentarosato520.spacefighter.asset.animation.Animator;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.EntityObject;
import com.rentarosato520.spacefighter.entity.GameObject;
import com.rentarosato520.spacefighter.entity.ID;
import com.rentarosato520.spacefighter.entity.ships.Player;

public class ShipPart extends GameObject{
	private Animator a;
	private Handler h;
	private String[] parts = {"Wing","Engine","Laser Gun","Nose","Armor Plate", "Reinforced Steel",
			"Flag Emblem"};
	private float pX, pY;
	
	public ShipPart(float x, float y, int width, int height, ID id, Handler h) {
		super(x, y, width, height, h);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.h = h;
		this.a = new Animator();
		new Random();
	}


	public void tick() {
		x += velX;
		y += velY;
		for(EntityObject p : h.entity){
			if(p instanceof Player){
				pX = p.getX();
				pY = p.getY();
			}
		}
		if(x > pX && x < pX + 300){velX = -3;}
		if(x < pX && x > pX + 300){velX = 3;}
		if(y > pY && y < pY + 300){velY = -3;}
		if(y < pY && y > pY + 300){velY = 3;}
		else{velX = 0; velY = 0;}
	}


	public void render(Graphics g) {
		a.AnimateShipParts(g,(int) x + 11,(int) y + 11, width, height);
	}
	
	public String getPart(int n) {
		return parts[n];
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, width, height);
	}
}
