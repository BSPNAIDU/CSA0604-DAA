import java.util.Random;

public class Q4_Strassen {
    static long conventionalMultiplies, strassenMultiplies;
    static long[][] conventional(long[][] a, long[][] b) {
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++)
            for (int k = 0; k < n; k++)
                for (int j = 0; j < n; j++) {
                    conventionalMultiplies++;
                    c[i][j] += a[i][k] * b[k][j];
                }
        return c;
    }
    static long[][] add(long[][] a, long[][] b) {
        int n=a.length; long[][] c=new long[n][n];
        for(int i=0;i<n;i++) for(int j=0;j<n;j++) c[i][j]=a[i][j]+b[i][j];
        return c;
    }
    static long[][] sub(long[][] a, long[][] b) {
        int n=a.length; long[][] c=new long[n][n];
        for(int i=0;i<n;i++) for(int j=0;j<n;j++) c[i][j]=a[i][j]-b[i][j];
        return c;
    }
    static long[][] strassen(long[][] a, long[][] b) {
        int n=a.length;
        if(n<=2) return conventionalCounted(a,b);
        int m=n/2;
        long[][] a11=part(a,0,0,m), a12=part(a,0,m,m), a21=part(a,m,0,m), a22=part(a,m,m,m);
        long[][] b11=part(b,0,0,m), b12=part(b,0,m,m), b21=part(b,m,0,m), b22=part(b,m,m,m);
        long[][] m1=strassen(add(a11,a22),add(b11,b22));
        long[][] m2=strassen(add(a21,a22),b11);
        long[][] m3=strassen(a11,sub(b12,b22));
        long[][] m4=strassen(a22,sub(b21,b11));
        long[][] m5=strassen(add(a11,a12),b22);
        long[][] m6=strassen(sub(a21,a11),add(b11,b12));
        long[][] m7=strassen(sub(a12,a22),add(b21,b22));
        long[][] c11=add(sub(add(m1,m4),m5),m7);
        long[][] c12=add(m3,m5);
        long[][] c21=add(m2,m4);
        long[][] c22=add(sub(add(m1,m3),m2),m6);
        long[][] c=new long[n][n];
        put(c,c11,0,0); put(c,c12,0,m); put(c,c21,m,0); put(c,c22,m,m);
        return c;
    }
    static long[][] conventionalCounted(long[][] a,long[][] b) {
        int n=a.length; long[][] c=new long[n][n];
        for(int i=0;i<n;i++) for(int k=0;k<n;k++) for(int j=0;j<n;j++) {
            strassenMultiplies++; c[i][j]+=a[i][k]*b[k][j];
        }
        return c;
    }
    static long[][] part(long[][] a,int r,int c,int n) {
        long[][] x=new long[n][n];
        for(int i=0;i<n;i++) for(int j=0;j<n;j++) x[i][j]=a[r+i][c+j];
        return x;
    }
    static void put(long[][] a,long[][] b,int r,int c) {
        for(int i=0;i<b.length;i++) for(int j=0;j<b.length;j++) a[r+i][c+j]=b[i][j];
    }
    static int nextPowerOfTwo(int n) { int p=1; while(p<n)p*=2; return p; }
    static long[][] pad(long[][] a,int n) {
        long[][] x=new long[n][n];
        for(int i=0;i<a.length;i++) System.arraycopy(a[i],0,x[i],0,a.length);
        return x;
    }
    static boolean equal(long[][] a,long[][] b) {
        if(a.length!=b.length)return false;
        for(int i=0;i<a.length;i++) for(int j=0;j<a.length;j++) if(a[i][j]!=b[i][j])return false;
        return true;
    }
    public static void main(String[] args) {
        int[] sizes={2,4,8,16,32,64};
        Random r=new Random(42);
        System.out.println("n,conventional_multiplies,strassen_leaf_multiplies,correct");
        for(int n:sizes) {
            long[][] a=new long[n][n],b=new long[n][n];
            for(int i=0;i<n;i++) for(int j=0;j<n;j++){a[i][j]=r.nextInt(10);b[i][j]=r.nextInt(10);}
            conventionalMultiplies=0; long[][] expected=conventional(a,b);
            int p=nextPowerOfTwo(n); long[][] ap=pad(a,p),bp=pad(b,p);
            strassenMultiplies=0; long[][] cp=strassen(ap,bp);
            long[][] actual=part(cp,0,0,n);
            System.out.println(n+","+conventionalMultiplies+","+strassenMultiplies+","+equal(expected,actual));
        }
    }
}
