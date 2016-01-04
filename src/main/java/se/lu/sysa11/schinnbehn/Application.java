package se.lu.sysa11.schinnbehn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import net._01001111.text.LoremIpsum;
import se.lu.sysa11.schinnbehn.controller.CustomerController;
import se.lu.sysa11.schinnbehn.controller.OrderController;
import se.lu.sysa11.schinnbehn.controller.ProductController;
import se.lu.sysa11.schinnbehn.gui.CustomerGui;
import se.lu.sysa11.schinnbehn.gui.OrderGui;
import se.lu.sysa11.schinnbehn.gui.ProductGui;
import se.lu.sysa11.schinnbehn.gui.Views;
import se.lu.sysa11.schinnbehn.gui.Window;
import se.lu.sysa11.schinnbehn.model.Customer;
import se.lu.sysa11.schinnbehn.model.CustomerReg;
import se.lu.sysa11.schinnbehn.model.Order;
import se.lu.sysa11.schinnbehn.model.OrderLine;
import se.lu.sysa11.schinnbehn.model.OrderReg;
import se.lu.sysa11.schinnbehn.model.Product;
import se.lu.sysa11.schinnbehn.model.ProductReg;

/**
 * @author Matteus Magnusson <matteus.magnusson@spiddekauga.com>
 */
public class Application {
	private static final int CUSTOMERS = 50;
	private static final int ORDERS_PER_CUSTOMERS = 50;
	private static final int ORDER_LINES_PER_ORDER = 50;
	private static final int PRODUCTS = 50;
	private static final int PRODUCT_PRICE_MAX = 5000;
	private static final double PRODUCT_WEIGHT_MAX = 50;
	private static final int QUANTITY_MAX = 1000;

	/**
	 * Main class
	 * @param args not used
	 */
	public static void main(String[] args) {
		// Window
		Window window = new Window();

		// Customer
		CustomerGui customerGui = new CustomerGui();
		customerGui.initialize();
		CustomerReg customerReg = new CustomerReg();
		new CustomerController(window, customerGui, customerReg);
		window.addGui(Views.CUSTOMER, customerGui);

		// Product
		ProductGui productGui = new ProductGui();
		productGui.initialize();
		ProductReg productReg = new ProductReg();
		new ProductController(window, productGui, productReg);
		window.addGui(Views.PRODUCT, productGui);

		// Order
		OrderGui orderGui = new OrderGui();
		orderGui.initialize();
		OrderReg orderReg = new OrderReg();
		new OrderController(window, orderGui, orderReg);
		window.addGui(Views.ORDER, orderGui);

		populateRegisters(customerReg, productReg, orderReg);

		window.switchTo(Views.CUSTOMER);
	}

	private static void populateRegisters(CustomerReg customerReg, ProductReg productReg, OrderReg orderReg) {
		LoremIpsum loremIpsum = new LoremIpsum();
		Random random = new Random();

		// Products
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < PRODUCTS; ++i) {
			Product product = new Product();
			product.setName(loremIpsum.words(2));
			product.setIngredients(loremIpsum.sentence());
			product.setPrice(random.nextInt(PRODUCT_PRICE_MAX) + 1);
			product.setProductNbr(UUID.randomUUID().toString());
			product.setWeight(random.nextDouble() * PRODUCT_WEIGHT_MAX);
			productReg.add(product);
			products.add(product);
		}

		// Customers
		List<Customer> customers = new ArrayList<>();
		for (int i = 0; i < CUSTOMERS; ++i) {
			Customer customer = new Customer();
			customer.setEmail(loremIpsum.randomWord() + "." + loremIpsum.randomWord() + "@example.com");
			customer.setName(loremIpsum.randomWord());
			customer.setTelephoneNbr("000-000 00 00");
			// TODO address
			customerReg.add(customer);
			customers.add(customer);
		}

		// Orders
		for (int i = 0; i < ORDERS_PER_CUSTOMERS; ++i) {
			Order order = new Order();
			order.setDeliveryAdress(loremIpsum.words(2));
			Customer customer = customers.get(random.nextInt(CUSTOMERS));
			order.setMadeby(customer);
			customer.addOrder(order);

			// Create order lines
			for (i = 0; i < ORDER_LINES_PER_ORDER; ++i) {
				OrderLine orderLine = new OrderLine();
				orderLine.setLineNbr(i + 1);
				orderLine.setOrder(order);
				order.addOrderLine(orderLine);
				Product product = products.get(random.nextInt(PRODUCTS));
				orderLine.setProduct(product);
				orderLine.setProductPrice(product.getPrice());
				orderLine.setQuantity(random.nextInt(QUANTITY_MAX + 1));
			}

			orderReg.add(order);
		}
	}
}
