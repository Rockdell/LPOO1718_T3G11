package dkeep.ui.gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JButton;

public class LoadLevel {

	private static JFrame _frame;
	
	private JButton	_btnStartGame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadLevel window = new LoadLevel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoadLevel() {
		
		_initializeComponents();
		_initializeEventHandlers();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void _initializeComponents() {
		_frame = new JFrame();
		_frame.setVisible(true);
		_frame.setBounds(100, 100, 450, 300);
		_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		_frame.getContentPane().setLayout(springLayout);
		
		_btnStartGame = new JButton("Start Game");
		springLayout.putConstraint(SpringLayout.SOUTH, _btnStartGame, -10, SpringLayout.SOUTH, _frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, _btnStartGame, -10, SpringLayout.EAST, _frame.getContentPane());
		_frame.getContentPane().add(_btnStartGame);
		
		
		


	}
	
	private void _initializeEventHandlers() {

		// START BUTTON
		_btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!_btnStartGame.isEnabled() || _jlMapSelection.getSelectedIndex() == -1)
					return;

				// Update buttons
				_btnStartGame.setEnabled(false);

				try {
					LinkStart.game.loadLevel(_jlMapSelection.getSelectedIndex() + 1,((String) _cbGuardPersonality.getSelectedItem()), _sldNrOgres.getValue());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}

				_panel.requestFocusInWindow();
				// LinkStart.game.getCurrentLevel().display();
			}
		});
		
		// 'X' Close Button Handler
		_frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(_frame, "Are you sure to close this window?", "Exit?",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		
	}
}
