package com.rentarosato520.spacefighter.entity.lasers;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.asset.animation.Assets;
import com.rentarosato520.spacefighter.engine.GameMain;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.EntityObject;
import com.rentarosato520.spacefighter.entity.GameObject;
import com.rentarosato520.spacefighter.entity.misc.Meteor;
import com.rentarosato520.spacefighter.entity.ships.Goliath;
import com.rentarosato520.spacefighter.factions.Faction;

public class Broadside extends GameObject{
	private Handler h;
	private Faction ef, f;
	private EntityObject g;
	private int damage;
	private int side;

	public Broadside(float x, float y, int width, int height, Handler h, Goliath g, int l, Faction f) {
		super(x, y, width, height,  h);
		
		this.x = x + 100;
		this.y = y + 50;
		this.width = width;
		this.height = height;
		this.h = h;
		this.g = g;
		this.damage = g.getAttack();
		this.side = l;
		this.f = f;
	}

	
	public void tick() {
		if(side == 0){
			velX = 5;
		}
		if(side == 1){
			velX = -5;
		}
		if(side == 2){
			velY = -5;
		}
		if(side == 3){
			velY = 5;
		}
		if(velX > 0 || velY > 0){
			x += velX += 0.2;
			y += velY += 0.2;
		}else if(velX < 0 || velY < 0){
			y += velY += -0.2;
			x += velX += -0.2;
		}else{
			y += velY;
			x += velX;
			// 0x1A1 || 0xC0
		}
		velX = GameMain.clampF(-10, 10, velX);
		velY = GameMain.clampF(-10, 10, velY);
		
		for(Faction ef : f.isEnemy){
			for(EntityObject eo : ef.members){
				if(getBounds().intersects(eo.getBounds())){
					eo.setDamage(damage, g);
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
		
		if(velX == 0 && velY == 0){
			h.removeObject(this);
		}
		if(x > Window.screensize.width + 1200 || x < 0 - 1200){
			h.removeObject(this);
		}
		if(y > Window.screensize.height + 1200 || y < 0 - 1200){
			h.removeObject(this);
		}
	}

	
	public void render(Graphics g) {
		g.drawImage(Assets.Broad,(int) x,(int) y, null);
	}

	
	public Rectangle getBounds() {
		return new Rectangle((int) x,(int) y, width, height);
	}
}
