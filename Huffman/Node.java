package Huffman;

/**
 * Santiago Yepes Mesa.
 * Code highly based from the video: "Huffman Coding Algorithm Explained and Implemented in Java | Data Compression | Geekific" by Geekific
 */

public class Node implements Comparable<Node> {
    private final int frequency; // Frequency that the node represents
    private final Node leftNode; // Left node child
    private final Node rightNode; // Right node child

    /**
     * Constructor
     * @param leftNode - The left son of the node
     * @param rightNode - The right son of the node
     */
    public Node(Node leftNode, Node rightNode)
    {
        this.frequency = leftNode.getFrequency() + rightNode.getFrequency();
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    /**
     * Second constructor, in case that the Node being created is a leaf Node
     * @param frequency that the node represents
     */
    public Node(int frequency)
    {
        this.frequency = frequency;
        this.leftNode = null;
        this.rightNode = null;
    }

    /**
     * @return Node to the left
     */
    public Node getLeftNode() { return leftNode; }


    /**
     * @return Node to the right
     */
    public Node getRightNode() { return rightNode; }

    /**
     * @return The frequency that the node is representing
     */
    public int getFrequency() { return frequency; }

    /**
     * Returns the comparison between the node and a node you give, based on the frequencies of each node,
     * returns 0 if the nodes have the same frequencies, else it will return the difference between the two numbers;
     * if this node has a higher frequency, the difference is going to be positive, if the given node has a higher
     * frequency then the difference is going to be negative.
     * @param node the object to be compared.
     * @return result of the comparison
     */
    @Override
    public int compareTo(Node node)
    {
        return this.frequency - node.getFrequency();
    }
}
