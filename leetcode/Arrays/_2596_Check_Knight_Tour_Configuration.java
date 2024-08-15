package leetcode.Arrays;

/**
 *
 *
 * There is a knight on an n x n chessboard. In a valid configuration, the knight starts at the top-left cell of the board and visits every cell on the board exactly once.
 *
 * You are given an n x n integer matrix grid consisting of distinct integers from the range [0, n * n - 1] where grid[row][col] indicates that the cell (row, col) is the grid[row][col]th cell that the knight visited. The moves are 0-indexed.
 *
 * Return true if grid represents a valid configuration of the knight's movements or false otherwise.
 *
 * Note that a valid knight move consists of moving two squares vertically and one square horizontally, or two squares horizontally and one square vertically. The figure below illustrates all the possible eight moves of a knight from some cell.
 *
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]
 * Output: true
 * Explanation: The above diagram represents the grid. It can be shown that it is a valid configuration.
 * Example 2:
 *
 *
 * Input: grid = [[0,3,6],[5,8,1],[2,7,4]]
 * Output: false
 * Explanation: The above diagram represents the grid. The 8th move of the knight is not valid considering its position after the 7th move.
 *
 *
 * Constraints:
 *
 * n == grid.length == grid[i].length
 * 3 <= n <= 7
 * 0 <= grid[row][col] < n * n
 * All integers in grid are unique.
 *
 *
 */

public class _2596_Check_Knight_Tour_Configuration {
    public boolean checkValidGrid(int[][] grid) {
        int[][] dirs = {{2, 1}, {-2, 1}, {-2, -1}, {2, -1},
                {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};
        int count = grid.length * grid[0].length - 1;
        int r = 0;
        int c = 0;

        while (count > 0) {
            boolean found = false;
            int currVal = grid[r][c];

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nc < 0 || nr >= grid.length ||
                        nc >= grid[0].length || grid[nr][nc] != currVal + 1) {
                    continue;
                }
                found = true;
                r = nr;
                c = nc;
                break;
            }

            if (!found) {
                break;
            }
            count--;
        }
        return count == 0;
    }
}
