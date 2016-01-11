package se.lu.sysa11.schinnbehn.model;

import java.util.HashSet;

/**
 * dfdsf
 *
 * @author Jesper
 */
public class Order {
	private String orderNbr;
	private String deliveryAdress;
	private Customer madeby;
	private HashSet<OrderLine> orderlines = new HashSet<OrderLine>();
	private static int orderNbrCounter = 1;
	private String orderDate = "2016-01-04";

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

	public HashSet<OrderLine> getOrderline() {
		return orderlines;
	}

	public void setOrderLine(HashSet<OrderLine> orderlines) {
		this.orderlines = orderlines;
	}

	public void addOrderLine(OrderLine line) {
		orderlines.add(line);
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

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public void removeOrderLine(OrderLine line) {
		orderlines.remove(line);
	}

	public void clearOrderLines() {
		orderlines.clear();
	}

}
