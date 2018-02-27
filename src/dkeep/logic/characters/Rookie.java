package dkeep.logic.characters;

import dkeep.logic.layout.Level;

public class Rookie extends Guard {

	public Rookie(int x, int y, Level l) {
		super(x, y, l);
	}
	
	public void patrol() {
		move();
	}
}
