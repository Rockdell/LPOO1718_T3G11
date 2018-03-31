package dkeep.logic.objects;

public class Door extends DKObject {
	
	public enum door_t { REGULAR, EXIT }
	
	private door_t _type;
	
	public Door(int x, int y, char icon, door_t type) {
		super(x, y, icon);
		
		_type = type;
	}
	
	public door_t getType() {
		return _type;
	}
	
	public void unlockDoor() {
		
		if(getIcon() == 'S' || getIcon() == 'e')
			return;
		
		switch(_type) {
		case REGULAR:
			updateIcon('S');
			break;
		case EXIT:
			updateIcon('e');
			break;
		}
		
		DKObject.level.getMap()[getY()][getX()] = getIcon();
	}
}
