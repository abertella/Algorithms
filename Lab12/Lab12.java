import java.io.IOException;
import java.util.*;

public class Lab12 {

    private static long startT, endT;
    private static final String[] filenames = {"top_DG2.txt", "tinyG.txt", "mediumG.txt"};
    private static Graph[] graphs = new Graph[3];
    public static void main(String[] args) {
        startT = System.nanoTime();
        Graph myg = new Graph(6, true);
        myg.addEdge(0, 3);
        myg.addEdge(0, 2);
        myg.addEdge(2, 5);
        myg.addEdge(5, 4);
        myg.addEdge(1, 4);
        myg.addEdge(1, 3);
        endT = System.nanoTime();
        System.out.format("Build on graph %s = %d\u03BCs\n", myg.name, (endT-startT)/1000);
        System.out.println("***printGraph");
        myg.printGraph();
        startT = System.nanoTime();
        myg.topologicalSort();
        endT = System.nanoTime();
        System.out.format("Topological sort on graph %s = %d\u03BCs\n", myg.name, (endT-startT)/1000);
        for(Map.Entry<Integer, GraphNode> entry : myg.verts.entrySet()) {
            int pi = -1;
            if(entry.getValue().pi != null) {
                pi = entry.getValue().pi.key;
            }
            System.out.format("Key = %d, pi = %s depth = %d, f = %d\n", entry.getValue().key, pi, entry.getValue().depth, entry.getValue().f);
        }
        allPaths(myg, 0);
        try{
            for(int i = 0; i < filenames.length; i++) {
                timeIt(i);
                if(i == 0) {
                    System.out.println("***printGraph");
                    graphs[0].BFS(0);
                    graphs[0].printGraph();
                }
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public static void allPaths(Graph g, int key) {
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

    public static void timeIt(int i) throws IOException {
        startT = System.nanoTime();
        graphs[i] = new Graph(filenames[i], true);
        endT = System.nanoTime();
        System.out.format("Build on graph %s = %d\u03BCs\n", graphs[i].name, (endT-startT)/1000);
        startT = System.nanoTime();
        graphs[i].topologicalSort();
        endT = System.nanoTime();
        System.out.format("Topological sort on graph %s = %d\u03BCs\n", graphs[i].name, (endT-startT)/1000);
    }
}