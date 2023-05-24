package frontEnd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Huffman.CompressedFile;
import backEnd.FileViewerBackEnd;
import backEnd.FileExtensionException;
import backEnd.FileManager;

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
	String fileSelectedName;
	String memorySelected;
	File fileToCompress;
	String fileSelected2;
	String fileSelectedName2;
	String memorySelected2;
	File fileToCompress2;
	String auxText;
	panelInicio panelInicio = new panelInicio();
	panelComprimir panel2 = new panelComprimir();
	panelTextViewer1 panel3 = new panelTextViewer1();
	panelDescomprimir panel4 = new panelDescomprimir();
	panelTextViewer2 panel5 = new panelTextViewer2();
	panelHistorial panelH = new panelHistorial();
		
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
				fileSelectedName = null;
				memorySelected = null;
				fileToCompress = null;
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
				header.add(titulo);				
				header.setVisible(true);
				panel4.setVisible(true);
				panelInicio.setVisible(false);
				contentPane.add(header);
				contentPane.add(panel4);
				fileSelected2 = null;
				fileSelectedName2 = null;
				memorySelected2 = null;
				fileToCompress2 = null;
				auxText = null;
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
					
					try {
						String textTofill = FileViewerBackEnd.readFile(fileSelected);
						panel3.textFileViewer.setText(textTofill);
						panel3.lblTituloText.setText(fileSelectedName);
						memorySelected = null;
						toTextViewer(panel2, panel3);
						panel2.limpiarCasillas();
					}
					catch(IOException err) {
						JOptionPane.showMessageDialog(panel2, err.getMessage());
					}
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
	                fileSelectedName = selectedFile.getName();
	                fileToCompress = new File(selectedFile.getAbsolutePath());
	                
	                panel2.txtPath.setText(fileSelected);
	            } else {
	            	fileSelected = null;
	            }
			}
		});
		
		panel3.btnExplorador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("Seleccione una carpeta");
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		        int returnValue = fileChooser.showOpenDialog(null);
	            
	            if (returnValue == JFileChooser.APPROVE_OPTION) {
	                // Obtener el archivo seleccionado
//	            	String selectedPath = fileChooser.getSelectedFile().getPath();
	                File selectedFile = fileChooser.getSelectedFile();
	                memorySelected = selectedFile.getAbsolutePath();
//	                File file = new File(selectedFile.getAbsolutePath());
//	                String fileName = file.getName();
	                panel3.textMemorySelected.setText(memorySelected);
	                //Se necesita guardar el nombre del file para el fichero
	            } else {
	            	memorySelected = null;
	            }
			}
		});
		
		panel3.btnComprimir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(memorySelected == null) {
					JOptionPane.showMessageDialog(panel3, "Debes seleccionar un lugar en memoria");
				}
				else {
					try {
						CompressedFile filesito = FileViewerBackEnd.compressFile(fileToCompress);
						FileManager.writeCompressedFile(memorySelected, filesito, fileSelectedName);
						JOptionPane.showMessageDialog(panel3, "Archivo guardado");
						panel3.limpiar();
						toComprimir(panel3);
					} catch (IOException err) {
						JOptionPane.showMessageDialog(panel3, err.getMessage());
					}
				}
			}
		});
		
		panel3.btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel3.limpiar();
				toComprimir(panel3);
			}
		});
		
		panel4.btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPrincipal(panel4);
				panel4.limpiarCasillas();
			}
		});
		
		panel4.btnRevisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(fileSelected2 == null) {
					JOptionPane.showMessageDialog(panel4, "Debes seleccionar un archivo primero");
				}
				else {	
					
					try {
//						String textTofill = FileViewerBackEnd.readFile(fileSelected2);
						String textTofill = FileViewerBackEnd.decompressFile(fileSelected2);
						auxText = textTofill;
						panel5.textFileViewer.setText(textTofill);
						panel5.lblTituloText.setText(fileSelectedName2);
						memorySelected2 = null;
						toTextViewer(panel4, panel5);
						panel4.limpiarCasillas();
					}
					catch(IOException err) {
						JOptionPane.showMessageDialog(panel4, err.getMessage());
					}
					catch(FileExtensionException err) {
						JOptionPane.showMessageDialog(panel4, err.getMessage());
					}
					catch(ClassNotFoundException err) {
						JOptionPane.showMessageDialog(panel4, err.getMessage());
					}
				}
			}
		});
		
		panel4.btnExplorador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos comprimidos", "comp");
	            
				fileChooser.setFileFilter(filter);
	            fileChooser.setAcceptAllFileFilterUsed(false);
	            
	            int result = fileChooser.showOpenDialog(null);
	            
	            if (result == JFileChooser.APPROVE_OPTION) {
	                // Obtener el archivo seleccionado
	                File selectedFile = fileChooser.getSelectedFile();
	                
	                fileSelected2 = selectedFile.getAbsolutePath();
	                fileSelectedName2 = selectedFile.getName();
	                fileToCompress2 = new File(selectedFile.getAbsolutePath());
	                
	                panel4.txtPath.setText(fileSelected2);
	            } else {
	            	fileSelected2 = null;
	            }
			}
		});
		
		panel5.btnExplorador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("Seleccione una carpeta");
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		        int returnValue = fileChooser.showOpenDialog(null);
	            
	            if (returnValue == JFileChooser.APPROVE_OPTION) {
	                // Obtener el archivo seleccionado
//	            	String selectedPath = fileChooser.getSelectedFile().getPath();
	                File selectedFile = fileChooser.getSelectedFile();
	                memorySelected2 = selectedFile.getAbsolutePath();
//	                File file = new File(selectedFile.getAbsolutePath());
//	                String fileName = file.getName();
	                panel5.textMemorySelected.setText(memorySelected2);
	                //Se necesita guardar el nombre del file para el fichero
	            } else {
	            	memorySelected2 = null;
	            }
			}
		});
		
		panel5.btnDescomprimir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(memorySelected2 == null) {
					JOptionPane.showMessageDialog(panel5, "Debes seleccionar un lugar en memoria");
				}
				else {
					try {
						String newName = fileSelectedName2.substring(0, fileSelectedName2.indexOf("."));
						newName += ".txt";
						FileManager.writeText(memorySelected2,auxText ,newName);
						JOptionPane.showMessageDialog(panel5, "Archivo guardado");
						panel5.limpiar();
						toDescomprimir(panel5);
					} catch (IOException err) {
						JOptionPane.showMessageDialog(panel5, err.getMessage());
					}
				}
			}
		});
		
		panel5.btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel5.limpiar();
				toDescomprimir(panel5);
			}
		});
		
		
		DropTarget dropTarget = new DropTarget(panel2.txtPath, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent event) {
                event.acceptDrop(DnDConstants.ACTION_COPY);
                Transferable transferable = event.getTransferable();

                if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    try {
                        List<File> fileList = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                        // Aquí puedes procesar los archivos
                        for (File file : fileList) {
                            // Realiza las operaciones necesarias con cada archivo
                            // Por ejemplo, obtener datos o realizar acciones específicas
                        	String extension = getFileExtension(file);
                            if (extension != null && extension.equalsIgnoreCase("txt")) {
                            	fileSelected = file.getAbsolutePath();
            	                fileSelectedName = file.getName();
            	                fileToCompress = new File(file.getAbsolutePath());
            	                
            	                panel2.txtPath.setText(fileSelected);
                            }
                            else {
                            	JOptionPane.showMessageDialog(panel2, "Extension no soportada");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                event.dropComplete(true);
            }
        });
		panel2.txtPath.setDropTarget(dropTarget);
		
		panel2.txtPath.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            }

            @Override
            public boolean importData(TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }

                Transferable transferable = support.getTransferable();
                try {
                    List<File> fileList = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    // Aquí puedes procesar los archivos
                    for (File file : fileList) {
                        // Realiza las operaciones necesarias con cada archivo
                        // Por ejemplo, obtener datos o realizar acciones específicas
                    	String extension = getFileExtension(file);
                        if (extension != null && extension.equalsIgnoreCase("txt")) {
                        	fileSelected = file.getAbsolutePath();
        	                fileSelectedName = file.getName();
        	                fileToCompress = new File(file.getAbsolutePath());
        	                
        	                panel2.txtPath.setText(fileSelected);
                        }
                        else {
                        	JOptionPane.showMessageDialog(panel2, "Extension no soportada");
                        }
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return false;
            }
        });
		
		DropTarget dropTarget2 = new DropTarget(panel4.txtPath, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent event) {
                event.acceptDrop(DnDConstants.ACTION_COPY);
                Transferable transferable = event.getTransferable();

                if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    try {
                        List<File> fileList = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                        // Aquí puedes procesar los archivos
                        for (File file : fileList) {
                            // Realiza las operaciones necesarias con cada archivo
                            // Por ejemplo, obtener datos o realizar acciones específicas
                        	String extension = getFileExtension(file);
                            if (extension != null && extension.equalsIgnoreCase("comp")) {
                            	fileSelected2 = file.getAbsolutePath();
            	                fileSelectedName2 = file.getName();
            	                fileToCompress2 = new File(file.getAbsolutePath());
            	                
            	                panel4.txtPath.setText(fileSelected2);
                            }
                            else {
                            	JOptionPane.showMessageDialog(panel2, "Extension no soportada");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                event.dropComplete(true);
            }
        });
		panel4.txtPath.setDropTarget(dropTarget2);
		
		panel4.txtPath.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            }

            @Override
            public boolean importData(TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }

                Transferable transferable = support.getTransferable();
                try {
                    List<File> fileList = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    // Aquí puedes procesar los archivos
                    for (File file : fileList) {
                        // Realiza las operaciones necesarias con cada archivo
                        // Por ejemplo, obtener datos o realizar acciones específicas
                    	String extension = getFileExtension(file);
                        if (extension != null && extension.equalsIgnoreCase("comp")) {
        	                fileSelected2 = file.getAbsolutePath();
        	                fileSelectedName2 = file.getName();
        	                fileToCompress2 = new File(file.getAbsolutePath());
        	                
        	                panel4.txtPath.setText(fileSelected2);
                        }
                        else {
                        	JOptionPane.showMessageDialog(panel4, "Extension no soportada");
                        }
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return false;
            }
        });
		
		
		
		contentPane.add(panelInicio);
	}
	
	public void panelPrincipal(JPanel anterior) {
		panelInicio.setVisible(true);
		anterior.setVisible(false);
		header.setVisible(false);
		fileSelected = null;
		fileSelectedName = null;
		memorySelected = null;
		fileToCompress = null;
		fileSelected2 = null;
		fileSelectedName2 = null;
		memorySelected2 = null;
		fileToCompress2 = null;
		auxText = null;
		contentPane.add(panelInicio);
		
	}
	
	public void toComprimir(JPanel anterior) {
		fileSelected = null;
		fileSelectedName = null;
		memorySelected = null;
		fileToCompress = null;
		panel2.setVisible(true);
		anterior.setVisible(false);
		contentPane.add(panel2);
	}
	
	public void toDescomprimir(JPanel anterior) {
		fileSelected = null;
		fileSelectedName = null;
		memorySelected = null;
		fileToCompress = null;
		auxText= null;
		panel4.setVisible(true);
		anterior.setVisible(false);
		contentPane.add(panel4);
	}
	
	public void toTextViewer(JPanel anterior, JPanel nuevo) {
		nuevo.setVisible(true);
		anterior.setVisible(false);
		contentPane.add(nuevo);
	}
	
	private String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return null;
    }

}

