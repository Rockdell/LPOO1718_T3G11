package dkeep.io;

import dkeep.ui.gui.GraphicsMap;

/** GUI's implementation for input/output. */
public class ApplicationIO implements IO {

	/** Input from user. */
	static public char 	input;
	
	/** Screen to be used as display. */
	private GraphicsMap _display;

	/** Creates an instance of ApplicationIO. */
	public ApplicationIO() {}
	
	/** Creates an instance of ApplicationIO.
	 * @param display Screen to be used.
	 */
	public ApplicationIO(GraphicsMap display) {
		_display = display;
	}
	
	public void write(char [][] map) {
		_display.append(map);
	}
	
	public char read() {	
		return input;		
	}

	public void clear() {
		char[][] empty = { {} };
		_display.append(empty);
	}
}
