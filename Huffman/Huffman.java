package Huffman;

import java.io.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.zip.InflaterInputStream;

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
        // Need this variable in order to save the amount of bits we are using
        ArrayList<Boolean> encodingBooleanVersion = getEncodedText(treeHead, file);
        BitSet encoding = arrayListToBitSet(encodingBooleanVersion);

        return new CompressedFile(treeHead, encoding, encodingBooleanVersion.size());
    }

    /**
     * Turns the text to bitset
     * @param treeHead - Head of the Huffman tree
     * @param file - .txt file that contains the text
     * @return The encoded version of the text, in the form of a bitset
     * @throws IOException - if the file does not exist
     */
    private ArrayList<Boolean> getEncodedText(Node treeHead, File file) throws IOException {
        ArrayList<Boolean> code = new ArrayList<>(); // Stores an array of booleans that later will become bits
        HashMap<Character, ArrayList<Boolean>> bitsets = generateCode(treeHead); // Get the code for each char in text

        FileReader fr = new FileReader(file);
        BufferedReader bf = new BufferedReader(fr);

        int character; // Is of type int because BufferedReader only allows to read the int value of the char
        while ((character = bf.read()) != -1)
        {
            code.addAll(bitsets.get((char) character));
        }

        return code;
    }

    /**
     * Given a boolean ArrayList, return the bitset version of the ArrayList
     * @param code - Arraylist to convert
     * @return The bitset version
     */
    private BitSet arrayListToBitSet(ArrayList<Boolean> code) {
        int x = code.size();
        // Give the BitSet the amount of bits we are going to use, so it allocates the necessary memory
        BitSet bitset = new BitSet(x);

        // Turn the Booleans to bits
        for (int i = 0; i < x; i++)
        {
            bitset.set(i, code.get(i));
        }

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
            ArrayList<Boolean> codeOfTheNode = new ArrayList<>(actual);
            bitsets.put(((LeafNode) node).getValue(), codeOfTheNode);
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
     * Given a compressed text, returns the uncompressed version of the text contained in it
     * @param compressedFile - An CompressedFile object, that contains the desired text to decompress
     * @return text contained in the compressedFile
     */
    public String decompressFile(CompressedFile compressedFile) throws IOException {
        /*
        File outputFile = new File(compressedFile.getParent(), getFileNameWithoutExtension(compressedFile) + ".txt");

        InputStream inputStream = Files.newInputStream(compressedFile.getPath());
        InflaterInputStream inflaterInputStream = new InflaterInputStream(inputStream);
        OutputStream outputStream = Files.newOutputStream(outputFile.toPath());

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inflaterInputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        inflaterInputStream.close();
        inputStream.close();

        return outputFile;*/
        return null;
    }

    /**
     * Obtiene el nombre de archivo sin la extensión.
     *
     * @param file El archivo del cual se desea obtener el nombre sin extensión.
     * @return El nombre del archivo sin la extensión.
     */
    private String getFileNameWithoutExtension(CompressedFile file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0) {
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
        // This method will probably be eliminated, since it has no need to be used. It will stick around for now.
        int indicePunto = nombreArchivo.lastIndexOf(".");
        if (indicePunto != -1) {
            return nombreArchivo.substring(0, indicePunto);
        }
        return nombreArchivo;
    }

}
