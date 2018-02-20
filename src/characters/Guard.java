package characters;

public class Guard extends Entity {
	
	private char[] movingPattern = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	private int lastMove = 0;
	
	public Guard(int x, int y) {
		super(x, y);
	}
	
	public void move(char[][] m) {
		
		//Delete old position
		m[getY()][getX()] = ' ';
		
		//Save old x and y, in case the new x and y are not accept
		int next_x = getX(), next_y = getY();
		
		char direction = movingPattern[lastMove];
		
		if(lastMove + 1 > 23)
			lastMove = 0;
		else
			lastMove++;
		
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
		
		//Set new x and y (no need to check collision)
		setX(next_x);
		setY(next_y);
		
		//Save new position
		m[getY()][getX()] = 'G';		
	}
}
