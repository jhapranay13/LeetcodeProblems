package leetcode.Arrays;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * We are given hours, a list of the number of hours worked per day for a given employee.
 *
 * A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.
 *
 * A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.
 *
 * Return the length of the longest well-performing interval.
 *
 *
 *
 * Example 1:
 *
 * Input: hours = [9,9,6,0,6,6,9]
 * Output: 3
 * Explanation: The longest well-performing interval is [9,9,6].
 * Example 2:
 *
 * Input: hours = [6,6,6]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= hours.length <= 10^4
 * 0 <= hours[i] <= 16
 *
 */

public class _1124_Longest_Well_Performing_Interval {
    // Similar to 525. Contiguous Array
    public int longestWPI(int[] hours) {
        Map<Integer, Integer> sumPosMap = new HashMap<>();
        sumPosMap.put(0, -1);
        int sum = 0;
        int ans = 0;

        for (int i = 0; i < hours.length; i++) {
            sum += hours[i] > 8 ? 1 : -1;

            if (!sumPosMap.containsKey(sum)) {
                sumPosMap.put(sum, i);
            }

            if (sum > 0) {
                ans = i + 1;
            } else if (sumPosMap.containsKey(sum - 1)){ // checking where previous sum exist
                ans = Math.max(ans, i - sumPosMap.get(sum - 1));
            }
        }
        return ans;
    }
}
