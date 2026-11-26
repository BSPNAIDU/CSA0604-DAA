public class Task3_Knapsack01 {
    public static void main(String[] args) {
        String[] id={"P","Q","R","S"}; int[] w={2,3,4,5},v={12,20,25,30}; int cap=7,n=w.length;
        int[][] dp=new int[n+1][cap+1];
        for(int i=1;i<=n;i++) for(int c=0;c<=cap;c++) {
            dp[i][c]=dp[i-1][c];
            if(w[i-1]<=c) dp[i][c]=Math.max(dp[i][c],v[i-1]+dp[i-1][c-w[i-1]]);
        }
        System.out.println("Maximum value = "+dp[n][cap]);
        int c=cap; for(int i=n;i>0;i--) if(dp[i][c]!=dp[i-1][c]) {
            System.out.println("Selected item "+id[i-1]+" weight="+w[i-1]+" value="+v[i-1]); c-=w[i-1];
        }
        System.out.println("DP table:");
        for(int i=0;i<=n;i++){for(int j=0;j<=cap;j++)System.out.print(dp[i][j]+"\t");System.out.println();}
    }
}
