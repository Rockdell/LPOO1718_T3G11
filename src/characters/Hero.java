package characters;

import layout.*;

public class Hero extends Entity {
	
	//0 -> doesn't have key
	//1 -> has key
	//2 -> unlock door
	//-1 -> doors open
	private int key;
	private Level currentLevel;
	
	public Hero(int x, int y, Level l) {
		super(x, y, 'H');
		key = 0;
		currentLevel = l;
	}
	
	public int getKey() {
		return key;
	}
	
	public void updateKey(int k) {
		key = k;
	}
	
	public void move(char direction) {
		
		//Erase last position
		eraseLastPosition();
		
		int next_x = getX(), next_y = getY();
		
		//Generate new position
		generatePosition(direction, next_x, next_y);
		
		//Save new position
		currentLevel.getMap()[getY()][getX()] = getIcon();
	}
	
	protected boolean checkCollision(int x, int y) {
		
		if(currentLevel.getMap()[y][x] == 'I' && key == 1) {
			key = 2;
			updateIcon('H');
			return false;
		}
		else if(currentLevel.getMap()[y][x] == 'k') {
			key = 1;
		}
		else if(currentLevel.getMap()[y][x] == 'X' || currentLevel.getMap()[y][x] == 'I')
			return false;
		
		if(currentLevel.getID() == 2 && getKey() == 1)
			updateIcon('K');
		else
			updateIcon('H');
		
		return true;
	}
	
	protected void eraseLastPosition() {
		
		switch(currentLevel.getID()) {
		case 1:
			if((getX() == 2 && getY() == 3) || (getX() == 4 && getY() == 3) || (getX() == 0 && getY() == 5) || (getX() == 0 && getY() == 6) ||
					(getX() == 2 && getY() == 8) || (getX() == 4 && getY() == 8)) {
				currentLevel.getMap()[getY()][getX()] = 'S';
			}
			else
				currentLevel.getMap()[getY()][getX()] = ' ';
			break;
		case 2:
			currentLevel.getMap()[getY()][getX()] = ' ';
			break;
		}
	}
}
