package dkeep.logic.entities;

import dkeep.logic.objects.DKObject;
import dkeep.logic.objects.Door;

/** Hero from a level. */
public class Hero extends Entity {
	
	/** Key's possible states. */
	public enum key_t { 
		/** Used/no key */ NULL,
		/** Got key. */ KEY, 
		/** Touched lever. */ LEVER, 
		};
	
	/** Hero's state. */
	private key_t _key;
	
	/** Creates an instance of Hero.
	 * @param x X-position of the hero.
	 * @param y Y-position of the hero.
	 * @param icon Icon of the hero. */
	public Hero(int x, int y, char icon) {
		super(x, y, icon);
		_key = key_t.NULL;
	}
	
	/** @return Key's state. */
	public key_t getKey() {
		return _key;
	}
	
	/** Updates key's value.
	 * @param key New key value. */
	public void updateKey(key_t key) {
		_key = key;
	}
	
	/** Moves the hero.
	 * @param direction Direction to move. */
	public void move(char direction) {
		generatePosition(direction, getX(), getY(), false);
	}
		
	public void drawEntity() {
		DKObject.level.getMap()[getY()][getX()] = getIcon();
	}
	
	public void eraseEntity() {
		
		for(Door door : DKObject.level.getDoors()) {
			
			if(door.equalPosition(getCoords())) {
				DKObject.level.getMap()[getY()][getX()] = door.getIcon();
				return;
			}
		}
		
		DKObject.level.getMap()[getY()][getX()] = ' ';
	}
	
	public boolean checkCollision(int x, int y) {
		
		if(!checkWalls(x, y) || !_checkDoors(x, y) || !_checkEnemies(x, y))
			return false;
		
		_checkKey(x, y);
		
		return true;
	}
	
	/** Checks if the hero can move to a door on the map.
	 * @param x X-position to be checked.
	 * @param y Y-position to be checked.
	 * @return True if he can, false otherwise. */
	private boolean _checkDoors(int x, int y) {
		
		for(Door door : DKObject.level.getDoors()) {
			
			if(door.equalPosition(x, y)) {
				
				if(!door.isOpen()) {
					
					if(door.isExit() && _key == key_t.KEY)
						door.unlockDoor();
					
					return false;
				}
			}
		}
		
		return true;		
	}
	
	/** Checks if there's a key in a certain position of the map.
	 * @param x X-position to be checked.
	 * @param y Y-position to be checked. */
	private void _checkKey(int x, int y) {
		
		if (DKObject.level.getKey() != null && DKObject.level.getKey().equalPosition(x, y)) {

			switch (DKObject.level.getKey().getIcon()) {
			case 'k':
				_key = key_t.KEY;
				updateIcon('K');
				break;
			case 'l':
				_key = key_t.LEVER;
				break;
			}
			
			DKObject.level.setKey(null);
		}
	}
	
	/** Checks if there's an enemy in a certain position of the map.
	 * @param x X-position to be checked.
	 * @param y Y-position to be checked. */
	private boolean _checkEnemies(int x, int y) {
		
		//Check for enemies
		for(Entity enemy : DKObject.level.getEnemies()) {
			
			if(enemy.equalPosition(x,  y))
				return false;			
		}
		
		return true;
	}
}
