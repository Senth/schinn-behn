package se.lu.sysa11.schinnbehn;

import se.lu.sysa11.schinnbehn.controller.CustomerController;
import se.lu.sysa11.schinnbehn.controller.OrderController;
import se.lu.sysa11.schinnbehn.controller.ProductController;
import se.lu.sysa11.schinnbehn.gui.CustomerGui;
import se.lu.sysa11.schinnbehn.gui.OrderGui;
import se.lu.sysa11.schinnbehn.gui.ProductGui;
import se.lu.sysa11.schinnbehn.gui.Views;
import se.lu.sysa11.schinnbehn.gui.Window;
import se.lu.sysa11.schinnbehn.model.CustomerReg;
import se.lu.sysa11.schinnbehn.model.OrderReg;
import se.lu.sysa11.schinnbehn.model.ProductReg;

/**
 * Main application of Schinn & Behn
 */
public class Application {
	/**
	 * Main class
	 *
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		// Window
		Window window = new Window();

		// Customer
		CustomerGui customerGui = new CustomerGui(window);
		CustomerReg customerReg = new CustomerReg();
		CustomerController customerController = new CustomerController(window, customerGui, customerReg);
		window.addController(Views.CUSTOMER, customerController);

		// Product
		ProductGui productGui = new ProductGui(window);
		ProductReg productReg = new ProductReg();
		ProductController productController = new ProductController(window, productGui, productReg);
		window.addController(Views.PRODUCT, productController);

		// Order
		OrderGui orderGui = new OrderGui(window);
		OrderReg orderReg = new OrderReg();
		OrderController orderController = new OrderController(window, orderGui, orderReg, productReg, customerReg);
		window.addController(Views.ORDER, orderController);

		TestDataPopulater.populateRegisters(customerReg, productReg, orderReg);

		window.switchTo(Views.CUSTOMER);
	}
}
