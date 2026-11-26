import java.util.Random;

public class Q7_CountInversions {
    static long mergeComparisons;
    static long countDivide(int[] a) {
        mergeComparisons=0;
        int[] temp=new int[a.length];
        return sortCount(a,temp,0,a.length-1);
    }
    static long sortCount(int[] a,int[] temp,int lo,int hi) {
        if(lo>=hi)return 0;
        int mid=lo+(hi-lo)/2;
        long count=sortCount(a,temp,lo,mid)+sortCount(a,temp,mid+1,hi);
        int i=lo,j=mid+1,k=lo;
        while(i<=mid&&j<=hi){
            mergeComparisons++;
            if(a[i]<=a[j])temp[k++]=a[i++];
            else {temp[k++]=a[j++];count+=mid-i+1;}
        }
        while(i<=mid)temp[k++]=a[i++];
        while(j<=hi)temp[k++]=a[j++];
        for(int x=lo;x<=hi;x++)a[x]=temp[x];
        return count;
    }
    static long brute(int[] a) {
        long count=0;
        for(int i=0;i<a.length;i++)for(int j=i+1;j<a.length;j++)if(a[i]>a[j])count++;
        return count;
    }
    public static void main(String[] args) {
        int[][] tests={{1,2,3,4,5},{5,4,3,2,1},{2,2,1},{2,4,1,3,5},{}};
        System.out.println("test,brute_count,divide_count,merge_comparisons,correct");
        for(int t=0;t<tests.length;t++){
            int[] a=tests[t].clone(),b=tests[t].clone();
            long expected=brute(a),actual=countDivide(b);
            System.out.println((t+1)+","+expected+","+actual+","+mergeComparisons+","+(expected==actual));
        }
        int[] sizes={100,1000,5000,10000,20000};Random r=new Random(42);
        System.out.println("n,brute_pair_checks,divide_merge_comparisons,brute_ms,divide_ms,correct");
        for(int n:sizes){
            int[] original=new int[n];for(int i=0;i<n;i++)original[i]=r.nextInt();
            long start=System.nanoTime();long b=brute(original);long bt=System.nanoTime()-start;
            int[] copy=original.clone();start=System.nanoTime();long d=countDivide(copy);long dt=System.nanoTime()-start;
            System.out.println(n+","+((long)n*(n-1)/2)+","+mergeComparisons+","+bt/1e6+","+dt/1e6+","+(b==d));
        }
    }
}
