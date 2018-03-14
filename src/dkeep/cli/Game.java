package dkeep.cli;

import java.io.FileNotFoundException;
import java.io.IOException;

import dkeep.logic.layout.Level;
import dkeep.logic.layout.Level01;
import dkeep.logic.layout.Level02;
import dkeep.test.LevelTest;

public class Game {
	
	private Level level;
	private InputScanner is;
	
	public static void main(String[] args)  throws IOException, FileNotFoundException{
		
		Game newGame = new Game();
		
		newGame.startGame();
	}
	
	public Game()  throws IOException, FileNotFoundException{
		loadLevel(1);
		is = new InputScanner();
	}
	
	public Game(int id) throws IOException, FileNotFoundException {
		loadLevel(id);
		is = new InputScanner();
	}
	
	public Level getCurrentLevel() {
		return level;
	}
	
	private void loadLevel(int id) throws IOException, FileNotFoundException {
		
		if(id == 1)
			level = new Level01();
		else if(id == 2)
			level = new Level02();
		else if(id == 3)
			level = new LevelTest();
	}
	
	public void startGame() throws IOException, FileNotFoundException {
		
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
