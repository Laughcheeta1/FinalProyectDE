package Huffman;

/**
 * Santiago Yepes Mesa.
 * Code highly based from the video: "Huffman Coding Algorithm Explained and Implemented in Java | Data Compression | Geekific" by Geekific
 */

public class LeafNode extends Node{
    private final char value;

    /**
     * Constructor for the leadNode.
     * @param value
     * @param frequency
     */
    public LeafNode(char value, int frequency)
    {
        super(frequency);
        this.value = value;
    }

    /**
     * returns the char value of the leaf
     * @return value of leaf
     */
    public char getValue() {
        return value;
    }
}
