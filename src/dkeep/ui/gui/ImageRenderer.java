package dkeep.ui.gui;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer extends DefaultTableCellRenderer {

	/** Create the panel. */
	public ImageRenderer() {}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		JLabel lbl = ((JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column));

		Image im = null;
		
		try {
			im = (ImageIO.read(new File((String) value))).getScaledInstance(30, 30, Image.SCALE_FAST);
		} catch (IOException e) {
			e.printStackTrace();
		}

		lbl = new JLabel(new ImageIcon(im));
		
		return lbl;
	}
}
