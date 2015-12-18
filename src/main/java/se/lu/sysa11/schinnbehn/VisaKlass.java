package se.lu.sysa11.schinnbehn;

/**
 * @author Matteus hej
 */
public class VisaKlass {
	/**
	 * Update an existing product
	 * @param productNbr number/serial of the product
	 * @param name product name
	 * @param price current price of the product
	 * @param ingredients ingredients of the product
	 * @param weight weight of the product
	 * @param cost how much it costs to buy or produce the product
	 * @return true if successfully added product
	 */
	public boolean updateProduct(String productNbr, String name, double price, String ingredients, double weight, double cost) {
		// TODO check if valid
		if (productNbr == null || productNbr.isEmpty()) {
			// TODO send notification

			return false;
		}
		if (name == null || name.isEmpty()) {
			// TODO send notification

			return false;
		}

		name = null;
		name = "";

		// TODO if invalid -> Send notification

		// TODO Update product

		// TODO send notification

		return true;
	}
}