package characters;

import java.util.Random;

public class Ogre extends Entity {
	
	private Random intGenerator = new Random();
	
	public Ogre(int x, int y) {
		super(x, y);
	}
	
	public void move(char[][] m) {
		
		//Delete old position
		m[getY()][getX()] = ' ';
		
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
			
			//TODO Error -> array index -1 sometimes -> why?
			// Check for collision
			if (m[next_y][next_x] != 'X' && m[next_y][next_x] != 'I') {

				// Set new x and y
				setX(next_x);
				setY(next_y);

				accepted = true;
			}
		}
		
		//Save new position
		m[getY()][getX()] = 'O';
	}
}
