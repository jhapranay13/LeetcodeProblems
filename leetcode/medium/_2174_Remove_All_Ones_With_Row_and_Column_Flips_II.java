package leetcode.medium;

/**
 *
 * You are given a 0-indexed m x n binary matrix grid.
 *
 * In one operation, you can choose any i and j that meet the following conditions:
 *
 * 0 <= i < m
 * 0 <= j < n
 * grid[i][j] == 1
 * and change the values of all cells in row i and column j to zero.
 *
 * Return the minimum number of operations needed to remove all 1's from grid.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,1,1],[1,1,1],[0,1,0]]
 * Output: 2
 * Explanation:
 * In the first operation, change all cell values of row 1 and column 1 to zero.
 * In the second operation, change all cell values of row 0 and column 0 to zero.
 * Example 2:
 *
 *
 * Input: grid = [[0,1,0],[1,0,1],[0,1,0]]
 * Output: 2
 * Explanation:
 * In the first operation, change all cell values of row 1 and column 0 to zero.
 * In the second operation, change all cell values of row 2 and column 1 to zero.
 * Note that we cannot perform an operation using row 1 and column 1 because grid[1][1] != 1.
 * Example 3:
 *
 *
 * Input: grid = [[0,0],[0,0]]
 * Output: 0
 * Explanation:
 * There are no 1's to remove so return 0.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 15
 * 1 <= m * n <= 15
 * grid[i][j] is either 0 or 1.
 *
 */

public class _2174_Remove_All_Ones_With_Row_and_Column_Flips_II {
    public int removeOnes(int[][] grid) {
        return recur(grid);
    }

    private int recur(int[][] grid) {

        if (isEmpty(grid)) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {

                if (grid[r][c] == 1) {
                    int[] row = new int[grid[0].length];
                    int[] col = new int[grid.length];

                    for (int i = 0; i < grid[0].length; i++) {
                        row[i] = grid[r][i];
                        grid[r][i] = 0;
                    }

                    for (int j = 0; j < grid.length; j++) {
                        col[j] = grid[j][c];
                        grid[j][c] = 0;
                    }
                    int temp = 1 + recur(grid);
                    ans = Math.min(ans, temp);
                    //first col then row coz it will override the one with 0 otherwise
                    for (int j = 0; j < grid.length; j++) {
                        grid[j][c] = col[j];
                    }

                    for (int i = 0; i < grid[0].length; i++) {
                        grid[r][i] = row[i];
                    }
                }
            }
        }
        return ans;
    }

    private boolean isEmpty(int[][] grid) {
        boolean isEmpty = true;

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {

                if (grid[r][c] == 1) {
                    isEmpty = false;
                    break;
                }
            }
        }
        return isEmpty;
    }
}
