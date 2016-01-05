package se.lu.sysa11.schinnbehn.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import se.lu.sysa11.schinnbehn.controller.ProductController;
import se.lu.sysa11.schinnbehn.model.Product;

/**
 * @author Jesper
 */
public class ProductGui extends Gui<ProductController> {
	private static final int COLUMN_NUMBER = 0;
	private static final int COLUMN_NAME = 1;
	private static final int COLUMN_PRICE = 2;
	private static final int COLUMN_COST = 3;

	/**
	 * Can't have panel in base class as we're not able to access WindowBuilder
	 * correctly then
	 */
	private JPanel panel = new JPanel();
	private JTextField textField_ProductNr;
	private JTextField textField_Name;
	private JTextField textField_Price;
	private JTextField textField_Weight;
	private JTextField textField_Cost;
	private JTable table_Products;
	private JTextField textField_SearchProduct;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextArea textArea_Ingredients;
	private DefaultTableModel tableModel_Product;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void initialize() {
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(359, 57, 559, 340);
		panel.add(scrollPane);

		textField_ProductNr = new JTextField();
		textField_ProductNr.setBounds(158, 57, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_ProductNr);
		textField_ProductNr.setColumns(10);

		textField_Name = new JTextField();
		textField_Name.setBounds(158, 86, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Name);
		textField_Name.setColumns(10);

		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);
		textField_Price = new JFormattedTextField(decimalFormat);
		textField_Price.setBounds(158, 115, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Price);
		textField_Price.setColumns(10);

		textField_Weight = new JTextField();
		textField_Weight.setBounds(158, 144, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Weight);
		textField_Weight.setColumns(10);

		textField_Cost = new JTextField();
		textField_Cost.setBounds(158, 173, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Cost);
		textField_Cost.setColumns(10);

		textField_SearchProduct = new JTextField();
		textField_SearchProduct.setBounds(500, 24, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
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

		JRadioButton rdbtnYes = new JRadioButton("Ja");
		rdbtnYes.setSelected(true);
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setBounds(158, 269, 43, 23);
		panel.add(rdbtnYes);

		JRadioButton rdbtnNo = new JRadioButton("Nej");
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(203, 269, 61, 23);
		panel.add(rdbtnNo);

		JButton btnAddProduct = new JButton("L\u00E4gg till:");
		btnAddProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField_Name.getText();
				String productNbr = textField_ProductNr.getText();
				String priceString = textField_Price.getText();
				String costString = textField_Cost.getText();
				String weightString = textField_Weight.getText();
				String ingredients = textArea_Ingredients.getText();

				double price = 0;
				double weight = 0;
				double cost = 0;
				try {
					price = Double.parseDouble(priceString);
					weight = Double.parseDouble(weightString);
					cost = Double.parseDouble(costString);

				} catch (NumberFormatException exception) {
					// TODO
					return;
				}

				controller.addProduct(productNbr, name, price, ingredients, weight, cost);

			}
		});
		btnAddProduct.setBounds(12, 307, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnAddProduct);

		JButton btnChangeProduct = new JButton("Ändra:");
		btnChangeProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String productNbr = textField_ProductNr.getText();
				String name = textField_Name.getText();
				String costString = textField_Cost.getText();
				String priceString = textField_Price.getText();
				String weightString = textField_Weight.getText();
				String ingredients = textArea_Ingredients.getText();

				double cost = 0;
				double price = 0;
				double weight = 0;

				try {
					price = Double.parseDouble(priceString);
					weight = Double.parseDouble(weightString);
					cost = Double.parseDouble(costString);

				} catch (NumberFormatException exception) {
					// TODO
					return;
				}

				controller.updateProduct(productNbr, name, price, ingredients, weight, cost);
			}
		});
		btnChangeProduct.setBounds(158, 307, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnChangeProduct);

		JLabel lblProduct = new JLabel("Produkt");
		lblProduct.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblProduct.setBounds(12, 24, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblProduct);

		JLabel lblProductNbr = new JLabel("ProduktNr:");
		lblProductNbr.setBounds(12, 57, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblProductNbr);

		JLabel lblName = new JLabel("Namn:");
		lblName.setBounds(12, 86, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblName);

		JLabel lblPrice = new JLabel("Pris:");
		lblPrice.setBounds(12, 115, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblPrice);

		JLabel lblWeight = new JLabel("Vikt:");
		lblWeight.setBounds(12, 144, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblWeight);

		JLabel lblCost = new JLabel("Kostnad:");
		lblCost.setBounds(12, 173, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblCost);

		JLabel lblIngredients = new JLabel("Ingredienser:");
		lblIngredients.setBounds(12, 202, 79, 23);
		panel.add(lblIngredients);

		JLabel lblActive = new JLabel("Aktiv i sortiment:");
		lblActive.setBounds(12, 269, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblActive);

		JLabel lblSearchProduct = new JLabel("S�k produkt:");
		lblSearchProduct.setBounds(359, 24, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblSearchProduct);

		String column_names[] = { "Nummer", "Namn", "Pris", "Kostnad" };
		table_Products = new JTable();
		tableModel_Product = new DefaultTableModel(new Object[][] {}, column_names) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case COLUMN_NUMBER:
					return String.class;
				case COLUMN_NAME:
					return String.class;
				case COLUMN_PRICE:
					return Double.class;
				case COLUMN_COST:
					return Double.class;
				default:
					return String.class;
				}

			}
		};
		table_Products.setAutoCreateRowSorter(true);
		table_Products.setModel(tableModel_Product);
		table_Products.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table_Products);

		JTextArea textArea_Ingredients = new JTextArea();
		textArea_Ingredients.setBounds(158, 202, TEXTFIELD_WIDTH, 59);
		panel.add(textArea_Ingredients);

		populateTable(controller.findProducts(""));

		setInitialized(true);
	}

	/**
	 * Populate table with all products that matches the search string
	 */
	private void populateTable() {
		populateTable(controller.findProducts(textField_SearchProduct.getText()));
	}

	private void populateTable(List<Product> products) {
		// Remove rows
		while (tableModel_Product.getRowCount() > 0) {
			tableModel_Product.removeRow(0);
		}

		// Add products
		for (Product product : products) {
			Object[] row = { product.getProductNbr(), product.getName(), product.getPrice(), product.getCost() };
			tableModel_Product.addRow(row);
		}
	}

	@Override
	public JPanel getContent() {
		return panel;
	}
}
