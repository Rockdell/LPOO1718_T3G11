package dkeep.logic.characters;

import java.util.Random;

import dkeep.logic.layout.Level;

public abstract class Entity {
	
	//Fields
	private int x;
	private int y;
	private Level currentLevel;
	private char icon;
	
	//Constructor
	public Entity(int x, int y, Level l, char i) {
		this.x = x;
		this.y = y;
		currentLevel = l;
		icon = i;
	}
	
	//Get methods
	public int getX() {
		return x;
	}	
	public int getY() {
		return y;
	}
	public Level getLevel() {
		return currentLevel;
	}
	public char getIcon() {
		return icon;
	}
	
	//Set methods
	public void updateCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}	
	public void updateIcon(char i) {
		icon = i;
	}
	
	//Methods to draw and erase position
	public abstract void drawPosition();
	public abstract void erasePosition();
	
	//Methods to generate the next position of an entity
	protected void generatePosition(char direction, int initial_x, int initial_y, boolean rand) {

		int iX = initial_x, iY = initial_y;

		boolean done = false;
		while (!done) {

			if (rand)
				direction = generateDirection();

			switch (direction) {
			case 'w':
				initial_y--;
				break;
			case 'a':
				initial_x--;
				break;
			case 's':
				initial_y++;
				break;
			case 'd':
				initial_x++;
				break;
			}

			if (checkCollision(initial_x, initial_y)) {
				updateCoord(initial_x, initial_y);
				break;
			}

			if (!rand)
				break;

			initial_x = iX;
			initial_y = iY;
		}
	}
	protected char generateDirection() {

		// Random number between 0 and 3;
		int nextMove = new Random().nextInt(4);

		switch (nextMove) {
		case 0:
			return 'w';
		case 1:
			return 'a';
		case 2:
			return 's';
		case 3:
			return 'd';
		default:
			return 'n';
		}
	}
	
	protected abstract boolean checkCollision(int x, int y);
}
