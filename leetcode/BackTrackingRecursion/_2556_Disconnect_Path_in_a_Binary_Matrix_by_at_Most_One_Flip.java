package leetcode.BackTrackingRecursion;

/**
 *
 *
 * You are given a 0-indexed m x n binary matrix grid. You can move from a cell (row, col) to any of the cells (row + 1, col) or (row, col + 1) that has the value 1. The matrix is disconnected if there is no path from (0, 0) to (m - 1, n - 1).
 *
 * You can flip the value of at most one (possibly none) cell. You cannot flip the cells (0, 0) and (m - 1, n - 1).
 *
 * Return true if it is possible to make the matrix disconnect or false otherwise.
 *
 * Note that flipping a cell changes its value from 0 to 1 or from 1 to 0.
 *
 *
 *
 * Example 1:
 *
 *              [1,1,1],
 *              [1,0,0],
 *              [1,1,1]
 *
 *              [1,1,1],
 *  *           [0,0,0],
 *  *           [1,1,1]
 *
 *
 * Input: grid = [[1,1,1],[1,0,0],[1,1,1]]
 * Output: true
 * Explanation: We can change the cell shown in the diagram above. There is no path from (0, 0) to (2, 2) in the resulting grid.
 * Example 2:
 *
 *
 * Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: false
 * Explanation: It is not possible to change at most one cell such that there is not path from (0, 0) to (2, 2).
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 1000
 * 1 <= m * n <= 105
 * grid[i][j] is either 0 or 1.
 * grid[0][0] == grid[m - 1][n - 1] == 1
 *
 */

public class _2556_Disconnect_Path_in_a_Binary_Matrix_by_at_Most_One_Flip {
    // The whole Idea is to make one path unusable.
    // Then check if we can reach it again. If we can reach it again then
    // there is another path and one flip won't be enough
    public boolean isPossibleToCutPath(int[][] grid) {
        boolean firstPath = recur(grid, 0, 0);

        if (!firstPath) {
            return true;
        }
        grid[0][0] = 1; // Setting up start to 1 so that we can start again
        return !recur(grid, 0, 0);
    }
    int[][] dirs = {{0, 1}, {1, 0}};

    private boolean recur(int[][] grid, int r, int c) {

        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return true;
        }

        if  (r == grid.length || c == grid[0].length || grid[r][c] == 0) {
            return false;
        }
        grid[r][c] = 0;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (recur(grid, nr, nc)) {
                return true;
            }
        }
        return false;
    }
}
