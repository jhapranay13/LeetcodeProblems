package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given n rectangles represented by a 0-indexed 2D integer array rectangles, where rectangles[i] = [widthi, heighti] denotes the width and height of the ith rectangle.
 *
 * Two rectangles i and j (i < j) are considered interchangeable if they have the same width-to-height ratio. More formally, two rectangles are interchangeable if widthi/heighti == widthj/heightj (using decimal division, not integer division).
 *
 * Return the number of pairs of interchangeable rectangles in rectangles.
 *
 *
 *
 * Example 1:
 *
 * Input: rectangles = [[4,8],[3,6],[10,20],[15,30]]
 * Output: 6
 * Explanation: The following are the interchangeable pairs of rectangles by index (0-indexed):
 * - Rectangle 0 with rectangle 1: 4/8 == 3/6.
 * - Rectangle 0 with rectangle 2: 4/8 == 10/20.
 * - Rectangle 0 with rectangle 3: 4/8 == 15/30.
 * - Rectangle 1 with rectangle 2: 3/6 == 10/20.
 * - Rectangle 1 with rectangle 3: 3/6 == 15/30.
 * - Rectangle 2 with rectangle 3: 10/20 == 15/30.
 * Example 2:
 *
 * Input: rectangles = [[4,5],[7,8]]
 * Output: 0
 * Explanation: There are no interchangeable pairs of rectangles.
 *
 *
 * Constraints:
 *
 * n == rectangles.length
 * 1 <= n <= 10^5
 * rectangles[i].length == 2
 * 1 <= widthi, heighti <= 10^5
 *
 *
 */

public class _2001_Number_of_Pairs_of_Interchangeable_Rectangles {

    public long interchangeableRectangles(int[][] rectangles) {
        Map<Double, Long> countMap = new HashMap<>();

        for (int[] rect : rectangles) {
            double ratio = (double)rect[0] / rect[1];
            countMap.put(ratio, countMap.getOrDefault(ratio, 0L) + 1L);
        }
        long ans = 0;

        for (Map.Entry<Double, Long> entry : countMap.entrySet()) {
            Long count = entry.getValue();
            ans += (long)( count * (count - 1) ) / 2;
        }
        return ans;
    }
}
