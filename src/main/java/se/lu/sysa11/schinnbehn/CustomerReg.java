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

	public Customer findCustomer(String cNbr) {
		return customers.get(cNbr);
	}

}
