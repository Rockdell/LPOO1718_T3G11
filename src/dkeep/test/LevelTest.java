package dkeep.test;

import java.io.FileNotFoundException;

import java.io.IOException;

import dkeep.logic.characters.Guard;
import dkeep.logic.characters.Hero;
import dkeep.logic.characters.Rookie;
import dkeep.logic.characters.Hero.key_t;
import dkeep.logic.layout.Level;

public class LevelTest extends Level {
	
	private Guard guard;
	
	public LevelTest() throws IOException, FileNotFoundException {
		loadMap(3);
		mapID =  3;
		hero = new Hero(1, 1, this);
		levelStatus = status_t.ONGOING;
		loadEnemies();
	}
	
	public void updateEntities(char d) {
		hero.move(d);
	}
	
	public void drawEntities() {
		guard.drawPosition();
		hero.drawPosition();
	}
	
	public void updateDoors() {
		
		if(hero.getKey() == key_t.NULL || hero.getKey() == key_t.UNLOCKED)
			return;
		
		if(hero.getKey() == key_t.LEVER) {
			map[2][0] = 'S';
			map[3][0] = 'S';
			
			hero.updateKey(key_t.UNLOCKED);
		}		
	}

	public void clearEntities() {
		hero.erasePosition();
		guard.erasePosition();
	}

	public void updateLevelStatus() {
		
		//Check if hero found the exit
		if((hero.getY() == 2 || hero.getY() == 3) && hero.getX() == 0) {
			levelStatus = status_t.WON;
			return;
		}
		
		//Check if the hero is in the same spot as the guard 
		else if(hero.getX() == guard.getX() && hero.getY() == guard.getY()) {
			levelStatus = status_t.CAUGHT;
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
				levelStatus = status_t.CAUGHT;
				return;
			}
		}	
	}

	public void loadEnemies() {
		guard = new Rookie(3, 1, this);
	}
}
