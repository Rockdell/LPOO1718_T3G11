package dkeep.ui.gui;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {
	
	private Clip _music;
	
	SoundManager() {
		
		try {
			_music = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	private void _loadMusic(String name) {
		
		if(_music.isOpen()) {
			_music.stop();
			_music.close();
		}
		
		AudioInputStream audioIn;
		
		try {
			audioIn = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/src/miscellaneous/" + name + ".wav"));
			_music.open(audioIn);
			_music.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}		
		
		_music.start();
		
	}
	
	public void loadMenuMusic() {
		
		_loadMusic("main_theme");
	}
	
	public void loadPlayMusic() {
		
		_loadMusic("play_theme");
	}
	
	public void finalize() {
		
		if(_music.isOpen()) {
			_music.stop();
			_music.close();
		}
	}
}
