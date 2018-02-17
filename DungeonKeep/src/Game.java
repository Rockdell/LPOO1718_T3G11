import layout.*;
import java.util.Scanner;

public class Game {
	
	public static void main(String args[]) {
		
		Map link_start = new Map();
		link_start.displayMap();
		
		Scanner S = new Scanner(System.in);
		
		int count = 10;
		
		while(count >= 1)
		{
			char c = S.next(".").charAt(0);
			
			link_start.moveHero(c);
			
			link_start.displayMap();	
			
			count--;
		}
		
		S.close();
		
	}
	
}
