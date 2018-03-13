package dkeep.logic.layout;

import java.util.Random;

import dkeep.logic.characters.*;

public class Level01 extends Level {
	
	private Guard guard;
	
	public Level01() {
		map = Maps.map01;
		mapID =  1;
		hero = new Hero(1, 1, this);
		levelStatus = status_t.ONGOING;
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
	
	protected void drawEntities() {
		guard.drawPosition();
		hero.drawPosition();
	}
	
	protected void openDoors(int key) {

		if(key == 1) {
			map[5][0] = 'S';
			map[6][0] = 'S';
		}
		
		hero.updateKey(-1);
	}
	
	protected void updateLevelStatus() {
		
		//Check if hero found the exit
		if((hero.getY() == 5 || hero.getY() == 6) && hero.getX() == 0) {
			levelStatus = status_t.WON;
			return;
		}
		
		//If the guard is harmless, skip the rest
		if(guard.getHarmless())
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
		
		int guard_type = new Random().nextInt(3);
		
		switch(guard_type) {
		case 0:
			guard = new Rookie(8, 1, this);
			break;
		case 1:
			guard = new Drunken(8, 1, this);
			break;
		case 2:
			guard = new Suspicious(8, 1, this);
			break;
			
		default:
			guard = new Rookie(0, 0, null);				
		}
	}
}
