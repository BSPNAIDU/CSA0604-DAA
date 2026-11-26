import java.util.*;
public class Task4_AssemblyLine {
    public static void main(String[] args){int[][] a={{7,9,3,4,8,4},{8,5,6,4,5,7}};int[][] t={{0,2,3,1,3,4},{0,2,1,2,2,1}};
        int[] entry={2,4},exit={3,2};int n=a[0].length;int[][] dp=new int[2][n],prev=new int[2][n];
        dp[0][0]=entry[0]+a[0][0];dp[1][0]=entry[1]+a[1][0];
        for(int j=1;j<n;j++){int stay0=dp[0][j-1]+a[0][j],transfer0=dp[1][j-1]+t[1][j]+a[0][j];
            int stay1=dp[1][j-1]+a[1][j],transfer1=dp[0][j-1]+t[0][j]+a[1][j];
            dp[0][j]=Math.min(stay0,transfer0);prev[0][j]=stay0<=transfer0?0:1;
            dp[1][j]=Math.min(stay1,transfer1);prev[1][j]=stay1<=transfer1?1:0;
            System.out.println("Station "+(j+1)+": L1 stay="+stay0+" transfer="+transfer0+"; L2 stay="+stay1+" transfer="+transfer1);}
        int line=dp[0][n-1]+exit[0]<=dp[1][n-1]+exit[1]?0:1;int best=dp[line][n-1]+exit[line];int[] route=new int[n];
        for(int j=n-1;j>=0;j--){route[j]=line; if(j>0)line=prev[line][j];}
        System.out.println("Minimum time="+best);for(int j=0;j<n;j++)System.out.println("Station "+(j+1)+" -> Line "+(route[j]+1));}
}
