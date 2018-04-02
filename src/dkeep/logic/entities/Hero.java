package dkeep.logic.entities;

import dkeep.logic.objects.DKObject;
import dkeep.logic.objects.Door;

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
			
			if(door.equalPosition(getCoords())) {
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
		if (DKObject.level.getKey() != null && DKObject.level.getKey().equalPosition(x, y)) {

			switch (DKObject.level.getKey().getIcon()) {
			case 'k':
				_key = hero_t.KEY;
				updateIcon('K');
				break;
			case 'l':
				_key = hero_t.LEVER;
				break;
			}

			return true;
		}
		
		//Check for enemies
		for(Entity enemy : DKObject.level.getEnemies()) {
			
			if(enemy.equalPosition(x,  y))
				return false;			
		}
		
		//Check for locked end doors
		for(Door door : DKObject.level.getDoors()) {
			
			if(door.equalPosition(x, y)) {
				
				if(!door.isOpen()) {
					
					if(door.isExit() && _key == hero_t.KEY) {
						door.unlockDoor();
						_key = hero_t.NULL;
						DKObject.level.setKey(null);
					}
					
					return false;
				}
			}
		}
		
		return true;
	}
}
