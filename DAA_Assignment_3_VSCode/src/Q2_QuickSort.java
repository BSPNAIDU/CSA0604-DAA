import java.util.Random;

public class Q2_QuickSort {
    static long comparisons;
    static int maxDepth;
    static void sort(int[] a) {
        comparisons = 0; maxDepth = 0;
        quickSort(a, 0, a.length - 1, 1);
    }
    static void quickSort(int[] a, int lo, int hi, int depth) {
        if (lo >= hi) return;
        if (depth > maxDepth) maxDepth = depth;
        int p = partition(a, lo, hi);
        quickSort(a, lo, p - 1, depth + 1);
        quickSort(a, p + 1, hi, depth + 1);
    }
    // Last element is the pivot.
    static int partition(int[] a, int lo, int hi) {
        int pivot = a[hi], i = lo - 1;
        for (int j = lo; j < hi; j++) {
            comparisons++;
            if (a[j] <= pivot) {
                i++;
                int t = a[i]; a[i] = a[j]; a[j] = t;
            }
        }
        int t = a[i + 1]; a[i + 1] = a[hi]; a[hi] = t;
        return i + 1;
    }
    static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i - 1] > a[i]) return false;
        return true;
    }
    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 2000};
        Random r = new Random(42);
        System.out.println("case,n,comparisons,max_recursion_depth,sorted");
        for (int n : sizes) {
            int[] random = new int[n];
            for (int i = 0; i < n; i++) random[i] = r.nextInt();
            sort(random);
            System.out.println("random," + n + "," + comparisons + "," + maxDepth + "," + isSorted(random));

            int[] ordered = new int[n];
            for (int i = 0; i < n; i++) ordered[i] = i;
            sort(ordered);
            System.out.println("sorted," + n + "," + comparisons + "," + maxDepth + "," + isSorted(ordered));
        }
    }
}
