import layout.*;
import java.util.Scanner;

public class Game {
	
	public static void main(String args[]) {
		
		Map link_start = new Map();
		link_start.displayMap();
		
		Scanner S = new Scanner(System.in);
		
		int count = 100;
		
		
		while(count >= 1)
		{
			//Checks for user input
			boolean cont;
			char c;
			
			do {
				cont = false;
				String scan = S.next();
				c = scan.charAt(0);
				
				if(scan.length() > 1 || !Character.isLetter(c))
					cont = true;
				
			} while (cont);
			
			link_start.moveHero(c);
			
			if(link_start.displayMap() == 1)
				break;
			
			count--;
		}
		
		S.close();
		
	}
	
}
