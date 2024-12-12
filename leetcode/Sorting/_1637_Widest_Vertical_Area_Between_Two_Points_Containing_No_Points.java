package leetcode.Sorting;

import java.util.Arrays;

/**
 *
 *
 * Given n points on a 2D plane where points[i] = [xi, yi], Return the widest vertical area between two points such that no points are inside the area.
 *
 * A vertical area is an area of fixed-width extending infinitely along the y-axis (i.e., infinite height). The widest vertical area is the one with the maximum width.
 *
 * Note that points on the edge of a vertical area are not considered included in the area.
 *
 *
 *
 * Example 1:
 *                      _____
 *          10         |  |  |
 *          9          |  |  O
 *          9          |  |  |
 *          7          |  O  O
 *          6          |  |  |
 *          5          |  |  |
 *          4          O  |  |
 *                     |  |  |
 *                  6  7  8  9  10
 *
 *
 * Input: points = [[8,7],[9,9],[7,4],[9,7]]
 * Output: 1
 * Explanation: Both the red and the blue area are optimal.
 * Example 2:
 *
 * Input: points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * n == points.length
 * 2 <= n <= 10^5
 * points[i].length == 2
 * 0 <= xi, yi <= 10^9
 *
 */

public class _1637_Widest_Vertical_Area_Between_Two_Points_Containing_No_Points {
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, (arr1, arr2) -> {
            return arr1[0] - arr2[0];
        });
        int ans = 0;

        for (int i = 1; i < points.length; i++) {
            ans = Math.max(points[i][0] - points[i - 1][0], ans);
        }
        return ans;
    }
}
