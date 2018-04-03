package dkeep.logic.objects;

import java.io.Serializable;

public class Door extends DKObject implements Serializable {
	
	public Door(int x, int y, char icon) {
		super(x, y, icon);
	}
	
	public boolean isOpen() {
		return getIcon() == 'e' || getIcon() == 'S';
	}
	
	public boolean isExit() {
		return getIcon() == 'E' || getIcon() == 'e';
	}
	
	public void unlockDoor() {
		
		if(getIcon() == 'E')
			updateIcon('e');
		
//		switch(getIcon()) {
//		case 'I':
//			updateIcon('S');
//			break;
//		case 'E':
//			updateIcon('e');
//			break;
//		}
		
		DKObject.level.getMap()[getY()][getX()] = getIcon();
	}
}
