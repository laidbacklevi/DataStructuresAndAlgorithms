package algorithms.math;

public class LeviMath {

    public static final long MOD = 1000000007;

    public static long modPower(long a, long b, long mod) {
        if(a == 0)
            return 0;
        if(b == 0)
            return 1;
        long res = modPower(a, b / 2, mod);
        res = (res * res) % mod;
        if(b % 2 == 0)
            return res;
        else return (res * a) % mod;
    }

    public static long modPower(long a, long b) {
        return modPower(a, b, MOD);
    }

}
