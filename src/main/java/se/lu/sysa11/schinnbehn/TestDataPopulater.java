package se.lu.sysa11.schinnbehn;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.spiddekauga.utils.Maths;

import se.lu.sysa11.schinnbehn.model.Customer;
import se.lu.sysa11.schinnbehn.model.CustomerReg;
import se.lu.sysa11.schinnbehn.model.Order;
import se.lu.sysa11.schinnbehn.model.OrderLine;
import se.lu.sysa11.schinnbehn.model.OrderReg;
import se.lu.sysa11.schinnbehn.model.Product;
import se.lu.sysa11.schinnbehn.model.ProductReg;


public class TestDataPopulater {
	private static final int ORDERS_PER_CUSTOMERS = 50;
	private static final int PRODUCT_PRICE_MAX = 50;
	private static final double PRODUCT_WEIGHT_MAX = 50;
	private static final int QUANTITY_MAX = 100;
	private static final int INGREDIENTS_PER_PRODUCT = 5;
	private static final Date CURRENT_DATE = new Date();
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private static final String[] COMPANY_NAMES = { "Ica Maxi L\u00F6ddek\u00F6pinge", "IKEA Restauranger AB", "S\u00F6derlunds Delikatesser KB",
			"V\u00E4sterbergs K\u00F6tt AB", "\u00D6stermans Chark och p\u00E5l\u00E4gg AB", "Norrby Mat och Frukt" };

	private static final String[] PRODUCT_NAMES = { "Julskinka", "Kokt Julskinka av Whiskygris", "Kokt p\u00E5skskinka", "Offerlamm", "Prinskorv",
			"Kungskorv (en sorts falukorv)", "Kalkonprinskorv", "Sylta", "Kalvsylta", "Rullsylta", "Kallsylta", "Slarvsylta", "K\u00F6ttkorv",
			"Fl\u00E4skkorv", "Kokt K\u00F6ttkorv", "Hederlig N\u00F6tkorv", "Gammeldags Kokt K\u00F6ttkorv", "Leverpastej",
			"Ingas Gr\u00E4ddleverpastej", "Karl-Orvars Leverpastej Original", "Kn\u00E4ckebr\u00F6dhults \u00C4kta Leverpastej", "Alad\u00F6b",
			"Fl\u00E4skkorv", "Falsk Fl\u00E4skkorv", "Leverkorv", "Vitl\u00F6kskorv", "Hackkyckling", "Hackkorv", "Smulkorv", "Grynkorv",
			"Vegetarisk Grynkorv", "Julkorv", "Hjulkorv", "Petters h\u00E5rdkokta Ny\u00E5rskorv", "Blodkorv", "Lammhults Blodkorv",
			"Gammeldags Blodkorv", "R\u00F6dbetskorv", "L\u00F6kkorv", "Lommakorv", "Kryddskinka", "Sm\u00F6rg\u00E5sskinka Lyx", "Rostbiff",
			"Mariannelunds Rostbiff Original", "Rostbiff Original", "Rostbiff sous vide" };

	private static final String[] ADDRESSES = { "Malm\u00F6gatan 2, 25566 L\u00F6ddek\u00F6pinge", "Gyllenkroks All\u00E8 19, 22223 Lund",
			"Pilgatan 11, 33332 Sm\u00E5landsstenar", "Gyllenstj\u00E4rnasv\u00E4g 14A, 27354 Karlskrona", "Gatv\u00E4gen 7, 972 53 Lule\u00E5",
			"Marumsgatan 3, 532 32 Skara" };

	private static final String[] INGREDIENTS = { "salt", "peppar", "E302", "gurkmeja", "mejram", "druvsocker", "kummin", "E301", "maltodextrin",
			"vitl\u00F6k", "gelatin", "glukossirap" };

	
	public static void populateRegisters(CustomerReg customerReg, ProductReg productReg, OrderReg orderReg) {
		Random random = new Random(12);

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

				ingredients.append(INGREDIENTS[ingredientsIndex]).append(", ");
			}
			product.setIngredients(ingredients.toString());
			product.setPrice(random.nextInt(PRODUCT_PRICE_MAX) + 10);
			product.setCost(round(product.getPrice() * random.nextDouble()));
			product.setProductNbr(String.valueOf(i));
			product.setWeight(round(random.nextDouble() * PRODUCT_WEIGHT_MAX));
			productReg.add(product);
			products.add(product);
		}

		// Customers
		int addressIndex = 0;
		List<Customer> customers = new ArrayList<>();
		for (int customerIndex = 0; customerIndex < COMPANY_NAMES.length; ++customerIndex) {
			Customer customer = new Customer();

			String name = COMPANY_NAMES[customerIndex];

			customer.setEmail("info@" + name.toLowerCase().replace(' ', '-') + ".se");
			customer.setName(name);

			// Telephone number
			String number = "076-";
			number += (random.nextInt(899) + 100) + " ";
			number += (random.nextInt(89) + 10) + " ";
			number += (random.nextInt(89) + 10);
			customer.setTelephoneNbr(number);

			customer.setBillingAdress(ADDRESSES[addressIndex]);
			customerReg.add(customer);
			customers.add(customer);

			addressIndex++;
			if (addressIndex >= ADDRESSES.length) {
				addressIndex = 0;
			}

			// Orders
			int orderCount = random.nextInt(ORDERS_PER_CUSTOMERS) + 1;
			for (int orderIndex = 0; orderIndex < orderCount; ++orderIndex) {
				Order order = new Order();
				order.setDeliveryAdress(customer.getBillingadress());
				order.setMadeby(customer);

				long randomizedTime = (long) (random.nextDouble() * CURRENT_DATE.getTime());
				Date orderDate = new Date(randomizedTime);
				order.setOrderDate(DATE_FORMAT.format(orderDate));
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

	
	private static double round(double value) {
		return Maths.round(value, 2, BigDecimal.ROUND_HALF_UP);
	}
}
