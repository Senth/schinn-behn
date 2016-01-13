package se.lu.sysa11.schinnbehn.controller;

import java.util.List;

import se.lu.sysa11.schinnbehn.gui.ProductGui;
import se.lu.sysa11.schinnbehn.gui.Window;
import se.lu.sysa11.schinnbehn.model.Product;
import se.lu.sysa11.schinnbehn.model.ProductReg;


public class ProductController extends Controller<ProductGui, ProductReg> {
	
	public ProductController(Window window, ProductGui productGui, ProductReg productReg) {
		super(window, productGui, productReg);
		gui.setController(this);
	}

	
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

	
	public Product findProduct(String searchString) {
		return register.findProduct(searchString);
	}

	
	public List<Product> findProducts(String searchString) {
		return register.findProducts(searchString);
	}

}
