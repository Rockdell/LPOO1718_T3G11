package characters;

public class Club extends Entity {
	
	private char icon = '*';
	
	public Club(int x, int y) {
		super(x, y);
	}
	
	public char getIcon() {
		return icon;
	}
}
