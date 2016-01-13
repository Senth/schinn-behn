package se.lu.sysa11.schinnbehn.controller;

import se.lu.sysa11.schinnbehn.gui.Gui;
import se.lu.sysa11.schinnbehn.gui.Window;


public abstract class Controller<GuiType extends Gui<?>, RegisterType> {
	
	protected Window window;
	
	protected GuiType gui;
	
	protected RegisterType register;

	
	protected Controller(Window window, GuiType gui, RegisterType register) {
		this.window = window;
		this.gui = gui;
		this.register = register;
	}

	
	protected void onActivate(Object data) {
		// Does nothing
	}

	
	public void activate(Object data) {
		if (!gui.isInitialized()) {
			gui.initialize();
		}
		onActivate(data);
	}

	
	public Gui<?> getGui() {
		return gui;
	}
}
