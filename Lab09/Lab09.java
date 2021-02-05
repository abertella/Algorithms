import java.util.*;
import java.io.*;

public class Lab09 {

    public static HashMap hMap1, hMap2, hMap3;
    public static String[] strings1;
    public static long[] keys = new long[100];

    public static String[] getArray(String path) throws IOException {
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
        return lines.toArray(new String[lines.size()]);
    }

    public static void makeMap(HashMap map, String[] strings, String type){
        for(String str : strings) {
            long key = Long.parseLong(str.split(",")[0]);
            if(type == "d") {
                map.put(key, str);
            }
            else if(type == "l") {
                map.linearPut(key, str);
            }
            else if(type == "q"){
                map.quadraticPut(key, str);
            }
        }
    }

    public static void makeKeys(String[] strings) {
        int index;
        for(int i = 0; i < keys.length; i++) {
            index = (int)(Math.random()*strings.length);
            keys[i] = Long.parseLong(strings[index].split(",")[0]);
        }
    }

    public static void userMap() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap usrMap = new HashMap(10);
        HashMap usrMap1 = new HashMap(10);
        HashMap usrMap2 = new HashMap(10);
        String[] usrStr = new String[10];
        int i = 0;
        while(i != 10) {
            System.out.println("Enter a key value pair");
            usrStr[i] = br.readLine();
            i++;
        }
        makeMap(usrMap, usrStr, "l");
        makeMap(usrMap1, usrStr, "d");
        makeMap(usrMap2, usrStr, "q");
        System.out.println("-------------Linear-------------");
        usrMap.printMap();
        System.out.println("-------------Default-------------");
        usrMap1.printMap();
        System.out.println("-------------Quadratic-------------");
        usrMap2.printMap();
    }
    public static void main(String[] args) {
        hMap1 = new HashMap(300000);
        hMap2 = new HashMap(300000);
        hMap3 = new HashMap(300000);
        long dTime = 0, lTime = 0, qTime = 0;
        int mkl = 0, mkd = 0, mkq = 0;
        try{
            userMap();
            strings1 = getArray("UPC.csv");
            makeMap(hMap1, strings1, "d");
            makeMap(hMap2,strings1, "l");
            makeMap(hMap3, strings1, "q");
            makeKeys(strings1);
            for(Long key : keys) {
                String data;
                long startTime = System.nanoTime();
                data = hMap1.get(key);
                long endTime = System.nanoTime();
                if(data == "Key not found") {
                    mkd++;
                }
                System.out.format("Search on hMap1 %d : %d \u03BCs\t", key, (endTime-startTime)/1000);
                dTime = dTime + (endTime-startTime);
                System.out.format("%d : %s\n", key, data);
            }
            for(Long key : keys) {
                String data;
                long startTime = System.nanoTime();
                data = hMap2.linearGet(key);
                long endTime = System.nanoTime();
                if(data == "Key not found") {
                    mkl++;
                }
                System.out.format("Search on hMap2 %d : %d \u03BCs\t", key, (endTime-startTime)/1000);
                lTime = lTime + (endTime-startTime);
                System.out.format("%d : %s\n", key, data);
            }
            for(Long key : keys) {
                String data;
                long startTime = System.nanoTime();
                data = hMap3.quadraticGet(key);
                long endTime = System.nanoTime();
                if(data == "Key not found") {
                    mkq++;
                }
                System.out.format("Search on hMap3 %d : %d \u03BCs\t", key, (endTime-startTime)/1000);
                qTime = qTime + (endTime-startTime);
                System.out.format("%d : %s\n", key, data);
            }
            System.out.format("Average time for default probe: %d ns\t\tMissed keys for default: %d\n", dTime/100, mkd);
            System.out.format("Average time for linear probe: %d ns\t\tMissed keys for linear: %d\n", lTime/100, mkl);
            System.out.format("Average time for quadratic probe: %d ns\t\tMissed keys for quadratic: %d\n", qTime/100, mkq);

        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}