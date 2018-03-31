package dkeep.logic.objects;

import dkeep.logic.layout.Level;
import dkeep.logic.objects.Pair;

public abstract class DKObject {
	
	private Pair<Integer, Integer> _coord;
	private Level _level;
	private char _icon;
	
	public DKObject(int x, int y, Level level, char icon) {
		_coord = new Pair<Integer, Integer>(x, y);
		_level = level;
		_icon = icon;
	}
	
	public int getX() {
		return _coord.first;
	}
	
	public int getY() {
		return _coord.second;
	}
	
	public Pair<Integer, Integer> getCoords() {
		return _coord;
	}
	
	public Level getLevel() {
		return _level;
	}
	
	public char getIcon() {
		return _icon;
	}
	
	/** Updates the coordinates of the entity.
	 * @param x New x-position.
	 * @param y New y-position. */
	public void updateCoord(int x, int y) {
		_coord.first = x;
		_coord.second = y;
	}
	
	/** Update icon of the entity.
	 * @param i New icon. */
	public void updateIcon(char icon) {
		_icon = icon;
	}
}
