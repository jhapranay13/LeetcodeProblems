package leetcode.hard;

/**
 *
 * Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.
 *
 * A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements chosen in adjacent rows are in the same column.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 13
 * Explanation:
 * The possible falling paths are:
 * [1,5,9], [1,5,7], [1,6,7], [1,6,8],
 * [2,4,8], [2,4,9], [2,6,7], [2,6,8],
 * [3,4,8], [3,4,9], [3,5,7], [3,5,9]
 * The falling path with the smallest sum is [1,5,7], so the answer is 13.
 * Example 2:
 *
 * Input: grid = [[7]]
 * Output: 7
 *
 *
 * Constraints:
 *
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * -99 <= grid[i][j] <= 99
 *
 */

public class _1289_Minimum_Falling_Path_Sum_II {
    public int minFallingPathSum(int[][] grid) {
        int ans = Integer.MAX_VALUE;
        Integer[][] memo = new Integer[grid.length][grid[0].length];

        for (int col = 0; col < grid.length; col++) {
            ans = Math.min(ans, recur(grid, 0, col, memo));
        }
        return ans;
    }

    private int recur(int[][] grid, int row, int col, Integer[][] memo) {

        if (row == grid.length - 1) {
            return grid[row][col];
        }

        if (memo[row][col] != null) {
            return memo[row][col];
        }
        int ans = Integer.MAX_VALUE;

        for (int c = 0; c < grid[0].length; c++) {

            if (c != col) {
                int temp = grid[row][col] + recur(grid, row + 1, c, memo);
                ans = Math.min(ans, temp);
            }
        }
        return memo[row][col] = ans;
    }
}
