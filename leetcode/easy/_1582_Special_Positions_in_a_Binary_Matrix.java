package leetcode.easy;

/**
 *
 *
 * Given an m x n binary matrix mat, return the number of special positions in mat.
 *
 * A position (i, j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0 (rows and columns are 0-indexed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[1,0,0],[0,0,1],[1,0,0]]
 * Output: 1
 * Explanation: (1, 2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.
 * Example 2:
 *
 *
 * Input: mat = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 * Explanation: (0, 0), (1, 1) and (2, 2) are special positions.
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * mat[i][j] is either 0 or 1.
 *
 *
 *
 */

public class _1582_Special_Positions_in_a_Binary_Matrix {
    public int numSpecial(int[][] mat) {
        int[] rows = new int[mat.length];
        int[] cols = new int[mat[0].length];

        for (int r = 0; r < mat.length; r++) {

            for (int c = 0; c < mat[0].length; c++) {

                if (mat[r][c] == 1) {
                    rows[r]++;
                    cols[c]++;
                }
            }
        }
        int ans = 0;

        for (int r = 0; r < mat.length; r++) {

            for (int c = 0; c < mat[0].length; c++) {

                if (mat[r][c] == 1 && rows[r] == 1 && cols[c] == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
