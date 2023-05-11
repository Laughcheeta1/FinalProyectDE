package Huffman;

public class Node {
    private Node parent;
    private Node left;
    private Node right;

    public Node(Node parent)
    {
        this.parent = parent;
        left = null;
        right = null;
    }

    public Node()
    {
        parent = null;
        left = null;
        right = null;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getParent() {
        return parent;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
