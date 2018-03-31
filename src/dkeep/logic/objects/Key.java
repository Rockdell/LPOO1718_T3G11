package dkeep.logic.objects;

public class Key extends DKObject {
	
	public enum key_t { KEY, LEVER }

	key_t _type;
	
	public Key(int x, int y, char icon, key_t type) {
		super(x, y, icon);
		
		_type = type;
	}
	
	public key_t getType() {
		return _type;
	}
}
