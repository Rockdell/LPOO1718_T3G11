package dkeep.logic.characters;

import dkeep.logic.layout.Level;

public class Ogre extends Entity {
	
	Club weapon;
	boolean stunned = false;
	int roundsStunned;
	
	public Ogre(int x, int y, Level l) {
		super(x, y, l, 'O');
		weapon = new Club(getX(), getY(), this);
		roundsStunned = 0;
	}
	
	 public Club getWeapon() {
		 return weapon;
	 }
	 
	 public boolean getStunned()
	 {
		 return stunned;
	 }
	 
	 public void setStunned(boolean s)
	 {
		 stunned = s;

		if (s)
		{
			updateIcon('8');
			roundsStunned = 2;
		}
		else
			updateIcon('O');
	 }
	
	public void move() {
		
		int next_x = getX(), next_y = getY();

		if (!stunned) {
			// Generate new position
			generateRandomPosition(next_x, next_y);

			weapon.attack();
		}
	}

	protected boolean checkCollision(int x, int y) {

		if (getLevel().getMap()[y][x] == 'X' || getLevel().getMap()[y][x] == 'I' || getLevel().getMap()[y][x] == 'S')
			return false;

		if (getLevel().getMap()[y][x] == 'k' || getLevel().getMap()[y][x] == '$')
			updateIcon('$');
		else
			updateIcon('O');

		return true;
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
	
	public void erasePosition() {
			
		//Ogre esta na posiçao da chave (independente da char atual no map)
		if(getIcon() == '$')
			getLevel().getMap()[getY()][getX()] = 'k';
		else
			getLevel().getMap()[getY()][getX()] = ' ';
		
		weapon.erasePosition();
	}
}
