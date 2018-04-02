package dkeep.ui.gui;

import java.awt.EventQueue;

import dkeep.launcher.Launcher;
import dkeep.ui.gui.LinkStart;

public class GUI implements Launcher {
	
	public static void main(String[] args) {
		new GUI().start();
	}
	
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
