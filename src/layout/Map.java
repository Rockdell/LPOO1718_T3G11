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
	
	public char[][] getMap() {
		return map;
	}
	
	public void moveHero(char direction)
	{
		//Save position if needed
		int last_x = hero.getX();
		int last_y = hero.getY();
		
		//Update position
		switch(direction)
		{
		case 'w':
			hero.setY(hero.getY() - 1);
			break;
		case 'a':
			hero.setX(hero.getX() - 1);
			break;
		case 's':
			hero.setY(hero.getY() + 1);
			break;
		case 'd':
			hero.setX(hero.getX() + 1);
			break;
		default:
			return;
		}
		
		//Confirm position
		if(getMap()[hero.getY()][hero.getX()] != ' '
				&& getMap()[hero.getY()][hero.getX()] != 'k'
				&& getMap()[hero.getY()][hero.getX()] != 'S')
		{
			hero.setX(last_x);
			hero.setY(last_y);
		}
		else
			map[last_y][last_x] = ' ';
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
			//Open the exit doors -> Checks if the Hero is standing on the lever
			if(hero.getY() == 8 && hero.getX() == 7)
			{
				map[5][0] = 'S';
				map[6][0] = 'S';
			}
			break;
		case 2:
			break;
		}
		
		//Load lever / hero / enemy
		map[8][7] = 'k';
		map[hero.getY()][hero.getX()] = 'H';
		map[enemy.getY()][enemy.getX()] = 'G';
	}
	
	private int checkPosition() {
		
		//Return values:
		//0 -> Nothing happens;
		//1 -> Captured
		//2 -> End level / Win;
		
		int[][] adjacent = {{enemy.getY() + 1,enemy.getX()},
				{enemy.getY() - 1,enemy.getX()},
				{enemy.getY(),enemy.getX() + 1},
				{enemy.getY(),enemy.getX() - 1}};
		
		for(int[] spot:adjacent)
		{
			if(map[spot[0]][spot[1]] == 'H')
				return 1;
		}
		
		if((hero.getY() == 5 || hero.getY() == 6) && hero.getX() == 0)
			return 2;
		
		return 0;
	}	
	
	public int displayMap() {
		
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
		
		switch(checkPosition())
		{
		case 0:
			//Nothing happens
			break;
		case 1:
			//Captured
			System.out.print("You have benn captured!\nGAME OVER");
			return 1;
		case 2:
			//Next Level / Win
			//TODO Add next level/map
			System.out.print("YOU'VE WON!");
			return 1;
		default:
			break;
		}
		return 0;
	}		
}
