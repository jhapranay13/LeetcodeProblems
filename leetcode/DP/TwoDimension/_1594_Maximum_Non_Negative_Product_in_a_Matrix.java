package leetcode.DP.TwoDimension;

/**
 *
 * You are given a m x n matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step, you can only move right or down in the matrix.
 *
 * Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner (m - 1, n - 1), find the path with the maximum non-negative product. The product of a path is the product of all integers in the grid cells visited along the path.
 *
 * Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative, return -1.
 *
 * Notice that the modulo is performed after getting the maximum product.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
 * Output: -1
 * Explanation: It is not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.
 * Example 2:
 *
 *
 * Input: grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
 * Output: 8
 * Explanation: Maximum non-negative product is shown (1 * 1 * -2 * -4 * 1 = 8).
 * Example 3:
 *
 *
 * Input: grid = [[1,3],[0,-4]]
 * Output: 0
 * Explanation: Maximum non-negative product is shown (1 * 0 * -4 = 0).
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 15
 * -4 <= grid[i][j] <= 4
 *
 */

public class _1594_Maximum_Non_Negative_Product_in_a_Matrix {
    // Top Down Approach
    public int maxProductPath(int[][] grid) {
        Long memo[][][] = new Long[grid.length][grid[0].length][];
        Long[] result = recur(grid, 0, 0, memo);
        int max = (int)(result[1] % MOD);
        return max < 0 ? -1 : max;
    }
    int[][] dirs = {{0, 1}, {1, 0}};
    private long MOD = (long) Math.pow(10, 9) + 7;

    private Long[] recur(int[][] grid, int row, int col, Long[][][] memo) {

        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return new Long[] {(long)grid[row][col], (long)grid[row][col]};
        }

        if (memo[row][col] != null) {
            return memo[row][col];
        }
        long min = Integer.MAX_VALUE;
        long max = Integer.MIN_VALUE;
        int currVal = grid[row][col];

        for (int[] dir : dirs) {
            int nr = row + dir[0];
            int nc = col + dir[1];

            if (nr >= grid.length || nc >= grid[0].length) {
                continue;
            }
            Long[] result = recur(grid, nr, nc, memo);
            min = Math.min(min, Math.min(result[0] * currVal, result[1] * currVal));
            max = Math.max(max, Math.max(result[0] * currVal, result[1] * currVal));
        }
        return memo[row][col] = new Long[] {min, max};
    }
    //=============================================================================================
    // Bottom up approach
    public int maxProductPath1(int[][] grid) {
        Long memo[][][] = new Long[grid.length][grid[0].length][];

        for (int row = grid.length - 1; row >= 0; row--) {

            for (int col = grid[0].length - 1; col >= 0; col--) {

                if (row == grid.length - 1 && col == grid[0].length - 1) {
                    memo[row][col] = new Long[] {(long)grid[row][col], (long)grid[row][col]};
                    continue;
                }

                long min = Integer.MAX_VALUE;
                long max = Integer.MIN_VALUE;
                int currVal = grid[row][col];

                for (int[] dir : dirs) {
                    int nr = row + dir[0];
                    int nc = col + dir[1];

                    if (nr >= grid.length || nc >= grid[0].length) {
                        continue;
                    }
                    Long[] result = memo[nr][nc];
                    min = Math.min(min, Math.min(result[0] * currVal, result[1] * currVal));
                    max = Math.max(max, Math.max(result[0] * currVal, result[1] * currVal));
                }
                memo[row][col] = new Long[] {min, max};
            }
        }
        Long[] result = memo[0][0];
        int max = (int)(result[1] % MOD);
        return max < 0 ? -1 : max;
    }
}
