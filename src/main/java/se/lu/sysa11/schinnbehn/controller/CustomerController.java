package se.lu.sysa11.schinnbehn.controller;

import java.util.List;

import se.lu.sysa11.schinnbehn.gui.CustomerGui;
import se.lu.sysa11.schinnbehn.gui.Views;
import se.lu.sysa11.schinnbehn.gui.Window;
import se.lu.sysa11.schinnbehn.model.Customer;
import se.lu.sysa11.schinnbehn.model.CustomerReg;


public class CustomerController extends Controller<CustomerGui, CustomerReg> {
	
	public CustomerController(Window window, CustomerGui customerGui, CustomerReg customerReg) {
		super(window, customerGui, customerReg);
		gui.setController(this);
	}

	
	public String addCustomer(String name, String telephone, String address, String email) {

		if (!isInputValid(name, telephone, address, email)) {
			return null;
		}

		Customer customer = new Customer();
		customer.setName(name);
		customer.setTelephoneNbr(telephone);
		customer.setBillingAdress(address);
		customer.setEmail(email);

		register.add(customer);

		window.showNotificationSuccess("Kund tillagd");

		return customer.getCustomerNbr();
	}

	
	public boolean updateCustomer(String customerNbr, String name, String telephone, String address, String email) {


		if (!isInputValid(name, telephone, address, email)) {
			return false;
		}

		Customer customer = register.findCustomer(customerNbr);
		if (customer != null) {
			customer.setName(name);
			customer.setTelephoneNbr(telephone);
			customer.setBillingAdress(address);
			customer.setEmail(email);
		} else {
			window.showNotificationError("Ingen kund med kundnummer " + customerNbr + " funnen");

			return false;
		}

		window.showNotificationSuccess("Kund uppdaterad");

		return true;
	}

	private boolean isInputValid(String name, String telephone, String address, String email) {
		if (name == null || name.isEmpty()) {
			window.showNotificationError("Ogiltigt namn");

			return false;
		}
		if (telephone == null || telephone.isEmpty()) {
			window.showNotificationError("Ogiltigt telefonnummer");

			return false;
		}

		if (address == null || address.isEmpty()) {
			window.showNotificationError("Ogiltig adress");

			return false;
		}
		if (email == null || email.isEmpty()) {
			window.showNotificationError("Ogiltig emailadress");

			return false;
		}
		return true;
	}

	@Override
	protected void onActivate(Object data) {
		gui.populateOrderTable();
	}

	
	public void gotoOrder(String orderNumber) {
		window.switchTo(Views.ORDER, orderNumber);
	}

	public void newOrder(String customerNbr) {
		Customer customer = register.findCustomer(customerNbr);
		window.switchTo(Views.ORDER, customer);
	}

	
	public Customer findCustomer(String searchString) {
		Customer tmpCustomer = register.findCustomer(searchString);
		if (tmpCustomer == null) {
			window.showNotificationError("Finns ingen kund med kundnummer " + searchString + ".");
		} else {
			window.showNotificationSuccess("Kund med kundnummer " + searchString + " hittad.");
		}
		return tmpCustomer;
	}

	
	public List<Customer> findCustomers(String searchString) {
		return register.findCustomers(searchString);
	}
}
