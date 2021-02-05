import java.util.Comparator;

public class MergeSorter extends Sorter {
    
    private static int[] temp = new int[Lab07.maxArraySize];

    private static String name = "Merge Sort";

    private static void mergeSort(int[] arr, int[] temp, int beginning, int end, Comparator comp) {
        if(beginning < end) {
            int mid = (beginning + end)/2;
            mergeSort(arr, temp, beginning, mid, comp);
            mergeSort(arr, temp, mid + 1, end, comp);
            merge(arr, temp, beginning, mid, end, comp);
        }
    }

    private static void merge(int[] arr, int[] temp, int beginning, int mid, int end, Comparator comp) {
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
            else if (comp.compare(temp[j], temp[i]) < 0) {
                arr[k] = temp[j];
                j++;
            }
            else {
                arr[k] = temp[i];
                i++;
            }
        }
    }

    public void sort(int[] arr, Comparator comp) {
        mergeSort(arr, temp, 0, arr.length - 1, comp);
    }

    public String getName() {
        return name;
    }
}