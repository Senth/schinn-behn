package se.lu.sysa11.schinnbehn.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import se.lu.sysa11.schinnbehn.controller.Controller;

/**
 * Main window for the application. Switch between content by calling
 * {@link #switchTo(Views)} or {@link #switchTo(Views, Object)}
 */
public class Window {
	private JFrame frame;
	private JScrollPane content = new JScrollPane();
	private HashMap<Views, Controller<?, ?>> controllers = new HashMap<>();
	private HashMap<Views, JButton> viewButtons = new HashMap<>();
	private JPanel menu = new JPanel();
	private JLabel notificationLabel = new JLabel("Meddelanden");

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
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setTitle("Schinn & Behn");


		// Menu
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
		viewButtons.put(Views.CUSTOMER, btnCustomer);
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchTo(Views.ORDER);
			}
		});
		menu.add(btnOrder);
		viewButtons.put(Views.ORDER, btnOrder);
		JButton btnProduct = new JButton("Produkt");
		btnProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchTo(Views.PRODUCT);
			}
		});
		menu.add(btnProduct);
		viewButtons.put(Views.PRODUCT, btnProduct);


		// Content
		content.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(content, BorderLayout.CENTER);


		// Notification
		JPanel notificationPanel = new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		notificationPanel.setLayout(flowLayout);
		notificationPanel.add(notificationLabel);
		notificationPanel.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
		frame.getContentPane().add(notificationPanel, BorderLayout.PAGE_END);


		frame.setVisible(true);
	}

	/**
	 * Add a new Controller to the window
	 * @param view identifier used for switching to this GUI
	 * @param controller the controller to switch to when {@link #switchTo(Views)} is
	 *        called
	 */
	public void addController(Views view, Controller<?, ?> controller) {
		controllers.put(view, controller);
	}

	/**
	 * Switch to this view
	 * @param view the view
	 */
	public void switchTo(Views view) {
		switchTo(view, null);
	}

	/**
	 * Switch to this view and send the appropriate data to it
	 * @param view the view to switch to
	 * @param data what to send to the view
	 */
	public void switchTo(Views view, Object data) {
		Controller<?, ?> controller = controllers.get(view);

		if (controller != null) {
			controller.activate(data);
			content.setViewportView(controller.getGui().getContent());
			content.revalidate();
			content.repaint();

			JButton button = viewButtons.get(view);
			if (button != null && !button.isSelected()) {
				button.setSelected(true);
			}
		} else {
			showNotificationError("Ingen view med namnet " + view.toString().toLowerCase() + "!");
		}
	}

	/**
	 * Send an error notification (displayed as red)
	 * @param message the message to display
	 */
	public void showNotificationError(String message) {
		notificationLabel.setText(message);
		notificationLabel.setForeground(ERROR_COLOR);
	}

	/**
	 * Send a success notification (displayed as green)
	 * @param message the message to display
	 */
	public void showNotificationSuccess(String message) {
		notificationLabel.setText(message);
		notificationLabel.setForeground(SUCCESS_COLOR);
	}

	/**
	 * Send an info notification (displayed as black text)
	 * @param message the message to display
	 */
	public void showNotificationInfo(String message) {
		notificationLabel.setText(message);
		notificationLabel.setForeground(INFO_COLOR);
	}

	private static final Color ERROR_COLOR = new Color(242, 116, 137);
	private static final Color SUCCESS_COLOR = new Color(116, 242, 137);
	private static final Color INFO_COLOR = new Color(0, 0, 0);
}
