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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import se.lu.sysa11.schinnbehn.controller.CustomerController;
import se.lu.sysa11.schinnbehn.model.Customer;
import se.lu.sysa11.schinnbehn.model.Order;

/**
 * GUI for the Customer
 */
public class CustomerGui extends Gui<CustomerController> {
	/**
	 * @param window
	 */
	public CustomerGui(Window window) {
		super(window);
	}

	private static final int ORDER_COLUMN_DATE = 0;
	private static final int ORDER_COLUMN_ID = 1;
	private static final int ORDER_COLUMN_SUM = 2;

	private static final int CUSTOMER_COLUMN_ID = 0;
	private static final int CUSTOMER_COLUMN_NAME = 1;
	private static final int CUSTOMER_COLUMN_ADRESS = 2;

	/**
	 * Can't have panel in base class as we're not able to access WindowBuilder
	 * correctly then
	 */
	private JPanel panel = new JPanel();
	private JTextField textField_Name;
	private JTextField textField_Phone;
	private JTextField textField_Adress;
	private JTextField textField_Email;
	private JTextField textField_FindCustomer;
	private JTextField textField_ShowCustomerNbr;
	private JTable table_Orders;
	private DefaultTableModel tableModel_Order;
	private DefaultTableModel tableModel_Customer;
	private JTextField textField_OrdersTotal;
	private JTable table_Customer;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void initialize() {

		panel.setLayout(null);

		JLabel lblNewCustomer = new JLabel("Ny Kund");
		lblNewCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewCustomer.setBounds(12, 24, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblNewCustomer);

		JLabel lblName = new JLabel("Namn:");
		lblName.setBounds(12, 57, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblName);

		JLabel lblPhoneNumber = new JLabel("Telefonnr:");
		lblPhoneNumber.setBounds(12, 86, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblPhoneNumber);

		JLabel lblAdress = new JLabel("Adress:");
		lblAdress.setBounds(12, 115, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblAdress);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 145, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblEmail);

		JLabel lblFoundCustomer = new JLabel("Kundnr:");
		lblFoundCustomer.setBounds(12, 174, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblFoundCustomer);

		JLabel lblFindCustomer = new JLabel("Hitta kund");
		lblFindCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFindCustomer.setBounds(25, 341, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblFindCustomer);

		JLabel lblKundnr = new JLabel("Kundnr:");
		lblKundnr.setBounds(25, 378, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblKundnr);

		textField_Name = new JTextField();
		textField_Name.setBounds(158, 57, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Name);
		textField_Name.setColumns(10);

		textField_Phone = new JTextField();
		textField_Phone.setBounds(158, 86, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Phone);
		textField_Phone.setColumns(10);

		textField_Adress = new JTextField();
		textField_Adress.setBounds(158, 115, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Adress);
		textField_Adress.setColumns(10);

		textField_Email = new JTextField();
		textField_Email.setBounds(158, 145, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Email);
		textField_Email.setColumns(10);

		textField_FindCustomer = new JTextField();
		textField_FindCustomer.setBounds(133, 378, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_FindCustomer);
		textField_FindCustomer.setColumns(10);

		textField_ShowCustomerNbr = new JTextField();
		textField_ShowCustomerNbr.setEditable(false);
		textField_ShowCustomerNbr.setBounds(158, 174, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_ShowCustomerNbr);
		textField_ShowCustomerNbr.setColumns(10);

		JButton btnAddCustomer = new JButton("L\u00E4gg till kund");
		btnAddCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField_Name.getText();
				String phoneNbr = textField_Phone.getText();
				String adress = textField_Adress.getText();
				String email = textField_Email.getText();
				String customerNbr = controller.addCustomer(name, phoneNbr, adress, email);

				if (customerNbr != null) {
					textField_ShowCustomerNbr.setText(customerNbr);
				}
			}
		});
		btnAddCustomer.setBounds(12, 216, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnAddCustomer);

		JButton btnSearchCustomer = new JButton("S\u00F6k");
		btnSearchCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchString = textField_FindCustomer.getText();
				Customer tmpCustomer = controller.findCustomer(searchString);
				double sum = 0;

				while (tableModel_Order.getRowCount() > 0) {
					tableModel_Order.removeRow(0);
				}

				if (tmpCustomer != null) {
					textField_Name.setText(tmpCustomer.getName());
					textField_Phone.setText(tmpCustomer.getTelephoneNbr());
					textField_Adress.setText(tmpCustomer.getAddress());
					textField_Email.setText(tmpCustomer.getEmail());
					textField_ShowCustomerNbr.setText(tmpCustomer.getCustomerNbr());

					for (Order tmpOrder : tmpCustomer.getOrders().values()) {
						int orderNbr = Integer.parseInt(tmpOrder.getOrderNbr());
						Object[] row = { tmpOrder.getOrderDate(), orderNbr, tmpOrder.getTotalPrice() };
						tableModel_Order.addRow(row);
						sum += tmpOrder.getTotalPrice();

					}

				} else {
					textField_Name.setText("");
					textField_Phone.setText("");
					textField_Adress.setText("");
					textField_Email.setText("");
					textField_ShowCustomerNbr.setText(searchString);
				}
				textField_OrdersTotal.setText(Double.toString(sum));
			}
		});
		btnSearchCustomer.setBounds(304, 379, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnSearchCustomer);

		JButton btnUpdateCustomer = new JButton("Uppdatera kund");
		btnUpdateCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String customerNbr = textField_ShowCustomerNbr.getText();
				String name = textField_Name.getText();
				String telephone = textField_Phone.getText();
				String address = textField_Adress.getText();
				String email = textField_Email.getText();

				controller.updateCustomer(customerNbr, name, telephone, address, email);
			}
		});
		btnUpdateCustomer.setBounds(158, 216, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnUpdateCustomer);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(459, 24, 452, 277);
		panel.add(scrollPane);

		String column_names[] = { "Datum", "Ordernummer", "Summa (exkl. moms)" };
		tableModel_Order = new DefaultTableModel(new Object[][] {}, column_names) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case ORDER_COLUMN_DATE:
					return String.class;
				case ORDER_COLUMN_ID:
					return Integer.class;
				case ORDER_COLUMN_SUM:
					return Double.class;
				default:
					return String.class;
				}
			}

		};

		table_Orders = new JTable();
		table_Orders.setAutoCreateRowSorter(true);
		table_Orders.setModel(tableModel_Order);
		table_Orders.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table_Orders);
		table_Orders.addMouseListener(new TableClickListener() {
			@Override
			public void onDoubleClick(JTable table, int row) {
				try {
					Object value = tableModel_Order.getValueAt(row, ORDER_COLUMN_ID);
					if (value instanceof Integer) {
						String orderNumber = String.valueOf(value);
						controller.gotoOrder(orderNumber);
					}
				} catch (ArrayIndexOutOfBoundsException exception) {
					// Does nothing
				}
			}
		});

		JButton btnClearFields = new JButton("T\u00F6m f\u00E4lten");
		btnClearFields.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField_Name.setText("");
				textField_Phone.setText("");
				textField_Adress.setText("");
				textField_Email.setText("");
				textField_ShowCustomerNbr.setText("");
			}
		});
		btnClearFields.setBounds(304, 216, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnClearFields);

		textField_OrdersTotal = new JTextField();
		textField_OrdersTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_OrdersTotal.setEditable(false);
		textField_OrdersTotal.setBounds(763, 313, 146, 26);
		panel.add(textField_OrdersTotal);
		textField_OrdersTotal.setColumns(10);

		JLabel lblTotaltexklMoms = new JLabel("Totalt (exkl. moms):");
		lblTotaltexklMoms.setBounds(624, 313, 138, 20);
		panel.add(lblTotaltexklMoms);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(25, 411, 411, 254);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 411, 254);
		panel_1.add(scrollPane_1);

		table_Customer = new JTable();
		scrollPane_1.setViewportView(table_Customer);

		setInitialized(true);

		String column_namesCustomer[] = { "Kundnummer", "Namn", "Adress" };
		tableModel_Customer = new DefaultTableModel(new Object[][] {}, column_namesCustomer) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case ORDER_COLUMN_DATE:
					return String.class;
				case ORDER_COLUMN_ID:
					return Integer.class;
				case ORDER_COLUMN_SUM:
					return Double.class;
				default:
					return String.class;
				}
			}

		};

		table_Customer.setAutoCreateRowSorter(true);
		table_Customer.setModel(tableModel_Customer);

	}

	@Override
	public JPanel getContent() {
		return panel;
	}
}
