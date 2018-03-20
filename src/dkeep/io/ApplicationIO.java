package dkeep.io;

import javax.swing.JTextArea;

public class ApplicationIO implements IO {
	
	private JTextArea console;
	static public char input = 'n';

	public ApplicationIO(JTextArea c) {
		console = c;
	}
	
	public void write(String out) {
		console.append(out);
	}
	
	public char read() {	
		return input;		
	}
	
	public void clearConsole() {
		console.setText("");
	}
}
