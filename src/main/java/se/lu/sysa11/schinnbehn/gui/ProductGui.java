package se.lu.sysa11.schinnbehn.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import javax.swing.table.DefaultTableModel;

import se.lu.sysa11.schinnbehn.controller.ProductController;

/**
 * @author Jesper
 */
public class ProductGui extends Gui<ProductController> {
	/**
	 * Can't have panel in base class as we're not able to access WindowBuilder correctly
	 * then
	 */
	private JPanel panel = new JPanel();
	private JTextField textField_ProductNr;
	private JTextField textField_Name;
	private JTextField textField_Pris;
	private JTextField textField_Vikt;
	private JTextField textField_Kostnad;
	private JTable table_Products;
	private JTextField textField_6;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextArea textArea_Ingridienser;


	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void initialize() {
		panel.setLayout(null);

		textField_ProductNr = new JTextField();
		textField_ProductNr.setBounds(103, 60, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_ProductNr);
		textField_ProductNr.setColumns(10);

		textField_Name = new JTextField();
		textField_Name.setBounds(103, 101, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Name);
		textField_Name.setColumns(10);

		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);
		textField_Pris = new JFormattedTextField(decimalFormat);
		textField_Pris.setBounds(103, 139, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Pris);
		textField_Pris.setColumns(10);

		textField_Vikt = new JTextField();
		textField_Vikt.setBounds(103, 177, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Vikt);
		textField_Vikt.setColumns(10);

		textField_Kostnad = new JTextField();
		textField_Kostnad.setBounds(103, 215, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_Kostnad);
		textField_Kostnad.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(280, 66, 350, 340);
		panel.add(scrollPane);

		textField_6 = new JTextField();
		textField_6.setBounds(500, 26, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		panel.add(textField_6);
		textField_6.setColumns(10);

		JRadioButton rdbtnYes = new JRadioButton("Ja");
		rdbtnYes.setSelected(true);
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setBounds(139, 329, 43, 23);
		panel.add(rdbtnYes);

		JRadioButton rdbtnNo = new JRadioButton("Nej");
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(184, 329, 61, 23);
		panel.add(rdbtnNo);

		JButton btnAddProduct = new JButton("L\u00E4gg till:");
		btnAddProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textField_Name.getText();
				String productNbr = textField_ProductNr.getText();
				String priceString = textField_Pris.getText();
				String costString = textField_Kostnad.getText();
				String weightString = textField_Vikt.getText();
				String ingredients = textArea_Ingridienser.getText();

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
		btnAddProduct.setBounds(43, 377, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnAddProduct);

		JButton btnNewButton_1 = new JButton("Ändra:");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String productNbr = textField_ProductNr.getText();
				String name = textField_Name.getText();
				String costString = textField_Kostnad.getText();
				String priceString = textField_Pris.getText();
				String weightString = textField_Vikt.getText();
				String ingredients = textArea_Ingridienser.getText();

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
		btnNewButton_1.setBounds(139, 377, BUTTON_WIDTH, BUTTON_HEIGHT);
		panel.add(btnNewButton_1);

		JLabel lblProduct = new JLabel("Produkt");
		lblProduct.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblProduct.setBounds(20, 31, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblProduct);

		JLabel lblProduktnr = new JLabel("ProduktNr:");
		lblProduktnr.setBounds(20, 68, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblProduktnr);

		JLabel lblNewLabel = new JLabel("Namn:");
		lblNewLabel.setBounds(20, 106, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Pris:");
		lblNewLabel_1.setBounds(20, 144, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblNewLabel_1);

		JLabel lblVikt = new JLabel("Vikt:");
		lblVikt.setBounds(20, 182, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblVikt);

		JLabel lblNewLabel_2 = new JLabel("Kostnad:");
		lblNewLabel_2.setBounds(20, 220, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Ingridienser:");
		lblNewLabel_3.setBounds(20, 253, 79, 23);
		panel.add(lblNewLabel_3);

		JLabel lblAktivISortiment = new JLabel("Aktiv i sortiment:");
		lblAktivISortiment.setBounds(20, 336, 110, LABEL_HEIGHT);
		panel.add(lblAktivISortiment);

		JLabel lblSearchProduct = new JLabel("Sök produkt:");
		lblSearchProduct.setBounds(395, 31, 94, LABEL_HEIGHT);
		panel.add(lblSearchProduct);

		String column_names[] = { "Nummer", "Namn", "Pris", "Kostnad" };
		table_Products = new JTable();
		table_Products.setModel(new DefaultTableModel(new Object[][] {}, column_names) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table_Products.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table_Products);

		JTextArea textArea_Ingridienser = new JTextArea();
		textArea_Ingridienser.setBounds(103, 253, 120, 59);
		panel.add(textArea_Ingridienser);

	}

	@Override
	public JPanel getContent() {
		return panel;
	}
}
