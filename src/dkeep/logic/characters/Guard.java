package dkeep.logic.characters;

import dkeep.logic.layout.Level;

public abstract class Guard extends Entity {
	
	private char[] movingPattern = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	private int lastMove;
	
	public Guard(int x, int y, Level l) {
		super(x, y, l, 'G');
		lastMove = 0;
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
		getLevel().getMap()[getY()][getX()] = 'G';		
	}
	
	protected abstract void patrol();
	
	protected boolean checkCollision(int x, int y) {
		
		if(getLevel().getMap()[y][x] == 'X' || getLevel().getMap()[y][x] == 'I')
			return false;
		else
			return true;
	}
	
	protected void eraseLastPosition() {
		getLevel().getMap()[getY()][getX()] = ' ';
	}
}
