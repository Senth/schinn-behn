package se.lu.sysa11.schinnbehn.model;

import java.util.HashMap;
import java.util.List;

import com.spiddekauga.utils.Strings.TokenizePatterns;
import com.spiddekauga.utils.TokenSearch;

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

	public void add(Product p) {
		if (p != null) {
			products.put(p.getProductNbr(), p);

			searchHelper.add(p, TokenizePatterns.FROM_START, p.getProductNbr(), p.getName());
		}
	}

	public Product findProduct(String prodNbr) {
		return products.get(prodNbr);
	}

	/**
	 * Find a product by search string.
	 * @param searchString what to search for. If you use more than one word they are
	 *        combined with an and.
	 * @return found products sorted by relevance
	 */
	public List<Product> findProducts(String searchString) {
		return searchHelper.search(searchString);
	}

}
