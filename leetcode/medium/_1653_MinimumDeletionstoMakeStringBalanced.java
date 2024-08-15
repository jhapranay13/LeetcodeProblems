package leetcode.medium;

/**
 *
 * You are given a string s consisting only of characters 'a' and 'b'​​​​.
 *
 * You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.
 *
 * Return the minimum number of deletions needed to make s balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aababbab"
 * Output: 2
 * Explanation: You can either:
 * Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
 * Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
 * Example 2:
 *
 * Input: s = "bbaaaaabb"
 * Output: 2
 * Explanation: The only solution is to delete the first two characters.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is 'a' or 'b'.
 *
 */

public class _1653_MinimumDeletionstoMakeStringBalanced {

    public int minimumDeletions(String s) {
        int memo[][]= new int[s.length() + 1][2];

        int ans = recur(s, 0, 'a', memo);
        return ans >= Integer.MAX_VALUE ? 0 : ans;
    }

    private int recur(String s, int curr, char prevVal, int[][] memo) {

        if (curr >= s.length()) {
            return 0;
        }

        if (memo[curr][prevVal - 'a'] > 0) {
            return memo[curr][prevVal -'a'];
        }
        char currChar = s.charAt(curr);
        int deleteCurr = Integer.MAX_VALUE / 10;
        int doNotDelete = Integer.MAX_VALUE / 10;

        if (prevVal != currChar && prevVal == 'b') {
            deleteCurr = 1 + recur(s, curr + 1, prevVal, memo);
        } else {
            deleteCurr = 1 + recur(s, curr + 1, prevVal, memo);
            doNotDelete = recur(s, curr + 1, currChar, memo);
        }
        return memo[curr][prevVal - 'a'] = Math.min(deleteCurr, doNotDelete);
    }

}
