package dkeep.ui.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class EditionTable extends AbstractTableModel {

	private ArrayList<String> 	_custom_map;
	private ArrayList<Integer>	_colIndexes;
	private boolean 			_hero;
	private boolean 			_key;
	private int 				_ogre;
	private int 				_exit;
	
	/** Create the table. */
	public EditionTable() {
		
		_custom_map = new ArrayList<String>();
		_colIndexes = new ArrayList<Integer>();
		_hero = false;
		_key = false;
		_ogre = 0;
		_exit = 0;
		
		//Preloaded Map
		_custom_map.add("XXXXX");
		_custom_map.add("X   X");
		_custom_map.add("X   X");
		_custom_map.add("X   X");
		_custom_map.add("X   X");
		_custom_map.add("XXXXX");
		
		//Must add column indexes
		for(int i = 0; i < _custom_map.size(); i++) {
			_colIndexes.add(i);
		}
	}
	
	public ArrayList<String> getMap() {
		return _custom_map;
	}
	
	public boolean isAcceptable() {
		return (wallsAround() && _hero && _key && _ogre > 0 && _exit > 0);
	}

	public boolean wallsAround() {
		boolean exit_on_side = false;
		
		for(int i = 0; i < getRowCount(); i++)
		{
			if(i == 0 || i == getRowCount() - 1)
			{
				for(int j = 0; j < getColumnCount(); j++)
				{
					if(_custom_map.get(i).charAt(j) != 'X')
					{
						if(_custom_map.get(i).charAt(j) != 'E')
							return false;
						else
						{
							if(j != 1 && j != getColumnCount() - 1)
							exit_on_side = true;	
						}
					}
				}
			}
			else
			{
				if (_custom_map.get(i).charAt(0) != 'X') {
					if (_custom_map.get(i).charAt(0) != 'E')
						return false;
					else
						exit_on_side = true;
				}

				if (_custom_map.get(i).charAt(getColumnCount() - 1) != 'X') {
					if (_custom_map.get(i).charAt(getColumnCount() - 1) != 'E')
						return false;
					else
						exit_on_side = true;
				}
			}
		}

		return exit_on_side;
	}

	public boolean checkMapComponents(char c, int row, int col) {

		char deleting = _custom_map.get(row).charAt(col);

		if (deleting == 'A') {
			_hero = false;
		} else if (deleting == 'k') {
			_key = false;
		} else if (deleting == 'O')
			_ogre--;
		else if (deleting == 'E')
			_exit--;

		if (c == 'A') {
			if (_hero)
				return false;
			else
				_hero = true;
		} else if (c == 'k') {
			if (_key)
				return false;
			else
				_key = true;
		} else if (c == 'O')
			_ogre++;
		else if (c == 'E')
			_exit++;

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

			for (String str : _custom_map) {
				writer.append(str + "\n");
			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int getColumnCount() {
		if(_custom_map.size() == 0)
			return 0;
		
		return _custom_map.get(0).length();
	}

	@Override
	public int getRowCount() {
		return _custom_map.size();
	}

	@Override
	public String getColumnName(int col) {
		return _colIndexes.get(col).toString();
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

		switch (_custom_map.get(row).charAt(col)) {
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

			StringBuilder tmp = new StringBuilder(_custom_map.get(row));
			tmp.setCharAt(col, alter);
			_custom_map.set(row, tmp.toString());

			fireTableCellUpdated(row, col);
		}
	}

	//TODO ALWAYS ADDS ROW AT THE END FOR NOW
	public void addRow() {
		
		if(getRowCount() >= 15)
			return;
		
		String addedString = "";
		
		for (int i = 0; i < _custom_map.get(0).length(); i++) {
			addedString += " ";
		}
		
		_custom_map.add(addedString);
		fireTableRowsInserted(_custom_map.size() - 1, _custom_map.size() - 1);
	}

	public void removeRow(int row) {
		
		if(getRowCount() <= 3)
			return;
		
		_custom_map.remove(row);
		fireTableRowsDeleted(row, row);
	}

	//TODO ALWAYS ADDS COLUMN AT THE END FOR NOW
	public void addColumn() {
		
		if(getColumnCount() >= 15)
			return;
		
		//Updates colIndexes
		_colIndexes.add(new Integer(_colIndexes.get(_colIndexes.size() - 1) + 1));
		
		//Updates custom_map
		for(int i = 0; i < _custom_map.size(); i++) {
			_custom_map.set(i, _custom_map.get(i) + " ");
		}
		
		fireTableStructureChanged();
	}
	
	//TODO ALWAYS REMOVES THE LAST COLUMN FOR NOW
	public void removeColumn() {
		
		if(getColumnCount() <= 3)
			return;
		
		//Updates custom_map
		for(int i = 0; i < _custom_map.size(); i++) {
			_custom_map.set(i, _custom_map.get(i).substring(0, _custom_map.get(i).length() - 1));
		}
		
		//Updates colIndexes
		_colIndexes.remove(_colIndexes.size() - 1);
		
		fireTableStructureChanged();
	}
}
