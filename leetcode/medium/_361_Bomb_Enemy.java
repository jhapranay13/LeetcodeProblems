package leetcode.medium;

/**
 *
 * Given an m x n matrix grid where each cell is either a wall 'W', an enemy 'E' or empty '0', return the maximum enemies you can kill using one bomb. You can only place the bomb in an empty cell.
 *
 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since it is too strong to be destroyed.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
 * Output: 3
 * Example 2:
 *
 *
 * Input: grid = [["W","W","W"],["0","0","0"],["E","E","E"]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] is either 'W', 'E', or '0'.
 *
 */

public class _361_Bomb_Enemy {
    // Can also be done by brute force by checking the row and column for
    // one particular point and getting the answer
    // This approach is keeping track of above column values as they dont change till a wall is hit
    // In which case we recalculate
    public int maxKilledEnemies(char[][] grid) {
        int[] colHit = new int[grid[0].length];
        int ans = 0;

        for (int r = 0; r < grid.length; r++) {
            int rowHit = 0;

            for (int c = 0; c < grid[0].length; c++) {
                // Scanning a particular row
                if (c == 0 || grid[r][c - 1] == 'W') {
                    rowHit = 0;

                    for (int col = c; col < grid[0].length; col++) {

                        if (grid[r][col] == 'W') {
                            break;
                        } else if (grid[r][col] == 'E') {
                            rowHit++;
                        }
                    }
                }
                // Column Scan
                if (r == 0 || grid[r - 1][c] == 'W') {
                    colHit[c] = 0;

                    for (int row = r; row < grid.length; row++) {

                        if (grid[row][c] == 'W') {
                            break;
                        } else if (grid[row][c] == 'E') {
                            colHit[c]++;
                        }
                    }
                }

                if (grid[r][c] == '0') {
                    ans = Math.max(ans, rowHit + colHit[c]);
                }
            }
        }
        return ans;
    }
}
