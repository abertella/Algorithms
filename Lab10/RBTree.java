public class RBTree<E> {
    
    private RBTreeNode<E> root;
    private int size;
    private E defaultValue;

    public RBTree(E defaultValue) {
        this.root = null;
        this.size = 0;
        this.defaultValue = defaultValue;;
    }
    public boolean insert(long key, E e) {
        RBTreeNode<E> insertNode = new RBTreeNode<E>(e, key, null);
        if (e == null) {
        return false;
      }
        else if (this.root == null && this.size == 0) {
          this.root = insertNode;
          this.size++;
          return true;
        }
        else {
          recursiveInsert(this.root, insertNode);
          this.size++;
          rBFixup(insertNode);
          return true;
        }
      }
      
    private void recursiveInsert(RBTreeNode<E> top, RBTreeNode<E> iNode) {
        if(top.getKey() <= iNode.getKey()){
            if(top.getRight() == null) {
            top.setRight(iNode);
            iNode.setParent(top);
          }
          else{
            recursiveInsert(top.getRight(), iNode);
        }
      }
        else if(top.getKey() > iNode.getKey()){
          if(top.getLeft() == null) {
            top.setLeft(iNode);
            iNode.setParent(top);
          }
          else {
            recursiveInsert(top.getLeft(), iNode);
          }
        }
    }

    private void rBFixup(RBTreeNode<E> node) {
        RBTreeNode y;
        if(this.size <= 2){
            this.root.setColor("black");
            return;
        }
        while(node != this.root && node.getParent().getColor() == "red") {
            if(node.getParent() == node.getParent().getParent().getLeft()) {
                y = node.getParent().getParent().getRight();
                if(y != null && y.getColor() == "red") {
                    node.getParent().setColor("black");
                    y.setColor("black");
                    node.getParent().getParent().setColor("red");
                    node = node.getParent().getParent();
                }
                else {
                    if(node == node.getParent().getRight()) {
                        node = node.getParent();
                        leftRotate(node);
                    }
                    node.getParent().setColor("black");
                    node.getParent().getParent().setColor("red");
                    rightRotate(node.getParent().getParent());
                }
            }
            else {
                y = node.getParent().getParent().getLeft();
                if(y != null && y.getColor() == "red") {
                    node.getParent().setColor("black");
                    y.setColor("black");
                    node.getParent().getParent().setColor("red");
                    node = node.getParent().getParent();
                }
                else {
                    if(node == node.getParent().getLeft()) {
                        node = node.getParent();
                        leftRotate(node);
                    }
                    node.getParent().setColor("black");
                    if(node.getParent().getParent() != null){
                        node.getParent().getParent().setColor("red");
                        rightRotate(node.getParent().getParent());
                    }
                }
            }
        }
        this.root.setColor("black");
    }
    private void leftRotate(RBTreeNode<E> node) {
        RBTreeNode y = node.getRight();
        node.setRight(y.getLeft());
        if(y.getLeft() != null) {
            y.getLeft().setParent(node);
            y.setParent(node.getParent());
            if(node.getParent() == null) {
                this.root = y;
            }
            else if(node == node.getParent().getLeft()) {
                node.getParent().setLeft(y);
            }
            else {
                node.getParent().setRight(y);
            }
        }
        y.setLeft(node);
        node.setParent(y);
    }

    private void rightRotate(RBTreeNode<E> node) {
        RBTreeNode y = node.getLeft();
        node.setLeft(y.getRight());
        if(y.getRight() != null) {
            y.getRight().setParent(node);
            y.setParent(node.getParent());
            if(node.getParent() == null) {
                this.root = y;
            }
            else if(node == node.getParent().getRight()) {
                node.getParent().setRight(y);
            }
            else {
                node.getParent().setLeft(y);
            }
        }
        y.setRight(node);
        node.setParent(y);
    }


    public int size(){
        return this.size;
      }
          
      public E search(long key) {
        if(this.root == null) {
          return null;
        }
        else {
          return recursiveSearch(this.root, key);
        }
      }
  
      private E recursiveSearch(RBTreeNode<E> top, long key) {
        if(top.getKey() == key) {
          return top.getData();
        }
        else if(top.getKey() < key){
          if (top.getRight() == null) {
            return defaultValue;
          }
          else {
            return recursiveSearch(top.getRight(), key);
          }
        }
        else if(top.getKey() > key) {
          if (top.getLeft() == null) {
            return defaultValue;
          }
          else {
            return recursiveSearch(top.getLeft(), key);
          }
        }
        else {
          return defaultValue;
        }
      }
  
      public void inOrderWalk() {
          inOrderWalk(this.root);
      }
  
      public void inOrderWalk(RBTreeNode<E> top) {
          if (top != null) {
              inOrderWalk(top.getLeft());
              System.out.printf("%d[%s] : %s\n", top.getKey(), top.getColor() ,top.getData());
              inOrderWalk(top.getRight());
          }
      }
  
      public RBTreeNode getRoot() {
          return this.root;
      }
}