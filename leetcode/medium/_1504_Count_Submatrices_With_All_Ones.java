package leetcode.medium;

/**
 *
 * Given an m x n binary matrix mat, return the number of submatrices that have all ones.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[1,0,1],[1,1,0],[1,1,0]]
 * Output: 13
 * Explanation:
 * There are 6 rectangles of side 1x1.
 * There are 2 rectangles of side 1x2.
 * There are 3 rectangles of side 2x1.
 * There is 1 rectangle of side 2x2.
 * There is 1 rectangle of side 3x1.
 * Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
 * Example 2:
 *
 *
 * Input: mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
 * Output: 24
 * Explanation:
 * There are 8 rectangles of side 1x1.
 * There are 5 rectangles of side 1x2.
 * There are 2 rectangles of side 1x3.
 * There are 4 rectangles of side 2x1.
 * There are 2 rectangles of side 2x2.
 * There are 2 rectangles of side 3x1.
 * There is 1 rectangle of side 3x2.
 * Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 150
 * mat[i][j] is either 0 or 1.
 *
 */

public class _1504_Count_Submatrices_With_All_Ones {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length, res = 0;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                // matrix stores num of consecutive ones from matrix[i][j] first zero on its right
                mat[i][j] = mat[i][j] == 0 ? 0 : mat[i][j] + mat[i][j + 1];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int min = mat[i][j];
                res += min;
                for (int k = i + 1; k < m; k++) {
                    if (mat[k][j] == 0) break;
                    min = Math.min(min, mat[k][j]);
                    res += min;
                }
            }
        }
        return res;
    }
    //=============================================================================================
    // Another approach
    private int haveAllOne(int[][] mat, int i, int j){
        int n = mat.length, m = mat[0].length, count = 0, bound = m;
        for(int x = i; x < n; x ++){
            for(int y = j; y < bound; y++){
                if(mat[x][y] == 1) {
                    count ++;
                }else bound = y;
            }
        }
        return count;
    }
    public int numSubmat1(int[][] mat) {
        int count = 0, n = mat.length, m = mat[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(mat[i][j] == 0) continue;
                count += haveAllOne(mat, i, j);
            }
        }
        return count;
    }

}
