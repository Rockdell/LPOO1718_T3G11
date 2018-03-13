package dkeep.logic.characters;

import java.util.Random;

import dkeep.logic.layout.Level;

public abstract class Entity {
	
	/**
	 * X coordinate
	 */
	private int _x;
	
	/**
	 * Y coordinate
	 */
	private int _y;
	
	/**
	 * Current level of the entity.
	 */
	private Level _level;
	
	/**
	 * Current icon of the entity
	 */
	private char _icon;
	
	//Constructor
	public Entity(int x, int y, Level l, char i) {
		_x = x;
		_y = y;
		_level = l;
		_icon = i;
	}
	
	//Get methods
	public int getX() {
		return _x;
	}	
	public int getY() {
		return _y;
	}
	public Level getLevel() {
		return _level;
	}
	public char getIcon() {
		return _icon;
	}
	
	//Set methods
	public void updateCoord(int x, int y) {
		_x = x;
		_y = y;
	}	
	public void updateIcon(char i) {
		_icon = i;
	}
	
	//Methods to generate the next position of an entity
	protected char generatePosition(char direction, int initial_x, int initial_y, boolean rand) {

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
		
		return direction;
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
	
	/**
	 * Draws the position of the entity on the map.
	 */
	protected abstract void drawPosition();
	
	/**
	 * Erases the position of the entity on the map.
	 */
	protected abstract void erasePosition();
	
	/**
	 * Checks if it's possible for the entity to move to a certain position.
	 * @param x X coordinate of the new possible position.
	 * @param y Y coordinate of the new possible position.
	 * @return Returns true if it's possible to move to that position, or false otherwise.
	 */
	protected abstract boolean checkCollision(int x, int y);
}
