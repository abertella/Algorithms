public class MaxMinSorter extends Sorter {

    private static final String name = "MinMaxSorter";
    
    private static int max(int[] arr, int beginning, int end) {
        int i = beginning;
        int max = arr[i];
        int maxIndex = i;
        for(; i <= end; i++) {
            if(arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
        }
    return maxIndex;
    }

    private static int min(int[] arr, int beginning, int end) {
        int i = beginning;
        int min = arr[i];
        int minIndex = i;
        for(; i <= end; i++) {
            if(arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
    return minIndex;
    }

    private static void minMaxSort(int[] arr, int beginning, int end) {
        int i = beginning;
        int k = end;
        while(i < k) {
            SortLib.swap(arr, k, max(arr, i, k));
            SortLib.swap(arr, i, min(arr, i, k));
            i++;
            k--;
        }
    }

    public void sort(int[] arr) {
        minMaxSort(arr, 0, arr.length - 1);
    }

    public String getName() {
        return name;
    }
}