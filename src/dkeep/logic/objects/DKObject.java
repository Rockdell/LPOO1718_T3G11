package dkeep.logic.objects;

import dkeep.logic.layout.Level;
import dkeep.logic.objects.Pair;

public class DKObject {
	
	/** Level of the object. */
	static public Level 			level;
	
	/** Coordinates of the object. */
	private Pair<Integer, Integer>	_coord;
	
	/** Icon of the */
	private char 					_icon;
	
	public DKObject(int x, int y, char icon) {
		_coord = new Pair<Integer, Integer>(x, y);
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
	
	public boolean equalPosition(int x, int y) {
		return _coord.equals(new Pair<Integer, Integer>(x, y));
	}
	
	public boolean equalPosition(Pair<Integer, Integer> pair) {
		return _coord.equals(pair);
	}
}
