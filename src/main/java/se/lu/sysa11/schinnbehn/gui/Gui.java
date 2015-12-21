package se.lu.sysa11.schinnbehn.gui;

import javax.swing.JPanel;

/**
 * @author Matteus Magnusson
 * @param <ControllerType> The Controller class
 */
public abstract class Gui<ControllerType> {
	/** Controller for the GUI class */
	protected ControllerType controller;

	/**
	 * Initialize the GUI
	 */
	public abstract void initialize();

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
