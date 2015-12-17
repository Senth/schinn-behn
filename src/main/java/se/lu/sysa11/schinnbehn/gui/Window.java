package se.lu.sysa11.schinnbehn.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author Matteus Magnusson
 */
public class Window {
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		// Add menu to the left
		JPanel panel = new JPanel();
		GridLayout menuLayout = new GridLayout(4, 1);
		panel.setLayout(menuLayout);
		frame.getContentPane().add(panel, BorderLayout.WEST);

		JButton btnCustomer = new JButton("Customer");
		panel.add(btnCustomer);
		JButton btnOrder = new JButton("Order");
		panel.add(btnOrder);
		JButton btnProduct = new JButton("Product");
		panel.add(btnProduct);

		// Add content
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);


	}

}
