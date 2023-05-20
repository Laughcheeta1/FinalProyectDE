package Huffman;

import java.io.Serializable;
import java.util.BitSet;

/**
 * The class is a record, since it is only used for storing Data.
 * Explanation of the variables:
 *  treeHead: stores the head node of the HuffmanTree
 *  encoding: is the encoded version of the text, in the form of a BitSet
 *      We are using a BitSet since booleans actually use 1 byte (the same as a char) instead of 1 bit, so there's no
 *      point to the compression if we were to use booleans.
 *  sizeOfTheCode: since the BitSets does not have the exact size we want, it will be often that there's going to be
 *      more zeros in the BitSet that we need. So to counter this, we must store the exact number of bits we are using.
 * Santiago Yepes Mesa
 */
public record CompressedFile(Node treeHead, BitSet encoding, int sizeOfTheCode) implements Serializable {
}
