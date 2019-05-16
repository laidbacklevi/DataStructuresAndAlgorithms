package algorithms.string.stringmatching;

import java.util.Arrays;

public class KMPSearch {

    public static int search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        if(n == 0 || m == 0 || m > n)
            return -1;

        int lookUpArr[] = createLookUpArray(pattern);

        for(int k = 0, i = 0; i < n; i++) {
            while(k != 0 && text.charAt(i) != pattern.charAt(k)) {
                k = lookUpArr[k - 1];
            }
            if(text.charAt(i) == pattern.charAt(k)) {
                k++;
                if(k == m) return i - m + 1;
            }
        }
        return -1;
    }

    public static int[] createLookUpArray(String str) {
        int len = str.length();
        int arr[] = new int[len];

        for(int k = 0, i = 1; i < len; i++) {
            while(k != 0 && str.charAt(i) != str.charAt(k)) {
                k = arr[k - 1];
            }
            if(str.charAt(i) == str.charAt(k)) {
                arr[i] = k + 1;
                k++;
            }
        }
        return arr;
    }

    public static void main(String args[]) {
        System.out.println(KMPSearch.search("baaaabbaaabbaabbaaaaaab" +
                "aaaaaaabbbbaaaabbaabbababbbaaaabababaabaabaabaabbabbabbbabb" +
                        "baaabaaaabaaaaaab",
                "aabbbababbbaabaaabbaaabbbbbabababaabaaabbbbaaaaaa"));
    }

}
