package dkeep.logic.objects;

public class Door extends DKObject {
	
	public Door(int x, int y, char icon) {
		super(x, y, icon);
	}
	
	public boolean isOpen() {
		
		if(getIcon() == 'e' || getIcon() == 'S')
			return true;
		else
			return false;
	}
	
	public boolean isExit() {
		
		if(getIcon() == 'E' || getIcon() == 'e')
			return true;
		else
			return false;
	}
	
	public void unlockDoor() {
		
		switch(getIcon()) {
		case 'I':
			updateIcon('S');
			break;
		case 'E':
			updateIcon('e');
			break;
		}
		
		DKObject.level.getMap()[getY()][getX()] = getIcon();
	}
}
