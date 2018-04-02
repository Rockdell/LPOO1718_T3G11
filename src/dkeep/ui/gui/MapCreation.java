package dkeep.ui.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SpringLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class MapCreation {

	private JFrame frame;
	private ButtonGroup buttonGroup  = new ButtonGroup();
	private static String toDraw = "X";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapCreation window = new MapCreation();
//					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MapCreation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		EditionTable model = new EditionTable();
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		JTable table = new JTable(model);
		springLayout.putConstraint(SpringLayout.WEST, table, 15, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, table, -303, SpringLayout.EAST, frame.getContentPane());

		// TODO ALTER LATER
		table.setRowHeight(30);
		// table.setFillsViewportHeight(true);
		table.setGridColor(Color.BLACK);
		table.setBackground(new Color(74, 156, 74)); // Same color as game's background
		table.setOpaque(true);
		springLayout.putConstraint(SpringLayout.NORTH, table, 15, SpringLayout.NORTH, frame.getContentPane());

		table.setDefaultRenderer(String.class, new ImageRenderer());

		frame.getContentPane().add(table);

		//JButtons
		JButton btnAddRow = new JButton("Add Row");
		springLayout.putConstraint(SpringLayout.EAST, btnAddRow, -46, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnAddRow);

		JButton btnRemoveRow = new JButton("Remove Row");
		springLayout.putConstraint(SpringLayout.WEST, btnAddRow, 0, SpringLayout.WEST, btnRemoveRow);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAddRow, -6, SpringLayout.NORTH, btnRemoveRow);
		springLayout.putConstraint(SpringLayout.EAST, btnRemoveRow, -46, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnRemoveRow);

		JButton btnAddColumn = new JButton("Add Column");
		springLayout.putConstraint(SpringLayout.WEST, btnRemoveRow, 0, SpringLayout.WEST, btnAddColumn);
		springLayout.putConstraint(SpringLayout.SOUTH, btnRemoveRow, -6, SpringLayout.NORTH, btnAddColumn);
		springLayout.putConstraint(SpringLayout.EAST, btnAddColumn, -46, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnAddColumn);
		
		JButton btnRemoveColumn = new JButton("Remove Column");
		springLayout.putConstraint(SpringLayout.SOUTH, btnRemoveColumn, -60, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnAddColumn, 0, SpringLayout.WEST, btnRemoveColumn);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAddColumn, -6, SpringLayout.NORTH, btnRemoveColumn);
		springLayout.putConstraint(SpringLayout.EAST, btnRemoveColumn, -46, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnRemoveColumn);
		
		//JRadioButtons
		JRadioButton rdbtnWall = new JRadioButton("Wall");
		springLayout.putConstraint(SpringLayout.WEST, rdbtnWall, 6, SpringLayout.EAST, table);
		rdbtnWall.setToolTipText("X");
		frame.getContentPane().add(rdbtnWall);
		
		JRadioButton rdbtnDoor = new JRadioButton("Door");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnWall, 0, SpringLayout.NORTH, rdbtnDoor);
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnDoor, 23, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, rdbtnDoor, -182, SpringLayout.EAST, frame.getContentPane());
		rdbtnDoor.setToolTipText("I");
		frame.getContentPane().add(rdbtnDoor);
		
		JRadioButton rdbtnKey = new JRadioButton("Key");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnKey, 23, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, rdbtnKey, -127, SpringLayout.EAST, frame.getContentPane());
		rdbtnKey.setToolTipText("k");
		springLayout.putConstraint(SpringLayout.WEST, rdbtnKey, 6, SpringLayout.EAST, rdbtnDoor);
		frame.getContentPane().add(rdbtnKey);
		
		JRadioButton rdbtnHeroWeapon = new JRadioButton("Hero + Weapon");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnHeroWeapon, 6, SpringLayout.SOUTH, rdbtnDoor);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnHeroWeapon, 0, SpringLayout.WEST, rdbtnDoor);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnHeroWeapon, -72, SpringLayout.EAST, btnAddRow);
		rdbtnHeroWeapon.setToolTipText("A");
		frame.getContentPane().add(rdbtnHeroWeapon);
		
		JRadioButton rdbtnOgre = new JRadioButton("Ogre");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnOgre, 23, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, rdbtnOgre, 6, SpringLayout.EAST, rdbtnKey);
		rdbtnOgre.setToolTipText("O");
		frame.getContentPane().add(rdbtnOgre);
		
		JRadioButton rdbtnEmpty = new JRadioButton("Empty");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnEmpty, 0, SpringLayout.NORTH, rdbtnHeroWeapon);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnEmpty, 194, SpringLayout.EAST, table);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnEmpty, 0, SpringLayout.EAST, btnAddRow);
		rdbtnEmpty.setToolTipText(" ");
		frame.getContentPane().add(rdbtnEmpty);
		
		JRadioButton rdbtnExit = new JRadioButton("Exit");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnExit, 6, SpringLayout.SOUTH, rdbtnWall);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnExit, 6, SpringLayout.EAST, table);
		rdbtnExit.setToolTipText("E");
		frame.getContentPane().add(rdbtnExit);

		rdbtnWall.setSelected(true);
		
		buttonGroup.add(rdbtnWall);
		buttonGroup.add(rdbtnDoor);
		buttonGroup.add(rdbtnKey);
		buttonGroup.add(rdbtnHeroWeapon);
		buttonGroup.add(rdbtnOgre);
		buttonGroup.add(rdbtnExit);
		buttonGroup.add(rdbtnEmpty);

		JButton btnDone = new JButton("Done");
		springLayout.putConstraint(SpringLayout.SOUTH, btnDone, -23, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnDone, -46, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnDone);
		
		JLabel lblWarning = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, lblWarning, 0, SpringLayout.WEST, btnAddRow);
		springLayout.putConstraint(SpringLayout.SOUTH, lblWarning, -36, SpringLayout.NORTH, btnAddRow);
		springLayout.putConstraint(SpringLayout.EAST, lblWarning, 0, SpringLayout.EAST, btnAddRow);
		frame.getContentPane().add(lblWarning);
		
		btnAddRow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!btnAddRow.isEnabled())
					return;

				// Adds an empty row to the end of the model
				model.addRow();
				
				// Set the view to show the new row
				int newRow = model.getRowCount() - 1;
				//table.editCellAt(newRow, 0);
				table.setRowSelectionInterval(newRow, newRow);
			}
		});

		btnRemoveRow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!btnRemoveRow.isEnabled())
					return;

				// TODO ALWAYS REMOVES THE FIRST ROW FOR NOW
				model.removeRow(0);

				// Set the view to show the new row
				int newRow = model.getRowCount() - 1;
				table.setRowSelectionInterval(newRow, newRow);
			}
		});
		
		btnAddColumn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!btnAddColumn.isEnabled())
					return;

				model.addColumn();

				// Set the view to show the new row
				int newColumn = model.getColumnCount() - 1;
				table.setColumnSelectionInterval(newColumn, newColumn);
			}
		});

		btnRemoveColumn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!btnRemoveColumn.isEnabled())
					return;

				model.removeColumn();

				// Set the view to show the new row
				int newColumn = model.getColumnCount() - 1;
				table.setColumnSelectionInterval(newColumn, newColumn);
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!table.isEnabled())
					return;

				int row = table.rowAtPoint(arg0.getPoint());
				int col = table.columnAtPoint(arg0.getPoint());

				if (row < 0 || row > table.getRowCount() || col < 0 || col > table.getColumnCount())
					return;

				for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
		            AbstractButton button = buttons.nextElement();

		            if (button.isSelected()) {
		                toDraw = button.getToolTipText();
		            }
		        }
				
				table.setValueAt(toDraw, row, col);
			}
		});

		// DONE BUTTON
		btnDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(!model.isAcceptable())
				{
					lblWarning.setText("Map NOT acceptable!");
					return;
				}
				
				ArrayList<String> tmp = model.getMap();	
				
				for(int i = 0; i < tmp.size(); i++) {
					System.out.println(tmp.get(i));
				}
				
				model.save(System.getProperty("user.dir") + "/src/miscellaneous/maps.txt");
				
				frame.dispose();
				
				LinkStart Restarting = new LinkStart();
			}
		});

		//'X' Close Button Handler
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(frame, "Are you sure to close this window?", "Created Map will be LOST!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
					frame.dispose();
			}
		});

	}
}
