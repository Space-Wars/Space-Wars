package com.rentarosato520.spacefighter.entity.lasers;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.asset.animation.Animator;
import com.rentarosato520.spacefighter.asset.animation.Assets;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.EntityObject;
import com.rentarosato520.spacefighter.entity.GameObject;
import com.rentarosato520.spacefighter.entity.misc.Meteor;
import com.rentarosato520.spacefighter.factions.Faction;

public class NpcLaser extends GameObject{
	private Handler h;
	private Faction f, ef;
	private EntityObject firer;
	private int laserAim;
	private int currentTarget;
	private float vectorX, vectorY;
	private int damage;
	
	public NpcLaser(float x, float y, int width, int height, Handler h, Faction f, float targetX, float targetY, int attack, EntityObject firer) {
		super(x, y, width, height, h);
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.h = h;
		this.f = f;
		this.vectorX = targetX;
		this.vectorY = targetY;
		this.damage = attack;
		this.firer = firer;
		new Animator();
		
		aimAI();
	}

	
	public void tick() {
		//Collision(g);
		x += velX;
		y += velY;
		
		if(velX == 0 && velY == 0){
			h.removeObject(this);
		}
		
		for(Faction ef : f.isEnemy){
			for(EntityObject eo : ef.members){
				if(getBounds().intersects(eo.getBounds())){
					eo.setDamage(damage, firer);
					h.removeObject(this);
				}
			}	
		}
		
		for(GameObject o : h.object){
			if(this.getBounds().intersects(o.getBounds()) && o instanceof Meteor){
				Meteor m = (Meteor) o;
				m.setHealth(damage);
				h.removeObject(this);
			}
		}
		
		if(x > Window.screensize.getWidth() + 1200 || x < 0 - 1200){
			h.removeObject(this);
		}
		if(y > Window.screensize.getHeight() + 1200 || y < 0 - 1200){
			h.removeObject(this);
		}
	}
	
	public void aimAI(){
		if(x < vectorX){velX = 10;}
		if(x > vectorX){velX = -10;}
		if(y < vectorY){velY = 10;}
		if(y > vectorY){velY = -10;}
		if(x <= vectorX + 60 && x >= vectorX - 60){velX = 0;}
		if(y <= vectorY + 60 && y >= vectorY - 60){velY = 0;}
	}

	public void render(Graphics g) {
		/*if(p.isRemoveShot()){
			h.removeObject(this);
			p.setRemoveShot(false);
		}*/
		if(velX == 10 && velY == 10 || velX == -10 && velY == -10){
			laserAim = 3;
		}
		if(velX == 10 && velY == -10 || velX == -10 && velY == 10){
			laserAim = 2;
		}
		if(velX == 10 && velY == 0 || velX == -10 && velY == 0){
			laserAim = 1;
		}
		if(velX == 0 && velY == 10 || velX == 0 && velY == -10){
			laserAim = 0;
		}
		
		if(laserAim == 0){
			g.drawImage(Assets.playerLaser,(int) x,(int) y, width, height, null);
		}
		if(laserAim == 1){
			g.drawImage(Assets.playerLaser1,(int) x,(int) y, width, height, null);
		}
		if(laserAim == 2){
			g.drawImage(Assets.playerLaser2,(int) x,(int) y, width, height, null);
		}
		if(laserAim == 3){
			g.drawImage(Assets.playerLaser3,(int) x,(int) y, width, height, null);
		}
	}
	
	
	
	public int getDamage() {
		return damage;
	}


	public void setDamage(int damage) {
		this.damage = damage;
	}


	public int getCurrentTarget() {
		return currentTarget;
	}


	public void setCurrentTarget(int currentTarget) {
		this.currentTarget = currentTarget;
	}


	public Rectangle getBounds() {
		return new Rectangle((int) x,(int) y, width, height);
	}
}
