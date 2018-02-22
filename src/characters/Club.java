package characters;

public class Club extends Entity {
	
	private Entity wielder;
	private char lastDirection;
	
	public Club(int x, int y, Entity w) {
		super(x, y, null, '*');
		wielder = w;
		lastDirection = 'n';
	}
	
	public char getLastDirection() {
		return lastDirection;
	}
	
	public void attack() {
		
		//Erase last position
		eraseLastPosition();
		
		int next_x = wielder.getX(), next_y = wielder.getY();
		
		//Generate new position
		generateRandomPosition(next_x, next_y);
		
		wielder.getLevel().getMap()[getY()][getX()] = getIcon();
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
			
			x = wielder.getX();
			y = wielder.getY();
		}
	}
	
	protected boolean checkCollision(int x, int y) {
		
		if (wielder.getLevel().getMap()[y][x] == 'X' || wielder.getLevel().getMap()[y][x] == 'I' || wielder.getLevel().getMap()[y][x] == 'S')
			return false;
			
		if (wielder.getLevel().getMap()[y][x] == 'k' || wielder.getLevel().getMap()[y][x] == '$')
			updateIcon('$');
		else
			updateIcon('*');
		
		return true;
	}
	
	protected void eraseLastPosition() {
		
		if (wielder.getLevel().getMap()[getY()][getX()] == '$')
			wielder.getLevel().getMap()[getY()][getX()] = 'k';
		else if(wielder.getLevel().getMap()[getY()][getX()] != 'O')
			wielder.getLevel().getMap()[getY()][getX()] = ' ';
	}
}
