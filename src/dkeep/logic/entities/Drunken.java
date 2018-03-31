package dkeep.logic.entities;

import java.util.Random;

public class Drunken extends Guard {

	/** True if the guard is sleeping, false otherwise. */
	private boolean isSleeping;
	
	/** Number of rounds the guard is going to be asleep. */
	private int rounds_asleep;
	
	/** Creates an object Drunken.
	 * @param x X-position of the guard.
	 * @param y Y-position of the guard.
	 * @param l Current level.
	 */
	public Drunken(int x, int y) {
		super(x, y);
		isSleeping = false;
		rounds_asleep = 0;
	}
	
	public void patrol() {
		
		//Is the guard sleeping?
		if(isSleeping) {
			
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
		
			if(sleep > 7)
				fallAsleep();
			else
				move();
		}
	}
	
	/** Guard falls asleep. */
	private void fallAsleep() {
		
		isSleeping = true;
		setHarmless(true);
		updateIcon('g');
		rounds_asleep = new Random().nextInt(4) + 1;
	}
	
	/** Guard wakes up. */
	private void wakeUp() {
		
		isSleeping = false;
		setHarmless(false);
		updateIcon('G');
	}
}