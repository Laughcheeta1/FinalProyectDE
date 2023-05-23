package frontEnd;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class panelInicio extends JPanel {

	/**
	 * Create the panel.
	 */
	
	JButton btnComprimir;
	JButton btnDescomp;
	JButton btnHistorial;
	
	public panelInicio() {
		
		setBackground(new Color(242, 242, 242));
		setBounds(0, 0, 1200, 720);
		setLayout(null);
		
		JPanel panelsitoDecoration = new JPanel();
		panelsitoDecoration.setBackground(new Color(136, 218, 232));
		panelsitoDecoration.setBounds(750, 0, 450, 720);
		add(panelsitoDecoration);
		panelsitoDecoration.setLayout(null);
		
		JLabel titulo = new JLabel("Huffman");
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setFont(new Font("Verdana", Font.BOLD, 40));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 0, 450, 300);
		panelsitoDecoration.add(titulo);
		
		btnComprimir = new JButton("Comprimir");
		btnComprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnComprimir.setBackground(new Color(33, 210, 29));
		btnComprimir.setFont(new Font("Verdana", Font.BOLD, 22));
		btnComprimir.setBounds(200, 180, 300, 90);
		add(btnComprimir);
		
		btnDescomp = new JButton("Descomprimir");
		btnDescomp.setFont(new Font("Verdana", Font.BOLD, 22));
		btnDescomp.setBackground(new Color(20, 100, 255));
		btnDescomp.setBounds(200, 450, 300, 90);
		add(btnDescomp);
		
//		btnHistorial = new JButton("Historial");
//		btnHistorial.setFont(new Font("Verdana", Font.BOLD, 22));
//		btnHistorial.setBackground(new Color(250, 255, 23));
//		btnHistorial.setBounds(200, 480, 300, 90);
//		add(btnHistorial);

	}
}
