package dkeep.io;

public interface IO {

	/** Reads input from user. */
	char read();
	
	/** Writes current map to the screen. */
	void write(char[][] map);
	
	/** Clears the console (only for CLI). */
	void clear();
}
