package se.lu.sysa11.schinnbehn;

/**
 *
 *
 * @author Jesper
 */
public class Order {
	private String orderNbr;
	private String deliveryAddress;

	/**
	 * @return the orderNbr
	 */
	public String getOrderNbr() {
		return orderNbr;
	}

	/**
	 * @param orderNbr
	 *            the orderNbr to set
	 */
	public void setOrderNbr(String orderNbr) {
		this.orderNbr = orderNbr;
	}

	/**
	 * @return the deliveryAddress
	 */
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * @param deliveryAddress
	 *            the deliveryAddress to set
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
}
