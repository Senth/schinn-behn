package se.lu.sysa11.schinnbehn.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import se.lu.sysa11.schinnbehn.controller.Controller;


public class Window {
	private JFrame frame;
	private JScrollPane content = new JScrollPane();
	private HashMap<Views, Controller<?, ?>> controllers = new HashMap<>();
	private HashMap<Views, AbstractButton> viewButtons = new HashMap<>();
	private JPanel menu = new JPanel();
	private JLabel notificationLabel = new JLabel("Meddelanden");

	
	public Window() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setTitle("Schinn & Behn");


		// Menu
		FlowLayout menuLayout = new FlowLayout();
		menu.setLayout(menuLayout);
		menu.setPreferredSize(new Dimension(150, 1000));
		frame.getContentPane().add(menu, BorderLayout.WEST);

		ButtonGroup group = new ButtonGroup();
		AbstractButton btnCustomer = new JButton("Kund", new ImageIcon("assets/customer.png"));
		btnCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchTo(Views.CUSTOMER);
			}
		});
		menu.add(btnCustomer);
		viewButtons.put(Views.CUSTOMER, btnCustomer);
		AbstractButton btnOrder = new JButton("Order", new ImageIcon("assets/order.png"));
		btnOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchTo(Views.ORDER);
			}
		});
		menu.add(btnOrder);
		viewButtons.put(Views.ORDER, btnOrder);
		AbstractButton btnProduct = new JButton("Produkt", new ImageIcon("assets/products.png"));
		btnProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchTo(Views.PRODUCT);
			}
		});
		menu.add(btnProduct);
		viewButtons.put(Views.PRODUCT, btnProduct);

		// Set common things for all buttons
		for (AbstractButton button : viewButtons.values()) {
			button.setHorizontalTextPosition(AbstractButton.CENTER);
			button.setVerticalTextPosition(AbstractButton.BOTTOM);
			group.add(button);
		}


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

	
	public void addController(Views view, Controller<?, ?> controller) {
		controllers.put(view, controller);
	}

	
	public void switchTo(Views view) {
		switchTo(view, null);
	}

	
	public void switchTo(Views view, Object data) {
		Controller<?, ?> controller = controllers.get(view);

		if (controller != null) {
			controller.activate(data);
			content.setViewportView(controller.getGui().getContent());
			content.revalidate();
			content.repaint();

			AbstractButton button = viewButtons.get(view);
			if (button != null && !button.isSelected()) {
				button.setSelected(true);
			}
		} else {
			showNotificationError("Ingen view med namnet " + view.toString().toLowerCase() + "!");
		}
	}

	
	public void showNotificationError(String message) {
		notificationLabel.setText(message);
		notificationLabel.setForeground(ERROR_COLOR);
	}

	
	public void showNotificationSuccess(String message) {
		notificationLabel.setText(message);
		notificationLabel.setForeground(SUCCESS_COLOR);
	}

	
	public void showNotificationInfo(String message) {
		notificationLabel.setText(message);
		notificationLabel.setForeground(INFO_COLOR);
	}

	private static final Color ERROR_COLOR = new Color(115, 14, 14);
	private static final Color SUCCESS_COLOR = new Color(63, 115, 14);
	private static final Color INFO_COLOR = new Color(0, 0, 0);
}
