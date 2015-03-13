package com.rentarosato520.spacefighter;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class GameScene {
	protected boolean isActive;
	private static GameScene Scene;
	
	public GameScene(boolean active){
		
	}

	public abstract void render(Graphics g, Graphics2D g2d);
	public abstract void tick();
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static GameScene getCurrentScene() {
		return Scene;
	}

	public static void setCurrentScene(GameScene currentScene) {
		Scene = currentScene;
	}
}
