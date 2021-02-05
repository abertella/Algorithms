import java.util.*;
import java.io.*;

public class Lab08 {

    public static String[] strings1;
    public static String[] strings2;
    public static BinaryTree<String> bTree2;
    public static BinaryTree<String> bTree3;
    public static long[] keys = new long[100];

    public static String[] getArray(String path, boolean shuffle) throws IOException {
        List<String> lines = new ArrayList<String>();
        BufferedReader in = null;
        try { 
            in = new BufferedReader(new FileReader(path));
            String line;
            while((line = in.readLine()) != null) {
                lines.add(line);
            }
        }
        finally {
            if(in != null){
                in.close();
            }
        }
        if(shuffle) {
            Collections.shuffle(lines);
        }
        return lines.toArray(new String[lines.size()]);
    }

    public static void makeTree(BinaryTree tree, String[] strings){
        for(String str : strings) {
            long key = Long.parseLong(str.split(",")[0]);
            tree.insert(key, str);
        }
    }

    public static void makeKeys(String[] strings) {
        int index;
        for(int i = 0; i < keys.length; i++) {
            index = (int)(Math.random()*strings.length);
            keys[i] = Long.parseLong(strings[index].split(",")[0]);
        }
    }
    public static void main(String[] args) {
        BinaryTree<String> bTree = new BinaryTree("Key not found.");
        long i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10;
        i0 = 72; 
        i1 = 70; 
        i2 = 98; 
        i3 = 65; 
        i4 = 71; 
        i5 = 82;
        i6 = 101; 
        i7 = 50; 
        i8 = 68; 
        i9 = 79;
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

        System.out.println("----------Search----------");
        System.out.printf("%d : %s\n", i8, bTree.search(i8));
        System.out.printf("%d : %s\n", i4, bTree.search(i4));
        System.out.printf("%d : %s\n", i10, bTree.search(i10));
        System.out.println("----------Traversal----------");
        bTree.inOrderWalk();
        System.out.println();
        try{
            bTree2 = new BinaryTree<String>("Key not found");
            strings1 = getArray("input.dat", true);
            makeTree(bTree2, strings1);
            bTree2.inOrderWalk();
            System.out.println();
            bTree3 = new BinaryTree<String>("Key not found.");
            strings2 = getArray("UPC.csv", true);
            makeTree(bTree3, strings2);
            makeKeys(strings2);
            for(Long key : keys) {
                String data;
                long startTime = System.nanoTime();
                data = bTree3.search(key);
                long endTime = System.nanoTime();
                System.out.format("Search on bTree3 %d : %d \u03BCs\t", key, (endTime-startTime)/1000);
                System.out.format("%d : %s\n", key, data);
            }
            for(int i = 0; i < 5; i++) {
                String data;
                long startTime = System.nanoTime();
                data = bTree3.search(i);
                long endTime = System.nanoTime();
                System.out.format("Search on bTree3 %d : %d \u03BCs\t", i, (endTime-startTime)/1000);
                System.out.format("%d : %s\n", i, data);
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        
        }
    }
}