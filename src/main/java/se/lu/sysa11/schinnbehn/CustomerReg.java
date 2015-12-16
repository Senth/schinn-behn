package se.lu.sysa11.schinnbehn;

import java.util.HashMap;

/**
 *
 * @author
 */
public class CustomerReg {
	private HashMap<String, Customer> customers = new HashMap<>();

	public void addCustomer(Customer c) {
		customers.put(c.getCustomerNbr(), c);
	}

	/**
	 * @return the customers
	 */
	public HashMap<String, Customer> getCustomers() {
		return customers;
	}

	/**
	 * @param customers
	 *            the customers to set
	 */
	public void setCustomers(HashMap<String, Customer> customers) {
		this.customers = customers;
	}

	public Customer findCustomer(String cNbr) {
		return customers.get(cNbr);
	}

}
