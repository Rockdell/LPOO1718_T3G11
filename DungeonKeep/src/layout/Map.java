package layout;

public class Map {
	
	private char[][] map;
	
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
