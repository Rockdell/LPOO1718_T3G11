package dkeep.logic.layout;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import dkeep.logic.characters.Hero;

public abstract class Level {
	
	/**
	 * Level's possible status.
	 */
	public enum status_t { 
		/** Game is ongoing. */ ONGOING,
		/** Won the age. */ WON, 
		/** Lost the game. */ LOST
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
	 * Loads Level's map accordingly
	 * @param mapID ID of the wanted map
	 * @return Returns a char[][] with the map
	 */
	public void loadMap(int mapID) throws IOException, FileNotFoundException {

		char[][] test;
		boolean found = false;

		//Tries reading the file
		try (BufferedReader br = new BufferedReader(new FileReader("maps.txt"))) {

			//Searches for the correct map in maps.txt
			for (String mapSearch; (mapSearch = br.readLine()) != null;) {
				if(mapSearch.equals("Map" + mapID))
				{
					found = true;
					break;
				}
			}
			
			//Checks if the map was found
			if(!found)
				return;	

			//If it was found starts retrieving it
			int i = 0;
			String firstLine = br.readLine();

			//Checks how many lines and columns the map is composed of
			String[] parts = firstLine.split("-");
			String lines = parts[0];
			String columns = parts[1];

			int l = Integer.parseInt(lines);
			int c = Integer.parseInt(columns);

			test = new char[l][c];

			//Creates an array representing the map
			for (String line; (line = br.readLine()) != null; i++) {
				
				if(line.equals(""))
					break;
					
				char[] tmp = line.toCharArray();
				test[i] = tmp;
			}
		}

		map = test;
	}
	
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
		
		//Clear console
		for(int i = 0; i < 10; i++)
			System.out.print("\n");
		
		//Display map
		for(int i = 0; i < map.length; i++) {
			
			for(int j = 0; j < map[i].length; j++) {
				
				System.out.print(map[i][j] + " ");
			}
			
			System.out.println();
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
