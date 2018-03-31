package dkeep.logic.entities;

public class Rookie extends Guard {

	/**
	 * Creates an object Rookie.
	 * @param x X-position of the guard.
	 * @param y Y-position of the guard.
	 * @param l Current level.
	 */
	public Rookie(int x, int y) {
		super(x, y);
	}
	
	public void patrol() {
		move();
	}
}
