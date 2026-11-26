import java.util.*;
public class TaskB1_GraphColouring {
    static class Stats{long nodes,attempts,rejected,backtracks;int maxDepth;}
    static boolean solve(int[][] g,int m,int[] colour,int v,Stats s,boolean heuristic){
        s.nodes++;s.maxDepth=Math.max(s.maxDepth,v);if(v==g.length)return true;
        int vertex=v;
        if(heuristic){int best=-1,degree=-1;for(int x=0;x<g.length;x++)if(colour[x]==0){int d=0;for(int y=0;y<g.length;y++)if(g[x][y]!=0)d++;if(d>degree){degree=d;best=x;}}vertex=best;}
        for(int c=1;c<=m;c++){s.attempts++;boolean ok=true;for(int u=0;u<g.length;u++)if(g[vertex][u]!=0&&colour[u]==c){ok=false;break;}
            if(!ok){s.rejected++;continue;}colour[vertex]=c;
            if(solve(g,m,colour,v+1,s,heuristic))return true;
            colour[vertex]=0;s.backtracks++;
        }return false;
    }
    static void run(String name,int[][] g,int m,boolean h){int[] c=new int[g.length];Stats s=new Stats();boolean ok=solve(g,m,c,0,s,h);System.out.println("\\n"+name+" result="+ok+" colours="+Arrays.toString(c));
        System.out.println("nodes="+s.nodes+" attempts="+s.attempts+" rejected="+s.rejected+" backtracks="+s.backtracks+" maxDepth="+s.maxDepth);}
    public static void main(String[] args){int[][] easy={{0,1,0,0,1},{1,0,1,0,0},{0,1,0,1,0},{0,0,1,0,1},{1,0,0,1,0}};
        int[][] hard={{0,1,1,0,0,1},{1,0,1,1,0,0},{1,1,0,1,1,0},{0,1,1,0,1,1},{0,0,1,1,0,1},{1,0,0,1,1,0}};
        run("Input order / easy",easy,3,false);run("Degree heuristic / easy",easy,3,true);
        run("Input order / adversarial K4 with 3 colours",new int[][]{{0,1,1,1},{1,0,1,1},{1,1,0,1},{1,1,1,0}},3,false);
        run("Degree heuristic / adversarial K4",new int[][]{{0,1,1,1},{1,0,1,1},{1,1,0,1},{1,1,1,0}},3,true);
        run("Input order / sample graph",hard,3,false);run("Degree heuristic / sample graph",hard,3,true);
    }
}
