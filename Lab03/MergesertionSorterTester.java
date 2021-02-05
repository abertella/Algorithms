import java.util.StringJoiner;

public class MergesertionSorterTester {
    private static int[] testArray1 = new int[]{1,2,3,4,5,6,7,8,9};
    private static int[] testArray2 = new int[]{5,9,4,7,6,7,8,3,-1};
    private static int[] testArray3 = new int[]{-1,2,3,4,5,6,7,8,9,-1};
    private static int[] testArray4 = new int[]{9,8,7,6,5,4,3,2,1,0};
    private static int[] testArray5 = new int[]{-3,1,-3,1,-3,1,-3,1,-3,1};
    private static int[] testArray6 = new int[]{};

    private static int[] temp = new int[10];
    
    public static String stringArray(int[] arr) {
    StringJoiner sj = new StringJoiner(",", "[", "]");
    for(int i = 0; i < arr.length; i++) {
        sj.add(String.valueOf(arr[i]));
        }
        return sj.toString();
    }

    public static void testItM(int[] arr) {
        System.out.format("Input Array = %s\n", stringArray(arr));
        MergesertionSorter.hybridSort(arr, temp, 0, arr.length - 1, 3);
        System.out.format("Output Array = %s\n\n", stringArray(arr));
    }

    public static void main(String[] args) {
        System.out.println("--------------Hybrid Sort----------------");
        testItM(testArray1);
        testItM(testArray2);
        testItM(testArray3);
        testItM(testArray4);
        testItM(testArray5);
        testItM(testArray6);
    }
}