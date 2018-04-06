package dkeep.ui.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JLabel;

public class MapCreation {
	
	private SpringLayout 		_sprLayout;
	private JFrame 				_frame;
	private ButtonGroup 		_buttonGroup;
	private String 				_toDraw;
	private EditionTable 		_model;
	private JTable 				_table;
	private SpinnerNumberModel 	_m_numberSpinnerModel;
	private JSpinner			_m_numberSpinner;
	private JButton 			_btnAddRow;
	private JButton 			_btnRemoveRow;
	private JButton 			_btnAddColumn;
	private JButton 			_btnRemoveColumn;
	private JButton				_btnDone;
	private JButton				_btnReturn;
	private JLabel				_lblWarning;
	private JLabel			 	_lblLegend;
	private JRadioButton 		_rdbtnWall;
	private JRadioButton 		_rdbtnDoor;
	private JRadioButton 		_rdbtnKey;
	private JRadioButton 		_rdbtnHeroWeapon;
	private JRadioButton 		_rdbtnOgre;
	private JRadioButton 		_rdbtnEmpty;
	private JRadioButton 		_rdbtnExit;

	/** Create the application. */
	public MapCreation() {
		initialize();
	}

	/** Initialize the contents of the frame. */
	private void initialize() {
		
		_initializeComponents();
		_initializeEventHandlers();
	}
	
	/** Initialize the contents of the frame. */
	private void _initializeComponents() {
		
		_initFrame();
		
		_initModel();
		
		_initTable();
		
		_initSpinner();

		_initButtons();
		
		_initRadioButtons();
		
		_initButtonGroup();
		
		_initDoneReturnButtons();
		
		_initLabel();
	}
	
	private void _initFrame() {
		
		_frame = new JFrame();
		_frame.setVisible(true);
		_frame.setBounds(100, 100, 800, 550);
		_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		try {
			_frame.setIconImage(
					ImageIO.read(new File(System.getProperty("user.dir") + "/src/miscellaneous/LPOO_icon.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}
	
	private void _initModel() {
		
		_toDraw = "X";
		_model = new EditionTable();
		_sprLayout = new SpringLayout();
		_frame.getContentPane().setLayout(_sprLayout);
	}
	
	private void _initTable() {
		
		_table = new JTable(_model);
		_sprLayout.putConstraint(SpringLayout.WEST, _table, 15, SpringLayout.WEST, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.EAST, _table, -303, SpringLayout.EAST, _frame.getContentPane());
		
		_table.setRowHeight(30);
		_table.setGridColor(Color.BLACK);
		_table.setBackground(new Color(74, 156, 74)); // Same color as game's background
		_table.setOpaque(true);
		_sprLayout.putConstraint(SpringLayout.NORTH, _table, 15, SpringLayout.NORTH, _frame.getContentPane());
		_table.setDefaultRenderer(String.class, new ImageRenderer());

		_frame.getContentPane().add(_table);
	}

	private void _initSpinner() {

		_m_numberSpinnerModel = new SpinnerNumberModel(1, 1, 6, 1);
		_m_numberSpinner = new JSpinner(_m_numberSpinnerModel);
		_m_numberSpinner.setEditor(new JSpinner.DefaultEditor(_m_numberSpinner));
		
		_frame.getContentPane().add(_m_numberSpinner);
	}

	private void _initButtons() {
		
		_btnAddRow = new JButton("Add Row");
		_sprLayout.putConstraint(SpringLayout.SOUTH, _m_numberSpinner, -4, SpringLayout.NORTH, _btnAddRow);
		_sprLayout.putConstraint(SpringLayout.EAST, _m_numberSpinner, 0, SpringLayout.EAST, _btnAddRow);
		_sprLayout.putConstraint(SpringLayout.EAST, _btnAddRow, -46, SpringLayout.EAST, _frame.getContentPane());
		_frame.getContentPane().add(_btnAddRow);

		_btnRemoveRow = new JButton("Remove Row");
		_sprLayout.putConstraint(SpringLayout.WEST, _btnAddRow, 0, SpringLayout.WEST, _btnRemoveRow);
		_sprLayout.putConstraint(SpringLayout.SOUTH, _btnAddRow, -6, SpringLayout.NORTH, _btnRemoveRow);
		_sprLayout.putConstraint(SpringLayout.EAST, _btnRemoveRow, -46, SpringLayout.EAST, _frame.getContentPane());
		_frame.getContentPane().add(_btnRemoveRow);

		_btnAddColumn = new JButton("Add Column");
		_sprLayout.putConstraint(SpringLayout.WEST, _btnRemoveRow, 0, SpringLayout.WEST, _btnAddColumn);
		_sprLayout.putConstraint(SpringLayout.SOUTH, _btnRemoveRow, -6, SpringLayout.NORTH, _btnAddColumn);
		_sprLayout.putConstraint(SpringLayout.EAST, _btnAddColumn, -46, SpringLayout.EAST, _frame.getContentPane());
		_frame.getContentPane().add(_btnAddColumn);
		
		_btnRemoveColumn = new JButton("Remove Column");
		_sprLayout.putConstraint(SpringLayout.SOUTH, _btnRemoveColumn, -60, SpringLayout.SOUTH, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.WEST, _btnAddColumn, 0, SpringLayout.WEST, _btnRemoveColumn);
		_sprLayout.putConstraint(SpringLayout.SOUTH, _btnAddColumn, -6, SpringLayout.NORTH, _btnRemoveColumn);
		_sprLayout.putConstraint(SpringLayout.EAST, _btnRemoveColumn, -46, SpringLayout.EAST, _frame.getContentPane());
		_frame.getContentPane().add(_btnRemoveColumn);
	}

	private void _initRadioButtons() {
		
		_rdbtnWall = new JRadioButton("Wall");
		_sprLayout.putConstraint(SpringLayout.WEST, _rdbtnWall, 6, SpringLayout.EAST, _table);
		_rdbtnWall.setToolTipText("X");
		_frame.getContentPane().add(_rdbtnWall);
		
		_rdbtnDoor = new JRadioButton("Door");
		_sprLayout.putConstraint(SpringLayout.NORTH, _rdbtnWall, 0, SpringLayout.NORTH, _rdbtnDoor);
		_sprLayout.putConstraint(SpringLayout.NORTH, _rdbtnDoor, 23, SpringLayout.NORTH, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.EAST, _rdbtnDoor, -182, SpringLayout.EAST, _frame.getContentPane());
		_rdbtnDoor.setToolTipText("I");
		_frame.getContentPane().add(_rdbtnDoor);
		
		_rdbtnKey = new JRadioButton("Key");
		_sprLayout.putConstraint(SpringLayout.NORTH, _rdbtnKey, 23, SpringLayout.NORTH, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.EAST, _rdbtnKey, -127, SpringLayout.EAST, _frame.getContentPane());
		_rdbtnKey.setToolTipText("k");
		_sprLayout.putConstraint(SpringLayout.WEST, _rdbtnKey, 6, SpringLayout.EAST, _rdbtnDoor);
		_frame.getContentPane().add(_rdbtnKey);
		
		_rdbtnHeroWeapon = new JRadioButton("Hero + Weapon");
		_sprLayout.putConstraint(SpringLayout.NORTH, _rdbtnHeroWeapon, 6, SpringLayout.SOUTH, _rdbtnDoor);
		_sprLayout.putConstraint(SpringLayout.WEST, _rdbtnHeroWeapon, 0, SpringLayout.WEST, _rdbtnDoor);
		_sprLayout.putConstraint(SpringLayout.EAST, _rdbtnHeroWeapon, -72, SpringLayout.EAST, _btnAddRow);
		_rdbtnHeroWeapon.setToolTipText("A");
		_frame.getContentPane().add(_rdbtnHeroWeapon);
		
		_rdbtnOgre = new JRadioButton("Ogre");
		_sprLayout.putConstraint(SpringLayout.NORTH, _rdbtnOgre, 23, SpringLayout.NORTH, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.WEST, _rdbtnOgre, 6, SpringLayout.EAST, _rdbtnKey);
		_rdbtnOgre.setToolTipText("O");
		_frame.getContentPane().add(_rdbtnOgre);
		
		_rdbtnEmpty = new JRadioButton("Empty");
		_sprLayout.putConstraint(SpringLayout.NORTH, _rdbtnEmpty, 0, SpringLayout.NORTH, _rdbtnHeroWeapon);
		_sprLayout.putConstraint(SpringLayout.WEST, _rdbtnEmpty, 194, SpringLayout.EAST, _table);
		_sprLayout.putConstraint(SpringLayout.EAST, _rdbtnEmpty, 0, SpringLayout.EAST, _btnAddRow);
		_rdbtnEmpty.setToolTipText(" ");
		_frame.getContentPane().add(_rdbtnEmpty);
		
		_rdbtnExit = new JRadioButton("Exit");
		_sprLayout.putConstraint(SpringLayout.NORTH, _rdbtnExit, 6, SpringLayout.SOUTH, _rdbtnWall);
		_sprLayout.putConstraint(SpringLayout.WEST, _rdbtnExit, 6, SpringLayout.EAST, _table);
		_rdbtnExit.setToolTipText("E");
		_frame.getContentPane().add(_rdbtnExit);

		_rdbtnWall.setSelected(true);
	}
	
	private void _initButtonGroup() {
		
		_buttonGroup = new ButtonGroup();
		_buttonGroup.add(_rdbtnWall);
		_buttonGroup.add(_rdbtnDoor);
		_buttonGroup.add(_rdbtnKey);
		_buttonGroup.add(_rdbtnHeroWeapon);
		_buttonGroup.add(_rdbtnOgre);
		_buttonGroup.add(_rdbtnExit);
		_buttonGroup.add(_rdbtnEmpty);
	}
	
	private void _initDoneReturnButtons() {
		
		_btnDone = new JButton("Done");
		_sprLayout.putConstraint(SpringLayout.SOUTH, _btnDone, -23, SpringLayout.SOUTH, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.EAST, _btnDone, -46, SpringLayout.EAST, _frame.getContentPane());
		_frame.getContentPane().add(_btnDone);
		
		_btnReturn = new JButton("Return to Menu");
		_sprLayout.putConstraint(SpringLayout.SOUTH, _btnReturn, -23, SpringLayout.SOUTH, _frame.getContentPane());
		_sprLayout.putConstraint(SpringLayout.EAST, _btnReturn, -31, SpringLayout.WEST, _btnDone);
		_frame.getContentPane().add(_btnReturn);
	}
	
	private void _initLabel() {
		
		_lblWarning = new JLabel("");
		_sprLayout.putConstraint(SpringLayout.WEST, _lblWarning, 0, SpringLayout.WEST, _btnAddRow);
		_sprLayout.putConstraint(SpringLayout.SOUTH, _lblWarning, -20, SpringLayout.NORTH, _m_numberSpinner);
		_sprLayout.putConstraint(SpringLayout.EAST, _lblWarning, 0, SpringLayout.EAST, _btnAddRow);
		_frame.getContentPane().add(_lblWarning);
		
		_lblLegend = new JLabel("Row / Column:");
		_sprLayout.putConstraint(SpringLayout.NORTH, _lblLegend, 0, SpringLayout.NORTH, _m_numberSpinner);
		_sprLayout.putConstraint(SpringLayout.WEST, _lblLegend, 0, SpringLayout.WEST, _btnAddRow);
		_sprLayout.putConstraint(SpringLayout.SOUTH, _lblLegend, 0, SpringLayout.SOUTH, _m_numberSpinner);
		_sprLayout.putConstraint(SpringLayout.EAST, _lblLegend, -6, SpringLayout.WEST, _m_numberSpinner);
		_frame.getContentPane().add(_lblLegend);
	}
	
	private void _initializeEventHandlers() {
		
		_initAddRowBtnHandlers();

		_initRemRowBtnHandlers();
		
		_initAddColBtnHandlers();

		_initRemColBtnHandlers();

		_initTableHandlers();

		_initMenuBtnHandlers();

		_initDoneBtnHandlers();
		
		_initCloseBtnHandlers();
	}
	
	private void _initAddRowBtnHandlers() {
		
		_btnAddRow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!_btnAddRow.isEnabled())
					return;
				
				_lblWarning.setText("");

				_model.addRow((int)_m_numberSpinner.getValue());
				
				// Set the view to show the new row
				int newRow = _model.getRowCount() - 1;
				_table.setRowSelectionInterval(newRow, newRow);
				_m_numberSpinnerModel.setMaximum(Math.max(_table.getRowCount(),  _table.getColumnCount()));
				if((int)_m_numberSpinner.getValue() > (int)_m_numberSpinnerModel.getMaximum())
				{
					_m_numberSpinner.setValue(_m_numberSpinnerModel.getMaximum());	
					_m_numberSpinner.revalidate();
				}
			}
		});
	}
	
	private void _initRemRowBtnHandlers() {
		
		_btnRemoveRow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!_btnRemoveRow.isEnabled())
					return;
				
				_lblWarning.setText("");

				_model.removeRow((int)_m_numberSpinner.getValue());

				// Set the view to show the new row
				int newRow = _model.getRowCount() - 1;
				_table.setRowSelectionInterval(newRow, newRow);
				_m_numberSpinnerModel.setMaximum(Math.max(_table.getRowCount(),  _table.getColumnCount()));
				if((int)_m_numberSpinner.getValue() > (int)_m_numberSpinnerModel.getMaximum())
				{
					_m_numberSpinner.setValue(_m_numberSpinnerModel.getMaximum());	
					_m_numberSpinner.revalidate();
				}
			}
		});
	}
	
	private void _initAddColBtnHandlers() {
		
		_btnAddColumn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!_btnAddColumn.isEnabled())
					return;
				
				_lblWarning.setText("");

				_model.addColumn((int)_m_numberSpinner.getValue());

				// Set the view to show the new row
				int newColumn = _model.getColumnCount() - 1;
				_table.setColumnSelectionInterval(newColumn, newColumn);
				_m_numberSpinnerModel.setMaximum(Math.max(_table.getRowCount(),  _table.getColumnCount()));
				if((int)_m_numberSpinner.getValue() > (int)_m_numberSpinnerModel.getMaximum())
				{
					_m_numberSpinner.setValue(_m_numberSpinnerModel.getMaximum());	
					_m_numberSpinner.revalidate();
				}
			}
		});
	}
	
	private void _initRemColBtnHandlers() {
		
		_btnRemoveColumn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!_btnRemoveColumn.isEnabled())
					return;
				
				_lblWarning.setText("");

				_model.removeColumn((int)_m_numberSpinner.getValue());

				// Set the view to show the new row
				int newColumn = _model.getColumnCount() - 1;
				_table.setColumnSelectionInterval(newColumn, newColumn);
				_m_numberSpinnerModel.setMaximum(Math.max(_table.getRowCount(),  _table.getColumnCount()));
				if((int)_m_numberSpinner.getValue() > (int)_m_numberSpinnerModel.getMaximum())
				{
					_m_numberSpinner.setValue(_m_numberSpinnerModel.getMaximum());	
					_m_numberSpinner.revalidate();
				}
			}
		});
	}
	
	private void _initTableHandlers() {
		
		_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!_table.isEnabled())
					return;

				int row = _table.rowAtPoint(arg0.getPoint());
				int col = _table.columnAtPoint(arg0.getPoint());

				if (row < 0 || row > _table.getRowCount() || col < 0 || col > _table.getColumnCount())
					return;

				for (Enumeration<AbstractButton> buttons = _buttonGroup.getElements(); buttons.hasMoreElements();) {
		            AbstractButton button = buttons.nextElement();

		            if (button.isSelected()) {
		            	_toDraw = button.getToolTipText();
		            }
		        }
				
				_table.setValueAt(_toDraw, row, col);
				_lblWarning.setText("");
			}
		});
	}
	
	private void _initMenuBtnHandlers() {
		
		_btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				_frame.dispose();
				LinkStart.frame.setVisible(true);
			}
		});
	}
	
	private void _initDoneBtnHandlers() {
		
		_btnDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(!_model.isAcceptable())
				{
					_lblWarning.setText("Map NOT acceptable!");
					return;
				}
				
				_model.save(System.getProperty("user.dir") + "/src/miscellaneous/maps.txt");
				
				_frame.dispose();
				
				LinkStart.frame.setVisible(true);
			}
		});
	}
	
	private void _initCloseBtnHandlers() {
		
		_frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(_frame, "Are you sure to close this window?", "Created Map will be LOST!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
	}
}
