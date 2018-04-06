package dkeep.logic.entities;

import dkeep.logic.objects.DKObject;
import dkeep.logic.objects.Door;

/** Weapon of an entity. */
public class Club extends Entity {
	
	/** Entity who holds the weapon. */
	private Entity 	_wielder;
	
	/** Direction of the last attack. */
	private char 	_lastDirection;
	
	/** Creates an instance of Club.
	 * @param wielder Wielder of the weapon. */
	public Club(Entity wielder) {
		super(wielder.getX(), wielder.getY(), '*');
		_wielder = wielder;
		_lastDirection = 'n';
	}
	
	/** @return Entity who holds the weapon. */
	public Entity getWielder() {
		return _wielder;
	}
	
	/** @return Direction of last attack. */
	public char getLastDirection() {
		return _lastDirection;
	}
	
	/** Attack with the weapon. */
	public void attack() {
		char d = generatePosition('w', _wielder.getX(), _wielder.getY(), true);
		
		_lastDirection = d;
	}
	
	public void drawEntity() {
		DKObject.level.getMap()[getY()][getX()] = getIcon();
	}
	
	public void eraseEntity() {
		
		if (getIcon() == '$')
			DKObject.level.getMap()[getY()][getX()] = DKObject.level.getKey().getIcon();
		else if(DKObject.level.getMap()[getY()][getX()] != 'O')
			DKObject.level.getMap()[getY()][getX()] = ' ';
	}
	
	public boolean checkCollision(int x, int y) {
		
		if(!checkWalls(x, y) || !_checkDoors(x, y))
			return false;
		
		_checkKey(x, y);
		
		return true;
	}
	
	/** Checks if there's a door in a certain position of the map.
	 * @param x X-position to be checked.
	 * @param y Y-position to be checked.
	 * @return True if there's no door, false otherwise. */
	private boolean _checkDoors(int x, int y) {
		
		for(Door door : DKObject.level.getDoors()) {
			
			if(door.equalPosition(x, y))
				return false;
		}
		
		return true;		
	}
	
	/** Checks if there's a key in a certain position of the map.
	 * @param x X-position to be checked.
	 * @param y Y-position to be checked. */
	private void _checkKey(int x, int y) {
		
		if (DKObject.level.getKey() != null && DKObject.level.getKey().equalPosition(x, y) || DKObject.level.getMap()[y][x] == '$')
			updateIcon('$');
		else
			updateIcon('*');		
	}
	
	/** Checks if the weapon hit a certain position of the map.
	 * @param x X-position to be checked.
	 * @param y Y-position to be checked.
	 * @return True if hit, false otherwise. */
	public boolean checkHit(int x, int y) {
		
		int cx = getX(), cy = getY();

		int[][] adjacent_club;

		switch (_lastDirection) {
		case 'w':
			adjacent_club = new int[][] { { cy, cx - 1 }, 	// esquerda
					{ cy, cx + 1 }, 						// direita
					{ cy - 1, cx },							// cima
			};
			break;
		case 'a':
			adjacent_club = new int[][] { { cy, cx - 1 }, 	// esquerda
					{ cy - 1, cx }, 						// cima
					{ cy + 1, cx },							// baixo
			};
			break;
		case 's':
			adjacent_club = new int[][] { { cy, cx - 1 }, 	// esquerda
					{ cy, cx + 1 }, 						// direita
					{ cy + 1, cx }, 						// baixo
			};
			break;
		case 'd':
			adjacent_club = new int[][] { { cy, cx + 1 }, 	// direita
					{ cy - 1, cx }, 						// cima
					{ cy + 1, cx }, 						// baixo
			};
			break;
		default:
			adjacent_club = new int[0][0];
		}

		for (int[] spot : adjacent_club) {
			if (spot[1] == x && spot[0] == y)
				return true;
		}
		
		return false;		
	}
}
