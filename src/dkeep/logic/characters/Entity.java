package dkeep.logic.characters;

import java.util.Random;

import dkeep.logic.layout.Level;

public abstract class Entity {
	
	/**
	 * X coordinate
	 */
	private int x;
	
	/**
	 * Y coordinate
	 */
	private int y;
	
	/**
	 * Level of the entity.
	 */
	private Level currentLevel;
	
	/**
	 * Icon of the entity
	 */
	private char icon;
	
	/**
	 * Creates an object Entity.
	 * @param x X-position of the entity.
	 * @param y Y-position of the entity.
	 * @param l Current level.
	 * @param i
	 */
	public Entity(int x, int y, Level l, char i) {
		this.x = x;
		this.y = y;
		currentLevel = l;
		icon = i;
	}
	
	/**
	 * @return X coordinate.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return Y coordinate.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @return Level of the entity.
	 */
	public Level getLevel() {
		return currentLevel;
	}
	
	/**
	 * @return Icon of the entity.
	 */
	public char getIcon() {
		return icon;
	}
	
	/**
	 * Updates the coordinates of the entity.
	 * @param x New x-position.
	 * @param y New y-position.
	 */
	public void updateCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Update icon of the entity.
	 * @param i New icon.
	 */
	public void updateIcon(char i) {
		icon = i;
	}
	
	/**
	 * Generates a new position for the entity, randomly or not.
	 * @param direction 
	 * @param initial_x Initial x-position of the entity.
	 * @param initial_y Initial y-position of the entity.
	 * @param rand True to randomly generate a direction, false otherwise.
	 * @return Last movement of the entity.
	 */
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
	
	/**
	 * Generates a direction to be used in generatePosition.
	 * @return Direction generated.
	 */
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
