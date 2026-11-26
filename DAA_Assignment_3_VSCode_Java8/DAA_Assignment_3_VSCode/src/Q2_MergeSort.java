import java.util.Random;

public class Q2_MergeSort {
    static long comparisons;
    static void sort(int[] a) {
        comparisons = 0;
        int[] temp = new int[a.length];
        mergeSort(a, temp, 0, a.length - 1);
    }
    static void mergeSort(int[] a, int[] temp, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, temp, lo, mid);
        mergeSort(a, temp, mid + 1, hi);
        merge(a, temp, lo, mid, hi);
    }
    static void merge(int[] a, int[] temp, int lo, int mid, int hi) {
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            comparisons++;
            if (a[i] <= a[j]) temp[k++] = a[i++];
            else temp[k++] = a[j++];
        }
        while (i <= mid) temp[k++] = a[i++];
        while (j <= hi) temp[k++] = a[j++];
        for (int x = lo; x <= hi; x++) a[x] = temp[x];
    }
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 5000, 10000, 20000};
        Random r = new Random(42);
        System.out.println("n,comparisons,time_ms,sorted");
        for (int n : sizes) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = r.nextInt();
            long start = System.nanoTime();
            sort(a);
            long elapsed = System.nanoTime() - start;
            boolean sorted = true;
            for (int i = 1; i < n; i++) if (a[i - 1] > a[i]) sorted = false;
            System.out.println(n + "," + comparisons + "," + (elapsed / 1_000_000.0) + "," + sorted);
        }
    }
}
