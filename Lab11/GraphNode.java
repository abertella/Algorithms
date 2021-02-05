import java.util.*;

public class GraphNode {

    public static enum Color {
        WHITE, BLACK, GRAY;
    }

    public Color color;
    public int key;
    public GraphNode pi;
    public int depth;
    public List<GraphNode> neighbors;

    public GraphNode(int key){
        this.key = key;
        this.neighbors = new LinkedList<GraphNode>();
        this.initialize();
    }

    public void initialize() {
        this.color = Color.WHITE;
        this.pi = null;
        this.depth = Integer.MAX_VALUE;
    }
}