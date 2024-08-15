package leetcode.DP.SingleDimension;

import java.util.Arrays;

/**
 *
 * There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).
 *
 * There are n + 1 taps located at points [0, 1, ..., n] in the garden.
 *
 * Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.
 *
 * Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, ranges = [3,4,1,1,0,0]
 * Output: 1
 * Explanation: The tap at point 0 can cover the interval [-3,3]
 * The tap at point 1 can cover the interval [-3,5]
 * The tap at point 2 can cover the interval [1,3]
 * The tap at point 3 can cover the interval [2,4]
 * The tap at point 4 can cover the interval [4,4]
 * The tap at point 5 can cover the interval [5,5]
 * Opening Only the second tap will water the whole garden [0,5]
 * Example 2:
 *
 * Input: n = 3, ranges = [0,0,0,0]
 * Output: -1
 * Explanation: Even if you activate all the four taps you cannot water the whole garden.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^4
 * ranges.length == n + 1
 * 0 <= ranges[i] <= 100
 *
 */

public class _1326_Minimum_Number_of_Taps_to_Open_to_Water_a_Garden {
    public int minTaps(int n, int[] ranges) {
        int[] jumps = new int[n + 1];
        // Converting it into jumps game 2 problem
        for(int i = 0; i < jumps.length; ++i){
            int l = Math.max(0, i - ranges[i]);
            int r = Math.min(n, i + ranges[i]);
            // from index l we can jump maximum r
            jumps[l] = Math.max(jumps[l], r - l);
        }
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        int ans = recur(jumps, 0, memo);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int recur(int[] jumps, int index, int[] memo) {

        if (jumps.length - 1 <= index) {
            return 0;
        }

        if (jumps[index] == 0) {
            return Integer.MAX_VALUE;
        }

        if (memo[index] > -1) {
            return memo[index];
        }
        int jump = index + jumps[index];
        int ans = Integer.MAX_VALUE;

        for (int i = index + 1; i <= jump; i++) {
            ans = Math.min(ans, recur(jumps, i, memo));
        }
        return memo[index] = ans == Integer.MAX_VALUE ? Integer.MAX_VALUE : ans + 1;
    }
}
