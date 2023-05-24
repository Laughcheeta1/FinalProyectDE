package frontEnd;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class panelTextViewer2 extends JPanel {

	/**
	 * Create the panel.
	 */
	JTextArea textFileViewer;
	JLabel lblTituloText;
	JButton btnExplorador;
	JTextArea textMemorySelected;
	
	JLabel btnVolver;
	JLabel btnDescomprimir;
	
	public panelTextViewer2() {
		setBackground(new Color(242, 242, 242));
		setBounds(0, 0, 1200, 720);
		setLayout(null);
		
		JPanel panelTextViewer = new JPanel();
		panelTextViewer.setBackground(new Color(255, 255, 255));
		panelTextViewer.setBounds(50, 108, 540, 550);
		add(panelTextViewer);
		panelTextViewer.setLayout(null);
		
		textFileViewer = new JTextArea();
		textFileViewer.setEditable(false);
		textFileViewer.setLineWrap(true);
		textFileViewer.setWrapStyleWord(true);
//		textFileViewer.setBounds(8, 40, 504, 502);
//		panelTextViewer.add(textFileViewer);
		
		lblTituloText = new JLabel("");
		lblTituloText.setFont(new Font("Verdana", Font.BOLD, 20));
		lblTituloText.setBounds(8, 0, 400, 40);
		panelTextViewer.add(lblTituloText);
		
		JTextArea txtSubtitulo = new JTextArea();
		txtSubtitulo.setFont(new Font("Verdana", Font.BOLD, 24));
		txtSubtitulo.setText("Seleccione la direccion a guardar el archivo:");
		txtSubtitulo.setEditable(false);
		txtSubtitulo.setLineWrap(true);
		txtSubtitulo.setWrapStyleWord(true);
		txtSubtitulo.setBackground(new Color(242, 242, 242));
		txtSubtitulo.setBounds(645, 150, 555, 100);
		add(txtSubtitulo);
		
		JScrollPane scrollPane = new JScrollPane(textFileViewer);
		scrollPane.setPreferredSize(new Dimension(504, 502));
		scrollPane.setBounds(8, 40, 524, 502);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panelTextViewer.add(scrollPane);
		
		
		btnExplorador = new JButton("Abrir explorador");
		btnExplorador.setHorizontalAlignment(SwingConstants.LEFT);
		btnExplorador.setBackground(new Color(246, 255, 147));
		btnExplorador.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnExplorador.setBounds(645, 250, 190, 32);
		btnExplorador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnExplorador);
		
		textMemorySelected = new JTextArea();
		textMemorySelected.setFont(new Font("Monospaced", Font.BOLD, 16));
		textMemorySelected.setBounds(645, 350, 555, 22);
		textMemorySelected.setEditable(false);
		textMemorySelected.setLineWrap(true);
		textMemorySelected.setWrapStyleWord(true);
		add(textMemorySelected);
		
		btnVolver = new JLabel("");
		btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVolver.setIcon(new ImageIcon(panelComprimir.class.getResource("/imagenes/volver.png")));
		btnVolver.setFont(new Font("Verdana", Font.BOLD, 20));
		btnVolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnVolver.setForeground(new Color(255, 0, 0));
		btnVolver.setBounds(664, 484, 200, 50);
		add(btnVolver);
		
		btnDescomprimir = new JLabel("");
		btnDescomprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDescomprimir.setIcon(new ImageIcon(panelComprimir.class.getResource("/imagenes/guardar.png")));
		btnDescomprimir.setFont(new Font("Verdana", Font.BOLD, 20));
		btnDescomprimir.setHorizontalAlignment(SwingConstants.CENTER);
		btnDescomprimir.setForeground(new Color(255, 0, 0));
		btnDescomprimir.setBounds(924, 484, 200, 50);
		add(btnDescomprimir);

	}
	
	public void limpiar() {
		textFileViewer.setText("");
		lblTituloText.setText("");
		textMemorySelected.setText("");
	}

}
