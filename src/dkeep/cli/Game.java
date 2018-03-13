package dkeep.cli;

import dkeep.logic.layout.Level;
import dkeep.logic.layout.Level01;
import dkeep.logic.layout.Level02;

public class Game {
	
	private Level level;
	private InputScanner is;
	
	public static void main(String[] args) {
		
		Game newGame = new Game(1);
		
		newGame.startGame();
	}
	
	public Game(int id) {
		loadLevel(id);
		is = new InputScanner();
	}
	
	public Level getCurrentLevel() {
		return level;
	}
	
	private void loadLevel(int id) {
		
		if(id == 1)
			level = new Level01();
		else if(id == 2)
			level = new Level02();
	}
	
	public void startGame() {
		
		boolean stopGame = false;
		while(!stopGame) {
			
			//Display the current level
			level.display();
			
			//Check level's status
			switch(level.getLevelStatus()) {
			case ONGOING:
				break;
			case WON:
				if(level.getID() == 1)
					loadLevel(level.getID() + 1);
				else {
					System.out.println("You win!");
					stopGame = true;
				}
				continue;
			case LOST:
				System.out.println("You lose!");
				stopGame = true;
				continue;				
			}
			
			//Read input
			char input = is.readInput();
			
			//Move entities
			level.updateLevel(input);
		}
	}
}
