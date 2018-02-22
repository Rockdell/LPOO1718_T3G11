package layout;

import characters.*;

public class Level01 extends Level {
	
	private Guard guard;
	
	public Level01() {
		map = Maps.map01;
		mapID =  1;
		hero = new Hero(1, 1, this);
		guard = new Guard(8, 1, this);
	}
	
	public void updateLevel(char direction) {
		
		hero.move(direction);
		guard.move();
		
		if(hero.getKey() != 0 && hero.getKey() != -1)
			openDoors(hero.getKey());
	}
	
	private void openDoors(int key) {

		if(key == 1) {
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
}
