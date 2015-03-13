package com.rentarosato520.spacefighter.entity;

import java.util.ArrayList;

import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.ships.StandardShip;
import com.rentarosato520.spacefighter.factions.Faction;

public abstract class EntityObject extends GameObject{
	protected int health;
	protected boolean isFiring;
	protected boolean isMoving;
	protected boolean isBacking;
	protected int targetX, targetY;
	protected boolean isDeath;
	protected int isFacing;
	protected int isDamage;
	protected int level;
	protected int kills;
	protected int killLim;
	protected int EnemySize;
	protected int SearchTime;
	protected int memberSize;
	protected boolean wasPlayer, wasEnemy, wasNeutral, wasFriendly;
	protected Faction f;
	protected Faction ef;
	protected EntityObject target;
	protected ArrayList<EntityObject> currentTargets = new ArrayList<EntityObject>();
	
	protected int attack, defense, speed, shealth, commanding, coolDown;
	protected int attackLim, defenseLim, speedLim, shealthLim, commandingLim;
	protected int currency, numShipParts;
	
	public EntityObject(int x, int y, int width, int height, Handler h) {
		super(x, y, width, height, h);
	}
	
	
	
	public int getSearchTime() {
		return SearchTime;
	}



	public void setSearchTime(int searchTime) {
		SearchTime = searchTime;
	}



	public int getTargetX() {
		return targetX;
	}



	public void setTargetX(int targetX) {
		this.targetX = targetX;
	}



	public int getTargetY() {
		return targetY;
	}



	public void setTargetY(int targetY) {
		this.targetY = targetY;
	}



	public int getCoolDown() {
		return coolDown;
	}



	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
	}



	public EntityObject getTarget() {
		return target;
	}



	public void setTarget(EntityObject target) {
		this.target = target;
	}



	public Faction getF() {
		return f;
	}



	public void setF(Faction f) {
		this.f = f;
	}



	public int getIsFacing() {
		return isFacing;
	}



	public void setIsFacing(int isFacing) {
		this.isFacing = isFacing;
	}

	public StandardShip getEnemy(StandardShip e){
		return e;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isFiring() {
		return isFiring;
	}

	public void setFiring(boolean isFiring) {
		this.isFiring = isFiring;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public boolean isBacking() {
		return isBacking;
	}

	public void setBacking(boolean isBacking) {
		this.isBacking = isBacking;
	}



	public boolean isDeath() {
		return isDeath;
	}



	public void setDeath(boolean isDeath) {
		this.isDeath = isDeath;
	}
	
	public int isDamage() {
		return isDamage;
	}

	public void setDamage(int isDamaged) {
		this.isDamage = isDamaged;
	}



	public int getIsDamage() {
		return isDamage;
	}



	public void setIsDamage(int isDamage) {
		this.isDamage = isDamage;
	}



	public int getAttack() {
		return attack;
	}



	public void setAttack(int attack) {
		this.attack = attack;
	}



	public int getDefense() {
		return defense;
	}



	public void setDefense(int defense) {
		this.defense = defense;
	}



	public int getSpeed() {
		return speed;
	}



	public void setSpeed(int speed) {
		this.speed = speed;
	}



	public int getShealth() {
		return shealth;
	}



	public void setShealth(int shealth) {
		this.shealth = shealth;
	}



	public int getCommanding() {
		return commanding;
	}



	public void setCommanding(int commanding) {
		this.commanding = commanding;
	}

	public int getAttackLim() {
		return attackLim;
	}



	public void setAttackLim(int attackLim) {
		this.attackLim = attackLim;
	}



	public int getDefenseLim() {
		return defenseLim;
	}



	public void setDefenseLim(int defenseLim) {
		this.defenseLim = defenseLim;
	}



	public int getSpeedLim() {
		return speedLim;
	}



	public void setSpeedLim(int speedLim) {
		this.speedLim = speedLim;
	}



	public int getShealthLim() {
		return shealthLim;
	}



	public void setShealthLim(int shealthLim) {
		this.shealthLim = shealthLim;
	}



	public int getCommandingLim() {
		return commandingLim;
	}



	public void setCommandingLim(int commandingLim) {
		this.commandingLim = commandingLim;
	}



	public int getCurrency() {
		return currency;
	}



	public void setCurrency(int currency) {
		this.currency = currency;
	}



	public int getNumShipParts() {
		return numShipParts;
	}



	public void setNumShipParts(int numShipParts) {
		this.numShipParts = numShipParts;
	}



	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
	}



	public int getKills() {
		return kills;
	}



	public void setKills(int kills) {
		this.kills += kills;
	}



	public int getKillLim() {
		return killLim;
	}



	public void setKillLim(int killLim) {
		this.killLim = killLim;
	}



	public boolean isWasPlayer() {
		return wasPlayer;
	}



	public void setWasPlayer(boolean wasPlayer) {
		this.wasPlayer = wasPlayer;
	}



	public boolean isWasEnemy() {
		return wasEnemy;
	}



	public void setWasEnemy(boolean wasEnemy) {
		this.wasEnemy = wasEnemy;
	}



	public boolean isWasNeutral() {
		return wasNeutral;
	}



	public void setWasNeutral(boolean wasNeutral) {
		this.wasNeutral = wasNeutral;
	}



	public boolean isWasFriendly() {
		return wasFriendly;
	}



	public void setWasFriendly(boolean wasFriendly) {
		this.wasFriendly = wasFriendly;
	}
	
	


}
