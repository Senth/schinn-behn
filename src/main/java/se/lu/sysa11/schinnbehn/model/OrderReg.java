package se.lu.sysa11.schinnbehn.model;

import java.util.HashMap;

/**
 *
 * @author
 */
public class OrderReg {
	private HashMap<String, Order> orders = new HashMap<>();

	public HashMap<String, Order> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<String, Order> orders) {
		this.orders = orders;
	}

	public void add(Order o) {
		orders.put(o.getOrderNbr(), o);
	}

	public Order findOrder(String orderNbr) {
		return orders.get(orderNbr);
	}
}
