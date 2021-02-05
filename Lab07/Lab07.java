import java.util.*;

public class Lab07 {

    public static final int maxArraySize = 1000000;
    
    public static List<SortArray> arrays = new ArrayList<>();
    
    public static List<Long> results = new ArrayList<>(70);
    
    public static final String[] arrayStrings = {"100", "1000", "5000", "10000", 
                        "50000", "100000", "Random", "ReversedSorted",
                        "Sorted"};
        
    public static List<Sorter> sorters = new ArrayList<>(5);

    static Comparator dComp = new Decending();
    static Comparator aComp = new Ascending();
    
    public static void main(String[] args) {
        sorters.add(new InsertionSorter());
        sorters.add(new MergeSorter());
        sorters.add(new HeapSorter()); 
        sorters.add(new QuickSorter());
        sorters.add(new BubbleSorter());
        sorters.add(new SelectionSorter());
        
        try {
            for(String name : arrayStrings) {
                arrays.add(new SortArray(name));
            }
            System.out.println();
            for(Sorter sorter : sorters) {
                System.out.format("-----------------------------%s-----------------------------\n", sorter.getName());
                for(SortArray arr : arrays) {
                    SortLib.timeIt(sorter, aComp, arr, results, true);
                }
            }
            SortLib.writeOutput("lab07data.txt", arrayStrings, results, arrays.size());
        }
        catch (Exception e) {
                System.err.println(e.getMessage());
                System.exit(0);
        }
    }
}
