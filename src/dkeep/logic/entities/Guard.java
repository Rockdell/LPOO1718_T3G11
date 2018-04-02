package dkeep.logic.entities;

import java.util.Arrays;
import java.util.List;

import dkeep.logic.objects.DKObject;
import dkeep.logic.objects.Door;

public abstract class Guard extends Entity {
	
	/** Regular movement pattern. */
	private List<Character> movingPattern = Arrays.asList('a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w');
	
	/** Inverse movement pattern. */
	private List<Character> movingPatternReverse = Arrays.asList('d','w','w','w','w','d','d','d','d','d','d','w','a','a','a','a','a','a','a','s','s','s','s','s');
	
	/** Index of the next movement. */
	private int nextMove;
	
	/** True if guard is moving in reverse, false otherwise. */
	private boolean reverse;
	
	/** True if guard is harmless, false otherwise. */
	private boolean harmless;
	
	/** Creates an object Guard.
	 * @param x X-position of the guard.
	 * @param y Y-position of the guard.
	 * @param l Current level. */
	public Guard(int x, int y) {
		super(x, y, 'G');
		nextMove = 0;
		reverse = false;
		harmless = false;
	}
	
	/** @return True if guard is harmless, false otherwise. */
	public boolean isHarmless() {
		return harmless;
	}
	
	/** Sets the guard as harmless/not-harmless.
	 * @param harmless True to make guard harmless, false otherwise. */
	public void setHarmless(boolean harmless) {
		this.harmless = harmless;
	}
	
	/** Move the guard. */
	protected void move() {
		
		char direction;
		
		if(reverse) {
			
			direction = movingPatternReverse.get(nextMove);
			
			if(nextMove - 1 < 0)
				nextMove = movingPattern.size() - 1;
			else
				nextMove--;
		}
		else {
	
			direction = movingPattern.get(nextMove);
			
			if(nextMove + 1 > movingPattern.size() - 1)
				nextMove = 0;
			else
				nextMove++;
		}
	
		generatePosition(direction, getX(), getY(), false);
	}
	
	/** Patrol the level. */
	public abstract void patrol();
	
	/** Reverse the movement of the guard. */
	protected void reversePath() {
		
		if(reverse) {
			
			if(nextMove + 1 > movingPattern.size() - 1)
				nextMove = 0;
			else
				nextMove++;
			
			reverse = false;
		}
		else {
			
			if(nextMove - 1 < 0)
				nextMove = movingPattern.size() - 1;
			else
				nextMove--;
			
			reverse = true;
		}
	}
	
	public void drawEntity() {
		DKObject.level.getMap()[getY()][getX()] = getIcon();	
	}
	
	public void eraseEntity() {
		DKObject.level.getMap()[getY()][getX()] = ' ';
	}
	
	public boolean checkCollision(int x, int y) {
		
		if (DKObject.level.getMap()[y][x] == 'X')
			return false;
		
		for(Door door : DKObject.level.getDoors()) {
			
			if(door.equalPosition(x, y) && !door.isOpen())
				return false;
		}
		
		return true;
	}
	
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
