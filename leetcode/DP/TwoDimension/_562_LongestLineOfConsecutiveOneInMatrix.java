package leetcode.DP.TwoDimension;

/**
 *
 * Given an m x n binary matrix mat, return the length of the longest line of consecutive one in the matrix.
 *
 * The line could be horizontal, vertical, diagonal, or anti-diagonal.
 *
 *
 *
 * Example 1:
 *               [0,|1|,1,0]
 *               [0,1,|1|,0]
 *               [0,0,0,|1|]
 *
 * Input: mat = [[0,1,1,0],[0,1,1,0],[0,0,0,1]]
 * Output: 3
 * Example 2:
 *              [|1|,|1|,|1|,|1|]
 *              [0,1,1,0]
 *              [0,0,0,1]
 *
 * Input: mat = [[1,1,1,1],[0,1,1,0],[0,0,0,1]]
 * Output: 4
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 *
 */

public class _562_LongestLineOfConsecutiveOneInMatrix {
    //=============================================================================================
    //Top Down Approach
    public int longestLine(int[][] mat) {
        int ans = 0;
        int[][][] memo = new int[mat.length][mat[0].length][4];

        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[0].length; c++) {

                for (int dir = 0; dir < dirs.length; dir++) {
                    ans = Math.max(ans, recur(mat, r, c, dir, memo));
                }
            }
        }
        return ans;
    }
    //down left right diagnoal anti daigonal
    private int[][] dirs = {{1, 0}, {0, 1}, {1, 1}, {1, -1}};

    private int recur(int[][] mat, int r, int c, int dir, int[][][] memo) {
        if (r < 0 || c < 0 || r == mat.length || c == mat[0].length || mat[r][c] == 0) {
            return 0;
        }

        if (memo[r][c][dir] > 0) {
            return memo[r][c][dir];
        }
        int nr = r + dirs[dir][0];
        int nc = c + dirs[dir][1];
        return memo[r][c][dir] = 1 + recur(mat, nr, nc, dir, memo);
    }
    //=============================================================================================
    //Bottom up Approach
    public int longestLine1(int[][] mat) {
        int ans = 0;
        int[][][] memo = new int[mat.length][mat[0].length][4];

        for (int r = mat.length - 1; r >= 0; r--) {
            for (int c = mat[0].length - 1; c >= 0; c--) {

                for (int dir = 0; dir < dirs.length; dir++) {

                    if (mat[r][c] == 0) {
                        continue;
                    }
                    int nr = r + dirs[dir][0];
                    int nc = c + dirs[dir][1];

                    if (nr < 0 || nc < 0 || nr == mat.length || nc == mat[0].length ||
                            mat[nr][nc] == 0) {
                        memo[r][c][dir] = 1;
                        ans = Math.max(ans, memo[r][c][dir]);
                        continue;
                    }
                    memo[r][c][dir] = 1 + memo[nr][nc][dir];
                    ans = Math.max(ans, memo[r][c][dir]);
                }
            }
        }
        return ans;
    }
}
