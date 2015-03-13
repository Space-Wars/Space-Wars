package com.rentarosato520.spacefighter.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rentarosato520.spacefighter.engine.Handler;

public abstract class GameObject{
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected ID id;
	protected int velX;
	protected int velY;
	protected boolean isCollidingX, isCollidingY;
	
	public GameObject(int x, int y, int width, int height, Handler h){
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public int getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public int getVelY() {
		return velY;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}

	public boolean isCollidingY() {
		return isCollidingY;
	}

	public void setCollidingY(boolean isCollidingY) {
		this.isCollidingY = isCollidingY;
	}
	
	public boolean isCollidingX() {
		return isCollidingX;
	}

	public void setCollidingX(boolean isCollidingX) {
		this.isCollidingX = isCollidingX;
	}
}
