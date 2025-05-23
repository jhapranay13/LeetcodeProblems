package leetcode.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
 *
 * In one shift operation:
 *
 * Element at grid[i][j] moves to grid[i][j + 1].
 * Element at grid[i][n - 1] moves to grid[i + 1][0].
 * Element at grid[m - 1][n - 1] moves to grid[0][0].
 * Return the 2D grid after applying shift operation k times.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * Output: [[9,1,2],[3,4,5],[6,7,8]]
 * Example 2:
 *
 *
 * Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 * Example 3:
 *
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * Output: [[1,2,3],[4,5,6],[7,8,9]]
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 50
 * 1 <= n <= 50
 * -1000 <= grid[i][j] <= 1000
 * 0 <= k <= 100
 *
 */

public class _1260_Shift_2D_Grid {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int numCols = grid[0].length;
        int numRows = grid.length;

        // Setup the 2d list.
        List<List<Integer>> ans = new ArrayList<>();

        for (int row = 0; row < numRows; row++) {
            List<Integer> newRow = new ArrayList<>();
            ans.add(newRow);

            for (int col = 0; col < numCols; col++) {
                newRow.add(0);
            }
        }
        k %= (numRows * numCols);

        for (int r = 0; r < numRows; r++) {

            for (int c = 0; c < numCols; c++) {
                int id = r * numCols + c;
                int idx = (id + k) % (numRows * numCols);
                int row = idx / numCols;
                int col = idx % numCols;
                ans.get(row).set(col, grid[r][c]);
            }
        }
        return ans;
    }
}
