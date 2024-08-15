package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a m x n matrix mat and an integer k, return a matrix answer
 *         where each answer[i][j] is the sum of all elements mat[r][c] for:
 * 
 *         i - k <= r <= i + k, j - k <= c <= j + k, and (r, c) is a valid
 *         position in the matrix.
 * 
 * 
 *         Example 1:
 * 
 *         Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1 Output:
 *         [[12,21,16],[27,45,33],[24,39,28]]
 * 
 *         Example 2:
 * 
 *         Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2 Output:
 *         [[45,45,45],[45,45,45],[45,45,45]]
 * 
 * 
 *         Constraints:
 * 
 *         m == mat.length n == mat[i].length 1 <= m, n, k <= 100 1 <= mat[i][j]
 *         <= 100
 *
 */

public class _1314_MatrixBlockSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[][] matrixBlockSum(int[][] mat, int k) {
		int pSums[][] = new int[mat.length][mat[0].length];

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (j == 0) {
					pSums[i][j] = mat[i][j];
				} else {
					pSums[i][j] = mat[i][j] + pSums[i][j - 1];
				}
			}
		}
		int ans[][] = new int[mat.length][mat[0].length];

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				populate(pSums, ans, i, j, k);
			}
		}
		return ans;
	}

	private void populate(int[][] pSums, int[][] ans, int row, int col, int k) {
		int prevRow = row - k < 0 ? 0 : row - k;
		int prevCol = col - k < 0 ? 0 : col - k;
		int nextRow = row + k >= pSums.length ? pSums.length - 1 : row + k;
		int nextCol = col + k >= pSums[0].length ? pSums[0].length - 1 : col + k;
		int sum = 0;

		for (; prevRow <= nextRow; prevRow++) {
			if (prevCol == 0) {
				sum += pSums[prevRow][nextCol];
			} else {
				sum += pSums[prevRow][nextCol] - pSums[prevRow][prevCol - 1];
			}
		}
		ans[row][col] = sum;
	}
}
