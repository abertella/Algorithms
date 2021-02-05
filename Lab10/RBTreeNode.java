public class RBTreeNode<T> {
    
    private String color;
    private T data;
    private RBTreeNode<T> rightNode;
    private RBTreeNode<T> leftNode;
    private RBTreeNode<T> parent;
    private long key;
    
    public RBTreeNode(T data, long key, RBTreeNode<T> parent) {
        this.data = data;
        this.rightNode = null;
        this.leftNode = null;
        this.parent = parent;
        this.key = key;
        this.color = "red";
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
    
    public void setRight(RBTreeNode<T> paramTreeNode) {
        this.rightNode = paramTreeNode;
    }
        
    public void setLeft(RBTreeNode<T> paramTreeNode) {
        this.leftNode = paramTreeNode;
    }

    public void setParent(RBTreeNode<T> paramTreeNode) {
        this.parent = paramTreeNode;
    }
        
    public RBTreeNode<T> getRight() {
        return this.rightNode;
    }
        
    public RBTreeNode<T> getLeft() {
        return this.leftNode;
    }
        
    public T getData() {
        return this.data;
    }

    public RBTreeNode<T> getParent() {
        return this.parent;
    }

    public long getKey() {
        return this.key;
    }

    public String toString() {
        String dataString = this.getData().toString();
        String nodeString = String.format("Node: %d %s[%s]", this.key, dataString, color);
        return nodeString;
    }
}