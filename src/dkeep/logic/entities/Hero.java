package dkeep.logic.entities;

import dkeep.logic.objects.DKObject;
import dkeep.logic.objects.Door;
import dkeep.logic.objects.Door.door_t;

public class Hero extends Entity {
	
	/** Key's possible states. */
	public enum hero_t { 
		/** Used/no key */ NULL,
		/** Got key. */ KEY, 
		/** Touched lever. */ LEVER, 
		};
	
	/** Hero's state. */
	private hero_t _key;
	
	/** Creates an object Hero.
	 * @param x X-position of the hero.
	 * @param y Y-position of the hero.
	 * @param l Current level. */
	public Hero(int x, int y, char icon) {
		super(x, y, icon);
		_key = hero_t.NULL;
	}
	
	/** @return Key's state. */
	public hero_t getKey() {
		return _key;
	}
	
	/** Updates key's value.
	 * @param k New key value. */
	public void updateKey(hero_t key) {
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
			
			if(door.getCoords().equals(getCoords())) {
				DKObject.level.getMap()[getY()][getX()] = door.getIcon();
				return;
			}
		}
		
		DKObject.level.getMap()[getY()][getX()] = ' ';
	}
	
	public boolean checkCollision(int x, int y) {
		
		//Check for wall
		if(DKObject.level.getMap()[y][x] == 'X')
			return false;
		
		//Check for key
		if(DKObject.level.getKey() != null) {
			if (DKObject.level.getKey().getX() == x && DKObject.level.getKey().getY() == y) {

				switch (DKObject.level.getKey().getType()) {
				case KEY:
					_key = hero_t.KEY;
					updateIcon('K');
					break;
				case LEVER:
					_key = hero_t.LEVER;
					break;
				}
			}
		}
		
		//Check for enemies
		for(Entity enemy : DKObject.level.getEnemies()) {
			
			if(enemy.getX() == x && enemy.getY() == y)
				return false;			
		}
		
		//Check for locked end doors
		for(Door door : DKObject.level.getDoors()) {
			
			if(door.getX() == x && door.getY() == y) {
				
				if(door.getIcon() == 'I') {
					
					if(door.getType() == door_t.EXIT && _key == hero_t.KEY) {
						door.unlockDoor();
						_key = hero_t.NULL;
						DKObject.level.setKey(null);
					}
					
					return false;
				}
			}
		}
		
//		else if(getLevel().getMap()[y][x] == 'X' || getLevel().getMap()[y][x] == 'g' || getLevel().getMap()[y][x] == '8')
//			return false;
		
		return true;
	}
}
