import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class LogSorter {

    private String[] keys;
    public String[] strings;
    private Map<String, List<String>> hMap;

    public LogSorter(String path) throws IOException {
        hMap = new HashMap<String, List<String>>();
        List<String> lines = new ArrayList<String>();
        BufferedReader in = null;
        try { 
            in = new BufferedReader(new FileReader(path));
            String line;
            while((line = in.readLine()) != null) {
                lines.add(line);
            }
        }
        finally {
            if(in != null){
                in.close();
            }
        }
        strings = lines.toArray(new String[lines.size()]);
        makeMap(strings);
        keys = hMap.keySet().toArray(new String[0]);
    }

    private void makeMap(String[] strings){
        for(String str : strings) {
            String key = str.split(" ")[0];
            List values = hMap.getOrDefault(key, new ArrayList<String>());
            values.add(str);
            hMap.put(key, values);
        }
    }

    public void sort() {
        insertionSort(keys, 0, keys.length);
        List values = hMap.get(keys[0]);
        for(int i = 1; i < keys.length; i++) {
            values.addAll(hMap.get(keys[i]));
        }
        values.toArray(strings);
    }

    public static void insertionSort(String[] arr, int beginning, int end) {
        for (int j = beginning + 1; j < end; j++) {
            String key = arr[j];
            int i = j-1;
            while (i >= 0 && arr[i].compareTo(key) > 0) {
                arr[i + 1] = arr[i];
                i--;
            }
        arr[i + 1] = key;
        }
    }
}