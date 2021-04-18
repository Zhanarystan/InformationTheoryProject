package Entities;


public class BinarySearchTree<T extends Comparable> {
    private Node<T> root = null;
    private int size = 0;

    public BinarySearchTree(SymbolProbability1 data) {
        root = new Node(data);
        size++;
    }

    public BinarySearchTree() {
    }

    public void insert(SymbolProbability1 data) {
        if (size == 0) {
            root = new Node(data);
            size++;
        } else {
            if(size == 1){
                insert(root,data);
            }
            else {
                insert(largest(), data);
            }
        }
    }

    private void insert(Node node, SymbolProbability1 data) {

        if (data.compareTo(node.getData()) == -1) {
            if (node.getLeft() == null) {

                node.setLeft(new Node(data));
                size++;
            } else {
                insert(node.getLeft(), data);
            }

        } else if (data.compareTo(node.getData()) >= 0) {
            if (node.getRight() == null) {

                node.setRight(new Node(data));
                size++;
            } else {
                insert(node.getRight(), data);
            }

        }
    }

    public SymbolProbability1 find(SymbolProbability1 data) {
        if (data.equals(root.getData())) {
            return root.getData();
        }
        String code = "";
        return find(root, data, code);
    }

    private SymbolProbability1 find(Node node, SymbolProbability1 data, String code) {

        node.getData().setCode(code);
        if (data.compareTo(node.getData()) == -1) {
            if (node.getData().equals(data)) {

                return node.getData();
            } else {
                return find(node.getLeft(), data, code+"0");
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getData().equals(data)) {

                return node.getData();
            } else {
                return find(node.getRight(), data, code+"1");
            }
        }
        return node.getData();
    }

    public SymbolProbability1 smallest() {
        Node cur = root;
        while (cur.getLeft() != null) {
            cur = cur.getLeft();
        }
        return cur.getData();
    }

    public Node largest() {
        Node cur = root;
        while (cur.getRight() != null) {
            cur = cur.getRight();
        }
        return cur;
    }

    public void print() {
        print(root);
        System.out.println();
    }

    private void print(Node node) {
        if (node != null) {
            print(node.getLeft());
            System.out.print(node.getData().getSymbol()  + " ");
            print(node.getRight());
        }
    }

    public SymbolProbability1 delete(SymbolProbability1 data){
        SymbolProbability1 obj=find(data);
        delete(root,data);
        return obj;
    }

    private Node delete(Node node,SymbolProbability1 data){
        if(node==null){
            return node;
        }
        if(data.compareTo(node.getData())==-1){
            node.setLeft(delete(node.getLeft(),data));
        }
        else if(data.compareTo(node.getData())==1){
            node.setRight(delete(node.getRight(),data));
        }
        else {
            if(node.getLeft()==null){
                return node.getRight();
            }
            else if(node.getRight()==null){
                return node.getLeft();
            }

            node.setData(minValue(node.getRight()));

            node.setRight(delete(node.getRight(), node.getData()));
        }
        return node;
    }

    private SymbolProbability1 minValue(Node node){
        SymbolProbability1 min= node.getData();
        while (node.getLeft()!=null){
            min= node.getData();
            node=node.getLeft();
        }
        return min;
    }
}
