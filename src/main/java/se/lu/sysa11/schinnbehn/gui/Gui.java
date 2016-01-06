package se.lu.sysa11.schinnbehn.gui;

import javax.swing.JPanel;

/**
 * @param <ControllerType>
 *            The Controller class
 */
public abstract class Gui<ControllerType> {
	/** Controller for the GUI class */
	protected ControllerType controller;
	protected Window window;
	/** if the GUI has been initialized */
	private boolean initialized = false;

	protected static final int LABEL_HEIGHT = 25;
	protected static final int LABEL_WIDTH = 144;
	protected static final int TEXTFIELD_HEIGHT = 25;
	protected static final int TEXTFIELD_WIDTH = 138;
	protected static final int BUTTON_HEIGHT = 25;
	protected static final int BUTTON_WIDTH = 138;

	public Gui(Window window) {
		this.window = window;
	}

	/**
	 * Initialize the GUI
	 */
	public abstract void initialize();

	/**
	 * @return true if initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * Set if the GUI has been initialized or not
	 * 
	 * @param initialized
	 *            set to true when the GUI has been initialized
	 */
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	/**
	 * @return context for this GUI
	 */
	public abstract JPanel getContent();

	/**
	 * Set the controller for the GUI
	 * 
	 * @param controller
	 */
	public void setController(ControllerType controller) {
		this.controller = controller;
	}
}
