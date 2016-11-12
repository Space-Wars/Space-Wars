package com.rentarosato520.spacefighter.factions;

import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.engine.Spawner;
import com.rentarosato520.spacefighter.entity.ID;

public class SpaceMarauder extends Faction{
	private Handler h;
	public SpaceMarauder(ID id, int rep, Handler h) {
		super(id, rep, h);	
		this.rep = rep;
		this.id = id;
		this.h = h;
		this.genFactionRep = true;
		
		if(h != null){
			this.h = h;
		}
		
		if(rep <= 100){
			this.repTeam = 0;
		}
		if(rep <= 200 && rep > 100){
			this.repTeam = 1;
		}
		if(rep <= 300 && rep > 200){
			this.repTeam = 2;
		}
	}

	public void tick() {
		if(rep <= 100){
			this.repTeam = 0;
		}
		if(rep <= 200 && rep > 100){
			this.repTeam = 1;
		}
		if(rep <= 300 && rep > 200){
			this.repTeam = 2;
		}
		
		if(genFactionRep){
			for(int i = 0; i < h.faction.size(); i++){
				Faction f = h.faction.get(i);
				
				if(f.getRepTeam() != this.repTeam){
					this.isEnemy.add(f);
				}else{
					this.isFriend.add(f);
				}
				//System.out.println(isFriend+" "+isEnemy);
			}
			this.genFactionRep = false;
		}
		if(Spawner.hasSpawned){
			getHighestCommand();
		}	
		//System.out.println("Sp: "+repTeam);
	}
}
