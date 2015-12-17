package se.lu.sysa11.schinnbehn.gui;

import javax.swing.JPanel;

/**
 * @author senth
 */
public abstract class Gui {
	protected JPanel panel = new JPanel();

	/**
	 * Initialize the GUI
	 */
	protected abstract void initialize();

	/**
	 * @return context for this GUI
	 */
	public JPanel getContext() {
		return panel;
	}
}
