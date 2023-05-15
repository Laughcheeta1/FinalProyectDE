package Huffman;

import java.io.Serializable;
import java.util.BitSet;

/**
 * Santiago Yepes Mesa
 */
public class CompressedFile implements Serializable {
    private Node treeHead;
    private BitSet encoding;

    public Node getTreeHead()
    {
        return treeHead;
    }

    public BitSet getEncoding()
    {
        return encoding;
    }
}
