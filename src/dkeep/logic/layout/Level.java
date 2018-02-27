package dkeep.logic.layout;

import dkeep.logic.characters.Hero;

public abstract class Level {
	
	protected char[][] map;
	protected int mapID;
	protected Hero hero;
	
	public char[][] getMap() {
		return map;
	}
	
	public int getID() {
		return mapID;
	}
	
	public abstract void updateLevel(char direction);
	
	protected abstract boolean checkEnemy();
	
	protected abstract boolean checkEnd();
	
	public int display() {
		
		//0 -> continue
		//1 -> lost
		//2 -> new map/won
		
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
		
		if(checkEnemy())
			return 1;
		else if(checkEnd())
			return 2;
		else
			return 0;
	}		
}
