package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Font;

public class Application {

	private JFrame frame;
	private JTextField tfNrOgres;

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
		springLayout.putConstraint(SpringLayout.NORTH, btnExitGame, 296, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnExitGame, 0, SpringLayout.WEST, btnStartGame);
		springLayout.putConstraint(SpringLayout.EAST, btnExitGame, -64, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnExitGame);
		
		JTextArea taGame = new JTextArea();
		springLayout.putConstraint(SpringLayout.NORTH, taGame, 21, SpringLayout.SOUTH, cbGuardPersonality);
		springLayout.putConstraint(SpringLayout.SOUTH, btnExitGame, 0, SpringLayout.SOUTH, taGame);
		springLayout.putConstraint(SpringLayout.NORTH, btnStartGame, -2, SpringLayout.NORTH, taGame);
		springLayout.putConstraint(SpringLayout.WEST, btnStartGame, 76, SpringLayout.EAST, taGame);
		springLayout.putConstraint(SpringLayout.EAST, taGame, -257, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, taGame, 30, SpringLayout.WEST, frame.getContentPane());
		taGame.setFont(new Font("Courier New", Font.PLAIN, 13));
		frame.getContentPane().add(taGame);
		
		JButton btnUp = new JButton("Up");
		springLayout.putConstraint(SpringLayout.EAST, btnUp, -94, SpringLayout.EAST, frame.getContentPane());
		btnUp.setEnabled(false);
		frame.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("Left");
		springLayout.putConstraint(SpringLayout.WEST, btnLeft, 31, SpringLayout.EAST, taGame);
		springLayout.putConstraint(SpringLayout.EAST, btnLeft, -152, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnUp, -6, SpringLayout.NORTH, btnLeft);
		springLayout.putConstraint(SpringLayout.NORTH, btnLeft, 178, SpringLayout.NORTH, frame.getContentPane());
		btnLeft.setEnabled(false);
		frame.getContentPane().add(btnLeft);
		
		JButton btnDown = new JButton("Down");
		springLayout.putConstraint(SpringLayout.WEST, btnDown, 89, SpringLayout.EAST, taGame);
		springLayout.putConstraint(SpringLayout.EAST, btnDown, -94, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnUp, 0, SpringLayout.WEST, btnDown);
		springLayout.putConstraint(SpringLayout.NORTH, btnDown, 6, SpringLayout.SOUTH, btnLeft);
		btnDown.setEnabled(false);
		frame.getContentPane().add(btnDown);
		
		JButton btnRight = new JButton("Right");
		springLayout.putConstraint(SpringLayout.NORTH, btnRight, 0, SpringLayout.NORTH, btnLeft);
		springLayout.putConstraint(SpringLayout.WEST, btnRight, 39, SpringLayout.EAST, btnLeft);
		springLayout.putConstraint(SpringLayout.EAST, btnRight, -39, SpringLayout.EAST, frame.getContentPane());
		btnRight.setEnabled(false);
		frame.getContentPane().add(btnRight);
		
		JLabel lblStatus = new JLabel("You can start a new game!");
		springLayout.putConstraint(SpringLayout.SOUTH, taGame, -13, SpringLayout.NORTH, lblStatus);
		springLayout.putConstraint(SpringLayout.NORTH, lblStatus, 332, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStatus, 30, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblStatus, -31, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblStatus, -162, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(lblStatus);
	}
}
