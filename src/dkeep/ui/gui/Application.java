package dkeep.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dkeep.io.ApplicationIO;
import dkeep.ui.cli.Game;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.JList;

public class Application {

	private JFrame frame;
//	private Game g;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Application window = new Application();
					// window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application(Game g) {
		initialize(g);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Game g) {
		frame.setVisible(true);
		frame.setBounds(100, 100, 600, 480);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		
		JButton btnExitGame = new JButton("Return to Menu");
		springLayout.putConstraint(SpringLayout.WEST, btnExitGame, -15, SpringLayout.WEST, btnStartGame);
		springLayout.putConstraint(SpringLayout.EAST, btnExitGame, -48, SpringLayout.EAST, frame.getContentPane());
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
		springLayout.putConstraint(SpringLayout.NORTH, btnExitGame, 130, SpringLayout.SOUTH, btnDown);
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
		springLayout.putConstraint(SpringLayout.EAST, lblStatus, -18, SpringLayout.EAST, frame.getContentPane());
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblStatus);
		
		JLabel legend = new JLabel("Map Selection :");
		springLayout.putConstraint(SpringLayout.WEST, legend, -3, SpringLayout.WEST, btnStartGame);
		legend.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(legend);
		
		JList mapSelection = new JList(existentMaps().toArray());
		JScrollPane mapScroll = new JScrollPane(mapSelection);
		springLayout.putConstraint(SpringLayout.SOUTH, legend, -6, SpringLayout.NORTH, mapScroll);
		springLayout.putConstraint(SpringLayout.NORTH, mapScroll, -90, SpringLayout.NORTH, btnExitGame);
		springLayout.putConstraint(SpringLayout.WEST, mapScroll, -165, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, mapScroll, -20, SpringLayout.NORTH, btnExitGame);
		springLayout.putConstraint(SpringLayout.EAST, mapScroll, -55, SpringLayout.EAST, frame.getContentPane());
		mapSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.getContentPane().add(mapScroll);
		
		GraphicsMap panel = new GraphicsMap();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.SOUTH, cbGuardPersonality);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -230, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		// Event Handling

		// MAP SELECTION
		mapSelection.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (mapSelection.getSelectedIndex() != 0 && mapSelection.getSelectedIndex() != 1) {
					cbGuardPersonality.setEnabled(false);
					sliderNumberOfOgres.setEnabled(false);
				} else {
					cbGuardPersonality.setEnabled(true);
					sliderNumberOfOgres.setEnabled(true);
				}
			}
		});

		//START BUTTON
		btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(!btnStartGame.isEnabled() || mapSelection.getSelectedIndex() == -1)
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
				mapSelection.setEnabled(false);
				
				//Update status
				lblStatus.setText("Move Hero - Arrow Keys");
				
				try {
					g = new Game(new ApplicationIO(panel), ((String) cbGuardPersonality.getSelectedItem()), sliderNumberOfOgres.getValue(), 1);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
	
				panel.requestFocusInWindow();
				g.getCurrentLevel().display();
				
			}
		});
		
		//MENU BUTTOM
		btnExitGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Update status
				lblStatus.setText("Exiting program!");
				
				frame.dispose();

				LinkStart Restarting = new LinkStart();
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

		//'X' Close Button Handler
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(frame, "Are you sure to close this window?", "Exit?",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
					frame.dispose();
			}
		});

	}
	
	public List<String> existentMaps() {
		
		List<String> mapsID = new ArrayList<String>();
		
		// Tries reading the file
		try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/miscellaneous/maps.txt"))) {

			// Searches for the correct map in maps.txt
			for (String mapSearch; (mapSearch = br.readLine()) != null;) {
				
				if(mapSearch.length() <= 3)
					continue;
				
				if (mapSearch.contains("Map"))
					mapsID.add(mapSearch.substring(3, mapSearch.length()));
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mapsID;

	}
	
}
