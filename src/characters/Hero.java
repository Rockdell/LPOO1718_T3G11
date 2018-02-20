package characters;

public class Hero extends Entity {
	
	//0 -> doesn't have key
	//1 -> has key
	//2 -> unlock door
	//-1 -> doors open
	private int key;
	
	private char icon = 'H';
	
	public Hero(int x, int y) {
		super(x, y);
		key = 0;
	}

	public char getIcon() {
		return icon;
	}
	
	public int getKey() {
		return key;
	}
	
	public void setKey(int k) {
		key = k;
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
		
		//Checking for collision
		if(m[next_y][next_x] == 'I') {
			if(key == 1)
				key = 2;
		}
		else if(m[next_y][next_x] != 'X' && m[next_y][next_x] != 'I') {
			
			//Set new x and y
			setX(next_x);
			setY(next_y);
			
			//Check if it got a key
			if(m[next_y][next_x] == 'k')
				key = 1;
		}

		if (m.length == 9 && getKey() == 1)
			icon = 'K';
		else
			icon = 'H';
		
		//Save new position
		m[getY()][getX()] = icon;
	}
	
	private void eraseLastPosition(char[][] m) {
		if(((getX() == 2 && getY() == 3) || (getX() == 4 && getY() == 3) || (getX() == 0 && getY() == 5) || (getX() == 0 && getY() == 6) ||
				(getX() == 2 && getY() == 8) || (getX() == 4 && getY() == 8)) && m.length == 10) {
			m[getY()][getX()] = 'S';
		}
		else
			m[getY()][getX()] = ' ';
	}
}
