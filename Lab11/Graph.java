import java.io.*;
import java.util.*;

public class Graph {

    public Queue<GraphNode> graphQueue;
    public HashMap<Integer, GraphNode> verts;
    public int numNodes;
    public int endKey;
    public String name;

    public Graph(String path) throws IOException {
        this.name = path;
        verts = new HashMap<>();
        List<String> lines = new ArrayList<>();
        BufferedReader in = null;
        try { 
            in = new BufferedReader(new FileReader(path));
            String line;
            while((line = in.readLine()) != null) {
                lines.add(line);
            }
            numNodes = Integer.parseInt(lines.get(0));
            for(int i = 0; i < numNodes; i++) {
                verts.put(i, new GraphNode(i));
            }
            lines.remove(0);
            lines.remove(0);
            for (String line2 : lines) {
                String[] edge = line2.split(" ");
                addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
            }
        }
        finally {
            if(in != null){
                in.close();
            }
        }
    }

    public Graph(int numNodes) {
        this.name = String.valueOf(numNodes);
        this.numNodes = numNodes;
        verts = new HashMap<>();
        for(int i = 0; i < numNodes; i++) {
            verts.put(i, new GraphNode(i));
        }
    }

    public void addEdge(int key1, int key2) {
        GraphNode gNode1 = verts.get(key1);
        GraphNode gNode2 = verts.get(key2);
        gNode1.neighbors.add(gNode2);
        gNode2.neighbors.add(gNode1);
    }

    public void printGraph() {
        for(Map.Entry<Integer, GraphNode> entry : verts.entrySet()) {
            StringJoiner sj = new StringJoiner(", ", "[", "]");
            for(GraphNode gn : entry.getValue().neighbors){
                sj.add(String.valueOf(gn.key));
            }
        System.out.format("%d : %s\n", entry.getKey(), sj.toString());
        }
    }

    public void BFS(int key){
        for(Map.Entry<Integer, GraphNode> entry : verts.entrySet()) {
            entry.getValue().initialize();
        }
        GraphNode s = verts.get(key);
        s.color = GraphNode.Color.GRAY;
        s.depth = 0;
        graphQueue = new LinkedList<>();
        graphQueue.add(s);
        while(!graphQueue.isEmpty()){
            GraphNode u = graphQueue.remove();
            for(GraphNode v : u.neighbors) {
                if(v.color == GraphNode.Color.WHITE) {
                    v.color = GraphNode.Color.GRAY;
                    v.depth = u.depth + 1;
                    v.pi = u;
                    graphQueue.add(v);
                }
            }
            u.color = GraphNode.Color.BLACK;
        }
    }

    public void printPath(int keyStart, int keyEnd) {
        GraphNode s = verts.get(keyStart);
        GraphNode v = verts.get(keyEnd);
        if(s.key == v.key && v.key != this.endKey) {
            System.out.format("%d -> ", keyStart);
        }
        else if(s.key == v.key && v.key == this.endKey) {
            System.out.format("%d\n ", keyStart);
        }
        else if(v.pi == null) {
            System.out.format("No path from %d to %d exists\n", keyStart, keyEnd);
        }
        else {
            printPath(keyStart, v.pi.key);
            if(v.key == endKey) {
                System.out.format("%d\n", v.key);
            }else {
                System.out.format("%d -> ", v.key);
            }
        }
    }
}