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
	private JTextField textField_OrderNbr;
	private JTextField textField_Name;
	private JTextField textField_CustomerNbr;
	private JTextField textField_StreetAdress;
	private JTable table_Products;
	private JTable table_Orders;
	private JTextField textField_SearchProduct;
	private JTextField textField;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void initialize() {
		panel.setLayout(null);

		JButton btnSearch = new JButton("S\u00F6k");
		btnSearch.setBounds(274, 73, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnSearch);

		JLabel lblSearchOrder = new JLabel("Order");
		lblSearchOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchOrder.setBounds(12, 35, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblSearchOrder);

		JLabel lblFindOrderNbr = new JLabel("Ordernr:");
		lblFindOrderNbr.setBounds(12, 73, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblFindOrderNbr);

		JLabel lblDeliveryAddress = new JLabel("Leveransaddress:");
		lblDeliveryAddress.setBounds(12, 102, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblDeliveryAddress);

		JLabel lblCustomerName = new JLabel("Kundnamn:");
		lblCustomerName.setBounds(12, 160, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblCustomerName);

		JLabel lblCustomerNbr = new JLabel("Kundnr:");
		lblCustomerNbr.setBounds(12, 131, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblCustomerNbr);

		textField_OrderNbr = new JTextField();
		textField_OrderNbr.setBounds(124, 73, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_OrderNbr);
		textField_OrderNbr.setColumns(10);

		textField_Name = new JTextField();
		textField_Name.setBounds(124, 102, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Name);
		textField_Name.setColumns(10);

		textField_CustomerNbr = new JTextField();
		textField_CustomerNbr.setText("");
		textField_CustomerNbr.setBounds(124, 131, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_CustomerNbr);
		textField_CustomerNbr.setColumns(10);

		textField_StreetAdress = new JTextField();
		textField_StreetAdress.setEditable(false);
		textField_StreetAdress.setBounds(124, 160, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_StreetAdress);
		textField_StreetAdress.setColumns(10);

		JScrollPane scrollPane_Products = new JScrollPane();
		scrollPane_Products.setBounds(12, 236, 636, 424);
		panel.add(scrollPane_Products);

		String columnHeadersForProducts[] = { "Produktnamn", "Pris", "Lagersaldo" };
		table_Products = new JTable();
		table_Products.setModel(new DefaultTableModel(new Object[][] {}, columnHeadersForProducts));
		scrollPane_Products.setViewportView(table_Products);

		JLabel lblAddProducts = new JLabel("L\u00E4gg till produkter");
		lblAddProducts.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddProducts.setBounds(12, 198, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblAddProducts);

		JScrollPane scrollPane_Orders = new JScrollPane();
		scrollPane_Orders.setBounds(715, 236, 636, 424);
		panel.add(scrollPane_Orders);

		String columnHeadersForOrders[] = { "Produktnamn", "Pris", "Antal", "Summa" };
		table_Orders = new JTable();
		table_Orders.setModel(new DefaultTableModel(new Object[][] {}, columnHeadersForOrders));
		scrollPane_Orders.setViewportView(table_Orders);

		JLabel lblOrder = new JLabel("Orderrader");
		lblOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOrder.setBounds(715, 198, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblOrder);

		JButton btnAddToOrder = new JButton(">");
		btnAddToOrder.setBounds(660, 423, 45, 25);
		panel.add(btnAddToOrder);

		JButton btnRemoveFromOrder = new JButton("<");
		btnRemoveFromOrder.setBounds(660, 461, 45, 25);
		panel.add(btnRemoveFromOrder);

		JButton btnCreateOrder = new JButton("Skapa order");
		btnCreateOrder.setBounds(913, 712, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnCreateOrder);

		JLabel lblSearchProduct = new JLabel("S\u00F6k produkt:");
		lblSearchProduct.setBounds(12, 673, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblSearchProduct);

		textField_SearchProduct = new JTextField();
		textField_SearchProduct.setBounds(12, 712, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_SearchProduct);
		textField_SearchProduct.setColumns(10);

		JButton btnSearchProduct = new JButton("S\u00F6k");
		btnSearchProduct.setBounds(162, 712, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnSearchProduct);

		JButton btnChangeOrder = new JButton("\u00C4ndra order");
		btnChangeOrder.setBounds(1063, 712, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnChangeOrder);

		JButton btnRemoveOrder = new JButton("Ta bort order");
		btnRemoveOrder.setBounds(1213, 712, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnRemoveOrder);

		JButton btnSearchCustomerNbr = new JButton("S\u00F6k");
		btnSearchCustomerNbr.setBounds(274, 131, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnSearchCustomerNbr);

		JLabel lblTotalSumma = new JLabel("Total summa:");
		lblTotalSumma.setBounds(1125, 673, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblTotalSumma);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(1213, 674, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField);
		textField.setColumns(10);


	}

	@Override
	public JPanel getContent() {
		return panel;
	}
}
