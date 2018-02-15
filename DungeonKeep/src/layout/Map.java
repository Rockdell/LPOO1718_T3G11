package layout;

public class Map {
	
	private char[][] map;
	
	public Map() {
		loadMap(1);
	}
	
	public void loadMap(int option) {
		
		switch(option) {
		case 1:
			map = Levels.map01;
			break;
		case 2:
			map = Levels.map02;
			break;
		}
	}
	
	public void displayMap() {
		
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
