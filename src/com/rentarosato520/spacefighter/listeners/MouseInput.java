package com.rentarosato520.spacefighter.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.rentarosato520.spacefighter.GameScene;
import com.rentarosato520.spacefighter.GameSession;
import com.rentarosato520.spacefighter.StartUp;
import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.entity.ships.Player;

public class MouseInput extends MouseAdapter{
	public static int mouseX;
	public static int mouseY;
	public static boolean isOver, isShop;
	private GameScene start;
	
	public MouseInput(GameScene scene){
		this.start = scene;
	}
	
	public void mousePressed(MouseEvent e){
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(mouseX >= Window.screensize.width/2 - 105 && mouseX <= Window.screensize.width/2 - 105 + 250 &&
				mouseY >= Window.screensize.height - 205 && mouseY <= Window.screensize.height - 205 + 70){
			start.setActive(false);
		}
		if(mouseX >= Window.screensize.width - 70 && mouseX <= Window.screensize.width - 70 + 60 && 
				mouseY >= 12 && mouseY <= 12 + 20){
			GameSession.isShop = true;
		}else{
			GameSession.isShop = false;
		}
		
		if(mouseX >= GameSession.rectX - 400 && mouseX <= GameSession.rectX - 400 + GameSession.rectW &&
				mouseY >= GameSession.rectY && mouseY <= GameSession.rectY + GameSession.rectH){
			Player.choAttack = true;
			GameSession.statChoice = false;
		}
		if(mouseX >= GameSession.rectX - 300 && mouseX <= GameSession.rectX - 300 + GameSession.rectW &&
				mouseY >= GameSession.rectY && mouseY <= GameSession.rectY + GameSession.rectH){
			Player.choDefense = true;
			GameSession.statChoice = false;
		}
		if(mouseX >= GameSession.rectX - 200 && mouseX <= GameSession.rectX - 200 + GameSession.rectW &&
				mouseY >= GameSession.rectY && mouseY <= GameSession.rectY + GameSession.rectH){
			Player.choSpeed = true;
			GameSession.statChoice = false;
		}
		if(mouseX >= GameSession.rectX - 100 && mouseX <= GameSession.rectX - 100 + GameSession.rectW &&
				mouseY >= GameSession.rectY && mouseY <= GameSession.rectY + GameSession.rectH){
			Player.choShealth = true;
			GameSession.statChoice = false;
		}
		if(mouseX >= GameSession.rectX && mouseX <= GameSession.rectX + GameSession.rectW + 50&&
				mouseY >= GameSession.rectY && mouseY <= GameSession.rectY + GameSession.rectH){
			Player.choCommanding = true;
			GameSession.statChoice = false;
		}
		/*g.fillRect(rectX - 400, rectY, rectW, rectH);
		g.fillRect(rectX - 300, rectY, rectW, rectH);
		g.fillRect(rectX - 200, rectY, rectW, rectH);
		g.fillRect(rectX - 100, rectY, rectW, rectH);
		g.fillRect(rectX, rectY, rectW + 50, rectH);*/
	}
	
	public void mouseMoved(MouseEvent e){
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(mouseX >= Window.screensize.width/2 - 105 && mouseX <= Window.screensize.width/2 - 105 + 250 &&
				mouseY >= Window.screensize.height - 205 && mouseY <= Window.screensize.height - 205 + 70){
			StartUp.buttonColor = 0;
		}else{
			StartUp.buttonColor = 255;
		}
	}
}
