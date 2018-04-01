package dkeep.ui.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class EditionTable extends AbstractTableModel {

	private static ArrayList<String> custom_map = new ArrayList<String>();
	private static ArrayList<Integer> colIndexes = new ArrayList<Integer>();
	private static boolean hero = false;
	private static boolean key = false;
	private static int ogre = 0;
	private static int exit = 0;
	
	public ArrayList<String> getMap() {
		return custom_map;
	}
	
	/**
	 * Create the table.
	 */
	public EditionTable() {
		
		//Preloaded Map
		custom_map.add("XXXXX");
		custom_map.add("X   X");
		custom_map.add("X   X");
		custom_map.add("X   X");
		custom_map.add("X   X");
		custom_map.add("XXXXX");
		
		//Must add column indexes
		for(int i = 0; i < custom_map.size(); i++)
		{
			colIndexes.add(i);
		}
	}
	
	public boolean isAcceptable() {
		return (wallsAround() && hero && key && ogre > 0 && exit > 0);
	}

	public boolean wallsAround() {
		boolean exit_on_side = false;
		
		for(int i = 0; i < getRowCount(); i++)
		{
			if(i == 0 || i == getRowCount() - 1)
			{
				for(int j = 1; j < getColumnCount() - 1; j++)
				{
					if(custom_map.get(i).charAt(j) != 'X')
					{
						if(custom_map.get(i).charAt(j) != 'E')
							return false;
						else
							exit_on_side = true;							
					}
				}
			}
			else
			{
				if (custom_map.get(i).charAt(0) != 'X' || custom_map.get(getColumnCount() - 1).charAt(0) != 'X') {
					if (custom_map.get(i).charAt(0) != 'E' || custom_map.get(getColumnCount() - 1).charAt(0) != 'E')
						return false;
					else
						exit_on_side = true;
				}
			}
		}

		return exit_on_side;
	}

	public boolean checkMapComponents(char c, int row, int col) {

		char deleting = custom_map.get(row).charAt(col);

		if (deleting == 'A') {
			hero = false;
		} else if (deleting == 'k') {
			key = false;
		} else if (deleting == 'O')
			ogre--;
		else if (deleting == 'E')
			exit--;

		if (c == 'A') {
			if (hero)
				return false;
			else
				hero = true;
		} else if (c == 'k') {
			if (key)
				return false;
			else
				key = true;
		} else if (c == 'O')
			ogre++;
		else if (c == 'E')
			exit++;

		return true;
	}

	public void save(String path) {

		int lastID = 0;

		//Searches for the ID of the last existing Map
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			for (String IDSearch; (IDSearch = br.readLine()) != null;) {
				
				if(IDSearch.length() <= 3)
					continue;
				
				if (IDSearch.contains("Map"))
					lastID = (int) Integer.parseInt(IDSearch.substring(3, IDSearch.length()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Saves the created map onto the file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {

			writer.append("\n");

			writer.append("Map" + (lastID + 1) + "\n");

			writer.append(getRowCount() + "-" + getColumnCount() + "\n");

			for (String str : custom_map) {
				writer.append(str + "\n");
			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

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
		return false;
	}

	@Override
	public Object getValueAt(int row, int col) {

		String path = System.getProperty("user.dir") + "/src/miscellaneous";

		switch (custom_map.get(row).charAt(col)) {
		case 'X':
			path += "/rock.png";
			break;
		case 'I':
			path += "/bush.png";
			break;
		case 'A':
			path += "/link_sword.jpg";
			break;
		case 'E':
			path += "/bush_exit.png";
			break;
		case 'k':
			path += "/key.png";
			break;
		case 'O':
			path += "/ganon.png";
			break;
		case ' ':
			if (col % 2 == 0 && row % 2 == 0)
				path += "/grass_bottom.png";
			else
				path += "/grass_TOP.png";
			break;
		default:
		}

		return path;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {

		char alter = ((String) value).charAt(0);

		if (checkMapComponents(alter, row, col)) {

			StringBuilder tmp = new StringBuilder(custom_map.get(row));
			tmp.setCharAt(col, alter);
			custom_map.set(row, tmp.toString());

			fireTableCellUpdated(row, col);
		}
	}

	//TODO ALWAYS ADDS ROW AT THE END FOR NOW
	public void addRow() {
		
		if(getRowCount() >= 15)
			return;
		
		String addedString = "";
		
		for (int i = 0; i < custom_map.get(0).length(); i++) {
			addedString += " ";
		}
		
		custom_map.add(addedString);
		fireTableRowsInserted(custom_map.size() - 1, custom_map.size() - 1);
	}

	public void removeRow(int row) {
		
		if(getRowCount() <= 3)
			return;
		
		custom_map.remove(row);
		fireTableRowsDeleted(row, row);
	}

	//TODO ALWAYS ADDS COLUMN AT THE END FOR NOW
	public void addColumn() {
		
		if(getColumnCount() >= 15)
			return;
		
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
		
		if(getColumnCount() <= 3)
			return;
		
		//Updates custom_map
		for(int i = 0; i < custom_map.size(); i++) {
			custom_map.set(i, custom_map.get(i).substring(0, custom_map.get(i).length() - 1));
		}
		
		//Updates colIndexes
		colIndexes.remove(colIndexes.size() - 1);
		
		fireTableStructureChanged();
	}

}
