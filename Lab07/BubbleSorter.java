import java.util.Comparator;

public class BubbleSorter extends Sorter {

    private static String name = "Bubble Sort";

    private static void bubbleSort(int[] arr, int beginning, int end, Comparator comp) {
        for(int i = 0; i <= end; i++) {
            for(int j = end; j >= i + 1; j--) {
                if(comp.compare(arr[j], arr[j-1]) < 0) {
                    SortLib.swap(arr, j, j-1);
                }
            }
        }
    }

    public void sort(int[] arr, Comparator comp) {
        bubbleSort(arr, 0, arr.length - 1, comp);
    }

    public String getName() {
        return name;
    }
}