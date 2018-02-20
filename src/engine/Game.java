package engine;
import layout.*;
import io.*;

public class Game {
	
	private Level level;
	private InputScanner is;
	
	public Game() {
		
		level = new Level();
		is = new InputScanner();
	}
	
	public void startGame() {
		
		boolean stopGame = false;
		while(!stopGame) {
			
			//Display map every iteration
			switch(level.display()) {
			case 0:
				break;
			case 1:
				stopGame = true;
				continue;
			case 2:
				continue;
			}
			
			//Read input
			char input = is.readInput();
			
			//Move entities
			level.moveHero(input);
		}
		
		is.closeInput();
	}	
}
