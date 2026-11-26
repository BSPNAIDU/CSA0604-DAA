import java.math.BigInteger;
import java.util.Random;

public class Q3_Karatsuba {
    static long recursiveMultiplications;
    static BigInteger standard(BigInteger x, BigInteger y) { return x.multiply(y); }

    static BigInteger karatsuba(BigInteger x, BigInteger y) {
        recursiveMultiplications++;
        if (x.signum() == 0 || y.signum() == 0) return BigInteger.ZERO;
        int n = Math.max(x.toString().length(), y.toString().length());
        if (n <= 18) return x.multiply(y);
        int m = (n + 1) / 2;
        BigInteger base = BigInteger.TEN.pow(m);
        BigInteger[] xs = x.divideAndRemainder(base);
        BigInteger[] ys = y.divideAndRemainder(base);
        BigInteger a = xs[0], b = xs[1], c = ys[0], d = ys[1];
        BigInteger z2 = karatsuba(a, c);
        BigInteger z0 = karatsuba(b, d);
        BigInteger z1 = karatsuba(a.add(b), c.add(d)).subtract(z2).subtract(z0);
        return z2.multiply(base.pow(2)).add(z1.multiply(base)).add(z0);
    }

    public static void main(String[] args) {
        int[] digits = {16, 32, 64, 128, 256, 512, 1024};
        Random random = new Random(42);
        System.out.println("digits,standard_ms,karatsuba_ms,karatsuba_recursive_calls,correct");
        for (int d : digits) {
            StringBuilder sx = new StringBuilder(), sy = new StringBuilder();
            sx.append(1 + random.nextInt(9)); sy.append(1 + random.nextInt(9));
            for (int i = 1; i < d; i++) { sx.append(random.nextInt(10)); sy.append(random.nextInt(10)); }
            BigInteger x = new BigInteger(sx.toString()), y = new BigInteger(sy.toString());
            long start = System.nanoTime();
            BigInteger expected = standard(x, y);
            long standardTime = System.nanoTime() - start;
            recursiveMultiplications = 0;
            start = System.nanoTime();
            BigInteger actual = karatsuba(x, y);
            long karaTime = System.nanoTime() - start;
            System.out.println(d + "," + standardTime / 1e6 + "," + karaTime / 1e6 + "," +
                    recursiveMultiplications + "," + expected.equals(actual));
        }
    }
}
