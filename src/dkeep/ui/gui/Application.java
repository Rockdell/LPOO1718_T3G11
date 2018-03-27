package dkeep.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SpringLayout;

import dkeep.io.ApplicationIO;
import dkeep.ui.cli.Game;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Application {

	private JFrame frame;
	private JTextField tfNrOgres;
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
		frame.setBounds(100, 100, 578, 416);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JButton btnStartGame = new JButton("Start Game");
		springLayout.putConstraint(SpringLayout.NORTH, btnStartGame, 82, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnStartGame, 381, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnStartGame, -64, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnStartGame);
		
		JLabel lblNrOgres = new JLabel("Number of Ogres");
		springLayout.putConstraint(SpringLayout.NORTH, lblNrOgres, 13, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNrOgres, 30, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNrOgres);
		
		JLabel lblGuardPersonality = new JLabel("Guard personality");
		springLayout.putConstraint(SpringLayout.NORTH, lblGuardPersonality, 19, SpringLayout.SOUTH, lblNrOgres);
		springLayout.putConstraint(SpringLayout.WEST, lblGuardPersonality, 0, SpringLayout.WEST, lblNrOgres);
		frame.getContentPane().add(lblGuardPersonality);
		
		tfNrOgres = new JTextField();
		tfNrOgres.setText("1");
		springLayout.putConstraint(SpringLayout.NORTH, tfNrOgres, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tfNrOgres, 32, SpringLayout.EAST, lblNrOgres);
		springLayout.putConstraint(SpringLayout.EAST, tfNrOgres, -377, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(tfNrOgres);
		tfNrOgres.setColumns(10);
		
		JComboBox cbGuardPersonality = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, cbGuardPersonality, 13, SpringLayout.SOUTH, tfNrOgres);
		springLayout.putConstraint(SpringLayout.WEST, cbGuardPersonality, 29, SpringLayout.EAST, lblGuardPersonality);
		springLayout.putConstraint(SpringLayout.EAST, cbGuardPersonality, -311, SpringLayout.EAST, frame.getContentPane());
		cbGuardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		cbGuardPersonality.setSelectedIndex(0);
		frame.getContentPane().add(cbGuardPersonality);
		
		JButton btnExitGame = new JButton("Exit Game");
		springLayout.putConstraint(SpringLayout.WEST, btnExitGame, 0, SpringLayout.WEST, btnStartGame);
		springLayout.putConstraint(SpringLayout.EAST, btnExitGame, -64, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnExitGame);
		
		JButton btnUp = new JButton("Up");
		springLayout.putConstraint(SpringLayout.EAST, btnUp, -94, SpringLayout.EAST, frame.getContentPane());
		btnUp.setEnabled(false);
		frame.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("Left");
		springLayout.putConstraint(SpringLayout.WEST, btnLeft, 336, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnUp, -6, SpringLayout.NORTH, btnLeft);
		springLayout.putConstraint(SpringLayout.NORTH, btnLeft, 178, SpringLayout.NORTH, frame.getContentPane());
		btnLeft.setEnabled(false);
		frame.getContentPane().add(btnLeft);
		
		JButton btnDown = new JButton("Down");
		springLayout.putConstraint(SpringLayout.WEST, btnDown, 394, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnDown, -94, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnExitGame, 66, SpringLayout.SOUTH, btnDown);
		springLayout.putConstraint(SpringLayout.WEST, btnUp, 0, SpringLayout.WEST, btnDown);
		springLayout.putConstraint(SpringLayout.NORTH, btnDown, 6, SpringLayout.SOUTH, btnLeft);
		btnDown.setEnabled(false);
		frame.getContentPane().add(btnDown);
		
		JButton btnRight = new JButton("Right");
		springLayout.putConstraint(SpringLayout.WEST, btnRight, 449, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLeft, -39, SpringLayout.WEST, btnRight);
		springLayout.putConstraint(SpringLayout.NORTH, btnRight, 0, SpringLayout.NORTH, btnLeft);
		springLayout.putConstraint(SpringLayout.EAST, btnRight, -39, SpringLayout.EAST, frame.getContentPane());
		btnRight.setEnabled(false);
		frame.getContentPane().add(btnRight);
		
		JLabel lblStatus = new JLabel("You can start a new game!");
		springLayout.putConstraint(SpringLayout.SOUTH, btnExitGame, -13, SpringLayout.NORTH, lblStatus);
		springLayout.putConstraint(SpringLayout.NORTH, lblStatus, 332, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStatus, 30, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblStatus, -31, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblStatus, -162, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(lblStatus);
		
		GraphicsMap panel = new GraphicsMap();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 19, SpringLayout.SOUTH, cbGuardPersonality);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.SOUTH, btnExitGame);
		springLayout.putConstraint(SpringLayout.EAST, panel, -6, SpringLayout.WEST, btnLeft);
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
				
				tfNrOgres.setEnabled(false);
				cbGuardPersonality.setEnabled(false);
				//taGame.setEnabled(true);
				
				//Update status
				lblStatus.setText("Click the direction buttons to move the hero!");
				
				if(Integer.parseInt(tfNrOgres.getText()) < 1)
					tfNrOgres.setText("1");
				else if(Integer.parseInt(tfNrOgres.getText()) > 5)
					tfNrOgres.setText("5");
				
				try {
					g = new Game(new ApplicationIO(panel), ((String) cbGuardPersonality.getSelectedItem()), Integer.parseInt(tfNrOgres.getText()));
				} catch (NumberFormatException | IOException e) {
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
				
				try {
					if(g.tickGame()) {
						lblStatus.setText(g.getCurrentLevel().endgameSummary());
					}
				} catch (IOException e) {
					e.printStackTrace();
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
				
				try {
					if(g.tickGame()) {
						lblStatus.setText(g.getCurrentLevel().endgameSummary());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
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
				
				try {
					if(g.tickGame()) {
						lblStatus.setText(g.getCurrentLevel().endgameSummary());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
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
				
				try {
					if(g.tickGame()) {
						lblStatus.setText(g.getCurrentLevel().endgameSummary());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				panel.requestFocusInWindow();
			}
		});

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
				
				try {
					if(g.tickGame()) {
						lblStatus.setText(g.getCurrentLevel().endgameSummary());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});		
		
	}
}
