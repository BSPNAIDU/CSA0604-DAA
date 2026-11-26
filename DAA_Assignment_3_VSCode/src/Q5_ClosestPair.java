import java.util.*;

public class Q5_ClosestPair {
    static class Point {
        double x,y;
        Point(double x,double y){this.x=x;this.y=y;}
    }
    static class Result {
        double distance;
        Point p1,p2;
        long calculations;
        Result(double d,Point a,Point b,long c){distance=d;p1=a;p2=b;calculations=c;}
    }
    static double dist(Point a,Point b){return Math.hypot(a.x-b.x,a.y-b.y);}
    static Result brute(Point[] p) {
        double best=Double.POSITIVE_INFINITY; Point x=null,y=null; long count=0;
        for(int i=0;i<p.length;i++) for(int j=i+1;j<p.length;j++){
            count++; double d=dist(p[i],p[j]);
            if(d<best){best=d;x=p[i];y=p[j];}
        }
        return new Result(best,x,y,count);
    }
    static Result divide(Point[] points) {
        Point[] px=points.clone(), py=points.clone();
        Arrays.sort(px, new Comparator<Point>(){public int compare(Point a,Point b){return Double.compare(a.x,b.x);}});
        Arrays.sort(py, new Comparator<Point>(){public int compare(Point a,Point b){return Double.compare(a.y,b.y);}});
        return solve(px,py);
    }
    static Result solve(Point[] px,Point[] py) {
        int n=px.length;
        if(n<=3)return brute(px);
        int mid=n/2; double midX=px[mid].x;
        Point[] lx=Arrays.copyOfRange(px,0,mid), rx=Arrays.copyOfRange(px,mid,n);
        Set<Point> leftSet=Collections.newSetFromMap(new IdentityHashMap<Point,Boolean>());
        for(Point p:lx)leftSet.add(p);
        List<Point> lyList=new ArrayList<Point>(),ryList=new ArrayList<Point>();
        for(Point p:py){if(leftSet.contains(p))lyList.add(p);else ryList.add(p);}
        Result l=solve(lx,lyList.toArray(new Point[0]));
        Result r=solve(rx,ryList.toArray(new Point[0]));
        Result best=l.distance<=r.distance?l:r;
        double delta=best.distance;
        List<Point> strip=new ArrayList<Point>();
        for(Point p:py)if(Math.abs(p.x-midX)<delta)strip.add(p);
        long count=l.calculations+r.calculations;
        Point bp1=best.p1,bp2=best.p2;
        for(int i=0;i<strip.size();i++){
            for(int j=i+1;j<strip.size() && j<=i+7;j++){
                if(strip.get(j).y-strip.get(i).y>=delta)break;
                count++;
                double d=dist(strip.get(i),strip.get(j));
                if(d<delta){delta=d;bp1=strip.get(i);bp2=strip.get(j);}
            }
        }
        return new Result(delta,bp1,bp2,count);
    }
    public static void main(String[] args) {
        int[] sizes={100,1000,5000,10000};
        Random r=new Random(42);
        System.out.println("n,brute_distance_calculations,divide_conquer_calculations,brute_ms,dc_ms,answers_match");
        for(int n:sizes){
            Point[] p=new Point[n];
            for(int i=0;i<n;i++)p[i]=new Point(r.nextDouble()*100000,r.nextDouble()*100000);
            long s=System.nanoTime();Result b=brute(p);long bt=System.nanoTime()-s;
            s=System.nanoTime();Result d=divide(p);long dt=System.nanoTime()-s;
            boolean match=Math.abs(b.distance-d.distance)<1e-9;
            System.out.println(n+","+b.calculations+","+d.calculations+","+bt/1e6+","+dt/1e6+","+match);
        }
    }
}
