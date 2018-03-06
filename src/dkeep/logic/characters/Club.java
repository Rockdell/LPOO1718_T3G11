package dkeep.logic.characters;

public class Club extends Entity {
	
	private Entity wielder;
	private char lastDirection;
	
	public Club(Entity w) {
		super(w.getX(), w.getY(), null, '*');
		wielder = w;
		lastDirection = 'n';
	}
	
	public char getLastDirection() {
		return lastDirection;
	}
	
	public void attack() {
		char d = generatePosition(' ', wielder.getX(), wielder.getY(), true);
		
		lastDirection = d;
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
	
	public void drawPosition() {
		wielder.getLevel().getMap()[getY()][getX()] = getIcon();
	}
	
	public void erasePosition() {
		
		//Club esta na posiçao da chave
		if (getIcon() == '$')
			wielder.getLevel().getMap()[getY()][getX()] = 'k';
		else if(wielder.getLevel().getMap()[getY()][getX()] != 'O')
			wielder.getLevel().getMap()[getY()][getX()] = ' ';
	}
}
