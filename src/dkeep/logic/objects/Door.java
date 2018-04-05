package dkeep.logic.objects;

import java.io.Serializable;

/** Door from a Level. */
public class Door extends DKObject implements Serializable {
	
	/** Creates an instance of Door.
	 * @param x X-position of the door.
	 * @param y Y-position of the door.
	 * @param icon Icon of the door. */
	public Door(int x, int y, char icon) {
		super(x, y, icon);
	}
	
	/** Checks if the door is open. 
	 * @return True if open, false otherwise. */
	public boolean isOpen() {
		return getIcon() == 'e' || getIcon() == 'S';
	}
	
	/** Checks if the door leads to an exit.
	 * @return True if it's an exit, false otherwise. */
	public boolean isExit() {
		return getIcon() == 'E' || getIcon() == 'e';
	}
	
	/** Unlocks an exit door previously locked. */
	public void unlockDoor() {
		
		if(getIcon() == 'E')
			updateIcon('e');
		
		DKObject.level.getMap()[getY()][getX()] = getIcon();
	}
}
