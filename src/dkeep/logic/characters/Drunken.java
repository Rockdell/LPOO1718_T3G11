package dkeep.logic.characters;

import java.util.Random;
import dkeep.logic.layout.Level;

public class Drunken extends Guard {

	private boolean sleeping;
	private int rounds_asleep;
	
	public Drunken(int x, int y, Level l) {
		super(x, y, l);
		sleeping = false;
		rounds_asleep = 0;
	}
	
	public void patrol() {
		
		//Is the guard sleeping?
		if(sleeping) {
			
			if(rounds_asleep == 0) {
				
				boolean reverse = new Random().nextBoolean();
			
				//Invert the way
				if(reverse)
					reversePath();
				
				wakeUp();
				
				move();
			}
			else
				rounds_asleep--;
		}
		else {
			
			int sleep = new Random().nextInt(10) + 1;
			
			// 3 in 10 chance of falling asleep
			if(sleep > 7)
				fallAsleep();
			else
				move();
		}
	}
	
	private void fallAsleep() {
		
		sleeping = true;
		setArmless(true);
		updateIcon('g', true);
		rounds_asleep = new Random().nextInt(4) + 1;
	}
	
	private void wakeUp() {
		
		sleeping = false;
		setArmless(false);
		updateIcon('G', true);
	}
}