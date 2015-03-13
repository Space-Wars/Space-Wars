package com.rentarosato520.spacefighter.asset.animation;

import java.awt.Graphics;

import com.rentarosato520.spacefighter.Window;
import com.rentarosato520.spacefighter.factions.Faction;
import com.rentarosato520.spacefighter.factions.Mercenary;
import com.rentarosato520.spacefighter.factions.SpaceMarauder;
import com.rentarosato520.spacefighter.factions.Watchmen;
import com.rentarosato520.spacefighter.listeners.KeyInput;

public class Animator {
	private int frameRate;
	private int[] sec = new int[16];
	private int[] i = new int[16];
	public static boolean[] isFin = new boolean[6];
	
	public Animator(){
		this.frameRate = 10;
		for(int u = 0; u < sec.length; u++){
			sec[u] = 0;
		}
		for(int m = 0; m < i.length; m++){
			i[m] = 0;
		}
		for(int b = 0; b < isFin.length; b++){
			Animator.isFin[b] = false;
		}
	}
	
	public void AnimateStart(Graphics g){
		i[0]++;
		if(i[0] == frameRate + 10){
			sec[14]++;
			i[0] = 0;
		}
		
		if(sec[14] == 0){
			g.drawImage(Assets.Space, 0, 0, Window.screensize.width, Window.screensize.height, null);
		}
		if(sec[14] == 1){
			g.drawImage(Assets.Space1, 0, 0, Window.screensize.width, Window.screensize.height, null);
		}
		if(sec[14] == 2){
			g.drawImage(Assets.Space2, 0, 0, Window.screensize.width, Window.screensize.height, null);
		}
		if(sec[14] == 3){
			g.drawImage(Assets.Space3, 0, 0, Window.screensize.width, Window.screensize.height, null);
			sec[14] = 0;
		}
	}
	
	public void AnimateEnemy(Graphics g, int x, int y, int w, int h, Faction f){
		if(f instanceof Watchmen){
			if(i[1] == frameRate + 2){
				sec[0]++;
				i[1] = 0;
			}else{
				i[1]++;
			}
			
			if(sec[0] == 0){
				g.drawImage(Assets.Enemy, x, y, w, h, null);
			}
			if(sec[0] == 1){
				g.drawImage(Assets.Enemy1, x, y, w, h, null);
			}
			if(sec[0] == 2){
				g.drawImage(Assets.Enemy2, x, y, w, h, null);
			}
			if(sec[0] == 3){
				g.drawImage(Assets.Enemy3, x, y, w, h, null);
				sec[0] = 0;
			}
		}
		
		if(f instanceof Mercenary){
			if(i[2] == frameRate + 2){
				sec[13]++;
				i[2] = 0;
			}else{
				i[2]++;
			}
			
			if(sec[13] == 0){
				g.drawImage(Assets.neutral, x, y, w, h, null);
			}
			if(sec[13] == 1){
				g.drawImage(Assets.neutral1, x, y, w, h, null);
			}
			if(sec[13] == 2){
				g.drawImage(Assets.neutral2, x, y, w, h, null);
			}
			if(sec[13] == 3){
				g.drawImage(Assets.neutral3, x, y, w, h, null);
			}
			if(sec[13] == 4){
				sec[13] = 0;
			}
		}
		
		if(f instanceof SpaceMarauder){
			if(i[3] == frameRate + 2){
				sec[12]++;
				i[3] = 0;
			}else{
				i[3]++;
			}
			
			if(sec[12] == 0){
				g.drawImage(Assets.friend, x, y, w, h, null);
			}
			if(sec[12] == 1){
				g.drawImage(Assets.friend1, x, y, w, h, null);
			}
			if(sec[12] == 2){
				g.drawImage(Assets.friend2, x, y, w, h, null);
			}
			if(sec[12] == 3){
				g.drawImage(Assets.friend3, x, y, w, h, null);
			}
			if(sec[12] == 4){
				sec[12] = 0;
			}
		}
	}
	
	public void AnimateExplosion(Graphics g, int x, int y, int w, int h){
		i[4]++;
		
		if(i[4] == frameRate + 7){
			sec[11]++;
			i[4] = 0;
		}
		
		if(sec[11] == 0){
			g.drawImage(Assets.exp, x, y, w, h, null);
		}
		if(sec[11] == 1){
			g.drawImage(Assets.exp1, x, y, w, h, null);
		}
		if(sec[11] == 2){
			g.drawImage(Assets.exp2, x, y, w, h, null);
		}
		if(sec[11] == 3){
			g.drawImage(Assets.exp3, x, y, w, h, null);
		}
		if(sec[11] == 4){
			g.drawImage(Assets.exp4, x, y, w, h, null);
		}
		if(sec[11] == 5){
			g.drawImage(Assets.exp5, x, y, w, h, null);
		}
		if(sec[11] == 6){
			g.drawImage(Assets.exp6, x, y, w, h, null);
		}
		if(sec[11] == 7){
			g.drawImage(Assets.exp7, x, y, w, h, null);
		}
		if(sec[11] == 8){
			g.drawImage(Assets.exp8, x, y, w, h, null);
		}
		if(sec[11] == 9){
			g.drawImage(Assets.exp9, x, y, w, h, null);
		}
		if(sec[11] == 10){
			g.drawImage(Assets.exp10, x, y, w, h, null);
		}
		if(sec[11] == 11){
			g.drawImage(Assets.exp11, x, y, w, h, null);
			isFin[0] = true;
		}
		if(sec[11] == 12){
			sec[11] = 0;
		}
		//System.out.println(sec2+" "+i+" "+frameRate);
	}
	
	public void AnimateStars(Graphics g, int w, int h){
		i[5]++;
		if(i[5] == frameRate + 11){
			//sec3++;
			i[5] = 0;
		}
		
		if(sec[10] == 0){
			for(int x = -2000; x < Window.screensize.getWidth() + 2000; x += w){
				for(int y = -2000; y < Window.screensize.getHeight() + 2000; y += w){
					g.drawImage(Assets.Stars, x, y, w, h, null);
				}
			}
		}
		/*if(sec3 == 1){
			for(int x = 0; x < Window.screensize.getWidth(); x += w){
				for(int y = 0; y < Window.screensize.getHeight(); y += w){
					g.drawImage(Assets.Star1, x, y, w, h, null);
				}
			}
		}
		if(sec3 == 2){
			for(int x = 0; x < Window.screensize.getWidth(); x += w){
				for(int y = 0; y < Window.screensize.getHeight(); y += w){
					g.drawImage(Assets.Star2, x, y, w, h, null);
				}
			}
		}
		if(sec3 == 3){
			for(int x = 0; x < Window.screensize.getWidth(); x += w){
				for(int y = 0; y < Window.screensize.getHeight(); y += w){
					g.drawImage(Assets.Stai[10], x, y, w, h, null);
				}
			}
		}
		if(sec3 == 4){
			for(int x = 0; x < Window.screensize.getWidth(); x += w){
				for(int y = 0; y < Window.screensize.getHeight(); y += w){
					g.drawImage(Assets.Star4, x, y, w, h, null);
				}
			}
		}
		if(sec3 == 5){
			sec3 = 0;
		}*/
	}
	
	public void AnimateBarrier(Graphics g, int w, int h){
		i[6]++;
		if(i[6] == frameRate + 7){
			sec[9]++;
			i[6] = 0;
		}
		if(sec[9] == 0){
			int x = 0 - 1000;
			int xxx = (int) Window.screensize.width + 1095;
			int yy = 0 - 1000;
			int yyy = (int) Window.screensize.height + 1080;
			for(int xx = 0 - 1000; xx < Window.screensize.width + 1100; xx += w){
				g.drawImage(Assets.ArenaBarrier, xx, yy, w, h, null);
				g.drawImage(Assets.ArenaBarrier, xx, yyy, w, h, null);
			}
			for(int y = 0 - 1000; y < Window.screensize.height + 1080; y += h){
				g.drawImage(Assets.ArenaBarrier1, x, y, w, h, null);
				g.drawImage(Assets.ArenaBarrier1, xxx, y, w, h, null);
			}
		}
		if(sec[9] == 1){
		}
		if(sec[9] == 2){
			sec[9] = 0;
		}
	}
	
	public void AnimateMini(Graphics g, int x, int y, int w, int h){
		i[7]++;
		if(i[7] == frameRate + 7){
			sec[8]++;
			i[7] = 0;
		}	
		
		if(sec[8] == 0){
			g.drawImage(Assets.Mexp, x, y, w, h, null);
		}
		if(sec[8] == 1){
			g.drawImage(Assets.Mexp1, x, y, w, h, null);
		}
		if(sec[8] == 2){
			g.drawImage(Assets.Mexp2, x, y, w, h, null);
		}
		if(sec[8] == 3){
			g.drawImage(Assets.Mexp3, x, y, w, h, null);
		}
		if(sec[8] == 4){
			g.drawImage(Assets.Mexp4, x, y, w, h, null);
		}
		if(sec[8] == 5){
			g.drawImage(Assets.Mexp5, x, y, w, h, null);
		}
		if(sec[8] == 6){
			isFin[1] = true;
			sec[8] = 0;
		}
	}
	
	public void AnimateRocket(Graphics g, int x, int y, int w, int h){
		i[8]++;
		
		if(i[8] == frameRate + 7){
			sec[7]++;
			i[8] = 0;
		}
		
		if(sec[7] == 0){
			g.drawImage(Assets.rb, x, y, w, h, null);
			g.drawImage(Assets.rb, x + 28, y, w, h, null);
			if(KeyInput.keyIsPressed1){
				sec[7] = 0;
			}
		}
		if(sec[7] == 1){
			g.drawImage(Assets.rb1, x, y, w, h, null);
			g.drawImage(Assets.rb1, x + 28, y, w, h, null);
		}
		if(sec[7] == 2){
			g.drawImage(Assets.rb2, x, y, w, h, null);
			g.drawImage(Assets.rb2, x + 28, y, w, h, null);
		}
		if(sec[7] == 3){
			g.drawImage(Assets.rb3, x, y, w, h, null);
			g.drawImage(Assets.rb3, x + 28, y, w, h, null);
		}
		if(sec[7] == 4){
			g.drawImage(Assets.rb4, x, y, w, h, null);
			g.drawImage(Assets.rb4, x + 28, y, w, h, null);
		}
		if(sec[7] == 5){
			g.drawImage(Assets.rb5, x, y, w, h, null);
			g.drawImage(Assets.rb5, x + 28, y, w, h, null);
		}
		if(sec[7] == 6){
			g.drawImage(Assets.rb6, x, y, w, h, null);
			g.drawImage(Assets.rb6, x + 28, y, w, h, null);
		}
		if(sec[7] == 7){
			sec[7] = 0;
			isFin[3] = true;
		}
	}
	
	public void AnimateRocketRight(Graphics g, int x, int y, int w, int h){
		i[9]++;
		
		if(i[9] == frameRate + 7){
			sec[6]++;
			i[9] = 0;
		}
		
		if(sec[6] == 0){
			g.drawImage(Assets.rb21, x, y, w, h, null);
			g.drawImage(Assets.rb21, x, y + 28, w, h, null);
			
			if(KeyInput.keyIsPressed1){
				sec[6] = 0;
			}
		}
		if(sec[6] == 1){
			g.drawImage(Assets.rb22, x, y, w, h, null);
			g.drawImage(Assets.rb22, x, y + 28, w, h, null);
		}
		if(sec[6] == 2){
			g.drawImage(Assets.rb23, x, y, w, h, null);
			g.drawImage(Assets.rb23, x, y + 28, w, h, null);
		}
		if(sec[6] == 3){
			g.drawImage(Assets.rb24, x, y, w, h, null);
			g.drawImage(Assets.rb24, x, y + 28, w, h, null);
		}
		if(sec[6] == 4){
			g.drawImage(Assets.rb25, x, y, w, h, null);
			g.drawImage(Assets.rb25, x, y + 28, w, h, null);
		}
		if(sec[6] == 5){
			g.drawImage(Assets.rb26, x, y, w, h, null);
			g.drawImage(Assets.rb26, x, y + 28, w, h, null);
		}
		if(sec[6] == 6){
			g.drawImage(Assets.rb6, x, y, w, h, null);
			g.drawImage(Assets.rb6, x, y + 28, w, h, null);
		}
		if(sec[6] == 7){
			sec[6] = 0;
			isFin[4] = true;
		}
	}
	
	public void AnimateRocketLeft(Graphics g, int x, int y, int w, int h){
		i[10]++;
		
		if(i[10] == frameRate + 7){
			sec[5]++;
			i[10] = 0;
		}
		
		if(sec[5] == 0){
			g.drawImage(Assets.rb7, x, y, w, h, null);
			g.drawImage(Assets.rb7, x, y  + 28, w, h, null);
			
			if(KeyInput.keyIsPressed1){
				sec[5] = 0;
			}
		}
		if(sec[5] == 1){
			g.drawImage(Assets.rb8, x, y, w, h, null);
			g.drawImage(Assets.rb8, x, y  + 28, w, h, null);
		}
		if(sec[5] == 2){
			g.drawImage(Assets.rb9, x, y, w, h, null);
			g.drawImage(Assets.rb9, x, y  + 28, w, h, null);
		}
		if(sec[5] == 3){
			g.drawImage(Assets.rb10, x, y, w, h, null);
			g.drawImage(Assets.rb10, x, y + 28, w, h, null);
		}
		if(sec[5] == 4){
			g.drawImage(Assets.rb11, x, y, w, h, null);
			g.drawImage(Assets.rb11, x, y + 28, w, h, null);
		}
		if(sec[5] == 5){
			g.drawImage(Assets.rb12, x, y, w, h, null);
			g.drawImage(Assets.rb12, x, y + 28, w, h, null);
		}
		if(sec[5] == 6){
			g.drawImage(Assets.rb13, x, y, w, h, null);
			g.drawImage(Assets.rb13, x, y + 28, w, h, null);
		}
		if(sec[5] == 7){
			sec[5] = 0;
			i[10] = 0;
			isFin[5] = true;
		}
	}
	
	public void AnimateRocketDown(Graphics g, int x, int y, int w, int h){
		i[11]++;
		
		if(i[11] == frameRate + 7){
			sec[4]++;
			i[11] = 0;
		}
		
		if(sec[4] == 0){
			g.drawImage(Assets.rb14, x, y, w, h, null);
			g.drawImage(Assets.rb14, x + 28, y, w, h, null);
			if(KeyInput.keyIsPressed1){
				sec[4] = 0;
			}
		}
		if(sec[4] == 1){
			g.drawImage(Assets.rb15, x, y, w, h, null);
			g.drawImage(Assets.rb15, x + 28, y, w, h, null);
		}
		if(sec[4] == 2){
			g.drawImage(Assets.rb16, x, y, w, h, null);
			g.drawImage(Assets.rb16, x + 28, y, w, h, null);
		}
		if(sec[4] == 3){
			g.drawImage(Assets.rb17, x, y, w, h, null);
			g.drawImage(Assets.rb17, x + 28, y, w, h, null);
		}
		if(sec[4] == 4){
			g.drawImage(Assets.rb18, x, y, w, h, null);
			g.drawImage(Assets.rb18, x + 28, y, w, h, null);
		}
		if(sec[4] == 5){
			g.drawImage(Assets.rb19, x, y, w, h, null);
			g.drawImage(Assets.rb19, x + 28, y, w, h, null);
		}
		if(sec[4] == 6){
			g.drawImage(Assets.rb20, x, y, w, h, null);
			g.drawImage(Assets.rb20, x + 28, y, w, h, null);
		}
		if(sec[4] == 7){
			sec[4] = 0;
			isFin[2] = true;
		}
	}
	
	public void AnimateEx(Graphics g, int x, int y, int w, int h){
		i[15]++;
		
		if(i[15] == frameRate + 7){
			sec[15]++;
			i[15] = 0;
		}
		
		if(sec[15] == 0){
			g.drawImage(Assets.ex, x, y, w, h, null);
		}
		if(sec[15] == 1){
			g.drawImage(Assets.ex1, x, y, w, h, null);
		}
		if(sec[15] == 2){
			g.drawImage(Assets.ex2, x, y, w, h, null);
		}
		if(sec[15] == 3){
			g.drawImage(Assets.ex3, x, y, w, h, null);
		}
		if(sec[15] == 4){
			g.drawImage(Assets.ex4, x, y, w, h, null);
		}
		if(sec[15] == 5){
			g.drawImage(Assets.ex5, x, y, w, h, null);
		}
		if(sec[15] == 6){
			g.drawImage(Assets.ex6, x, y, w, h, null);
		}
		if(sec[15] == 7){
			g.drawImage(Assets.ex7, x, y, w, h, null);
		}
		if(sec[15] == 8){
			g.drawImage(Assets.ex8, x, y, w, h, null);
		}
		if(sec[15] == 9){
			g.drawImage(Assets.ex9, x, y, w, h, null);
		}
		if(sec[15] == 10){
			g.drawImage(Assets.ex10, x, y, w, h, null);
		}
		if(sec[15] == 11){
			sec[15] = 0;
		}
	}
	
	public void AnimateMoney(Graphics g, int x, int y, int w, int h){
		i[12]++;
		if(i[12] == frameRate + 7){
			sec[3]++;
			i[12] = 0;
		}
		
		if(sec[3] == 1){
			g.drawImage(Assets.Cash, x, y, w, h, null);
		}
		if(sec[3] == 2){
			g.drawImage(Assets.Cash1, x, y, w, h, null);
		}
		if(sec[3] == 3){
			sec[3] = 0;
		}
	}
	
	public void AnimateShipParts(Graphics g, int x, int y, int w, int h){
		i[13]++;
		if(i[13] == frameRate + 7){
			sec[2]++;
			i[13] = 0;
		}
		if(sec[2] == 1){
			g.drawImage(Assets.Shipp, x, y, w, h, null);
		}
		if(sec[2] == 2){
			g.drawImage(Assets.Shipp1, x, y, w, h, null);
		}
		if(sec[2] == 3){
			sec[2] = 0;
		}
	}
}
