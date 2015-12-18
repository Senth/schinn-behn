package se.lu.sysa11.schinnbehn.controller;

import se.lu.sysa11.schinnbehn.gui.OrderGui;
import se.lu.sysa11.schinnbehn.gui.Window;
import se.lu.sysa11.schinnbehn.model.OrderReg;

/**
 * The order controller
 */
public class OrderController extends Controller<OrderGui, OrderReg> {
	/**
	 * @param window the application window
	 * @param orderGui GUI/view of the product controller
	 * @param orderRegister Register/model of the product controller
	 */
	public OrderController(Window window, OrderGui orderGui, OrderReg orderRegister) {
		super(window, orderGui, orderRegister);
		orderGui.setController(this);
		// TODO Auto-generated constructor stub
	}

	// TODO
}
