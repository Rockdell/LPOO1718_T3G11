package dkeep.logic.entities;

import dkeep.logic.objects.DKObject;
import dkeep.logic.objects.Door;

/** Ogre from a level. */
public class Ogre extends Entity {
	
	/** Ogre's weapon. */
	private Club 	_weapon;
	
	/** True if stunned, false otherwise. */
	private boolean _stunned;
	
	/** Number of rounds the ogre is going to be stunned. */
	private int 	_roundsStunned;
	
	/** Creates an instance of Ogre.
	 * @param x X-position of the ogre.
	 * @param y Y-position of the ogre. */
	public Ogre(int x, int y) {
		super(x, y, 'O');
		_weapon = new Club(this);
		_stunned = false;
		_roundsStunned = 0;
	}
	
	/** @return Ogre's weapon. */
	 public Club getWeapon() {
		 return _weapon;
	 }
	 
	 /** @return True if ogre is stunned, false otherwise. */
	 public boolean isStunned() {
		 return _stunned;
	 }
	 
	 /** Sets the ogre as stunned/not-stunned.
	  * @param s True to make the ogre stunned, false otherwise. */
	 public void setStunned(boolean s) {
		 _stunned = s;

		if (s) {
			updateIcon('8');
			_roundsStunned = 2;
		}
		else
			updateIcon('O');
	 }
	
	 /** Move the ogre. */
	public void move() {

		if (!_stunned) {
			generatePosition(' ', getX(), getY(), true);
			_weapon.attack();
		}
	}
	
	public void drawEntity() {
		DKObject.level.getMap()[getY()][getX()] = getIcon();

		if (!_stunned)
			_weapon.drawEntity();
		else {

			if (_roundsStunned-- == 0)
				setStunned(false);
		}
	}
	
	public void eraseEntity() {
			
		//Ogre esta na posicao da chave (independente da char atual no map)
		if(getIcon() == '$')
			DKObject.level.getMap()[getY()][getX()] = DKObject.level.getKey().getIcon();
		else
			DKObject.level.getMap()[getY()][getX()] = ' ';
		
		_weapon.eraseEntity();
	}
	
	public boolean checkCollision(int x, int y) {

		if (DKObject.level.getMap()[y][x] == 'X')
			return false;
		
		if(!checkWalls(x, y) || !_checkDoors(x, y))
			return false;
		
		_checkKey(x, y);

		return true;
	}
	
	/** Checks if there's a door in a certain position of the map.
	 * @param x X-position to be checked.
	 * @param y Y-position to be checked.
	 * @return True if there's no door, false otherwise. */
	private boolean _checkDoors(int x, int y) {
		
		for(Door door : DKObject.level.getDoors()) {
			
			if(door.equalPosition(x, y))
				return false;
		}
		
		return true;
	}
	
	/** Checks if there's a key in a certain position of the map.
	 * @param x X-position to be checked.
	 * @param y Y-position to be checked. */
	private void _checkKey(int x, int y) {
		
		if (DKObject.level.getKey() != null && DKObject.level.getKey().equalPosition(x, y) || DKObject.level.getMap()[y][x] == '$')
			updateIcon('$');
		else
			updateIcon('O');		
	}
	
	/** Checks if the ogre gets stunned and if the weapon hits a certain position of the map.
	 * @param x X-position to be checked.
	 * @param y Y-position to be checked.
	 * @return True if it hits, false otherwise. */
	public boolean checkHit(int x, int y) {
		
		int[][] adjacent = new int[][] {
			{ getY() + 1, getX()},
			{ getY() - 1, getX()},
			{ getY(), getX() + 1},
			{ getY(), getX() - 1}
			};

		for (int[] spot : adjacent) {
			if (spot[1] == x && spot[0] == y)
				setStunned(true);
		}
		
		return _weapon.checkHit(x, y);		
	}
}
