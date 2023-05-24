package backEnd;

import Huffman.Huffman;
import Huffman.CompressedFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BackEnd {
    /**
     * Removes the file extension from a filename
     * @param fileName - String that is the name of the file
     * @return the filename without the extension
     */
    public static String removeExtension(String fileName)
    {
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    /**
     * Return the file extension for the given file
     * @param file - file from to get extension
     * @return The extension in question without the point
     */
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return null;
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
     * @param fileDirection, location of the file
     * @return File Content
     * @throws IOException
     */
    public static String readFile(String fileDirection) throws IOException {
        File file = FileManager.readTxt(fileDirection);
        FileReader fr = new FileReader(file);
        BufferedReader b = new BufferedReader(fr);
        String linea;
        String lectura = "";
        while ((linea = b.readLine()) != null) {
            lectura = lectura.concat(linea).concat("\n");
        }
        b.close();
        fr.close();
        return lectura;
    }

    public static String decompressFile(String memoryDirection) throws IOException, ClassNotFoundException {
        return Huffman.decompressFile(FileManager.readCompressedFile(memoryDirection));
    }

    //
    public static CompressedFile compressFile(File file) throws IOException {
        return Huffman.compressTxt(file);
    }
}
