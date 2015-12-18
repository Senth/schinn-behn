package se.lu.sysa11.schinnbehn.controller;

import java.util.ArrayList;

import se.lu.sysa11.schinnbehn.gui.ProductGui;
import se.lu.sysa11.schinnbehn.gui.Window;
import se.lu.sysa11.schinnbehn.model.Product;
import se.lu.sysa11.schinnbehn.model.ProductReg;

/**
 * Production controller
 */
public class ProductController extends Controller<ProductGui, ProductReg> {
	/**
	 * @param window the application window
	 * @param productGui GUI/view of the product controller
	 * @param productReg Register/model of the product controller
	 */
	public ProductController(Window window, ProductGui productGui, ProductReg productReg) {
		super(window, productGui, productReg);
		gui.setController(this);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Add a new product
	 * @param productNbr number/serial of the product
	 * @param name product name
	 * @param price current price of the product
	 * @param ingredients ingredients of the product
	 * @param weight weight of the product
	 * @param cost how much it costs to buy or produce the product
	 * @return true if successfully added product
	 */
	public boolean addProduct(String productNbr, String name, double price, String ingredients, double weight, double cost) {
		boolean successful = false;

		if (!isInputValid(productNbr, name, price, ingredients, weight, cost)) {
			return false;
		}


		// Add product
		Product product = new Product();
		product.setProductNbr(productNbr);
		product.setName(name);
		product.setPrice(price);
		product.setIngredients(ingredients);
		product.setWeight(weight);
		product.setCost(cost);
		register.add(product);

		// send notification
		window.showNotificationSuccess("Produkt tillagd");

		return successful;
	}

	/**
	 * Update an existing product
	 * @param productNbr number/serial of the product
	 * @param name product name
	 * @param price current price of the product
	 * @param ingredients ingredients of the product
	 * @param weight weight of the product
	 * @param cost how much it costs to buy or produce the product
	 * @return true if successfully added product
	 */
	public boolean updateProduct(String productNbr, String name, double price, String ingredients, double weight, double cost) {
		// check if valid
		if (!isInputValid(productNbr, name, price, ingredients, weight, cost)) {
			return false;
		}
		// Update product
		Product product = register.findProduct(productNbr);
		if (product != null) {
			product.setProductNbr(productNbr);
			product.setName(name);
			product.setPrice(price);
			product.setIngredients(ingredients);
			product.setWeight(weight);
			product.setCost(cost);
		}

		// send notification
		window.showNotificationSuccess("Produkt uppdaterad");

		return true;
	}

	/**
	 * Tests if input is valid
	 */
	private boolean isInputValid(String productNbr, String name, double price, String ingredients, double weight, double cost) {
		if (productNbr == null || productNbr.isEmpty()) {
			window.showNotificationError("Produktnummer är tomt");

			return false;
		}
		if (name == null || name.isEmpty()) {
			window.showNotificationError("Produktnamnet är tomt");

			return false;
		}
		if (price <= 0) {
			window.showNotificationError("Ogilitgt pris");

			return false;
		}
		if (weight <= 0) {
			window.showNotificationError("Ogiltig vikt");

			return false;
		}
		if (cost <= 0) {
			window.showNotificationError("Ogilig kostnad");

			return false;
		}
		if (ingredients == null || ingredients.isEmpty()) {
			window.showNotificationError("Ingridienser finns ej");

			return false;
		}

		return true;
	}

	/**
	 * Find existing products
	 * @param searchString what to search for (currently only the product number)
	 * @return list of all found products (currently the list only contains either one or
	 *         zero)
	 */
	public ArrayList<Product> findProduct(String searchString) {
		// TODO implement find custom

		return null;
	}

}
