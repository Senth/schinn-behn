package se.lu.sysa11.schinnbehn.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import se.lu.sysa11.schinnbehn.controller.CustomerController;

/**
 * @author Kalle
 */
public class CustomerGui extends Gui<CustomerController> {
	private JPanel panel = new JPanel();
	private JTextField textField_Name;
	private JTextField textField_Phone;
	private JTextField textField_Adress;
	private JTextField textField_Email;
	private JTextField textField_BillingAdress;
	private JTextField textField_ContactPerson;
	private JTextField textField_Postadress;
	private JTextField textField_FindCustomer;
	private JTextField textField_ShowCustomerNbr;
	private JTable table_Orders;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	protected void initialize() {

		panel.setLayout(null);

		JLabel lblNewCustomer = new JLabel("Ny Kund");
		lblNewCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewCustomer.setBounds(12, 24, 72, 22);
		panel.add(lblNewCustomer);

		JLabel lblName = new JLabel("Namn: ");
		lblName.setBounds(12, 57, 56, 22);
		panel.add(lblName);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 173, 56, 22);
		panel.add(lblEmail);

		JLabel lblGatuadress = new JLabel("Gatuadress:");
		lblGatuadress.setBounds(12, 115, 72, 22);
		panel.add(lblGatuadress);

		JLabel lblPostadress = new JLabel("Postadress:");
		lblPostadress.setBounds(12, 144, 72, 22);
		panel.add(lblPostadress);

		JLabel lblPhoneNumber = new JLabel("Telefonnr:");
		lblPhoneNumber.setBounds(12, 86, 72, 22);
		panel.add(lblPhoneNumber);

		JLabel lblBillingAdress = new JLabel("Faktureringssadress:");
		lblBillingAdress.setBounds(12, 202, 129, 22);
		panel.add(lblBillingAdress);

		JLabel lblContactPerson = new JLabel("Kontaktperson:");
		lblContactPerson.setBounds(12, 231, 86, 22);
		panel.add(lblContactPerson);

		JLabel lblFoundCustomer = new JLabel("Kundnr:");
		lblFoundCustomer.setBounds(12, 260, 56, 22);
		panel.add(lblFoundCustomer);

		JLabel lblFindCustomer = new JLabel("Hitta kund");
		lblFindCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFindCustomer.setBounds(12, 330, 86, 22);
		panel.add(lblFindCustomer);

		JLabel lblKundnr = new JLabel("Kundnr:");
		lblKundnr.setBounds(12, 359, 56, 22);
		panel.add(lblKundnr);

		textField_Name = new JTextField();
		textField_Name.setBounds(158, 57, 138, 22);
		panel.add(textField_Name);
		textField_Name.setColumns(10);

		textField_Phone = new JTextField();
		textField_Phone.setBounds(158, 86, 138, 22);
		panel.add(textField_Phone);
		textField_Phone.setColumns(10);

		textField_Adress = new JTextField();
		textField_Adress.setBounds(158, 115, 138, 22);
		panel.add(textField_Adress);
		textField_Adress.setColumns(10);

		textField_Email = new JTextField();
		textField_Email.setBounds(158, 173, 138, 22);
		panel.add(textField_Email);
		textField_Email.setColumns(10);

		textField_BillingAdress = new JTextField();
		textField_BillingAdress.setBounds(158, 202, 138, 22);
		panel.add(textField_BillingAdress);
		textField_BillingAdress.setColumns(10);

		textField_ContactPerson = new JTextField();
		textField_ContactPerson.setBounds(158, 231, 138, 22);
		panel.add(textField_ContactPerson);
		textField_ContactPerson.setColumns(10);

		textField_Postadress = new JTextField();
		textField_Postadress.setBounds(158, 144, 138, 22);
		panel.add(textField_Postadress);
		textField_Postadress.setColumns(10);

		textField_FindCustomer = new JTextField();
		textField_FindCustomer.setBounds(158, 359, 138, 22);
		panel.add(textField_FindCustomer);
		textField_FindCustomer.setColumns(10);

		textField_ShowCustomerNbr = new JTextField();
		textField_ShowCustomerNbr.setEditable(false);
		textField_ShowCustomerNbr.setBounds(158, 260, 138, 22);
		panel.add(textField_ShowCustomerNbr);
		textField_ShowCustomerNbr.setColumns(10);

		JButton btnAddCustomer = new JButton("L\u00E4gg till kund");
		btnAddCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField_Name.getText();
				String phoneNbr = textField_Phone.getText();
				String adress = textField_Adress.getText() + " " + textField_Postadress.getText();
				String email = textField_Email.getText();
				String billingAdress = textField_BillingAdress.getText();

				controller.addCustomer(name, phoneNbr, adress, email, billingAdress);
			}
		});
		btnAddCustomer.setBounds(12, 292, 138, 25);
		panel.add(btnAddCustomer);

		JButton btnSearchCustomer = new JButton("S\u00F6k");
		btnSearchCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchString = textField_FindCustomer.getText();
				controller.findCustomer(searchString);
			}
		});
		btnSearchCustomer.setBounds(308, 358, 97, 25);
		panel.add(btnSearchCustomer);

		JButton btnUpdateCustomer = new JButton("Uppdatera kund");
		btnUpdateCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String customerNbr = textField_ShowCustomerNbr.getText();
				String name = textField_Name.getText();
				String telephone = textField_Phone.getText();
				String address = textField_Adress.getText() + " " + textField_Postadress.getText();
				String email = textField_Email.getText();
				String billingAddress = textField_BillingAdress.getText();

				controller.updateCustomer(customerNbr, name, telephone, address, email, billingAddress);
			}
		});
		btnUpdateCustomer.setBounds(158, 292, 138, 25);
		panel.add(btnUpdateCustomer);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(459, 28, 452, 353);
		panel.add(scrollPane);

		String column_names[] = { "Ordernummer", "Datum", "Summa" };
		table_Orders = new JTable();
		table_Orders.setModel(new DefaultTableModel(new Object[][] {}, column_names) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table_Orders.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table_Orders);

	}
}
