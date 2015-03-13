package com.rentarosato520.spacefighter.entity.lasers;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.asset.animation.Assets;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.EntityObject;
import com.rentarosato520.spacefighter.entity.ships.Goliath;
import com.rentarosato520.spacefighter.factions.Faction;

public class Broadside extends EntityObject{
	private Handler h;
	private Faction ef;
	private int damage;
	private int side;
	private static int MaxNum, Broadnum;

	public Broadside(int x, int y, int width, int height, Handler h, Goliath g, int l, Faction f) {
		super(x, y, width, height,  h);
		
		this.x = x + 100;
		this.y = y + 50;
		this.width = width;
		this.height = height;
		this.h = h;
		this.damage = g.getAttack();
		this.side = l;
		Broadside.MaxNum = 10;
		Broadside.Broadnum++;
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
		x += velX;
		y += velY;
		
		
		for(Faction ef : f.isEnemy){
			for(EntityObject eo : ef.members){
				if(getBounds().intersects(eo.getBounds())){
					eo.setDamage(damage);
					h.removeObject(this);
				}
			}	
		}
		
		if(Broadside.Broadnum > Broadside.MaxNum){
			h.removeObject(this);
		}
		
		if(velX == 0 && velY == 0){
			h.removeObject(this);
		}
		
		if(x > Window.screensize.getWidth() + 1200 || x < 0 - 1200){
			h.removeObject(this);
		}
		if(y > Window.screensize.getHeight() + 1200 || y < 0 - 1200){
			h.removeObject(this);
		}
	}

	
	public void render(Graphics g) {
		g.drawImage(Assets.Broad, x, y, null);
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
