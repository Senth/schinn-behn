package se.lu.sysa11.schinnbehn.controller;

import java.util.ArrayList;

import se.lu.sysa11.schinnbehn.Customer;
import se.lu.sysa11.schinnbehn.CustomerReg;
import se.lu.sysa11.schinnbehn.gui.CustomerGui;
import se.lu.sysa11.schinnbehn.gui.Window;

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
	protected CustomerController(Window window, CustomerGui customerGui, CustomerReg customerReg) {
		super(window, customerGui, customerReg);
		gui.setController(this);
	}

	/**
	 * Create a new customer
	 * @param name customer/company name
	 * @param telephone company telephone number
	 * @param address company address
	 * @param email company email address
	 * @param billingAddress where to send the bills
	 * @return true if the customer was added successfully. False if one or more of the
	 *         parameters are wrong or empty
	 */
	public boolean addCustomer(String name, String telephone, String address, String email, String billingAddress) {
		boolean successfull = false;

		// TODO check if valid

		// TODO if invalid -> Send notification

		// TODO Add customer

		// TODO send notification

		return successfull;
	}

	/**
	 * Update an existing customer
	 * @param customerNbr the customer number
	 * @param name customer/company name
	 * @param telephone company telephone number
	 * @param address company address
	 * @param email company email address
	 * @param billingAddress where to send the bills
	 * @return true if the customer was changed successfully. False if one or more of the
	 *         parameters are wrong or empty.
	 */
	public boolean updateCustomer(String customerNbr, String name, String telephone, String address, String email, String billingAddress) {
		boolean successfull = false;

		// TODO check if valid

		// TODO update customer

		// TODO send notification

		return successfull;
	}

	/**
	 * Find existing customers
	 * @param searchString what to search for (currently only the customer number)
	 * @return list of all found customer (currently the list can only contain one or
	 *         zero)
	 */
	public ArrayList<Customer> findCustomer(String searchString) {
		// TODO implement find Customer

		return null;
	}
}
