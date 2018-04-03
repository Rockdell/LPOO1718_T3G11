package dkeep.ui.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JButton;


import dkeep.engine.Game;

public class LinkStart {

	public static JFrame	 	frame;
	public static Game		 	game;
	public static SoundManager 	sound;
	
	private SpringLayout 		_sprLayout;
	private JLabel 				_background;
	private JButton 			_btnNewGame;
	private JButton 			_btnLoadGame;
	private JButton 			_btnMapDesign;
	private JButton  			_btnExitGame;

	/** Create the application. */
	public LinkStart() {
		initialize();
	}
	
	/**Initialize the contents of the frame. */
	private void initialize() {
		
		_initializeComponents();
		_initializeEventHandlers();
		_initializeSound();
	}
	
	private void _initializeComponents() {
		
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 960, 540);
		frame.getContentPane().setSize(new Dimension(960, 540));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			frame.setIconImage(
					ImageIO.read(new File(System.getProperty("user.dir") + "/src/miscellaneous/LPOO_icon.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		_sprLayout = new SpringLayout();
		frame.getContentPane().setLayout(_sprLayout);

		_btnNewGame = new JButton("New Game");
		_sprLayout.putConstraint(SpringLayout.SOUTH, _btnNewGame, -300, SpringLayout.SOUTH, frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.EAST, _btnNewGame, -90, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(_btnNewGame);

		_btnLoadGame = new JButton("Load Game");
		_sprLayout.putConstraint(SpringLayout.SOUTH, _btnLoadGame, -250, SpringLayout.SOUTH, frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.EAST, _btnLoadGame, -90, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(_btnLoadGame);

		_btnMapDesign = new JButton("Map Design");
		_sprLayout.putConstraint(SpringLayout.SOUTH, _btnMapDesign, -200, SpringLayout.SOUTH, frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.EAST, _btnMapDesign, -90, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(_btnMapDesign);

		_btnExitGame = new JButton("Exit Game");
		_sprLayout.putConstraint(SpringLayout.SOUTH, _btnExitGame, -50, SpringLayout.SOUTH, frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.EAST, _btnExitGame, -90, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(_btnExitGame);

		_background = new JLabel();
		
		_loadBackground();
		
		frame.getContentPane().add(_background);
	}
	
	private void _initializeEventHandlers() {

		// When the window is resized the map is resized with it!
		frame.getContentPane().addHierarchyBoundsListener(new HierarchyBoundsListener() {

			@Override
			public void ancestorMoved(HierarchyEvent e) {}

			@Override
			public void ancestorResized(HierarchyEvent e) {
				_loadBackground();
			}
		});

		// NEW GAME BUTTON
		_btnNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				new Application();
			}
		});

		// LOAD GAME BUTTON
		_btnLoadGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});

		// MAP DESIGN BUTTON
		_btnMapDesign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				new MapCreation();
			}
		});

		// EXIT BUTTON
		_btnExitGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void _initializeSound() {
		
		sound = new SoundManager();
		sound.loadMenuMusic();
	}
	
	private void _loadBackground() {
		String path = System.getProperty("user.dir") + "/src/miscellaneous/background_scaled.png";
		Image im = null;

		try {
			im = ImageIO.read(new File(path)).getScaledInstance(frame.getContentPane().getWidth(),
					frame.getContentPane().getHeight(), Image.SCALE_FAST);
		} catch (IOException e) {
			e.printStackTrace();
		}

		_background.setIcon(new ImageIcon(im));
	}
}