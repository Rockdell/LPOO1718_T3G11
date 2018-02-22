package characters;

import layout.Level;

public class Ogre extends Entity {
	
	Club weapon;
	
	public Ogre(int x, int y, Level l) {
		super(x, y, l, 'O');
		weapon = new Club(getX(), getY(), this);
	}
	
	 public Club getWeapon() {
	 return weapon;
	 }
	
	public void move() {
		
		//Erase last position
		eraseLastPosition();
		
		int next_x = getX(), next_y = getY();
		
		//Generate new position
		generateRandomPosition(next_x, next_y);
	
		getLevel().getMap()[getY()][getX()] = getIcon();
		
		weapon.attack();
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
	
	protected void eraseLastPosition() {
			
		if (getLevel().getMap()[getY()][getX()] == '$')
			getLevel().getMap()[getY()][getX()] = 'k';
		else
			getLevel().getMap()[getY()][getX()] = ' ';
	}
}
