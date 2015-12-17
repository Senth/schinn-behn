package se.lu.sysa11.schinnbehn;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author
 */
public class Customer {
	private String customerNbr;
	private String name;
	private String billingAdress;
	private String email;
	private String telephoneNbr;
	private String contactPerson;
	private HashMap<String, Order> orders = new HashMap<>();
	private ArrayList<ContactPerson> contactpersons = new ArrayList<>();
	private static int customerNbrCounter = 1;

	/**
	 * Default constructor, automatically sets the customer number to the next
	 * available customer number
	 */
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

	public String getBillingAdress() {
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

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public void addOrder(Order o) {
		orders.put(o.getOrderNbr(), o);
	}

	public ArrayList<ContactPerson> getContactpersons() {
		return contactpersons;
	}

	public void setContactpersons(ArrayList<ContactPerson> contactpersons) {
		this.contactpersons = contactpersons;
	}

	public void addContactPerson(ContactPerson a) {
		contactpersons.add(a);
	}

}
