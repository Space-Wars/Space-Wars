package com.rentarosato520.spacefighter.entity.ships;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.EntityObject;

public class StealthDrone extends EntityObject{

	public StealthDrone(int x, int y, int width, int height, Handler h) {
		super(x, y, width, height, h);
	}

	public void tick() {

		
	}
	
	public void render(Graphics g) {

		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
