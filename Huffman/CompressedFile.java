package Huffman;

import java.io.Serializable;
import java.util.BitSet;

/**
 * The class is a record, since it is only used for storing Data.
 * Explanation of the variables:
 *  baseNodes: stores an array of Strings that contain the necessary info for creating a huffman tree, the base nodes,
 *      and its frequencies. The first character of the string is the char, and the rest is the int frequency.
 *      and example would be "a13".
 *  encoding: is the encoded version of the text, in the form of a BitSet
 *      We are using a BitSet since booleans actually use 1 byte (the same as a char) instead of 1 bit, so there's no
 *      point to the compression if we were to use booleans.
 *  sizeOfTheCode: since the BitSets does not have the exact size we want, it will be often that there's going to be
 *      more zeros in the BitSet that we need. So to counter this, we must store the exact number of bits we are using.
 * Santiago Yepes Mesa
 */
public record CompressedFile(String[] baseNodes, BitSet encoding, int sizeOfTheCode) implements Serializable {
}
