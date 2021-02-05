import java.io.*;
import java.util.*;

public class Graph {

    public Queue<GraphNode> graphQueue;
    public List<GraphNode> MSTqueue;
    public HashMap<Integer, GraphNode> verts;
    public HashMap<Integer, List<Pair>> edges;
    public int numNodes;
    public int endKey;
    public String name;
    private int time;
    private List<GraphNode> dFSList;
    private boolean directed;
    private boolean weighted;

    public Graph(String path, boolean dir, boolean weight) throws IOException {
        this.directed = dir;
        this.weighted = weight;
        this.graphQueue = new LinkedList<>();
        this.dFSList = new ArrayList<>();
        this.name = path;
        this.edges = new HashMap<>();
        this.verts = new HashMap<>();
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
            if(!this.weighted) {
                for (String line2 : lines) {
                    String[] edge = line2.split(" ");
                    addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
                }
            }
            else {
                for (String line2 : lines) {
                    String[] edge = line2.split(" ");
                    addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]), Float.parseFloat(edge[2]));
                }   
            }   
        }
        finally {
            if(in != null){
                in.close();
            }
        }
    }

    public Graph(int numNodes, boolean dir) {
        this.directed = dir;
        this.graphQueue = new LinkedList<>();
        this.dFSList = new ArrayList<>();
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
        if(!this.directed) {
            gNode2.neighbors.add(gNode1);
        }
    }

    public void addEdge(int key1, int key2, float weight) {
        GraphNode gNode1 = verts.get(key1);
        GraphNode gNode2 = verts.get(key2);
        gNode1.neighbors.add(gNode2);
        if(!this.directed) {
            gNode2.neighbors.add(gNode1);
        }
        List wList = edges.getOrDefault(key1, new ArrayList<Pair>());
        wList.add(new Pair(key2, weight));
        edges.put(key1, wList);
        if(!this.directed) {
            wList = edges.getOrDefault(key2, new ArrayList<Pair>());
            wList.add(new Pair(key1, weight));
            edges.put(key2, wList);
        }
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

    public void DFS() {
        this.time = 0;
        for(Map.Entry<Integer, GraphNode> entry : verts.entrySet()) {
            entry.getValue().initialize();
        }
        for(Map.Entry<Integer, GraphNode> entry : verts.entrySet()) {
            if (entry.getValue().color == GraphNode.Color.WHITE){
                DFSVisit(entry.getValue());
            }
        }
    }

    private void DFSVisit(GraphNode u) {
        this.time++;
        u.depth = time;
        u.color = GraphNode.Color.GRAY;
        for(GraphNode v : u.neighbors) {
            if(v.color == GraphNode.Color.WHITE) {
                v.pi = u;
                DFSVisit(v);
            }
        }
        u.color = GraphNode.Color.BLACK;
        this.time++;
        u.f = this.time;
        dFSList.add(u);
    }

    public void topologicalSort() {
        this.DFS();
        ListIterator<GraphNode> it = dFSList.listIterator(dFSList.size());
        while(it.hasPrevious()) {
            GraphNode gn = it.previous();
            if(gn == dFSList.get(0)){
                System.out.format("%d\n", gn.key);
            }
            else {
                System.out.format("%d -> ", gn.key);
            }
        }
    }

    public void MSTPrim(int key) {
        for(Map.Entry<Integer, GraphNode> entry : verts.entrySet()) {
            entry.getValue().initialize();
        }
        verts.get(key).depth = 0;
        this.MSTqueue = new ArrayList(verts.values());
        while(!MSTqueue.isEmpty()) {
            GraphNode u = extractMin(MSTqueue);
            System.out.format("Min Node = %d, depth = %f\n", u.key, u.depth);
            for(GraphNode v : u.neighbors) {
                float weight = w(u, v);
                if(MSTqueue.contains(v) && weight < v.depth) {
                    v.pi = u;
                    v.depth = weight;
                }
            }
        }
    }

    private float w(GraphNode u, GraphNode v) {
        int key1 = u.key;
        int key2 = v.key;
        List<Pair> weights = edges.get(key1);
        for(Pair weight : weights) {
            if(weight.getKey() == key2) {
                System.out.format("w(%d, %d) = %f\n", key1, key2, weight.getValue());
                return weight.getValue();
            }
        }
        return Integer.MAX_VALUE;
    }

    private GraphNode extractMin(List<GraphNode> queue) {
        GraphNode min = queue.get(0);
        for(GraphNode gn : queue) {
            if(gn.depth < min.depth) {
                min = gn;
            }
        }
        queue.remove(min);
        return min;
    }
}