import java.util.Comparator;

public class QuickSorter extends Sorter {

    private static String name = "Quick Sort";
    
    private static void quickSort(int[] arr, int beginning, int end, Comparator comp) {
        if(beginning < end) {
            int pivot = partition(arr, beginning, end, comp);
            quickSort(arr, beginning, pivot - 1, comp);
            quickSort(arr, pivot + 1, end, comp);
        }
    }

    protected static int partition(int[] arr, int beginning, int end, Comparator comp) {
        int x = arr[end];
        int i = beginning - 1;
        int j = beginning;
        for(; j <= end - 1; j++) {
            if(comp.compare(arr[j], x) <= 0) {
                i = i + 1;
                SortLib.swap(arr, i, j);
            }
        }
        SortLib.swap(arr, i + 1, j);
        return i + 1;
    }
    
    public void sort(int[] arr, Comparator comp) {
        quickSort(arr, 0, arr.length - 1, comp);
    }

    public String getName() {
        return name;
    }
}