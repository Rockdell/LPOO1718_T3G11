package dkeep.logic.entities;

import dkeep.logic.layout.Level;

public class Rookie extends Guard {

	/**
	 * Creates an object Rookie.
	 * @param x X-position of the guard.
	 * @param y Y-position of the guard.
	 * @param l Current level.
	 */
	public Rookie(int x, int y, Level l) {
		super(x, y, l);
	}
	
	public void patrol() {
		move();
	}
}
