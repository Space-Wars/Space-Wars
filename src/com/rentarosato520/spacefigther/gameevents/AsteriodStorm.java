package com.rentarosato520.spacefigther.gameevents;

import java.util.Random;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.GameObject;
import com.rentarosato520.spacefighter.entity.ID;
import com.rentarosato520.spacefighter.entity.misc.Meteor;

public class AsteriodStorm extends AsteriodEvents{
	private Random r;
	private Handler h;
	private int members;
	
	public AsteriodStorm(Handler h){
		super(h);
		this.r = new Random();
		this.h = h;
		this.members = r.nextInt(10)+10;
		this.eventX = r.nextInt(Window.screensize.width + 1000);
		this.eventY = r.nextInt(Window.screensize.height + 1000);
		this.eventW = eventX + r.nextInt(50) + 50;
		this.eventH = eventY + r.nextInt(50) + 50;
		this.direction = r.nextInt(4);
		this.Active = true;
		
		spawnAsteriods();
	}
	
	public void spawnAsteriods(){
		for(int i = 0; i < members; i++){
			asteriods.add(new Meteor(r.nextInt(eventW)+eventX, r.nextInt(eventH)+eventY, r.nextInt(30)+30, r.nextInt(30)+30, ID.Meteor, h, direction));
		}
		for(Meteor o : asteriods){
			h.addObject(o);
		}
	}

	public void tick() {
		if(asteriods.isEmpty()){
			Active = false;
		}
	}
}
