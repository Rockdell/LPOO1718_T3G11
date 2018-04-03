package dkeep.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dkeep.io.ConsoleIO;
import dkeep.logic.entities.Hero.hero_t;
import dkeep.logic.layout.Level.status_t;
import dkeep.engine.Game;

public class TestEntities {
	
	@Test
	public void giveWeaponToOgre() {
		
		Game test1 = new Game(new ConsoleIO());
		test1.loadLevel(99);
		
		//assertEquals(test1.getCurrentLevel().getEnemies().get(0), ((Ogre) test1.getCurrentLevel().getEnemies().get(0)).);
	}

}
