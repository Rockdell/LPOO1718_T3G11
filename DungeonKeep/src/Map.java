import java.util.Scanner;

public class Map {
	
	private char[][] map = {{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ','I',' ','X',' ','G','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X','k',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'},
	};
	
	private int[] hero = {1,1};

	public static void main(String[] args) {
		
		Map test = new Map();
		
		test.display_map();

	}
	
	public Map() {
		
	}
	
	public void display_map()
	{
		//Display map
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				System.out.print(map[i][j] + " ");
			}
			
			System.out.println();
		}
		
		//Display Hero
		
	}
	
	
	
}
