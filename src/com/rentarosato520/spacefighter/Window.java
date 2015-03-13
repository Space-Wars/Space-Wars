package com.rentarosato520.spacefighter;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

import com.rentarosato520.spacefighter.engine.GameMain;

public class Window extends Canvas{
	private static final long serialVersionUID = 3615537902187837915L;
	public static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	public static Dimension screensizeMin = new Dimension(screensize.width/2, screensize.height/2);
	private static JFrame window;
	
	public Window(GameMain gamemain){
		/*write = new GetSpecs("/Maps/Space.txt", true);
		try {
			write.addSpecs(screensize.width, screensize.height);
			System.out.println("Written");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		Window.window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setSize(screensize);
		//window.setMinimumSize(screensizeMin);
		//window.setPreferredSize(screensize);
		//window.setMaximumSize(screensize);
		window.setLocationRelativeTo(null);
		window.add(gamemain);
		window.setTitle(GameMain.Title);
		window.setVisible(true);
		
		gamemain.start();
	}
}
