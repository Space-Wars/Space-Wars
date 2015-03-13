package com.rentarosato520.spacefighter.entity.lasers;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.asset.animation.Assets;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.EntityObject;
import com.rentarosato520.spacefighter.entity.GameObject;
import com.rentarosato520.spacefighter.entity.ships.Player;
import com.rentarosato520.spacefighter.factions.Faction;

public class PlayerLaser extends GameObject{
	private Handler h;
	private Random r;
	private Player p;
	private Faction f, ef;
	private int shotDam, laserAim;
	
	public PlayerLaser(int x, int y, int width, int height, Handler h, EntityObject p) {
		super(x, y, width, height, h);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velY = 0;
		this.h = h;
		this.p = (Player) p;

		r = new Random();
		
		this.f = this.p.getPlayerFaction();
		
		this.shotDam = r.nextInt(20)+20/5 + Player.pAttack;
		
		if(p.getIsFacing() == 0){
			this.velY = -10;
		}	
		if(p.getIsFacing() == 1){
			this.velY = 10;
		}
		if(p.getIsFacing() == 2){
			this.velX = 10;
		}
		if(p.getIsFacing() == 3){
			this.velX = -10;
		}
		
		if(p.getIsFacing() == 4){
			this.velY = 10; 
			this.velX = -10;
		}	
		if(p.getIsFacing() == 5){
			this.velY = -10; 
			this.velX = -10;
		}
		if(p.getIsFacing() == 6){
			this.velY = 10; 
			this.velX = 10;
		}
		if(p.getIsFacing() == 7){
			this.velY = -10; 
			this.velX = 10;
		}
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		if(velX == 0 && velY == 0){
			h.removeObject(this);
		}
		
		for(Faction ef : f.isEnemy){
			for(EntityObject eo : ef.members){
				if(getBounds().intersects(eo.getBounds())){
					eo.setDamage(shotDam);
					h.removeObject(this);
				}
			}	
		}
		
		if(x > Window.screensize.getWidth() + 1200 || x < 0 - 1200){
			h.removeObject(this);
		}
		if(y > Window.screensize.getHeight() + 1200 || y < 0 - 1200){
			h.removeObject(this);
		}
	}

	public int getShotDam() {
		return shotDam;
	}


	public void setShotDam(int shotDam) {
		this.shotDam = shotDam;
	}
	
	public void render(Graphics g) {
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
			g.drawImage(Assets.playerLaser, x, y, width, height, null);
		}
		if(laserAim == 1){
			g.drawImage(Assets.playerLaser1, x, y, width, height, null);
		}
		if(laserAim == 2){
			g.drawImage(Assets.playerLaser2, x, y, width, height, null);
		}
		if(laserAim == 3){
			g.drawImage(Assets.playerLaser3, x, y, width, height, null);
		}
	}


	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public Rectangle getBounds2(){
		return new Rectangle(x + 85, y, width, height);
	}
}
