package Entities;

public class Node<T>{
    private Node root = null;
    private Node left = null;
    private Node right = null;

    private SymbolProbability1 data;


    public Node(SymbolProbability1 data) {
        this.data = data;
    }

    public SymbolProbability1 getData() {
        return data;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setData(SymbolProbability1 data) {
        this.data = data;
    }

    public Node<SymbolProbability1> getRight() {
        return right;
    }

    public Node<SymbolProbability1> getLeft() {
        return left;
    }

    public Node getRoot() {
        return root;
    }


}
