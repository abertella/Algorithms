public class MergeSorter extends Sorter {
    
    private static int[] temp = new int[SorterTester.maxArraySize];

    private static String name = "Merge Sort";

    private static void mergeSort(int[] arr, int[] temp, int beginning, int end) {
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

    public void sort(int[] arr) {
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    public String getName() {
        return name;
    }
}