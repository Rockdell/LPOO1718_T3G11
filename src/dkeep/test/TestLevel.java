package dkeep.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import dkeep.io.ConsoleIO;
import dkeep.logic.engine.Game;

public class TestLevel {

	@Test
	public void testLoadingLevel() {
		
		Game test1 = new Game(new ConsoleIO());
		test1.loadLevel(98);
		
		assertEquals(98, test1.getCurrentLevel().getID());
	}
	
	@Test
	public void testEndgameSummary() {
		
		Game test3 = new Game(new ConsoleIO());
		test3.loadLevel(98);
		
		assertEquals("Moving ", test3.getCurrentLevel().endgameSummary());
		
		test3.getCurrentLevel().updateLevel('d');
		
		assertEquals("You got killed by the enemy!", test3.getCurrentLevel().endgameSummary());
	}
}
