import java.util.*;

public class Lab13 {

    public static final String[] filenames = {"weighted.txt", "tinyDG.txt"};
    public static void main(String[] args) {
        try{
            for(String fn : filenames){
            Graph myG = new Graph(fn, false, true);
            myG.MSTPrim(0);
            for(Map.Entry<Integer, List<Pair>> entry : myG.edges.entrySet()) {
                System.out.format("Key = %d, ", entry.getKey());
                for(Pair lst : entry.getValue()) {
                    System.out.format("Pair(%d, %f) ", lst.getKey(), lst.getValue());
                }
                System.out.println();
            }
            System.out.format("Key1\tWeight\t\tKey2\n");
            for(Map.Entry<Integer, GraphNode> entry : myG.verts.entrySet()) {
                if(entry.getValue().pi != null) {
                System.out.format("%d\t%f\t%d\n", entry.getValue().pi.key, entry.getValue().depth, entry.getKey());
                }
            }
            for(Map.Entry<Integer, List<Pair>> entry : myG.edges.entrySet()) {
                System.out.format("Key = %d, edges = %d\n", entry.getKey(), entry.getValue().size());
            }
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
