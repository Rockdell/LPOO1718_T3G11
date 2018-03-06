package dkeep.logic.characters;

//import java.util.Map;

import dkeep.logic.layout.Level;

public class Hero extends Entity {
	
	//0 -> doesn't have key
	//1 -> has key
	//2 -> unlock door
	//-1 -> doors open
	private int key;
	
	public Hero(int x, int y, Level l) {
		super(x, y, l, 'H');
		key = 0;
	}
	
	public int getKey() {
		return key;
	}
	
	public void updateKey(int k) {
		key = k;
	}
	
	public void move(char direction) {
		generatePosition(direction, getX(), getY(), false);
	}
	
	protected boolean checkCollision(int x, int y) {
		
		if(getLevel().getMap()[y][x] == 'I' && key == 1) {
			key = 2;
			updateIcon('A');
			return false;
		}
		else if(getLevel().getMap()[y][x] == 'k') {
			key = 1;
		}
		else if (getLevel().getMap()[y][x] == 'X' || getLevel().getMap()[y][x] == 'I'
				|| getLevel().getMap()[y][x] == 'g' || getLevel().getMap()[y][x] == '8')
			return false;
		
		if(getLevel().getID() == 2 && getKey() == 1)
			updateIcon('K');
		else
			updateIcon('A');
		
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
