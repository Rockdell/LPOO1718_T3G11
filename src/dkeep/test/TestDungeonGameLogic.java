package dkeep.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import dkeep.cli.Game;
import dkeep.logic.characters.Hero.key_t;
import dkeep.logic.layout.Level.status_t;

public class TestDungeonGameLogic {
	
	@Test
	public void moveHeroIntoFreeCell() throws IOException, FileNotFoundException {
		
		Game test1 = new Game(3);
		
		assertEquals(1, test1.getCurrentLevel().getHero().getX());
		assertEquals(1, test1.getCurrentLevel().getHero().getY());
		
		test1.getCurrentLevel().display();
		test1.getCurrentLevel().updateLevel('s');
		
		assertEquals(1, test1.getCurrentLevel().getHero().getX());
		assertEquals(2, test1.getCurrentLevel().getHero().getY());
	}
	
	@Test
	public void heroMovesAgainstWall() throws IOException, FileNotFoundException {
		
		Game test2 = new Game(3);
		
		assertEquals(1, test2.getCurrentLevel().getHero().getX());
		assertEquals(1, test2.getCurrentLevel().getHero().getY());
		
		test2.getCurrentLevel().updateLevel('w');
		
		assertEquals(1, test2.getCurrentLevel().getHero().getX());
		assertEquals(1, test2.getCurrentLevel().getHero().getY());
	}

	@Test
	public void heroCaughtByGuard() throws IOException, FileNotFoundException {
		
		Game test2 = new Game(3);
		
		assertEquals(status_t.ONGOING, test2.getCurrentLevel().getLevelStatus());
		
		test2.getCurrentLevel().updateLevel('d');
		
		assertEquals(status_t.LOST, test2.getCurrentLevel().getLevelStatus());
	}
	
	@Test
	public void heroFailsToLeave() throws IOException, FileNotFoundException {
		
		Game test3 = new Game(3);
		
		assertEquals(status_t.ONGOING, test3.getCurrentLevel().getLevelStatus());
		
		test3.getCurrentLevel().updateLevel('s');
		test3.getCurrentLevel().updateLevel('d');
		
		assertEquals(status_t.ONGOING, test3.getCurrentLevel().getLevelStatus());
	}
	
	@Test
	public void heroPicksUpKeyAndOpensDoors() throws IOException, FileNotFoundException {
		
		Game test4 = new Game(3);
		
		assertEquals(key_t.NULL, test4.getCurrentLevel().getHero().getKey());
		
		test4.getCurrentLevel().display();
		test4.getCurrentLevel().updateLevel('s');
		test4.getCurrentLevel().updateLevel('s');
		test4.getCurrentLevel().display();
		
		assertEquals(key_t.UNLOCKED, test4.getCurrentLevel().getHero().getKey());	
	}
	
	@Test
	public void heroOpensDoorsAndLeavesRoom() throws IOException, FileNotFoundException {
		
		Game test5 = new Game(3);
		
		assertEquals(status_t.ONGOING, test5.getCurrentLevel().getLevelStatus());
		
		test5.getCurrentLevel().updateLevel('s');
		test5.getCurrentLevel().updateLevel('s');
		test5.getCurrentLevel().updateLevel('a');
		
		assertEquals(status_t.WON, test5.getCurrentLevel().getLevelStatus());
	}
	
}
