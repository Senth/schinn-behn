package se.lu.sysa11.schinnbehn.model;

import java.util.HashMap;

public class Product {
	private String productNbr;
	private double price;
	private String name;
	private String ingredients;
	private double weight;
	private double cost;
	private boolean active = true;
	private HashMap<String, OrderLine> orderlines = new HashMap<String, OrderLine>();

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public String getProductNbr() {
		return productNbr;
	}

	public void setProductNbr(String productNbr) {
		this.productNbr = productNbr;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public HashMap<String, OrderLine> getOrderlines() {
		return orderlines;
	}

	public void setOrderlines(HashMap<String, OrderLine> orderlines) {
		this.orderlines = orderlines;
	}

	public int totalQuantityOrder() {
		int sum = 0;

		for (OrderLine tmp : getOrderlines().values()) {
			sum += tmp.getQuantity();
		}
		// for (OrderLine tmp : orderlines) {
		// sum += tmp.getQuantity();
		// }
		return sum;
	}
}
