package dkeep.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import dkeep.cli.Game;
import dkeep.io.ConsoleIO;
import dkeep.logic.characters.Hero.key_t;
import dkeep.logic.layout.Level.status_t;

public class TestKeepGameLogic {

	@Test 
	public void heroKilledByOgre() throws IOException, FileNotFoundException {
		
		//Calls the constructor with "Rookie" but we never use the Guard, just for the constructor only
		Game test = new Game(new ConsoleIO(), "Rookie", 1, 4);
	
		assertEquals(status_t.ONGOING, test.getCurrentLevel().getLevelStatus());
		
		test.getCurrentLevel().updateLevel('d');
		test.getCurrentLevel().updateLevel('d');
		
		assertEquals(status_t.KILLED, test.getCurrentLevel().getLevelStatus());
	}
	
	@Test 
	public void heroPicksUpKey() throws IOException, FileNotFoundException {
		
		//Calls the constructor with "Rookie" but we never use the Guard, just for the constructor only
		Game test = new Game(new ConsoleIO(), "Rookie", 1, 4);
	
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
		Game test = new Game(new ConsoleIO(), "Rookie", 1, 4);
	
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
		Game test = new Game(new ConsoleIO(), "Rookie", 1, 4);
	
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
		Game test = new Game(new ConsoleIO(), "Rookie", 1, 4);
	
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
