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
	private JTable table_Orders;
	private JTextField textField_SearchProduct;
	private JTextField textField_SearchOrder;

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
		lblName.setBounds(12, 150, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblName);

		JLabel lblStreetAdress = new JLabel("Gatuaddress:");
		lblStreetAdress.setBounds(12, 208, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblStreetAdress);

		JLabel lblPhoneNbr = new JLabel("Telefonnr:");
		lblPhoneNbr.setBounds(12, 179, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblPhoneNbr);

		JLabel lblPostalNbr = new JLabel("Postnummer:");
		lblPostalNbr.setBounds(12, 237, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblPostalNbr);

		JLabel lblCity = new JLabel("Stad:");
		lblCity.setBounds(12, 266, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblCity);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 295, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblEmail);

		textField_CustomerNbr = new JTextField();
		textField_CustomerNbr.setBounds(12, 101, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_CustomerNbr);
		textField_CustomerNbr.setColumns(10);

		textField_Name = new JTextField();
		textField_Name.setEditable(false);
		textField_Name.setBounds(107, 150, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Name);
		textField_Name.setColumns(10);

		textField_PhoneNbr = new JTextField();
		textField_PhoneNbr.setEditable(false);
		textField_PhoneNbr.setText("");
		textField_PhoneNbr.setBounds(107, 179, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_PhoneNbr);
		textField_PhoneNbr.setColumns(10);

		textField_StreetAdress = new JTextField();
		textField_StreetAdress.setEditable(false);
		textField_StreetAdress.setBounds(107, 208, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_StreetAdress);
		textField_StreetAdress.setColumns(10);

		textField_PostalNbr = new JTextField();
		textField_PostalNbr.setEditable(false);
		textField_PostalNbr.setBounds(107, 237, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_PostalNbr);
		textField_PostalNbr.setColumns(10);

		textField_City = new JTextField();
		textField_City.setEditable(false);
		textField_City.setText("");
		textField_City.setBounds(107, 295, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_City);
		textField_City.setColumns(10);

		textField_Email = new JTextField();
		textField_Email.setEditable(false);
		textField_Email.setText("");
		textField_Email.setBounds(107, 266, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Email);
		textField_Email.setColumns(10);

		JScrollPane scrollPane_Products = new JScrollPane();
		scrollPane_Products.setBounds(346, 150, 438, 319);
		panel.add(scrollPane_Products);

		String columnHeadersForProducts[] = { "Produktnamn", "Pris", "Lagersaldo" };
		table_Products = new JTable();
		table_Products.setModel(new DefaultTableModel(new Object[][] {}, columnHeadersForProducts));
		scrollPane_Products.setViewportView(table_Products);

		JLabel lblProducts = new JLabel("Produkter");
		lblProducts.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProducts.setBounds(346, 35, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblProducts);

		JScrollPane scrollPane_Orders = new JScrollPane();
		scrollPane_Orders.setBounds(864, 150, 438, 319);
		panel.add(scrollPane_Orders);

		String columnHeadersForOrders[] = { "Produktnamn", "Pris", "Antal", "Summa" };
		table_Orders = new JTable();
		table_Orders.setModel(new DefaultTableModel(new Object[][] {}, columnHeadersForOrders));
		scrollPane_Orders.setViewportView(table_Orders);

		JLabel lblOrder = new JLabel("Order");
		lblOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOrder.setBounds(864, 35, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblOrder);

		JButton btnAddToOrder = new JButton(">");
		btnAddToOrder.setBounds(796, 266, 56, 25);
		panel.add(btnAddToOrder);

		JButton btnRemoveFromOrder = new JButton("<");
		btnRemoveFromOrder.setBounds(796, 304, 56, 25);
		panel.add(btnRemoveFromOrder);

		JButton btnCreateOrder = new JButton("Skapa order");
		btnCreateOrder.setBounds(864, 482, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnCreateOrder);

		JLabel lblSearchProduct = new JLabel("S\u00F6k produkt:");
		lblSearchProduct.setBounds(346, 73, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblSearchProduct);

		textField_SearchProduct = new JTextField();
		textField_SearchProduct.setBounds(346, 101, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_SearchProduct);
		textField_SearchProduct.setColumns(10);

		JButton btnSearchProduct = new JButton("S\u00F6k");
		btnSearchProduct.setBounds(496, 101, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnSearchProduct);

		JLabel lblSearchOrder = new JLabel("S\u00F6k order:");
		lblSearchOrder.setBounds(864, 73, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblSearchOrder);

		textField_SearchOrder = new JTextField();
		textField_SearchOrder.setBounds(864, 101, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_SearchOrder);
		textField_SearchOrder.setColumns(10);

		JButton btnSearchOrder = new JButton("S\u00F6k");
		btnSearchOrder.setBounds(1014, 101, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnSearchOrder);

		JButton btnChangeOrder = new JButton("\u00C4ndra order");
		btnChangeOrder.setBounds(1014, 482, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnChangeOrder);

		JButton btnRemoveOrder = new JButton("Ta bort order");
		btnRemoveOrder.setBounds(1164, 482, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnRemoveOrder);


	}

	@Override
	public JPanel getContent() {
		return panel;
	}
}
