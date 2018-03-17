package dkeep.cli;

import dkeep.io.ConsoleIO;

import dkeep.io.IO;
import java.io.FileNotFoundException;

import java.io.IOException;

import dkeep.logic.layout.Level;
import dkeep.logic.layout.Level.status_t;
import dkeep.logic.layout.Level01;
import dkeep.logic.layout.Level02;
import dkeep.logic.layout.LevelTest;
import dkeep.logic.layout.LevelTest2;

public class Game {
	
	/**
	 * Loaded level.
	 */
	private Level level;
	
	static public String guardPersonality;
	static public int nrOgres;
	
	/**
	 * Input scanner.
	 */
	static public IO io;
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		
		Game g = new Game(new ConsoleIO(), "Rookie", 3);
		
		g.startGame();
	}
	
	/**
	 * Creates an object Game.
	 */
	public Game(IO io, String gP, int nO) throws IOException, FileNotFoundException {
		
		Game.guardPersonality = gP;
		Game.nrOgres = nO;
		Game.io = io;
		
		loadLevel(1);
	}
	
	/**
	 * Creates an object Game with custom Level.
	 */
	public Game(IO io, String gP, int nO, int id) throws IOException, FileNotFoundException {

		Game.guardPersonality = gP;
		Game.nrOgres = nO;
		Game.io = io;

		loadLevel(id);
	}
	
	/**
	 * Creates an object Game.
	 * @param id Level to start the game with.
	 */
	public Game(int id) throws IOException, FileNotFoundException {
		loadLevel(id);
	}
	
	/**
	 * @return Loaded level.
	 */
	public Level getCurrentLevel() {
		return level;
	}

	/**
	 * Loads a level into the game.
	 * @param id Level to load.
	 */
	private void loadLevel(int id) throws IOException, FileNotFoundException {
		
		if(id == 1)
			level = new Level01();
		else if(id == 2)
			level = new Level02();
		else if(id == 3)
			level = new LevelTest();
		else if(id == 4)
			level = new LevelTest2();
		
		//Display the initial level
		level.display();
	}
	
	/**
	 * Starts the game (for console).
	 */
	public void startGame() throws IOException, FileNotFoundException {
		
		boolean over =  false;
		do {
			over = tickGame();
		}
		while(!over);
	}
	
	/**
	 * Ticks the game.
	 * @return True if won/lost, false if ongoing.
	 */
	public boolean tickGame() throws IOException, FileNotFoundException {
		
		if(level.getLevelStatus() != status_t.ONGOING)
			return true;
		
		//Read input
		char input = io.read();
		
		//Move entities
		level.updateLevel(input);
		
		//Display the current level
		level.display();
		
		//Check level's status
		switch(level.getLevelStatus()) {
		case ONGOING:
			break;
		case PROCEED:
			if(level.getID() < 2)
				loadLevel(level.getID() + 1);
			else {
				level.setLevelStatus(status_t.WON);
				return true;
			}
			break;
		case CAUGHT:
			return true;
		case KILLED:
			return true;
		case WON:
			return true;
		}
		
		return false;
	}
}
