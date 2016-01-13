package se.lu.sysa11.schinnbehn.gui;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.SwingUtilities;


public class TableCellListener implements PropertyChangeListener, Runnable {
	private JTable table;
	private Action action;

	private int row;
	private int column;
	private Object oldValue;
	private Object newValue;

	
	public TableCellListener(JTable table, Action action) {
		this.table = table;
		this.action = action;
		this.table.addPropertyChangeListener(this);
	}

	
	private TableCellListener(JTable table, int row, int column, Object oldValue, Object newValue) {
		this.table = table;
		this.row = row;
		this.column = column;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	
	public int getColumn() {
		return column;
	}

	
	public Object getNewValue() {
		return newValue;
	}

	
	public Object getOldValue() {
		return oldValue;
	}

	
	public int getRow() {
		return row;
	}

	
	public JTable getTable() {
		return table;
	}

	//
	// Implement the PropertyChangeListener interface
	//
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		// A cell has started/stopped editing

		if ("tableCellEditor".equals(e.getPropertyName())) {
			if (table.isEditing()) {
				processEditingStarted();
			} else {
				processEditingStopped();
			}
		}
	}

	
	private void processEditingStarted() {
		// The invokeLater is necessary because the editing row and editing
		// column of the table have not been set when the "tableCellEditor"
		// PropertyChangeEvent is fired.
		// This results in the "run" method being invoked

		SwingUtilities.invokeLater(this);
	}

	
	@Override
	public void run() {
		row = table.convertRowIndexToModel(table.getEditingRow());
		column = table.convertColumnIndexToModel(table.getEditingColumn());
		oldValue = table.getModel().getValueAt(row, column);
		newValue = null;
	}

	
	private void processEditingStopped() {
		newValue = table.getModel().getValueAt(row, column);

		// The data has changed, invoke the supplied Action
		if (newValue != oldValue || !newValue.equals(oldValue)) {
			// Make a copy of the data in case another cell starts editing
			// while processing this change

			TableCellListener tcl = new TableCellListener(getTable(), getRow(), getColumn(), getOldValue(), getNewValue());

			ActionEvent event = new ActionEvent(tcl, ActionEvent.ACTION_PERFORMED, "");
			action.actionPerformed(event);
		}
	}
}