package se.lu.sysa11.schinnbehn.model;

import java.util.HashMap;


public class Customer {
	private String customerNbr;
	private String name;
	private String billingAdress;
	private String email;
	private String telephoneNbr;
	private HashMap<String, Order> orders = new HashMap<>();
	private static int customerNbrCounter = 1;

	
	public Customer() {
		customerNbr = String.valueOf(customerNbrCounter);
		customerNbrCounter++;
	}

	public HashMap<String, Order> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<String, Order> orders) {
		this.orders = orders;
	}

	public String getCustomerNbr() {
		return customerNbr;
	}

	public void setCustomerNbr(String customerNbr) {
		this.customerNbr = customerNbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBillingadress() {
		return billingAdress;
	}

	public void setBillingAdress(String billingAdress) {
		this.billingAdress = billingAdress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNbr() {
		return telephoneNbr;
	}

	public void setTelephoneNbr(String telephoneNbr) {
		this.telephoneNbr = telephoneNbr;
	}

	public void addOrder(Order o) {
		orders.put(o.getOrderNbr(), o);
	}

}
