package Huffman;

import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.PriorityQueue;

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
        return new CompressedFile(treeHead, null);
    }

    /**
     * Given a compressed file, of the extension .compr, returns the .txt uncompressed version of this file
     * @param compressedFile with the .compr extension
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
     * Recursive method that given a Huffman tree and the hashmap returns the entire compressed
     * binary code of the file.
     * @param head
     * @return
     */
    private BitSet generateCode(Node head, Node node, HashMap<Character, Integer> frequencies){
        BitSet code = new BitSet();
        return generateCode(head, node, frequencies, code, 0);
    }
    private BitSet generateCode(Node head, Node node, HashMap<Character, Integer> frequencies, BitSet code, int actual){
        Node rs = head.getRightNode();
        Node ls = head.getLeftNode();
        if (rs == null && ls == null)
            return code;
        if (ls != null) {
            code.set(actual, false);
            return generateCode(rs, node, frequencies, code, actual + 1);
        }
        else {
            code.set(actual, true);
            return generateCode(ls, node, frequencies, code, actual + 1);
        }
    }
}
