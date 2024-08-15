package leetcode.DP;

import java.util.Arrays;

/**
 *
 *
 * Given the array houses where houses[i] is the location of the ith house along a street and an integer k, allocate k mailboxes in the street.
 *
 * Return the minimum total distance between each house and its nearest mailbox.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *       _2_ _1_      _1_ _1_     _0
 *      |  VV  |     |  VV  |    | V
 *      H   M  H     H  M   H    H M
 *      1   3  4     8  9  10    20
 *
 * Input: houses = [1,4,8,10,20], k = 3
 * Output: 5
 * Explanation: Allocate mailboxes in position 3, 9 and 20.
 * Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5
 *
 * Example 2:
 *
 *       _1_ _2__     __2_  _4_
 *      |  V V  |    |   V V  |
 *      H  HM   H    H    M   H
 *      2  3    5   12  14   18
 *
 * Input: houses = [2,3,5,12,18], k = 2
 * Output: 9
 * Explanation: Allocate mailboxes in position 3 and 14.
 * Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.
 *
 *
 * Constraints:
 *
 * 1 <= k <= houses.length <= 100
 * 1 <= houses[i] <= 10^4
 * All the integers of houses are unique.
 *
 */

public class _1478_Allocate_Mailboxes {
    // The problem is dividing houses into k groups
    // in Arrays shortest distance sum is sum from it's median
    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);
        int[][] memo = new int[k + 1][houses.length];
        return recur(houses, k, 0, memo);
    }

    private int recur(int[] houses, int k, int index, int[][] memo) {

        if (k == 0 || index >= houses.length) {
            return Integer.MAX_VALUE;
        }

        if (k == 1) {
            return getMedian(houses, index, houses.length - 1);
        }

        if (memo[k][index] > 0) {
            return memo[k][index];
        }
        int ans = Integer.MAX_VALUE;

        for (int i = index; i < houses.length; i++) {
            int medianDistance = getMedian(houses, index, i);
            int nextSum = recur(houses, k - 1, i + 1, memo);

            if (nextSum != Integer.MAX_VALUE) {
                ans = Math.min(ans, medianDistance + nextSum) % 1000000007;
            }
        }
        return memo[k][index] = ans;
    }

    private int getMedian(int[] houses, int lo, int hi) {
        int sum = 0;
        int pos = lo + (hi - lo) / 2;

        for (int i = lo; i <= hi; i++) {
            sum += Math.abs(houses[pos] - houses[i]);
        }
        return sum;
    }
}
