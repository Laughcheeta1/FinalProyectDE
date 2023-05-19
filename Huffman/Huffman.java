package Huffman;

import java.io.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.zip.*;

/**
 * Santiago Yepes Mesa, Simon Eduardo Parisca Muñoz, Santiago Augusto Toro Bonilla
 */

public class Huffman {
    /**
     * Given a .txt file, returns the compressed version of this file in the way of a CompressedFile class object.
     * @param file to compress
     * @return CompressedFile object.
     */
    public CompressedFile compressTxt(File file) throws FileExtensionException, IOException
    {
        Node treeHead = createHuffmanTree(countCharacters(file));
        BitSet encoding = getEncodedText(treeHead, file);

        return new CompressedFile(treeHead, encoding);
    }

    /**
     * Turns the text to bitset
     * @param treeHead
     * @param file
     * @return The enconded vertion of the text, in the form of a bitset
     * @throws IOException
     */
    private BitSet getEncodedText(Node treeHead, File file) throws IOException {
        ArrayList<Boolean> code = new ArrayList<>(); // Stores an array of booleans that later will become bits
        HashMap<Character, ArrayList<Boolean>> bitsets = generateCode(treeHead); // Get the code for each char in text

        FileReader fr = new FileReader(file);
        BufferedReader bf = new BufferedReader(fr);

        int character; // Is of type int because BufferedReader only allows to read the int value of the char
        while ((character = bf.read()) != -1)
        {
            code.addAll(bitsets.get((char) character));
        }

        return arrayListToBitSet(code);
    }

    /**
     * Given a boolean ArrayList, return the bitset of the code
     * @param code
     * @return The bitset
     */
    private BitSet arrayListToBitSet(ArrayList<Boolean> code) {
        BitSet bitset = new BitSet();

        int index = 0;
        for (Boolean value: code)
            bitset.set(index++, value);

        return bitset;
    }

    /**
     * Recursive method that given a Huffman tree returns the boolean code of each character in the text
     * @param head of the tree
     * @return The encoded version of a given Character
     */
    private HashMap<Character, ArrayList<Boolean>> generateCode(Node head){
        HashMap<Character, ArrayList<Boolean>> bitsets = new HashMap<>();
        ArrayList<Boolean> actual = new ArrayList<>();
        return generateCode(head, actual, bitsets);
    }

    /**
     * Recursive method that does the work
     * @param node, head node of the huffman tree
     * @param actual, code that has been generated up until now
     * @param bitsets, the HashMap that stores the codes
     * @return HashMap that has all the codes
     */
    private HashMap<Character, ArrayList<Boolean>> generateCode(Node node, ArrayList<Boolean> actual, HashMap<Character, ArrayList<Boolean>> bitsets){
        if (node instanceof LeafNode) {
            bitsets.put(((LeafNode) node).getValue(), actual);
            return bitsets;
        }
        actual.add(false);
        bitsets = generateCode(node.getLeftNode(),actual, bitsets);
        actual.remove(actual.size() - 1);
        actual.add(true);
        bitsets = generateCode(node.getRightNode(), actual, bitsets);
        return bitsets;
    }

    /**
     * Given a compressed file, of the extension .compr, returns the .txt uncompressed version of this file
     * @param compressedFile with the .compr extension
     * @return .txt File
     */
    public File decompressFile(File compressedFile) {
        File outputFile = new File(compressedFile.getParent(), getFileNameWithoutExtension(compressedFile) + ".txt");

        try {
            FileInputStream fileInputStream = new FileInputStream(compressedFile);
            GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = gzipInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            fileOutputStream.close();
            gzipInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputFile;
    }

    /**
     * Obtiene el nombre de archivo sin la extensión.
     *
     * @param file El archivo del cual se desea obtener el nombre sin extensión.
     * @return El nombre del archivo sin la extensión.
     */
    private String getFileNameWithoutExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(0, dotIndex);
        }
        return fileName;
    }

    /**
     * Given a file returns the count of the characters in form of a hashmap, where its key is the
     * character, and the value is the frequency of the character.
     * @param file to count the number of characters
     * @return HashMap that contains the number of chars
     */
    private HashMap<Character, Integer> countCharacters(File file) throws FileExtensionException, IOException
    {
        // Check that the file is of the correct type
        if (!file.getName().endsWith(".txt") || file.getName().isBlank())
        {
            throw new FileExtensionException();
        }

        // Create the needed resources
        HashMap<Character, Integer> count = new HashMap<>();
        FileReader fr = new FileReader(file);
        // Buffered reader is faster than file reader, that's why we use it
        BufferedReader br = new BufferedReader(fr);

        // Count the times each character appears, and save it into the HashMap
        int character; // Is of type int because BufferedReader only allows to read the int value of the char
        while ((character = br.read()) != -1)
        {
            if (count.containsKey((char) character))
                count.replace((char) character, count.get((char) character) + 1);

            else
                count.put((char) character, 1);
        }

        return count;
    }


    /**
     * Given a HashMap that stores the frequencies returns the Tree head of the Huffman tree
     * @param frequencies of the characters
     * @return Head node of the tree
     */
    private Node createHuffmanTree(HashMap<Character, Integer> frequencies)
    {
        // Creates a priority queue that will always have first the nodes that stores the lower values (because of the
            // compare to of the class Node)
        PriorityQueue<Node> list = new PriorityQueue<>();
        // Create all the base nodes of the tree
        for (Character c : frequencies.keySet())
        {
            list.add(new LeafNode(c, frequencies.get(c)));
        }

        // As the huffman code says, make a new node with the two lowest value nodes
        while (list.size() > 1)
        {
            list.add(new Node(list.poll(), list.poll()));
        }

        // The last remaining element of the queue is the head of the created tree
        return list.poll();
    }

    /**
     * Method that given the name of a file with the extension, returns the name of the file without the extension
     * @param nombreArchivo, the name of the archive with the extension
     * @return the name of the archive without the extension
     */
    private static String obtenerNombreSinExtension(String nombreArchivo) {
        int indicePunto = nombreArchivo.lastIndexOf(".");
        if (indicePunto != -1) {
            return nombreArchivo.substring(0, indicePunto);
        }
        return nombreArchivo;
    }

}
