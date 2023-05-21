package frontEnd;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class panelTextViewer1 extends JPanel {

	/**
	 * Create the panel.
	 */
	
	JTextArea textFileViewer;
	
	public panelTextViewer1() {
		
		setBackground(new Color(242, 242, 242));
		setBounds(0, 0, 1200, 720);
		setLayout(null);
		
		JPanel panelTextViewer = new JPanel();
		panelTextViewer.setBackground(new Color(255, 255, 255));
		panelTextViewer.setBounds(50, 108, 520, 550);
		add(panelTextViewer);
		panelTextViewer.setLayout(null);
		
		textFileViewer = new JTextArea();
		textFileViewer.setEditable(false);
		textFileViewer.setLineWrap(true);
		textFileViewer.setWrapStyleWord(true);
		textFileViewer.setBounds(8, 40, 504, 502);
		panelTextViewer.add(textFileViewer);

	}
	
	public void limpiar() {
		textFileViewer.setText("");
	}

}
