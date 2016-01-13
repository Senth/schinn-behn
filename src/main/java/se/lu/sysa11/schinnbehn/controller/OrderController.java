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


public class OrderController extends Controller<OrderGui, OrderReg> {
	private ProductReg productRegister;
	private CustomerReg customerRegister;

	
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

	@Override
	protected void onActivate(Object data) {
		if (data instanceof String) {
			String orderNumber = (String) data;
			gui.setOrder(findOrder(orderNumber));

		} else if (data instanceof Customer) {
			Customer customer = (Customer) data;
			gui.setCustomer(customer);
		}
		gui.populateTable();
	}

	public Product findProduct(String searchString) {
		return productRegister.findProduct(searchString);
	}

	
	public List<Product> findProducts(String searchString) {
		return productRegister.findProducts(searchString);
	}

	
	public Customer findCustomer(String searchString) {
		Customer tmpCustomer = customerRegister.findCustomer(searchString);

		if (tmpCustomer == null) {
			window.showNotificationError("Finns ingen kund med kundnummer " + searchString + ".");
		} else {
			window.showNotificationSuccess("Kund med kundnummer " + searchString + " hittad.");
		}
		return tmpCustomer;

	}

	public void addOrder(Order order) {
		register.add(order);
	}
}