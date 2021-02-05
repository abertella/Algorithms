import java.util.*;

public class Lab05 {

    public static final int maxArraySize = 500000;

    public static List<SortArray> arrays = new ArrayList<>();
    public static List<SortArray> arraysQ = new ArrayList<>();

    public static List<Long> results = new ArrayList<>(35);
    public static List<Long> resultsQ = new ArrayList<>(26);

    public static final String[] arrayStrings = {"100", "1000", "5000", "10000", 
                    "50000", "100000", "500000"};

    public static final String[] arrayStringsQ ={"16", "32", "64", "128", "256", 
                    "512", "1024", "2048", "4096", "8192", "Random", "ReversedSorted",
                    "Sorted"};
    
    public static List<Sorter> sorters = new ArrayList<>(5);

    public static void main(String[] args) {
        sorters.add(new InsertionSorter());
        sorters.add(new MergeSorter());
        sorters.add(new HeapSorter()); 
        sorters.add(new QuickSorter());
        sorters.add(new QuickSorterM3());

        try {
            for(String name : arrayStrings) {
                arrays.add(new SortArray(name));
            }
            for(String name : arrayStringsQ) {
                arraysQ.add(new SortArray(name));
            }
            System.out.format("-----------------------------%s-----------------------------\n", sorters.get(3).getName());
            for(SortArray arr : arraysQ) {
                SortLib.timeIt(sorters.get(3), arr, resultsQ, true);
            }
            System.out.format("-----------------------------%s-----------------------------\n", sorters.get(4).getName());
            for(SortArray arr : arraysQ) {
                SortLib.timeIt(sorters.get(4), arr, resultsQ, true);
            }
            System.out.println();
            for(Sorter sorter : sorters) {
                System.out.format("-----------------------------%s-----------------------------\n", sorter.getName());
                for(SortArray arr : arrays) {
                    SortLib.timeIt(sorter, arr, results, true);
                }
            }
            SortLib.writeOutput("quicksortdata.txt", arrayStringsQ, resultsQ, arraysQ.size());
            SortLib.writeOutput("lab05data.txt", arrayStrings, results, arrays.size());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}