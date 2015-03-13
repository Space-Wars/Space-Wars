package com.rentarosato520.spacefighter;

import com.rentarosato520.spacefighter.entity.EntityObject;

public class Camera {
	private int x, y;
	
	public Camera(int x, int y){
		this.x = x;
		this.y = y;
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

	public void tick(EntityObject p){
		x = -p.getX() + Window.screensize.width/2 - 60;
		y = -p.getY() + Window.screensize.height/2 - 40;
	}
}
