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

public class Money extends GameObject {
	private Animator a;
	private Random r;
	private Handler h;
	private int cashValue;
	private float pX, pY;
	
	public Money(float x, float y, int width, int height, ID id, Handler h, int addMoney) {
		super(x, y, width, height, h);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.a = new Animator();
		this.r = new Random();
		this.pX = 0;
		this.pY = 0;
		this.velX = 0;
		this.velY = 0;
		this.h = h;
		
		this.cashValue = r.nextInt(1000) + addMoney;
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
		if(x - 10 > pX && x - 10 < pX + 300){velX = -3;}
		if(x + 10 < pX && x + 10 > pX - 300){velX = 3;}
		if(y - 10 > pY && y - 10 < pY + 300){velY = -3;}
		if(y + 10 < pY && y + 10  > pY - 300){velY = 3;}
	}


	public void render(Graphics g) {
		a.AnimateMoney(g,(int) x + 11,(int) y + 11, width, height);
	}
	
	public int getCashValue() {
		return cashValue;
	}


	public void setCashValue(int cashValue) {
		this.cashValue = cashValue;
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, width, height);
	}

}
