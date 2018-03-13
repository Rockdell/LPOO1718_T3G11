package dkeep.logic.characters;

//import java.util.Map;

import dkeep.logic.layout.Level;

public class Hero extends Entity {
	
	public enum key_t { NULL, LEVER, KEY, UNLOCKING, UNLOCKED }
	
	private key_t key;
	
	public Hero(int x, int y, Level l) {
		super(x, y, l, 'H');
		key = key_t.NULL;
	}
	
	public key_t getKey() {
		return key;
	}
	
	public void updateKey(key_t k) {
		key = k;
	}
	
	public void move(char direction) {
		generatePosition(direction, getX(), getY(), false);
	}
	
	protected boolean checkCollision(int x, int y) {
		
		switch(getLevel().getID()) {
		case 1:
			if(getLevel().getMap()[y][x] == 'k')
				key = key_t.LEVER;
			else if(getLevel().getMap()[y][x] == 'X' || getLevel().getMap()[y][x] == 'I')
				return false;
			break;
		case 2:
			if(getLevel().getMap()[y][x] == 'k') {
				key = key_t.KEY;
				updateIcon('K');
			}
			else if(getLevel().getMap()[y][x] == 'I' && key == key_t.KEY) {
				key = key_t.UNLOCKING;
				return false;
			}
			else if(getLevel().getMap()[y][x] == 'X' || getLevel().getMap()[y][x] == 'I' || getLevel().getMap()[y][x] == 'g' || getLevel().getMap()[y][x] == '8')
				return false;			
			break;
		case 3:
			if(getLevel().getMap()[y][x] == 'k')
				key = key_t.LEVER;
			else if(getLevel().getMap()[y][x] == 'X' || getLevel().getMap()[y][x] == 'I')
				return false;
			break;
		}
		
		return true;
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
		}
	}
}
