package characters;

import java.util.Random;

public class Ogre extends Entity {
	
	private Random intGenerator = new Random();
	
	private char icon = 'O';
	
	public Ogre(int x, int y) {
		super(x, y);
	}
	
	public void move(char[][] m) {
		
		eraseLastPosition(m);
		
		//Save old x and y, in case the new x and y are not accept
		int next_x = getX(), next_y = getY();
		
		//Keep generating inputs until an accepted movement is generated
		boolean accepted = false;
		while(!accepted) {
			
			//Random number between 0 and 3;
			int nextMove = intGenerator.nextInt(4);

			switch (nextMove) {
			case 0:
				// w
				next_y = getY() - 1;
				break;
			case 1:
				// a
				next_x = getX() - 1;
				break;
			case 2:
				// s
				next_y = getY() + 1;
				break;
			case 3:
				// d
				next_x = getX() + 1;
				break;
			}
			
			// Check for collision
			if (m[next_y][next_x] != 'X' && m[next_y][next_x] != 'I' && m[next_y][next_x] != 'S') {

				// Set new x and y
				setX(next_x);
				setY(next_y);

				accepted = true;
			}
		}
		
		//Save new position
		if(m[getY()][getX()] == 'k')
			icon = '$';
		else
			icon = 'O';
			
		m[getY()][getX()] = icon;
	}

	private void eraseLastPosition(char[][] m) {
		if (m[getY()][getX()] == '$')
			m[getY()][getX()] = 'k';
		else
			m[getY()][getX()] = ' ';
	}
}
