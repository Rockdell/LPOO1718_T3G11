package dkeep.logic.characters;

//import java.util.Map;

import dkeep.logic.layout.Level;

public class Hero extends Entity {
	
	/**
	 *	Key' possible states.
	 */
	public enum key_t { 
		/** No key */ NULL,
		/** Got key. */ KEY, 
		/** Touched lever. */ LEVER, 
		/** Unlocking exit. */ UNLOCKING,
		/** Exit is unlocked. */ UNLOCKED 
		};
	
	/**
	 * Key state.
	 */
	private key_t key;
	
	/**
	 * Creates an object Hero.
	 * @param x X-position of the hero.
	 * @param y Y-position of the hero.
	 * @param l Current level.
	 */
	public Hero(int x, int y, Level l) {
		super(x, y, l, 'H');
		key = key_t.NULL;
	}
	
	/**
	 * @return Key's state.
	 */
	public key_t getKey() {
		return key;
	}
	
	/**
	 * Updates key's value.
	 * @param k New key value.
	 */
	public void updateKey(key_t k) {
		key = k;
	}
	
	/**
	 * Moves the hero.
	 * @param direction Direction to move.
	 */
	public void move(char direction) {
		generatePosition(direction, getX(), getY(), false);
	}
		
	public void drawPosition() {
		getLevel().getMap()[getY()][getX()] = getIcon();
	}
	
	public void erasePosition() {
		
		switch(getLevel().getID()) {
		case 1:
			if((getX() == 2 && getY() == 3) ||(getX() == 4 && getY() == 3) || (getX() == 0 && getY() == 5) || (getX() == 0 && getY() == 6) || 
					(getX() == 2 && getY() == 8) || (getX() == 4 && getY() == 8) || (getX() == 4 && getY() == 1)) {
				getLevel().getMap()[getY()][getX()] = 'S';
			}
			else
				getLevel().getMap()[getY()][getX()] = ' ';
			break;
		case 2:
			getLevel().getMap()[getY()][getX()] = ' ';
			break;
		case 3:
			getLevel().getMap()[getY()][getX()] = ' ';
		case 4:
			getLevel().getMap()[getY()][getX()] = ' ';
		}
	}
	
	public boolean checkCollision(int x, int y) {
		
		if(getLevel().getMap()[y][x] == 'k') {
			
			if(getLevel().getID() == 1)
				key = key_t.LEVER;
			else if (getLevel().getID() == 2) {
				key = key_t.KEY;
				updateIcon('K');
			}
			else if (getLevel().getID() == 3) {
				key = key_t.LEVER;
			}
		}
		else if(getLevel().getMap()[y][x] == 'X' || getLevel().getMap()[y][x] == 'g' || getLevel().getMap()[y][x] == '8')
			return false;
		
		else if(getLevel().getMap()[y][x] == 'I') {
			
			if(key == key_t.KEY)
				key = key_t.UNLOCKING;
			
			return false;
		}
		
		return true;
	}
}
