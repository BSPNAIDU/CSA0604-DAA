import java.util.*;

public class Q6_MedianOfMedians {
    static long comparisons;
    static int select(int[] a,int k) { // k is 1-based
        if(k<1 || k>a.length) throw new IllegalArgumentException("k out of range");
        return selectRange(a,0,a.length-1,k);
    }
    static int selectRange(int[] a,int lo,int hi,int k) {
        int n=hi-lo+1;
        if(n<=5){insertionSort(a,lo,hi);return a[lo+k-1];}
        int groups=(n+4)/5;
        int[] medians=new int[groups];
        for(int g=0;g<groups;g++){
            int start=lo+g*5,end=Math.min(start+4,hi);
            insertionSort(a,start,end);
            medians[g]=a[start+(end-start)/2];
        }
        int pivot=select(medians,(groups+1)/2);
        int lt=lo,i=lo,gt=hi;
        while(i<=gt){
            comparisons++;
            if(a[i]<pivot){swap(a,lt++,i++);}
            else {comparisons++;if(a[i]>pivot)swap(a,i,gt--);else i++;}
        }
        int left=lt-lo,equal=gt-lt+1;
        if(k<=left)return selectRange(a,lo,lt-1,k);
        if(k<=left+equal)return pivot;
        return selectRange(a,gt+1,hi,k-left-equal);
    }
    static void insertionSort(int[] a,int lo,int hi){
        for(int i=lo+1;i<=hi;i++){int key=a[i],j=i-1;while(j>=lo){comparisons++;if(a[j]>key){a[j+1]=a[j];j--;}else break;}a[j+1]=key;}
    }
    static void swap(int[]a,int i,int j){int t=a[i];a[i]=a[j];a[j]=t;}
    public static void main(String[] args){
        int[] sizes={100,1000,10000,50000};Random r=new Random(42);
        System.out.println("n,k,result,comparisons,correct");
        for(int n:sizes){int[] a=new int[n];for(int i=0;i<n;i++)a[i]=r.nextInt(1000000);
            int[] copy=a.clone();Arrays.sort(copy);comparisons=0;int k=(n+1)/2;int v=select(a,k);
            System.out.println(n+","+k+","+v+","+comparisons+","+(v==copy[k-1]));
        }
    }
}
