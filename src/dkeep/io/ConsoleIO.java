package dkeep.io;

import java.util.Scanner;

public class ConsoleIO implements IO {

	private Scanner is;

	public ConsoleIO() {
		is = new Scanner(System.in);
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

		char input = is.next().charAt(0);
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

	public void finalize() {
		is.close();
	}
}
