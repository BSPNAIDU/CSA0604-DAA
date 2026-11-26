import java.util.*;

public class Q6_MeetInTheMiddle {
    static boolean subsetSum(int[] a,long target) {
        int mid=a.length/2;
        long[] left=sums(a,0,mid), right=sums(a,mid,a.length);
        Arrays.sort(right);
        for(long x:left) if(Arrays.binarySearch(right,target-x)>=0)return true;
        return false;
    }
    static long[] sums(int[] a,int from,int to) {
        int n=to-from;
        if(n>=31)throw new IllegalArgumentException("Half too large for Java array indexing");
        int count=1<<n;
        long[] result=new long[count];
        for(int mask=1;mask<count;mask++){
            int bit=Integer.numberOfTrailingZeros(mask);
            int previous=mask&(mask-1);
            result[mask]=result[previous]+a[from+bit];
        }
        return result;
    }
    public static void main(String[] args) {
        int[][] tests={{3,34,4,12,5,2},{1,2,3,4,5},{8,11,7,3}};
        long[] targets={9,20,19};
        System.out.println("test,target,found");
        for(int i=0;i<tests.length;i++)
            System.out.println((i+1)+","+targets[i]+","+subsetSum(tests[i],targets[i]));
        int[] sizes={20,30,36,40};
        Random r=new Random(42);
        System.out.println("n,target,found,time_ms");
        for(int n:sizes){
            int[] a=new int[n];for(int i=0;i<n;i++)a[i]=r.nextInt(100);
            long target=Arrays.stream(a).asLongStream().sum()/2;
            long s=System.nanoTime();boolean found=subsetSum(a,target);long t=System.nanoTime()-s;
            System.out.println(n+","+target+","+found+","+t/1e6);
        }
    }
}
