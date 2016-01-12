package se.lu.sysa11.schinnbehn.model;

import java.util.HashMap;
import java.util.List;

import com.spiddekauga.utils.TokenSearch;
import com.spiddekauga.utils.TokenizePatterns;

/**
 * @author Ann-Kathrine
 */
public class ProductReg {
	private HashMap<String, Product> products = new HashMap<String, Product>();
	private TokenSearch<Product> searchHelper = new TokenSearch<>();

	public void setProducts(HashMap<String, Product> products) {
		this.products = products;
	}

	public HashMap<String, Product> getProducts() {
		return products;
	}

	public boolean updateProductNbr(String oldNbr, String newNbr) {
		// Check if the new product number exist, return false then
		if (findProduct(newNbr) != null) {
			return false;
		}

		// Update hashmap number
		Product product = products.remove(oldNbr);
		if (product != null) {
			products.put(newNbr, product);
		} else {
			return false;
		}

		return true;
	}

	/**
	 * Call this when a product has been updated. This makes the product searchable with
	 * the new information.
	 * @param product the product that has been updated
	 */
	public void update(Product product) {
		if (product != null) {
			searchHelper.update(product, TokenizePatterns.FROM_START, product.getProductNbr(), product.getIngredients());
			searchHelper.add(product, TokenizePatterns.ALL, product.getName());

			// Add active/deactive state afterwards
			if (product.isActive()) {
				searchHelper.add(product, TokenizePatterns.WORD, "aktiv");
			} else {
				searchHelper.add(product, TokenizePatterns.WORD, "inaktiv");
			}
		}
	}

	public void add(Product product) {
		if (product != null) {
			products.put(product.getProductNbr(), product);

			update(product);
		}
	}

	public Product findProduct(String prodNbr) {
		return products.get(prodNbr);
	}

	/**
	 * Find a product by search string.
	 * @param searchString what to search for. If you use more than one word they are
	 *        combined with an 'and'.
	 * @return found products sorted by relevance
	 */
	public List<Product> findProducts(String searchString) {
		return searchHelper.search(searchString);
	}

}
