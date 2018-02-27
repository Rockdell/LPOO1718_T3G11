package dkeep.logic.characters;

import dkeep.logic.layout.Level;

import java.util.Random;

public class Suspicious extends Guard {
	
	private int nextTurnAround;
	
	public Suspicious(int x, int y, Level l) {
		super(x, y, l);
		nextTurnAround = generateNextTurnAround();
	}
	
	public void patrol() {
		
		move();
		nextTurnAround--;
		
		if(nextTurnAround == 0)
			changeWay();
		
		nextTurnAround = generateNextTurnAround();	
	}
	
	private int generateNextTurnAround() {
		
		Random turn = new Random();
		
		return (turn.nextInt(5) + 1);
	}
}
