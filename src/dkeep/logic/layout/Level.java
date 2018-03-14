package dkeep.logic.layout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dkeep.logic.characters.Hero;

public abstract class Level implements ILevelLogic {
	
	public enum status_t { ONGOING, WON, LOST};
	
	protected char[][] map;
	protected int mapID;
	protected Hero hero;
	protected status_t levelStatus;

	public char[][] loadMap() throws IOException, FileNotFoundException {

		char[][] test;

		try (BufferedReader br = new BufferedReader(new FileReader("maps.txt"))) {

			int i = 0;

			String firstLine = br.readLine();

			String[] parts = firstLine.split("-");
			String lines = parts[0];
			String columns = parts[1];

			int l = Integer.parseInt(lines);
			int c = Integer.parseInt(columns);

			test = new char[l][c];

			for (String line; (line = br.readLine()) != null;) {

				char[] tmp = line.toCharArray();
				test[i] = tmp;

				i++;
			}
		}

//		for (int i = 0; i < test.length; i++) {
//
//			for (int j = 0; j < test[i].length; j++) {
//
//				System.out.print(test[i][j] + " ");
//			}
//
//			System.out.println();
//		}

		return test;
	}
	
	public Level() {
		levelStatus = status_t.ONGOING;
	}

	public char[][] getMap() {
		return map;
	}
	
	public int getID() {
		return mapID;
	}
	
	public Hero getHero() {
		return hero;
	}
	
	public status_t getLevelStatus() {
		return levelStatus;
	}
	
	public void updateLevel(char d) {
		
		//Clears current level
		clearEntities();
		
		//Update level's entities
		updateEntities(d);
		
		updateDoors();
		
		//Draws current level
		drawEntities();
		
		updateLevelStatus();
	}

	//Display level
	public void display() {
		
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
	}	
}
