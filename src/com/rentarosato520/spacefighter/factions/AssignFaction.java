package com.rentarosato520.spacefighter.factions;

import java.util.Random;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.ships.Goliath;
import com.rentarosato520.spacefighter.entity.ships.StandardShip;

public class AssignFaction {
	private Random r;
	private Handler h;
	public static Faction m, w, s, f;
	private int[] rep, ran;
	private int ra;
	private int x, y, xx, yy, xxx, yyy;
	//add spawn areas
	public AssignFaction(Handler h){
		this.r = new Random();
		this.h = h;
		this.rep = new int[3];
		this.ran = new int[6];
		
		for(int i = 0; i < ran.length; i++){
			ran[i] = r.nextInt(3);
		}
		
		rep[0] = r.nextInt(15)+15;
		rep[1] = r.nextInt(15)+15;
		rep[2] = r.nextInt(15)+15;
		
		m = new Mercenary(null, rep[0], h);
		w = new Watchmen(null, rep[1], h);
		s = new SpaceMarauder(null, rep[2], h);
		
		h.faction.add(m);
		h.faction.add(w);
		h.faction.add(s);
		
		randomizeSpawn();
	}

	public void assign(){
		if(h.entity.size() < 20){
			spawnMarauder();
			spawnMercenary();
			spawnWatchmen();
		}
		randomizeSpawn();
	}
	
	public void spawnMarauder(){
		if(r.nextInt(2) == 1){
			h.addEntity(new StandardShip(xx, yy, 128, 128, h, s));
			
		}else{
			h.addEntity(new Goliath(xx, yy, 300, 300, h, s));
			
		}
	}
	
	public void spawnMercenary(){
		if(r.nextInt(2) == 1){
			h.addEntity(new StandardShip(xxx, yyy, 128, 128, h, m));
			
		}else{
			h.addEntity(new Goliath(xxx, yyy, 300, 300, h, m));
			
		}
	}
	
	public void spawnWatchmen(){
		if(r.nextInt(2) == 1){
			h.addEntity(new StandardShip(x, y, 128, 128, h, w));
			
		}else{
			h.addEntity(new Goliath(x, y, 300, 300, h, w));
			
		}

	}
	
	public void randomizeSpawn(){
		
		if(ran[0] == 1){
			x = 0 - 1000;
		}
		if(ran[0] == 2){
			x = Window.screensize.width + 1000;
		}
		if(ran[0] == 0){
			x = r.nextInt(Window.screensize.width + 1000);
		}

		if(ran[1] == 1){
			y = r.nextInt(Window.screensize.height + 1000);
		}
		if(ran[1] == 2){
			y = 0 - 1000;
		}
		if(ran[1] == 0){
			y = Window.screensize.height + 1000;
		}
		
		if(ran[2] == 1){
			xx = 0 - 1000;
		}
		if(ran[2] == 2){
			xx = Window.screensize.width + 1000;
		}
		if(ran[2] == 0){
			xx = r.nextInt(Window.screensize.width + 1000);
		}

		if(ran[3] == 1){
			yy = r.nextInt(Window.screensize.height + 1000);
		}
		if(ran[3] == 2){
			yy = 0 - 1000;
		}
		if(ran[3] == 0){
			yy = Window.screensize.height + 1000;
		}
		
		if(ran[4] == 1){
			xxx = 0 - 1000;
		}
		if(ran[4] == 2){
			xxx = Window.screensize.width + 1000;
		}
		if(ran[4] == 0){
			xxx = r.nextInt(Window.screensize.width + 1000);
		}

		if(ran[5] == 1){
			yyy = r.nextInt(Window.screensize.height + 1000);
		}
		if(ran[5] == 2){
			yyy = 0 - 1000;
		}
		if(ran[5] == 0){
			yyy = Window.screensize.height + 1000;
		}
	}

	public static Faction getM() {
		return m;
	}

	public static void setM(Faction m) {
		AssignFaction.m = m;
	}

	public static Faction getW() {
		return w;
	}

	public static void setW(Faction w) {
		AssignFaction.w = w;
	}

	public static Faction getS() {
		return s;
	}

	public static void setS(Faction s) {
		AssignFaction.s = s;
	}
	
	

	/*public void setEnemyFaction() {
		for(int i = 0; i < h.faction.size(); i++){
			Faction tempFaction = h.faction.get(i);
			
			if(tempFaction instanceof Mercenary){
				Mercenary m = (Mercenary) tempFaction;
				
			}
			if(tempFaction instanceof SpaceMarauder){
				SpaceMarauder sm = (SpaceMarauder) tempFaction;
			}
			if(tempFaction instanceof Watchmen){
				Watchmen wm = (Watchmen) tempFaction;
			}
		}
	}*/
}
