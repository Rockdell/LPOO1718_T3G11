package dkeep.logic.layout;

public interface ILevelLogic {
	
	/**
	 * Clears the all entities from the level.
	 */
	void clearEntities();
	
	/**
	 * Updates the entities from the level.
	 * @param d
	 */
	void updateEntities(char d);
	
	/**
	 * Updates the doors from the level.
	 */
	void updateDoors();
	
	/**
	 * Draws the entities from the level.
	 */
	void drawEntities();
	
	/**
	 * Updates level status.
	 */
	void updateLevelStatus();
	
	/**
	 * Load enemies into the level.
	 */
	void loadEnemies();

}
