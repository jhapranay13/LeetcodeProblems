package leetcode.DP;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * You are given a 0-indexed m x n binary matrix grid. You can move from a cell (row, col) to any of the cells (row + 1, col) or (row, col + 1).
 *
 * Return true if there is a path from (0, 0) to (m - 1, n - 1) that visits an equal number of 0's and 1's. Otherwise return false.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,1,0,0],[0,1,0,0],[1,0,1,0]]
 * Output: true
 * Explanation: The path colored in blue in the above diagram is a valid path because we have 3 cells with a value of 1 and 3 with a value of 0. Since there is a valid path, we return true.
 * Example 2:
 *
 *
 * Input: grid = [[1,1,0],[0,0,1],[1,0,0]]
 * Output: false
 * Explanation: There is no path in this grid with an equal number of 0's and 1's.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 100
 * grid[i][j] is either 0 or 1.
 *
 */

public class _2510_Check_if_There_is_a_Path_With_Equal_Number_of_0s_And_1s {
    public boolean isThereAPath(int[][] grid) {
        Map<String, Boolean> memo = new HashMap<>();
        return recur(grid, 0, 0, 0, memo);
    }
    int[][] dirs = {{0, 1}, {1, 0}};

    private boolean recur(int[][] grid, int r, int c, int sum, Map<String, Boolean> memo) {

        if (r > grid.length - 1 || c > grid[0].length - 1) {
            return false;
        }
        int nextSum = sum + (grid[r][c] == 0 ? -1 : 1);

        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return nextSum == 0;
        }
        String key = r + "|" + c + "|" + sum;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        boolean ans = false;

        for (int[] dir : dirs) {
            ans = recur(grid, r + dir[0], c + dir[1], nextSum, memo);

            if (ans) {
                break;
            }
        }
        memo.put(key, ans);
        return ans;
    }
}
