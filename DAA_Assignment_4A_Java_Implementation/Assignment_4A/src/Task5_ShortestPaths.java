import java.util.*;
public class Task5_ShortestPaths {
    static final int INF=1000000000;
    static void dijkstra(int n,int[][] e,int src) {
        int[] d=new int[n],p=new int[n]; boolean[] used=new boolean[n]; Arrays.fill(d,INF);Arrays.fill(p,-1);d[src]=0;
        for(int k=0;k<n;k++){int u=-1;for(int i=0;i<n;i++)if(!used[i]&&(u<0||d[i]<d[u]))u=i;
            if(u<0||d[u]==INF)break;used[u]=true;
            for(int[] x:e)if(x[0]==u&&d[u]+x[2]<d[x[1]]){d[x[1]]=d[u]+x[2];p[x[1]]=u;}
        }
        System.out.println("Dijkstra distances: "+Arrays.toString(d));
        System.out.println("Predecessors: "+Arrays.toString(p));
    }
    static void bellmanFord(int n,int[][] e,int src) {
        int[] d=new int[n];Arrays.fill(d,INF);d[src]=0;
        for(int i=1;i<n;i++){boolean changed=false;for(int[] x:e)if(d[x[0]]!=INF&&d[x[0]]+x[2]<d[x[1]]){d[x[1]]=d[x[0]]+x[2];changed=true;}if(!changed)break;}
        for(int[] x:e)if(d[x[0]]!=INF&&d[x[0]]+x[2]<d[x[1]]){System.out.println("Reachable negative cycle detected");return;}
        System.out.println("Bellman-Ford distances: "+Arrays.toString(d));
    }
    static void floyd(int n,int[][] e) {
        int[][] d=new int[n][n];for(int i=0;i<n;i++){Arrays.fill(d[i],INF);d[i][i]=0;}
        for(int[] x:e)d[x[0]][x[1]]=Math.min(d[x[0]][x[1]],x[2]);
        for(int k=0;k<n;k++)for(int i=0;i<n;i++)for(int j=0;j<n;j++)if(d[i][k]<INF&&d[k][j]<INF)d[i][j]=Math.min(d[i][j],d[i][k]+d[k][j]);
        System.out.println("Floyd-Warshall matrix:");for(int[] row:d)System.out.println(Arrays.toString(row));
    }
    public static void main(String[] args) {
        int[][] e={{0,1,4},{0,2,7},{1,2,2},{1,3,6},{2,3,1},{3,4,3},{2,4,8}};
        dijkstra(5,e,0);bellmanFord(5,e,0);floyd(5,e);
        int[][] negative={{0,1,4},{1,2,-6},{2,1,1}};bellmanFord(3,negative,0);
    }
}
