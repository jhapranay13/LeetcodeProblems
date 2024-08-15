package leetcode.medium;

/**
 *
 * A binary string is monotone increasing if it consists of some number of 0's (possibly none),
 * followed by some number of 1's (also possibly none).
 *
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
 *
 * Return the minimum number of flips to make s monotone increasing.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 * Example 2:
 *
 * Input: s = "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 * Example 3:
 *
 * Input: s = "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either '0' or '1'.
 *
 */

public class _926_FlipStringToMonotoneIncreasing {
    //==============================================================================================
    //Top Down Approach
    public int minFlipsMonoIncr(String s) {
        int memo[][] = new int[s.length()][2];
        return recur(s, 0, 0, memo);
    }

    private int recur(String s, int index, int prevVal, int[][] memo) {
        if (index == s.length()) {
            return 0;
        }

        if (memo[index][prevVal] > 0) {
            return memo[index][prevVal];
        }
        int val = s.charAt(index) - '0';
        int ans = Integer.MAX_VALUE / 10;

        if (prevVal == 0) {
            ans = Math.min(ans, recur(s, index + 1, val, memo));
            ans = Math.min(ans, 1 + recur(s, index + 1, val ^ 1, memo));
        } else {
            //prevVal or val cannot be left to zero after prevVal is one
            if (val == 1) {
                ans = Math.min(ans, recur(s, index + 1, val, memo));
            } else {
                ans = Math.min(ans, 1 + recur(s, index + 1, val ^ 1, memo));
            }
        }
        return memo[index][prevVal] = ans;
    }
    //=============================================================================================
    //Bottom up approach
    public int minFlipsMonoIncr1(String s) {
        int memo[][] = new int[s.length() + 1][2];

        for (int index = s.length() - 1; index >= 0; index--) {

            for (int prevVal = 1; prevVal >= 0; prevVal--) {
                int val = s.charAt(index) - '0';
                int ans = Integer.MAX_VALUE / 10;

                if (prevVal == 0) {
                    ans = Math.min(ans, memo[index + 1][val]);
                    ans = Math.min(ans, 1 + memo[index + 1][val ^ 1]);
                } else {
                    //prevVal or val cannot be left to zero after prevVal is one
                    if (val == 1) {
                        ans = Math.min(ans, memo[index + 1][val]);
                    } else {
                        ans = Math.min(ans, 1 + memo[index + 1][val ^ 1]);
                    }
                }
                memo[index][prevVal] = ans;
            }
        }
        return memo[0][0];
    }
}
