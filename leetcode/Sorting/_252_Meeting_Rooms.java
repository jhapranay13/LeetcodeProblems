package leetcode.Sorting;

import java.util.Arrays;

/**
 *
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 *
 *
 * Constraints:
 *
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti < endi <= 10^6
 *
 */

public class _252_Meeting_Rooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int end = -1;

        for (int[] interval : intervals) {

            if (end > interval[0]) {
                return false;
            }
            end = interval[1];
        }
        return true;
    }
}
