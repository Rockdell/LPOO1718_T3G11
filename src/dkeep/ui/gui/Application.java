package dkeep.ui.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.JList;

import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

import dkeep.engine.Game;
import dkeep.io.ApplicationIO;

public class Application {
	
	private SpringLayout 	_sprLayout;
	private JFrame 			_frame;
	private GraphicsMap 	_panel;
	private JButton 		_btnStartGame;
	private JButton 		_btnExitGame;	
	private JButton 		_btnUp;
	private JButton 		_btnDown;
	private JButton 		_btnLeft;
	private JButton 		_btnRight;
	private JLabel 			_lblNrOgres;
	private JLabel 			_lblGuardPersonality;
	private JLabel 			_lblStatus;
	private JLabel 			_lblLegend;
	private JSlider 		_sldNrOgres;
	private JComboBox 		_cbGuardPersonality;
	private JList 			_jlMapSelection;
	
	private boolean			_load = false;
	
	/** Create the application. */
	public Application() {
		initialize();
	}
	
	public void setLoad(boolean load) {
		_load = load;
		_cbGuardPersonality.setEnabled(false);
		_sldNrOgres.setEnabled(false);
		_jlMapSelection.setEnabled(false);
	}

	/** Initialize the contents of the frame. */
	private void initialize() {
		_initializeComponents();
		_initializeEventHandlers();
	}
	
	private void _initializeComponents() {
		
		_initFrame();
		
		_initStartGameButton();
		
		_initEnemyLabels();
		
		_initOgreSlider();
		
		_initGuardComboBox();
		
		_initPanel();
		
		_initButtons();
		
		_initStatusLabels();
		
		_initMapSelection();	
	}
	
	private void _initFrame() {
		
		_frame = new JFrame();
		_frame.setVisible(true);
		_frame.setBounds(100, 100, 600, 480);
		_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		_sprLayout = new SpringLayout();
		_frame.getContentPane().setLayout(_sprLayout);		
	}
	
	private void _initStartGameButton() {
		
		_btnStartGame = new JButton("Start Game");
		_sprLayout.putConstraint(SpringLayout.EAST, _btnStartGame, -63, SpringLayout.EAST, _frame.getContentPane());
		_frame.getContentPane().add(_btnStartGame);
	}
	
	private void _initEnemyLabels() {
		
		_lblNrOgres = new JLabel("Number of Ogres");
		_frame.getContentPane().add(_lblNrOgres);
		
		_lblGuardPersonality = new JLabel("Guard personality");
		_sprLayout.putConstraint(SpringLayout.NORTH, _lblNrOgres, 0, SpringLayout.NORTH, _lblGuardPersonality);
		_frame.getContentPane().add(_lblGuardPersonality);
	}

	private void _initOgreSlider() {
		
		_sldNrOgres = new JSlider();
		_sprLayout.putConstraint(SpringLayout.WEST, _sldNrOgres, -150, SpringLayout.EAST, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.EAST, _sldNrOgres, -80, SpringLayout.EAST, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.NORTH, _btnStartGame, 7, SpringLayout.SOUTH, _sldNrOgres);
		_sldNrOgres.setValue(1);
		_sprLayout.putConstraint(SpringLayout.EAST, _lblNrOgres, -6, SpringLayout.WEST, _sldNrOgres);
		_sprLayout.putConstraint(SpringLayout.SOUTH, _sldNrOgres, 76, SpringLayout.NORTH, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.NORTH, _sldNrOgres, 0, SpringLayout.NORTH, _frame.getContentPane());
		_sldNrOgres.setToolTipText("");
		_sldNrOgres.setPaintLabels(true);
		_sldNrOgres.setPaintTicks(true);
		_sldNrOgres.setMajorTickSpacing(1);
		_sldNrOgres.setMinimum(1);
		_sldNrOgres.setMaximum(5);
		_frame.getContentPane().add(_sldNrOgres);
	}
	
	private void _initGuardComboBox() {
		
		_cbGuardPersonality = new JComboBox();
		_sprLayout.putConstraint(SpringLayout.WEST, _lblGuardPersonality, -110, SpringLayout.WEST, _cbGuardPersonality);
		_sprLayout.putConstraint(SpringLayout.WEST, _cbGuardPersonality, -230, SpringLayout.EAST, _lblNrOgres);
		_sprLayout.putConstraint(SpringLayout.EAST, _cbGuardPersonality, -30, SpringLayout.WEST, _lblNrOgres);
		_sprLayout.putConstraint(SpringLayout.NORTH, _lblGuardPersonality, 3, SpringLayout.NORTH, _cbGuardPersonality);
		_sprLayout.putConstraint(SpringLayout.NORTH, _cbGuardPersonality, 17, SpringLayout.NORTH, _frame.getContentPane());
		_cbGuardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		_cbGuardPersonality.setSelectedIndex(0);
		_frame.getContentPane().add(_cbGuardPersonality);
	}
	
	private void _initPanel() {
		
		_panel = new GraphicsMap();
		_sprLayout.putConstraint(SpringLayout.NORTH, _panel, 10, SpringLayout.SOUTH, _cbGuardPersonality);
		_sprLayout.putConstraint(SpringLayout.WEST, _panel, 10, SpringLayout.WEST, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.SOUTH, _panel, -10, SpringLayout.SOUTH, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.EAST, _panel, -230, SpringLayout.EAST, _frame.getContentPane());
		_frame.getContentPane().add(_panel);
		SpringLayout sl_panel = new SpringLayout();
		_panel.setLayout(sl_panel);
		
		LinkStart.game = new Game(new ApplicationIO(_panel));
	}
	
	private void _initButtons() {
		
		_btnExitGame = new JButton("Return to Menu");
		_sprLayout.putConstraint(SpringLayout.WEST, _btnExitGame, -15, SpringLayout.WEST, _btnStartGame);
		_sprLayout.putConstraint(SpringLayout.EAST, _btnExitGame, -48, SpringLayout.EAST, _frame.getContentPane());
		_frame.getContentPane().add(_btnExitGame);
		
		_btnUp = new JButton("Up");
		_sprLayout.putConstraint(SpringLayout.NORTH, _btnUp, 125, SpringLayout.NORTH, _lblNrOgres);
		_sprLayout.putConstraint(SpringLayout.WEST, _btnUp, -141, SpringLayout.EAST, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.EAST, _btnUp, -80, SpringLayout.EAST, _frame.getContentPane());
		_btnUp.setEnabled(false);
		_frame.getContentPane().add(_btnUp);
		
		_btnLeft = new JButton("Left");
		_sprLayout.putConstraint(SpringLayout.NORTH, _btnLeft, 140, SpringLayout.SOUTH, _lblNrOgres);
		_sprLayout.putConstraint(SpringLayout.EAST, _btnLeft, -121, SpringLayout.EAST, _frame.getContentPane());
		_btnLeft.setEnabled(false);
		_frame.getContentPane().add(_btnLeft);
		
		_btnDown = new JButton("Down");
		_sprLayout.putConstraint(SpringLayout.NORTH, _btnExitGame, 130, SpringLayout.SOUTH, _btnDown);
		_sprLayout.putConstraint(SpringLayout.NORTH, _btnDown, 172, SpringLayout.SOUTH, _lblNrOgres);
		_sprLayout.putConstraint(SpringLayout.WEST, _btnDown, -141, SpringLayout.EAST, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.EAST, _btnDown, -80, SpringLayout.EAST, _frame.getContentPane());
		_btnDown.setEnabled(false);
		_frame.getContentPane().add(_btnDown);
		
		_btnRight = new JButton("Right");
		_sprLayout.putConstraint(SpringLayout.NORTH, _btnRight, 140, SpringLayout.SOUTH, _lblNrOgres);
		_sprLayout.putConstraint(SpringLayout.EAST, _btnRight, -39, SpringLayout.EAST, _frame.getContentPane());
		_btnRight.setEnabled(false);
		_frame.getContentPane().add(_btnRight);
	}
	
	private void _initStatusLabels() {
		
		_lblStatus = new JLabel("You can start a new game!");
		_sprLayout.putConstraint(SpringLayout.NORTH, _lblStatus, 15, SpringLayout.SOUTH, _btnExitGame);
		_sprLayout.putConstraint(SpringLayout.EAST, _lblStatus, -18, SpringLayout.EAST, _frame.getContentPane());
		_lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		_frame.getContentPane().add(_lblStatus);
		
		_lblLegend = new JLabel("Map Selection :");
		_sprLayout.putConstraint(SpringLayout.WEST, _lblLegend, -3, SpringLayout.WEST, _btnStartGame);
		_lblLegend.setFont(new Font("Tahoma", Font.PLAIN, 16));
		_frame.getContentPane().add(_lblLegend);
	}
	
	private void _initMapSelection() {
		
		_jlMapSelection = new JList(LinkStart.game.existentMaps().toArray());
		JScrollPane mapScroll = new JScrollPane(_jlMapSelection);
		_jlMapSelection.setSelectedIndex(0);
		_sprLayout.putConstraint(SpringLayout.SOUTH, _lblLegend, -6, SpringLayout.NORTH, mapScroll);
		_sprLayout.putConstraint(SpringLayout.NORTH, mapScroll, -90, SpringLayout.NORTH, _btnExitGame);
		_sprLayout.putConstraint(SpringLayout.WEST, mapScroll, -165, SpringLayout.EAST, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.SOUTH, mapScroll, -20, SpringLayout.NORTH, _btnExitGame);
		_sprLayout.putConstraint(SpringLayout.EAST, mapScroll, -55, SpringLayout.EAST, _frame.getContentPane());
		_jlMapSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		_frame.getContentPane().add(mapScroll);
	}
	
	private void _initializeEventHandlers() {
		
		_initMapSelectionHandlers();
		
		_initStartBtnHandlers();
		
		_initMenuBtnHandlers();
		
		_initUpBtnHandlers();
			
		_initDownBtnHandlers();
		
		_initLeftBtnHandlers();
		
		_initRigthBtnHandlers();

		_initResizeHandlers();

		_initPanelHandlers();
		
		_initCloseBtnHandlers();	
	}

	private void _initMapSelectionHandlers() {
		
		_jlMapSelection.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (_jlMapSelection.getSelectedIndex() > 1 ||_load) {
					_cbGuardPersonality.setEnabled(false);
					_sldNrOgres.setEnabled(false);
				} else {
					_cbGuardPersonality.setEnabled(true);
					_sldNrOgres.setEnabled(true);
				}
			}
		});
	}
	
	private void _initStartBtnHandlers() {
		
		_btnStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(!_btnStartGame.isEnabled() || _jlMapSelection.getSelectedIndex() == -1)
					return;
				
				//Update buttons
				_btnStartGame.setEnabled(false);
				_btnUp.setEnabled(true);
				_btnDown.setEnabled(true);
				_btnLeft.setEnabled(true);
				_btnRight.setEnabled(true);
				_panel.setEnabled(true);
				
				_sldNrOgres.setEnabled(false);
				_cbGuardPersonality.setEnabled(false);
				_jlMapSelection.setEnabled(false);
				
				//Update status
				_lblStatus.setText("Move Hero - Arrow Keys");

				try {
					if (_load)
					{
						LinkStart.game.load();
						LinkStart.game.getCurrentLevel().display();
					}
					else
						LinkStart.game.loadLevel(_jlMapSelection.getSelectedIndex() + 1, ((String) _cbGuardPersonality.getSelectedItem()), _sldNrOgres.getValue());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
	
				_panel.requestFocusInWindow();
				
				LinkStart.sound.loadPlayMusic();
			}
		});
	}

	private void _initMenuBtnHandlers() {
		
		_btnExitGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Update status
				_lblStatus.setText("Exiting program!");
			
				_frame.dispose();
				LinkStart.game.save();
				LinkStart.frame.setVisible(true);
				
				LinkStart.sound.loadMenuMusic();
			}
		});
	}
	
	private void _initUpBtnHandlers() {
		
		_btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(!_btnUp.isEnabled())
					return;
				
				//Update status
				_lblStatus.setText(LinkStart.game.getCurrentLevel().endgameSummary() + "up!");
				
				ApplicationIO.input = 'w';
				
				if(LinkStart.game.tick()) {
					_lblStatus.setText(LinkStart.game.getCurrentLevel().endgameSummary());
				}
				
				_panel.requestFocusInWindow();
			}
		});
	}
	
	private void _initDownBtnHandlers() {
		
		_btnDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(!_btnDown.isEnabled())
					return;
				
				//Update status
				_lblStatus.setText(LinkStart.game.getCurrentLevel().endgameSummary() + "down!");
				
				ApplicationIO.input = 's';
				
				if(LinkStart.game.tick()) {
					_lblStatus.setText(LinkStart.game.getCurrentLevel().endgameSummary());
				}
				
				_panel.requestFocusInWindow();
			}
		});
	}
	
	private void _initLeftBtnHandlers() {
		
		_btnLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(!_btnLeft.isEnabled())
					return;
				
				//Update status
				_lblStatus.setText(LinkStart.game.getCurrentLevel().endgameSummary() + "left!");
				
				ApplicationIO.input = 'a';
				
				if(LinkStart.game.tick()) {
					_lblStatus.setText(LinkStart.game.getCurrentLevel().endgameSummary());
				}
				
				_panel.requestFocusInWindow();
			}
		});
	}
	
	private void _initRigthBtnHandlers() {
		
		_btnRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(!_btnRight.isEnabled())
					return;
				
				//Update status
				_lblStatus.setText(LinkStart.game.getCurrentLevel().endgameSummary() + "right!");
				
				ApplicationIO.input = 'd';
				
				if(LinkStart.game.tick()) {
					_lblStatus.setText(LinkStart.game.getCurrentLevel().endgameSummary());
				}
				
				_panel.requestFocusInWindow();
			}
		});
	}
	
	private void _initResizeHandlers() {
		
		_frame.getContentPane().addHierarchyBoundsListener(new HierarchyBoundsListener() {

			@Override
			public void ancestorMoved(HierarchyEvent e) {}

			@Override
			public void ancestorResized(HierarchyEvent e) {
				if(LinkStart.game.getCurrentLevel() != null)
					_panel.loadAssets();
			}
		});
	}	
	
	private void _initPanelHandlers() {
		
		_panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (!_panel.isEnabled())
					return;

				switch (arg0.getKeyCode()) {
				case KeyEvent.VK_UP:
					_lblStatus.setText(LinkStart.game.getCurrentLevel().endgameSummary() + "up!");
					ApplicationIO.input = 'w';
					break;
				case KeyEvent.VK_DOWN:
					_lblStatus.setText(LinkStart.game.getCurrentLevel().endgameSummary() + "down!");
					ApplicationIO.input = 's';
					break;
				case KeyEvent.VK_LEFT:
					_lblStatus.setText(LinkStart.game.getCurrentLevel().endgameSummary() + "left!");
					ApplicationIO.input = 'a';
					break;
				case KeyEvent.VK_RIGHT:
					_lblStatus.setText(LinkStart.game.getCurrentLevel().endgameSummary() + "right!");
					ApplicationIO.input = 'd';
					break;
				default:
					
				}
				
				if(LinkStart.game.tick()) {
					_lblStatus.setText(LinkStart.game.getCurrentLevel().endgameSummary());
				}
			}
		});
	}
	
	private void _initCloseBtnHandlers() {
		
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