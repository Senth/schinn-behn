package se.lu.sysa11.schinnbehn.gui;

import javax.swing.JButton;
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
	JPanel panel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table_Products;
	private JTextField textField_6;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	protected void initialize() {
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(103, 60, 130, 26);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(103, 101, 130, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(103, 139, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(103, 177, 130, 26);
		panel.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(103, 215, 130, 26);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(280, 66, 350, 340);
		panel.add(scrollPane);

		textField_6 = new JTextField();
		textField_6.setBounds(500, 26, 130, 26);
		panel.add(textField_6);
		textField_6.setColumns(10);

		JRadioButton rdbtnJa = new JRadioButton("Ja");
		rdbtnJa.setBounds(139, 329, 43, 23);
		panel.add(rdbtnJa);

		JRadioButton rdbtnNej = new JRadioButton("Nej");
		rdbtnNej.setBounds(184, 329, 61, 23);
		panel.add(rdbtnNej);

		JButton btnNewButton = new JButton("Lägg till:");
		btnNewButton.setBounds(43, 377, 84, 26);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Ändra:");
		btnNewButton_1.setBounds(139, 377, 84, 29);
		panel.add(btnNewButton_1);

		JLabel lblProduct = new JLabel("Produkt");
		lblProduct.setBounds(20, 31, 61, 16);
		panel.add(lblProduct);

		JLabel lblProduktnr = new JLabel("ProduktNr:");
		lblProduktnr.setBounds(20, 68, 75, 16);
		panel.add(lblProduktnr);

		JLabel lblNewLabel = new JLabel("Namn:");
		lblNewLabel.setBounds(20, 106, 61, 16);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Pris:");
		lblNewLabel_1.setBounds(20, 144, 61, 16);
		panel.add(lblNewLabel_1);

		JLabel lblVikt = new JLabel("Vikt:");
		lblVikt.setBounds(20, 182, 61, 16);
		panel.add(lblVikt);

		JLabel lblNewLabel_2 = new JLabel("Kostnad:");
		lblNewLabel_2.setBounds(20, 220, 61, 16);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Ingridienser:");
		lblNewLabel_3.setBounds(20, 253, 79, 23);
		panel.add(lblNewLabel_3);

		JLabel lblAktivISortiment = new JLabel("Aktiv i sortiment:");
		lblAktivISortiment.setBounds(20, 336, 110, 16);
		panel.add(lblAktivISortiment);

		JLabel lblSkProdukt = new JLabel("Sök produkt:");
		lblSkProdukt.setBounds(395, 31, 94, 16);
		panel.add(lblSkProdukt);

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

		JTextArea textArea = new JTextArea();
		textArea.setBounds(103, 253, 120, 59);
		panel.add(textArea);

	}
}
