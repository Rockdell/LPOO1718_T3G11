package dkeep.ui.gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MapCreation {

	private JFrame frame;

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
		springLayout.putConstraint(SpringLayout.WEST, table, 67, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, 379, SpringLayout.NORTH, frame.getContentPane());
		frame.getContentPane().add(table);

		JButton btnAddRow = new JButton("Add Row");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddRow, 82, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnAddRow, 94, SpringLayout.EAST, table);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAddRow, 119, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnAddRow, -69, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnAddRow);

		JButton btnRemoveRow = new JButton("Remove Row");
		springLayout.putConstraint(SpringLayout.NORTH, btnRemoveRow, 20, SpringLayout.SOUTH, btnAddRow);
		springLayout.putConstraint(SpringLayout.WEST, btnRemoveRow, 94, SpringLayout.EAST, table);
		springLayout.putConstraint(SpringLayout.SOUTH, btnRemoveRow, 57, SpringLayout.SOUTH, btnAddRow);
		springLayout.putConstraint(SpringLayout.EAST, btnRemoveRow, -69, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnRemoveRow);

		btnAddRow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!btnAddRow.isEnabled())
					return;

				// Adds an empty row to the end of the model
				model.addRow();

				// Set the view to show the new row
				int newRow = model.getRowCount() - 1;
				table.editCellAt(newRow, 0);
				table.setRowSelectionInterval(newRow, newRow);
			}
		});
		
		btnRemoveRow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!btnRemoveRow.isEnabled())
					return;

				// R
				model.removeRow(0);

				// Set the view to show the new row
				int newRow = model.getRowCount() - 1;
				table.setRowSelectionInterval(newRow, newRow);
			}
		});

	}

}
