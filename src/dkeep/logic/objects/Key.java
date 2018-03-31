package dkeep.logic.objects;

import dkeep.logic.layout.Level;

public class Key extends DKObject {
	
	public enum key_t { KEY, LEVER }

	key_t _type;
	
	public Key(int x, int y, Level level, char icon, key_t type) {
		super(x, y, level, icon);
		
		_type = type;
	}
	
	public key_t getType() {
		return _type;
	}
}
