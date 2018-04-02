package dkeep.ui.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GraphicsMap extends JPanel {

	private char[][] map;
	
	private static Image _wall;
	private static Image _door;
	private static Image _open_door;
	private static Image _exit;
	private static Image _hero;
	private static Image _hero_weapon;
	private static Image _hero_weapon_key;
	private static Image _guard;
	private static Image _ogre;
	private static Image _zzz;
	private static Image _key;
	private static Image _lever;
	private static Image _club;
	private static Image _symbol;
	private static Image _grass_b;
	private static Image _grass_t;
	
	/** Create the custom JPanel. */
	public GraphicsMap() {}

	public void loadAssets() {
		try {
			BufferedImage tmp;
			String path = System.getProperty("user.dir") + "/src/miscellaneous";

			int x = getWidth() / map[0].length;
			int y = getHeight() / map.length;
	
			tmp = ImageIO.read(new File(path + "/rock.png"));
			_wall = tmp.getScaledInstance(x, y, Image.SCALE_FAST);

			tmp = ImageIO.read(new File(path + "/bush.png"));
			_door = tmp.getScaledInstance(x, y, Image.SCALE_FAST);

			tmp = ImageIO.read(new File(path + "/bush_open.png"));
			_open_door = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/bush_exit.png"));
			_exit = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/link.jpg"));
			_hero = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/link_sword.jpg"));
			_hero_weapon = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/link_sword_key.jpg"));
			_hero_weapon_key = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/chicken.png"));
			_guard = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/ganon.png"));
			_ogre = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/zzz.png"));
			_zzz = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/key.png"));
			_key = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/pressure_plate.jpg"));
			_lever = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/club.png"));
			_club = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/key_overlapped.png"));
			_symbol = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/grass_bottom.png"));
			_grass_b = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/grass_top.png"));
			_grass_t = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void append(char[][] m) {
		
		if (m.length >= 3) {
			if (map == null) {
				map = m;
				loadAssets();
			} else if (map.length != m.length || map[0].length != m[0].length) {
				map = m;
				loadAssets();
			}
		}

		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {

		if (map == null)
			return;

		int x = 0;
		int y = 0;

		super.paintComponent(g);

		for (int i = 0; i < map.length; i++) {

			for (int j = 0; j < map[i].length; j++) {

				switch (map[i][j]) {
				case 'X':
					g.drawImage(_wall, x, y, this);
					break;
				case 'I':
					g.drawImage(_door, x, y, this);
					break;
				case 'E':
					g.drawImage(_exit, x, y, this);
					break;
				case 'S':
				case 'e':
					g.drawImage(_open_door, x, y, this);
					break;
				case 'H':
					g.drawImage(_hero, x, y, this);
					break;
				case 'A':
					g.drawImage(_hero_weapon, x, y, this);
					break;
				case 'G':
					g.drawImage(_guard, x, y, this);
					break;
				case 'g':
				case '8':
					g.drawImage(_zzz, x, y, this);
					break;
				case 'k':
					g.drawImage(_key, x, y, this);
					break;
				case 'l':
					g.drawImage(_lever, x, y, this);
					break;
				case 'K':
					g.drawImage(_hero_weapon_key, x, y, this);
					break;
				case 'O':
					g.drawImage(_ogre, x, y, this);
					break;
				case '$':
					g.drawImage(_symbol, x, y, this);
					break;
				case '*':
					g.drawImage(_club, x, y, this);
					break;
				case ' ':
					if (x % 2 == 0 && y % 2 == 0)
						g.drawImage(_grass_b, x, y, this);
					else
						g.drawImage(_grass_t, x, y, this);
					break;
				default:

				}

				x += getWidth() / map[0].length;
			}

			x = 0;
			y += getHeight() / map.length;
		}
	}
}
