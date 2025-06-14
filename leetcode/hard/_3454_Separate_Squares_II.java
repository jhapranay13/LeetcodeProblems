package leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * You are given a 2D integer array squares. Each squares[i] = [xi, yi, li] represents the coordinates of the bottom-left point and the side length of a square parallel to the x-axis.
 *
 * Find the minimum y-coordinate value of a horizontal line such that the total area covered by squares above the line equals the total area covered by squares below the line.
 *
 * Answers within 10-5 of the actual answer will be accepted.
 *
 * Note: Squares may overlap. Overlapping areas should be counted only once in this version.
 *
 *
 *
 * Example 1:
 *
 * Input: squares = [[0,0,1],[2,2,1]]
 *
 * Output: 1.00000
 *
 * Explanation:
 *
 *              |
 *            3 |             ______
 *              |            |     |
 *            2 |            |_____|
 *              |
 *            1 |____________________________   y = 1
 *              |     |
 *              |_____|__________________________
 *                    1      2      3
 *
 * Any horizontal line between y = 1 and y = 2 results in an equal split, with 1 square unit above and 1 square unit below. The minimum y-value is 1.
 *
 * Example 2:
 *
 *              |
 *            3 |
 *              |
 *            2 |_____________
 *              |     |      |
 *            1 |_____|______|_______________________ y = 1
 *              |            |
 *              |____________|___________________
 *                    1      2      3
 *
 * Input: squares = [[0,0,2],[1,1,1]]
 *
 * Output: 1.00000
 *
 * Explanation:
 *
 *
 *
 * Since the blue square overlaps with the red square, it will not be counted again. Thus, the line y = 1 splits the squares into two equal parts.
 *
 *
 *
 * Constraints:
 *
 * 1 <= squares.length <= 5 * 10^4
 * squares[i] = [xi, yi, li]
 * squares[i].length == 3
 * 0 <= xi, yi <= 10^9
 * 1 <= li <= 10^9
 * The total area of all the squares will not exceed 10^15.
 *
 */
public class _3454_Separate_Squares_II {

    //=============================================================================================
    // TLE but right concept
    public double separateSquares(int[][] squares) {
        return minLineY(squares);
    }

    private double minLineY(int[][] squares) {
        double lo = Integer.MAX_VALUE;
        double hi = Integer.MIN_VALUE;

        for (int[] square : squares) {
            int y1 = square[1];
            int l = square[2];
            int y2 = y1 + l;
            lo = Math.min(lo, y1);
            hi = Math.max(hi, y2);
        }

        while (1e-6 < hi - lo) {
            double pivot = lo + (hi - lo) / 2.0;

            double areaAbove = getArea(squares, pivot, true);
            double areaBelow = getArea(squares, pivot, false);

            if (areaAbove > areaBelow) {
                lo = pivot;
            } else {
                hi = pivot;
            }
        }
        return lo;
    }

    private double getArea(int[][] squares, double line, boolean isAbove) {
        List<double[]> lines = new ArrayList<>();
        double ans = 0.0;

        for (int[] square : squares) {
            int x1 = square[0];
            int y1 = square[1];
            int l = square[2];
            int y2 = y1 + l;
            int x2 = x1 + l;

            double top;
            double bottom;

            if (isAbove) {
                top =  y2;
                bottom = Math.max(line, y1);
            } else {
                top = Math.min(line, y2);
                bottom = y1;
            }

            if (top > bottom) {
                // 1 signifies open -1 close
                lines.add(new double[] {x1, bottom, top, 1});
                lines.add(new double[] {x2, bottom, top, -1});
            }
        }
        lines.sort((a, b) -> {

            if (a[0] == b[0]) {
                return Double.compare(b[3], a[3]); // open process first
            }
            return Double.compare(a[0], b[0]);
        });
        TreeMap<Double, Double> activeLines = new TreeMap<>();
        Double prevX = lines.get(0)[0];

        for (double[] curr : lines) {
            double x = curr[0];
            double y1 = curr[1];
            double y2 = curr[2];
            double openOrClose = curr[3];
            double stripLength =  x - prevX;

            if (stripLength > 0) {
                int overlapCount = 0;
                double unionLength = 0.0;
                Double prevY = null;

                for (Map.Entry<Double, Double> entry : activeLines.entrySet()) {
                    double openCloseCount = entry.getValue();
                    double currY = entry.getKey();

                    if (overlapCount > 0 && prevY != null) {
                        unionLength += currY - prevY;
                    }
                    prevY = currY;
                    overlapCount += openCloseCount;
                }
                ans += stripLength * unionLength;

            }
            activeLines.put(y1, activeLines.getOrDefault(y1, 0.0) + openOrClose);
            activeLines.put(y2, activeLines.getOrDefault(y2, 0.0) - openOrClose);
            activeLines.entrySet().removeIf(entry -> entry.getValue() == 0);
            prevX = x;
        }
        return ans;
    }
}
