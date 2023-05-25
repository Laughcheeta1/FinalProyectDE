package frontEnd;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class panelComprimir extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel btnVolver;
	JLabel btnRevisar;
	JButton btnExplorador;
	JPanel panelArchivoSelected;
	JTextArea txtPath;
	
	public panelComprimir() {
		
		setBackground(new Color(242, 242, 242));
		setBounds(0, 0, 1200, 720);
		setLayout(null);
		
		JLabel lblSelecciona = new JLabel("Suelte el archivo a comprimir");
		lblSelecciona.setFont(new Font("Verdana", Font.BOLD, 24));
		lblSelecciona.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecciona.setBounds(40, 383, 515, 150);
		add(lblSelecciona);
		
		JLabel lblO = new JLabel("O");
		lblO.setFont(new Font("Verdana", Font.BOLD, 28));
		lblO.setHorizontalAlignment(SwingConstants.CENTER);
		lblO.setBounds(40, 350, 515, 50);
		add(lblO);
		
		btnExplorador = new JButton("Abrir explorador de archivos");
		btnExplorador.setBackground(new Color(246, 255, 147));
		btnExplorador.setFont(new Font("Verdana", Font.BOLD, 24));
		btnExplorador.setBounds(40, 200, 515, 60);
		btnExplorador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnExplorador);
		
		panelArchivoSelected = new JPanel();
		panelArchivoSelected.setBackground(new Color(255, 255, 255));
		panelArchivoSelected.setBounds(615, 163, 485, 409);
		add(panelArchivoSelected);
		panelArchivoSelected.setLayout(null);
		
		
		txtPath = new JTextArea();
		txtPath.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 24));
		txtPath.setBounds(10, 5, 460, 390);
		txtPath.setLineWrap(true);
		txtPath.setWrapStyleWord(true);
		txtPath.setEditable(false);
		panelArchivoSelected.add(txtPath);
		
		btnVolver = new JLabel("");
		btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVolver.setIcon(new ImageIcon(panelComprimir.class.getResource("/imagenes/volver.png")));
		btnVolver.setFont(new Font("Verdana", Font.BOLD, 20));
		btnVolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnVolver.setForeground(new Color(255, 0, 0));
		btnVolver.setBounds(310, 640, 200, 50);
		add(btnVolver);
		
		btnRevisar = new JLabel("");
		btnRevisar.setHorizontalAlignment(SwingConstants.CENTER);
		btnRevisar.setForeground(Color.RED);
		btnRevisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRevisar.setIcon(new ImageIcon(panelComprimir.class.getResource("/imagenes/revisar.png")));
		btnRevisar.setFont(new Font("Verdana", Font.BOLD, 20));
		btnRevisar.setBounds(690, 640, 200, 50);
		add(btnRevisar);

	}
	
	public void limpiarCasillas() {
		txtPath.setText("");
		
	}
}
