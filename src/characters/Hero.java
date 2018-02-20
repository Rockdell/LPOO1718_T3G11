package characters;

public class Hero extends Entity {
	
	private int key;
	
	public Hero(int x, int y) {
		super(x, y);
		key = 0;
	}
	
	public boolean hasKey() {
		
		if(key == 1) {
			key = -1;
			return true;
		}
		else
			return false;
	}
	
	public void move(char direction, char[][] m) {
		
		//Delete old position
		eraseLastPosition(m);
		
		//Save old x and y, in case the new x and y are not accept
		int next_x = getX(), next_y = getY();
		
		switch(direction) {
		case 'w':
			next_y = getY() - 1;
			break;
		case 'a':
			next_x = getX() - 1;
			break;
		case 's':
			next_y = getY() + 1;
			break;
		case 'd':
			next_x = getX() + 1;
			break;
		}
		
		//Check for collision
		if(m[next_y][next_x] != 'X' && m[next_y][next_x] != 'I') {
			
			//Set new x and y
			setX(next_x);
			setY(next_y);
			
			//TODO In map2, the player must open the door (it doesnt open automatically)
			//Check if it got a key
			if(m[next_y][next_x] == 'k')
				key = 1;
		}
		
		//Save new position
		m[getY()][getX()] = 'H';
	}
	
	private void eraseLastPosition(char[][] m) {
		if((getX() == 2 && getY() == 3) || (getX() == 4 && getY() == 3) || (getX() == 0 && getY() == 5) || (getX() == 0 && getY() == 6) ||
				(getX() == 2 && getY() == 8) || (getX() == 4 && getY() == 8)) {
			m[getY()][getX()] = 'S';
		}
		else
			m[getY()][getX()] = ' ';
	}
}
