package dkeep.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import dkeep.io.ApplicationIO;
import dkeep.ui.cli.Game;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JSlider;

public class Application {

	private JFrame frame;
	private Game g;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JButton btnStartGame = new JButton("Start Game");
		springLayout.putConstraint(SpringLayout.EAST, btnStartGame, -63, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnStartGame);
		
		JLabel lblNrOgres = new JLabel("Number of Ogres");
		frame.getContentPane().add(lblNrOgres);
		
		JLabel lblGuardPersonality = new JLabel("Guard personality");
		springLayout.putConstraint(SpringLayout.NORTH, lblNrOgres, 0, SpringLayout.NORTH, lblGuardPersonality);
		frame.getContentPane().add(lblGuardPersonality);
		
		JSlider sliderNumberOfOgres = new JSlider();
		springLayout.putConstraint(SpringLayout.WEST, sliderNumberOfOgres, -150, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, sliderNumberOfOgres, -80, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnStartGame, 7, SpringLayout.SOUTH, sliderNumberOfOgres);
		sliderNumberOfOgres.setValue(1);
		springLayout.putConstraint(SpringLayout.EAST, lblNrOgres, -6, SpringLayout.WEST, sliderNumberOfOgres);
		springLayout.putConstraint(SpringLayout.SOUTH, sliderNumberOfOgres, 76, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, sliderNumberOfOgres, 0, SpringLayout.NORTH, frame.getContentPane());
		sliderNumberOfOgres.setToolTipText("");
		sliderNumberOfOgres.setPaintLabels(true);
		sliderNumberOfOgres.setPaintTicks(true);
		sliderNumberOfOgres.setMajorTickSpacing(1);
		sliderNumberOfOgres.setMinimum(1);
		sliderNumberOfOgres.setMaximum(5);
		frame.getContentPane().add(sliderNumberOfOgres);
		
		JComboBox cbGuardPersonality = new JComboBox();
		springLayout.putConstraint(SpringLayout.WEST, lblGuardPersonality, -110, SpringLayout.WEST, cbGuardPersonality);
		springLayout.putConstraint(SpringLayout.WEST, cbGuardPersonality, -230, SpringLayout.EAST, lblNrOgres);
		springLayout.putConstraint(SpringLayout.EAST, cbGuardPersonality, -30, SpringLayout.WEST, lblNrOgres);
		springLayout.putConstraint(SpringLayout.NORTH, lblGuardPersonality, 3, SpringLayout.NORTH, cbGuardPersonality);
		springLayout.putConstraint(SpringLayout.NORTH, cbGuardPersonality, 17, SpringLayout.NORTH, frame.getContentPane());
		cbGuardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		cbGuardPersonality.setSelectedIndex(0);
		frame.getContentPane().add(cbGuardPersonality);
		
		JButton btnExitGame = new JButton("Exit Game");
		springLayout.putConstraint(SpringLayout.WEST, btnExitGame, 0, SpringLayout.WEST, btnStartGame);
		springLayout.putConstraint(SpringLayout.EAST, btnExitGame, -63, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnExitGame);
		
		JButton btnUp = new JButton("Up");
		springLayout.putConstraint(SpringLayout.NORTH, btnUp, 125, SpringLayout.NORTH, lblNrOgres);
		springLayout.putConstraint(SpringLayout.WEST, btnUp, -141, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnUp, -80, SpringLayout.EAST, frame.getContentPane());
		btnUp.setEnabled(false);
		frame.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("Left");
		springLayout.putConstraint(SpringLayout.NORTH, btnLeft, 140, SpringLayout.SOUTH, lblNrOgres);
		springLayout.putConstraint(SpringLayout.EAST, btnLeft, -121, SpringLayout.EAST, frame.getContentPane());
		btnLeft.setEnabled(false);
		frame.getContentPane().add(btnLeft);
		
		JButton btnDown = new JButton("Down");
		springLayout.putConstraint(SpringLayout.NORTH, btnExitGame, 50, SpringLayout.SOUTH, btnDown);
		springLayout.putConstraint(SpringLayout.NORTH, btnDown, 172, SpringLayout.SOUTH, lblNrOgres);
		springLayout.putConstraint(SpringLayout.WEST, btnDown, -141, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnDown, -80, SpringLayout.EAST, frame.getContentPane());
		btnDown.setEnabled(false);
		frame.getContentPane().add(btnDown);
		
		JButton btnRight = new JButton("Right");
		springLayout.putConstraint(SpringLayout.NORTH, btnRight, 140, SpringLayout.SOUTH, lblNrOgres);
		springLayout.putConstraint(SpringLayout.EAST, btnRight, -39, SpringLayout.EAST, frame.getContentPane());
		btnRight.setEnabled(false);
		frame.getContentPane().add(btnRight);
		
		JLabel lblStatus = new JLabel("You can start a new game!");
		springLayout.putConstraint(SpringLayout.NORTH, lblStatus, 15, SpringLayout.SOUTH, btnExitGame);
		springLayout.putConstraint(SpringLayout.EAST, lblStatus, -10, SpringLayout.EAST, frame.getContentPane());
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblStatus);
		
		GraphicsMap panel = new GraphicsMap();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.SOUTH, cbGuardPersonality);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -230, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
//		JTextArea taGame = new JTextArea();
//		sl_panel.putConstraint(SpringLayout.SOUTH, taGame, 213, SpringLayout.NORTH, panel);
//		sl_panel.putConstraint(SpringLayout.EAST, taGame, 196, SpringLayout.WEST, panel);
//		taGame.setFont(new Font("Courier New", Font.PLAIN, 13));
//		sl_panel.putConstraint(SpringLayout.NORTH, taGame, 0, SpringLayout.NORTH, panel);
//		sl_panel.putConstraint(SpringLayout.WEST, taGame, 0, SpringLayout.WEST, panel);
//		panel.add(taGame);
		
		//Event Handling
		
		//START BUTTON
		btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(!btnStartGame.isEnabled())
					return;
				
				//Update buttons
				btnStartGame.setEnabled(false);
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				panel.setEnabled(true);
				
				sliderNumberOfOgres.setEnabled(false);
				cbGuardPersonality.setEnabled(false);
				//taGame.setEnabled(true);
				
				//Update status
				lblStatus.setText("Move Hero - Arrow Keys");
				
//				Integer.parseInt(tfNrOgres.getText()
				
				try {
					g = new Game(new ApplicationIO(panel), ((String) cbGuardPersonality.getSelectedItem()), sliderNumberOfOgres.getValue(), 1);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
	
				panel.requestFocusInWindow();
				g.getCurrentLevel().display();
				
			}
		});
		
		//EXIT BUTTON
		btnExitGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Update status
				lblStatus.setText("Exiting program!");
				
				System.exit(0);
			}
		});
		
		//UP BUTTON
		btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(!btnUp.isEnabled())
					return;
				
				//Update status
				lblStatus.setText(g.getCurrentLevel().endgameSummary() + "up!");
				
				ApplicationIO.input = 'w';
				
				if(g.tick()) {
					lblStatus.setText(g.getCurrentLevel().endgameSummary());
				}
				
				panel.requestFocusInWindow();
			}
		});
		
		
		//DOWN BUTTON
		btnDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(!btnDown.isEnabled())
					return;
				
				//Update status
				lblStatus.setText(g.getCurrentLevel().endgameSummary() + "down!");
				
				ApplicationIO.input = 's';
				
				if(g.tick()) {
					lblStatus.setText(g.getCurrentLevel().endgameSummary());
				}
				
				panel.requestFocusInWindow();
			}
		});
		
		//LEFT BUTTON
		btnLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(!btnLeft.isEnabled())
					return;
				
				//Update status
				lblStatus.setText(g.getCurrentLevel().endgameSummary() + "left!");
				
				ApplicationIO.input = 'a';
				
				if(g.tick()) {
					lblStatus.setText(g.getCurrentLevel().endgameSummary());
				}
				
				panel.requestFocusInWindow();
			}
		});
		
		//RIGHT BUTTON
		btnRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(!btnRight.isEnabled())
					return;
				
				//Update status
				lblStatus.setText(g.getCurrentLevel().endgameSummary() + "right!");
				
				ApplicationIO.input = 'd';
				
				if(g.tick()) {
					lblStatus.setText(g.getCurrentLevel().endgameSummary());
				}
				
				panel.requestFocusInWindow();
			}
		});

		//When the window is resized the map is resized with it!
		frame.getContentPane().addHierarchyBoundsListener(new HierarchyBoundsListener() {

			@Override
			public void ancestorMoved(HierarchyEvent e) {
				
			}

			@Override
			public void ancestorResized(HierarchyEvent e) {
				if(g != null)
					panel.loadAssets();
			}
		});

		//Listener for key input
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (!panel.isEnabled())
					return;

				switch (arg0.getKeyCode()) {
				case KeyEvent.VK_UP:
					lblStatus.setText(g.getCurrentLevel().endgameSummary() + "up!");
					ApplicationIO.input = 'w';
					break;
				case KeyEvent.VK_DOWN:
					lblStatus.setText(g.getCurrentLevel().endgameSummary() + "down!");
					ApplicationIO.input = 's';
					break;
				case KeyEvent.VK_LEFT:
					lblStatus.setText(g.getCurrentLevel().endgameSummary() + "left!");
					ApplicationIO.input = 'a';
					break;
				case KeyEvent.VK_RIGHT:
					lblStatus.setText(g.getCurrentLevel().endgameSummary() + "right!");
					ApplicationIO.input = 'd';
					break;
				default:
					
				}
				
				if(g.tick()) {
					lblStatus.setText(g.getCurrentLevel().endgameSummary());
				}
			}
		});		
		
	}
}
