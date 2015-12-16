package se.lu.sysa11.schinnbehn;

/**
 *
 *
 * @author Jesper
 */
public class Order {
	private String orderNbr;
	private String deliveryAdress;

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
	public String getDeliveryAdress() {
		return deliveryAdress;
	}

	/**
	 * @param deliveryAddress
	 *            the deliveryAddress to set
	 */
	public void setDeliveryAdress(String deliveryAdress) {
		this.deliveryAdress = deliveryAdress;
	}

	public void addOrderLine(OrderLine l) {
		addOrderLine(l);
	}

	public double getTotalPrice() {
		// TODO
		return 0;
	}
}
