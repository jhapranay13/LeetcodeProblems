package leetcode.Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * 539. Minimum Time Difference
 * Medium
 *
 * 854
 *
 * 187
 *
 * Add to List
 *
 * Share
 * Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.
 *
 *
 * Example 1:
 *
 * Input: timePoints = ["23:59","00:00"]
 * Output: 1
 * Example 2:
 *
 * Input: timePoints = ["00:00","23:59","00:00"]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 2 <= timePoints.length <= 2 * 104
 * timePoints[i] is in the format "HH:MM".
 *
 */

public class _539_MinimumTimeDifference {

    public int findMinDifference(List<String> timePoints) {
        List<Integer> timeInMinutes = new ArrayList<>();

        for (String time : timePoints) {
            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(3));
            timeInMinutes.add(hour * 60 + min);
        }
        Collections.sort(timeInMinutes);
        int ans = Integer.MAX_VALUE;
        int twentyFourHour = 24 * 60;

        for (int i = 1; i < timeInMinutes.size(); i++) {
            int normal = timeInMinutes.get(i) - timeInMinutes.get(i - 1);
            int circular = twentyFourHour - timeInMinutes.get(i) + timeInMinutes.get(i - 1);
            int temp = Math.min(normal, circular);
            ans = Math.min(ans, temp);
        }
        ans = Math.min(ans, twentyFourHour -
                timeInMinutes.get(timeInMinutes.size() - 1) + timeInMinutes.get(0));
        return ans;
    }
}
