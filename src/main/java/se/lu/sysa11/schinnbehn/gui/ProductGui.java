package se.lu.sysa11.schinnbehn.gui;

import java.awt.Component;
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

import org.eclipse.wb.swing.FocusTraversalOnArray;

import se.lu.sysa11.schinnbehn.controller.ProductController;
import se.lu.sysa11.schinnbehn.model.Product;

/**
 * Display products
 */
public class ProductGui extends Gui<ProductController> {
	/**
	 * @param window
	 */
	public ProductGui(Window window) {
		super(window);
	}

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
	private JRadioButton radio_Active;
	private JRadioButton radio_Inactive;
	private String oldProductNbr = null;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void initialize() {
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(458, 57, 559, 340);
		panel.add(scrollPane);

		textField_ProductNr = new JTextField();
		textField_ProductNr.setBounds(LEFT_COLUMN_2_POS, 57, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_ProductNr);
		textField_ProductNr.setColumns(10);

		textField_Name = new JTextField();
		textField_Name.setBounds(LEFT_COLUMN_2_POS, 86, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Name);
		textField_Name.setColumns(10);

		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);
		textField_Price = new JFormattedTextField(decimalFormat);
		textField_Price.setBounds(LEFT_COLUMN_2_POS, 115, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Price);
		textField_Price.setColumns(10);

		textField_Weight = new JFormattedTextField(decimalFormat);
		textField_Weight.setBounds(LEFT_COLUMN_2_POS, 144, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Weight);
		textField_Weight.setColumns(10);

		textField_Cost = new JFormattedTextField(decimalFormat);
		textField_Cost.setBounds(LEFT_COLUMN_2_POS, 173, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Cost);
		textField_Cost.setColumns(10);

		textField_SearchProduct = new JTextField();
		textField_SearchProduct.setBounds(599, 24, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
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

		radio_Active = new JRadioButton("Ja");
		radio_Active.setSelected(true);
		buttonGroup.add(radio_Active);
		radio_Active.setBounds(LEFT_COLUMN_2_POS, 269, 43, 23);
		panel.add(radio_Active);

		radio_Inactive = new JRadioButton("Nej");
		buttonGroup.add(radio_Inactive);
		radio_Inactive.setBounds(203, 269, 61, 23);
		panel.add(radio_Inactive);

		JButton btnAddProduct = new JButton("L\u00E4gg till");
		btnAddProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField_Name.getText();
				String productNbr = textField_ProductNr.getText();
				String priceString = textField_Price.getText();
				String costString = textField_Cost.getText();
				String weightString = textField_Weight.getText();
				String ingredients = textArea_Ingredients.getText();

				if (isInputValid(productNbr, name, priceString, ingredients, weightString, costString)) {

				double price = 0;
				double weight = 0;
				double cost = 0;


				try {
					price = Double.parseDouble(priceString);
					weight = Double.parseDouble(weightString);
					cost = Double.parseDouble(costString);

				} catch (NumberFormatException exception) {
					return;
				}

				oldProductNbr = null;

					if(controller.findProduct(productNbr) == null) {
					oldProductNbr = controller.addProduct(productNbr, name, price, ingredients, weight, cost);
				} else {
					window.showNotificationError("Det finns redan en produkt med produktnummer " + productNbr + ".");
				}
				}

			}
		});
		btnAddProduct.setBounds(LEFT_COLUMN_1_POS, 307, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnAddProduct);

		JButton btnChangeProduct = new JButton("Ändra");
		btnChangeProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String productNbr = textField_ProductNr.getText();
				String name = textField_Name.getText();
				String costString = textField_Cost.getText();
				String priceString = textField_Price.getText();
				String weightString = textField_Weight.getText();
				String ingredients = textArea_Ingredients.getText();
				boolean active = radio_Active.isSelected();

				if (isInputValid(productNbr, name, priceString, ingredients, weightString, costString)) {

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


				boolean success = controller.updateProduct(oldProductNbr, productNbr, name, price, ingredients, weight,
						cost, active);
				if (success) {
					populateTable();
				}
			}
			}
		});
		btnChangeProduct.setBounds(LEFT_COLUMN_2_POS, 307, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnChangeProduct);

		JButton btnClearFields = new JButton("T\u00F6m f\u00E4lten");
		btnClearFields.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textField_ProductNr.setText("");
				textField_Name.setText("");
				textField_Price.setText("");
				textArea_Ingredients.setText("");
				textField_Cost.setText("");
				textField_Weight.setText("");
			}
		});
		btnClearFields.setBounds(308, 307, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnClearFields);

		JLabel lblProduct = new JLabel("Produkt");
		lblProduct.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblProduct.setBounds(LEFT_COLUMN_1_POS, 24, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblProduct);

		JLabel lblProductNbr = new JLabel("ProduktNr:");
		lblProductNbr.setBounds(LEFT_COLUMN_1_POS, 57, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblProductNbr);

		JLabel lblName = new JLabel("Namn:");
		lblName.setBounds(LEFT_COLUMN_1_POS, 86, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblName);

		JLabel lblPrice = new JLabel("Pris (exkl. moms):");
		lblPrice.setBounds(LEFT_COLUMN_1_POS, 115, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblPrice);

		JLabel lblWeight = new JLabel("Vikt:");
		lblWeight.setBounds(LEFT_COLUMN_1_POS, 144, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblWeight);

		JLabel lblCost = new JLabel("Kostnad:");
		lblCost.setBounds(LEFT_COLUMN_1_POS, 173, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblCost);

		JLabel lblIngredients = new JLabel("Ingredienser:");
		lblIngredients.setBounds(LEFT_COLUMN_1_POS, 202, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblIngredients);

		JLabel lblActive = new JLabel("Aktiv i sortiment:");
		lblActive.setBounds(LEFT_COLUMN_1_POS, 269, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblActive);

		JLabel lblSearchProduct = new JLabel("Filtrera Produkt:");
		lblSearchProduct.setBounds(458, 24, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblSearchProduct);

		String column_names[] = { "Nummer", "Namn", "Pris (exkl. moms)", "Kostnad" };
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
		table_Products.addMouseListener(new TableClickListener() {
			@Override
			public void onClick(JTable table, int row) {
				String productNumber = (String) tableModel_Product.getValueAt(row, COLUMN_NUMBER);
				if (productNumber != null) {
					setProduct(controller.findProduct(productNumber));
				}
			}
		});

		textArea_Ingredients = new JTextArea();
		textArea_Ingredients.setBounds(LEFT_COLUMN_2_POS, 202, TEXTFIELD_WIDTH, 59);
		textArea_Ingredients.setWrapStyleWord(true);
		textArea_Ingredients.setLineWrap(true);
		panel.add(textArea_Ingredients);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { textField_ProductNr, textField_Name, textField_Price, textField_Weight, textField_Cost, textArea_Ingredients,
						radio_Active, radio_Inactive, btnAddProduct, btnChangeProduct, btnClearFields, textField_SearchProduct, table_Products,
						scrollPane, lblProduct, lblProductNbr, lblName, lblPrice, lblWeight, lblCost, lblIngredients, lblActive, lblSearchProduct }));
		panel.setFocusCycleRoot(true);

		populateTable();

		setInitialized(true);
	}

	/**
	 * Set the current active product
	 *
	 * @param product
	 *            the product to set
	 */
	private void setProduct(Product product) {
		if (product != null) {
			oldProductNbr = product.getProductNbr();
			textField_ProductNr.setText(product.getProductNbr());
			textField_Name.setText(product.getName());
			textField_Cost.setText(String.valueOf(product.getCost()));
			textField_Price.setText(String.valueOf(product.getPrice()));
			textField_Weight.setText(String.valueOf(product.getWeight()));
			textArea_Ingredients.setText(product.getIngredients());

			if (product.isActive()) {
				radio_Active.setSelected(true);
			} else {
				radio_Inactive.setSelected(true);
			}
		}
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

	private boolean isInputValid(String productNbr, String name, String price, String ingredients, String weight,
			String cost) {
		if (productNbr == null || productNbr.isEmpty()) {
			window.showNotificationError("Produktnummer är tomt");

			return false;
		}
		if (name == null || name.isEmpty()) {
			window.showNotificationError("Produktnamnet är tomt");

			return false;
		}
		if (price == null || price.isEmpty() || Double.parseDouble(price) <= 0) {
			window.showNotificationError("Ogilitgt pris");

			return false;
		}
		if (weight == null || weight.isEmpty() || Double.parseDouble(weight) <= 0) {
			window.showNotificationError("Ogiltig vikt");

			return false;
		}
		if (cost == null || cost.isEmpty() || Double.parseDouble(cost) <= 0) {
			window.showNotificationError("Ogilig kostnad");

			return false;
		}
		if (ingredients == null || ingredients.isEmpty()) {
			window.showNotificationError("Ingridienser finns ej");

			return false;
		}

		return true;
	}
}
