//package dkeep.test;
//
//import static org.junit.Assert.assertEquals;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import org.junit.Test;
//
//import dkeep.io.ConsoleIO;
//import dkeep.logic.layout.Level.status_t;
//import dkeep.ui.cli.Game;
//import dkeep.logic.entities.Hero.key_t;
//import dkeep.logic.layout.*;
//
//public class TestDungeonGameLogic {
//
//	@Test
//	public void moveHeroIntoFreeCell() throws IOException, FileNotFoundException {
//		
//		Game test1 = new Game(new ConsoleIO(), "Rookie", 1, 3);
//		
//		assertEquals(1, test1.getCurrentLevel().getHero().getX());
//		assertEquals(1, test1.getCurrentLevel().getHero().getY());
//		
//		test1.getCurrentLevel().updateLevel('s');
//		
//		assertEquals(1, test1.getCurrentLevel().getHero().getX());
//		assertEquals(2, test1.getCurrentLevel().getHero().getY());
//	}
//	
//	@Test
//	public void heroMovesAgainstWall() throws IOException, FileNotFoundException {
//		
//		Game test2 = new Game(new ConsoleIO(), "Rookie", 1, 3);
//		
//		assertEquals(1, test2.getCurrentLevel().getHero().getX());
//		assertEquals(1, test2.getCurrentLevel().getHero().getY());
//		
//		test2.getCurrentLevel().updateLevel('w');
//		
//		assertEquals(1, test2.getCurrentLevel().getHero().getX());
//		assertEquals(1, test2.getCurrentLevel().getHero().getY());
//	}
//
//	@Test
//	public void heroCaughtByGuard() throws IOException, FileNotFoundException {
//		
//		Game test2 = new Game(new ConsoleIO(), "Rookie", 1, 3);
//		LevelTest lv = (LevelTest) test2.getCurrentLevel();
//		
//		//Checks if the Guard is able to capture the Hero
//		assertEquals(false, lv.getGuard().isHarmless());
//		
//		assertEquals(status_t.ONGOING, test2.getCurrentLevel().getStatus());
//		
//		test2.getCurrentLevel().updateLevel('d');
//		
//		assertEquals(status_t.CAUGHT, test2.getCurrentLevel().getStatus());
//	}
//	
//	@Test
//	public void heroFailsToLeave() throws IOException, FileNotFoundException {
//		
//		Game test3 = new Game(new ConsoleIO(), "Rookie", 1, 3);
//		
//		assertEquals(status_t.ONGOING, test3.getCurrentLevel().getStatus());
//		
//		test3.getCurrentLevel().updateLevel('s');
//		test3.getCurrentLevel().updateLevel('a');
//		
//		assertEquals('I',test3.getCurrentLevel().getMap()[2][0]);
//		assertEquals('I',test3.getCurrentLevel().getMap()[3][0]);
//		assertEquals(status_t.ONGOING, test3.getCurrentLevel().getStatus());
//	}
//	
//	@Test
//	public void heroPicksUpKeyAndOpensDoors() throws IOException, FileNotFoundException {
//		
//		Game test4 = new Game(new ConsoleIO(), "Rookie", 1, 3);
//		
//		assertEquals('I',test4.getCurrentLevel().getMap()[2][0]);
//		assertEquals('I',test4.getCurrentLevel().getMap()[3][0]);
//		assertEquals(key_t.NULL, test4.getCurrentLevel().getHero().getKey());
//		
//		test4.getCurrentLevel().updateLevel('s');
//		test4.getCurrentLevel().updateLevel('s');
//		
//		assertEquals('S',test4.getCurrentLevel().getMap()[2][0]);
//		assertEquals('S',test4.getCurrentLevel().getMap()[3][0]);
//		assertEquals(key_t.UNLOCKED, test4.getCurrentLevel().getHero().getKey());	
//	}
//	
//	@Test
//	public void heroOpensDoorsAndLeavesRoom() throws IOException, FileNotFoundException {
//		
//		Game test5 = new Game(new ConsoleIO(), "Rookie", 1, 3);
//		
//		assertEquals(status_t.ONGOING, test5.getCurrentLevel().getStatus());
//		
//		test5.getCurrentLevel().updateLevel('s');
//		test5.getCurrentLevel().updateLevel('s');
//		test5.getCurrentLevel().updateLevel('a');
//		
//		assertEquals(status_t.WON, test5.getCurrentLevel().getStatus());
//	}
//	
//}
