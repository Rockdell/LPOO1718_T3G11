package characters;

import layout.Level;

public class Guard extends Entity {
	
	private char[] movingPattern = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	private int lastMove;
	private Level currentLevel;
	
	public Guard(int x, int y, Level l) {
		super(x, y, 'G');
		lastMove = 0;
		currentLevel = l;
	}
	
	public void move() {
		
		//Erase old position
		eraseLastPosition();
		
		int next_x = getX(), next_y = getY();
		
		char direction = movingPattern[lastMove];
		
		if(lastMove + 1 > 23)
			lastMove = 0;
		else
			lastMove++;
	
		//Generate new position
		generatePosition(direction, next_x, next_y);
		
		//Save new position
		currentLevel.getMap()[getY()][getX()] = 'G';		
	}
	
	protected boolean checkCollision(int x, int y) {
		
		if(currentLevel.getMap()[y][x] == 'X' || currentLevel.getMap()[y][x] == 'I')
			return false;
		else
			return true;
	}
	
	protected void eraseLastPosition() {
		currentLevel.getMap()[getY()][getX()] = ' ';
	}
}
