public class Q1_BinarySearch {
    static class Result {
        int index, comparisons;
        Result(int index, int comparisons) { this.index = index; this.comparisons = comparisons; }
    }

    static Result search(int[] a, int target) {
        int low = 0, high = a.length - 1, comparisons = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (a[mid] == target) return new Result(mid, comparisons);
            comparisons++;
            if (target < a[mid]) high = mid - 1;
            else low = mid + 1;
        }
        return new Result(-1, comparisons);
    }

    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 10000, 100000};
        System.out.println("n,target,result,comparisons");
        for (int n : sizes) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = i * 2;
            int[] targets = {0, a[n / 2], a[n - 1], -1};
            for (int target : targets) {
                Result r = search(a, target);
                System.out.println(n + "," + target + "," + r.index + "," + r.comparisons);
            }
        }
    }
}
