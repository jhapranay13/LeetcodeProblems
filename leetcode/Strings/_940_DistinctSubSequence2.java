package leetcode.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a string s, return the number of distinct non-empty subsequences of s. Since the answer may be very large, return it modulo 109 + 7.
 *
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not.
 *
 *
 * Example 1:
 *
 * Input: s = "abc"
 * Output: 7
 * Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
 * Example 2:
 *
 * Input: s = "aba"
 * Output: 6
 * Explanation: The 6 distinct subsequences are "a", "b", "ab", "aa", "ba", and "aba".
 * Example 3:
 *
 * Input: s = "aaa"
 * Output: 3
 * Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 2000
 * s consists of lowercase English letters.
 *
 */

public class _940_DistinctSubSequence2 {

    //==============================================================================================
    //Top Down
    public int distinctSubseqII(String s) {
        Map<String, Integer> memo = new HashMap<>();
        //-1 because empty will also be counted
        return recur(s, '\u0000', 0, memo) - 1;
    }
    int MOD = 1_000_000_007;

    private int recur(String s, char last, int index, Map<String, Integer> memo) {
        if (s.length() <= index) {
            return 1;
        }
        String key = last + ", " + index;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int ans = recur(s, s.charAt(index), index + 1, memo) % MOD;

        if (s.charAt(index) != last) {
            ans = (ans + recur(s, last, index + 1, memo)) % MOD;
        }
        memo.put(key, ans);
        return ans;
    }
    //=============================================================================================
    //Bottom up
    public int distinctSubseqII1(String s) {
        Map<String, Integer> memo = new HashMap<>();
        //-1 because empty will also be counted
        memo.put('\u0000' + ", " + s.length(), 1);

        for (int index = s.length() - 1; index >= 0; index--) {

            for (int i = 27; i >= 0; i--) {
                char last = '\u0000';

                if (i != 27) {
                    last = (char)(i + 'a');
                }
                String key = last + ", " + index;
                int ans = recur(s, s.charAt(index), index + 1, memo) % MOD;

                if (s.charAt(index) != last) {
                    ans = (ans + recur(s, last, index + 1, memo)) % MOD;
                }
                memo.put(key, ans);
            }
        }
        return memo.get('\u0000' + ", " + 0) - 1;
    }
    //=============================================================================================
    //Mathematical DP
    public int distinctSubseqII3(String s) {
        long mod = (long) Math.pow(10, 9) + 7;
        Map<Character, Integer> map = new HashMap<>();
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 0 ; i < s.length() ; i++)
        {
            //formula to get the number of subsequences at this position
            dp[i + 1] = 2 * dp[i];

            char c = s.charAt(i);
            if (map.containsKey(c))
            {
                int j = map.get(c);
                //Removing the duplicate computation
                dp[i + 1] -= dp[j];
            }

            map.put(c, i);
            if (dp[i + 1] < 0)
            {
                dp[i + 1] += mod;
            }
            dp[i + 1] %= mod;
        }
        return dp[s.length()] - 1;
    }
}
