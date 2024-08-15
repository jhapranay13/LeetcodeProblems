package leetcode.hard;

/**
 *
 * You are given an m x n binary matrix grid where each cell is either 0 (empty) or 1 (occupied).
 *
 * You are then given stamps of size stampHeight x stampWidth. We want to fit the stamps such that they follow the given restrictions and requirements:
 *
 * Cover all the empty cells.
 * Do not cover any of the occupied cells.
 * We can put as many stamps as we want.
 * Stamps can overlap with each other.
 * Stamps are not allowed to be rotated.
 * Stamps must stay completely inside the grid.
 * Return true if it is possible to fit the stamps while following the given restrictions and requirements. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
 * Output: true
 * Explanation: We have two overlapping stamps (labeled 1 and 2 in the image) that are able to cover all the empty cells.
 * Example 2:
 *
 *
 * Input: grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
 * Output: false
 * Explanation: There is no way to fit the stamps onto all the empty cells without the stamps going outside the grid.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[r].length
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 2 * 10^5
 * grid[r][c] is either 0 or 1.
 * 1 <= stampHeight, stampWidth <= 10^5
 *
 */

public class _2132_Stamping_the_Grid {
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int[][] presum = getPresum(grid);
        int[][] stamp = new int[grid.length][grid[0].length];

        for (int r = stampHeight - 1; r < grid.length; r++) {

            for (int c = stampWidth - 1; c < grid[0].length; c++) {
                int rangeQuery = rangeSum(presum, r - stampHeight + 1, c - stampWidth + 1, r, c);

                if (rangeQuery == 0) {
                    stamp[r][c] = 1;
                }
            }
        }
        presum = getPresum(stamp);

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {

                if (grid[r][c] == 0) {
                    int r2 = Math.min(grid.length - 1, r + stampHeight - 1);
                    int c2 = Math.min(grid[0].length - 1, c + stampWidth - 1);
                    int rangeQuery = rangeSum(presum, r, c, r2, c2);

                    if (rangeQuery == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private int rangeSum(int[][] presum, int r1, int c1, int r2, int c2) {
        return presum[r2 + 1][c2 + 1] - presum[r1][c2 + 1] - presum[r2 + 1][c1] + presum[r1][c1];
    }

    private int[][] getPresum(int[][] grid) {
        int[][] presum = new int[grid.length + 1][grid[0].length + 1];

        for (int r = 0; r < grid.length; r++) {
            int prefix = 0;

            for (int c = 0; c < grid[0].length; c++) {
                prefix += grid[r][c];
                presum[r + 1][c + 1] += prefix + presum[r][c + 1];
            }
        }
        return presum;
    }
    /**
     *
     * Different approach
     *
     * public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
     *         int[][] presum = getPresum(grid);
     *         int[][] holder = new int[grid.length][grid[0].length];
     *
     *         for (int r = stampHeight - 1; r < grid.length; r++) {
     *
     *             for (int c = stampWidth - 1; c < grid[0].length; c++) {
     *                 int sr = r - (stampHeight - 1);
     *                 int sc = c - (stampWidth - 1);
     *
     *                 if (getSum(presum, sr, sc, r, c) == 0) {
     *                     holder[r][c] = 1;
     *                 }
     *             }
     *         }
     *         presum = getPresum(holder);
     *
     *         for (int r = 0; r < grid.length; r++) {
     *
     *             for (int c = 0; c < grid[0].length; c++) {
     *
     *                 if (grid[r][c] == 0) {
     *                     int r2 = Math.min(grid.length - 1, r + stampHeight - 1);
     *                     int c2 = Math.min(grid[0].length - 1, c + stampWidth - 1);
     *                     int rangeQuery = getSum(presum, r, c, r2, c2);
     *
     *                     if (rangeQuery == 0) {
     *                         return false;
     *                     }
     *                 }
     *             }
     *         }
     *         return true;
     *     }
     *
     *     private int getSum(int[][] presum, int sr, int sc, int er, int ec) {
     *         int ans = presum[er][ec];
     *
     *         if (sr > 0) {
     *             ans -= presum[sr - 1][ec];
     *         }
     *
     *         if (sc > 0) {
     *             ans -= presum[er][sc - 1];
     *         }
     *
     *         if (sr > 0 && sc > 0) {
     *             ans += presum[sr - 1][sc - 1];
     *         }
     *         return ans;
     *     }
     *
     *     private int[][] getPresum(int[][] grid) {
     *         int[][] presum = new int[grid.length][grid[0].length];
     *
     *         for (int r = 0; r < grid.length; r++) {
     *
     *             for (int c = 0; c < grid[0].length; c++) {
     *
     *                 if (r > 0) {
     *                     presum[r][c] += presum[r - 1][c];
     *                 }
     *
     *                 if (c > 0) {
     *                     presum[r][c] += presum[r][c - 1];
     *                 }
     *
     *                 if (r > 0 && c > 0) {
     *                     presum[r][c] -= presum[r - 1][c - 1];
     *                 }
     *                 presum[r][c] += grid[r][c];
     *             }
     *         }
     *         return presum;
     *     }
     */
}
