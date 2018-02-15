package characters;

public abstract class Entity {
	
	private int x;
	private int y;
	
	public Entity() {
		x = 0;
		y = 0;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int new_x) {
		x = new_x;
	}
	
	public void setY(int new_y) {
		y = new_y;
	}
}
