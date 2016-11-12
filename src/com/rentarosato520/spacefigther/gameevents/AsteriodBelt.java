package com.rentarosato520.spacefigther.gameevents;

import java.util.Random;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.ID;
import com.rentarosato520.spacefighter.entity.misc.Meteor;

public class AsteriodBelt extends AsteriodEvents{
	private Random r;
	private Handler h;
	private int eventType, addAsteriod, add;
	
	public AsteriodBelt(Handler h) {
		super(h);
		this.r = new Random();
		this.h = h;
		this.eventType = r.nextInt(4);
		this.Active = true;
		this.addAsteriod = r.nextInt(10)+10;
		
		if(eventType == 1){
			this.eventX = r.nextInt(Window.screensize.width + 1000);
			this.eventY = 0;
			this.eventW = eventX + r.nextInt(50) + 50;
			this.eventH = eventY + r.nextInt(50) + 50;
			direction = 2;
		}
		if(eventType == 0){
			this.eventX = 0;
			this.eventY = r.nextInt(Window.screensize.height + 1000);
			this.eventW = eventX + r.nextInt(50) + 50;
			this.eventH = eventY + r.nextInt(50) + 50;
			direction = 0;
		}
		if(eventType == 2){
			this.eventX = Window.screensize.width;
			this.eventY = r.nextInt(Window.screensize.height + 1000);
			this.eventW = eventX + r.nextInt(50) + 50;
			this.eventH = eventY + r.nextInt(50) + 50;
			direction = 1;
		}
		if(eventType == 3){
			this.eventX = Window.screensize.height;
			this.eventY = r.nextInt(Window.screensize.width + 1000);
			this.eventW = eventX + r.nextInt(50) + 50;
			this.eventH = eventY + r.nextInt(50) + 50;
			direction = 3;
		}
	}

	public void tick() {
		add++;
		if(add == addAsteriod){
			asteriods.add(new Meteor(r.nextInt(eventW)+eventX, r.nextInt(eventH)+eventY, r.nextInt(30)+30, r.nextInt(30)+30, ID.Meteor, h, direction));
			add = 0;
		}
		for(Meteor o : asteriods){
			h.addObject(o);
		}	
	}
}
