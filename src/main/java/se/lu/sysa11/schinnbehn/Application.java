package se.lu.sysa11.schinnbehn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.spiddekauga.utils.Maths;

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
 * Main application of Schinn & Behn
 */
public class Application {
	private static final int CUSTOMERS = 50;
	private static final int ORDERS_PER_CUSTOMERS = 50;
	private static final int ORDER_LINES_PER_ORDER = 50;
	private static final int PRODUCTS = 500;
	private static final int PRODUCT_PRICE_MAX = 50;
	private static final double PRODUCT_WEIGHT_MAX = 50;
	private static final int QUANTITY_MAX = 100;

	/**
	 * Main class
	 * @param args not used
	 */
	public static void main(String[] args) {
		// Window
		Window window = new Window();

		// Customer
		CustomerGui customerGui = new CustomerGui();
		CustomerReg customerReg = new CustomerReg();
		CustomerController customerController = new CustomerController(window, customerGui, customerReg);
		window.addController(Views.CUSTOMER, customerController);

		// Product
		ProductGui productGui = new ProductGui();
		ProductReg productReg = new ProductReg();
		ProductController productController = new ProductController(window, productGui, productReg);
		window.addController(Views.PRODUCT, productController);

		// Order
		OrderGui orderGui = new OrderGui();
		OrderReg orderReg = new OrderReg();
		OrderController orderController = new OrderController(window, orderGui, orderReg, productReg, customerReg);
		window.addController(Views.ORDER, orderController);

		populateRegisters(customerReg, productReg, orderReg);

		window.switchTo(Views.CUSTOMER);
	}

	private static void populateRegisters(CustomerReg customerReg, ProductReg productReg, OrderReg orderReg) {
		LoremIpsum loremIpsum = new LoremIpsum();
		Random random = new Random(12);

		// Products
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < PRODUCTS; ++i) {
			Product product = new Product();
			product.setName(loremIpsum.words(2));
			product.setIngredients(loremIpsum.sentence());
			product.setPrice(random.nextInt(PRODUCT_PRICE_MAX) + 1);
			product.setCost(round(product.getPrice() * random.nextDouble()));
			product.setProductNbr(String.valueOf(i));
			product.setWeight(round(random.nextDouble() * PRODUCT_WEIGHT_MAX));
			productReg.add(product);
			products.add(product);
		}

		// Customers
		List<Customer> customers = new ArrayList<>();
		for (int customerIndex = 0; customerIndex < CUSTOMERS; ++customerIndex) {
			Customer customer = new Customer();
			customer.setEmail(loremIpsum.randomWord() + "." + loremIpsum.randomWord() + "@example.com");
			customer.setName(loremIpsum.randomWord());
			customer.setTelephoneNbr("000-000 00 00");
			customer.setAddress(loremIpsum.words(2));
			customerReg.add(customer);
			customers.add(customer);

			// Orders
			for (int orderIndex = 0; orderIndex < ORDERS_PER_CUSTOMERS; ++orderIndex) {
				Order order = new Order();
				order.setDeliveryAdress(loremIpsum.words(2));
				order.setMadeby(customer);
				customer.addOrder(order);

				// Create order lines
				for (int lineIndex = 0; lineIndex < ORDER_LINES_PER_ORDER; ++lineIndex) {
					OrderLine orderLine = new OrderLine();
					orderLine.setLineNbr(lineIndex + 1);
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

	/**
	 * Helper method for rounding double values
	 * @param value the value to round
	 * @return rounded to two decimals
	 */
	private static double round(double value) {
		return Maths.round(value, 2, BigDecimal.ROUND_HALF_UP);
	}
}
