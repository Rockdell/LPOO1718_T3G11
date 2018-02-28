package dkeep.logic.characters;

import java.util.Random;

import dkeep.logic.layout.Level;

public class Drunken extends Guard {

	//1 -> moving (normal or inverse path)
	//0 -> sleeping
	private int state;
	private int rounds_asleep;
	
	public Drunken(int x, int y, Level l) {
		super(x, y, l);
		state = 1;
		rounds_asleep = 0;
	}
	
	public void patrol() {
		
		switch(state)
		{
		case 0:
			
			if(rounds_asleep == 0)
			{
				int invert = new Random().nextInt(2);
			
				if(invert == 0)
					changeWay();
				
				setArmless(false);
				updateIcon('G');
				state = 1;
				
				//move already updates icon on map
				move();
			}
			else
				rounds_asleep--;
			
			break;
		case 1:
			
			int sleep = new Random().nextInt(10);
			
			if(sleep >= 7)
			{
				state = 0;
				setArmless(true);
				updateIcon('g');
				updateIconNow();
				rounds_asleep = new Random().nextInt(4) + 1;
			}
			else
				move();
			
			break;
		}
	}	
}