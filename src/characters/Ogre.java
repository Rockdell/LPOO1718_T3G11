package characters;

import layout.Level;

public class Ogre extends Entity {
	
	Club weapon;
	private Level currentLevel;
	
	public Ogre(int x, int y, Level l) {
		super(x, y, 'O');
		weapon = new Club(getX(), getY(), this);
		currentLevel = l;
	}
	
	 public Club getWeapon() {
	 return weapon;
	 }
	
	 public Level getLevel() {
	 return currentLevel;
	 }
	
	public void move() {
		
		//Erase last position
		eraseLastPosition();
		
		int next_x = getX(), next_y = getY();
		
		//Generate new position
		generateRandomPosition(next_x, next_y);
	
		currentLevel.getMap()[getY()][getX()] = getIcon();
		
		weapon.attack();
	}

	protected boolean checkCollision(int x, int y) {

		if (currentLevel.getMap()[y][x] == 'X' || currentLevel.getMap()[y][x] == 'I'
				|| currentLevel.getMap()[y][x] == 'S')
			return false;

		if (currentLevel.getMap()[y][x] == 'k' || currentLevel.getMap()[y][x] == '$')
			updateIcon('$');
		else
			updateIcon('O');

		return true;
	}
	
	protected void eraseLastPosition() {
			
		if (currentLevel.getMap()[getY()][getX()] == '$')
			currentLevel.getMap()[getY()][getX()] = 'k';
		else
			currentLevel.getMap()[getY()][getX()] = ' ';
	}
}
