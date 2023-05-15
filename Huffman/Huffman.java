package Huffman;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.HashMap;

/**
 * Santiago Yepes Mesa, Simon Eduardo Parisca Monuñoz, Santiago Augusto Toro Bonilla
 */

public class Huffman {
    /**
     * Given a .txt file, returns the compressed version of this file in the way of a CompressedFile class object.
     * @param file to compress
     * @return CompressedFile object.
     */
    public CompressedFile compressTxt(File file)
    {
        return null;
    }

    /**
     * Given a compressed file, of the extension .compr, returns the .txt uncompressed version of this file
     * @param compressedFile
     * @return .txt File
     */
    public File decompressFile(CompressedFile compressedFile)
    {
        return null;
    }

    /**
     * Given a file returns the count of the characters in form of a hashmap, where its key is the
     * character, and the value is the frequency of the character.
     * @param file to count the number of characters
     * @return HashMap that contains the number of chars
     */
    private HashMap<Character, Integer> countCharacters(File file) throws FileNotFoundException, FileExtensionException, IOException
    {
        if (!file.getName().endsWith(".txt") || file.getName().isBlank())
        {
            throw new FileExtensionException();
        }

        HashMap<Character, Integer> count = new HashMap<>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        // UsingFile Reader instead of BufferedReader since it only needs to check for une character at a time

        int character;
        while ((character = br.read()) != -1)
        {
            if (count.containsKey((char) character))
                count.replace((char) character, count.get((char) character) + 1);

            else
                count.put((char) character, 1);
        }

        return count;
    }
}
