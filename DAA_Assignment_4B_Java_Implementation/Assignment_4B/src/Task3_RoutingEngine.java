import java.util.*;
public class Task3_RoutingEngine {
    static final int INF=1000000000;
    static class Edge{int u,v,w;Edge(int u,int v,int w){this.u=u;this.v=v;this.w=w;}}
    static void dijkstra(int n,List<Edge> es,int s){int[] d=new int[n],p=new int[n];boolean[] done=new boolean[n];Arrays.fill(d,INF);Arrays.fill(p,-1);d[s]=0;
        for(int z=0;z<n;z++){int u=-1;for(int i=0;i<n;i++)if(!done[i]&&(u<0||d[i]<d[u]))u=i;if(u<0||d[u]==INF)break;done[u]=true;System.out.println("Finalise "+u+" distance "+d[u]);
            for(Edge e:es)if(e.u==u&&d[u]+e.w<d[e.v]){d[e.v]=d[u]+e.w;p[e.v]=u;}}
        System.out.println("Dijkstra distances "+Arrays.toString(d));System.out.println("Predecessors "+Arrays.toString(p));}
    static void bellman(int n,List<Edge> es,int s){int[] d=new int[n],p=new int[n];Arrays.fill(d,INF);Arrays.fill(p,-1);d[s]=0;
        for(int i=1;i<n;i++){boolean change=false;for(Edge e:es)if(d[e.u]!=INF&&d[e.u]+e.w<d[e.v]){d[e.v]=d[e.u]+e.w;p[e.v]=e.u;change=true;}if(!change)break;}
        for(Edge e:es)if(d[e.u]!=INF&&d[e.u]+e.w<d[e.v]){System.out.println("Reachable negative cycle detected.");return;}
        System.out.println("Bellman-Ford distances "+Arrays.toString(d));}
    static void floydWarshall(int n,List<Edge> es){int[][] d=new int[n][n];for(int i=0;i<n;i++){Arrays.fill(d[i],INF);d[i][i]=0;}for(Edge e:es)d[e.u][e.v]=Math.min(d[e.u][e.v],e.w);
        for(int k=0;k<n;k++)for(int i=0;i<n;i++)for(int j=0;j<n;j++)if(d[i][k]<INF&&d[k][j]<INF)d[i][j]=Math.min(d[i][j],d[i][k]+d[k][j]);
        System.out.println("Floyd-Warshall distances:");for(int[] r:d)System.out.println(Arrays.toString(r));}
    static void warshall(int n,List<Edge> es){boolean[][] a=new boolean[n][n];for(int i=0;i<n;i++)a[i][i]=true;for(Edge e:es)a[e.u][e.v]=true;
        for(int k=0;k<n;k++)for(int i=0;i<n;i++)for(int j=0;j<n;j++)a[i][j]=a[i][j]||(a[i][k]&&a[k][j]);
        System.out.println("Warshall reachability:");for(boolean[] r:a)System.out.println(Arrays.toString(r));}
    public static void main(String[] args){int n=8;List<Edge> e=new ArrayList<Edge>();int[][] raw={{0,1,4},{0,2,7},{1,2,2},{1,3,6},{2,3,1},{3,4,3},{2,4,8},{4,5,2},{5,6,3},{6,7,1},{1,5,10},{0,7,30}};
        for(int[] x:raw)e.add(new Edge(x[0],x[1],x[2]));System.out.println("Stage A:");dijkstra(n,e,0);
        List<Edge> neg=new ArrayList<Edge>(e);neg.add(new Edge(5,3,-8));System.out.println("Stage B: negative edge");bellman(n,neg,0);
        List<Edge> cycle=new ArrayList<Edge>(neg);cycle.add(new Edge(3,5,1));System.out.println("Stage C: negative cycle");bellman(n,cycle,0);
        System.out.println("Stage D:");floydWarshall(n,e);warshall(n,e);}
}
