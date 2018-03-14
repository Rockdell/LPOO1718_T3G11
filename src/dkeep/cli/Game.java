package dkeep.cli;

import dkeep.io.ConsoleIO;
import dkeep.io.IInputOutput;
import dkeep.logic.layout.Level;
import dkeep.logic.layout.Level01;
import dkeep.logic.layout.Level02;
import dkeep.test.LevelTest;

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
	static public IInputOutput io;
	
	public static void main(String[] args) {
		
		Game g = new Game(new ConsoleIO(), "Rookie", 3);
		
		g.startGame();
	}
	
	/**
	 * Creates an object Game.
	 */
	public Game(IInputOutput io, String gP, int nO) {
		
		Game.io = io;
		
		loadLevel(1);
		
		Game.guardPersonality = gP;
		Game.nrOgres = nO;
	}
	
	/**
	 * Creates an object Game.
	 * @param id Level to start the game with.
	 */
	public Game(int id) {
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
	private void loadLevel(int id) {
		
		if(id == 1)
			level = new Level01();
		else if(id == 2)
			level = new Level02();
		else if(id == 3)
			level = new LevelTest();
	}
	
	/**
	 * Starts the game (for console).
	 */
	public void startGame() {
		
		//Initial level
		level.display();
		
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
	public boolean tickGame() {
		
		//Check level's status
		switch(level.getLevelStatus()) {
		case ONGOING:
			break;
		case WON:
			if(level.getID() != 2)
				loadLevel(level.getID() + 1);
			else
				return true;
			break;
		case LOST:
			return true;
		}
		
		//Read input
		char input = io.read();
		
		//Move entities
		level.updateLevel(input);
		
		//Display the current level
		level.display();
		
		return false;
	}
}
