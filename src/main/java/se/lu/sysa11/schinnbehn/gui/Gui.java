package se.lu.sysa11.schinnbehn.gui;

import javax.swing.JPanel;

/**
 * @author Matteus Magnusson
 * @param <ControllerType> The Controller class
 */
public abstract class Gui<ControllerType> {
	/** Controller for the GUI class */
	protected ControllerType controller;

	protected static final int LABEL_HEIGHT = 25;
	protected static final int LABEL_WIDTH = 144;
	protected static final int TEXTFIELD_HEIGHT = 25;
	protected static final int TEXTFIELD_WIDTH = 138;
	protected static final int BUTTON_HEIGHT = 25;
	protected static final int BUTTON_WIDTH = 138;

	/**
	 * Initialize the GUI
	 */
	protected abstract void initialize();

	/**
	 * @return context for this GUI
	 */
	public abstract JPanel getContent();

	/**
	 * Set the controller for the GUI
	 * @param controller
	 */
	public void setController(ControllerType controller) {
		this.controller = controller;
	}
}
