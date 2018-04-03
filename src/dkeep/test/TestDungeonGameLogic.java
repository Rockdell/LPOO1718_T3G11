package dkeep.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dkeep.io.ConsoleIO;
import dkeep.logic.entities.Hero.hero_t;
import dkeep.logic.layout.Level.status_t;
import dkeep.engine.Game;

public class TestDungeonGameLogic {

	@Test
	public void moveHeroIntoFreeCell() {
		
		Game test1 = new Game(new ConsoleIO());
		test1.loadLevel(98, "Rookie", 3);
		
		assertEquals(1, test1.getCurrentLevel().getHero().getX());
		assertEquals(1, test1.getCurrentLevel().getHero().getY());
		
		test1.getCurrentLevel().updateLevel('s');
		
		assertEquals(1, test1.getCurrentLevel().getHero().getX());
		assertEquals(2, test1.getCurrentLevel().getHero().getY());
	}
	
	@Test
	public void heroMovesAgainstWall() {
		
		Game test2 = new Game(new ConsoleIO());
		test2.loadLevel(98, "Rookie", 3);
		
		assertEquals(1, test2.getCurrentLevel().getHero().getX());
		assertEquals(1, test2.getCurrentLevel().getHero().getY());
		
		test2.getCurrentLevel().updateLevel('w');
		
		assertEquals(1, test2.getCurrentLevel().getHero().getX());
		assertEquals(1, test2.getCurrentLevel().getHero().getY());
	}

	@Test
	public void heroCaughtByGuard() {
		
		Game test3 = new Game(new ConsoleIO());
		test3.loadLevel(98, "Rookie", 3);
		
		assertEquals(status_t.ONGOING, test3.getCurrentLevel().getStatus());
		
		test3.getCurrentLevel().updateLevel('d');
		
		assertEquals(status_t.KILLED, test3.getCurrentLevel().getStatus());
	}
	
	@Test
	public void heroFailsToLeave() {
		
		Game test4 = new Game(new ConsoleIO());
		test4.loadLevel(98, "Rookie", 3);
		
		assertEquals(status_t.ONGOING, test4.getCurrentLevel().getStatus());
		
		test4.getCurrentLevel().updateLevel('s');
		
		test4.getCurrentLevel().updateLevel('a');
		
		assertEquals('E', test4.getCurrentLevel().getMap()[2][0]);
		assertEquals('E', test4.getCurrentLevel().getMap()[3][0]);
		assertEquals('H', test4.getCurrentLevel().getMap()[2][1]);
		assertEquals(status_t.KILLED, test4.getCurrentLevel().getStatus());
	}
	
	@Test
	public void heroPicksUpLeverAndOpensDoors() {
		
		Game test5 = new Game(new ConsoleIO());
		test5.loadLevel(98, "Rookie", 3);
		
		assertEquals(status_t.ONGOING, test5.getCurrentLevel().getStatus());
		
		assertEquals('E', test5.getCurrentLevel().getMap()[2][0]);
		assertEquals('E', test5.getCurrentLevel().getMap()[3][0]);
		assertEquals('l', test5.getCurrentLevel().getMap()[3][1]);
		
		assertEquals(hero_t.NULL, test5.getCurrentLevel().getHero().getKey());
		
		test5.getCurrentLevel().updateLevel('s');
		test5.getCurrentLevel().updateLevel('s');
		
		assertEquals('e', test5.getCurrentLevel().getMap()[2][0]);
		assertEquals('e', test5.getCurrentLevel().getMap()[3][0]);
		assertEquals(hero_t.NULL, test5.getCurrentLevel().getHero().getKey());	
	}
	
	@Test
	public void heroOpensDoorAndLeavesRoom() {
		
		Game test6 = new Game(new ConsoleIO());
		test6.loadLevel(98, "Rookie", 3);
		
		assertEquals(status_t.ONGOING, test6.getCurrentLevel().getStatus());
		
		test6.getCurrentLevel().updateLevel('s');
		test6.getCurrentLevel().updateLevel('s');
		test6.getCurrentLevel().updateLevel('a');
		
		assertEquals('H', test6.getCurrentLevel().getMap()[3][0]);
		
		assertEquals(status_t.PROCEED, test6.getCurrentLevel().getStatus());
	}
	
	@Test
	public void heroRunsUpToGuard() {
		
		Game test7 = new Game(new ConsoleIO());
		test7.loadLevel(98);
		
		test7.getCurrentLevel().updateLevel('d');
		
		assertEquals(status_t.KILLED, test7.getCurrentLevel().getStatus());		
	}
}
