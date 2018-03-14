package dkeep.logic.layout;

import dkeep.cli.Game;
import dkeep.logic.characters.*;
import dkeep.logic.characters.Hero.key_t;

public class Level01 extends Level {
	
	/**
	 * Level's guard.
	 */
	private Guard guard;
	
	/**
	 * Creates an object Level01.
	 */
	public Level01() {
		
		map = Maps.dungeon;
		mapID =  1;
		hero = new Hero(1, 1, this);
		
		loadEnemies();
	}
	
	protected void clearEntities() {
		guard.erasePosition();
		hero.erasePosition();
	}
	
	protected void updateEntities(char d) {
		guard.patrol();
		hero.move(d);
	}
	
	protected void updateDoors() {
		
		if(hero.getKey() == key_t.NULL || hero.getKey() == key_t.UNLOCKED)
			return;
		
		if(hero.getKey() == key_t.LEVER) {
			map[5][0] = 'S';
			map[6][0] = 'S';
			
			hero.updateKey(key_t.UNLOCKED);
		}
	}
	
	protected void drawEntities() {
		guard.drawPosition();
		hero.drawPosition();
	}
	
	protected void updateLevelStatus() {
		
		//Check if hero found the exit
		if((hero.getY() == 5 || hero.getY() == 6) && hero.getX() == 0) {
			levelStatus = status_t.WON;
			return;
		}
		
		//If the guard is harmless, skip the rest
		if(guard.isHarmless())
			return;
		
		//Check if the hero is in the same spot as the guard 
		else if(hero.getX() == guard.getX() && hero.getY() == guard.getY()) {
			levelStatus = status_t.LOST;
			return;
		}
		
		//Check if the guard caught the hero
		int[][] adjacent = new int[][] {
			{ guard.getY() + 1, guard.getX()},
			{ guard.getY() - 1, guard.getX()},
			{ guard.getY(), guard.getX() + 1},
			{ guard.getY(), guard.getX() - 1}
			};

		for (int[] spot : adjacent) {
			if (map[spot[0]][spot[1]] == hero.getIcon()) {
				levelStatus = status_t.LOST;
				return;
			}
		}
	}
	
	protected void loadEnemies() {

		switch(Game.guardPersonality) {
		case "Rookie":
			guard = new Rookie(8, 1, this);
			break;
		case "Drunken":
			guard = new Drunken(8, 1, this);
			break;
		case "Suspicious":
			guard = new Suspicious(8, 1, this);
			break;
			
		default:
			guard = new Rookie(0, 0, null);				
		}
	}
}
