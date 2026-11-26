import java.util.*;
public class Task6_TSPHeldKarp {
    public static void main(String[] args){int[][] c={{0,10,15,20,18,12,14},{10,0,35,25,17,20,22},{15,35,0,30,28,16,24},{20,25,30,0,11,19,13},{18,17,28,11,0,9,15},{12,20,16,19,9,0,8},{14,22,24,13,15,8,0}};
        int n=c.length,states=1<<n,INF=1000000000;int[][] dp=new int[states][n],parent=new int[states][n];for(int[] r:dp)Arrays.fill(r,INF);for(int[] r:parent)Arrays.fill(r,-1);dp[1][0]=0;
        for(int mask=1;mask<states;mask++)if((mask&1)!=0)for(int u=0;u<n;u++)if(dp[mask][u]<INF)for(int v=1;v<n;v++)if((mask&(1<<v))==0){int nm=mask|(1<<v),val=dp[mask][u]+c[u][v];if(val<dp[nm][v]){dp[nm][v]=val;parent[nm][v]=u;}}
        int full=states-1,best=INF,last=-1;for(int i=1;i<n;i++)if(dp[full][i]+c[i][0]<best){best=dp[full][i]+c[i][0];last=i;}
        List<Integer> route=new ArrayList<Integer>();int mask=full;while(last!=-1){route.add(last);int p=parent[mask][last];mask^=1<<last;last=p;}Collections.reverse(route);route.add(0);route.add(0);
        System.out.println("Minimum tour cost="+best);System.out.println("Route (0 is hub)="+route);System.out.println("DP table cells="+(states*n)+", reachable state/location pairs evaluated at most "+(states*n));}
}
