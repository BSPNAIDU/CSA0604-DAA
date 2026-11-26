import java.util.*;
public class TaskB4_BinPacking {
    static class Bin{List<Integer> items=new ArrayList<Integer>();int remaining;Bin(int c){remaining=c;}}
    static List<Bin> firstFitDecreasing(int[] items,int cap){Integer[] a=new Integer[items.length];for(int i=0;i<items.length;i++)a[i]=items[i];Arrays.sort(a,Collections.reverseOrder());List<Bin> bins=new ArrayList<Bin>();
        for(int x:a){if(x<=0||x>cap)throw new IllegalArgumentException("Each demand must be between 1 and capacity");int i=0;while(i<bins.size()&&bins.get(i).remaining<x)i++;if(i==bins.size())bins.add(new Bin(cap));bins.get(i).items.add(x);bins.get(i).remaining-=x;}return bins;}
    public static void main(String[] args){int cap=10;int[] workloads={6,6,6,4,4,4,3,3,2,2};List<Bin> bins=firstFitDecreasing(workloads,cap);
        System.out.println("First-Fit Decreasing, capacity="+cap);for(int i=0;i<bins.size();i++)System.out.println("Server "+(i+1)+" workloads="+bins.get(i).items+" remaining="+bins.get(i).remaining);
        System.out.println("Servers used="+bins.size());}
}
