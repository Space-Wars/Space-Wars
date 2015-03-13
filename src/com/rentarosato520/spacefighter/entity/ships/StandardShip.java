package com.rentarosato520.spacefighter.entity.ships;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.asset.animation.Animator;
import com.rentarosato520.spacefighter.engine.GameMain;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.EntityObject;
import com.rentarosato520.spacefighter.entity.GameObject;
import com.rentarosato520.spacefighter.entity.ID;
import com.rentarosato520.spacefighter.entity.lasers.NpcLaser;
import com.rentarosato520.spacefighter.entity.misc.Meteor;
import com.rentarosato520.spacefighter.entity.misc.Money;
import com.rentarosato520.spacefighter.entity.misc.ShipPart;
import com.rentarosato520.spacefighter.factions.Faction;

//add armor eventually
public class StandardShip extends EntityObject{
	private Handler h;
	private Random r;
	private Animator a;
	private Faction ef;
	private EntityObject object;
	private int desX, desY;
	private int  lx, ly, targetX, targetY; //target;
	private boolean animateReady, ObjectX, ObjectY;
	public static int isDead, ran, ra, rn;
	public static boolean thisDeath;
	public static boolean choAttack, choDefense, choSpeed, choShealth, choCommanding;
	
	
	public StandardShip(int x, int y, int width, int height, Handler h, Faction f) {
		super(x, y, width, height, h);
		
		r = new Random();
		
		a = new Animator();
		
		this.setDeath(false);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.h = h;
		/*this.isDead = 0;
		this.thisDeath = false;
		this.p = p;*/
		this.health = 100;
		this.animateReady = false;
		//this.target = 1;
		this.killLim = r.nextInt(4) + 1;
	this.f = f;
	this.f.addMember(this);
		
		this.attackLim = 20;
		this.defenseLim = 20;
		this.speedLim = 20;
		this.shealthLim = 20;
		this.commandingLim = 20;
		this.currency = 0;
		
		this.attack = r.nextInt(attackLim);
		this.defense = r.nextInt(defenseLim) + 1;
		this.speed = r.nextInt(speedLim) + 2;
		this.shealth = r.nextInt(shealthLim);
		this.commanding = r.nextInt(commandingLim);
		
		this.velX = 1;
		this.velY = 1;
		this.SearchTime = 1000;
		this.desX = r.nextInt(Window.screensize.width + 900);
		this.desY = r.nextInt(Window.screensize.height + 900);
		//this.vectW = 5;
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
		if(rn == 2){
			if(speed < 15){
				speed = r.nextInt(speedLim) + 15;
			}
		}
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
	}

	
	public void tick() {
		ObjectX = GameMain.objectCollision(0, Window.screensize.width + 1000, x);
		ObjectY = GameMain.objectCollision(0, Window.screensize.height + 1000, y);
		
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
				attack(targetX, targetY);
			}
			if(targetX < x  && targetX > x - 300){
				attack(targetX, targetY);
			}
			if(targetY > y && targetY < y + 300){
				attack(targetX, targetY);
			}
			if(targetY < y  && targetY > y - 300){
				attack(targetX, targetY);
			}else{
				target = null;
				velX = 1;
				velY = 1;
			}
			coolDown = 500;
		}
		if(this.target == null){
			wander();
		}
		/*rand = r.nextInt(100);
		if(kills == killLim){
			level++;
			levelUp();
			kills = 0;
			killLim += killLim/2;
		}
		
		x += velX;
		y += velY;
		
		if(y < vectY && velY == -1){velY *= -1;}
		if(y > vectY && velY == 1){velY *= -1;}
		if(x < vectX && velX == -1){velX *= -1;}
		if(x > vectX && velX == 1){velX *= -1;}
		if(x == vectX){
			if(r.nextInt(2) == 0){
				vectX = r.nextInt(Window.screensize.width);
			}else{
				velX = 0;
			}
		}
		if(y == vectY){
			if(r.nextInt(2) == 0){
				vectY = r.nextInt(Window.screensize.height);
			}else{
				velY = 0;
			}
		}*/
		
		
		if(this.isDamage > 0 && this.isDamage > defense){
			health -= isDamage - (defense / 2);
			isDamage = 0;
		}
	}
	
	public void wander(){
		if(y < desY && velY <= -1){velY *= -1;}
		if(y > desY && velY >= 1){velY *= -1;}
		if(x < desX && velX <= -1){velX *= -1;}
		if(x > desX && velX >= 1){velX *= -1;}
		
		if(x == desX){
			if(r.nextInt(2) == 0){
				desX = r.nextInt(Window.screensize.width + 900);
			}else{
				velX = 0;
			}
		}
		if(y == desY){
			if(r.nextInt(2) == 0){
				desY = r.nextInt(Window.screensize.height + 900);
			}else{
				velY = 0;
			}
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
			/*if(tempObject.getId() == ID.Player){
				p = (Player) tempObject;
				if(getBounds().intersects(tempObject.getBounds())){
					Rectangle OverLap = getBounds().intersection(tempObject.getBounds());
					health -= r.nextInt(10) + 10;
					p.setDamage(r.nextInt(10) + 10);
					if(OverLap.height >= OverLap.width){
						tempObject.setVelX(0);
					}
					if(OverLap.width >= OverLap.height){
						tempObject.setVelY(0);
					}
				}
			}*/
		}
	}

	public void attack(int targetX, int targetY){
		h.addObject(new NpcLaser(x + 60, y + 60, 5, 9, h, f, targetX, targetY, this.attack));
	}
	
	public void render(Graphics g) {
		Collision(g);
		a.AnimateEnemy(g, this.x, this.y, this.width, this.height, f);
		if(animateReady){
			a.AnimateMini(g, lx, ly, ran, ran);
		}
		if(health <= 0){
			setDeath(true);
			StandardShip.thisDeath = true;
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
		return new Rectangle(x + 40, y + 37, 50, 50);
	}
}
