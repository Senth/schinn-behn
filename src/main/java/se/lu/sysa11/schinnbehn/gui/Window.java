package se.lu.sysa11.schinnbehn.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author Matteus Magnusson
 */
public class Window {
	private JFrame frame;
	private JPanel content;
	private ArrayList<Gui> guis = new ArrayList<>();
	private JPanel menu = new JPanel();

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
		GridLayout menuLayout = new GridLayout(4, 1);
		menu.setLayout(menuLayout);
		frame.getContentPane().add(menu, BorderLayout.WEST);

		JButton btnCustomer = new JButton("Customer");
		menu.add(btnCustomer);
		JButton btnOrder = new JButton("Order");
		menu.add(btnOrder);
		JButton btnProduct = new JButton("Product");
		menu.add(btnProduct);

		// Add content
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

}
