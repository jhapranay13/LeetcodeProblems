package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.

You must do it in place.

Example 1:

Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Example 2:

Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

Constraints:

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1
 *
 */

public class _73_SetMatrixZeroes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void setZeroes(int[][] matrix) {

		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		boolean firstRowContainsZero = false;
		boolean firstColContainsZero = false;

		for (int i = 0; i < matrix[0].length; i++) {

			if (matrix[0][i] == 0) {
				firstRowContainsZero = true;
			}
		}

		for (int i = 0; i < matrix.length; i++) {

			if (matrix[i][0] == 0) {
				firstColContainsZero = true;
			}
		}

		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix[i].length; j++) {

				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		for (int i = 1; i < matrix.length; i++) {

			if (matrix[i][0] == 0) {

				for (int j = 0; j < matrix[i].length; j++) {
					matrix[i][j] = 0;
				}
			}
		}

		for (int i = 1; i < matrix[0].length; i++) {

			if (matrix[0][i] == 0) {

				for (int j = 0; j < matrix.length; j++) {
					matrix[j][i] = 0;
				}
			}
		}

		if (firstRowContainsZero) {

			for (int j = 0; j < matrix[0].length; j++) {
				matrix[0][j] = 0;
			}
		}

		if (firstColContainsZero) {

			for (int j = 0; j < matrix.length; j++) {
				matrix[j][0] = 0;
			}
		}
	}
}
