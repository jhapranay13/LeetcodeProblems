package leetcode.DP;

/**
 *
 * The Tribonacci sequence Tn is defined as follows:
 *
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 *
 * Given n, return the value of Tn.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * Example 2:
 *
 * Input: n = 25
 * Output: 1389537
 *
 *
 * Constraints:
 *
 * 0 <= n <= 37
 * The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 *
 */

public class _1137_N_th_Tribonacci_Number {
    public int tribonacci(int n) {
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

        if (n == 2) {
            return 1;
        }

        if (memo[n] > 0) {
            return memo[n];
        }
        return memo[n] = recur(n - 1, memo) + recur(n - 2, memo) + recur(n - 3, memo);
    }
}
