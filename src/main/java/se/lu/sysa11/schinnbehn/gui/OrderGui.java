package se.lu.sysa11.schinnbehn.gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import se.lu.sysa11.schinnbehn.controller.OrderController;

/**
 * @author
 */
public class OrderGui extends Gui<OrderController> {
	/**
	 * Can't have panel in base class as we're not able to access WindowBuilder correctly
	 * then
	 */
	private JPanel panel = new JPanel();
	private JTextField textField_CustomerNbr;
	private JTextField textField_Name;
	private JTextField textField_PhoneNbr;
	private JTextField textField_StreetAdress;
	private JTextField textField_PostalNbr;
	private JTextField textField_City;
	private JTextField textField_Email;
	private JTable table_Products;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void initialize() {
		panel.setLayout(null);

		JButton btnSearch = new JButton("S\u00F6k");
		btnSearch.setBounds(162, 101, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnSearch);

		JLabel lblSearchCustomer = new JLabel("S\u00F6k kund");
		lblSearchCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchCustomer.setBounds(12, 35, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblSearchCustomer);

		JLabel lblFindCustomerNbr = new JLabel("Kundnr:");
		lblFindCustomerNbr.setBounds(12, 73, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblFindCustomerNbr);

		JLabel lblName = new JLabel("Namn:");
		lblName.setBounds(12, 205, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblName);

		JLabel lblStreetAdress = new JLabel("Gatuaddress:");
		lblStreetAdress.setBounds(12, 272, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblStreetAdress);

		JLabel lblPhoneNbr = new JLabel("Telefonnr:");
		lblPhoneNbr.setBounds(12, 243, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblPhoneNbr);

		JLabel lblPostalNbr = new JLabel("Postnummer:");
		lblPostalNbr.setBounds(12, 310, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblPostalNbr);

		JLabel lblCity = new JLabel("Stad:");
		lblCity.setBounds(12, 342, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblCity);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 371, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblEmail);

		textField_CustomerNbr = new JTextField();
		textField_CustomerNbr.setBounds(12, 101, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_CustomerNbr);
		textField_CustomerNbr.setColumns(10);

		textField_Name = new JTextField();
		textField_Name.setEditable(false);
		textField_Name.setBounds(107, 206, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Name);
		textField_Name.setColumns(10);

		textField_PhoneNbr = new JTextField();
		textField_PhoneNbr.setEditable(false);
		textField_PhoneNbr.setText("");
		textField_PhoneNbr.setBounds(107, 244, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_PhoneNbr);
		textField_PhoneNbr.setColumns(10);

		textField_StreetAdress = new JTextField();
		textField_StreetAdress.setEditable(false);
		textField_StreetAdress.setBounds(107, 273, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_StreetAdress);
		textField_StreetAdress.setColumns(10);

		textField_PostalNbr = new JTextField();
		textField_PostalNbr.setEditable(false);
		textField_PostalNbr.setBounds(107, 307, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_PostalNbr);
		textField_PostalNbr.setColumns(10);

		textField_City = new JTextField();
		textField_City.setEditable(false);
		textField_City.setText("");
		textField_City.setBounds(107, 339, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_City);
		textField_City.setColumns(10);

		textField_Email = new JTextField();
		textField_Email.setEditable(false);
		textField_Email.setText("");
		textField_Email.setBounds(107, 368, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Email);
		textField_Email.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(346, 48, 385, 319);
		panel.add(scrollPane);

		String columnHeaders[] = { "Produktnr", "Produktnamn", "Pris", "Lagersaldo" };
		table_Products = new JTable();
		table_Products.setModel(new DefaultTableModel(new Object[][] {}, columnHeaders));
		scrollPane.setViewportView(table_Products);


	}

	@Override
	public JPanel getContent() {
		return panel;
	}
}
