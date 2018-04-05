package dkeep.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import dkeep.engine.Game;
import dkeep.io.ConsoleIO;
import dkeep.logic.entities.Entity;
import dkeep.logic.entities.Ogre;

public class TestOgreRandomBehaviour {

	@Test(timeout = 1000)
	public void testOgresRandomMove() throws IOException, FileNotFoundException {

		Game test1 = new Game(new ConsoleIO());
		test1.loadLevel(97);

		boolean outcome1 = false, outcome2 = false, outcome3 = false, outcome4 = false;

		// Finds the Ogre among the enemies
		Ogre o = null;
		for (Entity e : test1.getCurrentLevel().getEnemies()) {
			if (e instanceof Ogre)
				o = (Ogre) e;
		}

		while (!outcome1 || !outcome2 || !outcome3 || !outcome4) {

			int[] b = { o.getX(), o.getY() };

			// Move Hero
			test1.getCurrentLevel().updateLevel('w');

			int[] a = { o.getX(), o.getY() };

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

		Game test1 = new Game(new ConsoleIO());
		test1.loadLevel(97);

		boolean outcome1 = false, outcome2 = false, outcome3 = false, outcome4 = false;

		// Finds the Ogre among the enemies
		Ogre o = null;
		for (Entity e : test1.getCurrentLevel().getEnemies()) {
			if (e instanceof Ogre)
				o = (Ogre) e;
		}

		while (!outcome1 || !outcome2 || !outcome3 || !outcome4) {

			// Move Hero
			test1.getCurrentLevel().updateLevel('s');

			int[] ogre_position = { o.getX(), o.getY() };
			int[] club_position = { o.getWeapon().getX(), o.getWeapon().getY() };

			if (club_position[0] == ogre_position[0] - 1 && club_position[1] == ogre_position[1]) // Ogre swinged left
				outcome1 = true;
			else if (club_position[0] == ogre_position[0] + 1 && club_position[1] == ogre_position[1]) // Ogre swinged right
				outcome2 = true;
			else if (club_position[0] == ogre_position[0] && club_position[1] == ogre_position[1] - 1) // Ogre swinged up
				outcome3 = true;
			else if (club_position[0] == ogre_position[0] && club_position[1] == ogre_position[1] + 1) // Ogre swinged down
				outcome4 = true;
			else
				fail("Ogre moved unexpectedly!");
		}
	}

}