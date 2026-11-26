import java.util.*;
public class Task2_KnapsackComparison {
    static class Item {String id;int w,v;Item(String i,int w,int v){id=i;this.w=w;this.v=v;}}
    static void fractional(Item[] a,int cap){Arrays.sort(a,(x,y)->Double.compare((double)y.v/y.w,(double)x.v/x.w));double val=0;int ops=0;System.out.println("Fractional greedy:");
        for(Item x:a){if(cap==0)break;double f=Math.min(1.0,(double)cap/x.w);val+=f*x.v;cap-=f*x.w;ops++;System.out.printf(Locale.US,"%s fraction %.2f%n",x.id,f);}
        System.out.printf(Locale.US,"Value %.2f; item visits %d%n",val,ops);}
    static void zeroOne(Item[] a,int cap){int n=a.length;int[][] dp=new int[n+1][cap+1];for(int i=1;i<=n;i++)for(int c=0;c<=cap;c++){dp[i][c]=dp[i-1][c];if(a[i-1].w<=c)dp[i][c]=Math.max(dp[i][c],a[i-1].v+dp[i-1][c-a[i-1].w]);}
        System.out.println("0/1 DP optimum="+dp[n][cap]+" table states="+((n+1)*(cap+1)));int c=cap;for(int i=n;i>0;i--)if(dp[i][c]!=dp[i-1][c]){System.out.println("Select "+a[i-1].id);c-=a[i-1].w;}}
    public static void main(String[] args){Item[] items={new Item("A",10,60),new Item("B",20,100),new Item("C",30,120)};System.out.println("Normal instance");fractional(items.clone(),50);zeroOne(items,50);
        Item[] counter={new Item("A",10,60),new Item("B",20,100),new Item("C",30,120)};System.out.println("\\n0/1 greedy counterexample at capacity 50: ratio greedy picks A+B=160, optimum B+C=220");fractional(counter,50);zeroOne(counter,50);}
}
