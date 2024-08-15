package leetcode.DP;

/**
 *
 *
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 * Example 2:
 *
 * Input: n = 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 * Example 3:
 *
 * Input: n = 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 *
 *
 * Constraints:
 *
 * 0 <= n <= 30
 *
 */

public class _509_FibonacciNumber {

    //=============================================================================================
    //Top Down Approach
    public int fib(int n) {
        int[] memo = new int[n + 1];
        return recur(n, memo);
    }

    private int recur(int n, int[] memo) {

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (memo[n] > 0) {
            return memo[n];
        }
        return memo[n] = recur(n - 1, memo) + recur(n - 2, memo);
    }
    //=============================================================================================
    //Bottom up approach
    public int fib1(int num) {
        if (num == 0) {
            return 0;
        }
        int[] memo = new int[num + 1];
        memo[1] = 1;

        for (int n = 2; n <= num; n++) {
            memo[n] = memo[n - 1] + memo[n - 2];
        }
        return memo[num];
    }
}
