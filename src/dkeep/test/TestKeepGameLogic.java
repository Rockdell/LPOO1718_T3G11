package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.io.ConsoleIO;
import dkeep.logic.entities.Hero.key_t;
import dkeep.logic.engine.Game;
import dkeep.logic.entities.Ogre;
import dkeep.logic.layout.Level.status_t;

public class TestKeepGameLogic {

	@Test
	public void heroKilledByOgre() {
		
		Game test1 = new Game(new ConsoleIO());
		
		//It's not guaranteed that the hero is killed (50% chance of the club hitting him)
		do {
			
		test1.loadLevel(99);
	
		assertEquals(status_t.ONGOING, test1.getCurrentLevel().getStatus());
		
		test1.getCurrentLevel().updateLevel('a');
		test1.getCurrentLevel().updateLevel('d');
	
		}
		while(test1.getCurrentLevel().getStatus() != status_t.KILLED);
		
		assertEquals(status_t.KILLED, test1.getCurrentLevel().getStatus());
	}
	
	@Test 
	public void heroPicksUpKey() {
		
		Game test2 = new Game(new ConsoleIO());
		test2.loadLevel(99);
	
		assertEquals('A', test2.getCurrentLevel().getHero().getIcon());
		
		test2.getCurrentLevel().updateLevel('s');
		
		assertEquals('K', test2.getCurrentLevel().getHero().getIcon());
		assertEquals(key_t.KEY, test2.getCurrentLevel().getHero().getKey());
		assertEquals('E', test2.getCurrentLevel().getMap()[4][0]);
	}
	
	@Test
	public void heroFailsToOpenDoor() {
		
		Game test2 = new Game(new ConsoleIO());
		test2.loadLevel(97);
		
		assertEquals('A', test2.getCurrentLevel().getHero().getIcon());
		assertEquals('E', test2.getCurrentLevel().getMap()[1][0]);
		
		test2.getCurrentLevel().updateLevel('a');
		
		assertEquals('A', test2.getCurrentLevel().getHero().getIcon());
		assertEquals(key_t.NULL, test2.getCurrentLevel().getHero().getKey());
		assertEquals('E', test2.getCurrentLevel().getMap()[1][0]);	
	}
	
	@Test 
	public void heroOpensDoorsAndLeaves() {
		
		Game test3 = new Game(new ConsoleIO());
		test3.loadLevel(99);
	
		assertEquals(status_t.ONGOING, test3.getCurrentLevel().getStatus());
		assertEquals(key_t.NULL, test3.getCurrentLevel().getHero().getKey());
		
		test3.getCurrentLevel().updateLevel('s');
		
		assertEquals(key_t.KEY, test3.getCurrentLevel().getHero().getKey());
		assertEquals('E', test3.getCurrentLevel().getMap()[4][0]);
		
		test3.getCurrentLevel().updateLevel('s');
		test3.getCurrentLevel().updateLevel('s');
		test3.getCurrentLevel().updateLevel('a');
		
		assertEquals(key_t.KEY, test3.getCurrentLevel().getHero().getKey());
		assertEquals('e', test3.getCurrentLevel().getMap()[4][0]);
		assertEquals('K', test3.getCurrentLevel().getMap()[4][1]);
		
		assertEquals(status_t.ONGOING, test3.getCurrentLevel().getStatus());
		
		test3.getCurrentLevel().updateLevel('a');
		
		assertEquals('K', test3.getCurrentLevel().getMap()[4][0]);
		assertEquals(status_t.PROCEED, test3.getCurrentLevel().getStatus());
	}
	
	@Test
	public void heroStunsOgre() {
		
		Game test4 = new Game(new ConsoleIO());
		test4.loadLevel(99);
		
		assertEquals(status_t.ONGOING, test4.getCurrentLevel().getStatus());
		assertEquals(false, ((Ogre) test4.getCurrentLevel().getEnemies().get(0)).isStunned());
		
		test4.getCurrentLevel().updateLevel('d');
		
		assertEquals(status_t.ONGOING, test4.getCurrentLevel().getStatus());
		assertEquals(true, ((Ogre) test4.getCurrentLevel().getEnemies().get(0)).isStunned());
	}
	
	@Test
	public void heroTriesToOpenRegularDoor() {
		
		Game test5 = new Game(new ConsoleIO());
		test5.loadLevel(99);
		
		assertEquals('I', test5.getCurrentLevel().getMap()[2][0]);
		
		test5.getCurrentLevel().updateLevel('s');
		test5.getCurrentLevel().updateLevel('a');

		assertEquals('I', test5.getCurrentLevel().getMap()[2][0]);
		assertEquals('K', test5.getCurrentLevel().getMap()[2][1]);
	}
	
	@Test
	public void heroStunsOgreAndTriesToMove() {
		
		Game test6 = new Game(new ConsoleIO());
		test6.loadLevel(99);
		
		test6.getCurrentLevel().updateLevel('d');
		
		assertEquals(status_t.ONGOING, test6.getCurrentLevel().getStatus());
		assertEquals(true, ((Ogre) test6.getCurrentLevel().getEnemies().get(0)).isStunned());
		
		test6.getCurrentLevel().updateLevel('d');
		
		assertEquals('A', test6.getCurrentLevel().getMap()[1][2]);
		assertEquals('8', test6.getCurrentLevel().getMap()[1][3]);
	}
}
