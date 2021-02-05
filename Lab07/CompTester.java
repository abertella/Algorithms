import java.util.Comparator;

public class CompTester {
    private static int[] testArray1 = new int[]{1,2,3,4,5,6,7,8,9};
    private static int[] testArray2 = new int[]{5,9,4,7,6,7,8,3,-1};
    private static int[] testArray3 = new int[]{-1,2,3,4,5,6,7,8,9,-1};
    private static int[] testArray4 = new int[]{9,8,7,6,5,4,3,2,1,0};
    private static int[] testArray5 = new int[]{-3,1,-3,1,-3,1,-3,1,-3,1};
    private static int[] testArray6 = new int[]{};
    static Sorter bSorter = new BubbleSorter();
    static Comparator dComp = new Decending();
    static Comparator aComp = new Ascending();

    public static void testIt(int[] arr) {
        System.out.format("Input Array = %s\n", SortLib.stringArray(arr, 0, arr.length));
        bSorter.sort(arr, dComp);
        System.out.format("Decending Array = %s\n", SortLib.stringArray(arr, 0, arr.length));
        bSorter.sort(arr, aComp);
        System.out.format("Ascending Array = %s\n", SortLib.stringArray(arr, 0, arr.length));
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.printf("--------------%s----------------\n", bSorter.getName());
        testIt(testArray1);
        testIt(testArray2);
        testIt(testArray3);
        testIt(testArray4);
        testIt(testArray5);
        testIt(testArray6);
    }
}