package se.lu.sysa11.schinnbehn;

import java.util.HashMap;

/**
 * @author Ann-Kathrine
 */
public class ProductReg {
	private HashMap<String, Product> products = new HashMap<String, Product>();

	public void setProducts(HashMap<String, Product> products) {
		this.products = products;
	}

	public HashMap<String, Product> getProducts() {
		return products;
	}

	public void add(Product p) {
		products.put(p.getProductNbr(), p);
	}

	public Product findProduct(String prodNbr) {
		return products.get(prodNbr);
	}

}
