package dkeep.ui.gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

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
	public MapCreation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 580, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		EditionTable model = new EditionTable();
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		JTable table = new JTable(model);
		springLayout.putConstraint(SpringLayout.NORTH, table, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, table, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, 379, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, table, -278, SpringLayout.EAST, frame.getContentPane());
		
		// TODO ALTER LATER
		table.setRowHeight(30);

		table.setDefaultRenderer(String.class, new ImageRenderer());
		frame.getContentPane().add(table);

		//JButtons
		JButton btnAddRow = new JButton("Add Row");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddRow, 165, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnAddRow, -46, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnAddRow);

		JButton btnRemoveRow = new JButton("Remove Row");
		springLayout.putConstraint(SpringLayout.NORTH, btnRemoveRow, 224, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnRemoveRow, 386, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnRemoveRow, -172, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnRemoveRow, -46, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnAddRow, 0, SpringLayout.WEST, btnRemoveRow);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAddRow, -22, SpringLayout.NORTH, btnRemoveRow);
		frame.getContentPane().add(btnRemoveRow);

		JButton btnAddColumn = new JButton("Add Column");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddColumn, 22, SpringLayout.SOUTH, btnRemoveRow);
		springLayout.putConstraint(SpringLayout.WEST, btnAddColumn, 102, SpringLayout.EAST, table);
		springLayout.putConstraint(SpringLayout.EAST, btnAddColumn, 0, SpringLayout.EAST, btnAddRow);
		frame.getContentPane().add(btnAddColumn);
		
		JButton btnRemoveColumn = new JButton("Remove Column");
		springLayout.putConstraint(SpringLayout.SOUTH, btnAddColumn, -22, SpringLayout.NORTH, btnRemoveColumn);
		springLayout.putConstraint(SpringLayout.NORTH, btnRemoveColumn, 342, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnRemoveColumn, 102, SpringLayout.EAST, table);
		springLayout.putConstraint(SpringLayout.SOUTH, btnRemoveColumn, 0, SpringLayout.SOUTH, table);
		springLayout.putConstraint(SpringLayout.EAST, btnRemoveColumn, 0, SpringLayout.EAST, btnAddRow);
		frame.getContentPane().add(btnRemoveColumn);
		
		//JRadioButtons
		JRadioButton rdbtnWall = new JRadioButton("Wall");
		rdbtnWall.setToolTipText("X");
		frame.getContentPane().add(rdbtnWall);
		
		JRadioButton rdbtnDoor = new JRadioButton("Door");
		rdbtnDoor.setToolTipText("I");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnWall, 0, SpringLayout.NORTH, rdbtnDoor);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnWall, -6, SpringLayout.WEST, rdbtnDoor);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnDoor, 361, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnDoor, 10, SpringLayout.NORTH, frame.getContentPane());
		frame.getContentPane().add(rdbtnDoor);
		
		JRadioButton rdbtnKey = new JRadioButton("Key");
		rdbtnKey.setToolTipText("k");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnKey, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, rdbtnKey, 6, SpringLayout.EAST, rdbtnDoor);
		frame.getContentPane().add(rdbtnKey);
		
		JRadioButton rdbtnHero = new JRadioButton("Guard");
		rdbtnHero.setToolTipText("G");
		springLayout.putConstraint(SpringLayout.WEST, rdbtnHero, 6, SpringLayout.EAST, rdbtnKey);
		frame.getContentPane().add(rdbtnHero);
		
		JRadioButton rdbtnGuard = new JRadioButton("Hero");
		rdbtnGuard.setToolTipText("H");
		frame.getContentPane().add(rdbtnGuard);
		
		JRadioButton rdbtnHeroWeapon = new JRadioButton("Hero + Weapon");
		rdbtnHeroWeapon.setToolTipText("A");
		springLayout.putConstraint(SpringLayout.SOUTH, rdbtnHero, -6, SpringLayout.NORTH, rdbtnHeroWeapon);
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnGuard, 0, SpringLayout.NORTH, rdbtnHeroWeapon);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnGuard, -4, SpringLayout.WEST, rdbtnHeroWeapon);
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnHeroWeapon, 6, SpringLayout.SOUTH, rdbtnDoor);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnHeroWeapon, 0, SpringLayout.WEST, rdbtnDoor);
		frame.getContentPane().add(rdbtnHeroWeapon);
		
		JRadioButton rdbtnOgre = new JRadioButton("Ogre");
		rdbtnOgre.setToolTipText("O");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnOgre, 41, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, rdbtnOgre, 0, SpringLayout.EAST, rdbtnHero);
		frame.getContentPane().add(rdbtnOgre);
		
		JRadioButton rdbtnEmpty = new JRadioButton("Empty");
		rdbtnEmpty.setToolTipText(" ");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnEmpty, 6, SpringLayout.SOUTH, rdbtnGuard);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnEmpty, 0, SpringLayout.WEST, rdbtnWall);
		frame.getContentPane().add(rdbtnEmpty);

		rdbtnWall.setSelected(true);
		
		buttonGroup.add(rdbtnWall);
		buttonGroup.add(rdbtnDoor);
		buttonGroup.add(rdbtnKey);
		buttonGroup.add(rdbtnHero);
		buttonGroup.add(rdbtnGuard);
		buttonGroup.add(rdbtnHeroWeapon);
		buttonGroup.add(rdbtnOgre);
		buttonGroup.add(rdbtnEmpty);
		
		JButton btnDone = new JButton("Done");
		springLayout.putConstraint(SpringLayout.NORTH, btnDone, 6, SpringLayout.SOUTH, btnRemoveColumn);
		springLayout.putConstraint(SpringLayout.EAST, btnDone, 0, SpringLayout.EAST, btnAddRow);
		frame.getContentPane().add(btnDone);
		
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

				ArrayList<String> tmp = model.getMap();	
				
				for(int i = 0; i < tmp.size(); i++) {
					System.out.println(tmp.get(i));
				}
				
				System.exit(0);
			}
		});

	}
}
