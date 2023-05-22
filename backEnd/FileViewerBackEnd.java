package backEnd;

import Huffman.CompressedFile;

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
	public static String readFile(String direccionArchivo) throws IOException {
		File file = FileManager.readTxt(direccionArchivo);
		FileReader fr = new FileReader(file);
		BufferedReader b = new BufferedReader(fr);
		String linea;
		String lectura = "";
		while ((linea = b.readLine()) != null) {
			lectura = lectura.concat(linea); //.concat("\n");
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
		File file = new File(downloadsPath().toString()); // Se crea el archivo en Descargas
		//método para leer archivo
		Desktop.getDesktop().open(file); //Se abre el archivo
	}

	public static boolean isFileCompressed(String filePath) { // Ingresa la dirección o ruta del archivo
		File file = new File(filePath);
		return file.getName().endsWith(".compr");
	}

	/**
	 * Saves a Txt file containing the given text to the specified place in memory
	 * @param path
	 * @param text
	 */
	public static void saveTxtFile(String path, String text)
	{
		FileManager.writeText(path, text);
	}

	/**
	 * Saves a CompreseedFile object to the specified place in memory
	 * @param path
	 * @param compressedFile
	 */
	public static void saveCompressedFile(String path, CompressedFile compressedFile)
	{
		FileManager.writeCompressedFile(path, compressedFile);
	}
}