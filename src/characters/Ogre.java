package characters;

import java.util.Random;

public class Ogre extends Entity {
	
	private Random intGenerator;
	private char icon = 'O';
	private int club_x = getX();
	private int club_y = getY();
	private char lastDirection = 'n';
	private char club_icon = '*';
	
	public Ogre(int x, int y) {
		super(x, y);
		intGenerator = new Random();
	}
	
	public char[] getClub() {
		return new char[] {(char) club_x, (char) club_y, lastDirection};
	}
	
	public void move(char[][] m) {
		
		eraseLastPosition(m);
		
		//Ogre position
		generatePosition(getX(), getY(), m);
		
		club_x = getX();
		club_y = getY();
	
		//Club position
		lastDirection = generatePosition(club_x, club_y, m);
		
		//Save new position
		if(m[getY()][getX()] == 'k')
			icon = '$';
		else
			icon = 'O';
		
		if(m[club_y][club_x] == 'k')
			club_icon = '$';
		else
			club_icon = '*';
		
		m[getY()][getX()] = icon;
		m[club_y][club_x] = club_icon;
	}
	
	private char generatePosition(int x, int y, char[][] m) {
		
		char direction = 'n';
		
		//Keep generating inputs until an accepted movement is generated
		boolean accepted = false;
		while(!accepted) {
					
			//Random number between 0 and 3;
			int nextMove = intGenerator.nextInt(4);
	
			switch (nextMove) {
			case 0:
				direction = 'w';
				y = getY() - 1;
				break;
			case 1:
				direction = 'a';
				x = getX() - 1;
				break;
			case 2:
				direction = 's';
				y = getY() + 1;
				break;
			case 3:
				direction = 'd';
				x = getX() + 1;
				break;
			}
			
			// Check for collision
			if (m[y][x] != 'X' && m[y][x] != 'I' && m[y][x] != 'S') {
	
				// Set new x and y
				setX(x);
				setY(y);
	
				accepted = true;
			}
		}
		
		return direction;
		
	}

	private void eraseLastPosition(char[][] m) {
		
		if (m[club_y][club_y] == '$')
			m[club_y][club_x] = 'k';
		else
			m[club_y][club_x] = ' ';
		
		if (m[getY()][getX()] == '$')
			m[getY()][getX()] = 'k';
		else
			m[getY()][getX()] = ' ';
	}
}
