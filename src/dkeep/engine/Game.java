package dkeep.engine;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import dkeep.io.IO;
import dkeep.logic.layout.Level;
import dkeep.logic.layout.Level.status_t;

public class Game {
	
	/** Loaded level. */
	private Level _level;
	
	/** Input scanner. */
	static public IO io;
	
	public Game(IO io) {
		Game.io = io;
	}

	/** @return Loaded level. */
	public Level getCurrentLevel() {
		return _level;
	}
	
	/** Serialize object Level for later use. */
	public void save() {
		
		try {
			FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + "/src/miscellaneous/savefile.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(_level);
			out.close();
			fileOut.close();
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Deserialize object Level for later use. */
	public void load() {
	
		try {
			FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + "/src/miscellaneous/savefile.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			_level = (Level) in.readObject();
			in.close();
			fileIn.close();
		} 
		catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/** Loads a level into the game.
	 * @param id Level to load. */
	public void loadLevel(int id, String gP, int nO) {

		_level = new Level(id, gP, nO);

		_level.display();
	}
	
	public void loadLevel(int id) {
		
		_level = new Level(id);
		
		_level.display();
		
	}
	
	public List<String> existentMaps() {

		List<String> mapsID = new ArrayList<String>();

		// Tries reading the file
		try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/miscellaneous/maps.txt"))) {

			// Searches for the correct map in maps.txt
			for (String mapSearch; (mapSearch = br.readLine()) != null;) {

				if (mapSearch.length() <= 3)
					continue;

				if (mapSearch.contains("Map"))
				{
					int tmp = new Integer(mapSearch.substring(3, mapSearch.length()));
					
					if(tmp < 90)
						mapsID.add(mapSearch.substring(3, mapSearch.length()));
				}
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return mapsID;
	}
	
	/** Ticks the game.
	 * 	@return True if won/lost, false if ongoing. */
	public boolean tick() {
		
		if(_level.getStatus() != status_t.ONGOING)
			return true;
		
		//Read input
		char input = io.read();
		
		if(input == 'n')
			return false;
		
		//Move entities
		_level.updateLevel(input);
		
		//Display the current level
		_level.display();
		
		// Check level's status
		switch (_level.getStatus()) {
		case ONGOING:
			break;
		case PROCEED:
			if (_level.getID() < 2)
				loadLevel(_level.getID() + 1, null, Level.nrOgres);
			else if(_level.getID() < existentMaps().size())
				loadLevel(_level.getID() + 1);
			else {
				_level.setStatus(status_t.WON);
				return true;
			}
			break;
		case KILLED:
		case WON:
			return true;
		}
		
		return false;
	}

}
