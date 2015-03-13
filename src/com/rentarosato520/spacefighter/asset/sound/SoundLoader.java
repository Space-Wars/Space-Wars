package com.rentarosato520.spacefighter.asset.sound;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.rentarosato520.spacefighter.engine.GameMain;

public class SoundLoader implements LineListener{
	private AudioInputStream audio;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip audioClip;
	private boolean isFin;
	private int x = 1;
	
	private AudioInputStream aud;
	private AudioFormat form;
	private DataLine.Info i;
	private Clip audioC;
	private boolean isDone;
	
	public void load(String path){
		try {
			audio = AudioSystem.getAudioInputStream(getClass().getResource(path));
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		format = audio.getFormat();
		this.info = new DataLine.Info(Clip.class, format);
		
		try {
			audioClip = (Clip) AudioSystem.getLine(info);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
		
		audioClip.addLineListener(this);
		
		try {
			try {
				audioClip.open(audio);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		audioClip.start();
		
		while(!isFin){
			audioClip.loop(x);
			x++;
			if(GameMain.SceneChange == 1){
				isFin = true;
				GameMain.SceneChange = 2;
			}
		}
		
		audioClip.close();
	}
	
	public void loadSoundEffect(String path){
		try {
			aud = AudioSystem.getAudioInputStream(getClass().getResource(path));
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		form = aud.getFormat();
		this.i = new DataLine.Info(Clip.class, form);
		
		try {
			audioC = (Clip) AudioSystem.getLine(i);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
		
		audioC.addLineListener(this);
		
		try {
			try {
				audioC.open(aud);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		audioC.start();
		
		while(!isDone){
			
		}
		
		audioC.close();
	}

	@Override
	public void update(LineEvent event) {
		// TODO Auto-generated method stub
		
	}
}
