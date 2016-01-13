package se.lu.sysa11.schinnbehn.gui;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


public abstract class Gui<ControllerType> {
	
	protected ControllerType controller;
	
	protected Window window;
	
	private boolean initialized = false;

	protected static final int LABEL_HEIGHT = 25;
	protected static final int LABEL_WIDTH = 144;
	protected static final int TEXTFIELD_HEIGHT = 25;
	protected static final int TEXTFIELD_WIDTH = 138;
	protected static final int BUTTON_HEIGHT = 25;
	protected static final int BUTTON_WIDTH = 138;
	protected static final int LEFT_COLUMN_1_POS = 12;
	protected static final int LEFT_COLUMN_2_POS = 158;
	protected static final int LEFT_COLUMN_3_POS = 304;

	public Gui(Window window) {
		this.window = window;
	}

	
	public abstract void initialize();

	
	public boolean isInitialized() {
		return initialized;
	}

	
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	
	public abstract JPanel getContent();

	
	public void setController(ControllerType controller) {
		this.controller = controller;
	}

	protected void clearTable(DefaultTableModel tableModel) {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
	}
}
