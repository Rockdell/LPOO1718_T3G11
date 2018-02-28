package dkeep.logic.layout;

import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Random;

import dkeep.logic.characters.*;

public class Level01 extends Level {
	
	//private List<Entry<Entry<Integer, Integer>, Character>> doors;
	private Guard guard;
	
	public Level01() {
		map = Maps.map01;
		mapID =  1;
		hero = new Hero(1, 1, this);
		//loadDoors();
		loadGuard();
	}
	
	public void updateLevel(char direction) {
			
		hero.move(direction);
		guard.patrol();
		
		if(hero.getKey() != 0 && hero.getKey() != -1)
			openDoors(hero.getKey());
		
//		for(Entry<Entry<Integer, Integer>, Character> door : doors) {
//			map[door.getKey().getValue()][door.getKey().getKey()] = door.getValue();
//		}
	}
	
	private void openDoors(int key) {
		
//		for(Entry<Entry<Integer, Integer>, Character> door : doors) {
//			door.setValue('S');
//		}

		if(key == 1) {
			map[1][4] = 'S';
			map[3][2] = 'S';
			map[3][4] = 'S';
			map[5][0] = 'S';
			map[6][0] = 'S';
			map[8][2] = 'S';
			map[8][4] = 'S';
		}
		
		hero.updateKey(-1);
	}
	
	protected boolean checkEnemy() {
		
		if(hero.getX() == guard.getX() && hero.getY() == guard.getY())
			return true;
		
		if(guard.getArmless())
			return false;
		
		int[][] adjacent = new int[][] {
					{ guard.getY() + 1, guard.getX()},
					{ guard.getY() - 1, guard.getX()},
					{ guard.getY(), guard.getX() + 1},
					{ guard.getY(), guard.getX() - 1}
					};
		
		for(int[] spot : adjacent) {
			if(map[spot[0]][spot[1]] == hero.getIcon())
				return true;
		}
		
		return false;
	}
	
	protected boolean checkEnd() {
		if((hero.getY() == 5 || hero.getY() == 6) && hero.getX() == 0)
			return true;
		else
			return false;
	}
	
//	private void loadDoors() {
//		
//		doors = new ArrayList<Entry<Entry<Integer, Integer>, Character>>();
//		
//		doors.add(new SimpleEntry<Entry<Integer, Integer>, Character>(new SimpleEntry<Integer, Integer>(4, 1), 'I'));
//		doors.add(new SimpleEntry<Entry<Integer, Integer>, Character>(new SimpleEntry<Integer, Integer>(2, 3), 'I'));
//		doors.add(new SimpleEntry<Entry<Integer, Integer>, Character>(new SimpleEntry<Integer, Integer>(4, 3), 'I'));
//		doors.add(new SimpleEntry<Entry<Integer, Integer>, Character>(new SimpleEntry<Integer, Integer>(0, 5), 'I'));
//		doors.add(new SimpleEntry<Entry<Integer, Integer>, Character>(new SimpleEntry<Integer, Integer>(0, 6), 'I'));
//		doors.add(new SimpleEntry<Entry<Integer, Integer>, Character>(new SimpleEntry<Integer, Integer>(2, 8), 'I'));
//		doors.add(new SimpleEntry<Entry<Integer, Integer>, Character>(new SimpleEntry<Integer, Integer>(4, 8), 'I'));	
//	}
	
	private void loadGuard() {
		
		int guard_type = new Random().nextInt(3);
		
		switch(guard_type) {
		case 0:
			guard = new Rookie(8, 1, this);
			break;
		case 1:
			guard = new Drunken(8, 1, this);
			break;
		case 2:
			guard = new Suspicious(8, 1, this);
			break;
			
		default:
			guard = new Rookie(0, 0, null);				
		}
	}
}
