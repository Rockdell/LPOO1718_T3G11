package dkeep.io;

import javax.swing.JPanel;

import dkeep.ui.gui.GraphicsMap;

public class ApplicationIO implements IO {
	
	private GraphicsMap display;
	static public char input = 'n';

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
		//console.setText("");
	}
}
