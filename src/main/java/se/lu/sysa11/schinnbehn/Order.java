package se.lu.sysa11.schinnbehn;

import java.util.ArrayList;

/**
 *
 *
 * @author Jesper
 */
public class Order {
	private String orderNbr;
	private String deliveryAdress;
	private Customer madeby;
	private ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();

	/**
	 * @return the orderNbr
	 */
	public String getOrderNbr() {
		return orderNbr;
	}

	/**
	 * @return the orderLineList
	 */
	public ArrayList<OrderLine> getOrderline() {
		return orderlines;
	}

	/**
	 * @param orderLineList
	 *            the orderLineList to set
	 */
	public void setOrderLine(ArrayList<OrderLine> orderlines) {
		this.orderlines = orderlines;
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
		orderlines.add(l);
	}

	public double getTotalPrice() {
		double sum = 0;
		for (OrderLine orderLine : orderlines) {
			sum += orderLine.getLinePrice();
		}
		return sum;
	}

	public Customer getMadeby() {
		return madeby;
	}

	public void setMadeby(Customer madeby) {
		this.madeby = madeby;
	}

}
