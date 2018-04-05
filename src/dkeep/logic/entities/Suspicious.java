package dkeep.logic.entities;

import java.util.Random;

/** Suspicious personality. */
public class Suspicious extends Guard {
	
	/** Number of rounds until the guard turns around. */
	private int _turnAround;
	
	/** Creates an instance of Suspicious.
	 * @param x X-position of the guard.
	 * @param y Y-position of the guard. */
	public Suspicious(int x, int y) {
		super(x, y);
		_turnAround = nextTurnAround();
	}
	
	public void patrol() {
		
		move();
		_turnAround--;
		
		if(_turnAround == 0) {
			reversePath();
			_turnAround = nextTurnAround();	
		}
	}
	
	/** Calculates when the guard is going to turn around.
	 * @return Number of rounds until the guard turns around. */
	private int nextTurnAround() {
		
		Random turn = new Random();
		
		return turn.nextInt(5) + 1;
	}
}
