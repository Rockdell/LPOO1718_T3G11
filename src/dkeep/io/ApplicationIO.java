package dkeep.io;

import dkeep.ui.gui.GraphicsMap;

public class ApplicationIO implements IO {
	
	private GraphicsMap display;
	static public char input = 'n';

	public ApplicationIO() {
		
	}
	
	public ApplicationIO(GraphicsMap jp) {
		display = jp;
	}
	
	public void write(char [][] map) {
		display.append(map);
	}
	
	public char read() {	
		return input;		
	}

	public void clearConsole() {
		char[][] empty = { {} };
		display.append(empty);
	}
}
