package com.rentarosato520.spacefighter.entity.ships;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.asset.animation.Animator;
import com.rentarosato520.spacefighter.asset.animation.Assets;
import com.rentarosato520.spacefighter.engine.GameMain;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.EntityObject;
import com.rentarosato520.spacefighter.entity.GameObject;
import com.rentarosato520.spacefighter.entity.ID;
import com.rentarosato520.spacefighter.entity.lasers.Broadside;
import com.rentarosato520.spacefighter.entity.misc.Meteor;
import com.rentarosato520.spacefighter.entity.misc.Money;
import com.rentarosato520.spacefighter.entity.misc.ShipPart;
import com.rentarosato520.spacefighter.factions.Faction;
import com.rentarosato520.spacefighter.factions.Mercenary;
import com.rentarosato520.spacefighter.factions.SpaceMarauder;
import com.rentarosato520.spacefighter.factions.Watchmen;

public class Goliath extends EntityObject{
	private Handler h;
	private Random r;
	private Animator a;
	private EntityObject object;
	private int desX, desY;
	private int lx, ly;
	private int health;
	private boolean animateReady, ObjectX, ObjectY;
	private int healthDis;
	public static int isDead, ran, ra, rn;
	public static boolean thisDeath;
	public static boolean choAttack, choDefense, choSpeed, choShealth, choCommanding;
	
	
	public Goliath(int x, int y, int width, int height, Handler h, Faction f) {
		super(x, y, width, height, h);
		
		r = new Random();
		
		a = new Animator();
		
		this.setDeath(false);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.h = h;
		this.health = r.nextInt(1000) + 1000;
		this.animateReady = false;
		this.killLim = r.nextInt(4) + 1;
		this.f = f;
		this.f.addMember(this);
		
		this.attackLim = 70;
		this.defenseLim = 85;
		this.shealthLim = 10;
		this.commandingLim = 30;
		this.currency = 0;
		
		this.attack = r.nextInt(attackLim) + 15;
		this.defense = r.nextInt(defenseLim) + 20;
		this.speed = 1;
		this.shealth = r.nextInt(shealthLim);
		this.commanding = r.nextInt(commandingLim) + 10;
		
		this.velX = speed;
		this.velY = speed;
		desX = r.nextInt((int) Window.screensize.width + 900);
		desY = r.nextInt((int)Window.screensize.height + 900);
		rn = r.nextInt(5);
		
		if(rn == 0){
			if(attack < 15){
				attack = r.nextInt(attackLim) + 15;
			}
		}
		if(rn == 1){
			if(defense < 15){
				defense = r.nextInt(defenseLim) + 15;
			}
		}
		/*if(rn == 2){
			if(speed < 15){
				speed = r.nextInt(speedLim) + 15;
			}
		}*/
		if(rn == 3){
			if(shealth < 15){
				shealth = r.nextInt(shealthLim) + 15;
			}
		}
		if(rn == 4){
			if(commanding < 15){
				commanding = r.nextInt(commandingLim) + 15;
			}
		}
		
		if(choAttack){
			if(attack < 15){
				attack = r.nextInt(attackLim) + 15;
			}
		}
		if(choDefense){
			if(defense < 15){
				defense = r.nextInt(defenseLim) + 15;
			}
		}
		if(choSpeed){
			if(speed < 15){
				speed = r.nextInt(speedLim) + 15;
			}
			
		}
		if(choShealth){
			if(shealth < 15){
				shealth = r.nextInt(shealthLim) + 15;
			}
		}
		if(choCommanding){
			if(commanding < 15){
				commanding = r.nextInt(commandingLim) + 15;
			}
			
		}
		healthDis = health;
	}

	
	public void tick() {
		ObjectX = GameMain.objectCollision(0, Window.screensize.width + 1000, x);
		ObjectY = GameMain.objectCollision(0, Window.screensize.height + 1000, y);
		
		if(ObjectX){
			velX *= -1;
		}
		if(ObjectY){
			velY *= -1;
		}
		
		x += velX;
		y += velY;
		
		EnemySize = f.isEnemy.size();
		if(coolDown > 0){
			coolDown--;
			coolDown = GameMain.clamp(0, 1000, coolDown);
		}
		if(this.target == null){
			for(Faction ef : f.isEnemy){
				for(EntityObject object : ef.members){
					if(object.getX() >= this.x && object.getX() <= this.x + 300){
						this.setTarget(object);
					}
					if(object.getY() >= this.y && object.getY() <= this.y + 300){
						this.setTarget(object);
					}
				}
			}
		}
		
		if(this.target != null && coolDown <= 0){
			velX = 0;
			velY = 0;
			targetX = this.target.getX();
			targetY = this.target.getY();
			if(targetX > x && targetX < x + 300){
				broadsideRight();
			}
			if(targetX < x  && targetX > x - 300){
				broadsideLeft();
			}
			if(targetY > y && targetY < y + 300){
				frontCannons();
			}
			if(targetY < y  && targetY > y - 300){
				backCannons();
			}else{
				if(y < targetY && velY <= -speed){velY *= -1;}
				if(y > targetY && velY >= speed){velY *= -1;}
				if(x < targetX && velX <= -speed){velX *= -1;}
				if(x > targetX && velX >= speed){velX *= -1;}
				target = null;
				velX = 1;
				velY = 1;
			}
				coolDown = 500;
		}
		if(this.target == null){
			wander();
		}
		System.out.println("velX: "+velX+" velY: "+velY);
		/*rand = r.nextInt(100);
		if(kills == killLim){
			level++;
			levelUp();
			kills = 0;
			killLim += killLim/2;
		}
		
		x += velX;
		y += velY;
		
		if(y < vectY && velY == -speed){velY *= -1;}
		if(y > vectY && velY == speed){velY *= -1;}
		if(x < vectX && velX == -speed){velX *= -1;}
		if(x > vectX && velX == speed){velX *= -1;}
		if(x == vectX){
			if(r.nextInt(2) == 0){
				vectX = r.nextInt(Window.screensize.width);
			}
		}
		if(y == vectY){
			if(r.nextInt(2) == 0){
				vectY = r.nextInt(Window.screensize.height);
			}
		}
		
		if(!wasFire){
			for(int i = 0; i < h.entity.size(); i++){
				EntityObject tempObject = h.entity.get(i);
			}
		}
		if(wasFire){
			reload++;
			if(reload == 1000){
				wasFire = false;
			}
		}*/
		
		if(velX >= -100 && velX < 0 && velY == 0){isFacing = 3;}
		if(velX >= 1 && velY == 0){isFacing = 2;}
		if(velX == 0 && velY >= -100 && velY < 0){isFacing = 0;}
		if(velX == 0 && velY >= 1){isFacing = 1;}
		
		if(this.isDamage > 0 && this.isDamage > defense){
			health -= isDamage - (defense / 2);
			isDamage = 0;
		}
	}
	
	public void levelUp(){
		this.health +=  r.nextInt(300) + 100;
		this.attack += r.nextInt(attackLim);
		this.defense += r.nextInt(defenseLim) + 1;
		this.speed += r.nextInt(speedLim) + 2;
		this.shealth += r.nextInt(shealthLim);
		this.commanding += r.nextInt(commandingLim);
		
		
		if(choAttack){
			attack += (r.nextInt(attackLim) + 15)/2;
		}
		if(choDefense){
			defense += (r.nextInt(defenseLim) + 15)/2;
		}
		if(choSpeed){
			speed += (r.nextInt(speedLim) + 15)/2;
		}
		if(choShealth){
			shealth += (r.nextInt(shealthLim) + 15)/2;
		}
		if(choCommanding){
			commanding += (r.nextInt(commandingLim) + 15)/2;	
		}
	}
	
	public void wander(){
		//tX = desX - this.x;
		//tY = desY - this.y;
		if(y < desY && velY <= -speed){velY *= -1;}
		if(y > desY && velY >= speed){velY *= -1;}
		if(x < desX && velX <= -speed){velX *= -1;}
		if(x > desX && velX >= speed){velX *= -1;}
		
		if(x == desX && x <= desX + 10){
			desX = r.nextInt(Window.screensize.width + 900);
		}
		if(y >= desY && y <= desY + 10){
			desY = r.nextInt(Window.screensize.height + 900);
		}
	}
	
	public void Collision(Graphics g){
		for(GameObject tempObject : h.object){
				
	
				if(tempObject.getId() == ID.Money){
					Money m = (Money) tempObject;
					if(getBounds().intersects(tempObject.getBounds())){
						currency += m.getCashValue();
						h.removeObject(tempObject);
					}
				}
				if(tempObject.getId() == ID.ShipPart){
					ShipPart s = (ShipPart) tempObject;
					if(getBounds().intersects(tempObject.getBounds())){
						numShipParts++;
						h.removeObject(tempObject);
					}
				}
				if(tempObject.getId() == ID.Meteor){
					Meteor m = (Meteor) tempObject;
					if(getBounds().intersects(tempObject.getBounds())){
						Rectangle OverLap = getBounds().intersection(tempObject.getBounds());
						if(OverLap.height >= OverLap.width){
							if(m.isDoesDamage() && !m.isExplodes() && !m.isOnFire()){
								this.health -= m.getDamage();
								velX *= -2;
							}
							if(m.isDoesDamage() && m.isExplodes() || m.isDoesDamage() && m.isOnFire()){
								if(m.isExplodes()){
									a.AnimateExplosion(g, tempObject.getX(), tempObject.getY(), 32, 32);
									this.health -= m.getDamage();
									if(a.isFin[0]){
										h.removeObject(tempObject);
										a.isFin[0] = false;
									}
								}
								if(m.isOnFire()){
									this.health -= m.getDamage();
									velX *= -2;
								}
							}else{
								velX *= -2;
							}
						}
						if(OverLap.width >= OverLap.height){
							if(m.isDoesDamage() && !m.isExplodes() && !m.isOnFire()){
								this.health -= m.getDamage();
								velY *= -2;
							}
							if(m.isDoesDamage() && m.isExplodes() || m.isDoesDamage() && m.isOnFire()){
								if(m.isExplodes()){
									this.health -= m.getDamage();
									m.setExplodeNow(true);
								}
								if(m.isOnFire()){
								this.health -= m.getDamage();
								velY *= -2;
							}
						}else{
							velY *= -2;
						}
					}
				}
			}
		}
	}
	
	public void broadsideRight(){
		//for(int i = 0; i < amount; i++){
			h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 0, this.f));
			h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 0, this.f));
			h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 0, this.f));
			h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 0, this.f));
			h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 0, this.f));
		//}
	}
	
	public void broadsideLeft(){
		//or(int i = 0; i < amount; i++){
			h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 1, this.f));
			h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 1, this.f));
			h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 1, this.f));
			h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 1, this.f));
			h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 1, this.f));
		//}
	}																																																																																															
	
	public void frontCannons(){

		h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 2, this.f));
		h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 2, this.f));
	}
	
	public void backCannons(){

		h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 3, this.f));
		h.addObject(new Broadside(x + 80, r.nextInt(height/2)+y - 30, 5, 9, h, this, 3, this.f));
	}
	
	public void render(Graphics g) {	
		if(velX >= -100 && velX < 0 && velY == 0){isFacing = 3;}
		if(velX >= 1 && velY == 0){isFacing = 2;}
		if(velX == 0 && velY >= -100 && velY < 0){isFacing = 0;}
		if(velX == 0 && velY >= 1){isFacing = 1;}
		
		if(velX >= 1 && velY >= 1){isFacing = 2;} 
		if(velX >= -100 && velX < 0 && velY >= -100 && velY < 0){isFacing = 1;}
		if(velX >= -100 && velX < 0 && velY >= 1){isFacing = 3;} 
		if(velX >= 1 && velY >= -100 && velY < 0){isFacing = 0;}
		Collision(g);
		g.setColor(Color.white);
		g.setFont(new Font(null, Font.PLAIN, 30));
		g.drawString("Faction: "+Faction.getFactionName(f), x + 45, y - 30);
		g.drawString("The Goliath", x + 60, y);
		g.drawString("Lvl: "+level, x + 250, y);
		g.setColor(Color.red);
		g.fillRect(x + 40, y + 20 , healthDis/4, 10);
		g.setColor(Color.green);
		g.fillRect(x + 40, y + 20 , health/4, 10);
		if(isFacing == 3 && this.f instanceof Watchmen){
			g.drawImage(Assets.Goliath, x + 10, y, width, height, null);
			//a.AnimateRocketLeft(g, x + 90, y + 33, 32, 32);
		}
		if(isFacing == 2 && this.f instanceof Watchmen){
			g.drawImage(Assets.Goliath3, x + 10, y, width, height, null);
			//a.AnimateRocketRight(g, x + 30, y + 33, 32, 32);
		}
		if(isFacing == 1 && this.f instanceof Watchmen){
			g.drawImage(Assets.Goliath1, x + 10, y, width, height, null);
			//a.AnimateRocketDown(g, x + 42, y + 20, 32, 32);
		}
		if(isFacing == 0 && this.f instanceof Watchmen){
			g.drawImage(Assets.Goliath2,  x + 10, y, width, height, null);
			//a.AnimateRocket(g, x + 45, y + 80, 32, 32);
		}
		
		if(isFacing == 3 && this.f instanceof Mercenary){
			g.drawImage(Assets.Goliath4, x + 10, y, width, height, null);
			//a.AnimateRocketLeft(g, x + 90, y + 33, 32, 32);
		}
		if(isFacing == 2 && this.f instanceof Mercenary){
			g.drawImage(Assets.Goliath7, x + 10, y, width, height, null);
			//a.AnimateRocketRight(g, x + 30, y + 33, 32, 32);
		}
		if(isFacing == 1 && this.f instanceof Mercenary){
			g.drawImage(Assets.Goliath5, x + 10, y, width, height, null);
			//a.AnimateRocketDown(g, x + 42, y + 20, 32, 32);
		}
		if(isFacing == 0 && this.f instanceof Mercenary){
			g.drawImage(Assets.Goliath6,  x + 10, y, width, height, null);
			//a.AnimateRocket(g, x + 45, y + 80, 32, 32);
		}
		
		if(isFacing == 3 && this.f instanceof SpaceMarauder){
			g.drawImage(Assets.Goliath8, x + 10, y, width, height, null);
			//a.AnimateRocketLeft(g, x + 90, y + 33, 32, 32);
		}
		if(isFacing == 2 && this.f instanceof SpaceMarauder){
			g.drawImage(Assets.Goliath11, x + 10, y, width, height, null);
			//a.AnimateRocketRight(g, x + 30, y + 33, 32, 32);
		}
		if(isFacing == 1 && this.f instanceof SpaceMarauder){
			g.drawImage(Assets.Goliath9, x + 10, y, width, height, null);
			//a.AnimateRocketDown(g, x + 42, y + 20, 32, 32);
		}
		if(isFacing == 0 && this.f instanceof SpaceMarauder){
			g.drawImage(Assets.Goliath10,  x + 10, y, width, height, null);
			//a.AnimateRocket(g, x + 45, y + 80, 32, 32);
		}
		if(animateReady){
			a.AnimateMini(g, lx, ly, ran, ran);
		}
		if(health <= 0){
			setDeath(true);
			Goliath.thisDeath = true;
			a.AnimateExplosion(g, x, y, width, height);
			
			if(Animator.isFin[0]){
				ra = r.nextInt(10);
				
				isDead++;
				
				if(ra <= 7){
					h.addObject(new Money(x, y, 32, 32, ID.Money, h, currency));
				}else{
					for(int i = 0; i < numShipParts; i++){
						h.addObject(new ShipPart(x, y, 32, 32, ID.ShipPart, h));
					}
				}
				h.removeEntity(this);
				Animator.isFin[0] = false;
			}
		}
		g.setColor(Color.red);
	}
	
	
	public Rectangle getBounds() {
		return new Rectangle(x + 50, y + 50, width - 100, height - 100);
	}

}
