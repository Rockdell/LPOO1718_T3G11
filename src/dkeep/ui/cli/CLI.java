package dkeep.ui.cli;

import java.util.Random;

import dkeep.io.ConsoleIO;
import dkeep.launcher.Launcher;
import dkeep.logic.engine.Game;

public class CLI implements Launcher {
	
	public static void main(String[] args) {
		new CLI().start();
	}

	public CLI() {}
	
	public void start() {
		
		Game game = new Game(new ConsoleIO());
		game.loadLevel(1, _generateRandomGuard(), _generateRandomOgres());
		
		boolean over =  false;
		do {
			over = game.tick();
		}
		while(!over);
	}
	
	private String _generateRandomGuard() {
		
		Random rn = new Random();
		int random_guard = rn.nextInt(3);
		
		switch(random_guard) {
		case 0:
			return "Rookie";
		case 1:
			return "Drunken";
		case 2:
			return "Suspicious";
		}
		
		return "";
	}
	
	private int _generateRandomOgres() {
		
		Random rn = new Random();
		return rn.nextInt(5) + 1;
	}
}
