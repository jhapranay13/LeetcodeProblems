package leetcode.Arrays;

/**
 *
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 *
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 *
 * Input: matrix = [[1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 *
 */

public class _329_LongestIncreasingPathInAMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        int ans = 0;
        int[][] memo = new int[matrix.length][matrix[0].length];

        for (int r = 0; r < matrix.length; r++) {

            for (int c = 0; c < matrix[0].length; c++) {
                ans = Math.max(ans, recur(matrix, r, c, memo));
            }
        }
        return ans;
    }
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    //No need of visited beacuse we will only go with greater or lesse number so no chace of coming back
    private int recur(int[][] matrix, int r, int c, int[][] memo) {

        if (memo[r][c] > 0) {
            return memo[r][c];
        }
        int ans = 0;

        for (int dir[] : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr < 0 || nc < 0 || nr == matrix.length || nc == matrix[0].length ||
                    matrix[nr][nc] >= matrix[r][c]) {
                continue;
            }
            ans = Math.max(ans, recur(matrix, nr, nc, memo));
        }
        return memo[r][c] = ans + 1;
    }
}
