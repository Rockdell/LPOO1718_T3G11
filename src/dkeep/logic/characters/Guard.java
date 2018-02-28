package dkeep.logic.characters;

import java.util.Arrays;
import java.util.List;

import dkeep.logic.layout.Level;

public abstract class Guard extends Entity {
	
	private List<Character> movingPattern = Arrays.asList('a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w');
	private List<Character> movingPatternInverse = Arrays.asList('d','w','w','w','w','d','d','d','d','d','d','w','a','a','a','a','a','a','a','s','s','s','s','s');
	private int nextMove;
	private int way;
	private boolean armless;
	
	public Guard(int x, int y, Level l) {
		super(x, y, l, 'G');
		nextMove = 0;
		way = 0;
		armless = false;
	}
	
	public boolean getArmless() {
		return armless;
	}
	
	public void setArmless(boolean armless) {
		this.armless = armless;
	}
	
	protected void move() {
		
		char direction;
		
		//Erase old position
		eraseLastPosition();
		
		int next_x = getX(), next_y = getY();
		
		if(way == 0)
			direction = movingPattern.get(nextMove);
		else
			direction = movingPatternInverse.get(nextMove);
		
		switch(way) {
		case 0:
			if(nextMove + 1 > movingPattern.size() - 1)
				nextMove = 0;
			else
				nextMove++;
			break;
		case 1:
			if(nextMove - 1 < 0)
				nextMove = movingPattern.size() - 1;
			else
				nextMove--;
		}
	
		//Generate new position
		generatePosition(direction, next_x, next_y);
		
		//Save new position
		getLevel().getMap()[getY()][getX()] = getIcon();		
	}
	
	public abstract void patrol();
	
	protected boolean checkCollision(int x, int y) {
		
		if(getLevel().getMap()[y][x] == 'X' || getLevel().getMap()[y][x] == 'I')
			return false;
		else
			return true;
	}
	
	protected void eraseLastPosition() {
		getLevel().getMap()[getY()][getX()] = ' ';
	}
	
	protected void changeWay() {
		
		if(way == 0) {
			
			if(nextMove - 1 < 0)
				nextMove = movingPattern.size() - 1;
			else
				nextMove--;
			
			way = 1;
		}
		else {
			
			if(nextMove + 1 > movingPattern.size() - 1)
				nextMove = 0;
			else
				nextMove++;
			
			way = 0;
		}
	}
}
