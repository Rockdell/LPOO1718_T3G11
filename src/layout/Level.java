package layout;

import characters.*;

public class Level {
	
	private char[][] map;
	private int mapID;
	private Hero hero;
	private Entity enemy;
	
	public Level() {	
		loadMap(Maps.map01);
	}
	
	public int getID() {
		return mapID;
	}
	
	public char[][] getMap() {
		return map;
	}
	
	public void loadMap(char[][] m) {
		
		map = m;
		
		if(m == Maps.map01) {
			mapID = 1;
			hero = new Hero(1,1);
			enemy = new Guard(8,1);
		}
		else if(m == Maps.map02) {
			mapID = 2;
			hero = new Hero(1,1);
			enemy = new Ogre(4,1);
		}
	}
	
	public void moveHero(char direction) {

		hero.move(direction, map);
		
		if(enemy instanceof Guard) {
			Guard g = (Guard) enemy;
			g.move(map);
		}
		else if(enemy instanceof Ogre) {
			Ogre o = (Ogre) enemy;
			o.move(map);
		}		
		
		if(hero.getKey() != 0 && hero.getKey() != -1)
			openDoors(hero.getKey());
	}
	
	public void openDoors(int key) {

		if(mapID == 1 && key == 1) {
			map[3][2] = 'S';
			map[3][4] = 'S';
			map[5][0] = 'S';
			map[6][0] = 'S';
			map[8][2] = 'S';
			map[8][4] = 'S';
			
			hero.setKey(-1);
	
		}
		else if(mapID == 2 && key == 2) {
			map[1][0] = 'S';
			
			hero.setKey(-1);
		}
	}
	
	private int checkPosition() {
		
		//Return values:
		//0 -> Nothing
		//1 -> Captured;
		//2 -> End level;
		
		if(hero.getX() == enemy.getX() && hero.getY() == enemy.getY())
			return 1;
		
		int[][] adjacent = new int[][] {
					{ enemy.getY() + 1, enemy.getX()},
					{ enemy.getY() - 1, enemy.getX()},
					{ enemy.getY(), enemy.getX() + 1},
					{ enemy.getY(), enemy.getX() - 1}
					};
		
		for(int[] spot : adjacent) {
			if(map[spot[0]][spot[1]] == hero.getIcon())
				return 1;
		}
		
		//Check club
		if(enemy instanceof Ogre) {
			
			Ogre o = (Ogre) enemy;
			char[] club_settings = o.getClub();
			int cx = (int) club_settings[0], cy = (int) club_settings[1];
			char ld = club_settings[2];
			
			int[][] adjw;
	
			switch(ld) {
			case 'w':
				adjw = new int[][] {
					{cy,cx - 1}, //esquerda
					{cy,cx + 1}, //direita
					{cy + 1,cx}, //cima
				};
				
	
				break;
			case 'a':
				adjw = new int[][] {
					{cy,cx - 1}, //esquerda
					{cy,cx + 1}, //direita
					{cy + 1,cx}, //cima
				};
				break;
			case 's':
				adjw = new int[][] {
					{cy,cx - 1}, //esquerda
					{cy,cx + 1}, //direita
					{cy + 1,cx}, //cima
				};
				break;
			case 'd':
				adjw = new int[][] {
					{cy,cx - 1}, //esquerda
					{cy,cx + 1}, //direita
					{cy + 1,cx}, //cima
				};
				break;
			default:
				adjw = new int[0][0];
			}	
			
			for(int[] spot : adjw) {
				if(map[spot[0]][spot[1]] == hero.getIcon())
					return 1;
			}
		}		
		
		switch(mapID) {
		case 1:
			if((hero.getY() == 5 || hero.getY() == 6) && hero.getX() == 0)
				return 2;
		case 2:
			if(hero.getY() == 1 && hero.getX() == 0)
				return 2;
		}
		
		return 0;
	}	
	
	public int display() {
		
		//Clear console
		for(int i = 0; i < 10; i++)
			System.out.print("\n");
		
		//Display map
		for(int i = 0; i < map.length; i++) {
			
			for(int j = 0; j < map[i].length; j++) {
				
				System.out.print(map[i][j] + " ");
			}
			
			System.out.println();
		}	
		
		switch(checkPosition())	{
		case 0:
			//Nothing happens
			break;
		case 1:
			//Captured
			return 1;
		case 2:
			//Load map or end game
			if(mapID == 1) {
				loadMap(Maps.map02);
				return 2;
			}
			else if(mapID == 2)
				return 3;
		}
		
		return 0;
	}		
}
