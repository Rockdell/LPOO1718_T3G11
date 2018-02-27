package dkeep.cli;
import dkeep.logic.layout.Level;
import dkeep.logic.layout.Level01;
import dkeep.logic.layout.Level02;

public class Game {
	
	private Level level;
	private InputScanner is;
	
	public static void main(String[] args) {
		
		Game newGame = new Game();
		
		newGame.startGame();
	}
	
	public Game() {
		
		loadLevel(2);
		is = new InputScanner();
	}
	
	private void loadLevel(int id) {
		
		if(id == 1)
			level = new Level01();
		else if(id == 2)
			level = new Level02();
	}
	
	public void startGame() {
		
		boolean stopGame = false;
		boolean wonGame = false;
		while(!stopGame) {
			
			//Display map every iteration
			switch(level.display()) {
			case 0:
				break;
			case 1:
				stopGame = true;
				continue;
			case 2:
				if(level.getID() == 1) {
					loadLevel(2);
					continue;
				}
				else if(level.getID() == 2) {
					stopGame = true;
					wonGame = true;
					continue;
				}
			}
			
			//Read input
			char input = is.readInput();
			
			//Move entities
			level.updateLevel(input);
		}
		
		if(wonGame)
			System.out.println("You won!");
		else
			System.out.println("You lose!");
	}
}
