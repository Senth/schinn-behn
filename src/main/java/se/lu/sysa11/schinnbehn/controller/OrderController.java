package se.lu.sysa11.schinnbehn.controller;

import java.util.List;

import se.lu.sysa11.schinnbehn.gui.OrderGui;
import se.lu.sysa11.schinnbehn.gui.Window;
import se.lu.sysa11.schinnbehn.model.Customer;
import se.lu.sysa11.schinnbehn.model.CustomerReg;
import se.lu.sysa11.schinnbehn.model.Order;
import se.lu.sysa11.schinnbehn.model.OrderReg;
import se.lu.sysa11.schinnbehn.model.Product;
import se.lu.sysa11.schinnbehn.model.ProductReg;

/**
 * The order controller
 */
public class OrderController extends Controller<OrderGui, OrderReg> {
	private ProductReg productRegister;
	private CustomerReg customerRegister;

	/**
	 * @param window the application window
	 * @param orderGui GUI/view of the product controller
	 * @param orderRegister Register/model of the product controller
	 * @param productRegister for finding products
	 * @param customerRegister for finding customers
	 */
	public OrderController(Window window, OrderGui orderGui, OrderReg orderRegister, ProductReg productRegister, CustomerReg customerRegister) {
		super(window, orderGui, orderRegister);
		this.productRegister = productRegister;
		this.customerRegister = customerRegister;
		orderGui.setController(this);
	}

	public Order findOrder(String searchString) {

		Order tmpOrder = register.findOrder(searchString);
		if (tmpOrder == null) {
			window.showNotificationError("Hittade ingen order med ordernummer " + searchString + ".");
		} else {
			window.showNotificationSuccess("Order med ordernummer " + searchString + " hittad.");
		}
		return tmpOrder;
	}

	private boolean isInputValid(String orderNbr, String deliveryAddress, String customerNbr) {
		if (orderNbr == null || orderNbr.isEmpty()) {
			window.showNotificationError("Ogiltigt ordernr.");

			return false;
		}
		if (deliveryAddress == null || deliveryAddress.isEmpty()) {
			window.showNotificationError("Ogiltig leveransadress.");

			return false;
		}
		if (customerNbr == null || customerNbr.isEmpty()) {
			window.showNotificationError("Ogiltigt kundnr.");

			return false;
		}
		return true;
	}

	/**
	 * Find or rather filter products
	 * @param searchString
	 * @return the found products
	 */
	public List<Product> findProducts(String searchString) {
		return productRegister.findProducts(searchString);
	}

	/**
	 * Find a customer
	 * @param searchString
	 * @return found customer, null if not found
	 */
	public Customer findCustomer(String searchString) {
		return customerRegister.findCustomer(searchString);
	}
}