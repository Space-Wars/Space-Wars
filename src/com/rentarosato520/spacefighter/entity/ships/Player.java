package com.rentarosato520.spacefighter.entity.ships;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.rentarosato520.spacefighter.Camera;
import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.asset.animation.Animator;
import com.rentarosato520.spacefighter.asset.animation.Assets;
import com.rentarosato520.spacefighter.engine.GameMain;
import com.rentarosato520.spacefighter.engine.HUD;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.engine.Spawner;
import com.rentarosato520.spacefighter.entity.EntityObject;
import com.rentarosato520.spacefighter.entity.GameObject;
import com.rentarosato520.spacefighter.entity.ID;
import com.rentarosato520.spacefighter.entity.misc.Meteor;
import com.rentarosato520.spacefighter.entity.misc.Money;
import com.rentarosato520.spacefighter.entity.misc.ShipPart;
import com.rentarosato520.spacefighter.factions.AssignFaction;
import com.rentarosato520.spacefighter.factions.Faction;
import com.rentarosato520.spacefighter.listeners.KeyInput;

public class Player extends EntityObject{
	private Handler h;
	private int tim = 0;
	private boolean removeShot, isLevelUp;
	private Animator a;
	private Camera c;
	private Random r;
	private int healthMax, rotateShot, rotate, aimVel, rotateTim;
	private double shotVelY, shotVelX;
	private boolean isDecay;
	public static boolean[] animate;
	public static boolean choAttack, choDefense, choSpeed, choShealth, choCommanding, isCommandShip;
	public static int Ammo, rand;
	public static int pAttack, defaultVel;
	
	public Player(int x, int y, int width, int height, ID id, Handler h, Camera c){
		super(x, y, width, height, h);
		
		this.x = x - 15;
		this.y = y - 20;
		this.height = height;
		this.width = width;
		this.h = h;
		this.id = id;
		this.c = c;
		this.rotate = 0;
		this.rotateShot = 0;
		this.aimVel = 0;
		this.rotateTim = 0;
		this.isDecay = false;
		
		Player.defaultVel =  1;
		Player.Ammo = KeyInput.Ammo;
		
		r = new Random();		
		a = new Animator();
		rand = r.nextInt(3);
		
		this.attackLim = 20;
		this.defenseLim = 20;
		this.speedLim = 20;
		this.shealthLim = 20;
		this.commandingLim = 20;
		this.currency = 0;
		this.killLim = r.nextInt(4) + 1;
		this.level = 1;
		this.isCollidingX = false;
		this.isCollidingY = false;

		Player.pAttack += r.nextInt(attackLim);
		this.defense += r.nextInt(defenseLim) + 1;
		this.speed += r.nextInt(speedLim) + 2;
		this.shealth += r.nextInt(shealthLim);
		this.commanding += r.nextInt(commandingLim);
		
		this.isBacking = false;
		this.isDamage = 0;
		this.isFiring = false;
		this.isMoving = false;
		this.removeShot = false;
		Player.animate = new boolean[4];
		this.isFacing = 0;
		this.healthMax = 1000;
		this.health = r.nextInt(500) + 300;
			
		for(int i = 0; i < animate.length; i++){
			animate[i] = false;
		}
	}
	
	public void decay(){
		if(isDecay){
			//System.out.println(velX+" past "+velY);
			if(velX < 0){velX += 1;}
			if(velY < 0){velY += 1;}
			if(velX > 0){velX -= 1;}
			if(velY > 0){velY -= 1;}
			//System.out.println(velX+" now "+velY);
		}
		if(isDecay && velX == 0 && velY == 0){
			
		}
	}
	
	public void turretAim(){
		if(rotateShot == 1){
			rotateTim++;
			if(rotateTim > 100){
				rotate += 1;
				rotateTim = 0;
			}
		}
		if(rotateShot == -1){
			rotateTim++;
			if(rotateTim > 100){
				rotate -= 1;
				rotateTim = 0;
			}
		}
		shotVelX = Math.cos(rotate) * 10;
		shotVelY = Math.sin(rotate) * 10;
		//System.out.println("VelX: "+shotVelX+" VelY: "+shotVelY+" rotate: "+rotate);
	}

	public void tick() {
		System.out.println("Player: "+commanding+" HasSpawned: "+Spawner.hasSpawned);
		x += velX;
		y += velY;
		
		if(rand == 0){
			this.f = AssignFaction.m;
			rand = 4;
		}
		if(rand == 2){
			this.f = AssignFaction.w;
			rand = 4;
		}
		if(rand == 1){
			this.f = AssignFaction.s;
			rand = 4;
		}
		
		//System.out.println("velX: "+shotVelX+" velY: "+shotVelY+" aimVel: "+aimVel);
		
		if(f != null){
			this.f.addMember(this);
		}
			
		if(kills == killLim){
			level++;
			levelUp();
			kills = 0;
			killLim += killLim/2;
		}
		if(rotate == 360){
			rotate = 0;
		}
		if(rotate == 0){
			rotate = 360;
		}
		x = GameMain.clampF(0 - 1000, Window.screensize.width + 1000, x);
		y = GameMain.clampF(0 - 1000, Window.screensize.height + 1000, y);
		velX = GameMain.clampF(-50, 50, velX);
		velY = GameMain.clampF(-50, 50, velY);
		
		if(velX >= 1 && velY >= 1){isFacing = 6;} 
		if(velX >= -100 && velX < 0 && velY >= -100 && velY < 0){isFacing = 5;}
		if(velX >= -100 && velX < 0 && velY >= 1){isFacing = 4;} 
		if(velX >= 1 && velY >= -100 && velY < 0){isFacing = 7;}
		if(velX >= -100 && velX < 0 && velY == 0){isFacing = 3;}
		if(velX >= 1 && velY == 0){isFacing = 2;}
		if(velX == 0 && velY >= -100 && velY < 0){isFacing = 0;}
		if(velX == 0 && velY >= 1){isFacing = 1;}

		
		if(this.isFiring){
			tim++;
			if(tim <= 1){
				this.isFiring = false;
			}
		}
		
		if(this.isDamage > 0 && this.isDamage > defense){
			health -= isDamage - (defense/2);
			removeShot = true;
			isDamage = 0;
		}else if(this.isDamage > 0){
			health -= 1;
			isDamage = 0;
		}
		//Deal with player kills
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
		
		health = GameMain.clamp(0, healthMax, health);
		turretAim();
		decay();
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
								a.AnimateExplosion(g,(int) tempObject.getX(), (int) tempObject.getY(), 32, 32);
								this.health -= m.getDamage();
								if(Animator.isFin[0]){
									h.removeObject(tempObject);
									Animator.isFin[0] = false;
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
		for(int i = 0; i < h.entity.size(); i++){
			EntityObject tempObject = h.entity.get(i);
			
			if(tempObject.getId() == ID.SupplyShip){
				SupplyShip ss = (SupplyShip) tempObject;
				if(getBounds().intersects(tempObject.getBounds())){
					health += ss.getHealthBonus();
					currency += ss.getCashBonus();
					numShipParts += ss.getShipBonus();
					h.removeEntity(tempObject);
				}
			}
		}
		Commanding();
	}

	public void render(Graphics g) {
		Collision(g);
		if(isFacing == 7){
			g.drawImage(Assets.playerR4, (int)x + 10, (int)y, width, height, null);
		}
		if(isFacing == 6){
			g.drawImage(Assets.playerR3,(int) x + 10, (int)y, width, height, null);
		}
		if(isFacing == 5){
			g.drawImage(Assets.playerR2, (int)x + 10, (int)y, width, height, null);
		}
		if(isFacing == 4){
			g.drawImage(Assets.playerR1, (int) x + 10,(int) y, width, height, null);
		}
		if(isFacing == 3){
			g.drawImage(Assets.playerLeft,(int) x + 10,(int) y, width, height, null);
			if(animate[3]){
				a.AnimateRocketLeft(g,(int) x + 90,(int) y + 33, 32, 32);
			}
		}
		if(isFacing == 2){
			g.drawImage(Assets.playerRight,(int) x + 10,(int) y, width, height, null);
			if(animate[2]){
				a.AnimateRocketRight(g,(int) x + 30,(int) y + 33, 32, 32);
			}
		}
		if(isFacing == 1){
			g.drawImage(Assets.playerBack,(int) x + 10,(int) y, width, height, null);
			if(animate[1]){
				a.AnimateRocketDown(g,(int) x + 42,(int) y + 20, 32, 32);
			}
		}
		if(isFacing == 0){
			g.drawImage(Assets.player, (int) x + 10,(int) y, width, height, null);
			if(animate[0]){
				a.AnimateRocket(g,(int) x + 45,(int) y + 80, 32, 32);
			}
		}
		
		if(Animator.isFin[3]){
			animate[0] = false;
			Animator.isFin[3] = false;
		}
		if(Animator.isFin[4]){
			animate[2] = false;
			Animator.isFin[4] = false;
		}
		if(Animator.isFin[5]){
			animate[3] = false;
			Animator.isFin[5] = false;
		}
		if(Animator.isFin[2]){
			animate[1] = false;
			Animator.isFin[2] = false;
		}
		
		if(health <= 0){
			velX = 0;
			velY = 0;
			g.setColor(Color.white);
			g.setFont(new Font(null, Font.PLAIN, 40));
			g.drawString("Press 'r' to Respawn!",(int) c.getX() + 500,(int) c.getY() + 500);
			this.setDeath(true);
			a.AnimateExplosion(g,(int) x,(int) y, width, height);
			if(Animator.isFin[0]){
				h.removeEntity(this);
				Animator.isFin[0] = false;
			}
		}
		
		if(isLevelUp){
			g.setColor(Color.WHITE);
			g.drawString("LEVEL UP!!!!!",(int) x,(int) y);
		}
	}
	
	public void levelUp(){
		KeyInput.Ammo += KeyInput.Ammo/2;
		Ammo = KeyInput.Ammo;
		this.health +=  r.nextInt(200) + 100;
		Player.pAttack += r.nextInt(attackLim);
		this.defense += r.nextInt(defenseLim) + 1;
		this.speed += r.nextInt(speedLim) + 2;
		this.shealth += r.nextInt(shealthLim);
		this.commanding += r.nextInt(commandingLim);
		HUD.isLevelUp = true;
		
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
	
	public void Commanding(){
		//if(this.setCommander){
		//	h.removeEntity(this);
		//	h.addEntity(new CommandShip(x, y, width, height, h, 1));
		//}
	}
	
	public String getMembers(){
		if(f != null){
			return ""+f.members.size();
		}else{
			return null;
		}
	}
	
	public void spendCurrency(int Amount){
		this.currency -= Amount;
	}
	
	public boolean isDecay() {
		return isDecay;
	}

	public void setDecay(boolean isDecay) {
		this.isDecay = isDecay;
	}

	public double getShotVelX() {
		return shotVelX;
	}

	public void setShotVelX(int shotVelX) {
		this.shotVelX = shotVelX;
	}

	public double getShotVelY() {
		return shotVelY;
	}

	public void setShotVelY(int shotVelY) {
		this.shotVelY = shotVelY;
	}

	public int getRotate() {
		return rotate;
	}

	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

	public int getRotateShot() {
		return rotateShot;
	}

	public void setRotateShot(int rotateShot) {
		this.rotateShot = rotateShot;
	}

	public Faction getPlayerFaction(){
		return this.f;
	}
	
	public int getNumShipParts() {
		return numShipParts;
	}

	public void setNumShipParts(int numShipParts) {
		this.numShipParts = numShipParts;
	}

	public boolean isRemoveShot() {
		return removeShot;
	}

	public void setRemoveShot(boolean removeShot) {
		this.removeShot = removeShot;
	}

	public Rectangle getBoundsRightWing() {
		return new Rectangle((int)x + 87,(int)y + 85, 30, 30);
	}
	
	public Rectangle getBoundsLeftWing(){
		return new Rectangle((int) x + 37,(int) y + 85, 30, 30);
	}
	
	public Rectangle getBoundsBottomLeftWing() {
		return new Rectangle((int) x + 34,(int) y + 115, 37, 10);
	}
	
	public Rectangle getBoundsBottomRightWing(){
		return new Rectangle((int) x + 84,(int) y + 115, 37, 10);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x + 67, (int)y + 35, 20, 90);
	}
}
