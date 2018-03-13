package dkeep.logic.layout;

import dkeep.logic.characters.Hero;

public abstract class Level implements ILevelLogic {
	
	public enum status_t { ONGOING, WON, LOST};
	
	protected char[][] map;
	protected int mapID;
	protected Hero hero;
	protected status_t levelStatus;
	
	public Level() {
		levelStatus = status_t.ONGOING;
	}

	public char[][] getMap() {
		return map;
	}
	
	public int getID() {
		return mapID;
	}
	
	public Hero getHero() {
		return hero;
	}
	
	public status_t getLevelStatus() {
		return levelStatus;
	}
	
	public void updateLevel(char d) {
		
		//Clears current level
		clearEntities();
		
		//Update level's entities
		updateEntities(d);
		
		updateDoors();
		
		//Draws current level
		drawEntities();
		
		updateLevelStatus();
	}

	//Display level
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
}
