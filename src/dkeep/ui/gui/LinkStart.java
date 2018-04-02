package dkeep.ui.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

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

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import dkeep.engine.Game;

public class LinkStart {

	public static JFrame frame;
	public static Game game;
	public static Clip music;
	private static JLabel background = new JLabel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LinkStart();
					// window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws LineUnavailableException 
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 */
	public LinkStart() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		initialize();
	}

	public void loadBackground() {
		String path = System.getProperty("user.dir") + "/src/miscellaneous/background_scaled.png";
		Image im = null;

		try {
			im = ImageIO.read(new File(path)).getScaledInstance(frame.getContentPane().getWidth(),
					frame.getContentPane().getHeight(), Image.SCALE_FAST);
		} catch (IOException e) {
			e.printStackTrace();
		}

		background.setIcon(new ImageIcon(im));
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 */
	private void initialize() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "\\src\\miscellaneous\\main_theme.wav"));
		music = AudioSystem.getClip();
		music.open(audioIn);
		music.start();

		// TODO ADD SOUNDTRACK
		/*
		 * public static void music(){
		 * 
		 * String filename = "darkAura.wav"; ContinuousAudioDataStream loop = null;
		 * InputStream in = null; try { in = new FileInputStream(filename); } catch
		 * (FileNotFoundException ex) { System.out.println("File not found"); } try {
		 * AudioStream s = new AudioStream(in); AudioData MD;
		 * AudioPlayer.player.start(s); } catch (IOException ex) {
		 * System.out.println(ex.getMessage()); }
		 * 
		 * }
		 * 
		 * AudioStream s = new AudioStream(in); AudioData audiodata = s.getData(); loop
		 * = new ContinuousAudioDataStream(audiodata); AudioPlayer.player.start(loop);
		 */
		
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

		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JButton btnNewGame = new JButton("New Game");
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewGame, -300, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewGame, -90, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnNewGame);

		JButton btnLoadGame = new JButton("Load Game");
		springLayout.putConstraint(SpringLayout.SOUTH, btnLoadGame, -250, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLoadGame, -90, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnLoadGame);

		JButton btnMapDesign = new JButton("Map Design");
		springLayout.putConstraint(SpringLayout.SOUTH, btnMapDesign, -200, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnMapDesign, -90, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnMapDesign);

		JButton btnExitGame = new JButton("Exit Game");
		springLayout.putConstraint(SpringLayout.SOUTH, btnExitGame, -50, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnExitGame, -90, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnExitGame);

		loadBackground();
		frame.getContentPane().add(background);

		// Event Handling

		// When the window is resized the map is resized with it!
		frame.getContentPane().addHierarchyBoundsListener(new HierarchyBoundsListener() {

			@Override
			public void ancestorMoved(HierarchyEvent e) {

			}

			@Override
			public void ancestorResized(HierarchyEvent e) {
				loadBackground();
			}
		});

		// NEW GAME BUTTON
		btnNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				music.stop();
				frame.setVisible(false);
				new Application();
			}
		});

		// LOAD GAME BUTTON
		btnLoadGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});

		// MAP DESIGN BUTTON
		btnMapDesign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				new MapCreation();
			}
		});

		// EXIT BUTTON
		btnExitGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				music.stop();
				music.close();
				System.exit(0);
			}
		});

	}
}
