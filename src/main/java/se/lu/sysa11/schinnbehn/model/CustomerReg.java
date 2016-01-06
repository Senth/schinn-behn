package se.lu.sysa11.schinnbehn.model;

import java.util.HashMap;
import java.util.List;

import com.spiddekauga.utils.TokenSearch;
import com.spiddekauga.utils.TokenizePatterns;

/**
 * Contains all customers
 */
public class CustomerReg {
	private HashMap<String, Customer> customers = new HashMap<>();
	private TokenSearch<Customer> searchHelper = new TokenSearch<>();

	public HashMap<String, Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(HashMap<String, Customer> customers) {
		this.customers = customers;
	}

	/**
	 * Call this when a customer has been updated. This makes the customer searchable with
	 * the new information
	 * @param customer
	 */
	public void update(Customer customer) {
		if (customer != null) {
			searchHelper.update(customer, TokenizePatterns.FROM_START, customer.getCustomerNbr(), customer.getName(), customer.getTelephoneNbr(),
					customer.getEmail());
		}
	}

	public void add(Customer c) {
		if (c != null) {
			customers.put(c.getCustomerNbr(), c);

			update(c);
		}
	}

	public Customer findCustomer(String cNbr) {
		return customers.get(cNbr);
	}

	/**
	 * Find a customer by a custom search string
	 * @param searchString what to search for. If you use more than one word they are
	 *        combined with an 'and'.
	 * @return found products sorted by relevance
	 */
	public List<Customer> findCustomers(String searchString) {
		return searchHelper.search(searchString);
	}
}
