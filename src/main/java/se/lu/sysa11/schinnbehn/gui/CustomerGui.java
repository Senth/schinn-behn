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
import javax.swing.table.DefaultTableModel;

import se.lu.sysa11.schinnbehn.controller.CustomerController;
import se.lu.sysa11.schinnbehn.model.Customer;

/**
 * @author Kalle
 */
public class CustomerGui extends Gui<CustomerController> {
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
		lblFindCustomer.setBounds(12, 271, LABEL_WIDTH, LABEL_HEIGHT);
		panel.add(lblFindCustomer);

		JLabel lblKundnr = new JLabel("Kundnr:");
		lblKundnr.setBounds(12, 304, LABEL_WIDTH, LABEL_HEIGHT);
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
		textField_FindCustomer.setBounds(158, 304, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
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

				controller.addCustomer(name, phoneNbr, adress, email);
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
				textField_Name.setText(tmpCustomer.getName());
				textField_Phone.setText(tmpCustomer.getTelephoneNbr());
				textField_Adress.setText(tmpCustomer.getAddress());
				textField_Email.setText(tmpCustomer.getEmail());
				textField_ShowCustomerNbr.setText(tmpCustomer.getCustomerNbr());
			}
		});
		btnSearchCustomer.setBounds(309, 304, BUTTON_WIDTH, BUTTON_HEIGHT);
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
		scrollPane.setBounds(459, 24, 452, 362);
		panel.add(scrollPane);

		String column_names[] = { "Ordernummer", "Datum", "Summa" };
		table_Orders = new JTable();
		table_Orders.setModel(new DefaultTableModel(new Object[][] {}, column_names) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table_Orders.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table_Orders);

	}

	@Override
	public JPanel getContent() {
		return panel;
	}
}
