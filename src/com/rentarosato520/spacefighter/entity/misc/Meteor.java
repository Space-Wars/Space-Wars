package com.rentarosato520.spacefighter.entity.misc;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.asset.animation.Animator;
import com.rentarosato520.spacefighter.asset.animation.Assets;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.GameObject;
import com.rentarosato520.spacefighter.entity.ID;

public class Meteor extends GameObject{
	private Handler h;
	private Random r;
	private Animator a;
	private boolean doesDamage, explodes, onFire, explodeNow;
	private int ra, rd, rn;
	private int directionSpawn, damage;

	public Meteor(int x, int y, int width, int height, ID id, Handler h, int s) {
		super(x, y, width, height,  h);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.h = h;
		this.id = id;
		this.r = new Random();
		this.directionSpawn = s;
		this.a = new Animator();

		ra = r.nextInt(2);
		rn = r.nextInt(2);
		rd = r.nextInt(2);
		
		if(ra == 1){
			doesDamage = true;
			damage = r.nextInt(40)+10;
			
			if(rn == 1){
				explodes = true;
				damage = r.nextInt(50)+50;
			}else{
				explodes = false;
			}
			
			if(rd == 1){
				onFire = true;
				damage = r.nextInt(20)+10;
			}else{
				onFire = false;
			}
			
		}else{
			doesDamage = false;
		}
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(directionSpawn == 0){
			velX = 3;
		}
		if(directionSpawn == 1){
			velX = -3;
		}
		if(directionSpawn == 2){
			velY = 3;
		}
		if(directionSpawn == 3){
			velY = -3;
		}else{
			velX = 0;
			velY = 0;
		}
		
		if(x > Window.screensize.getWidth() + 1200 || x < 0 - 1200){
			h.removeObject(this);
		}
		if(y > Window.screensize.getHeight() + 1200 || y < 0 - 1200){
			h.removeObject(this);
		}
	}

	public void render(Graphics g) {
		g.drawImage(Assets.meteor, x, y, width, height, null);
		if(explodeNow){
			a.AnimateExplosion(g, x, y, width, height);
			if(Animator.isFin[0]){
				h.object.remove(this);
				Animator.isFin[0] = false;
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public boolean isDoesDamage() {
		return doesDamage;
	}

	public void setDoesDamage(boolean doesDamage) {
		this.doesDamage = doesDamage;
	}

	public boolean isExplodes() {
		return explodes;
	}

	public void setExplodes(boolean explodes) {
		this.explodes = explodes;
	}

	public boolean isOnFire() {
		return onFire;
	}

	public void setOnFire(boolean onFire) {
		this.onFire = onFire;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean isExplodeNow() {
		return explodeNow;
	}

	public void setExplodeNow(boolean explodeNow) {
		this.explodeNow = explodeNow;
	}
	
	

}
