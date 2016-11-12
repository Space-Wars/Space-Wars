package com.rentarosato520.spacefighter.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.entity.EntityObject;
import com.rentarosato520.spacefighter.entity.ID;
import com.rentarosato520.spacefighter.entity.ships.Player;
import com.rentarosato520.spacefighter.factions.Faction;
import com.rentarosato520.spacefighter.factions.Watchmen;
import com.rentarosato520.spacefighter.listeners.KeyInput;

public class HUD {
	private int colorValue;
	private boolean healthOnce;
	public static int health, preHealth, c; 
	private Handler h;
	private Player p;
	public static boolean isLevelUp;
	
	public HUD(Handler h, Player p){
		this.h = h;
		this.healthOnce = true;
		this.p = p;
	}
	
	public void tick(){
		for(int i = 0; i < h.entity.size(); i++){
			EntityObject tempObject = h.entity.get(i);
			
			if(tempObject.getId() == ID.Player){
				colorValue = GameMain.clamp(colorValue, 0, 255);
				
				colorValue = tempObject.getHealth()/10 * 2;
				
				health = tempObject.getHealth();
				if(healthOnce){
					preHealth = tempObject.getHealth();
					healthOnce = false;
					c = colorValue;
				}
			}
		}
		if(isLevelUp){
			healthOnce = true;
			isLevelUp = false;
		}
	}
	
	public void render(Graphics g, Spawner s){
		g.setColor(Color.darkGray);
		g.fillRect(2, 2, c+20, 50);
		g.setColor(new Color(75, c, 0));
		g.fillRect(12, 12, colorValue, 30);
		g.setColor(Color.black);
		g.setFont(new Font(null, Font.PLAIN, 15));
		g.drawString("Health: "+health+"/"+preHealth, 20, 30);
		g.setColor(Color.red);
		g.drawString("Level: "+p.getLevel(), 15, 75);
		g.drawString("Player Deaths: "+GameMain.pDeaths, 15, 100);
		g.drawString("Enemy Kills: "+p.getKills()+ " / "+p.getKillLim(), 15, 125);
		g.drawString("Kills required to advance: "+p.getKillLim(), 15, 150);
		g.drawString("Attack: "+Player.pAttack, 15, 175);
		g.drawString("Defense: "+p.getDefense(), 15, 200);
		g.drawString("Speed: "+p.getSpeed(), 15, 225);
		g.drawString("Shealth: "+p.getShealth(), 15, 250);
		g.drawString("Commanding: "+p.getCommanding(), 15, 275);
		g.drawString("Faction: "+Faction.getFactionName(p.getPlayerFaction()), 15, 300);
		g.drawString("Cash: $"+p.getCurrency(), 15, 325);
		g.drawString("Ship Parts Collected: "+p.getNumShipParts(), 15, 350);
		g.drawString("Ammo: "+KeyInput.Ammo, 15, 375);
		g.drawString("Spawn Attempts: "+Spawner.spawnTimes, 15, 400);
		g.drawString("Time Until Next Spawn: "+s.getSpawnTick()+" / "+s.getSpawnLim(), 15, 425);
		g.drawString("Number of Events that Occured: "+GameMain.numEvents, 15, 450);
		g.drawString("Difficulty: "+Spawner.getDiffculty(), 15, 475);
	
		g.drawString("Faction Members: "+p.getMembers(), 15, Window.screensize.height - 75);
		g.drawString("Faction Members Lost: "+h.getDeaths(), 215, Window.screensize.height - 75);
		g.drawString("Total Faction Strength: ", 415, Window.screensize.height - 75);
		g.drawString("Total Commanding Rank: ", 615, Window.screensize.height - 75);
		g.drawString("Team Kill Total: ", 915, Window.screensize.height - 75);
		
		if(KeyInput.Ammo == 0){
			g.drawString("Reloading...", 95, 375);
		}
		g.setColor(Color.gray);
		g.fillRect(Window.screensize.width - 70, 12, 60, 20);
		g.setColor(Color.BLUE);
		g.drawString("Menu", Window.screensize.width - 55, 28);
		g.setColor(Color.RED);
		//g.drawLine(400, 200,(int) p.getShotVelX(),(int) p.getShotVelY());
	}
}
