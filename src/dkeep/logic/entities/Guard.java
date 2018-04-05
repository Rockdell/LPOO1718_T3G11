package dkeep.logic.entities;

import java.util.Arrays;
import java.util.List;

import dkeep.logic.objects.DKObject;
import dkeep.logic.objects.Door;

/** Guard from level. */
public abstract class Guard extends Entity {
	
	/** Regular movement pattern. */
	private List<Character> movingPattern = Arrays.asList('a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w');
	
	/** Inverse movement pattern. */
	private List<Character> movingPatternReverse = Arrays.asList('d','w','w','w','w','d','d','d','d','d','d','w','a','a','a','a','a','a','a','s','s','s','s','s');
	
	/** Index of the next movement. */
	private int 			_nextMove;
	
	/** True if guard is moving in reverse, false otherwise. */
	private boolean			_reverse;
	
	/** True if guard is harmless, false otherwise. */
	private boolean 		_harmless;
	
	/** Creates an instance of Guard.
	 * @param x X-position of the guard.
	 * @param y Y-position of the guard. */
	public Guard(int x, int y) {
		super(x, y, 'G');
		_nextMove = 0;
		_reverse = false;
		_harmless = false;
	}
	
	/** @return True if guard is harmless, false otherwise. */
	public boolean isHarmless() {
		return _harmless;
	}
	
	/** Sets the guard as harmless/not-harmless.
	 * @param harmless True to make guard harmless, false otherwise. */
	public void setHarmless(boolean harmless) {
		_harmless = harmless;
	}
	
	/** Move the guard. */
	protected void move() {
		
		char direction;
		
		if(_reverse) {
			
			direction = movingPatternReverse.get(_nextMove);
			
			if(_nextMove - 1 < 0)
				_nextMove = movingPattern.size() - 1;
			else
				_nextMove--;
		}
		else {
	
			direction = movingPattern.get(_nextMove);
			
			if(_nextMove + 1 > movingPattern.size() - 1)
				_nextMove = 0;
			else
				_nextMove++;
		}
	
		generatePosition(direction, getX(), getY(), false);
	}
	
	/** Patrol the level. */
	public abstract void patrol();
	
	/** Reverse the movement of the guard. */
	protected void reversePath() {
		
		if(_reverse) {
			
			if(_nextMove + 1 > movingPattern.size() - 1)
				_nextMove = 0;
			else
				_nextMove++;
			
			_reverse = false;
		}
		else {
			
			if(_nextMove - 1 < 0)
				_nextMove = movingPattern.size() - 1;
			else
				_nextMove--;
			
			_reverse = true;
		}
	}
	
	public void drawEntity() {
		DKObject.level.getMap()[getY()][getX()] = getIcon();	
	}
	
	public void eraseEntity() {
		DKObject.level.getMap()[getY()][getX()] = ' ';
	}
	
	public boolean checkCollision(int x, int y) {
		
		if(!checkWalls(x, y) || !_checkDoors(x, y))
			return false;
		
		return true;
	}
	
	/** Checks if there's a door in a certain position of the map.
	 * @param x X-position to be checked.
	 * @param y Y-position to be checked.
	 * @return True if there's no door, false otherwise. */
	private boolean _checkDoors(int x, int y) {
		
		for(Door door : DKObject.level.getDoors()) {
			
			if(door.equalPosition(x, y) && !door.isOpen())
				return false;
		}
		
		return true;
	}
	
	/** Checks if the weapon hit a certain position of the map.
	 * @param x X-position to be checked.
	 * @param y Y-position to be checked.
	 * @return True if hit, false otherwise. */
	public boolean checkHit(int x, int y) {
		
		//Check if the guard caught the hero
		int[][] adjacent = new int[][] {
			{ getY() + 1, getX()},
			{ getY() - 1, getX()},
			{ getY(), getX() + 1},
			{ getY(), getX() - 1}
			};

		for (int[] spot : adjacent) {
			if (spot[1] == x && spot[0] == y) {
				return true;
			}
		}
		
		return false;		
	}
}
