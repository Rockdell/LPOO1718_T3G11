package dkeep.logic.characters;

public class Club extends Entity {
	
	/**
	 * Entity who holds the weapon.
	 */
	private Entity wielder;
	
	/**
	 * Direction of the last attack.
	 */
	private char lastDirection;
	
	/**
	 * Creates an object Club.
	 * @param w Wielder of the weapon.
	 */
	public Club(Entity w) {
		super(w.getX(), w.getY(), null, '*');
		wielder = w;
		lastDirection = 'n';
	}
	
	/**
	 * @return Direction of last attack.
	 */
	public char getLastDirection() {
		return lastDirection;
	}
	
	/**
	 * Attack with the weapon.
	 */
	public void attack() {
		char d = generatePosition(' ', wielder.getX(), wielder.getY(), true);
		
		lastDirection = d;
	}
	
	public void drawPosition() {
		wielder.getLevel().getMap()[getY()][getX()] = getIcon();
	}
	
	public void erasePosition() {
		
		if (getIcon() == '$')
			wielder.getLevel().getMap()[getY()][getX()] = 'k';
		else if(wielder.getLevel().getMap()[getY()][getX()] != 'O')
			wielder.getLevel().getMap()[getY()][getX()] = ' ';
	}
	
	public boolean checkCollision(int x, int y) {
		
		if (wielder.getLevel().getMap()[y][x] == 'X' || wielder.getLevel().getMap()[y][x] == 'I' || wielder.getLevel().getMap()[y][x] == 'S')
			return false;
			
		if (wielder.getLevel().getMap()[y][x] == 'k' || wielder.getLevel().getMap()[y][x] == '$')
			updateIcon('$');
		else
			updateIcon('*');
		
		return true;
	}
}
