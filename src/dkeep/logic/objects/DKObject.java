package dkeep.logic.objects;

import java.io.Serializable;

import dkeep.logic.layout.Level;
import dkeep.logic.objects.Pair;

/** Object from a Level. */
public class DKObject implements Serializable {
	
	/** Level of the object. */
	static public Level 			level;
	
	/** Coordinates of the object. */
	private Pair<Integer, Integer>	_coord;
	
	/** Icon of the object. */
	private char 					_icon;
	
	/** Creates an instance of DKObject.
	 * @param x X-position of the object.
	 * @param y Y-position of the object.
	 * @param icon Icon of the object. */
	public DKObject(int x, int y, char icon) {
		_coord = new Pair<Integer, Integer>(x, y);
		_icon = icon;
	}
	
	/** @return X-position of the object. */
	public int getX() {
		return _coord.first;
	}
	
	/** @return Y-position of the object. */
	public int getY() {
		return _coord.second;
	}
	
	/** @return Coordinates of the object. */
	public Pair<Integer, Integer> getCoords() {
		return _coord;
	}
	
	/** @return Icon of the object. */
	public char getIcon() {
		return _icon;
	}
	
	/** Updates the coordinates of the object.
	 * @param x New x-position.
	 * @param y New y-position. */
	public void updateCoord(int x, int y) {
		_coord.first = x;
		_coord.second = y;
	}
	
	/** Update icon of the object.
	 * @param icon New icon. */
	public void updateIcon(char icon) {
		_icon = icon;
	}
	
	/** Compares object's position with the x-position and y-position given.
	 * @param x X-position to compare.
	 * @param y Y-position to compare.
	 * @return True if equals, false otherwise. */
	public boolean equalPosition(int x, int y) {
		return _coord.equals(new Pair<Integer, Integer>(x, y));
	}
	
	/** Compares object's position with the coordinates given.
	 * @param pair Coordinates to compare.
	 * @return True if equals, false otherwise. */
	public boolean equalPosition(Pair<Integer, Integer> pair) {
		return _coord.equals(pair);
	}
}
