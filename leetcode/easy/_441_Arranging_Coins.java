package leetcode.easy;

/**
 *
 * You have n coins and you want to build a staircase with these coins. The staircase consists of k rows where the ith row has exactly i coins. The last row of the staircase may be incomplete.
 *
 * Given the integer n, return the number of complete rows of the staircase you will build.
 *
 *      X
 *      X X
 *      X X -
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5
 * Output: 2
 * Explanation: Because the 3rd row is incomplete, we return 2.
 *
 * Example 2:
 *
 *         X
 *  *      X X
 *  *      X X X
 *         X X - -
 *
 *
 * Input: n = 8
 * Output: 3
 * Explanation: Because the 4th row is incomplete, we return 3.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2^31 - 1
 *
 */

public class _441_Arranging_Coins {
    public int arrangeCoins(int n) {
        long hi = n;
        long lo = 0;

        while (lo <= hi) {
            long pivot = lo + (hi - lo) / 2;
            // Since it  iwll sum of 1 , 2, 3, 4, 5 etc
            long sum = pivot * (pivot + 1) / 2;

            if (sum == n) {
                return (int)pivot;
            }

            if (sum < n) {
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return (int)hi;
    }
}
