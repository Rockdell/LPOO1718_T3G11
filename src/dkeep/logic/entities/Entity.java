package dkeep.logic.entities;

import java.util.Random;

import dkeep.logic.objects.DKObject;

public abstract class Entity extends DKObject {
	
	/** Creates an object Entity.
	 * @param x X-position of the entity.
	 * @param y Y-position of the entity.
	 * @param l Current level.
	 * @param i Icon. */
	public Entity(int x, int y, char icon) {
		super(x, y, icon);
	}
	
	/** Generates a new position for the entity, randomly or not.
	 * @param direction 
	 * @param initial_x Initial x-position of the entity.
	 * @param initial_y Initial y-position of the entity.
	 * @param rand True to randomly generate a direction, false otherwise.
	 * @return Last movement of the entity. */
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
	
	/** Generates a direction to be used in generatePosition.
	 * @return Direction generated. */
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
	
	/** Draws the position of the entity on the map. */
	public abstract void drawEntity();
	
	/** Erases the position of the entity on the map. */
	public abstract void eraseEntity();
	
	/** Checks if it's possible for the entity to move to a certain position.
	 * @param x X coordinate of the new possible position.
	 * @param y Y coordinate of the new possible position.
	 * @return Returns true if it's possible to move to that position, or false otherwise. */
	protected abstract boolean checkCollision(int x, int y);
}
