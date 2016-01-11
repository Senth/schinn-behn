package se.lu.sysa11.schinnbehn.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
	private static final int CUSTOMER_COLUMN_EMAIL = 2;
	private static final int CUSTOMER_COLUMN_PHONENUMBER = 3;
	private static final int CUSTOMER_COLUMN_ADRESS = 4;

	/**
	 * Can't have panel in base class as we're not able to access WindowBuilder correctly
	 * then
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
		lblNewCustomer.setBounds(LEFT_COLUMN_1_POS, 24, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblNewCustomer);

		JLabel lblName = new JLabel("Namn:");
		lblName.setBounds(LEFT_COLUMN_1_POS, 57, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblName);

		JLabel lblPhoneNumber = new JLabel("Telefonnr:");
		lblPhoneNumber.setBounds(LEFT_COLUMN_1_POS, 86, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblPhoneNumber);

		JLabel lblAdress = new JLabel("Adress:");
		lblAdress.setBounds(LEFT_COLUMN_1_POS, 115, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblAdress);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(LEFT_COLUMN_1_POS, 145, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblEmail);

		JLabel lblFoundCustomer = new JLabel("Kundnr:");
		lblFoundCustomer.setBounds(LEFT_COLUMN_1_POS, 174, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblFoundCustomer);

		JLabel lblFindCustomer = new JLabel("Hitta kund");
		lblFindCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFindCustomer.setBounds(LEFT_COLUMN_1_POS, 341, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblFindCustomer);

		JLabel lblKundnr = new JLabel("Filtrera kund:");
		lblKundnr.setBounds(LEFT_COLUMN_1_POS, 378, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblKundnr);

		textField_Name = new JTextField();
		textField_Name.setBounds(LEFT_COLUMN_2_POS, 57, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Name);
		textField_Name.setColumns(10);

		textField_Phone = new JTextField();
		textField_Phone.setBounds(LEFT_COLUMN_2_POS, 86, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Phone);
		textField_Phone.setColumns(10);

		textField_Adress = new JTextField();
		textField_Adress.setBounds(LEFT_COLUMN_2_POS, 115, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Adress);
		textField_Adress.setColumns(10);

		textField_Email = new JTextField();
		textField_Email.setBounds(LEFT_COLUMN_2_POS, 145, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Email);
		textField_Email.setColumns(10);

		textField_FindCustomer = new JTextField();
		textField_FindCustomer.setBounds(LEFT_COLUMN_2_POS, 378, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_FindCustomer);
		textField_FindCustomer.setColumns(10);
		textField_FindCustomer.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				populateTable();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				populateTable();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				populateTable();
			}
		});

		textField_ShowCustomerNbr = new JTextField();
		textField_ShowCustomerNbr.setEditable(false);
		textField_ShowCustomerNbr.setBounds(LEFT_COLUMN_2_POS, 174, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
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
		btnAddCustomer.setBounds(LEFT_COLUMN_1_POS, 216, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnAddCustomer);

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
		btnUpdateCustomer.setBounds(LEFT_COLUMN_2_POS, 216, BUTTON_WIDTH, BUTTON_HEIGHT);
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
					String orderNumber = null;
					if (value instanceof Integer) {
						orderNumber = String.valueOf(value);
					} else if (value instanceof String) {
						orderNumber = (String) value;
					}

					if (orderNumber != null) {
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
				textField_OrdersTotal.setText("");
				while (tableModel_Order.getRowCount() > 0) {
					tableModel_Order.removeRow(0);
				}
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
		lblTotaltexklMoms.setBounds(624, 313, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(lblTotaltexklMoms);

		String column_namesCustomer[] = { "Kundnummer", "Namn", "E-mail", "Telefonnummer", "Address" };
		tableModel_Customer = new DefaultTableModel(new Object[][] {}, column_namesCustomer) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case CUSTOMER_COLUMN_ID:
					return String.class;
				case CUSTOMER_COLUMN_NAME:
					return String.class;
				case CUSTOMER_COLUMN_EMAIL:
					return String.class;
				case CUSTOMER_COLUMN_ADRESS:
					return String.class;
				case CUSTOMER_COLUMN_PHONENUMBER:
					return String.class;
				default:
					return String.class;
				}
			}

		};

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(LEFT_COLUMN_1_POS, 413, 884, 254);
		panel.add(scrollPane_1);

		table_Customer = new JTable();
		scrollPane_1.setViewportView(table_Customer);

		table_Customer.setAutoCreateRowSorter(true);
		table_Customer.setModel(tableModel_Customer);

		JButton btnNewOrder = new JButton("Ny Order");
		btnNewOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String customerNbr = textField_ShowCustomerNbr.getText();
				if (customerNbr != null) {
					controller.newOrder(customerNbr);
				}

			}
		});
		btnNewOrder.setBounds(459, 313, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnNewOrder);

		table_Customer.addMouseListener(new TableClickListener() {
			@Override
			public void onClick(JTable table, int row) {
				String customerNumber = (String) tableModel_Customer.getValueAt(row, CUSTOMER_COLUMN_ID);
				if (customerNumber != null) {
					setCustomer(controller.findCustomer(customerNumber));
				}
			}

		});

		populateTable();

		setInitialized(true);

	}

	private void setCustomer(Customer customer) {
		while (tableModel_Order.getRowCount() > 0) {
			tableModel_Order.removeRow(0);
		}

		double sum = 0;

		if (customer != null) {
			textField_ShowCustomerNbr.setText(customer.getCustomerNbr());
			textField_Name.setText(customer.getName());
			textField_Adress.setText(customer.getBillingadress());
			textField_Email.setText(customer.getEmail());
			textField_Phone.setText(customer.getTelephoneNbr());

			// calculate sum
			for (Order order : customer.getOrders().values()) {
				sum += order.getTotalPrice();
				Object[] row = { order.getOrderDate(), order.getOrderNbr(), order.getTotalPrice() };
				tableModel_Order.addRow(row);
			}

			textField_OrdersTotal.setText(Double.toString(sum));
		} else {
			textField_Name.setText("");
			textField_Phone.setText("");
			textField_Adress.setText("");
			textField_Email.setText("");
			textField_ShowCustomerNbr.setText("");
			textField_OrdersTotal.setText("");
		}
	}

	private void populateTable() {
		populateTable(controller.findCustomers(textField_FindCustomer.getText()));
	}

	private void populateTable(List<Customer> customers) {
		// Remove rows
		while (tableModel_Customer.getRowCount() > 0) {
			tableModel_Customer.removeRow(0);
		}

		// Add products
		for (Customer customer : customers) {
			Object[] row = { customer.getCustomerNbr(), customer.getName(), customer.getEmail(), customer.getTelephoneNbr(),
					customer.getBillingadress() };
			tableModel_Customer.addRow(row);
		}
	}

	@Override
	public JPanel getContent() {
		return panel;
	}
}
