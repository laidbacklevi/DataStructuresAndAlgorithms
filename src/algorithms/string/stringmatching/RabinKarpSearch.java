package algorithms.string.stringmatching;

import algorithms.math.LeviMath;

import java.util.ArrayList;

public class RabinKarpSearch {

    private static final long PRIME = 3;

    public static ArrayList<Integer> search(String text, String pattern) {
        ArrayList<Integer> toReturn = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        if(n == 0 || m == 0 || m > n)
            return toReturn;
        long patternHash = getInitialHash(pattern);
        long textHash = getInitialHash(text.substring(0, m));
        if(patternHash == textHash && isAMatch(text, pattern, 0))
            toReturn.add(0);
        for(int i = 1; i <= n - m; i++) {
            textHash = recalculateHash(textHash, m, text.charAt(i - 1), text.charAt(i + m - 1));
            if(patternHash == textHash && isAMatch(text, pattern, i))
                toReturn.add(i);
        }
        return toReturn;
    }

    private static long getInitialHash(String str) {
        // Overflow not handled
        long hash = 0;
        int len = str.length();
        for(int i = 0; i < len; i++) {
            int asciiCode = (int) str.charAt(i);
            hash += (asciiCode * LeviMath.modPower(PRIME, i));
        }
        return hash;
    }

    private static long recalculateHash(long previousHash, int length, char oldChar, char newChar) {
        // Overflow not handled
        long hash = previousHash - (int) oldChar;
        hash /= PRIME;
        hash += newChar * LeviMath.modPower(PRIME, length - 1);
        return hash;
    }

    private static boolean isAMatch(String text, String pattern, int beginIndex) {
        int m = pattern.length();
        for(int i = 0; i < m; i++) {
            if(text.charAt(beginIndex + i) != pattern.charAt(i))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(RabinKarpSearch.search("hellohellohello", "hello"));
    }

}
