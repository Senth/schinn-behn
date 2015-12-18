package se.lu.sysa11.schinnbehn.gui;

import javax.swing.JPanel;

import se.lu.sysa11.schinnbehn.controller.OrderController;

/**
 * @author
 */
public class OrderGui extends Gui<OrderController> {
	/**
	 * Can't have panel in base class as we're not able to access WindowBuilder correctly
	 * then
	 */
	private JPanel panel = new JPanel();

	@Override
	protected void initialize() {


	}

	@Override
	public JPanel getContent() {
		return panel;
	}

}
