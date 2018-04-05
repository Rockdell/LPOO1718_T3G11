package dkeep.io;

import java.util.Scanner;

/** CLI's implementation for input/output. */
public class ConsoleIO implements IO {

	/** Scanner to scan input from console. */
	private Scanner _is;

	/** Creates an instance of ConsoleIO. */
	public ConsoleIO() {
		_is = new Scanner(System.in);
	}

	public void write(char[][] map) {

		for (int i = 0; i < map.length; i++) {

			for (int j = 0; j < map[i].length; j++) {

				System.out.print(map[i][j] + " ");
			}

			System.out.print("\n");
		}
	}

	public char read() {

		char input = _is.next().charAt(0);
		input = Character.toLowerCase(input);

		// Check if we can accept that char
		if (input == 'w' || input == 's' || input == 'a' || input == 'd')
			return input;
		else
			return read();
	}

	public void clear() {

		for (int i = 0; i < 10; i++)
			System.out.print("\n");
	}
	
	/** Closes the scanner. */
	public void finalize() {
		_is.close();
	}
}
