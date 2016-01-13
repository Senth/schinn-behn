package se.lu.sysa11.schinnbehn.gui;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;


public abstract class TableClickListener extends MouseAdapter {
	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		Object source = mouseEvent.getSource();
		if (source instanceof JTable) {
			JTable table = (JTable) source;
			Point point = mouseEvent.getPoint();
			int row = table.rowAtPoint(point);
			if (row != -1) {
				int index = table.convertRowIndexToModel(row);
				if (mouseEvent.getClickCount() == 1) {
					onClick(table, index);
				} else if (mouseEvent.getClickCount() == 2) {
					onDoubleClick(table, index);
				}
			}
		}
	}

	
	public void onClick(JTable table, int row) {
		// Does nothing
	}

	
	public void onDoubleClick(JTable table, int row) {
		// Does nothing
	}
}
