import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class SortArray {

    private String name;
    private int[] arr;

    SortArray(String name) throws IOException {
        
        this.name = name;

        String path = String.format("input_%s.txt", name);
        BufferedReader in = null;

        try { 
            in = new BufferedReader(new FileReader(path));
            String arrayFile = in.readLine().trim();
            String[] arrayStrArray = arrayFile.split(" ");
            arr = new int[arrayStrArray.length];
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

    public int[] getArray() {
        return Arrays.copyOf(arr, arr.length);
    }

    public String getName() {
        return name;
    }
}