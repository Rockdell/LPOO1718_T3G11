package dkeep.io;

/** Interface for input/output. */
public interface IO {

	/** Reads input from user. */
	char read();
	
	/** Writes current map to the screen. */
	void write(char[][] map);
	
	/** Clears the screen. */
	void clear();
}
