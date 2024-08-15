package leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You are given a 0-indexed 2D integer array peaks where peaks[i] = [xi, yi] states that mountain i has a peak at coordinates (xi, yi). A mountain can be described as a right-angled isosceles triangle, with its base along the x-axis and a right angle at its peak. More formally, the gradients of ascending and descending the mountain are 1 and -1 respectively.
 *
 * A mountain is considered visible if its peak does not lie within another mountain (including the border of other mountains).
 *
 * Return the number of visible mountains.
 *
 *
 *
 * Example 1:
 *                         /\
 *                        /   \
 *                  / \ /   /  \
 *                 /  / \ /     \
 *
 *
 *
 * Input: peaks = [[2,2],[6,3],[5,4]]
 * Output: 2
 * Explanation: The diagram above shows the mountains.
 * - Mountain 0 is visible since its peak does not lie within another mountain or its sides.
 * - Mountain 1 is not visible since its peak lies within the side of mountain 2.
 * - Mountain 2 is visible since its peak does not lie within another mountain or its sides.
 * There are 2 mountains that are visible.
 *
 * Example 2:
 *                      /\
 *                     /  \
 *                   /     \
 *                 /        \
 *
 * Input: peaks = [[1,3],[1,3]]
 * Output: 0
 * Explanation: The diagram above shows the mountains (they completely overlap).
 * Both mountains are not visible since their peaks lie within each other.
 *
 *
 * Constraints:
 *
 * 1 <= peaks.length <= 10^5
 * peaks[i].length == 2
 * 1 <= xi, yi <= 10^5
 *
 *
 */

public class _2345_Finding_the_Number_of_Visible_Mountains {
    public int visibleMountains(int[][] peaks) {
        int[][] startEnd = new int[peaks.length][2];

        for (int i = 0; i < peaks.length; i++) {
            int[] peak = peaks[i];
            int x = peak[0];
            int y = peak[1];
            int leftDistance = x - y;
            int rightDistance = x + y;
            startEnd[i] = new int[] {leftDistance, rightDistance};
        }
        Set<String> invisible = new HashSet<>();
        Arrays.sort(startEnd, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int prev[] = startEnd[0];

        for (int i = 1; i < startEnd.length; i++) {
            int curr[] = startEnd[i];

            if (prev[0] <= curr[0]) {

                if (prev[1] >= curr[1]) {
                    String key = curr[0] + "," + curr[1];
                    invisible.add(key);
                    continue;
                }
            }

            if (prev[1] >= curr[1]) {
                String key = curr[0] + "," + curr[1];
                invisible.add(key);
                continue;
            }

            if (curr[1] > prev[1]) {
                prev = curr;
            }
        }
        int ans = peaks.length;

        for (int[] distance : startEnd) {
            String key = distance[0] + "," + distance[1];

            if (invisible.contains(key)) {
                ans--;
            }
        }
        return ans;
    }}
