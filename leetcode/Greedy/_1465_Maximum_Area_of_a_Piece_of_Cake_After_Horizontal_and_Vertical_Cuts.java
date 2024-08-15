package leetcode.Greedy;

import java.util.Arrays;

/**
 *
 * You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:
 *
 * horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
 * verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a large number, return this modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *            0 0_____1_____2____3____4____5
 *            1 |_____|_____|____|____|____|
 *            2 |_____|_____|____|____|____|
 *            3 |_____|//////////|____|____|
 *            4 |_____|//////////|____|____|
 *            5 |_____|_____|____|____|____|
 *
 * Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * Output: 4
 * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
 * Example 2:
 *
 *               0 0_____1_____2____3____4
 *  *            1 |_____|_____|____|____|
 *  *            2 |_____|///////////////|
 *  *            3 |_____|///////////////|
 *  *            4 |_____|_____|____|____|
 *  *            5 |_____|_____|____|____|
 *
 * Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
 * Output: 6
 * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
 * Example 3:
 *
 * Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
 * Output: 9
 *
 *
 * Constraints:
 *
 * 2 <= h, w <= 10^9
 * 1 <= horizontalCuts.length <= min(h - 1, 10^5)
 * 1 <= verticalCuts.length <= min(w - 1, 10^5)
 * 1 <= horizontalCuts[i] < h
 * 1 <= verticalCuts[i] < w
 * All the elements in horizontalCuts are distinct.
 * All the elements in verticalCuts are distinct.
 *
 */

public class _1465_Maximum_Area_of_a_Piece_of_Cake_After_Horizontal_and_Vertical_Cuts {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        long maxHorizontal = 0;

        for (int i = 0; i < horizontalCuts.length; i++) {

            if (i == 0) {
                maxHorizontal = Math.max(maxHorizontal, horizontalCuts[i] - 0);
            } else {
                maxHorizontal = Math.max(maxHorizontal, horizontalCuts[i] - horizontalCuts[i - 1]);
            }
        }
        maxHorizontal = Math.max(maxHorizontal, h - horizontalCuts[horizontalCuts.length - 1]);
        long maxVertical = 0;

        for (int i = 0; i < verticalCuts.length; i++) {

            if (i == 0) {
                maxVertical = Math.max(maxVertical, verticalCuts[i] - 0);
            } else {
                maxVertical = Math.max(maxVertical, verticalCuts[i] - verticalCuts[i - 1]);
            }
        }
        maxVertical = Math.max(maxVertical, w - verticalCuts[verticalCuts.length - 1]);

        return (int) ((maxVertical * maxHorizontal) % (1000000007));
    }
}
