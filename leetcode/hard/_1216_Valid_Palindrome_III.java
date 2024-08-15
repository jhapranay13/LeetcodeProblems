package leetcode.hard;

import java.util.Arrays;

/**
 *
 * Given a string s and an integer k, return true if s is a k-palindrome.
 *
 * A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcdeca", k = 2
 * Output: true
 * Explanation: Remove 'b' and 'e' characters.
 * Example 2:
 *
 * Input: s = "abbababa", k = 1
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of only lowercase English letters.
 * 1 <= k <= s.length
 *
 */

public class _1216_Valid_Palindrome_III {
    // Top Down MLE
    public boolean isValidPalindrome(String s, int k) {
        Boolean[][][] memo = new Boolean[k + 1][s.length()][s.length()];
        return recur(s, k, 0, s.length() - 1, memo);
    }

    private boolean recur(String s, int k, int lo, int hi, Boolean[][][] memo) {

        if (k < 0) {
            return false;
        }

        if (lo - hi == 1) {

            if (s.charAt(lo) == s.charAt(hi)) {
                return true;
            } else {
                return false;
            }
        }

        if (lo == hi) {
            return true;
        }

        if (memo[k][lo][hi] != null) {
            return memo[k][lo][hi];
        }
        boolean ans = false;

        if (s.charAt(lo) == s.charAt(hi)) {
            ans = recur(s, k, lo + 1, hi - 1, memo)
                    || recur(s, k - 1, lo + 1, hi, memo) //k - 1 coz we removed one
                    || recur(s, k - 1, lo, hi - 1, memo);//k - 1 coz we removed one
        } else {
            ans = recur(s, k - 1, lo + 1, hi, memo) //k - 1 coz we removed one
                    || recur(s, k - 1, lo, hi - 1, memo);//k - 1 coz we removed one
        }
        return memo[k][lo][hi] = ans;
    }
    //=============================================================================================
    //Another Approach that avoids MLE. Top Down
    public boolean isValidPalindrome1(String s, int k) {
        int[][] memo = new int[s.length()][s.length()];

        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return (recur(s, 0, s.length() - 1, memo) <= k) ? true : false;
    }
    //To counter the MLE we can return minimum K
    private int recur(String s, int lo, int hi, int[][] memo) {

        if (lo - hi == 1) {

            if (s.charAt(lo) == s.charAt(hi)) {
                return 0;
            } else {
                return 1;
            }
        }

        if (lo == hi) {
            return 0;
        }

        if (memo[lo][hi] > -1) {
            return memo[lo][hi];
        }
        int ans = Integer.MAX_VALUE;

        if (s.charAt(lo) == s.charAt(hi)) {
            ans = Math.min(recur(s, lo + 1, hi - 1, memo),
                    Math.min(1 + recur(s, lo + 1, hi, memo), //k - 1 coz we removed one
                            1 + recur(s, lo, hi - 1, memo)));//k - 1 coz we removed one
        } else {
            ans = Math.min(1 + recur(s, lo + 1, hi, memo), //k - 1 coz we removed one
                    1 + recur(s, lo, hi - 1, memo)); //k - 1 coz we removed one
        }
        return memo[lo][hi] = ans;
    }

    //=============================================================================================
    //Bottom up Approach
    public boolean isValidPalindrome2(String s, int k) {
        int[][] memo = new int[s.length()][s.length()];

        /*for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }*/

        for (int h = 1; h <= s.length(); h++) {

            for (int lo = 0; lo <= s.length() - h; lo++) {
                int hi = lo + h - 1;

                if (lo - hi == 1) {

                    if (s.charAt(lo) == s.charAt(hi)) {
                        memo[lo][hi] = 0;
                    } else {
                        memo[lo][hi] = 1;
                    }
                    continue;
                }

                if (lo == hi) {
                    memo[lo][hi] = 0;
                    continue;
                }
                int ans = Integer.MAX_VALUE;

                if (s.charAt(lo) == s.charAt(hi)) {
                    ans = Math.min(memo[lo + 1][hi - 1],
                            Math.min(1 + memo[lo + 1][hi], //k - 1 coz we removed one
                                    1 + memo[lo][hi - 1]));//k - 1 coz we removed one
                } else {
                    ans = Math.min(1 + memo[lo + 1][hi], //k - 1 coz we removed one
                            1 + memo[lo][hi - 1]); //k - 1 coz we removed one
                }
                memo[lo][hi] = ans;
            }
        }
        return (memo[0][s.length() - 1] <= k)? true : false;
    }
}
