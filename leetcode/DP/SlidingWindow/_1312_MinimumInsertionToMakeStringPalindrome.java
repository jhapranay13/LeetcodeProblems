package leetcode.DP.SlidingWindow;

/**
 *
 * Given a string s. In one step you can insert any character at any index of the string.
 *
 * Return the minimum number of steps to make s palindrome.
 *
 * A Palindrome String is one that reads the same backward as well as forward.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "zzazz"
 * Output: 0
 * Explanation: The string "zzazz" is already palindrome we don't need any insertions.
 * Example 2:
 *
 * Input: s = "mbadm"
 * Output: 2
 * Explanation: String can be "mbdadbm" or "mdbabdm".
 * Example 3:
 *
 * Input: s = "leetcode"
 * Output: 5
 * Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 *
 *
 */

public class _1312_MinimumInsertionToMakeStringPalindrome {
    //=============================================================================================
    //Top down approach
    public int minInsertions(String s) {
        int memo[][] = new int[s.length()][s.length()];
        return recur(s, 0, s.length() - 1, memo);
    }

    private int recur(String s, int lo, int hi, int[][] memo) {

        if (lo >= hi) {
            return 0;
        }

        if (memo[lo][hi] > 0) {
            return memo[lo][hi];
        }
        int ans = Integer.MAX_VALUE / 10;

        if (s.charAt(lo) == s.charAt(hi))  {
            ans = Math.min(ans, recur(s, lo + 1, hi - 1, memo));
        }
        ans = Math.min(ans, 1 + recur(s, lo + 1, hi, memo));//inserting at hi
        ans = Math.min(ans, 1 + recur(s, lo, hi - 1, memo));//inserting at lo
        return memo[lo][hi] = ans;
    }
    //=============================================================================================
    //Bottom up approach
    public int minInsertions1(String s) {
        int memo[][] = new int[s.length()][s.length()];

        for (int h = 1; h <= s.length(); h++) {

            for (int lo = 0; lo <= s.length() - h; lo++) {
                int hi = h + lo - 1;

                if (lo >= hi) {
                    continue;
                }
                int ans = Integer.MAX_VALUE / 10;

                if (s.charAt(lo) == s.charAt(hi))  {
                    ans = Math.min(ans, memo[lo + 1][hi - 1]);
                }
                ans = Math.min(ans, 1 + memo[lo + 1][hi]);//inserting at hi
                ans = Math.min(ans, 1 + memo[lo][hi - 1]);//inserting at lo
                memo[lo][hi] = ans;
            }
        }
        return memo[0][s.length() - 1];
    }
}
