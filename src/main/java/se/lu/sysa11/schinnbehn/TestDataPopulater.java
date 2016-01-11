package se.lu.sysa11.schinnbehn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.spiddekauga.utils.Maths;

import net._01001111.text.LoremIpsum;
import se.lu.sysa11.schinnbehn.model.Customer;
import se.lu.sysa11.schinnbehn.model.CustomerReg;
import se.lu.sysa11.schinnbehn.model.Order;
import se.lu.sysa11.schinnbehn.model.OrderLine;
import se.lu.sysa11.schinnbehn.model.OrderReg;
import se.lu.sysa11.schinnbehn.model.Product;
import se.lu.sysa11.schinnbehn.model.ProductReg;

/**
 * Populate the program with test data
 */
public class TestDataPopulater {
	private static final int ORDERS_PER_CUSTOMERS = 50;
	private static final int PRODUCT_PRICE_MAX = 50;
	private static final double PRODUCT_WEIGHT_MAX = 50;
	private static final int QUANTITY_MAX = 100;
	private static final int INGREDIENTS_PER_PRODUCT = 5;

	private static final String[] COMPANY_NAMES = { "Ica Maxi Löddeköpinge", "IKEA Restauranger AB",
			"Söderlunds Delikatesser KB", "Västerbergs Kött AB", "Östermans Chark och pålägg AB",
			"Norrby Mat och Frukt" };

	private static final String[] PRODUCT_NAMES = { "Julskinka", "Kokt Julskinka av Whiskygris", "Kokt påskskinka",
			"Offerlamm", "Prinskorv", "Kungskorv (en sorts falukorv)", "Kalkonprinskorv", "Sylta", "Kalvsylta",
			"Rullsylta", "Kallsylta", "Slarvsylta", "Köttkorv", "Fläskkorv", "Kokt Köttkorv", "Hederlig Nötkorv",
			"Gammeldags Kokt Köttkorv", "Leverpastej", "Ingas Gräddleverpastej", "Karl-Orvars Leverpastej Original",
			"Knäckebrödhults Äkta Leverpastej", "Aladåb", "Fläskkorv", "Falsk Fläskkorv", "Leverkorv", "Vitlökskorv",
			"Hackkyckling", "Hackkorv", "Smulkorv", "Grynkorv", "Vegetarisk Grynkorv", "Julkorv", "Hjulkorv",
			"Petters hårdkokta Nyårskorv", "Blodkorv", "Lammhults Blodkorv", "Gammeldags Blodkorv", "Rödbetskorv",
			"Lökkorv", "Lommakorv", "Kryddskinka", "Smörgåsskinka Lyx", "Rostbiff", "Mariannelunds Rostbiff Original",
			"Rostbiff Original", "Rostbiff sous vide" };

	private static final String[] ADDRESSES = {

	};

	private static final String[] INGREDIENTS = { "salt", "peppar", "gÃ©le", "E302", "gurkmeja", "mejram" };

	private static final int ORDER_LINES_PER_ORDER = PRODUCT_NAMES.length / 2;

	/**
	 * Populate the program with test data
	 */
	public static void populateRegisters(CustomerReg customerReg, ProductReg productReg, OrderReg orderReg) {
		LoremIpsum loremIpsum = new LoremIpsum();
		Random random = new Random(12);

		// Products
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < PRODUCT_NAMES.length; ++i) {
			Product product = new Product();
			product.setName(PRODUCT_NAMES[i]);

			StringBuilder ingredients = new StringBuilder();
			HashSet<Integer> addedIngredients = new HashSet<>();
			for (int ingredientsCount = 0; ingredientsCount < INGREDIENTS_PER_PRODUCT; ++ingredientsCount) {
				int ingredientsIndex = -1;
				while (ingredientsIndex == -1) {
					ingredientsIndex = random.nextInt(INGREDIENTS.length);

					if (addedIngredients.contains(ingredientsIndex)) {
						ingredientsIndex = -1;
					} else {
						addedIngredients.add(ingredientsIndex);
					}
				}

				ingredients.append(INGREDIENTS[ingredientsIndex]);
			}
			product.setIngredients(ingredients.toString());
			product.setPrice(random.nextInt(PRODUCT_PRICE_MAX) + 1);
			product.setCost(round(product.getPrice() * random.nextDouble()));
			product.setProductNbr(String.valueOf(i));
			product.setWeight(round(random.nextDouble() * PRODUCT_WEIGHT_MAX));
			productReg.add(product);
			products.add(product);
		}

		// Customers
		List<Customer> customers = new ArrayList<>();
		for (int customerIndex = 0; customerIndex < COMPANY_NAMES.length; ++customerIndex) {
			Customer customer = new Customer();

			String name = COMPANY_NAMES[customerIndex];

			customer.setEmail("info@" + name.toLowerCase().replace(' ', '-') + ".se");
			customer.setName(name);

			// Telephone number
			String number = "076-";
			number += " ";
			number += " ";
			number += " ";
			customer.setTelephoneNbr(number);

			customer.setBillingAdress(loremIpsum.words(2));
			customerReg.add(customer);
			customers.add(customer);

			// Orders
			int orderCount = random.nextInt(ORDERS_PER_CUSTOMERS) + 1;
			for (int orderIndex = 0; orderIndex < ORDERS_PER_CUSTOMERS; ++orderIndex) {
				Order order = new Order();
				order.setDeliveryAdress(loremIpsum.words(2));
				order.setMadeby(customer);
				customer.addOrder(order);

				// Create order lines
				HashSet<String> addedProducts = new HashSet<>();
				int orderLineCount = random.nextInt(PRODUCT_NAMES.length) + 1;
				for (int lineIndex = 0; lineIndex < orderLineCount; ++lineIndex) {
					OrderLine orderLine = new OrderLine();
					orderLine.setOrder(order);
					order.addOrderLine(orderLine);
					Product product = null;
					while (product == null) {
						product = products.get(random.nextInt(PRODUCT_NAMES.length));
						if (!addedProducts.contains(product.getProductNbr())) {
							addedProducts.add(product.getProductNbr());
						} else {
							product = null;
						}
					}

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
