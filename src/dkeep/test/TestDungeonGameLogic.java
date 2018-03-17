package dkeep.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import dkeep.cli.Game;
import dkeep.io.ConsoleIO;
import dkeep.logic.characters.Hero.key_t;
import dkeep.logic.layout.Level.status_t;

public class TestDungeonGameLogic {
	/*
	//Dungeon Tests
	@Test
	public void moveHeroIntoFreeCell() throws IOException, FileNotFoundException {
		
		Game test1 = new Game(new ConsoleIO(), "Rookie", 3, 3);
		
		assertEquals(1, test1.getCurrentLevel().getHero().getX());
		assertEquals(1, test1.getCurrentLevel().getHero().getY());
		
		test1.getCurrentLevel().updateLevel('s');
		
		assertEquals(1, test1.getCurrentLevel().getHero().getX());
		assertEquals(2, test1.getCurrentLevel().getHero().getY());
	}
	
	@Test
	public void heroMovesAgainstWall() throws IOException, FileNotFoundException {
		
		Game test2 = new Game(new ConsoleIO(), "Rookie", 3, 3);
		
		assertEquals(1, test2.getCurrentLevel().getHero().getX());
		assertEquals(1, test2.getCurrentLevel().getHero().getY());
		
		test2.getCurrentLevel().updateLevel('w');
		
		assertEquals(1, test2.getCurrentLevel().getHero().getX());
		assertEquals(1, test2.getCurrentLevel().getHero().getY());
	}

	@Test
	public void heroCaughtByGuard() throws IOException, FileNotFoundException {
		
		Game test2 = new Game(new ConsoleIO(), "Rookie", 3, 3);
		
		assertEquals(status_t.ONGOING, test2.getCurrentLevel().getLevelStatus());
		
		test2.getCurrentLevel().updateLevel('d');
		
		assertEquals(status_t.CAUGHT, test2.getCurrentLevel().getLevelStatus());
	}
	
	@Test
	public void heroFailsToLeave() throws IOException, FileNotFoundException {
		
		Game test3 = new Game(new ConsoleIO(), "Rookie", 3, 3);
		
		assertEquals(status_t.ONGOING, test3.getCurrentLevel().getLevelStatus());
		
		test3.getCurrentLevel().updateLevel('s');
		test3.getCurrentLevel().updateLevel('a');
		
		assertEquals('I',test3.getCurrentLevel().getMap()[2][0]);
		assertEquals('I',test3.getCurrentLevel().getMap()[3][0]);
		assertEquals(status_t.ONGOING, test3.getCurrentLevel().getLevelStatus());
	}
	*/
	@Test
	public void heroPicksUpKeyAndOpensDoors() throws IOException, FileNotFoundException {
		
		Game test4 = new Game(new ConsoleIO(), "Rookie", 3, 3);
		
		assertEquals('I',test4.getCurrentLevel().getMap()[2][0]);
		assertEquals('I',test4.getCurrentLevel().getMap()[3][0]);
		assertEquals(key_t.NULL, test4.getCurrentLevel().getHero().getKey());
		
		test4.getCurrentLevel().updateLevel('s');
		test4.getCurrentLevel().updateLevel('s');
		
		assertEquals('S',test4.getCurrentLevel().getMap()[2][0]);
		assertEquals('S',test4.getCurrentLevel().getMap()[3][0]);
		assertEquals(key_t.UNLOCKED, test4.getCurrentLevel().getHero().getKey());	
	}
	
	@Test
	public void heroOpensDoorsAndLeavesRoom() throws IOException, FileNotFoundException {
		
		Game test5 = new Game(new ConsoleIO(), "Rookie", 3, 3);
		
		assertEquals(status_t.ONGOING, test5.getCurrentLevel().getLevelStatus());
		
		test5.getCurrentLevel().updateLevel('s');
		test5.getCurrentLevel().updateLevel('s');
		test5.getCurrentLevel().updateLevel('a');
		
		assertEquals(status_t.WON, test5.getCurrentLevel().getLevelStatus());
	}
	
	//Keep Tests
	@Test 
	public void heroKilledByOgre() throws IOException, FileNotFoundException {
		
		//Calls the constructor with "Rookie" but we never use the Guard, just for the constructor only
		Game test = new Game(new ConsoleIO(), "Rookie", 3, 4);
	
		assertEquals(status_t.ONGOING, test.getCurrentLevel().getLevelStatus());
		
		test.getCurrentLevel().updateLevel('d');
		test.getCurrentLevel().updateLevel('d');
		
		assertEquals(status_t.KILLED, test.getCurrentLevel().getLevelStatus());
	}
	
	@Test 
	public void heroPicksUpKey() throws IOException, FileNotFoundException {
		
		//Calls the constructor with "Rookie" but we never use the Guard, just for the constructor only
		Game test = new Game(new ConsoleIO(), "Rookie", 3, 4);
	
		assertEquals('A', test.getCurrentLevel().getHero().getIcon());
		
		test.getCurrentLevel().updateLevel('s');
		test.getCurrentLevel().updateLevel('s');
		test.getCurrentLevel().updateLevel('s');
		test.getCurrentLevel().updateLevel('d');
		test.getCurrentLevel().updateLevel('d');
		test.getCurrentLevel().updateLevel('d');
		
		assertEquals('K', test.getCurrentLevel().getHero().getIcon());
		assertEquals(key_t.KEY, test.getCurrentLevel().getHero().getKey());
	}
	
	@Test 
	public void heroFailsToLeaveKeep() throws IOException, FileNotFoundException {
		
		//Calls the constructor with "Rookie" but we never use the Guard, just for the constructor only
		Game test = new Game(new ConsoleIO(), "Rookie", 3, 4);
	
		assertEquals(status_t.ONGOING, test.getCurrentLevel().getLevelStatus());
		assertEquals(key_t.NULL, test.getCurrentLevel().getHero().getKey());
		
		test.getCurrentLevel().updateLevel('s');
		test.getCurrentLevel().updateLevel('s');
		test.getCurrentLevel().updateLevel('s');
		test.getCurrentLevel().updateLevel('a');
		
		assertEquals(key_t.NULL, test.getCurrentLevel().getHero().getKey());
		assertEquals(status_t.ONGOING, test.getCurrentLevel().getLevelStatus());
	}
	
	@Test 
	public void heroOpensDoorWithKey() throws IOException, FileNotFoundException {
		
		//Calls the constructor with "Rookie" but we never use the Guard, just for the constructor only
		Game test = new Game(new ConsoleIO(), "Rookie", 3, 4);
	
		assertEquals(status_t.ONGOING, test.getCurrentLevel().getLevelStatus());
		
		test.getCurrentLevel().updateLevel('s');
		test.getCurrentLevel().updateLevel('s');
		test.getCurrentLevel().updateLevel('s');
		test.getCurrentLevel().updateLevel('d');
		test.getCurrentLevel().updateLevel('d');
		test.getCurrentLevel().updateLevel('d');
		
		//Got the key
		assertEquals('K', test.getCurrentLevel().getHero().getIcon());
		assertEquals(key_t.KEY, test.getCurrentLevel().getHero().getKey());
		
		test.getCurrentLevel().updateLevel('a');
		test.getCurrentLevel().updateLevel('a');
		test.getCurrentLevel().updateLevel('a');
		test.getCurrentLevel().updateLevel('a');
		
		assertEquals('S',test.getCurrentLevel().getMap()[4][0]);
		assertEquals(key_t.UNLOCKED, test.getCurrentLevel().getHero().getKey());
		assertEquals(status_t.ONGOING, test.getCurrentLevel().getLevelStatus());
	}
	
	@Test 
	public void heroWinsTheGame() throws IOException, FileNotFoundException {
		
		//Calls the constructor with "Rookie" but we never use the Guard, just for the constructor only
		Game test = new Game(new ConsoleIO(), "Rookie", 3, 4);
	
		assertEquals(status_t.ONGOING, test.getCurrentLevel().getLevelStatus());
		
		test.getCurrentLevel().updateLevel('s');
		test.getCurrentLevel().updateLevel('s');
		test.getCurrentLevel().updateLevel('s');
		test.getCurrentLevel().updateLevel('d');
		test.getCurrentLevel().updateLevel('d');
		test.getCurrentLevel().updateLevel('d');
		
		//Got the key
		assertEquals('K', test.getCurrentLevel().getHero().getIcon());
		assertEquals(key_t.KEY, test.getCurrentLevel().getHero().getKey());
		
		test.getCurrentLevel().updateLevel('a');
		test.getCurrentLevel().updateLevel('a');
		test.getCurrentLevel().updateLevel('a');
		test.getCurrentLevel().updateLevel('a');
		
		assertEquals('S',test.getCurrentLevel().getMap()[4][0]);
		assertEquals(key_t.UNLOCKED, test.getCurrentLevel().getHero().getKey());
		assertEquals(status_t.ONGOING, test.getCurrentLevel().getLevelStatus());
		
		test.getCurrentLevel().updateLevel('a');
		assertEquals(status_t.WON, test.getCurrentLevel().getLevelStatus());
	}
	
}
