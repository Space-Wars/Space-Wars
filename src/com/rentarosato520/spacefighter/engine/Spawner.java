package com.rentarosato520.spacefighter.engine;

import java.util.Random;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.entity.ID;
import com.rentarosato520.spacefighter.entity.misc.Meteor;
import com.rentarosato520.spacefighter.entity.ships.Player;
import com.rentarosato520.spacefighter.entity.ships.SupplyShip;
import com.rentarosato520.spacefighter.factions.AssignFaction;
import com.rentarosato520.spacefigther.gameevents.AsteriodStorm;

public class Spawner {
	private Random r;
	private Handler h;
	private AssignFaction af;
	private AsteriodStorm as;
	private int spawnTick, spawnLim, spawnCount, spawnChance, meteorAmount, eventTick, eventTickLim; 
	public static boolean easy, medium, hard, hasSpawned;
	public int spawnAmount, rand, rande, randf, randn, genSpace;
	public static int spawnTimes;
	
	public Spawner(Handler h, HUD hud, Player p){
		r = new Random();
		af = new AssignFaction(h);
		
		rand = r.nextInt(4);
		rande = r.nextInt(2);
		this.spawnTick = 0;
		this.eventTickLim = r.nextInt(500)+500;
		Spawner.spawnTimes = 0;
		Spawner.easy = true;
		Spawner.medium = false;
		Spawner.hard = false;
		if(Spawner.easy){
			this.spawnLim = r.nextInt(300)+1;
		}
		if(Spawner.medium){
			this.spawnLim = r.nextInt(200)+1;
		}
		if(Spawner.hard){
			this.spawnLim = r.nextInt(100)+1;
		}
		this.spawnCount = r.nextInt(10) + 5;
		this.spawnChance = r.nextInt(10);
		this.spawnAmount = spawnCount;
		this.genSpace = r.nextInt(2);
		this.meteorAmount = r.nextInt(2)+2;
		
		this.h = h;
	}

	public void tick(){
		spawnTick++;
		eventTick++;
		if(spawnTick == spawnLim){
			if(spawnChance <= 4){
				af.assign();
				Spawner.spawnTimes++;
			}else{
				if(r.nextInt(2) == 1){
					h.addEntity(new SupplyShip(r.nextInt(Window.screensize.width + 1000), r.nextInt(Window.screensize.height + 1000), r.nextInt(300)+32, r.nextInt(300)+32, ID.SupplyShip, h, 0));
				}else{
					for(int i = 0;i  < meteorAmount; i++){
						h.addObject(new Meteor(r.nextInt(Window.screensize.width + 1000), r.nextInt(Window.screensize.height + 1000), r.nextInt(32)+32, r.nextInt(32)+32, ID.Meteor, h, r.nextInt(4)));
					}
				}
			}
			hasSpawned = true;
			spawnCount = r.nextInt(4);
			spawnChance = r.nextInt(10);
			spawnAmount = spawnCount;
			spawnTick = 0;
			
			if(Spawner.easy){
				this.spawnLim = r.nextInt(300)+1;
			}
			if(Spawner.medium){
				this.spawnLim = r.nextInt(200)+1;
			}
			if(Spawner.hard){
				this.spawnLim = r.nextInt(100)+1;
			}
		}
		if(eventTick == eventTickLim){
			if(r.nextInt(100) <= 25){
				spawnEvent();
			}
			eventTick = 0;
		}
	}
	
	public void spawnEvent(){
		as = new AsteriodStorm(h);
		GameMain.numEvents++;
	}

	public int getSpawnTick() {
		return spawnTick;
	}

	public void setSpawnTick(int spawnTick) {
		this.spawnTick = spawnTick;
	}

	public int getSpawnLim() {
		return spawnLim;
	}

	public void setSpawnLim(int spawnLim) {
		this.spawnLim = spawnLim;
	}
	
	public static String getDiffculty(){
		if(Spawner.easy){
			return "Easy";
		}
		if(Spawner.medium){
			return "Medium";
		}
		if(Spawner.hard){
			return "Hard";
		}
		return null;
	}
}
