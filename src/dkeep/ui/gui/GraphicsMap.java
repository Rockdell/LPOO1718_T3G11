package dkeep.ui.gui;

import miscellaneous.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.objects.Door.door_t;

public class GraphicsMap extends JPanel {

	private static char[][] map;
	
	private static Image wall;
	private static Image door;
	private static Image open_door;
	private static Image exit;
	private static Image hero;
	private static Image hero_weapon;
	private static Image hero_weapon_key;
	private static Image guard;
	private static Image ogre;
	private static Image zzz;
	private static Image key;
	private static Image lever;
	private static Image club;
	private static Image symbol;
	private static Image grass_b;
	private static Image grass_t;
	
	/**
	 * Create the custom JPanel.
	 */
	public GraphicsMap() {
//		setBackground(Color.WHITE);
	}

	public void loadAssets() {
		try {
			BufferedImage tmp;
			String path = System.getProperty("user.dir") + "/src/miscellaneous";
			
			int x = getWidth() / map[0].length;
			int y = getHeight() / map.length;
	
			tmp = ImageIO.read(new File(path + "/rock.png"));
			wall = tmp.getScaledInstance(x, y, Image.SCALE_FAST);

			tmp = ImageIO.read(new File(path + "/bush.png"));
			door = tmp.getScaledInstance(x, y, Image.SCALE_FAST);

			tmp = ImageIO.read(new File(path + "/bush_open.png"));
			open_door = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/bush_exit.png"));
			exit = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/link.jpg"));
			hero = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/link_sword.jpg"));
			hero_weapon = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/link_sword_key.jpg"));
			hero_weapon_key = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/chicken.png"));
			guard = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/ganon.png"));
			ogre = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/zzz.png"));
			zzz = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/key.png"));
			key = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/pressure_plate.jpg"));
			lever = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/club.png"));
			club = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/key_overlapped.png"));
			symbol = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/grass_bottom.png"));
			grass_b = tmp.getScaledInstance(x, y, Image.SCALE_FAST);
			
			tmp = ImageIO.read(new File(path + "/grass_top.png"));
			grass_t = tmp.getScaledInstance(x, y, Image.SCALE_FAST);

//			int pixel = tmp.getRGB(1, 1);
//			Color testColor = new Color(pixel, true);
//			System.out.println(testColor.getRed() + " + " + testColor.getGreen() + " + " + testColor.getBlue());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void append(char[][] m) {

		if (map == null) {
			map = m;
			loadAssets();
		} else if (map.length != m.length || map[0].length != m[0].length) {
			map = m;
			loadAssets();
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
					g.drawImage(wall, x, y, this);
					break;
				case 'I':
					g.drawImage(door, x, y, this);
					break;
				case 'E':
					g.drawImage(exit, x, y, this);
					break;
				case 'S':
				case 'e':
					g.drawImage(open_door, x, y, this);
					break;
				case 'H':
					g.drawImage(hero, x, y, this);
					break;
				case 'A':
					g.drawImage(hero_weapon, x, y, this);
					break;
				case 'G':
					g.drawImage(guard, x, y, this);
					break;
				case 'g':
				case '8':
					g.drawImage(zzz, x, y, this);
					break;
				case 'k':
					g.drawImage(key, x, y, this);
					break;
				case 'l':
					g.drawImage(lever, x, y, this);
					break;
				case 'K':
					g.drawImage(hero_weapon_key, x, y, this);
					break;
				case 'O':
					g.drawImage(ogre, x, y, this);
					break;
				case '$':
					g.drawImage(symbol, x, y, this);
					break;
				case '*':
					g.drawImage(club, x, y, this);
					break;
				case ' ':
					if (x % 2 == 0 && y % 2 == 0)
						g.drawImage(grass_b, x, y, this);
					else
						g.drawImage(grass_t, x, y, this);
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
