package dkeep.logic.entities;

import dkeep.logic.layout.Level;
import dkeep.logic.objects.Door;

public class Ogre extends Entity {
	
	/** Ogre's weapon. */
	private Club 	_weapon;
	
	/** True if stunned, false otherwise. */
	private boolean _stunned;
	
	/** Number of rounds the ogre is going to be stunned. */
	private int 	_roundsStunned;
	
	/** Creates an object Ogre.
	 * @param x X-position of the ogre.
	 * @param y Y-position of the ogre.
	 * @param l Current level. */
	public Ogre(int x, int y, Level l) {
		super(x, y, l, 'O');
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
		getLevel().getMap()[getY()][getX()] = getIcon();

		if (!_stunned)
			_weapon.drawEntity();
		else {

			if (_roundsStunned-- == 0)
				setStunned(false);
		}
	}
	
	//Used only for tests
	public void drawPositionWithoutSwing() {
		getLevel().getMap()[getY()][getX()] = getIcon();

		if (_stunned)
		{
			if (_roundsStunned-- == 0)
				setStunned(false);
		}
	}
	
	public void eraseEntity() {
			
		//Ogre esta na posicao da chave (independente da char atual no map)
		if(getIcon() == '$')
			getLevel().getMap()[getY()][getX()] = 'k';
		else
			getLevel().getMap()[getY()][getX()] = ' ';
		
		_weapon.eraseEntity();
	}
	
	public boolean checkCollision(int x, int y) {

		if (getLevel().getMap()[y][x] == 'X')
			return false;
		
		for(Door door : getLevel().getDoors()) {
			
			if(door.getX() == x && door.getY() == y)
				return false;
		}

		if (getLevel().getMap()[y][x] == 'k' || getLevel().getMap()[y][x] == '$')
			updateIcon('$');
		else
			updateIcon('O');

		return true;
	}
	
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
