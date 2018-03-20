package dkeep.io;

import java.util.Scanner;

import dkeep.ui.cli.Game;

public class ConsoleIO implements IO {

	private Scanner is;
	
	public ConsoleIO() {
		is = new Scanner(System.in);
	}
	
	public void write(String out) {
		System.out.print(out);
	}
	
	public char read() {
		
		char input = is.next().charAt(0);
		input = Character.toLowerCase(input);
		
		//Check if we can accept that char
		if(input == 'w' || input == 's' || input == 'a' || input == 'd')
			return input;
		else
			return read();
	}
	
	public void clearConsole() {

		for(int i = 0; i < 10; i++)
			Game.io.write("\n");
	}
	
	public void finalize() {
		is.close();
	}
}
