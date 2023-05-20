package backEnd;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Creator: 
 * This class is the back end of the TextViewer program.
 */

public class FileViewerBackEnd {
	/**
	 * Returns the path of the download folder
	 * @return path of the download folder
	 */
    public static Path downloadsPath() {
		return Paths.get(System.getProperty("user.home"), "Downloads");
	}

	/**
	 * checks if a file exists for the given path
	 * @param path of the file
	 * @return if the file exists or not
	 */
    public static boolean checkPath(String path) {
		File file = new File(path);
		return file.exists();
	}

	/**
	 * Given the location of a file, returns the contents of the file as a String
	 * @param archivo, location of the file
	 * @return File Content
	 * @throws IOException
	 */
    public static String readFile(String archivo) throws IOException {
		File file = new File(archivo);
		FileReader fr = new FileReader(file);
		BufferedReader b = new BufferedReader(fr);
		String linea;
		String lectura = "";
		while ((linea = b.readLine()) != null) {
			lectura = lectura + linea + "\n";
		}
		b.close();
		fr.close();
		return lectura;
	}

	/**
	 * Saves the archive
	 * @throws IOException
	 */
    public static void save() throws IOException {
        File file = new File(downloadsPath().toString()); //Se crea el archivo en Descargas
        //método para leer archivo
		Desktop.getDesktop().open(file); //Se abre el archivo
	}

	public static boolean isFileCompressed(String filePath) { // Ingresa la dirección o ruta del archivo
		File file = new File(filePath);
		String extension = getFileExtension(file.getName());
		if (extension.equalsIgnoreCase("compr"))
			return true;
		else
			return false;
	}

	private static String getFileExtension(String fileName) {
		int Index = fileName.lastIndexOf(".");
		if (Index == -1 || Index == fileName.length() - 1) {
	        	/* Se verifica si el Indice es -1 o si el punto está al final del archivo para comprobar
	        		que la extensión sea valida, si es invalida retorna ("") */
			return "";
		}
		return fileName.substring(Index + 1); /* Se implementa para retornar la subcadena que se
		     											encuentra despues del punto */
	}

    // main method  · Susana Uribe
    public static void main(String[] args) throws IOException {
		String textForPath=downloadsPath().toString(); //We use this automatically in the space for the path
        
	}
}
