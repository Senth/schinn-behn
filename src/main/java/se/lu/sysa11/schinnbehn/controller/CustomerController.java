package se.lu.sysa11.schinnbehn.controller;


import se.lu.sysa11.schinnbehn.gui.CustomerGui;
import se.lu.sysa11.schinnbehn.gui.Views;
import se.lu.sysa11.schinnbehn.gui.Window;
import se.lu.sysa11.schinnbehn.model.Customer;
import se.lu.sysa11.schinnbehn.model.CustomerReg;

/**
 * Connects the customer model and view
 */
public class CustomerController extends Controller<CustomerGui, CustomerReg> {
	/**
	 * Create a custom controller with the window
	 * @param window application window
	 * @param customerGui the customer view/GUIö
	 * @param customerReg the customer model/register
	 */
	public CustomerController(Window window, CustomerGui customerGui, CustomerReg customerReg) {
		super(window, customerGui, customerReg);
		gui.setController(this);
	}

	/**
	 * Create a new customer
	 * @param name customer/company name
	 * @param telephone company telephone number
	 * @param address company address
	 * @param email company email address
	 * @return customerNbr if the customer was added successfully. Null if one or more of
	 *         the parameters are wrong or empty
	 */
	public String addCustomer(String name, String telephone, String address, String email) {

		if (!isInputValid(name, telephone, address, email)) {
			return null;
		}

		// Add customer
		Customer customer = new Customer();
		customer.setName(name);
		customer.setTelephoneNbr(telephone);
		customer.setAddress(address);
		customer.setEmail(email);

		register.add(customer);

		// send notification
		window.showNotificationSuccess("Kund tillagd");

		return customer.getCustomerNbr();
	}

	/**
	 * Update an existing customer
	 * @param customerNbr the customer number
	 * @param name customer/company name
	 * @param telephone company telephone number
	 * @param address company address
	 * @param email company email address
	 * @return true if the customer was changed successfully. False if one or more of the
	 *         parameters are wrong or empty.
	 */
	public boolean updateCustomer(String customerNbr, String name, String telephone, String address, String email) {

		// check if valid
		if (!isInputValid(name, telephone, address, email)) {
			return false;
		}
		// TODO update customer
		Customer customer = register.findCustomer(customerNbr);
		if (customerNbr != null) {
			customer.setName(name);
			customer.setTelephoneNbr(telephone);
			customer.setAddress(address);
			customer.setEmail(email);

		} else {
			window.showNotificationError("Ingen kund med kundnummer " + customerNbr + " funnen");

			return false;
		}


		// send notification
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
		if (email == null || email.isEmpty()) {
			window.showNotificationError("Ogiltig address");

			return false;
		}
		return true;
	}

	/**
	 * Go to the specified order
	 * @param orderNumber the order number to go to
	 */
	public void gotoOrder(String orderNumber) {
		window.switchTo(Views.ORDER, orderNumber);
	}

	/**
	 * Find existing customers
	 * @param searchString what to search for (currently only the customer number)
	 * @return list of all found customer (currently the list can only contain one or
	 *         zero)
	 */
	public Customer findCustomer(String searchString) {
		Customer tmpCustomer = register.findCustomer(searchString);
		if (tmpCustomer == null) {
			window.showNotificationError("Finns ingen kund med kundnummer " + searchString + ".");
		} else {
			window.showNotificationSuccess("Kund med kundnummer " + searchString + " hittad.");
		}
		return tmpCustomer;
	}
}
