package se.lu.sysa11.schinnbehn.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
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

import se.lu.sysa11.schinnbehn.controller.OrderController;
import se.lu.sysa11.schinnbehn.model.Customer;
import se.lu.sysa11.schinnbehn.model.Order;
import se.lu.sysa11.schinnbehn.model.OrderLine;
import se.lu.sysa11.schinnbehn.model.Product;

/**
 * @author
 */
public class OrderGui extends Gui<OrderController> {
	/**
	 * @param window
	 */
	public OrderGui(Window window) {
		super(window);
	}

	private static final int PRODUCT_TABLE_COLUMN_NUMBER = 0;
	private static final int PRODUCT_TABLE_COLUMN_NAME = 1;
	private static final int PRODUCT_TABLE_COLUMN_PRICE = 2;
	private static final int ORDER_TABLE_COLUMN_NUMBER = 0;
	private static final int ORDER_TABLE_COLUMN_NAME = 1;
	private static final int ORDER_TABLE_COLUMN_PRICE = 2;
	private static final int ORDER_TABLE_COLUMN_QUANTITY = 3;
	private static final int ORDER_TABLE_COLUMN_SUM = 4;
	/**
	 * Can't have panel in base class as we're not able to access WindowBuilder correctly
	 * then
	 */
	private JPanel panel = new JPanel();
	private JTextField textField_FindOrderNbr;
	private JTextField textField_DeliveryAddress;
	private JTextField textField_CustomerNbr;
	private JTextField textField_CustomerName;
	private JTable table_Products;
	private JTable table_Orders;
	private JTextField textField_SearchProduct;
	private JTextField textField_TotalSum;
	private DefaultTableModel tableModel_Orders;
	private DefaultTableModel tableModel_Products;
	private HashMap<String, OrderLine> orderLines = new HashMap<String, OrderLine>();
	private JTextField textField_CurrentOrder;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Calendar cal = Calendar.getInstance();

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void initialize() {
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1500, 800));

		JButton btnSearchOrder = new JButton("S\u00F6k");
		btnSearchOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchString = textField_FindOrderNbr.getText();
				Order order = controller.findOrder(searchString);
				if (order == null) {
					textField_CurrentOrder.setText("");
				}
				setOrder(order);
			}
		});
		btnSearchOrder.setBounds(LEFT_COLUMN_3_POS, 160, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnSearchOrder);

		JButton btnAddToOrder = new JButton(">");
		btnAddToOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table_Products.getSelectedRows();

				if (selectedRows.length > 0 && selectedRows != null) {

					for (int i : selectedRows) {
						int rowIndex = table_Products.convertRowIndexToModel(i);
						addProductToOrder(rowIndex);
					}
				} else if (selectedRows.length == 0) {
					window.showNotificationError("Inga produkter markerade.");
				}
				updateOrderSum();
			}

		});
		btnAddToOrder.setBounds(660, 423, 45, 25);
		panel.add(btnAddToOrder);

		JButton btnRemoveFromOrder = new JButton("<");
		btnRemoveFromOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table_Orders.getSelectedRows();

				if (selectedRows != null && selectedRows.length > 0) {
					ArrayList<Integer> rowsToRemove = new ArrayList<Integer>();

					for (int i : selectedRows) {
						int rowIndex = table_Orders.convertRowIndexToModel(i);

						orderLines.remove(tableModel_Orders.getValueAt(rowIndex, ORDER_TABLE_COLUMN_NUMBER));
						rowsToRemove.add(rowIndex);
						updateOrderSum();

					}
					Collections.sort(rowsToRemove);
					Collections.reverse(rowsToRemove);

					for (int rowIndex : rowsToRemove) {
						tableModel_Orders.removeRow(rowIndex);
					}
					window.showNotificationSuccess("Produkter borttagna fr\u00E5n ordern");
				} else if (selectedRows == null || selectedRows.length == 0) {
					window.showNotificationError("Inga orderrader markerade.");
				}
			}
		});
		btnRemoveFromOrder.setBounds(660, 461, 45, 25);
		panel.add(btnRemoveFromOrder);

		JButton btnCreateOrder = new JButton("Skapa order");
		btnCreateOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!orderLines.isEmpty() && controller.findCustomer(textField_CustomerNbr.getText()) != null
						&& !textField_DeliveryAddress.getText().isEmpty()) {
					Order order = new Order();
					HashSet<OrderLine> lines = new HashSet<OrderLine>();

					order.setMadeby(controller.findCustomer(textField_CustomerNbr.getText()));
					order.setDeliveryAdress(textField_DeliveryAddress.getText());
					order.setOrderDate(dateFormat.format(cal.getTime()));

					for (OrderLine orderLine : orderLines.values()) {
						lines.add(orderLine);
					}
					order.setOrderLines(lines);
					controller.findCustomer(textField_CustomerNbr.getText()).addOrder(order);
					controller.addOrder(order);
					window.showNotificationSuccess("Order med ordernummer " + order.getOrderNbr() + " tillagd till kund med kundnummer: "
							+ order.getMadeby().getCustomerNbr());
					orderLines.clear();
					clearTable(tableModel_Orders);
					updateOrderSum();
				} else if (orderLines.isEmpty()) {
					window.showNotificationError("Ordern inte tillagd, hittade inga orderrader i ordern");
				} else if (controller.findCustomer(textField_CustomerNbr.getText()) == null) {
					window.showNotificationError("Ordern inte tillagd, finns ingen kund med kundnummer: " + textField_CustomerNbr.getText() + ".");
				} else if (textField_DeliveryAddress.getText().isEmpty()) {
					window.showNotificationError("Ingen leveransadress ifylld.");
				}

			}
		});
		btnCreateOrder.setBounds(1063, 712, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnCreateOrder);

		JButton btnChangeOrder = new JButton("\u00C4ndra order");
		btnChangeOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Order tmpOrder = controller.findOrder(textField_CurrentOrder.getText());

				if (tmpOrder != null && !textField_DeliveryAddress.getText().isEmpty()) {
					tmpOrder.clearOrderLines();
					tmpOrder.setDeliveryAdress(textField_DeliveryAddress.getText());

					for (OrderLine tmpOrderLine : orderLines.values()) {
						tmpOrder.addOrderLine(tmpOrderLine);
					}
					window.showNotificationSuccess("Order med ordernummer: " + tmpOrder.getOrderNbr() + " �ndrad.");

				} else if (tmpOrder == null || textField_CurrentOrder.getText().isEmpty()) {
					window.showNotificationError("Inget ordernummer ifyllt.");
				} else if (textField_DeliveryAddress.getText().isEmpty()) {
					window.showNotificationError("Ingen leveransadress ifylld.");
				}
			}
		});
		btnChangeOrder.setBounds(1213, 712, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnChangeOrder);

		JButton btnSearchCustomerNbr = new JButton("S\u00F6k");
		btnSearchCustomerNbr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String searchString = textField_CustomerNbr.getText();
				Customer tmpCustomer = controller.findCustomer(searchString);

				textField_CurrentOrder.setText("");
				orderLines.clear();
				clearTable(tableModel_Orders);

				if (tmpCustomer != null) {
					textField_CustomerName.setText(tmpCustomer.getName());
					textField_DeliveryAddress.setText(tmpCustomer.getBillingadress());
					textField_FindOrderNbr.setText("");
				} else {
					textField_CustomerName.setText("");
					textField_DeliveryAddress.setText("");
					textField_FindOrderNbr.setText("");
				}
				textField_TotalSum.setText("");

			}
		});
		btnSearchCustomerNbr.setBounds(LEFT_COLUMN_3_POS, 73, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnSearchCustomerNbr);

		JLabel lblSearchOrder = new JLabel("Order");
		lblSearchOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchOrder.setBounds(LEFT_COLUMN_1_POS, 35, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblSearchOrder);

		JLabel lblFindOrderNbr = new JLabel("Ordernr:");
		lblFindOrderNbr.setBounds(LEFT_COLUMN_1_POS, 160, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblFindOrderNbr);

		JLabel lblDeliveryAddress = new JLabel("Leveransaddress:");
		lblDeliveryAddress.setBounds(LEFT_COLUMN_1_POS, 131, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblDeliveryAddress);

		JLabel lblCustomerName = new JLabel("Kundnamn:");
		lblCustomerName.setBounds(LEFT_COLUMN_1_POS, 102, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblCustomerName);

		JLabel lblCustomerNbr = new JLabel("Kundnr:");
		lblCustomerNbr.setBounds(LEFT_COLUMN_1_POS, 73, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblCustomerNbr);

		JLabel lblAddProducts = new JLabel("L\u00E4gg till produkter");
		lblAddProducts.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddProducts.setBounds(LEFT_COLUMN_1_POS, 198, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblAddProducts);

		JLabel lblOrder = new JLabel("Orderrader");
		lblOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOrder.setBounds(715, 198, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblOrder);

		JLabel lblSearchProduct = new JLabel("Filtrera produkt:");
		lblSearchProduct.setBounds(LEFT_COLUMN_1_POS, 673, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblSearchProduct);

		JLabel lblTotalSumma = new JLabel("Total summa (exkl. moms):");
		lblTotalSumma.setBounds(1034, 673, 210, 25);
		panel.add(lblTotalSumma);

		JLabel lblCurrentOrder = new JLabel("Aktuellt ordernummer:");
		lblCurrentOrder.setBounds(1057, 199, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblCurrentOrder);

		textField_FindOrderNbr = new JTextField();
		textField_FindOrderNbr.setBounds(LEFT_COLUMN_2_POS, 160, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_FindOrderNbr);
		textField_FindOrderNbr.setColumns(10);

		textField_DeliveryAddress = new JTextField();
		textField_DeliveryAddress.setBounds(LEFT_COLUMN_2_POS, 131, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_DeliveryAddress);
		textField_DeliveryAddress.setColumns(10);

		textField_CustomerNbr = new JTextField();
		textField_CustomerNbr.setText("");
		textField_CustomerNbr.setBounds(LEFT_COLUMN_2_POS, 73, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_CustomerNbr);
		textField_CustomerNbr.setColumns(10);

		textField_CustomerName = new JTextField();
		textField_CustomerName.setEditable(false);
		textField_CustomerName.setBounds(LEFT_COLUMN_2_POS, 102, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_CustomerName);
		textField_CustomerName.setColumns(10);

		textField_SearchProduct = new JTextField();
		textField_SearchProduct.setBounds(LEFT_COLUMN_2_POS, 673, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_SearchProduct);
		textField_SearchProduct.setColumns(10);
		textField_SearchProduct.getDocument().addDocumentListener(new DocumentListener() {

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

		textField_TotalSum = new JTextField();
		textField_TotalSum.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_TotalSum.setEditable(false);
		textField_TotalSum.setBounds(1213, 674, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_TotalSum);
		textField_TotalSum.setColumns(10);

		textField_CurrentOrder = new JTextField();
		textField_CurrentOrder.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_CurrentOrder.setEditable(false);
		textField_CurrentOrder.setBounds(1213, 199, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_CurrentOrder);
		textField_CurrentOrder.setColumns(10);

		JScrollPane scrollPane_Products = new JScrollPane();
		scrollPane_Products.setBounds(LEFT_COLUMN_1_POS, 236, 636, 424);
		panel.add(scrollPane_Products);

		String columnHeadersForProducts[] = { "Nummer", "Produktnamn", "Pris (exkl. moms)" };
		tableModel_Products = new DefaultTableModel(new Object[][] {}, columnHeadersForProducts) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case PRODUCT_TABLE_COLUMN_NUMBER:
					return String.class;
				case PRODUCT_TABLE_COLUMN_NAME:
					return String.class;
				case PRODUCT_TABLE_COLUMN_PRICE:
					return Double.class;
				default:
					return String.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table_Products = new JTable();
		scrollPane_Products.setViewportView(table_Products);
		table_Products.setAutoCreateRowSorter(true);
		table_Products.setModel(tableModel_Products);
		table_Products.addMouseListener(new TableClickListener() {
			@Override
			public void onDoubleClick(JTable table, int row) {
				addProductToOrder(row);
				updateOrderSum();
			}
		});

		JScrollPane scrollPane_Orders = new JScrollPane();
		scrollPane_Orders.setBounds(715, 236, 636, 424);
		panel.add(scrollPane_Orders);

		String columnHeadersForOrders[] = { "Nummer", "Produktnamn", "Pris (exkl. moms)", "Antal", "Summa (exkl moms)" };
		tableModel_Orders = new DefaultTableModel(new Object[][] {}, columnHeadersForOrders) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case ORDER_TABLE_COLUMN_NUMBER:
					return String.class;
				case ORDER_TABLE_COLUMN_NAME:
					return String.class;
				case ORDER_TABLE_COLUMN_PRICE:
					return Double.class;
				case ORDER_TABLE_COLUMN_QUANTITY:
					return Integer.class;
				case ORDER_TABLE_COLUMN_SUM:
					return Double.class;
				default:
					return String.class;
				}

			}

			@Override
			public boolean isCellEditable(int row, int column) {
				switch (column) {
				case ORDER_TABLE_COLUMN_NAME:
					return false;
				case ORDER_TABLE_COLUMN_PRICE:
					return false;
				case ORDER_TABLE_COLUMN_QUANTITY:
					return true;
				case ORDER_TABLE_COLUMN_SUM:
					return false;
				default:
					return false;
				}
			}
		};

		table_Orders = new JTable();
		scrollPane_Orders.setViewportView(table_Orders);
		table_Orders.setAutoCreateRowSorter(true);
		table_Orders.setModel(tableModel_Orders);
		Action action = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				TableCellListener tableCellListener = (TableCellListener) e.getSource();

				Integer oldQuantity = (Integer) tableCellListener.getOldValue();
				Integer updatedQuantity = (Integer) tableCellListener.getNewValue();

				if (updatedQuantity != null && updatedQuantity > 0) {
					String productNbr = (String) tableModel_Orders.getValueAt(tableCellListener.getRow(), ORDER_TABLE_COLUMN_NUMBER);
					OrderLine orderLine = orderLines.get(productNbr);
					orderLine.setQuantity(updatedQuantity);
					try {
						double price = (Double) tableModel_Orders.getValueAt(tableCellListener.getRow(), ORDER_TABLE_COLUMN_PRICE);
						double totalSum = Double.parseDouble(textField_TotalSum.getText());
						double diff = (oldQuantity * price) - (updatedQuantity * price);
						tableModel_Orders.setValueAt(updatedQuantity * price, tableCellListener.getRow(), ORDER_TABLE_COLUMN_SUM);
						textField_TotalSum.setText(Double.toString(totalSum - diff));
					} catch (NumberFormatException exception) {
						// Do nothing
					}

				} else {
					tableModel_Orders.setValueAt(oldQuantity, tableCellListener.getRow(), ORDER_TABLE_COLUMN_QUANTITY);
					window.showNotificationError("Felaktigt v\u00E4rde, antal m\u00E5ste vara st\u00F6rre \u00E4n 0.");
				}

			}
		};

		new TableCellListener(table_Orders, action);

		populateTable();

		setInitialized(true);
	}

	/**
	 * Add the product from the specified row to the orderline
	 * @param productRow index of the product row
	 */
	private void addProductToOrder(int productRow) {
		Product tmpProduct = controller
				.findProduct((String) tableModel_Products.getValueAt(productRow, PRODUCT_TABLE_COLUMN_NUMBER));

		if (!orderLines.containsKey(tmpProduct.getProductNbr())) {
			OrderLine orderLine = new OrderLine();
			orderLine.setProduct(tmpProduct);
			orderLine.setQuantity(1);
			orderLine.setProductPrice(tmpProduct.getPrice());
			orderLines.put(tmpProduct.getProductNbr(), orderLine);
			Object[] row = { tmpProduct.getProductNbr(), tmpProduct.getName(), tmpProduct.getPrice(), orderLine.getQuantity(),
					tmpProduct.getPrice() };
			tableModel_Orders.addRow(row);
			window.showNotificationSuccess("Produkter tillagda till ordern.");
		} else if (orderLines.containsKey(tmpProduct.getProductNbr())) {
			window.showNotificationError("Produkten finns redan i ordern.");
		}
	}

	@Override
	public JPanel getContent() {
		return panel;
	}

	public void populateTable() {
		populateTable(controller.findProducts(textField_SearchProduct.getText()));
	}

	private void populateTable(List<Product> products) {
		clearTable(tableModel_Products);

		for (Product product : products) {
			if (product.isActive()) {
				Object[] row = { product.getProductNbr(), product.getName(), product.getPrice() };
				tableModel_Products.addRow(row);
			}
		}
	}

	public void setOrder(Order order) {

		clearTable(tableModel_Orders);
		orderLines.clear();

		if (order != null) {
			textField_CustomerName.setText(order.getMadeby().getName());
			textField_CustomerNbr.setText(order.getMadeby().getCustomerNbr());
			textField_DeliveryAddress.setText(order.getDeliveryAdress());
			textField_FindOrderNbr.setText(order.getOrderNbr());
			textField_CurrentOrder.setText(order.getOrderNbr());
			for (OrderLine tmpOrderLine : order.getOrderLines()) {
				Object[] row = { tmpOrderLine.getProduct().getProductNbr(), tmpOrderLine.getProduct().getName(), tmpOrderLine.getProductPrice(),
						tmpOrderLine.getQuantity(), tmpOrderLine.getLinePrice() };
				tableModel_Orders.addRow(row);
				orderLines.put(tmpOrderLine.getProduct().getProductNbr(), tmpOrderLine);
			}
		}
		updateOrderSum();
		// if (order != null) {
		// textField_TotalSum.setText(Double.toString(order.getTotalPrice()));
		// } else {
		// textField_TotalSum.setText("");
		// }
	}

	public void updateOrderSum() {
		double sum = 0;
		for (OrderLine tmpOrderLine : orderLines.values()) {
			sum += tmpOrderLine.getLinePrice();
		}
		textField_TotalSum.setText(Double.toString(sum));
	}

	public void setCustomer(Customer customer) {
		if (customer != null) {
			textField_CustomerName.setText(customer.getName());
			textField_CustomerNbr.setText(customer.getCustomerNbr());
			textField_DeliveryAddress.setText(customer.getBillingadress());
			textField_FindOrderNbr.setText("");
			textField_TotalSum.setText("");
			clearTable(tableModel_Orders);
			orderLines.clear();
		}
	}
}
