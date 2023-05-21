package frontEnd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	int xMouse, yMouse;
	JPanel header;
	String fileSelected;
	panelInicio panelInicio = new panelInicio();
	panelComprimir panel2 = new panelComprimir();
	panelTextViewer1 panel3 = new panelTextViewer1();
		
	public Main() {
		setLocationByPlatform(true);
		setUndecorated(true);
		setTitle("Compresor archivos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		header = new JPanel();
		header.setBackground(new Color(136, 218, 232));
		header.setBounds(0, 0, 1200, 80);
		header.setLayout(null);
		
		JLabel titulo = new JLabel("Huffman");
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Verdana", Font.BOLD, 30));
		titulo.setBounds(0, 0, 1184, 80);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		panelHeader.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - xMouse, y - yMouse);
			}
		});
		panelHeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		panelHeader.setBounds(0, 0, 1200, 42);
		contentPane.add(panelHeader);
		panelHeader.setLayout(null);
		
		JPanel panelClose = new JPanel();
		panelClose.setBackground(new Color(136, 218, 232));
		panelClose.setBounds(0, 0, 42, 42);
		panelHeader.add(panelClose);
		panelClose.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 0, 42, 42);
		panelClose.add(lblNewLabel);
		
		JPanel panelContenido = new JPanel();
		panelContenido.setBounds(0, 0, 10, 10);
		contentPane.add(panelContenido);
		
		panelClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelClose.setBackground(new Color(255, 0, 0));
				lblNewLabel.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelClose.setBackground(new Color(136, 218, 232));
				lblNewLabel.setForeground(new Color(0, 0, 0));
			}
		});
		
		panelInicio.btnComprimir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelInicio.btnComprimir.setBackground(new Color(113, 113, 113));
				panelInicio.btnComprimir.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelInicio.btnComprimir.setBackground(new Color(33, 210, 29));
				panelInicio.btnComprimir.setForeground(new Color(0, 0, 0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInicio.btnComprimir.setBackground(new Color(33, 210, 29));
				panelInicio.btnComprimir.setForeground(new Color(0, 0, 0));
				
				header.add(titulo);				
				header.setVisible(true);
				panel2.setVisible(true);
				panelInicio.setVisible(false);
				contentPane.add(header);
				contentPane.add(panel2);
				fileSelected = null;
			}
		});
		
		panelInicio.btnDescomp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelInicio.btnDescomp.setBackground(new Color(113, 113, 113));
				panelInicio.btnDescomp.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelInicio.btnDescomp.setBackground(new Color(20, 100, 255));
				panelInicio.btnDescomp.setForeground(new Color(0, 0, 0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInicio.btnDescomp.setBackground(new Color(20, 100, 255));
				panelInicio.btnDescomp.setForeground(new Color(0, 0, 0));
			}
		});
		
		panelInicio.btnHistorial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelInicio.btnHistorial.setBackground(new Color(113, 113, 113));
				panelInicio.btnHistorial.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelInicio.btnHistorial.setBackground(new Color(250, 255, 23));
				panelInicio.btnHistorial.setForeground(new Color(0, 0, 0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInicio.btnHistorial.setBackground(new Color(250, 255, 23));
				panelInicio.btnHistorial.setForeground(new Color(0, 0, 0));
			}
		});
		
		panel2.btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPrincipal(panel2);
				panel2.limpiarCasillas();
			}
		});
		
		panel2.btnRevisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(fileSelected == null) {
					JOptionPane.showMessageDialog(panel2, "Debes seleccionar un archivo primero");
				}
				else {					
					toTextViewer(panel2, panel3);
					panel2.limpiarCasillas();
				}
			}
		});
		
		panel2.btnExplorador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
	            
				fileChooser.setFileFilter(filter);
	            fileChooser.setAcceptAllFileFilterUsed(false);
	            
	            int result = fileChooser.showOpenDialog(null);
	            
	            if (result == JFileChooser.APPROVE_OPTION) {
	                // Obtener el archivo seleccionado
	                File selectedFile = fileChooser.getSelectedFile();
	                fileSelected = selectedFile.getAbsolutePath();
	                
//	                File file = new File(selectedFile.getAbsolutePath());
//	                String fileName = file.getName();
	                panel2.txtPath.setText(fileSelected);
	            } else {
	            	fileSelected = null;
	            }
			}
		});
		
		contentPane.add(panelInicio);
	}
	
	public void panelPrincipal(JPanel anterior) {
		panelInicio.setVisible(true);
		anterior.setVisible(false);
		header.setVisible(false);
		contentPane.add(panelInicio);
		
	}
	
	public void toTextViewer(JPanel anterior, JPanel nuevo) {
		nuevo.setVisible(true);
		anterior.setVisible(false);
		contentPane.add(nuevo);
	}

}

