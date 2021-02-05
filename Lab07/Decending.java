import java.util.Comparator;

public class Decending implements Comparator<Integer> {
    public int compare(Integer i1, Integer i2) {
        return (i2-i1);
    }
}