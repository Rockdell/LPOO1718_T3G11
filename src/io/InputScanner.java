package io;

import java.util.Scanner;

public class InputScanner {

	private Scanner is;
	
	public InputScanner() {
		is = new Scanner(System.in);
	}
	
	public char readInput() {
		
		System.out.print("\nCommand: ");
		
		char input = is.next().charAt(0);
		input = Character.toLowerCase(input);
		
		//Check if we can accept that char
		if(input == 'w' || input == 's' || input == 'a' || input == 'd')
			return input;
		else
			return readInput();
	}
	
	public void closeInput() {
		is.close();
	}
}
