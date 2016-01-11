package se.lu.sysa11.schinnbehn.model;

/**
 * Order line
 */
public class OrderLine {
	private int quantity;
	private Product product;
	private Order order;
	private double productPrice;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getLinePrice() {
		return productPrice * quantity;
	}

}
