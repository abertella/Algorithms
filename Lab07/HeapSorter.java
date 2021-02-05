import java.util.Comparator;

public class HeapSorter extends Sorter {

    private static String name = "Heap Sort";

    public static void heapSort(int[] arr, Comparator comp) {

        /**An implimentation of the HeapSort Algorithm based on the given pseudocode
         * @param arr : int[] - the array ro be sorted in place
         */
        
        int heapSize = arr.length - 1;
        buildMaxHeap(arr, comp);
        for(int i = arr.length - 1; i > 0; i--) {
            SortLib.swap(arr, 0, i);
            heapSize--;
            maxHeapify(arr, heapSize, 0, comp);
        }
    }

    private static void buildMaxHeap(int[] arr, Comparator comp) {

        /**A method to build the initial  Max Heap
         * @param arr : int[] - the array to be made into a max heap
        */

        for(int i = arr.length/2; i >= 0; i--){
            maxHeapify(arr, arr.length - 1, i, comp);
        }
    }

    private static void maxHeapify(int[] arr, int size, int node, Comparator comp){

        /**A method to impliment the heap structure and order property starting at node
         * @param arr : int[] the array in which node is found
         *        size : int - the size of the heap (the last index of the heap)
         *        node : int - the index of the node that is to be heapified
        */


        int l = 2*node;
        int r = 2*node+1;
        int largest;
        if(l <= size && comp.compare(arr[l], arr[node]) > 0) {
            largest = l;
        }
        else {
            largest = node;
        }
        if(r <= size && comp.compare(arr[r], arr[largest]) > 0) {
            largest = r;
        }
        if(largest != node) {
            SortLib.swap(arr, node, largest);
            maxHeapify(arr, size, largest, comp);
        }
    }
    
    public void sort(int[] arr, Comparator comp) {
        heapSort(arr, comp);
    }

    public String getName() {
        return name;
    }
}