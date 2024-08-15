package leetcode.medium;

/**
 *
 * Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2. You may assume that multiplication is always possible.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
 * Output: [[7,0,0],[-7,0,3]]
 * Example 2:
 *
 * Input: mat1 = [[0]], mat2 = [[0]]
 * Output: [[0]]
 *
 *
 * Constraints:
 *
 * m == mat1.length
 * k == mat1[i].length == mat2.length
 * n == mat2[i].length
 * 1 <= m, n, k <= 100
 * -100 <= mat1[i][j], mat2[i][j] <= 100
 *
 */

public class _311_Sparse_Matrix_Multiplication {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int n = mat1.length;
        int k = mat1[0].length;
        int m = mat2[0].length;

        // Product matrix will have 'n x m' size.
        int[][] ans = new int[n][m];

        for (int rowIndex = 0; rowIndex < n; ++rowIndex) {

            for (int elementIndex = 0; elementIndex < k; ++elementIndex) {
                // If current element of mat1 is non-zero then iterate over all columns of mat2.
                if (mat1[rowIndex][elementIndex] != 0)  {

                    for (int colIndex = 0; colIndex < m; ++colIndex) {
                        ans[rowIndex][colIndex] += mat1[rowIndex][elementIndex] * mat2[elementIndex][colIndex];
                    }
                }
            }
        }
        return ans;
    }
    //=============================================================================================
    // Another approach
    public int[][] multiply1(int[][] mat1, int[][] mat2) {
        int ans[][] = new int[mat1.length][mat2[0].length];


        for (int r = 0; r < ans.length; r++) {

            for (int c = 0; c < ans[0].length; c++) {

                for (int mc = 0, mr = 0; mc < mat1[0].length && mr < mat2.length; mc++, mr++) {
                    ans[r][c] = ans[r][c] + mat1[r][mc] * mat2[mr][c];
                }
            }
        }
        return ans;
    }
}
