package cs303.Lab01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ArraySearch {

    /* An implimentation of the Binary and Linear search algothirithms

    @author Andrew Bertella
    */

    private static int[] keyArray = new int[1000];
 
    public static void getKeys(String path) throws IOException {

        BufferedReader in = null;

        try { 
            in = new BufferedReader(new FileReader(path));
            if(in == null) {
                throw new IOException("File read Error");
            }
            String keyFile = in.readLine();
            String[] keyStrArray = keyFile.split(" ");
            for(int i = 0; i < keyArray.length; i++) {
                keyArray[i] = Integer.parseInt(keyStrArray[i]);
            }
        }
        finally {
            if(in != null){
                in.close();
            }
        }
    }
    
    public static int[] makeArray(int size) {

        List<Integer> myList = new ArrayList<>(size);
        int[] testArray = new int[size];

        for(int i=0; i < size; i++){
            myList.add(i);
        }
        Collections.shuffle(myList);
        for(int i = 0; i < size; i++) {
            testArray[i] = myList.get(i);
        }
        return testArray;
    }

    public static int linearSearch(int[] arr, int searchVal) {
        
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == searchVal) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int searchVal, int beginning, int end) {
        
        int pivot = beginning + (end-beginning)/2;

        if (arr[pivot] == searchVal){
            return pivot;
        }
        else if (pivot == 0 || pivot == arr.length - 1) {
            return -1;
        }
        else if (searchVal < arr[pivot]) {
            return binarySearch(arr, searchVal, beginning, pivot - 1);
        }
        else if (searchVal > arr[pivot]) {
            return binarySearch(arr, searchVal, pivot + 1, end);
        }
    return -1;
    }

    public static void timeit(int size) {
        
        int[] testArray = makeArray((int)Math.pow(2, size));
        long startTime = System.nanoTime();
        
        for(int i = 0; i < keyArray.length; i++) {
            linearSearch(testArray, keyArray[i]);
        }
        long endTime = System.nanoTime();
        System.out.format("Linear Search N = 2^%d: %d ms%n", size, (endTime-startTime)/1000);
        
        Arrays.sort(testArray);
        startTime = System.nanoTime();
        for(int i = 0; i < keyArray.length; i++) {
            binarySearch(testArray, keyArray[i], 0, testArray.length - 1);
            //Arrays.binarySearch(testArray, 0, testArray.length, keyArray[i]);
        }
        endTime = System.nanoTime();
        System.out.format("Binary Search N = 2^%d: %d ms%n", size, (endTime-startTime)/1000);
    }

    public static void main(String[] args) {
        try {
            getKeys("input_1000.txt");
        } 
        catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        for(int i = 3; i < 26; i++) {
            timeit(i);
            System.out.println();
        }
    }
}