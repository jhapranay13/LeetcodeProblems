package leetcode.Strings;

/**
 *
 *
 * You are given a string s consisting of lowercase letters and an integer k. We call a string t ideal if the following conditions are satisfied:
 *
 * t is a subsequence of the string s.
 * The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
 * Return the length of the longest ideal string.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 *
 * Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of 'a' and 'z' is 25, not 1.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "acfgbd", k = 2
 * Output: 4
 * Explanation: The longest ideal string is "acbd". The length of this string is 4, so 4 is returned.
 * Note that "acfgbd" is not ideal because 'c' and 'f' have a difference of 3 in alphabet order.
 * Example 2:
 *
 * Input: s = "abcd", k = 3
 * Output: 4
 * Explanation: The longest ideal string is "abcd". The length of this string is 4, so 4 is returned.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 0 <= k <= 25
 * s consists of lowercase English letters.
 *
 */

public class _2370_Longest_Ideal_Subsequence {
    public int longestIdealString(String s, int k) {
        Integer[][] memo = new Integer[130][s.length()];
        return recur(s, k, '#', 0, memo);
    }

    private int recur(String s, int k, char prevChar, int index, Integer[][] memo) {

        if (s.length() == index) {
            return 0;
        }

        if (memo[prevChar][index] != null) {
            return memo[prevChar][index];
        }
        int inc = 0;

        if (prevChar == '#' ||
                Math.abs((prevChar - 'a') - (s.charAt(index) - 'a')) <= k) {
            inc = 1 + recur(s, k, s.charAt(index), index + 1, memo);
        }
        int exc = recur(s, k, prevChar, index + 1, memo);
        return memo[prevChar][index] = Math.max(inc, exc);
    }
}
