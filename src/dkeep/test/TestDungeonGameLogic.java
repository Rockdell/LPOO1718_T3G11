package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.cli.Game;

public class TestDungeonGameLogic {
	
	@Test
	public void moveHeroIntoFreeCell() {
		
		Game test = new Game(3);
		
		assertEquals(1, test.getCurrentLevel().getHero().getX());
		assertEquals(1, test.getCurrentLevel().getHero().getY());
		
		test.getCurrentLevel().updateLevel('s');
		
		assertEquals(1, test.getCurrentLevel().getHero().getX());
		assertEquals(1, test.getCurrentLevel().getHero().getY());
	}

}
