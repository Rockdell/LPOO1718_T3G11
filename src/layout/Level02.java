package layout;

import characters.*;

public class Level02 extends Level {
	
	private Ogre ogre;
	
	public Level02() {
		map = Maps.map02;
		mapID = 2;
		hero = new Hero(1, 1, this);
		ogre = new Ogre(4, 1, this); 
	}
	
	public void updateLevel(char direction) {
		
		hero.move(direction);
		ogre.move();
		
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
		
		if((hero.getX() == ogre.getX() && hero.getY() == ogre.getY()) || (hero.getX() == ogre.getWeapon().getX() && hero.getY() == ogre.getWeapon().getY()))
			return true;
		
		int[][] adjacent = new int[][] {
					{ ogre.getY() + 1, ogre.getX()},
					{ ogre.getY() - 1, ogre.getX()},
					{ ogre.getY(), ogre.getX() + 1},
					{ ogre.getY(), ogre.getX() - 1}
					};
		
		for(int[] spot : adjacent) {
			if(map[spot[0]][spot[1]] == hero.getIcon())
				return true;
		}
		
		int cx = ogre.getWeapon().getX(), cy = ogre.getWeapon().getY();
		
		int[][] adjacent_club;

		switch (ogre.getWeapon().getLastDirection()) {
		case 'w':
			adjacent_club = new int[][] {
				{ cy, cx - 1 }, // esquerda
				{ cy, cx + 1 }, // direita
				{ cy - 1, cx }, // cima
			};
			break;
		case 'a':
			adjacent_club = new int[][] { 
				{ cy, cx - 1 }, // esquerda
				{ cy - 1, cx }, // cima
				{ cy + 1, cx }, // baixo
			};
			break;
		case 's':
			adjacent_club = new int[][] {
				{ cy, cx - 1 }, // esquerda
				{ cy, cx + 1 }, // direita
				{ cy + 1, cx }, // baixo
			};
			break;
		case 'd':
			adjacent_club = new int[][] { 
				{ cy, cx + 1 }, // direita
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
		
		return false;
	}
	
	protected boolean checkEnd() {
		if(hero.getY() == 1 && hero.getX() == 0)
			return true;
		else
			return false;
	}
}
