package dkeep.test;

import java.io.FileNotFoundException;

import java.io.IOException;

import dkeep.logic.characters.Hero;
import dkeep.logic.characters.Ogre;
import dkeep.logic.characters.Hero.key_t;
import dkeep.logic.layout.Level;

public class LevelTest2 extends Level {

	private Ogre ogre;
	
	public LevelTest2() throws IOException, FileNotFoundException {
		loadMap(4);
		mapID =  4;
		hero = new Hero(1, 1, this);
		hero.updateIcon('A');
		levelStatus = status_t.ONGOING;
		loadEnemies();
	}
	
	public void clearEntities() {
		hero.erasePosition();
		ogre.erasePosition();
	}
	
	public void updateEntities(char d) {
		hero.move(d);
	}
	
	public void updateDoors() {
		
		if(hero.getKey() == key_t.NULL || hero.getKey() == key_t.UNLOCKED)
			return;
		
		if(hero.getKey() == key_t.UNLOCKING) {
			map[4][0] = 'S';
			
			hero.updateKey(key_t.UNLOCKED);
		}		
	}
	
	public void drawEntities() {
		ogre.drawPosition();
		hero.drawPosition();
	}

	public void updateLevelStatus() {
		
		//Check if hero found the exit
		if(hero.getY() == 4 && hero.getX() == 0) {
			levelStatus = status_t.WON;
			return;
		}
		
		//Check if the hero is in the same spot as the ogre 
		else if(hero.getX() == ogre.getX() && hero.getY() == ogre.getY()) {
			levelStatus = status_t.KILLED;
			return;
		}
		
		//Check if the ogre killed the hero
		int[][] adjacent = new int[][] {
			{ ogre.getY() + 1, ogre.getX()},
			{ ogre.getY() - 1, ogre.getX()},
			{ ogre.getY(), ogre.getX() + 1},
			{ ogre.getY(), ogre.getX() - 1}
			};

		for (int[] spot : adjacent) {
			if (map[spot[0]][spot[1]] == hero.getIcon()) {
				levelStatus = status_t.KILLED;
				return;
			}
		}	
	}

	public void loadEnemies() {
		ogre = new Ogre(1, 4, this);
	}

	
	
}
