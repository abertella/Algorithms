import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HeapSorter {

    /* An implimentation of the Heap Sort Algorithm

    @author Andrew Bertella
    */

    private static int[][] arrays = {new int[100], new int[1000], new int[5000], 
        new int[10000], new int[50000], new int[100000], 
        new int[500000]};

    private static final String[] arrayStrings = {"100", "1000", "5000", "10000", 
                    "50000", "100000", "500000"};

    private static List<Long> results = new ArrayList<>(28);

    public static void getArray(String path, int[] arr) {

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

    private static void swap(int[] arr, int i, int j) {

        /**A method to swap two elements of an array
         * @param arr : int[] - the array in which the elements are to be swapped
         *        i : int - the index of the first element to be swapped
         *        j : int - the index of the second element to be swapped
         */

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapSort(int[] arr) {

        /**An implimentation of the HeapSort Algorithm based on the given pseudocode
         * @param arr : int[] - the array ro be sorted in place
         */
        
        int heapSize = arr.length - 1;
        buildMaxHeap(arr);
        for(int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapSize--;
            maxHeapify(arr, heapSize, 0);
        }
    }

    private static void buildMaxHeap(int[] arr) {

        /**A method to build the initial  Max Heap
         * @param arr : int[] - the array to be made into a max heap
        */

        for(int i = arr.length/2; i >= 0; i--){
            maxHeapify(arr, arr.length - 1, i);
        }
    }

    private static void maxHeapify(int[] arr, int size, int node){

        /**A method to impliment the heap structure and order property starting at node
         * @param arr : int[] the array in which node is found
         *        size : int - the size of the heap (the last index of the heap)
         *        node : int - the index of the node that is to be heapified
        */


        int l = 2*node;
        int r = 2*node+1;
        int largest;
        if(l <= size && arr[l] > arr[node]) {
            largest = l;
        }
        else {
            largest = node;
        }
        if(r <= size && arr[r] > arr[largest]) {
            largest = r;
        }
        if(largest != node) {
            swap(arr, node, largest);
            maxHeapify(arr, size, largest);
        }
    }

    public static void timeIt(String name, int[] arr, boolean save) {

        /**A method to time the execution of insertion sort on various sized arrays
         * and print the results to the consol. This method also verifies that the 
         * array is properly sorted and reports to the consol,
         * @param name : String - the name of the array being sorted
         *        arr : int[] - the aray being sorted
        */

        long startTime = System.nanoTime();
        heapSort(arr);
        long endTime = System.nanoTime();
        System.out.format("Heap Sort on array %s: %d \u03BCs\n", name, (endTime-startTime)/1000);
        if(save){
            results.add((endTime-startTime)/1000);
        }
        
        if(isSorted(arr)) {
            System.out.format("Array %s is sorted.\n", name);
        }
        else {
            System.out.format("Array %s is NOT sorted: Something went wrong.\n");
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

        //Dirver Method

        try {
            for(int i = 0; i < arrayStrings.length; i++) {
                getArray(String.format("input_%s.txt", arrayStrings[i]), arrays[i]);
            }
            System.out.println("-----------------HeapSort----------------------\n");
            System.out.println("-----------------Trash----------------------");
            for(int i = 0; i < arrayStrings.length; i++) {
                timeIt(arrayStrings[i], Arrays.copyOf(arrays[i], arrays[i].length), false);
            }
            System.out.println("-----------------Input----------------------");
            for(int i = 0; i < arrayStrings.length; i++) {
                timeIt(arrayStrings[i], arrays[i], true);
            }
            System.out.println("-----------------Sorted----------------------");
            for(int i = 0; i < arrayStrings.length; i++) {
                timeIt(arrayStrings[i], arrays[i], true);
            }
            System.out.println("-----------------Reversed----------------------");
            for(int i = 0; i < arrayStrings.length; i++) {
                timeIt(arrayStrings[i], arrayReverse(arrays[i]), true);
            }
            writeOutput("output.txt", 7);
            for(int i = 0; i < MergesertionSorter.arrayStrings.length; i++) {
                getArray(String.format("input_%s.txt", MergesertionSorter.arrayStrings[i]), MergesertionSorter.arrays[i]);
            }
            System.out.println("-----------------MergeSort----------------------");
            for(int i = 0; i < MergesertionSorter.arrayStrings.length; i++) {
                MergesertionSorter.timeIt(MergesertionSorter.arrayStrings[i], MergesertionSorter.arrays[i], "m");
            }
            System.out.println("-----------------InsertionSort----------------------");
            for(int i = 0; i < MergesertionSorter.arrayStrings.length; i++) {
                MergesertionSorter.timeIt(MergesertionSorter.arrayStrings[i], MergesertionSorter.arrays[i], "i");
            }
            MergesertionSorter.writeOutput("output1.txt", 5);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}