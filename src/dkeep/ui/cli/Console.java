package dkeep.ui.cli;

import java.util.Random;

import dkeep.io.ConsoleIO;

public class Console {
	
	public static void main(String[] args) {
		
		Random rn = new Random();
		int random_guard = rn.nextInt(3);
		int random_ogres = rn.nextInt(5) + 1;
		
		String guard_type = "";
		
		switch(random_guard) {
		case 0:
			guard_type = "Rookie";
			break;
		case 1:
			guard_type = "Drunken";
			break;
		case 2:
			guard_type = "Suspicious";
			break;
		}
		
		Game g = new Game(new ConsoleIO(), guard_type, random_ogres, 1);
		
		g.startGame();
	}
}
