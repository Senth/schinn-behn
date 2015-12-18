package se.lu.sysa11.schinnbehn.model;

import java.util.ArrayList;

/**
 * dfdsf
 *
 * @author Jesper
 */
public class Order {
	private String orderNbr;
	private String deliveryAdress;
	private Customer madeby;
	private ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
	private static int orderNbrCounter = 1;

	public Order() {
		orderNbr = String.valueOf(orderNbrCounter);
		orderNbrCounter++;
	}

	public String getOrderNbr() {
		return orderNbr;
	}

	public void setOrderNbr(String orderNbr) {
		this.orderNbr = orderNbr;
	}

	public String getDeliveryAdress() {
		return deliveryAdress;
	}

	public void setDeliveryAdress(String deliveryAdress) {
		this.deliveryAdress = deliveryAdress;
	}

	public ArrayList<OrderLine> getOrderline() {
		return orderlines;
	}

	public void setOrderLine(ArrayList<OrderLine> orderlines) {
		this.orderlines = orderlines;
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
