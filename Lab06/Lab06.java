import java.io.IOException;
import java.util.*;

public class Lab06 {

    public static final String[] arrayStrings = {"100", "1000", "5000", "10000", 
                    "50000", "100000", "500000"};

    public static List<SortArray> arrays = new ArrayList<>();
    public static List<Long> results = new ArrayList<>(35);
    public static void main(String[] args) {
        
        MaxMinSorter mMSorter = new MaxMinSorter();

        try{
            LogSorter ls = new LogSorter("NovelSortInput.txt");
            System.out.println("-----------------LogSorter Input-----------------");
            for(String str : ls.strings) {
                System.out.println(str);
            }
            ls.sort();
            System.out.println("-----------------LogSorter Sorted-----------------");
            for(String str : ls.strings) {
                System.out.println(str);
            }
            for(String name : arrayStrings) {
                arrays.add(new SortArray(name));
            }
            for(SortArray arr : arrays) {
                SortLib.timeIt(mMSorter, arr, results, true);
            }
            SortLib.writeOutput("lab06data.txt", arrayStrings, results, arrays.size());
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        System.exit(0);
        }
    }
}