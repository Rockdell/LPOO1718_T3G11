package dkeep.logic.layout;

import dkeep.logic.characters.Hero;

public abstract class Level {
	
	public enum status_t { ONGOING, WON, LOST};
	
	protected char[][] map;
	protected int mapID;
	protected Hero hero;
	protected status_t levelStatus;

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
		
		if(hero.getKey() != 0 && hero.getKey() != -1)
			openDoors(hero.getKey());
		
		//Draws current level
		drawEntities();
		
		updateLevelStatus();
	}
	
	//Clear, update and draw level
	protected abstract void clearEntities();
	protected abstract void updateEntities(char d);
	protected abstract void drawEntities();
	
	//Open doors
	protected abstract void openDoors(int key);
	
	//Update status
	protected abstract void updateLevelStatus();
	
	//Load level's enemies
	protected abstract void loadEnemies();

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
