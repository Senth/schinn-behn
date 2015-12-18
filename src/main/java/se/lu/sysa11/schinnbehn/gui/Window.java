package se.lu.sysa11.schinnbehn.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Matteus Magnusson
 */
public class Window {
	private JFrame frame;
	private JPanel content = new JPanel();
	private HashMap<Views, Gui<?>> guis = new HashMap<>();
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
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setTitle("Schinn & Behn");

		// Add menu to the left
		GridLayout menuLayout = new GridLayout(4, 1);
		menu.setLayout(menuLayout);
		frame.getContentPane().add(menu, BorderLayout.WEST);

		JButton btnCustomer = new JButton("Kund");
		btnCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchTo(Views.CUSTOMER);
			}
		});
		menu.add(btnCustomer);
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchTo(Views.ORDER);
			}
		});
		menu.add(btnOrder);
		JButton btnProduct = new JButton("Produkt");
		btnProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchTo(Views.PRODUCT);
			}
		});
		menu.add(btnProduct);

		// Add content
		// content = new JScrollPane();
		// content.setViewportView(new JPanel());
		frame.getContentPane().add(content, BorderLayout.CENTER);

		frame.setVisible(true);
	}

	/**
	 * Add a new GUI
	 * @param view identifier used for switching to this GUI
	 * @param gui the GUI to add
	 */
	public void addGui(Views view, Gui<?> gui) {
		guis.put(view, gui);
	}

	/**
	 * Switch to this view
	 * @param view the view
	 */
	public void switchTo(Views view) {
		Gui<?> gui = guis.get(view);

		if (gui != null) {
			// content.removeAll();
			JPanel innerContent = gui.getContent();
			innerContent.setVisible(true);
			content.removeAll();
			content.add(innerContent);
			innerContent.revalidate();
			innerContent.repaint();
			content.revalidate();
			content.repaint();
		} else {
			showNotificationError("Ingen view med namnet " + view.toString().toLowerCase() + "!");
		}
	}

	/**
	 * Send an error notification (displayed as red)
	 * @param message the message to display
	 */
	public void showNotificationError(String message) {
		// TODO
	}

	/**
	 * Send a success notification (displayed as green)
	 * @param message the message to display
	 */
	public void showNotificationSuccess(String message) {
		// TODO
	}

	/**
	 * Send an info notification (displayed as black text)
	 * @param message the message to display
	 */
	public void showNotificationInfo(String message) {
		// TODO
	}
}
