package layout;
import characters.*;

public class Map {
	
	private char[][] map;
	int mapN;
	Hero hero;
	Entity enemy;
	
	public Map() {	
		loadMap(Levels.map01);
	}
	
	public void loadMap(char[][] m) {
		
		map = m;
		
		if(m == Levels.map01) {
			mapN = 1;
			hero = new Hero(1,1);
			enemy = new Guard(8,1);
		}
		else if(m == Levels.map02) {
			mapN = 2;
			hero = new Hero(1,1);
			enemy = new Ogre(4,1);
		}
	}
	
	private void loadAssets() {
		
		//Load custom doors, etc
		switch(mapN) {
		case 1:
			break;
		case 2:
			break;
		}
		
		//Load hero
		map[hero.getY()][hero.getX()] = 'H';
		map[enemy.getY()][enemy.getX()] = 'G';
	}
	
	public void displayMap() {
		
		//Load characters and door
		loadAssets();
		
		//Display map
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				System.out.print(map[i][j] + " ");
			}
			
			System.out.println();
		}	
	}	
}
