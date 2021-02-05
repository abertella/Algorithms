public class BinaryTree<E> {
  
    private BinaryTreeNode<E> root;
    private int size;
    private E defaultValue;

    public BinaryTree(E defaultValue) {
      this.root = null;
      this.size = 0;
      this.defaultValue = defaultValue;
    }

    public boolean insert(long key, E e) {
      if (e == null) {
      return false;
    }
      else if (this.root == null && this.size == 0) {
        this.root = new BinaryTreeNode<E>(e, key, null);
        this.size++;
        return true;
      }
      else {
        recursiveInsert(this.root, e, key);
        this.size++;
        return true;
      }
    }
    
    private void recursiveInsert(BinaryTreeNode<E> top, E insertValue, long key) {
      if(top.getKey() <= key){
        if(top.getRight() == null) {
          BinaryTreeNode<E> insertNode = new BinaryTreeNode<E>(insertValue, key, top);
          top.setRight(insertNode);
        }
        else{
          recursiveInsert(top.getRight(), insertValue, key);
      }
    }
      else if(top.getKey() > key){
        if(top.getLeft() == null) {
          BinaryTreeNode<E> insertNode = new BinaryTreeNode<E>(insertValue, key, top);
          top.setLeft(insertNode);
        }
        else {
          recursiveInsert(top.getLeft(), insertValue, key);
        }
      }
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

    private E recursiveSearch(BinaryTreeNode<E> top, long key) {
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

    public void inOrderWalk(BinaryTreeNode<E> top) {
        if (top != null) {
            inOrderWalk(top.getLeft());
            System.out.printf("%d : %s\n", top.getKey(), top.getData());
            inOrderWalk(top.getRight());
        }
    }

    public BinaryTreeNode getRoot() {
        return this.root;
    }
}