package dkeep.logic.objects;

import dkeep.logic.layout.Level;

public class Door extends DKObject {
	
	public enum door_t { REGULAR, EXIT }
	
	private door_t _type;
	
	public Door(int x, int y, Level level, char icon, door_t type) {
		super(x, y, level, icon);
		
		_type = type;
	}
	
	public door_t getType() {
		return _type;
	}
	
	public void unlockDoor() {
		updateIcon('S');
		getLevel().getMap()[getY()][getX()] = getIcon();
	}
}
