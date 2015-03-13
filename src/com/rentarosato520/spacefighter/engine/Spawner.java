package com.rentarosato520.spacefighter.engine;

import java.util.Random;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.entity.ID;
import com.rentarosato520.spacefighter.entity.misc.Meteor;
import com.rentarosato520.spacefighter.entity.ships.Player;
import com.rentarosato520.spacefighter.entity.ships.SupplyShip;
import com.rentarosato520.spacefighter.factions.AssignFaction;

public class Spawner {
	private Random r;
	private Handler h;
	private AssignFaction af;
	private int spawnTick, spawnLim, spawnCount, spawnChance;
	public int spawnAmount, rand, rande, randf, randn, genSpace;
	
	public Spawner(Handler h, HUD hud, Player p){
		r = new Random();
		af = new AssignFaction(h);
		
		rand = r.nextInt(4);
		rande = r.nextInt(2);
		this.spawnTick = 0;
		this.spawnLim = r.nextInt(100);
		this.spawnCount = r.nextInt(10) + 5;
		this.spawnChance = r.nextInt(10);
		this.spawnAmount = spawnCount;
		this.genSpace = r.nextInt(2);
		
		this.h = h;
	}

	public void tick(){
		spawnTick++;
		if(spawnTick == spawnLim){
			if(spawnChance <= 4){
				af.assign();
			}else{
				if(r.nextInt(2) == 1){
					h.addEntity(new SupplyShip(0, r.nextInt(Window.screensize.height + 1000), r.nextInt(300)+32, r.nextInt(300)+32, ID.SupplyShip, h, 0));
				}else{
					h.addObject(new Meteor(0, r.nextInt(Window.screensize.height + 1000), r.nextInt(32)+32, r.nextInt(32)+32, ID.Meteor, h, 0));
				}
			}
			spawnTick = 0;
			spawnLim += spawnLim/2;
			spawnCount = r.nextInt(4);
			spawnChance = r.nextInt(10);
			spawnAmount = spawnCount;
		}
	}
	public void setSpawning(boolean isSpawning) {
	}
}
