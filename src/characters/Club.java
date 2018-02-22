package characters;

public class Club extends Entity {
	
	private Ogre ogre;
	private char lastDirection;
	
	public Club(int x, int y, Ogre o) {
		super(x, y, '*');
		ogre = o;
		lastDirection = 'n';
	}
	
	public char getLastDirection() {
		return lastDirection;
	}
	
	public void attack() {
		
		//Erase last position
		eraseLastPosition();
		
		int next_x = ogre.getX(), next_y = ogre.getY();
		
		//Generate new position
		generateRandomPosition(next_x, next_y);
		
		ogre.getLevel().getMap()[getY()][getX()] = getIcon();
	}
	
	@Override
	protected void generateRandomPosition(int x, int y) {
		
		char direction;
		
		while(true) {
			
			direction = generateDirection();

			switch (direction) {
			case 'w':
				y--;
				break;
			case 'a':
				x--;
				break;
			case 's':
				y++;
				break;
			case 'd':
				x++;
				break;
			}

			if (checkCollision(x, y)) {
				updateCoord(x, y);
				return;
			}
			
			x = ogre.getX();
			y = ogre.getY();
		}
	}
	
	protected boolean checkCollision(int x, int y) {
		
		if (ogre.getLevel().getMap()[y][x] == 'X' || ogre.getLevel().getMap()[y][x] == 'I' || ogre.getLevel().getMap()[y][x] == 'S')
			return false;
			
		if (ogre.getLevel().getMap()[y][x] == 'k' || ogre.getLevel().getMap()[y][x] == '$')
			updateIcon('$');
		else
			updateIcon('*');
		
		return true;
	}
	
	protected void eraseLastPosition() {
		
		if (ogre.getLevel().getMap()[getY()][getX()] == '$')
			ogre.getLevel().getMap()[getY()][getX()] = 'k';
		else if(ogre.getLevel().getMap()[getY()][getX()] != 'O')
			ogre.getLevel().getMap()[getY()][getX()] = ' ';
	}
}
