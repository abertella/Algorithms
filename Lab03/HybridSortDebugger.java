import java.util.StringJoiner;

public class HybridSortDebugger {

    public static String stringArray(int[] arr, int beginning, int end) {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        for(int i = beginning; i < end; i++) {
            sj.add(String.valueOf(arr[i]));
            }
            return sj.toString();
        }
        public static void hybridSort(int[] arr, int[] temp, int beginning, int end, int callInsert){
            System.out.format("Hybrid called on %s\n", stringArray(arr, beginning, end));
            if((end - beginning) <= callInsert){
                insertionSort(arr, beginning, end);
            }
            else {
                int mid = (beginning + end)/2;
                hybridSort(arr, temp, beginning, mid, callInsert);
                hybridSort(arr, temp, mid, end, callInsert);
                merge(arr, temp, beginning, mid, end);
            }
        }
        public static void insertionSort(int[] arr, int beginning, int end) {
            System.out.format("Insertion called on %s\n", stringArray(arr, beginning, end));
            for (int j = beginning + 1; j < end; j++) {
                int key = arr[j];
                int i = j-1;
                while (i >= 0 && arr[i] > key) {
                    arr[i + 1] = arr[i];
                    i--;
                }
            arr[i + 1] = key;
            }
            System.out.format("Sorted array =  %s\n", stringArray(arr, beginning, end));
            System.out.format("Array = %s\n", stringArray(arr, 0, arr.length - 1));
        }
        private static void merge(int[] arr, int[] temp, int beginning, int mid, int end) {
            System.out.format("Merge called on %s and %s\n", stringArray(arr, beginning, mid), stringArray(arr, mid, end));
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
            System.out.format("Final merge = %s\n", stringArray(arr, beginning, end));
            System.out.format("Array = %s\n", stringArray(arr, 0, arr.length - 1));
            System.out.format("Temp = %s\n", stringArray(temp, 0, temp.length - 1));
        }
    public static void main(String[] args) {
        int[] testArray = {9, 0, 1, 32, 12, -90, 88, 43, 21, 91, 102, 6, -123};
        System.out.println("==============Hybrid Debugger==============");
        hybridSort(testArray, new int[13], 0, testArray.length - 1, 3);
    }
}