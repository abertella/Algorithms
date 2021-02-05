import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SortLib {

    public static void swap(int[] arr, int i, int j) {

        /**A method to swap two elements of an array
         * @param arr : int[] - the array in which the elements are to be swapped
         *        i : int - the index of the first element to be swapped
         *        j : int - the index of the second element to be swapped
         */

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean isSorted(int[] arr) {

        /** A method to check if the array is sorted
         * @param arr : int[] - the array to be checked
         * @return true every element in the array is >= the one before it, false otherwise
        */

        for(int i = 1; i < arr.length ; i++) {
            if(arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] arrayReverse(int[] arr) {

        /** A method to reverse an array
         * @param arr : int[] - the array to be reversed
         * @return reversedArr int[] an ineger array that is the reverse of arr
        */

        int[] reversedArr = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            reversedArr[arr.length - i - 1] = arr[i];
        }
        return reversedArr;
    }

    public static void writeOutput(String path, String[] arrStrings, List<Long> results, int dataSize) throws IOException {
       
        /**A method to write the times of each sort to an output file that is formatted to be 
         * read by a python script ot generate plots.
         * @param path : String - the path to the file to be written
         *        dataSize : int - the number of datapoints in each data set
         */

        BufferedWriter out = new BufferedWriter(new FileWriter(path));
        try {
            for(int i = 0; i < arrStrings.length; i++) {
                if((i + 1) % dataSize == 0) {
                    out.write(String.format("%s\n", arrStrings[i]));
                }
                else {
                    out.write(String.format("%s,", arrStrings[i]));
                }
            }
            for(int i = 0 ; i < results.size(); i++) {
                if((i + 1) % dataSize == 0) {
                    out.write(String.format("%d\n", results.get(i)));
                }
                else {
                    out.write(String.format("%d,", results.get(i)));
                }
            }
            out.flush();
        }
        finally {
            out.close();
        }
    }

    public static String stringArray(int[] arr, int beginning, int end) {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        for(int i = beginning; i < end; i++) {
            sj.add(String.valueOf(arr[i]));
            }
            return sj.toString();
        }

    public static void timeIt(Sorter sorter, SortArray sarr, List<Long> results, boolean save) {

        /**A method to time the execution of insertion sort on various sized arrays
         * and print the results to the consol. This method also verifies that the 
         * array is properly sorted and reports to the consol,
         * @param name : String - the name of the array being sorted
         *        arr : int[] - the aray being sorted
        */

        int[] arr = sarr.getArray();
        long startTime = System.nanoTime();
        sorter.sort(arr);
        long endTime = System.nanoTime();
        System.out.format("%s on array %s: %d \u03BCs\n", sorter.getName(), sarr.getName(), (endTime-startTime)/1000);
        if(save){
            results.add((endTime-startTime)/1000);
        }
    
        if(isSorted(arr)) {
            System.out.format("Array %s is sorted.\n", sarr.getName());
        }
        else {
            System.out.format("Array %s is NOT sorted: Something went wrong.\n", sarr.getName());
            System.out.println(stringArray(arr, 0, arr.length));
            System.exit(0);
        }
        
    }
}