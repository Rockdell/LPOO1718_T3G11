package dkeep.ui.gui;

import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class EditionTable extends AbstractTableModel {

	private static ArrayList<String> custom_map = new ArrayList<String>();
	private static ArrayList<Integer> colIndexes = new ArrayList<Integer>();

	/**
	 * Create the table.
	 */
	public EditionTable() {
		
		//Testing
		custom_map.add("XXX");
		custom_map.add("X X");
		custom_map.add("XHX");
		
		//Must add column indexes
		for(int i = 0; i < 3; i++)
		{
			colIndexes.add(i);
		}
		System.out.println(colIndexes.size());
	}

	@Override
	public int getColumnCount() {
		if(custom_map.size() == 0)
			return 0;
		
		return custom_map.get(0).length();
	}

	@Override
	public int getRowCount() {
		return custom_map.size();
	}

	@Override
	public String getColumnName(int col) {
		return colIndexes.get(col).toString();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}
	 
	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return "" + custom_map.get(row).charAt(col);
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		
		char alter = ((String)value).charAt(0);
		
		StringBuilder tmp = new StringBuilder(custom_map.get(row));
		tmp.setCharAt(col, alter);
		custom_map.set(row, tmp.toString());
		fireTableCellUpdated(row, col);
	}

	//TODO ALWAYS ADDS ROW AT THE END FOR NOW
	public void addRow() {
		
		if(getRowCount() == 15)
			return;
		
		String addedString = "";
		
		for (int i = 0; i < custom_map.get(0).length(); i++) {
			addedString += " ";
		}
		
		custom_map.add(addedString);
		fireTableRowsInserted(custom_map.size() - 1, custom_map.size() - 1);
	}

	public void removeRow(int row) {
		
		if(getRowCount() == 3)
			return;
		
		custom_map.remove(row);
		fireTableRowsDeleted(row, row);
	}

	//TODO ALWAYS ADDS COLUMN AT THE END FOR NOW
	public void addColumn() {
		
		//Updates colIndexes
		colIndexes.add(new Integer(colIndexes.get(colIndexes.size() - 1) + 1));
		
		//Updates custom_map
		for(int i = 0; i < custom_map.size(); i++) {
			custom_map.set(i, custom_map.get(i) + " ");
		}
		
		fireTableStructureChanged();
	}
	
	//TODO ALWAYS REMOVES THE LAST COLUMN FOR NOW
	public void removeColumn() {
		
		//Updates custom_map
		for(int i = 0; i < custom_map.size(); i++) {
			custom_map.set(i, custom_map.get(i).substring(0, custom_map.get(i).length() - 1));
		}
		
		//Updates colIndexes
		colIndexes.remove(colIndexes.size() - 1);
		
		fireTableStructureChanged();
	}

}
