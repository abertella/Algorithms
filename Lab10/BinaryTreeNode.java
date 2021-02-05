public class BinaryTreeNode<T> {
    
    private T data;
    private BinaryTreeNode<T> rightNode;
    private BinaryTreeNode<T> leftNode;
    private BinaryTreeNode<T> parent;
    private long key;

    public BinaryTreeNode(T data, long key, BinaryTreeNode<T> parent) {
        this.data = data;
        this.rightNode = null;
        this.leftNode = null;
        this.parent = parent;
        this.key = key;
    }
    
    public void setRight(BinaryTreeNode<T> paramTreeNode) {
        this.rightNode = paramTreeNode;
    }
        
    public void setLeft(BinaryTreeNode<T> paramTreeNode) {
        this.leftNode = paramTreeNode;
    }

    public void setParent(BinaryTreeNode<T> paramTreeNode) {
        this.parent = paramTreeNode;
    }
        
    public BinaryTreeNode<T> getRight() {
        return this.rightNode;
    }
        
    public BinaryTreeNode<T> getLeft() {
        return this.leftNode;
    }
        
    public T getData() {
        return this.data;
    }

    public BinaryTreeNode<T> getParent() {
        return this.parent;
    }

    public long getKey() {
        return this.key;
    }

    public String toString() {
        String dataString = this.data.toString();
        String rightChild = getRight().getData().toString();
        String leftChild = getLeft().getData().toString();
        String nodeString = String.format("Node: %s, Left Child: %s, Right Child: %s", dataString, leftChild, rightChild);
        return nodeString;
    }

} 