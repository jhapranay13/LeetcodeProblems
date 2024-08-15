package leetcode.DP;

import java.util.Arrays;

/**
 *
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 *
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * Example 3:
 *
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 10^5
 * intervals[i].length == 2
 * -5 * 10^4 <= starti < endi <= 5 * 10^4
 *
 */

public class _435_Non_overlapping_Intervals {
    //Greedy
    public int eraseOverlapIntervals(int[][] intervals) {
        // Sorting by earliest end time to inculcate most number of intervals
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int count = 0;
        int prev = Integer.MIN_VALUE;

        for (int i = 0; i < intervals.length; i++) {

            if ( prev <= intervals[i][0]) {
                count++;
                prev = intervals[i][1];
            }
        }
        return intervals.length - count;
    }
    //=============================================================================================
    //Bottom up Tle
    public int eraseOverlapIntervals2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] memo = new int[intervals.length + 1];
        // Arrays.fill(memo, -1);

        for (int index = intervals.length - 1; index >= 0; index--) {
            int nextIndex = index + 1;

            while (nextIndex < intervals.length && intervals[index][1] > intervals[nextIndex][0]) {
                nextIndex++;
            }
            int include = 1 + memo[nextIndex];
            int exclude = memo[index + 1];
            memo[index] = Math.max(include, exclude);
        }
        return intervals.length - memo[0];
    }
    //=============================================================================================
    //Top down Tle
    public int eraseOverlapIntervals3(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] memo = new int[intervals.length];
        // Arrays.fill(memo, -1);
        return intervals.length - recur(intervals, 0, memo);
    }
    // Max Incresing SubSequence
    private int recur(int[][] intervals, int index, int[] memo) {

        if (index == intervals.length) {
            return 0;
        }

        if (memo[index] > 0) {
            return memo[index];
        }
        int nextIndex = index + 1;

        while (nextIndex < intervals.length && intervals[index][1] > intervals[nextIndex][0]) {
            nextIndex++;
        }
        int include = 1 + recur(intervals, nextIndex, memo);
        int exclude = recur(intervals, index + 1, memo);
        return memo[index] = Math.max(include, exclude);
    }
}
