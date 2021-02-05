import java.util.*;

public class Lab11 {
    public static void main(String[] args) {

        Graph myg = new Graph(6);
        myg.addEdge(1, 2);
        myg.addEdge(1, 5);
        myg.addEdge(2, 5);
        myg.addEdge(2, 3);
        myg.addEdge(2, 4);
        myg.addEdge(3, 4);
        myg.addEdge(4, 5);
        System.out.println("***printGraph");
        myg.printGraph();
        System.out.println("***allPaths");
        for(int i = 0; i < myg.numNodes; i++) {
            allPaths(myg, i);
        }
        try {
            Graph myg2 = new Graph("mediumG.txt");
            System.out.println("***Medium");
            myg2.BFS(0);
            myg2.endKey = 200;
            myg2.printPath(0, 200);
            myg2.endKey = 249;
            myg2.printPath(0, 249);
            System.out.format("Depth of 249 = %d\n", myg2.verts.get(249).depth);
            myg2.endKey = 120;
            myg2.printPath(0, 120);
            long startT, endT;
            Graph myg3 = new Graph("largeG.txt");
            System.out.println("***Large");
            startT = System.nanoTime();
            myg3.BFS(0);
            endT = System.nanoTime();
            System.out.format("**BFS on graph %s = %d\u03BCs\n", myg3.name, (endT-startT)/1000);
            myg3.endKey = 999082;
            myg3.printPath(0, 999082);
            System.out.format("Depth of 999082 = %d\n", myg3.verts.get(999082).depth);
            allPaths(myg2, 0);
        }catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public static void allPaths(Graph g, int key) {
        long startT, endT;
        startT = System.nanoTime();
        g.BFS(key);
        endT = System.nanoTime();
        System.out.format("BFS on graph %s = %d\u03BCs\n", g.name, (endT-startT)/1000);
        for(Map.Entry<Integer, GraphNode> entry : g.verts.entrySet()) {
            int end = entry.getValue().key;
            g.endKey = end;
            g.printPath(key, end);
        }
        System.out.println();
    }
}