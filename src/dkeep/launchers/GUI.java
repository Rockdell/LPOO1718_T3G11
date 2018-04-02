package dkeep.launchers;

import java.awt.EventQueue;

import dkeep.ui.gui.LinkStart;

public class GUI implements Launcher {
	
	public GUI() {}
	
	public void start() {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				new LinkStart();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});
	}
}
