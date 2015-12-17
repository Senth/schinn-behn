package se.lu.sysa11.schinnbehn;

import java.util.ArrayList;

/**
 *
 * @author Ann-Kathrine
 */
public class ProductReg {
	private ArrayList<Product> products = new ArrayList<Product>();

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void add(Product p) {
		products.add(p);
	}

	public Product findProduct(String prodNbr) {
		for (Product tmp : products) {
			if (tmp.getProductNbr().equals(prodNbr)) {
				return tmp;
			}
		}
		return null;
	}

}
