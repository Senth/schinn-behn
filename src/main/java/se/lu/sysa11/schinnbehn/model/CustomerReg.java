package se.lu.sysa11.schinnbehn.model;

import java.util.HashMap;

/**
 * @author Jesper
 */
public class CustomerReg {
	private HashMap<String, Customer> customers = new HashMap<>();

	public HashMap<String, Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(HashMap<String, Customer> customers) {
		this.customers = customers;
	}

	public void add(Customer c) {
		customers.put(c.getCustomerNbr(), c);
	}

	public Customer findCustomer(String cNbr) {
		return customers.get(cNbr);
	}

}
