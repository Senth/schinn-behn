package se.lu.sysa11.schinnbehn.controller;

import java.util.List;

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
	public String addProduct(String productNbr, String name, double price, String ingredients, double weight, double cost) {

		Product product = new Product();
		product.setProductNbr(productNbr);
		product.setName(name);
		product.setPrice(price);
		product.setIngredients(ingredients);
		product.setWeight(weight);
		product.setCost(cost);
		product.setActive(true);
		register.add(product);

		window.showNotificationSuccess("Produkt tillagd");

		return product.getProductNbr();
	}

	/**
	 * Update an existing product
	 * @param oldProductNbr the old product number of the product
	 * @param productNbr new number/serial of the product
	 * @param name product name
	 * @param price current price of the product
	 * @param ingredients ingredients of the product
	 * @param weight weight of the product
	 * @param cost how much it costs to buy or produce the product
	 * @param active if the product is active in the assortment
	 * @return true if successfully added product
	 */
	public boolean updateProduct(String oldProductNbr, String productNbr, String name, double price, String ingredients, double weight, double cost,
			boolean active) {


		if (oldProductNbr == null || oldProductNbr.isEmpty()) {
			window.showNotificationInfo("Klicka f\u00F6rst p\u00E5 en produkt du vill \u00E4ndra");
			return false;
		}

		Product product = register.findProduct(oldProductNbr);

		// Update product
		if (product != null) {

			if (!oldProductNbr.equals(productNbr)) {
				boolean success = register.updateProductNbr(oldProductNbr, productNbr);

				if (!success) {
					window.showNotificationError(
							"Finns redan en produkt med produktnummer " + productNbr + ". Var god v\u00E4lj ett annat produktnummer");
					return false;
				}
			}

			product.setProductNbr(productNbr);
			product.setName(name);
			product.setPrice(price);
			product.setIngredients(ingredients);
			product.setWeight(weight);
			product.setCost(cost);
			product.setActive(active);

			register.update(product);
		} else {
			window.showNotificationError("Ingen produkt med produktnummer " + productNbr + " funnen");
			return false;
		}

		window.showNotificationSuccess("Produkt uppdaterad");

		return true;
	}

	/**
	 * Find existing products
	 * @param searchString what to search for (currently only the product number)
	 * @return list of all found products (currently the list only contains either one or
	 *         zero)
	 */
	public Product findProduct(String searchString) {
		return register.findProduct(searchString);
	}

	/**
	 * Find a product by search string.
	 * @param searchString what to search for. If you use more than one word they are
	 *        combined with an and.
	 * @return found products sorted by relevance
	 */
	public List<Product> findProducts(String searchString) {
		return register.findProducts(searchString);
	}

}
