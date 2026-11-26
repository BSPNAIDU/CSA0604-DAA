import java.util.*;
public class TaskA2_SubsetSumBacktracking {
    static long nodes,pruned;static List<Integer> chosen=new ArrayList<Integer>();
    static boolean search(int[] a,int i,int sum,int target){nodes++;if(sum==target){System.out.println("Subset="+chosen);return true;}
        if(i==a.length||sum>target){pruned++;return false;}
        chosen.add(a[i]);if(search(a,i+1,sum+a[i],target))return true;chosen.remove(chosen.size()-1);
        if(search(a,i+1,sum,target))return true;return false;}
    public static void main(String[] args){int[] a={3,4,5,8,10};int target=13;System.out.println("Found="+search(a,0,0,target));System.out.println("Nodes="+nodes+" pruned="+pruned);}
}
