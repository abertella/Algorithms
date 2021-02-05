import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MergesertionSorter {
     
    /** An implimentation of the Merge Sort Algothirithm
     * @author Andrew Bertella
    */

    public static int[][] arrays = {new int[100], new int[1000], new int[5000], 
                                    new int[10000], new int[50000], new int[100000], 
                                    new int[500000]};

    public static final String[] arrayStrings = {"100", "1000", "5000", "10000", 
                                                "50000", "100000", "500000"};

    private static List<Long> results = new ArrayList<>(70);

    public static void getArray(String path, int[] arr) throws IOException {

        /**A method to get the interges in the input files and put them in arrays
         * @param path : String - the file path
         *        arr : int[] - the array to be loaded 
         */
        
        BufferedReader in = null;

        try { 
            in = new BufferedReader(new FileReader(path));
            String arrayFile = in.readLine().trim();
            String[] arrayStrArray = arrayFile.split(" ");
            for(int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(arrayStrArray[i]);
            }
        }
        finally {
            if(in != null){
                in.close();
            }
        }
    }

    public static void mergeSort(int[] arr, int[] temp, int beginning, int end) {
        if(beginning < end) {
            int mid = (beginning + end)/2;
            mergeSort(arr, temp, beginning, mid);
            mergeSort(arr, temp, mid + 1, end);
            merge(arr, temp, beginning, mid, end);
        }
    }

    private static void merge(int[] arr, int[] temp, int beginning, int mid, int end) {
        int i = beginning;
        int j = mid + 1;
        for(int k = beginning; k <= end; k++) {
            temp[k] = arr[k];
        }
        for(int k = beginning; k <= end; k++) {
            if(i > mid) {
                arr[k] = temp[j];
                j++;
            }
            else if (j > end) {
                arr[k] = temp[i];
                i++;
            }
            else if (temp[j] < temp[i]) {
                arr[k] = temp[j];
                j++;
            }
            else {
                arr[k] = temp[i];
                i++;
            }
        }
    }

    public static void hybridSort(int[] arr, int[] temp, int beginning, int end, int callInsert){
        if((end - beginning) <= callInsert){
            insertionSort(arr, beginning, end);
        }
        else {
            int mid = (beginning + end)/2;
            hybridSort(arr, temp, beginning, mid, callInsert);
            hybridSort(arr, temp, mid, end, callInsert);
            merge(arr, temp, beginning, mid, end);
        }
    }

    public static void insertionSort(int[] arr, int beginning, int end) {

        /** A implimentation of Insertion Sort based on the given pseudocode the array will 
         * be sorted in place.
         * @param arr : int[] - the array to be sorted 
        */

        for (int j = beginning + 1; j < end; j++) {
            int key = arr[j];
            int i = j-1;
            while (i >= 0 && arr[i] > key) {
                arr[i + 1] = arr[i];
                i--;
            }
        arr[i + 1] = key;
        }
    }

    public static void timeIt(String name, int[] arr, String sort, int callInsert) {

        /**A method to time the execution of insertion sort on various sized arrays
         * and print the results to the console. This method also verifies that the 
         * array is properly sorted and reports to the consol,
         * @param name : String - the name of the array being sorted
         *        arr : int[] - the aray being sorted
        */

        int[] copyArr = Arrays.copyOf(arr, arr.length);
        if(sort == "m") {
            int[] temp = new int[arr.length];
            long startTime = System.nanoTime();
            mergeSort(copyArr, temp , 0, arr.length - 1);
            long endTime = System.nanoTime();
            System.out.format("Merge Sort on array %s: %d \u03BCs\n", name, (endTime-startTime)/1000);
            results.add((endTime-startTime)/1000);
        }
        else if(sort == "i") {
            long startTime = System.nanoTime();
            insertionSort(copyArr, 0, copyArr.length);
            long endTime = System.nanoTime();
            System.out.format("Insertion Sort on array %s: %d \u03BCs\n", name, (endTime-startTime)/1000);
            results.add((endTime-startTime)/1000);
        }
        else if(sort == "h") {
            int[] temp = new int[arr.length];
            long startTime = System.nanoTime();
            hybridSort(copyArr, temp, 0, copyArr.length - 1, callInsert);
            long endTime = System.nanoTime();
            System.out.format("Hybrid Sort on array %s with call insert %d: %d \u03BCs\n", name, callInsert, (endTime-startTime)/1000);
            results.add((endTime-startTime)/1000);
        }
        else {
            System.out.println("%s is not a valid search.");
            System.exit(0);
        }
    }

    public static void writeOutput(String path, int dataSize) throws IOException {
       
        /**A method to write the times of each sort to an output file that is formatted to be 
         * read by a python script ot generate plots.
         * @param path : String - the path to the file to be written
         *        dataSize : int - the number of datapoints in each data set
         */

        BufferedWriter out = new BufferedWriter(new FileWriter(path));
        try {
            out.write("100,1000,5000,10000,50000,100000,500000\n");
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
    public static void main(String[] args) {
        
        //Driver method

        try {
            for(int i = 0; i < arrayStrings.length; i++) {
                getArray(String.format("input_%s.txt", arrayStrings[i]), arrays[i]);
            }
            System.out.println("-----------------MergeSort----------------------");
            for(int i = 0; i < arrayStrings.length; i++) {
                timeIt(arrayStrings[i], arrays[i], "m", 0);
            }
            System.out.println("-----------------InsertionSort----------------------");
            for(int i = 0; i < arrayStrings.length; i++) {
                timeIt(arrayStrings[i], arrays[i], "i", 0);
            }
            System.out.println("-----------------HybridSort----------------------");
            for(int i = 0; i < arrayStrings.length; i++) {
                timeIt(arrayStrings[i], arrays[i], "h", 25);
            }
            System.out.println();
            for(int j = 50; j <= 300; j = j + 50) {
                for(int i = 0; i < arrayStrings.length; i++) {
                    timeIt(arrayStrings[i], arrays[i], "h", j);
                }
            System.out.println();
            }
            writeOutput("output.txt", 7);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}