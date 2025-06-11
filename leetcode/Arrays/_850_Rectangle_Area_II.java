package leetcode.Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * You are given a 2D array of axis-aligned rectangles. Each rectangle[i] = [xi1, yi1, xi2, yi2] denotes the ith rectangle where (xi1, yi1) are the coordinates of the bottom-left corner, and (xi2, yi2) are the coordinates of the top-right corner.
 *
 * Calculate the total area covered by all rectangles in the plane. Any area covered by two or more rectangles should only be counted once.
 *
 * Return the total area. Since the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *                  |
 *                  |
 *                  |
 *                  |
 *               3  |        ----------
 *                  |        |         |
 *                  |        |         |
 *               2  |-------------------
 *                  |        |         |
 *                  |        |         |
 *               1  |        | --------|----------
 *                  |        |         |         |
 *                  |        |         |         |
 *               0  |________|_________|_________|____________
 *                  0        1         2         3         4
 *
 * Input: rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
 * Output: 6
 * Explanation: A total area of 6 is covered by all three rectangles, as illustrated in the picture.
 * From (1,1) to (2,2), the green and red rectangles overlap.
 * From (1,0) to (2,3), all three rectangles overlap.
 * Example 2:
 *
 * Input: rectangles = [[0,0,1000000000,1000000000]]
 * Output: 49
 * Explanation: The answer is 1018 modulo (109 + 7), which is 49.
 *
 *
 * Constraints:
 *
 * 1 <= rectangles.length <= 200
 * rectanges[i].length == 4
 * 0 <= xi1, yi1, xi2, yi2 <= 109
 * xi1 <= xi2
 * yi1 <= yi2
 * All rectangles have non zero area.
 *
 */
public class _850_Rectangle_Area_II {

    final int MOD = 1_000_000_007;

    public int rectangleArea(int[][] rectangles) {
        List<int[]> lines = new ArrayList<>();

        for (int[] rectangle : rectangles) {
            int x1 = rectangle[0];
            int y1 = rectangle[1];
            int x2 = rectangle[2];
            int y2 = rectangle[3];
            lines.add(new int[] {x1, y1, y2, 1});
            lines.add(new int[] {x2, y1, y2, -1});
        }
        lines.sort((a, b) -> a[0] - b[0]);
        List<int[]> activeLines = new ArrayList<>();
        int prevX = lines.get(0)[0];
        long ans = 0;
        // Calculating height between Previous and current X
        for (int[] line : lines) {
            int currX = line[0];
            int y1 = line[1];
            int y2 = line[2];
            int type = line[3];
            long xLength = currX - prevX;
            long yLength = getTotalYLength(activeLines);

            ans += xLength * yLength;
            ans %= MOD;
            if (type == 1) {
                activeLines.add(new int[] {y1, y2});
            } else {

                for (int i = 0; i < activeLines.size(); i++) {
                    // Closing lines
                    if (activeLines.get(i)[0] == y1 && activeLines.get(i)[1] == y2) {
                        activeLines.remove(i);
                        break;
                    }
                }
            }
            prevX = currX;
        }
        return (int)ans;
    }

    // Merge and sum up total union length of active intervals
    private long getTotalYLength(List<int[]> activeLines) {
        if (activeLines.size() == 0) {
            return 0;
        }
        Collections.sort(activeLines, (a, b) -> a[0] - b[0]);
        int[] prevLine = activeLines.get(0);
        int start = prevLine[0];
        int end = prevLine[1];
        int ans = 0;

        for (int i = 1; i < activeLines.size(); i++) {
            int currStart = activeLines.get(i)[0];
            int currEnd = activeLines.get(i)[1];

            if (currStart > end) {
                ans += end - start;
                start = currStart;
                end = currEnd;
            } else {
                end = Math.max(end, currEnd);
            }

        }
        ans += end - start;
        return ans;
    }
}
