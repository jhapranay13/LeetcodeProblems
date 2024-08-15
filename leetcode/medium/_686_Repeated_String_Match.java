package leetcode.medium;

/**
 *
 * Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it. If it is impossible for b​​​​​​ to be a substring of a after repeating it, return -1.
 *
 * Notice: string "abc" repeated 0 times is "", repeated 1 time is "abc" and repeated 2 times is "abcabc".
 *
 *
 *
 * Example 1:
 *
 * Input: a = "abcd", b = "cdabcdab"
 * Output: 3
 * Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.
 * Example 2:
 *
 * Input: a = "a", b = "aa"
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 10^4
 * a and b consist of lowercase English letters.
 *
 */

public class _686_Repeated_String_Match {
    long prime = 11L;
    long mod = Long.MAX_VALUE;

    public int repeatedStringMatch(String a, String b) {
        int ans = 1;

        int initLen = a.length();
        String holder = a;
        //multiply the string a until it will be >= to length of String b
        while(initLen < b.length()){
            holder = holder + a;
            initLen = initLen + a.length();
            ans = ans + 1;
        }
        long[] pow = new long[b.length()];

        for (int i = pow.length - 1; i >= 0; i--) {

            if (i == pow.length - 1) {
                pow[i] = 1;
            } else {
                pow[i] = pow[i + 1] * prime;
            }
        }

        if (rabinKarp(holder, b, pow)) {
            return ans;
        }

        if (rabinKarp(holder + a, b, pow)) {
            return ans + 1;
        }
        return -1;
    }

    private boolean rabinKarp(String a, String b, long[] pow) {
        long hashA = 0L;
        long hashB = 0L;
        int hi = b.length() -1;

        while (hi >= 0) {
            long temp = a.charAt(hi);
            hashA += (temp * pow[hi]) % mod;
            temp = b.charAt(hi);
            hashB += (temp * pow[hi--]) % mod;
        }

        if (hashA == hashB) {
            return true;
        }
        int index = b.length() - 1;

        while (hashA != hashB && index < a.length() - 1) {
            long temp = (a.charAt(index - b.length() + 1) * pow[0]) % mod;
            hashA -= temp;

            if (hashA < 0) {
                hashA += mod;
                hashA %= mod;
            }
            hashA = (hashA * prime) % mod;
            hashA += a.charAt(++index);
        }
        return hashA == hashB;
    }
}
