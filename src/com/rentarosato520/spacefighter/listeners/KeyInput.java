package com.rentarosato520.spacefighter.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.rentarosato520.spacefighter.GameSession;
import com.rentarosato520.spacefighter.asset.sound.SoundLoader;
import com.rentarosato520.spacefighter.engine.GameMain;
import com.rentarosato520.spacefighter.engine.Handler;
import com.rentarosato520.spacefighter.engine.Spawner;
import com.rentarosato520.spacefighter.entity.EntityObject;
import com.rentarosato520.spacefighter.entity.ID;
import com.rentarosato520.spacefighter.entity.lasers.PlayerLaser;
import com.rentarosato520.spacefighter.entity.ships.Player;
import com.rentarosato520.spacefighter.entity.ships.StandardShip;

public class KeyInput extends KeyAdapter{
	private Handler h;
	private SoundLoader l;
	private boolean[] keys = new boolean[6];
	public static int Ammo;
	public static boolean isSpawn, keyIsPressed, keyIsPressed1, play, keyReleased;
	private Random r;
	private Player p;
	private int rand;
	
	public KeyInput(Handler h){
		this.h = h;
		this.l = new SoundLoader();
		
		KeyInput.keyReleased = false;
		KeyInput.Ammo = 20;
		KeyInput.isSpawn = false;
		
		r = new Random();
		new SoundLoader();
		
		rand = r.nextInt(3);
		
		for(int i = 0; i < keys.length; i++){
			keys[i] = false;
		}
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		keyIsPressed1 = true;
		for(int i = 0; i < h.entity.size(); i++){
			EntityObject tempObject = h.entity.get(i);
			if(tempObject.getId() == ID.Player){
				p = (Player) tempObject;
				if(key == KeyEvent.VK_UP){
					p.setDecay(false);
					tempObject.setVelY(-p.getSpeed()/2); 
					tempObject.setMoving(true);
					keys[0] = true;
					Player.animate[0] = true;
				}
				if(key == KeyEvent.VK_DOWN){
					p.setDecay(false);
					tempObject.setVelY(p.getSpeed()/2);
					tempObject.setBacking(true);
					keys[1] = true;
					Player.animate[1] = true;
				}
				if(key == KeyEvent.VK_RIGHT){
					p.setDecay(false);
					tempObject.setVelX(p.getSpeed()/2);
					tempObject.setMoving(true);
					keys[2] = true;
					Player.animate[2] = true;
				}
				if(key == KeyEvent.VK_LEFT){
					p.setDecay(false);
					tempObject.setVelX(-p.getSpeed()/2); 
					tempObject.setMoving(true);
					keys[3] = true;
					Player.animate[3] = true;
				}
				
				if(key == KeyEvent.VK_SPACE){
					keyIsPressed = true;
					play = true;
					StandardShip.ran = r.nextInt(32)+32;
					if(Ammo > 0){
						if(rand == 0){
							SoundLoader.loadSoundEffect("/Sound/Laser1.wav");
						}else if(rand == 1){
							SoundLoader.loadSoundEffect("/Sound/Laser2.wav");
						}else{
							SoundLoader.loadSoundEffect("/Sound/Laser3.wav");
						}
						rand = r.nextInt(3);
						if(tempObject.getIsFacing() == 0 || tempObject.getIsFacing() == 1){
							
							h.addObject(new PlayerLaser(tempObject.getX() + 50, tempObject.getY() + 80, 5, 9,  h, tempObject, false));
							h.addObject(new PlayerLaser(tempObject.getX() + 85 + 16, tempObject.getY() + 80, 5, 9,  h, tempObject, false));
						}
						if(tempObject.getIsFacing() == 2 || tempObject.getIsFacing() == 3){
							
							h.addObject(new PlayerLaser(tempObject.getX() + 50, tempObject.getY() + 80, 5, 9,  h, tempObject, false));
							h.addObject(new PlayerLaser(tempObject.getX() + 50, tempObject.getY() + 80/2, 5, 9,  h, tempObject, false));
						}
						if(tempObject.getIsFacing() == 5){
							
							h.addObject(new PlayerLaser(tempObject.getX() + 60, tempObject.getY() + 80, 5, 9,  h, tempObject, false));
							h.addObject(new PlayerLaser(tempObject.getX() + 80, tempObject.getY() + 40, 5, 9, h, tempObject, false));
						}
						if(tempObject.getIsFacing() == 4){
							
							h.addObject(new PlayerLaser(tempObject.getX() + 80, tempObject.getY() + 80, 5, 9,  h, tempObject, false));
							h.addObject(new PlayerLaser(tempObject.getX() + 50, tempObject.getY() + 40, 5, 9,  h, tempObject, false));
						}
						if(tempObject.getIsFacing() == 6){ 
							
							h.addObject(new PlayerLaser(tempObject.getX() + 80, tempObject.getY() + 80, 5, 9,  h, tempObject, false));
							h.addObject(new PlayerLaser(tempObject.getX() + 120, tempObject.getY() + 80/2 + 20, 5, 9,  h, tempObject, false));
						}
						if(tempObject.getIsFacing() == 7){
							
							h.addObject(new PlayerLaser(tempObject.getX() + 80, tempObject.getY() + 40, 5, 9,  h, tempObject, false));
							h.addObject(new PlayerLaser(tempObject.getX() + 120, tempObject.getY() + 80/2 + 20, 5, 9,  h, tempObject, false));
						}
						Ammo--;
					}
				}
				if(Ammo > 0){
					if(key == KeyEvent.VK_W){
						h.addObject(new PlayerLaser(tempObject.getX() + 80, tempObject.getY() + 40, 5, 9,  h, tempObject, true));
						Ammo--;
					}
				}
				if(key == KeyEvent.VK_A){
					p.setRotateShot(1);
				}
				if(key == KeyEvent.VK_D){
					p.setRotateShot(-1);
				}
			}
		}
		
		
		if(key == KeyEvent.VK_ESCAPE){
			if(GameSession.isShop){
				GameSession.isShop = false;
			}
			if(!GameSession.isShop){
				GameSession.isShop = true;
			}
		}

		if(key == KeyEvent.VK_R){
			isSpawn = true;
			GameMain.pDeaths++;
		}
		
		if(key == KeyEvent.VK_E){
			Spawner.easy = true;
			Spawner.hard = false;
			Spawner.medium = false;
		}
		
		if(key == KeyEvent.VK_M){
			Spawner.medium = true;
			Spawner.hard = false;
			Spawner.easy = false;
		}
		
		if(key == KeyEvent.VK_H){
			Spawner.hard = true;
			Spawner.easy = false;
			Spawner.medium = false;
		}
		
		if(key == KeyEvent.VK_I){
			if(Ammo < 20){
				Ammo = 20;
				p.spendCurrency(20);
			}
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		keyIsPressed1 = false;
		//play = false;
		for(int i = 0; i < h.entity.size(); i++){
			EntityObject tempObject = h.entity.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_UP){keys[0] = false;}
				if(key == KeyEvent.VK_DOWN){keys[1] = false;}
				if(key == KeyEvent.VK_RIGHT){keys[2] = false;}
				if(key == KeyEvent.VK_LEFT){keys[3] = false;}
				
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_D){p.setRotateShot(0); keyReleased = true;}
				
				if(key == KeyEvent.VK_SPACE){tempObject.setFiring(false); keyIsPressed = false;}
				
				if(keys[0] && keys[1]){/*tempObject.setVelY(0);*/((Player) tempObject).setDecay(true); tempObject.setMoving(false); tempObject.setBacking(false);}
				if(!keys[0] && !keys[1]){/*tempObject.setVelX(0)*/;((Player) tempObject).setDecay(true); tempObject.setMoving(false); tempObject.setBacking(false);}
				if(keys[2] && keys[3]){/*tempObject.setVelX(0)*/;((Player) tempObject).setDecay(true); tempObject.setMoving(false);}
				if(!keys[2] && !keys[3]){/*tempObject.setVelY(0)*/;((Player) tempObject).setDecay(true); tempObject.setMoving(false);}
			}
		}
	}
}
