import java.util.*;
public class TaskA3_BacktrackingPatterns {
    static int n=4;static int[] q=new int[n];
    static boolean queen(int row){if(row==n){System.out.println("N-Queens="+Arrays.toString(q));return true;}for(int col=0;col<n;col++){boolean ok=true;for(int r=0;r<row;r++)if(q[r]==col||Math.abs(q[r]-col)==row-r)ok=false;if(ok){q[row]=col;if(queen(row+1))return true;q[row]=-1;}}return false;}
    static boolean colour(int[][] g,int[] c,int v,int m){if(v==g.length)return true;for(int x=1;x<=m;x++){boolean ok=true;for(int u=0;u<g.length;u++)if(g[v][u]!=0&&c[u]==x)ok=false;if(ok){c[v]=x;if(colour(g,c,v+1,m))return true;c[v]=0;}}return false;}
    public static void main(String[] args){Arrays.fill(q,-1);queen(0);int[][] g={{0,1,1},{1,0,1},{1,1,0}};int[] c=new int[3];System.out.println("Triangle 3-colourable="+colour(g,c,0,3)+" "+Arrays.toString(c));}
}
