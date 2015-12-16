package se.lu.sysa11.schinnbehn;

import java.util.HashMap;

/**
 *
 * @author
 */
public class Customer {
	private String customerNbr;
	private String name;
	private String billingAdress;
	private String email;
	private String telephoneNbr;
	private String contactPerson;
	private HashMap<String, Order> orders = new HashMap<>(); // Fail med get o
																// set

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

	/**
	 * @return the customerNbr
	 */
	public String getCustomerNbr() {
		return customerNbr;
	}

	/**
	 * @param customerNbr
	 *            the customerNbr to set
	 */
	public void setCustomerNbr(String customerNbr) {
		this.customerNbr = customerNbr;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the billingAdress
	 */
	public String getBillingAdress() {
		return billingAdress;
	}

	/**
	 * @param billingAdress
	 *            the billingAdress to set
	 */
	public void setBillingAdress(String billingAdress) {
		this.billingAdress = billingAdress;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telephoneNbr
	 */
	public String getTelephoneNbr() {
		return telephoneNbr;
	}

	/**
	 * @param telephoneNbr
	 *            the telephoneNbr to set
	 */
	public void setTelephoneNbr(String telephoneNbr) {
		this.telephoneNbr = telephoneNbr;
	}

	/**
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return contactPerson;
	}

	/**
	 * @param contactPerson
	 *            the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public void addOrder(Order o) {
		orders.put(o.getOrderNbr(), o);
	}

}
