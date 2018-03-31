package dkeep.logic.entities;

import java.util.Random;

public class Suspicious extends Guard {
	
	/**
	 * Number of rounds until the guard turns around.
	 */
	private int turnAround;
	
	/**
	 * Creates an object Suspicious.
	 * @param x X-position of the guard.
	 * @param y Y-position of the guard.
	 * @param l Current level.
	 */
	public Suspicious(int x, int y) {
		super(x, y);
		turnAround = nextTurnAround();
	}
	
	public void patrol() {
		
		move();
		turnAround--;
		
		if(turnAround == 0) {
			reversePath();
			turnAround = nextTurnAround();	
		}
	}
	
	/**
	 * Calculates when the guard is going to turn around.
	 * @return Number of rounds until the guard turns around.
	 */
	private int nextTurnAround() {
		
		Random turn = new Random();
		
		return turn.nextInt(5) + 1;
	}
}
