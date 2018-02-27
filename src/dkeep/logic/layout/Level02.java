package dkeep.logic.layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dkeep.logic.characters.Hero;
import dkeep.logic.characters.Ogre;

public class Level02 extends Level {
	
	private List<Ogre> ogres;
	
	public Level02() {
		map = Maps.map02;
		mapID = 2;
		hero = new Hero(1, 1, this);
		//ogre = new Ogre(4, 1, this);
		generateOgres();
	}
	
	public void updateLevel(char direction) {
		
		hero.move(direction);
		
		for(Ogre o : ogres) {
			o.move();
		}
		
		if(hero.getKey() != 0 && hero.getKey() != -1)
			openDoors(hero.getKey());
	}
	
	private void openDoors(int key) {

		if(key == 2) {
			map[1][0] = 'S';
			hero.updateKey(-1);
		}
	}
	
	protected boolean checkEnemy() {
		
		for(Ogre o : ogres) {

			if ((hero.getX() == o.getX() && hero.getY() == o.getY())
					|| (hero.getX() == o.getWeapon().getX() && hero.getY() == o.getWeapon().getY()))
				return true;

			int[][] adjacent = new int[][] { { o.getY() + 1, o.getX() }, { o.getY() - 1, o.getX() },
					{ o.getY(), o.getX() + 1 }, { o.getY(), o.getX() - 1 } };

			for (int[] spot : adjacent) {
				if (map[spot[0]][spot[1]] == hero.getIcon())
					return true;
			}

			int cx = o.getWeapon().getX(), cy = o.getWeapon().getY();

			int[][] adjacent_club;

			switch (o.getWeapon().getLastDirection()) {
			case 'w':
				adjacent_club = new int[][] { { cy, cx - 1 }, // esquerda
						{ cy, cx + 1 }, // direita
						{ cy - 1, cx }, // cima
				};
				break;
			case 'a':
				adjacent_club = new int[][] { { cy, cx - 1 }, // esquerda
						{ cy - 1, cx }, // cima
						{ cy + 1, cx }, // baixo
				};
				break;
			case 's':
				adjacent_club = new int[][] { { cy, cx - 1 }, // esquerda
						{ cy, cx + 1 }, // direita
						{ cy + 1, cx }, // baixo
				};
				break;
			case 'd':
				adjacent_club = new int[][] { { cy, cx + 1 }, // direita
						{ cy - 1, cx }, // cima
						{ cy + 1, cx }, // baixo
				};
				break;
			default:
				adjacent_club = new int[0][0];
			}

			for (int[] spot : adjacent_club) {
				if (map[spot[0]][spot[1]] == hero.getIcon())
					return true;
			}
		
		}
		
		return false;
	}
	
	protected boolean checkEnd() {
		if(hero.getY() == 1 && hero.getX() == 0)
			return true;
		else
			return false;
	}
	
	private void generateOgres() {
		
		ogres = new ArrayList<Ogre>();
		
		Random random = new Random();
		
		int nrOgres = random.nextInt(3) + 1;
		
		while(nrOgres > 0) {
			ogres.add(new Ogre(4, 1, this));
			nrOgres--;
		}
	}
}
