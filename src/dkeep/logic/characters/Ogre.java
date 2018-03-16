package dkeep.logic.characters;

import dkeep.logic.layout.Level;

public class Ogre extends Entity {
	
	/**
	 * Ogre's weapon.
	 */
	Club weapon;
	
	/**
	 * True if stunned, false otherwise.
	 */
	boolean stunned = false;
	
	/**
	 * Number of rounds the ogre is going to be stunned.
	 */
	int roundsStunned;
	
	/**
	 * Creates an object Ogre.
	 * @param x X-position of the ogre.
	 * @param y Y-position of the ogre.
	 * @param l Current level.
	 */
	public Ogre(int x, int y, Level l) {
		super(x, y, l, 'O');
		weapon = new Club(this);
		roundsStunned = 0;
	}
	
	/**
	 * @return Ogre's weapon.
	 */
	 public Club getWeapon() {
		 return weapon;
	 }
	 
	 /**
	  * @return True if ogre is stunned, false otherwise.
	  */
	 public boolean isStunned() {
		 return stunned;
	 }
	 
	 /**
	  * Sets the ogre as stunned/not-stunned.
	  * @param s True to make the ogre stunned, false otherwise.
	  */
	 public void setStunned(boolean s) {
		 stunned = s;

		if (s) {
			updateIcon('8');
			roundsStunned = 2;
		}
		else
			updateIcon('O');
	 }
	
	 /**
	  * Move the ogre.
	  */
	public void move() {

		if (!stunned) {
			generatePosition(' ', getX(), getY(), true);
			weapon.attack();
		}
	}
	
	public void drawPosition() {
		getLevel().getMap()[getY()][getX()] = getIcon();

		if (!stunned)
			weapon.drawPosition();
		else {

			if (roundsStunned-- == 0)
				setStunned(false);
		}
	}
	
	//Used only for tests
	public void drawPositionWithoutSwing() {
		getLevel().getMap()[getY()][getX()] = getIcon();

		if (stunned)
		{
			if (roundsStunned-- == 0)
				setStunned(false);
		}
	}
	
	public void erasePosition() {
			
		//Ogre esta na posi�ao da chave (independente da char atual no map)
		if(getIcon() == '$')
			getLevel().getMap()[getY()][getX()] = 'k';
		else
			getLevel().getMap()[getY()][getX()] = ' ';
		
		weapon.erasePosition();
	}
	
	public boolean checkCollision(int x, int y) {

		if (getLevel().getMap()[y][x] == 'X' || getLevel().getMap()[y][x] == 'I' || getLevel().getMap()[y][x] == 'S')
			return false;

		if (getLevel().getMap()[y][x] == 'k' || getLevel().getMap()[y][x] == '$')
			updateIcon('$');
		else
			updateIcon('O');

		return true;
	}
}
