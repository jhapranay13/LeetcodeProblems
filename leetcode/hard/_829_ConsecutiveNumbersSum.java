package leetcode.hard;

/**
 *
 * Given an integer n, return the number of ways you can write n as the sum of consecutive positive integers.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5
 * Output: 2
 * Explanation: 5 = 2 + 3
 * Example 2:
 *
 * Input: n = 9
 * Output: 3
 * Explanation: 9 = 4 + 5 = 2 + 3 + 4
 * Example 3:
 *
 * Input: n = 15
 * Output: 4
 * Explanation: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 *
 *
 * Constraints:
 *
 * 1 <= n <= 109
 *
 */

public class _829_ConsecutiveNumbersSum {
    //Even though this gives TLE Most of the guys maths won't be this strong to solve it.
    public int consecutiveNumbersSum(int n) {

        if (n <= 2) {
            return 1;
        }
        int slow = 1;
        int fast = 1;
        int end = n / 2 + 1; // This can also work (int)Math.sqrt( 2 * n);
        int sum = 0;
        int ans = 0;

        while (fast <= end) {
            sum += fast;

            if (sum == n) {
                ans++;
            }

            if (sum > n) {

                while (slow < fast) {
                    sum -= slow;

                    if (sum == n) {
                        ans++;
                    }
                    slow++;

                    if (sum < n) {
                        break;
                    }
                }
            }
            fast++;
        }
        return ans + 1;
    }
}
