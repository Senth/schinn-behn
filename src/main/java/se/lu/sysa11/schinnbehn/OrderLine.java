package se.lu.sysa11.schinnbehn;

/**
 *
 *
 * @author Jesper
 */
public class OrderLine {
	private int lineNbr;
	private int quantity;
	private Product product;
	private Order order;
	private double productPrice;

	/**
	 * @return the lineNbr
	 */
	public int getLineNbr() {
		return lineNbr;
	}

	/**
	 * @param lineNbr
	 *            the lineNbr to set
	 */
	public void setLineNbr(int lineNbr) {
		this.lineNbr = lineNbr;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return the productPrice
	 */
	public double getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice
	 *            the productPrice to set
	 */
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getPriceLine() {

		return productPrice * quantity;
	}

}
