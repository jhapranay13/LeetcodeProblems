package leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given an array of events where events[i] = [startDayi, endDayi, valuei]. The ith event starts at startDayi and ends at endDayi, and if you attend this event, you will receive a value of valuei. You are also given an integer k which represents the maximum number of events you can attend.
 *
 * You can only attend one event at a time. If you choose to attend an event, you must attend the entire event. Note that the end day is inclusive: that is, you cannot attend two events where one of them starts and the other ends on the same day.
 *
 * Return the maximum sum of values that you can receive by attending events.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
 * Output: 7
 * Explanation: Choose the green events, 0 and 1 (0-indexed) for a total value of 4 + 3 = 7.
 * Example 2:
 *
 *
 *
 * Input: events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
 * Output: 10
 * Explanation: Choose event 2 for a total value of 10.
 * Notice that you cannot attend any other event as they overlap, and that you do not have to attend k events.
 * Example 3:
 *
 *
 *
 * Input: events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
 * Output: 9
 * Explanation: Although the events do not overlap, you can only attend 3 events. Pick the highest valued three.
 *
 *
 * Constraints:
 *
 * 1 <= k <= events.length
 * 1 <= k * events.length <= 10^6
 * 1 <= startDayi <= endDayi <= 10^9
 * 1 <= valuei <= 10^6
 *
 *
 */

public class _1751_Maximum_Number_of_Events_That_Can_Be_Attended_II {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        Map<String, Integer> memo = new HashMap<>();
        return recur(events, k, 0, 0, 0, memo);
    }

    private int recur(int[][] events, int k, int index, int endTime, int count, Map<String, Integer> memo) {

        if (events.length == index) {
            return 0;
        }

        if (k == count) {
            return 0;
        }
        String key = index + "|" + endTime + "|" + count;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int include = 0;

        if (events[index][0] > endTime) {
            include = events[index][2] + recur(events, k, index + 1, events[index][1], count + 1, memo);
        }
        int exclude = recur(events, k, index + 1, endTime, count, memo);
        memo.put(key, Math.max(include, exclude));
        return Math.max(include, exclude);
    }
}
