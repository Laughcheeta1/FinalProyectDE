package Huffman;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.io.IOException;
import java.io.File;


/**
 * This is where the compressing and decompressing of files using the Huffman algorithm happens
 * Santiago Yepes Mesa, Simon Eduardo Parisca Muñoz, Santiago Augusto Toro Bonilla
 */
public class Huffman {
    /**
     * Given a .txt file, returns the compressed version of this file in the way of a CompressedFile class object.
     * @param file to compress
     * @return CompressedFile object.
     */
    public static CompressedFile compressTxt(File file) throws IOException
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
    private static ArrayList<Boolean> getEncodedText(Node treeHead, File file) throws IOException {
        ArrayList<Boolean> code = new ArrayList<>(); // Stores an array of booleans that later will become bits
        HashMap<Character, ArrayList<Boolean>> bitsets = generateCode(treeHead); // Get the code for each char in text

        FileReader fr = new FileReader(file);
        BufferedReader bf = new BufferedReader(fr);

        int character; // Is of type int because BufferedReader only allows to read the int value of the char
        while ((character = bf.read()) != -1)
        {
            code.addAll(bitsets.get((char) character)); // Retrieve the encoding for a given char, and add it
        }

        return code;
    }

    /**
     * Given a boolean ArrayList, return the bitset version of the ArrayList
     * @param code - Arraylist to convert
     * @return The bitset version
     */
    private static BitSet arrayListToBitSet(ArrayList<Boolean> code) {
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
     * @param head - head of the Huffman tree
     * @return The encoded version of a given Character
     */
    private static HashMap<Character, ArrayList<Boolean>> generateCode(Node head){
        HashMap<Character, ArrayList<Boolean>> bitsets = new HashMap<>();
        ArrayList<Boolean> actual = new ArrayList<>();

        return generateCode(head, actual, bitsets);
    }

    /**
     * Recursive method that generates the code for all the characters in the text, by traversing the tree in a
     *  DFS manner, each left turn corresponds to a 0 and each right turn corresponds to a 1
     * @param node - current node we are traversing in the huffman tree
     * @param actual - code that has been generated up until now
     * @param bitsets - the HashMap that stores the codes
     * @return HashMap that has all the codes
     */
    private static HashMap<Character, ArrayList<Boolean>> generateCode(Node node, ArrayList<Boolean> actual, HashMap<Character, ArrayList<Boolean>> bitsets){
        if (node instanceof LeafNode) {
            // If we get to a leaf node, we can stop generating the code, and assign the char contained in the leaf
                // node, its code.
            ArrayList<Boolean> codeOfTheNode = new ArrayList<>(actual);
            bitsets.put(((LeafNode) node).getValue(), codeOfTheNode);
        }
        else
        {
            // Keep going down the tree generating codes
            actual.add(false);
            bitsets = generateCode(node.getLeftNode(),actual, bitsets);
            actual.remove(actual.size() - 1);
            actual.add(true);
            bitsets = generateCode(node.getRightNode(), actual, bitsets);
        }

        return bitsets;
    }

    /**
     * Given a compressed text, returns the uncompressed version of the text contained in it
     * @param compressedFile - An CompressedFile object, that contains the desired text to decompress
     * @return decompressed text, in form of a String, contained in the compressedFile
     */
    public static String decompressFile(CompressedFile compressedFile) {
        Node treeHead = compressedFile.treeHead();
        BitSet encoding = compressedFile.encoding();
        int sizeOfTheCode = compressedFile.sizeOfTheCode();

        // Decompress the bit sequence using the HuffmanTree
        return decodeText(treeHead, encoding, sizeOfTheCode);
    }

    /**
     * Decodes the bits sequence using the Huffman tree, and returns the decompressed text
     * @param treeHead - The head of the Huffman tree
     * @param encoding - the bit sequence containing the compressed text
     * @param sizeOfTheCode - the amount of bits that compose the encoded message
     * @return the decompressed text in a String form
     */
    private static String decodeText(Node treeHead, BitSet encoding, int sizeOfTheCode) {
        StringBuilder decompressedText = new StringBuilder();
        Node currentNode = treeHead; // We start the decoding in the Tree head

        int i = 0;
        while (i < sizeOfTheCode) { // Only decode the amount of bits that we are using
            if (encoding.get(i)) {
                currentNode = currentNode.getRightNode(); // If the bit is a one, go right
            } else {
                currentNode = currentNode.getLeftNode(); // If the bit is a zero, go left
            }

            // When we get to a leaf, means we have finished decoding one of the characters
            if (currentNode instanceof LeafNode) {
                decompressedText.append(((LeafNode) currentNode).getValue());
                currentNode = treeHead; // Restart from the tree root
            }

            i++;
        }

        return decompressedText.toString();
    }




    /**
     * Given a file returns the count of the characters in form of a hashmap, where its key is the
     * character, and the value is the frequency of the character.
     * @param file - File to count the number of characters
     * @return HashMap that contains the number of chars
     */
    private static HashMap<Character, Integer> countCharacters(File file) throws IOException
    {
        // Create the needed resources
        HashMap<Character, Integer> count = new HashMap<>();
        FileReader fr = new FileReader(file);
        // Buffered reader is faster than file reader, that's why we use it
        BufferedReader br = new BufferedReader(fr);

        // Count the times each character appears, and save it into the HashMap
        int character; // Is of type int because BufferedReader only allows to read the int value of the char
        while ((character = br.read()) != -1)
        {
            if (count.containsKey((char) character)) // If char is contained, just add 1 to its frequency
                count.replace((char) character, count.get((char) character) + 1);

            else // If the char is not contained, add it
                count.put((char) character, 1);
        }

        return count;
    }


    /**
     * Given a HashMap that stores the frequencies returns the Tree head of the Huffman tree
     * @param frequencies - HashMap containing the frequency of the characters
     * @return Head node of the tree
     */
    private static Node createHuffmanTree(HashMap<Character, Integer> frequencies)
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
}
