package dkeep.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import dkeep.io.ConsoleIO;
import dkeep.logic.layout.Level.status_t;
import dkeep.engine.Game;

public class TestEngine {
	
//	@Test
//	public void testTick() throws InterruptedException {
//		Game test1 = new Game(new ConsoleIO());
//		test1.loadLevel(98);
//		
//		assertEquals(1, test1.getCurrentLevel().getHero().getX());
//		assertEquals(1, test1.getCurrentLevel().getHero().getY());
//		
//		test1.tick();		
//		
//		assertEquals(1, test1.getCurrentLevel().getHero().getX());
//		assertEquals(2, test1.getCurrentLevel().getHero().getY());		
//	}
	
	@Test
	public void testSaveLoad() {
		Game test2 = new Game(new ConsoleIO());
		test2.loadLevel(99);
		
		test2.getCurrentLevel().updateLevel('s');
		
		assertEquals('K', test2.getCurrentLevel().getMap()[2][1]);
		assertEquals(status_t.ONGOING, test2.getCurrentLevel().getStatus());
		
		test2.save();
		
		Game test3 = new Game(new ConsoleIO());
		test3.load();
		
		assertEquals('K', test3.getCurrentLevel().getMap()[2][1]);
		assertEquals(status_t.ONGOING, test3.getCurrentLevel().getStatus());
	}
	
	@Test
	public void testExistingMaps() {

		Game test3 = new Game(new ConsoleIO());

		assert (test3.existentMaps().size() >= 2);
	}

}
