package se.lu.sysa11.schinnbehn.controller;

import java.util.ArrayList;
import java.util.List;

import se.lu.sysa11.schinnbehn.gui.OrderGui;
import se.lu.sysa11.schinnbehn.gui.Window;
import se.lu.sysa11.schinnbehn.model.Customer;
import se.lu.sysa11.schinnbehn.model.Order;
import se.lu.sysa11.schinnbehn.model.OrderReg;
import se.lu.sysa11.schinnbehn.model.Product;

/**
 * The order controller
 */
public class OrderController extends Controller<OrderGui, OrderReg> {
	/**
	 * @param window
	 *            the application window
	 * @param orderGui
	 *            GUI/view of the product controller
	 * @param orderRegister
	 *            Register/model of the product controller
	 */
	public OrderController(Window window, OrderGui orderGui, OrderReg orderRegister) {
		super(window, orderGui, orderRegister);
		orderGui.setController(this);
		// TODO Auto-generated constructor stub
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

	public List<Product> findProducts(String searchString) {
		return new ArrayList();
	}

	public Customer findCustomer(String searchString) {
		return null;
	}
}