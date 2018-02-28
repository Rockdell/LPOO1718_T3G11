package dkeep.logic.characters;

import dkeep.logic.layout.Level;

import java.util.Random;

public class Suspicious extends Guard {
	
	private int turnAround;
	
	public Suspicious(int x, int y, Level l) {
		super(x, y, l);
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
	
	private int nextTurnAround() {
		
		Random turn = new Random();
		
		return turn.nextInt(5) + 1;
	}
}
