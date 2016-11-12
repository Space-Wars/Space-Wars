package com.rentarosato520.spacefighter.factions;

import java.util.ArrayList;

import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.entity.EntityObject;
import com.rentarosato520.spacefighter.entity.ID;
import com.rentarosato520.spacefighter.entity.ships.Player;

public abstract class Faction {
	protected int rep;
	protected int repTeam;
	protected ID id;
	protected int numOfKills;
	protected int memberLimit;
	protected boolean genFactionRep;
	protected EntityObject currentCommander;
	public ArrayList<Faction> isEnemy = new ArrayList<Faction>();
	public ArrayList<Faction> isFriend = new ArrayList<Faction>();
	public ArrayList<EntityObject> members = new ArrayList<EntityObject>();
	
	public Faction(ID id, int rep, Handler h){
		
	}
	
	
	public void addMember(EntityObject e){
		if(members.size() <= 4){
			this.members.add(e);
		}
	}
	
	public void removeMember(EntityObject e){
		this.members.remove(e);
	}

	public int getRep() {
		return rep;
	}

	public void setRep(int rep) {
		this.rep = rep;
	}
	
	public Faction getEnemyFactions(Faction f){
		return f;
	}
	
	public ArrayList<Faction> getIsEnemy() {
		return isEnemy;
	}

	public void setIsEnemy(ArrayList<Faction> isEnemy) {
		this.isEnemy = isEnemy;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getNumOfKills() {
		return numOfKills;
	}

	public void setNumOfKills(int numOfKills) {
		this.numOfKills = numOfKills;
	}

	public ArrayList<EntityObject> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<EntityObject> members) {
		this.members = members;
	}

	public int getRepTeam() {
		return repTeam;
	}

	public void setRepTeam(int repTeam) {
		this.repTeam = repTeam;
	}
	
	public void getHighestCommand(){
		System.out.println("Members:"+members.size());
		if(members.size() >= 5){
			EntityObject Commander = members.get(0);
			int highCommand = Commander.getCommanding();
			for(EntityObject e : this.members){
				System.out.println(e.getCommanding()+" Command "+Commander.getCommanding());
				if(highCommand < e.getCommanding()){
					Commander = e;
					highCommand = e.getCommanding();
				}
			}
			Commander.setSetCommander(true);
			this.currentCommander = Commander;
			System.out.println(Commander);
		}
	}

	public abstract void tick();
	
	
	public static String getFactionName(Faction f){
		if(f instanceof Mercenary){
			return "Mercenary";
		}
		if(f instanceof Watchmen){
			return "Watchmen";
		}
		if(f instanceof SpaceMarauder){
			return "Space Marauder";
		}else{
			return "Factionless";
		}
	}
	//public abstract void setEnemyFaction();
}
