package leetcode.LinkedList;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 *
 * Constraints:
 *
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^5
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 10^5
 *
 */

public class _57_Insert_Interval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> holder = new LinkedList<>();
        int index = 0;

        while (index < intervals.length) {

            if (intervals[index][1] >= newInterval[0]) {
                break;
            }
            holder.add(intervals[index++]);
        }
        int start = newInterval[0];
        int end = newInterval[1];

        while (index < intervals.length) {
            int[] interval = intervals[index++];
            start = Math.min(start, interval[0]);

            if (interval[1] > end) {

                if (interval[0] > end) {
                    index--;
                } else if (interval[0] <= end) {
                    end = interval[1];
                }
                break;
            }
        }
        holder.add(new int[] {start, end});

        while (index < intervals.length) {
            holder.add(intervals[index++]);
        }
        return holder.toArray(new int[holder.size()][]);
    }
}
