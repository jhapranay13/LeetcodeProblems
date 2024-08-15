package leetcode.medium;

import java.util.Arrays;

/**
 *
 * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
 *
 * Return the maximum product you can get.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * Example 2:
 *
 * Input: n = 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 58
 *
 */

public class _343_Integer_Break {
    //Top down
    public int integerBreak(int n) {

        if (n < 2) {
            return 1;
        }
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return recur(n, memo);
    }

    private int recur(int num, int[] memo) {

        if (num <= 2) {
            return 1;
        }

        if (memo[num] > -1) {
            return memo[num];
        }
        int ans = 0;
        // num / 2 because we need to atleast divide it by two
        for (int i = 1; i <= num / 2; i++) {
            //break the number into two and check
            int breakNum = num - i;
            ans = Math.max(ans, breakNum * i);
            //break number into more parts and check
            ans = Math.max(ans, i * recur(num - i, memo));
        }
        return memo[num] = ans;
    }
}
