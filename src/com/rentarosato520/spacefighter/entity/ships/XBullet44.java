package com.rentarosato520.spacefighter.entity.ships;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rentarosato520.spacefighter.asset.animation.Assets;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.EntityObject;

public class XBullet44 extends EntityObject{
	private Handler h;

	public XBullet44(float x, float y, int width, int height, Handler h) {
		super(x, y, width, height, h);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.h = h;
	}
	
	public void tick() {
		x += velX;
		y += velY;
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.XBullet44,(int) x,(int) y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, width, height);
	}
}
