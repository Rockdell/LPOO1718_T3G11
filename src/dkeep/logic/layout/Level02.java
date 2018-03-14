package dkeep.logic.layout;

import java.util.ArrayList;
import java.util.List;

import dkeep.cli.Game;
import dkeep.logic.characters.Hero;
import dkeep.logic.characters.Ogre;
import dkeep.logic.characters.Hero.key_t;

public class Level02 extends Level {
	
	/**
	 * Level's ogres.
	 */
	private List<Ogre> ogres;
	
	/**
	 * Creates an object Level02.
	 */
	public Level02() {
		
		map = Maps.keep;
		mapID = 2;
		hero = new Hero(1, 1, this);
		
		hero.updateIcon('A');
		
		loadEnemies();
	}
	
	protected void clearEntities() {

		for(Ogre o : ogres)
			o.erasePosition();
		
		hero.erasePosition();
	}
	
	protected void updateEntities(char d) {

		for(Ogre o : ogres)
			o.move();
		
		hero.move(d);
	}
	
	protected void updateDoors() {
		
		if(hero.getKey() == key_t.NULL || hero.getKey() == key_t.UNLOCKED)
			return;
		
		if(hero.getKey() == key_t.UNLOCKING) {
			map[1][0] = 'S';
			
			hero.updateKey(key_t.UNLOCKED);
		}
	}
	
	protected void drawEntities() {
		
		for(Ogre o : ogres)
			o.drawPosition();

		hero.drawPosition();
	}
	
	protected void updateLevelStatus() {
		
		if(hero.getY() == 1 && hero.getX() == 0) {
			levelStatus = status_t.WON;
			return;
		}
		
		for(Ogre o : ogres) {
			
			if(o.isStunned())
				continue;

			if ((hero.getX() == o.getX() && hero.getY() == o.getY()) || (hero.getX() == o.getWeapon().getX() && hero.getY() == o.getWeapon().getY())) {
				levelStatus = status_t.LOST;
				return;
			}

			int[][] adjacent = new int[][] { { o.getY() + 1, o.getX() }, { o.getY() - 1, o.getX() },
					{ o.getY(), o.getX() + 1 }, { o.getY(), o.getX() - 1 } };

			for (int[] spot : adjacent) {
				
				if (map[spot[0]][spot[1]] == hero.getIcon()) {
					o.setStunned(true);
					continue;
				}
			}

			int cx = o.getWeapon().getX(), cy = o.getWeapon().getY();

			int[][] adjacent_club;

			switch (o.getWeapon().getLastDirection()) {
			case 'w':
				adjacent_club = new int[][] { { cy, cx - 1 }, 	// esquerda
						{ cy, cx + 1 }, 						// direita
						{ cy - 1, cx },							// cima
				};
				break;
			case 'a':
				adjacent_club = new int[][] { { cy, cx - 1 }, 	// esquerda
						{ cy - 1, cx }, 						// cima
						{ cy + 1, cx },							// baixo
				};
				break;
			case 's':
				adjacent_club = new int[][] { { cy, cx - 1 }, 	// esquerda
						{ cy, cx + 1 }, 						// direita
						{ cy + 1, cx }, 						// baixo
				};
				break;
			case 'd':
				adjacent_club = new int[][] { { cy, cx + 1 }, 	// direita
						{ cy - 1, cx }, 						// cima
						{ cy + 1, cx }, 						// baixo
				};
				break;
			default:
				adjacent_club = new int[0][0];
			}

			for (int[] spot : adjacent_club) {
				if (map[spot[0]][spot[1]] == hero.getIcon()) {
					levelStatus = status_t.LOST;
					return;
				}
			}
		}
	}
	
	protected void loadEnemies() {
		
		ogres = new ArrayList<Ogre>();
		
		int nrOgres = Game.nrOgres;
		
		while(nrOgres > 0) {
			ogres.add(new Ogre(4, 1, this));
			nrOgres--;
		}
	}
}
