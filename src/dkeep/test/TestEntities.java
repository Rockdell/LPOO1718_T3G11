package dkeep.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import dkeep.io.ConsoleIO;
import dkeep.logic.engine.Game;
import dkeep.logic.entities.Ogre;
import dkeep.logic.layout.Level.status_t;

public class TestEntities {
	
	@Test
	public void giveWeaponToOgre() {
		
		Game test1 = new Game(new ConsoleIO());
		test1.loadLevel(99);
		
		assertEquals(test1.getCurrentLevel().getEnemies().get(0), ((Ogre) test1.getCurrentLevel().getEnemies().get(0)).getWeapon().getWielder());
	}
	
	@Test
	public void testGuardPersonalities() {
		
		Game test2 = new Game(new ConsoleIO());
		test2.loadLevel(1, "Drunken", 1);
		
		Game test3 = new Game(new ConsoleIO());
		test3.loadLevel(1, "Suspicious", 1);
		
		Game test4 = new Game(new ConsoleIO());
		test4.loadLevel(1, "Rookie", 1);
		
		for(int i = 0; i < 20; i++) {
			test2.getCurrentLevel().updateLevel('a');
			test3.getCurrentLevel().updateLevel('a');
			test4.getCurrentLevel().updateLevel('a');
		}
		
		assertEquals(status_t.ONGOING, test2.getCurrentLevel().getStatus());
		assertEquals(status_t.ONGOING, test3.getCurrentLevel().getStatus());
		assertEquals(status_t.ONGOING, test4.getCurrentLevel().getStatus());
		
		assertEquals('H', test2.getCurrentLevel().getMap()[1][1]);
		assertEquals('H', test3.getCurrentLevel().getMap()[1][1]);
		assertEquals('H', test4.getCurrentLevel().getMap()[1][1]);	
	}
	
	@Test
	public void testOgre() {
		
		Game test5 = new Game(new ConsoleIO());
		test5.loadLevel(97);
		
		for(int i = 0; i < 20; i++) {
			test5.getCurrentLevel().updateLevel('a');
		}
		
		assertEquals('A', test5.getCurrentLevel().getMap()[1][1]);
	}
	
}
