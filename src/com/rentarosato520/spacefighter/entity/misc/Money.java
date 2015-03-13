package com.rentarosato520.spacefighter.entity.misc;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.rentarosato520.spacefighter.asset.animation.Animator;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.GameObject;
import com.rentarosato520.spacefighter.entity.ID;

public class Money extends GameObject {
	private Animator a;
	private Random r;
	private int cashValue;
	
	public Money(int x, int y, int width, int height, ID id, Handler h, int addMoney) {
		super(x, y, width, height, h);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.a = new Animator();
		this.r = new Random();
		
		this.cashValue = r.nextInt(1000) + addMoney;
	}


	public void tick() {
		
	}


	public void render(Graphics g) {
		a.AnimateMoney(g, x + 11, y + 11, width, height);
	}
	
	public int getCashValue() {
		return cashValue;
	}


	public void setCashValue(int cashValue) {
		this.cashValue = cashValue;
	}


	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
