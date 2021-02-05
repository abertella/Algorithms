import java.util.Comparator;

public class SelectionSorter extends Sorter {

    private static String name = "Selection Sort";

    private static void selectionSort(int[] arr, int beginning, int end, Comparator comp) {
        for(int i = 0; i <= end; i++) {
            int key = i;
            for(int j = i + 1; j <= end; j++) {
                if(comp.compare(arr[j], arr[key]) < 0) {
                    key = j;
                }
            }
            if(i != key){
                SortLib.swap(arr, i, key);
            }
        }
    }

    public void sort(int[] arr, Comparator comp) {
        selectionSort(arr, 0, arr.length - 1, comp);
    }

    public String getName() {
        return name;
    }
}