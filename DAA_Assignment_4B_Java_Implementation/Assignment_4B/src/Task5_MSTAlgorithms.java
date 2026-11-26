import java.util.*;
public class Task5_MSTAlgorithms {
    static class E{int u,v,w;E(int u,int v,int w){this.u=u;this.v=v;this.w=w;}public String toString(){return u+"-"+v+"("+w+")";}}
    static class DSU{int[] p;DSU(int n){p=new int[n];for(int i=0;i<n;i++)p[i]=i;}int find(int x){if(p[x]!=x)p[x]=find(p[x]);return p[x];}boolean union(int a,int b){a=find(a);b=find(b);if(a==b)return false;p[a]=b;return true;}}
    static void kruskal(int n,List<E> es){List<E> a=new ArrayList<E>(es);Collections.sort(a,(x,y)->x.w-y.w);DSU d=new DSU(n);int sum=0;System.out.println("Kruskal:");for(E e:a)if(d.union(e.u,e.v)){System.out.println(e);sum+=e.w;}System.out.println("Cost="+sum);}
    static void prim(int n,List<E> es){boolean[] in=new boolean[n];int sum=0;List<E> chosen=new ArrayList<E>();in[0]=true;while(chosen.size()<n-1){E best=null;for(E e:es)if(in[e.u]!=in[e.v]&&(best==null||e.w<best.w))best=e;if(best==null)break;chosen.add(best);in[best.u]=in[best.v]=true;sum+=best.w;System.out.println("Prim select "+best);}System.out.println("Cost="+sum);}
    static void boruvka(int n,List<E> es){DSU d=new DSU(n);int components=n,sum=0;while(components>1){E[] cheapest=new E[n];for(E e:es){int a=d.find(e.u),b=d.find(e.v);if(a!=b){if(cheapest[a]==null||e.w<cheapest[a].w)cheapest[a]=e;if(cheapest[b]==null||e.w<cheapest[b].w)cheapest[b]=e;}}
        boolean merged=false;for(E e:cheapest)if(e!=null&&d.union(e.u,e.v)){System.out.println("Boruvka select "+e);sum+=e.w;components--;merged=true;}if(!merged)break;}System.out.println("Cost="+sum);}
    public static void main(String[] args){List<E> e=Arrays.asList(new E(0,1,4),new E(0,2,3),new E(1,2,1),new E(1,3,2),new E(2,3,4),new E(3,4,2),new E(4,5,6),new E(3,5,3),new E(2,5,7));kruskal(6,e);prim(6,e);boruvka(6,e);}
}
