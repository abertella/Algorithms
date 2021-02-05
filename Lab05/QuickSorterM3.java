public class QuickSorterM3 extends QuickSorter {

    private static String name = "Quick Sort M3";
    
    private static void quickSort(int[] arr, int beginning, int end) {
        if(beginning < end) {
            int N = end - beginning + 1;
            int m = median3(arr, beginning, beginning + N/2, end);
            SortLib.swap(arr, m, end);
            int pivot = QuickSorter.partition(arr, beginning, end);
            quickSort(arr, beginning, pivot - 1);
            quickSort(arr, pivot + 1, end);
        }
    }

    private static int median3(int[] arr, int i, int j, int k) {
        int p = arr[i];
        int q = arr[j];
        int r = arr[k];
        int[] marr = {p, q, r};
        InsertionSorter.insertionSort(marr, 0, marr.length);
        if(marr[1] == p) {
            return i;
        }
        else if(marr[1] == q) {
            return j;
        }
        else {
            return k;
        }
    }
    
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public String getName() {
        return name;
    }
}