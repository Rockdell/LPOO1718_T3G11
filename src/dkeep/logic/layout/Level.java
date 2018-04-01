package dkeep.logic.layout;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dkeep.logic.entities.*;
import dkeep.logic.entities.Hero.hero_t;
import dkeep.logic.objects.*;
import dkeep.logic.objects.Door.door_t;
import dkeep.logic.objects.Key.key_t;
import dkeep.ui.cli.Game;

public class Level {
	
	/** Level's possible status. */
	public enum status_t { 
		/** Level is ongoing. */ 	ONGOING,
		/** Cleared the area. */ 	PROCEED,
		/** Killed by an enemy. */ 	KILLED,
		/** Won the game. */ 		WON
		};
		
	/** Map's ID. */
	private int 			_id;
	
	/** Level's map. */
	private char[][] 		_map;
	
	/** Level's hero. */
	private Hero 			_hero;
	
	/** Level's enemies. */
	private List<Entity> 	_enemies = new ArrayList<Entity>();
	
	/** Level's doors. */
	private List<Door> 		_doors = new ArrayList<Door>();
	
	/** Level's key. */
	private Key 			_key;
	
	/** Level's status. */
	private status_t 		_status;
	
	//END_ATRIBUTES
	
	/** Creates an object Level. */
	public Level(int id) throws IOException {
		_status = status_t.ONGOING;
		_loadMap(id);
		_loadEntities();
	}
	
	//END_CONSTRUCTOR

	/** @return Level's map. */
	public char[][] getMap() {
		return _map;
	}
	
	/** @return Map's ID. */
	public int getID() {
		return _id;
	}
	
	/** @return Level's hero. */
	public Hero getHero() {
		return _hero;
	}
	
	/** @return Level's hero. */
	public List<Entity> getEnemies() {
		return _enemies;
	}
	
	/** @return Level's hero. */
	public List<Door> getDoors() {
		return _doors;
	}
	
	/** @return Level's key. */
	public Key getKey() {
		return _key;
	}
	
	/** @return Level's status. */
	public status_t getStatus() {
		return _status;
	}
	
	/** Updates level's status. 
	 * @param s New status. */
	public void setStatus(status_t s) {
		_status = s;
	}
	
	public void setKey(Key key) {
		_key = key;
	}
	
	//END_GETTERS_SETTERS
	
	/** Loads Level's map accordingly.
	 * @param mapID ID of the wanted map.
	 * @return Returns a char[][] with the map. */
	private void _loadMap(int mapID) throws IOException, FileNotFoundException {

		char[][] test;
		boolean found = false;

		// Tries reading the file
		try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/miscellaneous/maps.txt"))) {

			// Searches for the correct map in maps.txt
			for (String mapSearch; (mapSearch = br.readLine()) != null;) {
				if (mapSearch.equals("Map" + mapID)) {
					found = true;
					break;
				}
			}

			// Checks if the map was found
			if (!found)
				return;

			// If it was found starts retrieving it
			int i = 0;
			String firstLine = br.readLine();

			// Checks how many lines and columns the map is composed of
			String[] parts = firstLine.split("-");
			String lines = parts[0];
			String columns = parts[1];

			int l = Integer.parseInt(lines);
			int c = Integer.parseInt(columns);

			test = new char[l][c];

			// Creates an array representing the map
			for (String line; (line = br.readLine()) != null; i++) {

				if (line.equals(""))
					break;

				char[] tmp = line.toCharArray();
				test[i] = tmp;
			}

			_id = mapID;
			_map = test;

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void _loadEntities() {
		
		for(int y = 0; y < _map.length; y++) {
			
			for(int x = 0; x < _map[y].length; x++) {
				
				switch(_map[y][x]) {
				case 'H':
					_hero = new Hero(x, y, this, 'H');
					break;
				case 'A':
					_hero = new Hero(x, y, this, 'A');
					break;
				case 'E':
					_doors.add(new Door(x, y, this, 'E', door_t.EXIT));
					break;
				case 'e':
					_doors.add(new Door(x, y, this, 'e', door_t.EXIT));
					break;
				case 'I':
					_doors.add(new Door(x, y, this, 'I', door_t.REGULAR));
					break;
				case 'S':
					_doors.add(new Door(x, y, this, 'S', door_t.REGULAR));
					break;
				case 'G':
					_loadGuards(x, y);
					break;
				case 'O':
					_loadOgres(x, y);
					break;
				case 'k':
					_key = new Key(x, y, this, 'k', key_t.KEY);
					break;
				case 'l':
					_key = new Key(x, y, this, 'l', key_t.LEVER);
				}
			}
		}
	}
	
	private void _loadGuards(int x, int y) {
		
		switch(Game.guardPersonality) {
		case "Rookie":
			_enemies.add(new Rookie(x, y, this));
			break;
		case "Drunken":
			_enemies.add(new Drunken(x, y, this));
			break;
		case "Suspicious":
			_enemies.add(new Suspicious(x, y, this));
			break;		
		}
	}
	
	private void _loadOgres(int x, int y) {
		
		for(int i = 0; i < Game.nrOgres; i++)
			_enemies.add(new Ogre(x, y, this));		
	}
	
	//END_LOAD_FUNCTONS
	
	/** Updates the level and its entities.
	 * @param d Direction for the hero. */
	public void updateLevel(char d) {
		
		//Clears current level
		_clearEntities();
		
		//Update level's entities
		_updateEntities(d);
		
		//Update level's doors
		_updateDoors();
		
		//Draws current level
		_drawEntities();
		
		_updateLevelStatus();
	}
	
	/** Clears the all entities from the level. */
	private void _clearEntities() {
		
		for(Entity enemy : _enemies)
			enemy.eraseEntity();
		
		_hero.eraseEntity();
	}
	
	/** Updates the entities from the level.
	 * @param d Direction for hero. */
	protected void _updateEntities(char d) {
		
		for(Entity enemy : _enemies) {
			
			if(enemy instanceof Guard)
				((Guard) enemy).patrol();
			else if(enemy instanceof Ogre)
				((Ogre) enemy).move();
		}
		
		_hero.move(d);
	}
	
	/** Updates the doors from the level. */
	protected void _updateDoors() {
		
		if(_hero.getKey() == hero_t.NULL)
			return;
		else if(_hero.getKey() == hero_t.LEVER) {
			
			//Open exit doors
			for(Door door : _doors) {
				if(door.getType() == door_t.EXIT)
					door.unlockDoor();
			}
			
			_hero.updateKey(hero_t.NULL);
		}		
	}
	
	/** Draws the entities from the level. */
	protected void _drawEntities() {
		
		for(Entity enemy : _enemies) {
			enemy.drawEntity();
		}
		
		_hero.drawEntity();
	}
	
	/** Updates level status. */
	protected void _updateLevelStatus() {
		
		//Hero found the exit
		for(Door door : _doors) {
			
			if(door.getType() == door_t.EXIT && door.getCoords().equals(_hero.getCoords())) {
				_status = status_t.PROCEED;
				return;
			}			
		}
		
		for(Entity enemy : _enemies) {
			
			if(enemy instanceof Guard) {
				
				Guard g = ((Guard) enemy);
				
				if(g.isHarmless())
					continue;
				else if(g.checkHit(_hero.getX(), _hero.getY())) {
					_status = status_t.KILLED;
					return;
				}
			}
			else if(enemy instanceof Ogre) {
				
				Ogre o = ((Ogre) enemy);
				
				if(o.isStunned())
					continue;
				else if(o.checkHit(_hero.getX(), _hero.getY())) {
					_status = status_t.KILLED;
					return;
				}
			}
			
			if(enemy.getCoords().equals(_hero.getCoords())) {
				_status = status_t.KILLED;
				return;
			}
		}	
	}
	
	//END_LEVEL_RELATED_FUNCTIONS
	
	/** Display the level. */
	public void display() {
		
		Game.io.clearConsole();
		
		Game.io.write(_map);
	}
	
	/** @return Summary of the game. */
	public String endgameSummary() {
		
		switch(_status) {
		case ONGOING:
			return "Moving ";
		case PROCEED:
			return "You cleared the area!";
		case KILLED:
			return "You got killed by the enemy!";
		case WON:
			return "You won!";
		default:
			return "";
		}
	}
}
