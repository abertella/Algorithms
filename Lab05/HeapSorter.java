public class HeapSorter extends Sorter {

    private static String name = "Heap Sort";

    public static void heapSort(int[] arr) {

        /**An implimentation of the HeapSort Algorithm based on the given pseudocode
         * @param arr : int[] - the array ro be sorted in place
         */
        
        int heapSize = arr.length - 1;
        buildMaxHeap(arr);
        for(int i = arr.length - 1; i > 0; i--) {
            SortLib.swap(arr, 0, i);
            heapSize--;
            maxHeapify(arr, heapSize, 0);
        }
    }

    private static void buildMaxHeap(int[] arr) {

        /**A method to build the initial  Max Heap
         * @param arr : int[] - the array to be made into a max heap
        */

        for(int i = arr.length/2; i >= 0; i--){
            maxHeapify(arr, arr.length - 1, i);
        }
    }

    private static void maxHeapify(int[] arr, int size, int node){

        /**A method to impliment the heap structure and order property starting at node
         * @param arr : int[] the array in which node is found
         *        size : int - the size of the heap (the last index of the heap)
         *        node : int - the index of the node that is to be heapified
        */


        int l = 2*node;
        int r = 2*node+1;
        int largest;
        if(l <= size && arr[l] > arr[node]) {
            largest = l;
        }
        else {
            largest = node;
        }
        if(r <= size && arr[r] > arr[largest]) {
            largest = r;
        }
        if(largest != node) {
            SortLib.swap(arr, node, largest);
            maxHeapify(arr, size, largest);
        }
    }
    
    public void sort(int[] arr) {
        heapSort(arr);
    }

    public String getName() {
        return name;
    }
}