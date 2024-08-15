package leetcode.Greedy;

import java.util.Arrays;

/**
 *
 * Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove all intervals that are covered by another interval in the list.
 *
 * The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
 *
 * Return the number of remaining intervals.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 * Example 2:
 *
 * Input: intervals = [[1,4],[2,3]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 1000
 * intervals[i].length == 2
 * 0 <= li < ri <= 10^5
 * All the given intervals are unique.
 *
 */

public class _1288_Remove_Covered_Intervals {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int prevEnd = 0;
        int includeCount = 0;

        for (int[] interval : intervals) {

            if (prevEnd < interval[1]) {
                includeCount++;
                prevEnd = interval[1];
            }
        }
        return includeCount;
    }
}
