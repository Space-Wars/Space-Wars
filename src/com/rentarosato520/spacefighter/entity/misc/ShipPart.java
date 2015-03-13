package com.rentarosato520.spacefighter.entity.misc;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.rentarosato520.spacefighter.asset.animation.Animator;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.GameObject;
import com.rentarosato520.spacefighter.entity.ID;

public class ShipPart extends GameObject{
	private Animator a;
	private String[] parts = {"Wing","Engine","Laser Gun","Nose","Armor Plate", "Reinforced Steel",
			"Flag Emblem"};
	
	public ShipPart(int x, int y, int width, int height, ID id, Handler h) {
		super(x, y, width, height, h);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.a = new Animator();
		new Random();
	}


	public void tick() {
		
	}


	public void render(Graphics g) {
		a.AnimateShipParts(g, x + 11, y + 11, width, height);
	}
	
	public String getPart(int n) {
		return parts[n];
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
