package com.rentarosato520.spacefighter.engine;

import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rentarosato520.spacefighter.asset.animation.Animator;
import com.rentarosato520.spacefighter.asset.sound.SoundLoader;
import com.rentarosato520.spacefighter.entity.EntityObject;
import com.rentarosato520.spacefighter.entity.GameObject;
import com.rentarosato520.spacefighter.entity.lasers.Broadside;
import com.rentarosato520.spacefighter.entity.lasers.NpcLaser;
import com.rentarosato520.spacefighter.entity.lasers.PlayerLaser;
import com.rentarosato520.spacefighter.entity.ships.Player;
import com.rentarosato520.spacefighter.factions.Faction;

public class Handler {
	//Use Linked Lists
	private Animator a;
	private boolean renderMini, renderExp, playSound;
	private float x, y, bx, by;
	public static int fDeaths;
	
	public CopyOnWriteArrayList<GameObject> object = new CopyOnWriteArrayList<GameObject>();
	public CopyOnWriteArrayList<EntityObject> entity = new CopyOnWriteArrayList<EntityObject>();
	public CopyOnWriteArrayList<Faction> faction = new CopyOnWriteArrayList<Faction>();
	
	//public ArrayList<GameObject> object = new ArrayList<GameObject>();
	//public ArrayList<EntityObject> entity = new ArrayList<EntityObject>();
	//public ArrayList<Faction> faction = new ArrayList<Faction>();
	
	public Handler(){
		a = new Animator();
		this.renderMini = false;
		this.playSound = false;
	}
	
	public void render(Graphics g){
		///if(GameMain.numFrames > 5){
			if(renderMini){
				a.AnimateMini(g,(int) x,(int) y, 32, 32);
				if(playSound){
					SoundLoader.loadSoundEffect("/Sound/ExplosionMini.wav");
					playSound = false;
				}
			}
			if(renderExp){
				a.AnimateEx(g,(int) x,(int) y, 32, 32);
				if(playSound){
					SoundLoader.loadSoundEffect("/Sound/ExplosionMini.wav");
					playSound = false;
				}
			}
			for(EntityObject tempObject : entity){
				tempObject.render(g);
			}
			
			for(GameObject tempObject : object){
				tempObject.render(g);
			}

	}
	
	public void tick(){	
			for(EntityObject tempObject : entity){
				tempObject.tick();
			}
			for(GameObject tempObject : object){
				tempObject.tick();
			}
			for(Faction tempObject : faction){
				tempObject.tick();
			}
	}	
	
	public void addEntity(EntityObject o){
		this.entity.add(o);
	}
	
	public void removeEntity(EntityObject o){
		this.entity.remove(o);
	}
	
	public void addObject(GameObject o){
		this.object.add(o);
	}
	
	public void removeObject(GameObject o){
		if(o instanceof NpcLaser){
			x = o.getX();
			y = o.getY();
			renderMini = true;
			playSound = true;
		}
		if(o instanceof PlayerLaser){
			x = o.getX();
			y = o.getY();
			renderMini = true;
			playSound = true;
		}
		if(o instanceof Broadside){
			bx = o.getX();
			by = o.getY();
			renderExp = true;
			playSound = true;
		}
		
		if(o instanceof EntityObject && ((EntityObject) o).hasFaction()){
			for(EntityObject e : this.entity){
				for(int i = 0; i < e.getF().members.size(); i++){
					if(e.getF().members.get(i) instanceof Player && e == o){
						fDeaths++;
					}
				}
			}
		}
		
		this.object.remove(o);
	}
	
	public void addFaction(Faction f){
		this.faction.add(f);
	}
	
	public void removeFaction(Faction f){
		this.faction.remove(f);
	}
	
	public int getDeaths(){
		return fDeaths;
	}
}
