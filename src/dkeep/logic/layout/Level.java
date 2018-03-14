package dkeep.logic.layout;

import dkeep.cli.Game;
import dkeep.logic.characters.Hero;

public abstract class Level {
	
	/**
	 * Level's possible status.
	 */
	public enum status_t { 
		/** Level is ongoing. */ 	ONGOING,
		/** Cleared the area. */ 	PROCEED, 
		/** Caught by an enemy. */ 	CAUGHT,
		/** Killed by an enemy. */ 	KILLED,
		/** Won the game. */ 		WON
		};
	
	/**
	 * Level's map.
	 */
	protected char[][] map;
	
	/**
	 * Map's ID.
	 */
	protected int mapID;
	
	/**
	 * Level's hero.
	 */
	protected Hero hero;
	
	/**
	 * Level's status.
	 */
	protected status_t levelStatus;
	
	/**
	 * Creates an object Level.
	 */
	public Level() {
		levelStatus = status_t.ONGOING;
	}

	/**
	 * @return Level's map.
	 */
	public char[][] getMap() {
		return map;
	}
	
	/**
	 * @return Map's ID.
	 */
	public int getID() {
		return mapID;
	}
	
	/**
	 * @return Level's hero.
	 */
	public Hero getHero() {
		return hero;
	}
	
	/**
	 * @return Level's status.
	 */
	public status_t getLevelStatus() {
		return levelStatus;
	}
	
	public void setLevelStatus(status_t s) {
		levelStatus = s;
	}
	
	/**
	 * Updates the level and its entities.
	 * @param d Direction for the hero.
	 */
	public void updateLevel(char d) {
		
		//Clears current level
		clearEntities();
		
		//Update level's entities
		updateEntities(d);
		
		//Update level's doors
		updateDoors();
		
		//Draws current level
		drawEntities();
		
		updateLevelStatus();
	}

	/**
	 * Display the level.
	 */
	public void display() {
		
		Game.io.clearConsole();
		
		//Display map
		for(int i = 0; i < map.length; i++) {
			
			for(int j = 0; j < map[i].length; j++) {
				
				Game.io.write(map[i][j] + " ");
			}
			
			Game.io.write("\n");
		}
	}
	
	public String endgameSummary() {
		
		switch(levelStatus) {
		case ONGOING:
			return "Moving ";
		case PROCEED:
			return "You cleared the area!";
		case CAUGHT:
			return "You got caught by the enemy!";
		case KILLED:
			return "You got killed by the enemy!";
		case WON:
			return "You won!";
		default:
			return "";
		}
	}
	
	/**
	 * Clears the all entities from the level.
	 */
	protected abstract void clearEntities();
	
	/**
	 * Updates the entities from the level.
	 * @param d
	 */
	protected abstract void updateEntities(char d);
	
	/**
	 * Updates the doors from the level.
	 */
	protected abstract void updateDoors();
	
	/**
	 * Draws the entities from the level.
	 */
	protected abstract void drawEntities();
	
	/**
	 * Updates level status.
	 */
	protected abstract void updateLevelStatus();
	
	/**
	 * Load enemies into the level.
	 */
	protected abstract void loadEnemies();
}
