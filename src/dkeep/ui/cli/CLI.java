package dkeep.ui.cli;

import java.util.Random;

import dkeep.engine.Game;
import dkeep.io.ConsoleIO;
import dkeep.launcher.Launcher;

public class CLI implements Launcher {
	
	public static void main(String[] args) {
		new CLI().start();
	}

	public CLI() {}
	
	public void start() {
		
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
		
		Game game = new Game(new ConsoleIO());
		game.loadLevel(1, guard_type, random_ogres);
		
		boolean over =  false;
		do {
			over = game.tick();
		}
		while(!over);
	}
}
