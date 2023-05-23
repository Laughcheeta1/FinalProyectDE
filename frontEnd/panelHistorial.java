package frontEnd;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class panelHistorial extends JPanel {

	/**
	 * Create the panel.
	 */
	public panelHistorial() {
		setBackground(new Color(242, 242, 242));
		setBounds(0, 0, 1200, 720);
		setLayout(null);
		
		JSeparator separator1 = new JSeparator();
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setBackground(new Color(0, 0, 0));
		separator1.setForeground(new Color(0, 0, 0));
		separator1.setBounds(598, 110, 2, 560);
		add(separator1);

	}

}
