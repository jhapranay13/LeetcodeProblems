package leetcode.medium;

import java.util.Arrays;

/**
 *
 *You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.
 *
 * To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.
 *
 * However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
 *
 * Return the maximum number of points you can achieve.
 *
 * abs(x) is defined as:
 *
 * x for x >= 0.
 * -x for x < 0.
 *
 *
 * Example 1:
 *
 *
 * Input: points = [[1,2,3],[1,5,1],[3,1,1]]
 * Output: 9
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
 * You add 3 + 5 + 3 = 11 to your score.
 * However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
 * Your final score is 11 - 2 = 9.
 * Example 2:
 *
 *
 * Input: points = [[1,5],[2,3],[4,2]]
 * Output: 11
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
 * You add 5 + 3 + 4 = 12 to your score.
 * However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
 * Your final score is 12 - 1 = 11.
 *
 *
 * Constraints:
 *
 * m == points.length
 * n == points[r].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * 0 <= points[r][c] <= 105
 *
 */

public class _1937_MaximumNumberOfPointsWithCost {
    public long maxPoints(int[][] points) {
        int n = points[0].length - 1;
        long[] prevRow = new long[points[0].length];

        //Filling memo values with first row
        for (int c = 0; c < points[0].length; c++) {
            prevRow[c] = points[0][c];
        }
        //Using water trapping problem technique. Since the condtion is - y co ordinate
        // So take the maximum and previous - 1 coz we moved one from the current
        //Since current value depends on value from left right and current value
        //the equation becomes points[x1][y1] + points[x2][y2] - abs(y1 - y2);
        //So if the value is coming from left y1 < y2
        //removeing absolute points[x1][y1] + points[x2][y2] - (y2 - y1)
        //grouping (points[x1][y1] + y[1] + points[x2][y2] - y2)
        //So for left this is the equation so for left this should be maximum points[x1][y1] + y[1]
        //Similarly for the right y1 > y2
        //removeing absolute points[x1][y1] + points[x2][y2] - (y1 - y2)
        //(points[x1][y1] - y[1] + points[x2][y2] + y2)
        //So for current x2 and y2 best values of x1 and y1

        for (int r = 1; r < points.length; r++) {
            long[] left = new long[n + 1];
            long[] right = new long[n + 1];
            long[] currRow = new long[n + 1];
            long maxPrevRow = Integer.MIN_VALUE;
            // y1 < y2
            for (int c = 0; c <= n; c++) {
                maxPrevRow = Math.max(maxPrevRow, prevRow[c] + c);
                left[c] = maxPrevRow + points[r][c] - c;
            }
            maxPrevRow = Integer.MIN_VALUE;
            //y1 > y2
            for (int c = n; c >= 0; c--) {
                maxPrevRow = Math.max(maxPrevRow, prevRow[c] - c);
                right[c] = maxPrevRow + points[r][c] + c;
            }

            for (int c = 0; c <= n; c++) {
                currRow[c] = Math.max(left[c], right[c]);
            }
            prevRow = currRow;
        }
        long ans = 0;

        for (int i = 0; i < points[0].length; i++) {
            ans = Math.max(ans, prevRow[i]);
        }
        return ans;
    }
    //=============================================================================================
    //Top down TLE
    public long maxPoints1(int[][] points) {
        long ans = 0;
        long memo[][] = new long[points.length][points[0].length];

        for(long[] rows: memo){
            Arrays.fill(rows,-1L);
        }
        for (int c = 0; c < points[0].length; c++) {
            ans = Math.max(ans, recur(points, 0, c, memo));
        }
        return ans;
    }

    private long recur(int[][] points, int r, int c, long[][] memo) {

        if (r == points.length) {
            return 0;
        }

        if (r == points.length - 1) {
            return points[r][c];
        }

        if (memo[r][c] != -1L) {
            return memo[r][c];
        }
        long ans = 0;

        for (int col = 0; col < points[0].length; col++) {
            long temp = points[r][c] + recur(points, r + 1, col, memo) - Math.abs(c - col);
            ans = Math.max(ans, temp);
        }
        return memo[r][c] = ans;
    }
    //=============================================================================================
    //Bottom up TLE
}
