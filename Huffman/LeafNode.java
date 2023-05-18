package Huffman;

import java.io.Serializable;
import java.util.BitSet;

/**
 * Santiago Yepes Mesa.
 * Code highly based from the video: "Huffman Coding Algorithm Explained and Implemented in Java | Data Compression | Geekific" by Geekific
 */

public class LeafNode extends Node implements Serializable {
    private final char value;
    private BitSet code;

    /**
     * Constructor for the leadNode.
     * @param value char that the node represents
     * @param frequency that the node represents
     */
    public LeafNode(char value, int frequency)
    {
        super(frequency);
        this.value = value;
        code = new BitSet();
    }

    /**
     * returns the char value of the leaf
     * @return value of leaf
     */
    public char getValue() {
        return value;
    }

    public void setCode(BitSet code) {
        this.code = code;
    }
}
