package Huffman;

import java.io.Serializable;
import java.util.BitSet;

/**
 * Santiago Yepes Mesa
 */
public record CompressedFile(Node treeHead, BitSet encoding) implements Serializable {
}
