package Huffman;

public class LeafNode extends Node{
    private char value;

    public LeafNode(Node parent)
    {
        super(parent);
    }

    public LeafNode()
    {
        super();
    }

    public char getValue() {
        return value;
    }
}
