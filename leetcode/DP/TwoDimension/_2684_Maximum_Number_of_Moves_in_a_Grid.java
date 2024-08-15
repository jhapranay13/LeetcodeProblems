package leetcode.DP.TwoDimension;

/**
 *
 *
 * You are given a 0-indexed m x n matrix grid consisting of positive integers.
 *
 * You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
 *
 * From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
 * Return the maximum number of moves that you can perform.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
 * Output: 3
 * Explanation: We can start at the cell (0, 0) and make the following moves:
 * - (0, 0) -> (0, 1).
 * - (0, 1) -> (1, 2).
 * - (1, 2) -> (2, 3).
 * It can be shown that it is the maximum number of moves that can be made.
 * Example 2:
 *
 *
 * Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
 * Output: 0
 * Explanation: Starting from any cell in the first column we cannot perform any moves.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 10^5
 * 1 <= grid[i][j] <= 10^6
 *
 *
 *
 */

public class _2684_Maximum_Number_of_Moves_in_a_Grid {
    public int maxMoves(int[][] grid) {
        int ans = 0;
        Integer[][] memo = new Integer[grid.length][grid[0].length];

        for (int r = 0; r < grid.length; r++) {
            ans = Math.max(ans, recur(grid, r, 0, memo));
        }
        return ans;
    }
    private int dirs[][] = {{0, 1}, {1, 1}, {-1, 1}};

    private int recur(int[][] grid, int r, int c, Integer[][] memo) {
        // Since it is getting added in the previous step
        // So moving outside the matric will add one more move
        if (c == grid[0].length - 1) {
            return 0;
        }

        if (memo[r][c] != null) {
            return memo[r][c];
        }
        int ans = 0;
        int val = grid[r][c];

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nr < grid.length && grid[nr][nc] > grid[r][c]) {
                ans = Math.max(ans, 1 + recur(grid, nr, nc, memo));
            }
        }
        return memo[r][c] = ans;
    }
}
