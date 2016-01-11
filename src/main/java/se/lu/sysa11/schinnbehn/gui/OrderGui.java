package se.lu.sysa11.schinnbehn.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

	private static final int PRODCUT_TABLE_COLUMN_NUMBER = 0;
	private static final int PRODUCT_TABLE_COLUMN_NAME = 1;
	private static final int PRODUCT_TABLE_COLUMN_PRICE = 2;
	private static final int ORDER_TABLE_COLUMN_NUMBER = 0;
	private static final int ORDER_TABLE_COLUMN_NAME = 1;
	private static final int ORDER_TABLE_COLUMN_PRICE = 2;
	private static final int ORDER_TABLE_COLUMN_QUANTITY = 3;
	private static final int ORDER_TABLE_COLUMN_SUM = 4;
	/**
	 * Can't have panel in base class as we're not able to access WindowBuilder
	 * correctly then
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

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void initialize() {
		panel.setLayout(null);

		JButton btnSearchOrder = new JButton("S\u00F6k");
		btnSearchOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchString = textField_FindOrderNbr.getText();
				Order order = controller.findOrder(searchString);
				setOrder(order);

			}
		});
		btnSearchOrder.setBounds(274, 160, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnSearchOrder);

		JButton btnAddToOrder = new JButton(">");
		btnAddToOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table_Products.getSelectedRows();

				if (selectedRows.length > 0 && selectedRows != null) {

					for (int i : selectedRows) {
						int rowIndex = table_Products.convertRowIndexToModel(i);
						Product tmpProduct = controller.findProduct(
								(String) tableModel_Products.getValueAt(rowIndex, PRODCUT_TABLE_COLUMN_NUMBER));

						if (!orderLines.containsKey(tmpProduct.getProductNbr())) {
							OrderLine orderLine = new OrderLine();
							orderLine.setProduct(tmpProduct);
							orderLine.setQuantity(1);
							orderLine.setProductPrice(tmpProduct.getPrice());
							orderLines.put(tmpProduct.getProductNbr(), orderLine);
							Object[] row = { tmpProduct.getProductNbr(), tmpProduct.getName(), tmpProduct.getPrice(),
									orderLine.getQuantity(), tmpProduct.getPrice() };
							tableModel_Orders.addRow(row);
							window.showNotificationSuccess("Produkter tillagda till ordern.");
						} else if (orderLines.containsKey(tmpProduct.getProductNbr())) {
							window.showNotificationError("Produkten finns redan i ordern.");
						}
					}
				} else if (selectedRows.length == 0) {
					window.showNotificationError("Inga produkter markerade.");
				}
				textField_TotalSum.setText(updateOrderSum());
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
						textField_TotalSum.setText(updateOrderSum());

					}
					Collections.sort(rowsToRemove);
					Collections.reverse(rowsToRemove);

					for (int rowIndex : rowsToRemove) {
						tableModel_Orders.removeRow(rowIndex);
					}
					window.showNotificationSuccess("Produkter borttagna från ordern");
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
					order.setOrderDate("Testdate");

					for (OrderLine orderLine : orderLines.values()) {
						lines.add(orderLine);
					}
					order.setOrderLine(lines);
					controller.findCustomer(textField_CustomerNbr.getText()).addOrder(order);
					controller.addOrder(order);
					window.showNotificationSuccess(
"Order med ordernummer " + order.getOrderNbr()
							+ " tillagd till kund med kundnummer: " + order.getMadeby().getCustomerNbr());
					orderLines.clear();
					clearTable(tableModel_Orders);
				} else if (orderLines.isEmpty()) {
					window.showNotificationError("Ordern inte tillagd, hittade inga orderrader i ordern");
				} else if (controller.findCustomer(textField_CustomerNbr.getText()) == null) {
					window.showNotificationError("Ordern inte tillagd, finns ingen kund med kundnummer: "
							+ textField_CustomerNbr.getText() + ".");
				} else if (textField_DeliveryAddress.getText().isEmpty()) {
					window.showNotificationError("Ingen leveransadress ifylld.");
				}

			}
		});
		btnCreateOrder.setBounds(1063, 712, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnCreateOrder);

		JButton btnChangeOrder = new JButton("\u00C4ndra order");
		btnChangeOrder.setBounds(1213, 712, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnChangeOrder);

		JButton btnSearchCustomerNbr = new JButton("S\u00F6k");
		btnSearchCustomerNbr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String searchString = textField_CustomerNbr.getText();
				Customer tmpCustomer = controller.findCustomer(searchString);

				orderLines.clear();
				while (tableModel_Orders.getRowCount() > 0) {
					tableModel_Orders.removeRow(0);
				}

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
		btnSearchCustomerNbr.setBounds(274, 73, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnSearchCustomerNbr);

		JLabel lblSearchOrder = new JLabel("Order");
		lblSearchOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchOrder.setBounds(12, 35, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblSearchOrder);

		JLabel lblFindOrderNbr = new JLabel("Ordernr:");
		lblFindOrderNbr.setBounds(12, 160, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblFindOrderNbr);

		JLabel lblDeliveryAddress = new JLabel("Leveransaddress:");
		lblDeliveryAddress.setBounds(12, 131, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblDeliveryAddress);

		JLabel lblCustomerName = new JLabel("Kundnamn:");
		lblCustomerName.setBounds(12, 102, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblCustomerName);

		JLabel lblCustomerNbr = new JLabel("Kundnr:");
		lblCustomerNbr.setBounds(12, 73, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblCustomerNbr);

		JLabel lblAddProducts = new JLabel("L\u00E4gg till produkter");
		lblAddProducts.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddProducts.setBounds(12, 198, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblAddProducts);

		JLabel lblOrder = new JLabel("Orderrader");
		lblOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOrder.setBounds(715, 198, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblOrder);

		JLabel lblSearchProduct = new JLabel("S\u00F6k produkt:");
		lblSearchProduct.setBounds(12, 673, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblSearchProduct);

		JLabel lblTotalSumma = new JLabel("Total summa (exkl. moms):");
		lblTotalSumma.setBounds(1034, 673, 210, 25);
		panel.add(lblTotalSumma);

		textField_FindOrderNbr = new JTextField();
		textField_FindOrderNbr.setBounds(124, 160, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_FindOrderNbr);
		textField_FindOrderNbr.setColumns(10);

		textField_DeliveryAddress = new JTextField();
		textField_DeliveryAddress.setBounds(124, 131, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_DeliveryAddress);
		textField_DeliveryAddress.setColumns(10);

		textField_CustomerNbr = new JTextField();
		textField_CustomerNbr.setText("");
		textField_CustomerNbr.setBounds(124, 73, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_CustomerNbr);
		textField_CustomerNbr.setColumns(10);

		textField_CustomerName = new JTextField();
		textField_CustomerName.setEditable(false);
		textField_CustomerName.setBounds(124, 102, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_CustomerName);
		textField_CustomerName.setColumns(10);

		textField_SearchProduct = new JTextField();
		textField_SearchProduct.setBounds(12, 712, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
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

		JScrollPane scrollPane_Products = new JScrollPane();
		scrollPane_Products.setBounds(12, 236, 636, 424);
		panel.add(scrollPane_Products);

		String columnHeadersForProducts[] = { "Nummer", "Produktnamn", "Pris (exkl. moms)" };
		tableModel_Products = new DefaultTableModel(new Object[][] {}, columnHeadersForProducts) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case PRODCUT_TABLE_COLUMN_NUMBER:
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
		table_Products.setAutoCreateRowSorter(true);
		table_Products.setModel(tableModel_Products);
		table_Products.getColumnModel().getColumn(0).setResizable(false);
		scrollPane_Products.setViewportView(table_Products);

		JScrollPane scrollPane_Orders = new JScrollPane();
		scrollPane_Orders.setBounds(715, 236, 636, 424);
		panel.add(scrollPane_Orders);

		String columnHeadersForOrders[] = { "Nummer", "Produktnamn", "Pris (exkl. moms)", "Antal",
				"Summa (exkl moms)" };
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
		table_Orders.setAutoCreateRowSorter(true);

		table_Orders.setModel(tableModel_Orders);
		table_Orders.getColumnModel().getColumn(0).setResizable(false);
		Action action = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				TableCellListener tableCellListener = (TableCellListener) e.getSource();

				Integer oldQuantity = (Integer) tableCellListener.getOldValue();
				Integer updatedQuantity = (Integer) tableCellListener.getNewValue();

				if (updatedQuantity != null && updatedQuantity > 0) {
					String productNbr = (String) tableModel_Orders.getValueAt(tableCellListener.getRow(),
							ORDER_TABLE_COLUMN_NUMBER);
					OrderLine orderLine = orderLines.get(productNbr);
					orderLine.setQuantity(updatedQuantity);
					double price = (Double) tableModel_Orders.getValueAt(tableCellListener.getRow(),
							ORDER_TABLE_COLUMN_PRICE);
					double totalSum = Double.parseDouble(textField_TotalSum.getText());
					double diff = (oldQuantity * price) - (updatedQuantity * price);
					tableModel_Orders.setValueAt(updatedQuantity * price, tableCellListener.getRow(),
							ORDER_TABLE_COLUMN_SUM);
					textField_TotalSum.setText(Double.toString(totalSum - diff));

				} else {
					tableModel_Orders.setValueAt(oldQuantity, tableCellListener.getRow(), ORDER_TABLE_COLUMN_QUANTITY);
					window.showNotificationError("Felaktigt vï¿½rde, antal mï¿½ste vara stï¿½rre ï¿½n 0.");
				}

			}
		};

		new TableCellListener(table_Orders, action);
		scrollPane_Orders.setViewportView(table_Orders);

		populateTable();

		setInitialized(true);
	}

	@Override
	public JPanel getContent() {
		return panel;
	}

	public void populateTable() {
		populateTable(controller.findProducts(textField_SearchProduct.getText()));
	}

	private void populateTable(List<Product> products) {
		// Remove rows
		while (tableModel_Products.getRowCount() > 0) {
			tableModel_Products.removeRow(0);
		}

		// Add products
		for (Product product : products) {
			if (product.isActive()) {
				Object[] row = { product.getProductNbr(), product.getName(), product.getPrice() };
				tableModel_Products.addRow(row);
			}
		}
	}

	public void setOrder(Order order) {

		clearTable(tableModel_Orders);

		if (order != null) {
			textField_CustomerName.setText(order.getMadeby().getName());
			textField_CustomerNbr.setText(order.getMadeby().getCustomerNbr());
			textField_DeliveryAddress.setText(order.getDeliveryAdress());
			textField_FindOrderNbr.setText(order.getOrderNbr());

			for (OrderLine tmpOrderLine : order.getOrderline()) {
				Object[] row = { tmpOrderLine.getProduct().getProductNbr(), tmpOrderLine.getProduct().getName(),
						tmpOrderLine.getProductPrice(), tmpOrderLine.getQuantity(), tmpOrderLine.getLinePrice() };
				tableModel_Orders.addRow(row);
				orderLines.put(tmpOrderLine.getProduct().getProductNbr(), tmpOrderLine);
			}
		}
		if (order != null) {
			textField_TotalSum.setText(Double.toString(order.getTotalPrice()));
		} else {
			textField_TotalSum.setText("");
		}

	}

	public String updateOrderSum() {
		double sum = 0;
		for (OrderLine tmpOrderLine : orderLines.values()) {
			sum += tmpOrderLine.getLinePrice();
		}
		return Double.toString(sum);
	}

	public void setCustomer(Customer customer) {
		if (customer != null) {
			textField_CustomerName.setText(customer.getName());
			textField_CustomerNbr.setText(customer.getCustomerNbr());
			textField_DeliveryAddress.setText(customer.getBillingadress());
			textField_FindOrderNbr.setText("");
			textField_TotalSum.setText("");
			clearTable(tableModel_Orders);
		}
	}


}
