public class Lab10 {
    public static void main(String[] args) {
        RBTree<String> bTree = new RBTree("Key not found.");
        long i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10;
        i0 = 3; 
        i1 = 2; 
        i2 = 1; 
        i3 = 4; 
        i4 = 5; 
        i5 = 6;
        i6 = 7; 
        i7 = 8; 
        i8 = 9; 
        i9 = 10;
        i10 = 85;

        bTree.insert(i0, "Andrew");
        bTree.insert(i1, "Thomas");
        bTree.insert(i2, "John");
        bTree.insert(i3, "Rita");
        bTree.insert(i4, "Betty");
        bTree.insert(i5, "Jerome");
        bTree.insert(i6, "Alice");
        bTree.insert(i7, "Thomas");
        bTree.insert(i8, "Logan");
        bTree.insert(i9, "Gina");
        bTree.inOrderWalk();
    }
}