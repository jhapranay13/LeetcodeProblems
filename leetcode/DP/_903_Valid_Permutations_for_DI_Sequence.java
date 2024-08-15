package leetcode.DP;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given a string s of length n where s[i] is either:
 *
 * 'D' means decreasing, or
 * 'I' means increasing.
 * A permutation perm of n + 1 integers of all the integers in the range [0, n] is called a valid permutation if for all valid i:
 *
 * If s[i] == 'D', then perm[i] > perm[i + 1], and
 * If s[i] == 'I', then perm[i] < perm[i + 1].
 * Return the number of valid permutations perm. Since the answer may be large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "DID"
 * Output: 5
 * Explanation: The 5 valid permutations of (0, 1, 2, 3) are:
 * (1, 0, 3, 2)
 * (2, 0, 3, 1)
 * (2, 1, 3, 0)
 * (3, 0, 2, 1)
 * (3, 1, 2, 0)
 * Example 2:
 *
 * Input: s = "D"
 * Output: 1
 *
 *
 * Constraints:
 *
 * n == s.length
 * 1 <= n <= 200
 * s[i] is either 'I' or 'D'.
 *
 */

public class _903_Valid_Permutations_for_DI_Sequence {
    public int numPermsDISequence(String s) {
        int lo = 0;
        int hi = s.length();
        int ans = 0;
        int[] v = new int[hi + 1];
        Long[][] memo = new Long[s.length() + 1][s.length() + 1];
        //Try all start number to check
        for (int i = lo; i <= hi; i++) {
            ans += (int)recur(s, 0, s.length(), 0, i, v, memo);
            ans %= mod;
        }
        return ans;
    }
    int mod = (int)1e9 + 7;

    private long recur(String s, int lo, int hi, int index, int curr, int[] v,
                       Long[][] memo) {

        if (curr < lo || curr > hi) {
            return 0;
        }

        if (index == s.length()) {
            return 1;
        }
        String key =  "|" +index + "|" + curr;

        if (memo[index][curr] != null) {
            return memo[index][curr];
        }
        long ans = 0;
        v[curr] = 1;

        if (s.charAt(index) == 'I') {

            for (int i = curr + 1; i <= hi; i++) {

                if (v[i] == 1) {
                    continue;
                }
                ans = (ans + recur(s, lo, hi, index + 1, i, v, memo)) % mod;
            }
        } else {

            for (int i = curr - 1; i >= lo; i--) {

                if (v[i] == 1) {
                    continue;
                }
                ans = (ans + recur(s, lo, hi, index + 1, i, v, memo)) % mod;
            }
        }
        v[curr] = 0;
        return memo[index][curr] = ans % mod;
    }
    //=============================================================================================
    // HashMap memo Version
    public int numPermsDISequence1(String s) {
        int lo = 0;
        int hi = s.length();
        int ans = 0;
        int[] v = new int[hi + 1];
        Map<String, Long> memo = new HashMap<>();

        for (int i = lo; i <= hi; i++) {
            ans += (int)recur(s, 0, s.length(), 0, i, v, memo);
            ans %= mod;
        }
        return ans;
    }

    private long recur(String s, int lo, int hi, int index, int curr, int[] v,
                       Map<String, Long> memo) {

        if (curr < lo || curr > hi) {
            return 0;
        }

        if (index == s.length()) {
            return 1;
        }
        String key =  "|" +index + "|" + curr;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        long ans = 0;
        v[curr] = 1;

        if (s.charAt(index) == 'I') {

            for (int i = curr + 1; i <= hi; i++) {

                if (v[i] == 1) {
                    continue;
                }
                ans = (ans + recur(s, lo, hi, index + 1, i, v, memo) % mod) % mod;
            }
        } else {

            for (int i = curr - 1; i >= lo; i--) {

                if (v[i] == 1) {
                    continue;
                }
                ans = (ans + recur(s, lo, hi, index + 1, i, v, memo) % mod) % mod;
            }
        }
        v[curr] = 0;
        memo.put(key, ans);
        return ans % mod;
    }
}
