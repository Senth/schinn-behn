package se.lu.sysa11.schinnbehn;

import java.util.HashMap;

/**
 *
 * @author
 */
public class OrderReg {
	private HashMap<String, Order> orders = new HashMap<>();

	private void addOrder(Order o) {
		orders.put(o.getOrderNbr(), o);
	}

	/**
	 * @return the orders
	 */
	public HashMap<String, Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *            the orders to set
	 */
	public void setOrders(HashMap<String, Order> orders) {
		this.orders = orders;
	}

	public Order findOrder(String orderNbr) {
		return orders.get(orderNbr);
	}
}
