package com.rentarosato520.spacefighter.factions;

import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.ID;

public class SpaceMarauder extends Faction{
	private Handler h;
	public SpaceMarauder(ID id, int rep, Handler h) {
		super(id, rep, h);	
		this.rep = rep;
		this.id = id;
		this.h = h;
		
		if(h != null){
			this.h = h;
		}
		
		if(rep <= 10){
			this.repTeam = 0;
		}
		if(rep <= 20 && rep > 10){
			this.repTeam = 1;
		}
		if(rep <= 30 && rep > 20){
			this.repTeam = 2;
		}
	}

	public void tick() {
		if(rep <= 10){
			this.repTeam = 0;
		}
		if(rep <= 20 && rep > 10){
			this.repTeam = 1;
		}
		if(rep <= 30 && rep > 20){
			this.repTeam = 2;
		}
		
		for(int i = 0; i < h.faction.size(); i++){
			Faction f = h.faction.get(i);
			
			if(f.getRepTeam() != this.repTeam){
				this.isEnemy.add(f);
			}else{
				this.isFriend.add(f);
			}
		}
	}
}
