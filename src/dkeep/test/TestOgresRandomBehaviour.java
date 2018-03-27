package dkeep.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import dkeep.ui.cli.Game;
import dkeep.io.ConsoleIO;
import dkeep.logic.layout.LevelTest2;
import dkeep.logic.layout.Level.status_t;

public class TestOgresRandomBehaviour {

	@Test(timeout = 1000)
	public void testOgresRandomMove() throws IOException, FileNotFoundException {

		// Calls the constructor with "Rookie" but we never use the Guard, just for the constructor only
		Game test = new Game(new ConsoleIO(), "Rookie", 1, 4);
		LevelTest2 lt2 = (LevelTest2) test.getCurrentLevel();
		// Allow Ogre to move and swing club
		lt2.setMoveOgre(true);
		lt2.setSwing(true);

		boolean outcome1 = false, outcome2 = false, outcome3 = false, outcome4 = false;

		while (!outcome1 || !outcome2 || !outcome3 || !outcome4) {

			int[] b = { lt2.getOgre().getX(), lt2.getOgre().getY() };

			// Move Hero
			test.getCurrentLevel().updateLevel('s');
			
			if(status_t.KILLED == test.getCurrentLevel().getLevelStatus())
				fail("Ogre killed Hero!");

			int[] a = { lt2.getOgre().getX(), lt2.getOgre().getY() };

			if (a[0] == b[0] - 1 && a[1] == b[1]) // Ogre moved to the left
				outcome1 = true;
			else if (a[0] == b[0] + 1 && a[1] == b[1]) // Ogre moved to the right
				outcome2 = true;
			else if (a[0] == b[0] && a[1] == b[1] - 1) // Ogre moved up
				outcome3 = true;
			else if (a[0] == b[0] && a[1] == b[1] + 1) // Ogre moved down
				outcome4 = true;
			else
				fail("Ogre moved unexpectedly!");
		}
	}
	
	@Test(timeout = 1000)
	public void testOgresRandomSwing() throws IOException, FileNotFoundException {

		// Calls the constructor with "Rookie" but we never use the Guard, just for the constructor only
		Game test = new Game(new ConsoleIO(), "Rookie", 1, 4);
		LevelTest2 lt2 = (LevelTest2) test.getCurrentLevel();
		// Allow Ogre to move and swing club
		lt2.setMoveOgre(true);
		lt2.setSwing(true);

		boolean outcome1 = false, outcome2 = false, outcome3 = false, outcome4 = false;

		while (!outcome1 || !outcome2 || !outcome3 || !outcome4) {

			// Move Hero
			test.getCurrentLevel().updateLevel('s');
			
			if(status_t.KILLED == test.getCurrentLevel().getLevelStatus())
				fail("Ogre killed Hero!");

			int[] o = { lt2.getOgre().getX(), lt2.getOgre().getY() };
			int[] s = { lt2.getOgre().getWeapon().getX(), lt2.getOgre().getWeapon().getY() };

			if (s[0] == o[0] - 1 && s[1] == o[1]) // Ogre swinged left
				outcome1 = true;
			else if (s[0] == o[0] + 1 && s[1] == o[1]) // Ogre swinged right
				outcome2 = true;
			else if (s[0] == o[0] && s[1] == o[1] - 1) // Ogre swinged up
				outcome3 = true;
			else if (s[0] == o[0] && s[1] == o[1] + 1) // Ogre swinged down
				outcome4 = true;
			else
				fail("Ogre moved unexpectedly!");
		}
	}

}
