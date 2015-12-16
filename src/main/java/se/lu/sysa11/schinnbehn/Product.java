package se.lu.sysa11.schinnbehn;

/**
 *
 *
 * @author Matteus Magnusson <matteus.magnusson@spiddekauga.com>
 */
public class Product {
	private String productNbr;
	private double price;
	private String name;
	private String ingredients;
	private double weight;
	private String batchNbr;
	private double cost;

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

	public String getBatchNbr() {
		return batchNbr;
	}

	public void setBatchNbr(String batchNbr) {
		this.batchNbr = batchNbr;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
