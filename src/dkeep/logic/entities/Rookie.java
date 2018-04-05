package dkeep.logic.entities;

/** Rookie personality. */
public class Rookie extends Guard {

	/** Creates an instance of Rookie.
	 * @param x X-position of the guard.
	 * @param y Y-position of the guard. */
	public Rookie(int x, int y) {
		super(x, y);
	}
	
	public void patrol() {
		move();
	}
}
