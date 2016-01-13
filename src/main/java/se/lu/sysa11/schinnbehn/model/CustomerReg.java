package se.lu.sysa11.schinnbehn.model;

import java.util.HashMap;
import java.util.List;

import com.spiddekauga.utils.TokenSearch;
import com.spiddekauga.utils.TokenizePatterns;


public class CustomerReg {
	private HashMap<String, Customer> customers = new HashMap<>();
	private TokenSearch<Customer> searchHelper = new TokenSearch<>();

	public HashMap<String, Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(HashMap<String, Customer> customers) {
		this.customers = customers;
	}

	
	public void update(Customer customer) {
		if (customer != null) {
			searchHelper.update(customer, TokenizePatterns.FROM_START, customer.getCustomerNbr(), customer.getName(), customer.getTelephoneNbr(),
					customer.getEmail());
		}
	}

	public void add(Customer customer) {
		if (customer != null) {
			customers.put(customer.getCustomerNbr(), customer);

			update(customer);
		}
	}

	public Customer findCustomer(String cNbr) {
		return customers.get(cNbr);
	}

	
	public List<Customer> findCustomers(String searchString) {
		return searchHelper.search(searchString);
	}
}
