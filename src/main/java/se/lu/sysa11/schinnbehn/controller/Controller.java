package se.lu.sysa11.schinnbehn.controller;

import se.lu.sysa11.schinnbehn.gui.Gui;
import se.lu.sysa11.schinnbehn.gui.Window;

/**
 * Base class for all controllers?
 * @param <GuiType> the GUI/View class
 * @param <RegisterType> the Register/Model class
 */
public abstract class Controller<GuiType extends Gui<?>, RegisterType> {
	/** Application Window */
	protected Window window;
	/** GUI/View */
	protected GuiType gui;
	/** Register/Model */
	protected RegisterType register;

	/**
	 * Constructor that takes the application window
	 * @param window application window
	 * @param gui GUI/view object for the controller
	 * @param register Register/model object for the controller
	 */
	protected Controller(Window window, GuiType gui, RegisterType register) {
		this.window = window;
		this.gui = gui;
		this.register = register;
	}

	/**
	 * Called when an controller is activated / switched to
	 * @param data the data that was passed to the controller
	 */
	protected void onActivate(Object data) {
		// Does nothing
	}

	/**
	 * Activate the controller
	 * @param data the data passed to the controller
	 */
	public void activate(Object data) {
		if (!gui.isInitialized()) {
			gui.initialize();
		}

		onActivate(data);
	}

	/**
	 * @return GUI of the controller
	 */
	public Gui<?> getGui() {
		return gui;
	}
}
