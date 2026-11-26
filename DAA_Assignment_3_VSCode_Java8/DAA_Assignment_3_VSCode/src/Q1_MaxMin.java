import java.util.Random;

public class Q1_MaxMin {
    static class Result {
        int min, max, comparisons;
        Result(int min, int max, int comparisons) {
            this.min = min; this.max = max; this.comparisons = comparisons;
        }
    }

    static Result find(int[] a, int lo, int hi) {
        if (lo == hi) return new Result(a[lo], a[lo], 0);
        if (hi == lo + 1) {
            if (a[lo] < a[hi]) return new Result(a[lo], a[hi], 1);
            return new Result(a[hi], a[lo], 1);
        }
        int mid = lo + (hi - lo) / 2;
        Result left = find(a, lo, mid);
        Result right = find(a, mid + 1, hi);
        int minComparisons = 1, maxComparisons = 1;
        int min = left.min < right.min ? left.min : right.min;
        int max = left.max > right.max ? left.max : right.max;
        return new Result(min, max,
                left.comparisons + right.comparisons + minComparisons + maxComparisons);
    }

    static int[] independent(int[] a) {
        int min = a[0], max = a[0], comparisons = 0;
        for (int i = 1; i < a.length; i++) {
            comparisons++;
            if (a[i] < min) min = a[i];
            comparisons++;
            if (a[i] > max) max = a[i];
        }
        return new int[]{min, max, comparisons};
    }

    public static void main(String[] args) {
        int[] sizes = {2, 4, 8, 16, 32, 100, 1000, 5000};
        Random random = new Random(42);
        System.out.println("n,Tournament comparisons,Two-scan comparisons,Min,Max");
        for (int n : sizes) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = random.nextInt(200001) - 100000;
            Result t = find(a, 0, n - 1);
            int[] s = independent(a);
            if (t.min != s[0] || t.max != s[1]) throw new RuntimeException("Mismatch");
            System.out.println(n + "," + t.comparisons + "," + s[2] + "," + t.min + "," + t.max);
        }
    }
}
