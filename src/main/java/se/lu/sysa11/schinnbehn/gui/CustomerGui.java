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
	/**
	 * Can't have panel in base class as we're not able to access WindowBuilder correctly
	 * then
	 */
	private JPanel panel = new JPanel();
	private JTextField textField_Name;
	private JTextField textField_Phone;
	private JTextField textField_Adress;
	private JTextField textField_Email;
	private JTextField textField_PostalNbr;
	private JTextField textField_FindCustomer;
	private JTextField textField_ShowCustomerNbr;
	private JTable table_Orders;
	private JTextField textField_City;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void initialize() {

		panel.setLayout(null);

		JLabel lblNewCustomer = new JLabel("Ny Kund");
		lblNewCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewCustomer.setBounds(12, 24, 72, 22);
		panel.add(lblNewCustomer);

		JLabel lblName = new JLabel("Namn:");
		lblName.setBounds(12, 57, 56, 22);
		panel.add(lblName);

		JLabel lblPhoneNumber = new JLabel("Telefonnr:");
		lblPhoneNumber.setBounds(12, 86, 72, 22);
		panel.add(lblPhoneNumber);

		JLabel lblStreetAdress = new JLabel("Gatuadress:");
		lblStreetAdress.setBounds(12, 115, 72, 22);
		panel.add(lblStreetAdress);

		JLabel lblPostalNbr = new JLabel("Postnummer:");
		lblPostalNbr.setBounds(12, 144, 86, 22);
		panel.add(lblPostalNbr);

		JLabel lblCity = new JLabel("Stad:");
		lblCity.setBounds(12, 173, 56, 22);
		panel.add(lblCity);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 202, 56, 22);
		panel.add(lblEmail);

		JLabel lblFoundCustomer = new JLabel("Kundnr:");
		lblFoundCustomer.setBounds(12, 231, 56, 22);
		panel.add(lblFoundCustomer);

		JLabel lblFindCustomer = new JLabel("Hitta kund");
		lblFindCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFindCustomer.setBounds(12, 312, 86, 22);
		panel.add(lblFindCustomer);

		JLabel lblKundnr = new JLabel("Kundnr:");
		lblKundnr.setBounds(12, 345, 56, 22);
		panel.add(lblKundnr);

		textField_City = new JTextField();
		textField_City.setBounds(158, 173, 138, 22);
		panel.add(textField_City);
		textField_City.setColumns(10);

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
		textField_Email.setBounds(158, 202, 138, 22);
		panel.add(textField_Email);
		textField_Email.setColumns(10);

		textField_PostalNbr = new JTextField();
		textField_PostalNbr.setBounds(158, 144, 138, 22);
		panel.add(textField_PostalNbr);
		textField_PostalNbr.setColumns(10);

		textField_FindCustomer = new JTextField();
		textField_FindCustomer.setBounds(158, 345, 138, 22);
		panel.add(textField_FindCustomer);
		textField_FindCustomer.setColumns(10);

		textField_ShowCustomerNbr = new JTextField();
		textField_ShowCustomerNbr.setEditable(false);
		textField_ShowCustomerNbr.setBounds(158, 231, 138, 22);
		panel.add(textField_ShowCustomerNbr);
		textField_ShowCustomerNbr.setColumns(10);

		JButton btnAddCustomer = new JButton("L\u00E4gg till kund");
		btnAddCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField_Name.getText();
				String phoneNbr = textField_Phone.getText();
				String adress = textField_Adress.getText() + " " + textField_PostalNbr.getText() + " " + textField_City.getText();
				String email = textField_Email.getText();


				controller.addCustomer(name, phoneNbr, adress, email);
			}
		});
		btnAddCustomer.setBounds(12, 273, 138, 25);
		panel.add(btnAddCustomer);

		JButton btnSearchCustomer = new JButton("S\u00F6k");
		btnSearchCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchString = textField_FindCustomer.getText();
				controller.findCustomer(searchString);
			}
		});
		btnSearchCustomer.setBounds(304, 344, 97, 25);
		panel.add(btnSearchCustomer);

		JButton btnUpdateCustomer = new JButton("Uppdatera kund");
		btnUpdateCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String customerNbr = textField_ShowCustomerNbr.getText();
				String name = textField_Name.getText();
				String telephone = textField_Phone.getText();
				String address = textField_Adress.getText() + " " + textField_PostalNbr.getText();
				String email = textField_Email.getText();

				controller.updateCustomer(customerNbr, name, telephone, address, email);
			}
		});
		btnUpdateCustomer.setBounds(158, 273, 138, 25);
		panel.add(btnUpdateCustomer);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(459, 24, 452, 362);
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

	@Override
	public JPanel getContent() {
		return panel;
	}
}
