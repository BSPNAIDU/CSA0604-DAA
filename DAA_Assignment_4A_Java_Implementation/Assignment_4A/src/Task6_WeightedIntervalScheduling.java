import java.util.*;
public class Task6_WeightedIntervalScheduling {
    static class Job {String id;int start,end,value;Job(String i,int s,int e,int v){id=i;start=s;end=e;value=v;}}
    public static void main(String[] args) {
        Job[] a={new Job("J1",1,3,20),new Job("J2",2,5,50),new Job("J3",4,6,30),new Job("J4",6,8,40),new Job("J5",5,9,60),new Job("J6",8,10,30),new Job("J7",9,11,50),new Job("J8",11,13,40)};
        Arrays.sort(a,(x,y)->Integer.compare(x.end,y.end)); int n=a.length;
        int[] prev=new int[n],dp=new int[n+1];
        for(int i=0;i<n;i++){prev[i]=-1;for(int j=i-1;j>=0;j--)if(a[j].end<=a[i].start){prev[i]=j;break;}}
        for(int i=1;i<=n;i++)dp[i]=Math.max(dp[i-1],a[i-1].value+dp[prev[i-1]+1]);
        System.out.println("Maximum energy saving = "+dp[n]);
        List<String> chosen=new ArrayList<String>();int i=n;
        while(i>0){if(a[i-1].value+dp[prev[i-1]+1]>dp[i-1]){chosen.add(a[i-1].id);i=prev[i-1]+1;}else i--;}
        Collections.reverse(chosen);System.out.println("Selected jobs = "+chosen);
    }
}
