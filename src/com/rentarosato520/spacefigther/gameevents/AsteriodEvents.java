package com.rentarosato520.spacefigther.gameevents;

import java.util.ArrayList;

import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.misc.Meteor;

public abstract class AsteriodEvents {
	protected int eventX, eventY, eventW, eventH;
	protected int direction;
	protected boolean Active;
	ArrayList<Meteor> asteriods = new ArrayList<Meteor>();
	
	public AsteriodEvents(Handler h){
		
	}
	
	public abstract void tick();

	public int getEventX() {
		return eventX;
	}

	public void setEventX(int eventX) {
		this.eventX = eventX;
	}

	public int getEventY() {
		return eventY;
	}

	public void setEventY(int eventY) {
		this.eventY = eventY;
	}

	public int getEventW() {
		return eventW;
	}

	public void setEventW(int eventW) {
		this.eventW = eventW;
	}

	public int getEventH() {
		return eventH;
	}

	public void setEventH(int eventH) {
		this.eventH = eventH;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}
}
