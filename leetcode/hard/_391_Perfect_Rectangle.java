package leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Given an array rectangles where rectangles[i] = [xi, yi, ai, bi] represents an axis-aligned rectangle. The bottom-left point of the rectangle is (xi, yi) and the top-right point of it is (ai, bi).
 *
 * Return true if all the rectangles together form an exact cover of a rectangular region.
 *
 *
 *
 * Example 1:
 *
 *          |
 *        4 |   _______________
 *          |   |    |    |   |
 *        3 |   |____|____|   |
 *          |   |         |   |
 *        2 |   |         |___|
 *          |   |         |   |
 *        1 |   |---------|---|
 *          |__________________________________
 *              1    2   3   4
 *
 * Input: rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
 * Output: true
 * Explanation: All 5 rectangles together form an exact cover of a rectangular region.
 * Example 2:
 *
 *              |
 *  *        4 |   ______    _____
 *  *          |   |    |    |   |
 *  *        3 |   |____|    |   |
 *  *          |   |    |    |   |
 *  *        2 |   |    |    |___|
 *  *          |   |    |    |   |
 *  *        1 |   |----|    |---|
 *  *          |__________________________________
 *  *              1    2   3   4
 *
 *
 * Input: rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
 * Output: false
 * Explanation: Because there is a gap between the two rectangular regions.
 *
 * Example 3:
 *
 *             |
 *  *        4 |   _______________
 *  *          |   |    |        |
 *  *        3 |   |____|____    |
 *  *          |   |    | ///|   |
 *  *        2 |   |    |////|___|
 *  *          |   |         |   |
 *  *        1 |   |---------|---|
 *  *          |__________________________________
 *  *              1    2   3   4
 *
 * Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
 * Output: false
 * Explanation: Because two of the rectangles overlap with each other.
 *
 *
 * Constraints:
 *
 * 1 <= rectangles.length <= 2 * 10^4
 * rectangles[i].length == 4
 * -10^5 <= xi, yi, ai, bi <= 10^5
 *
 */

public class _391_Perfect_Rectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        Set<String> v = new HashSet<>();
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;
        int area = 0;

        for (int[] rectangle : rectangles) {
            x1 = Math.min(x1, rectangle[0]);
            y1 = Math.min(y1, rectangle[1]);
            x2 = Math.max(x2, rectangle[2]);
            y2 = Math.max(y2, rectangle[3]);

            area += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);

            String key1 = rectangle[0] + " " + rectangle[1];
            String key2 = rectangle[2] + " " + rectangle[3];
            String key3 = rectangle[0] + " " + rectangle[3];
            String key4 = rectangle[2] + " " + rectangle[1];
            //Removing it if it exists since now this will be the somewhere within rectangle
            if (!v.add(key1)) {
                v.remove(key1);
            }

            if (!v.add(key2)) {
                v.remove(key2);
            }

            if (!v.add(key3)) {
                v.remove(key3);
            }

            if (!v.add(key4)) {
                v.remove(key4);
            }
        }
        String key1 = x1 + " " + y1;
        String key2 = x2 + " " + y2;
        String key3 = x1 + " " + y2;
        String key4 = x2 + " " + y1;

        if (v.size() != 4 || !v.contains(key1) || !v.contains(key2) || !v.contains(key3) ||
                !v.contains(key4)) {
            return false;
        }
        return area == (x2-x1) * (y2-y1);
    }
}
