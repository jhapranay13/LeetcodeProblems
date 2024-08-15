package leetcode.Presum;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a 2D matrix matrix, handle multiple queries of the following
 *         type:
 * 
 *         Calculate the sum of the elements of matrix inside the rectangle
 *         defined by its upper left corner (row1, col1) and lower right corner
 *         (row2, col2). Implement the NumMatrix class:
 * 
 *         NumMatrix(int[][] matrix) Initializes the object with the integer
 *         matrix matrix. int sumRegion(int row1, int col1, int row2, int col2)
 *         Returns the sum of the elements of matrix inside the rectangle
 *         defined by its upper left corner (row1, col1) and lower right corner
 *         (row2, col2).
 * 
 * 
 *         Example 1:
 * 
 * 
 *         Input ["NumMatrix", "sumRegion", "sumRegion", "sumRegion"] [[[[3, 0,
 *         1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0,
 *         3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]] Output [null,
 *         8, 11, 12]
 * 
 *         Explanation NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5,
 *         6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
 *         numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red
 *         rectangle) numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of
 *         the green rectangle) numMatrix.sumRegion(1, 2, 2, 4); // return 12
 *         (i.e sum of the blue rectangle)
 * 
 * 
 *         Constraints:
 * 
 *         m == matrix.length n == matrix[i].length 1 <= m, n <= 200 -105 <=
 *         matrix[i][j] <= 105 0 <= row1 <= row2 < m 0 <= col1 <= col2 < n At
 *         most 104 calls will be made to sumRegion.
 *
 */

public class _304_RangeSumQuery2DImmutable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class NumMatrix {

		int[][] preSum;

		public NumMatrix(int[][] matrix) {
			this.preSum = new int[matrix.length][matrix[0].length];

			for (int r = 0; r < matrix.length; r++) {
				for (int c = 0; c < matrix[0].length; c++) {

					if (c == 0) {
						preSum[r][c] = matrix[r][c];
					} else {
						preSum[r][c] = preSum[r][c - 1] + matrix[r][c];
					}
				}
			}
		}

		public int sumRegion(int row1, int col1, int row2, int col2) {
			int ans = 0;

			for (int r = row1; r <= row2; r++) {

				if (col1 == 0) {
					ans += preSum[r][col2];
				} else {
					ans += preSum[r][col2] - preSum[r][col1 - 1];
				}
			}
			return ans;
		}
		//===========================================================================================
		//Another Approach
		///consutructor with void to eliminate the IDE error
		//presum is adding current left and top and minus diagonal
		public void NumMatrix1(int[][] matrix) {
			this.preSum = new int[matrix.length + 1][matrix[0].length + 1];

			for (int r = 0; r < matrix.length; r++) {

				for (int c = 0; c < matrix[0].length; c++) {
					preSum[r + 1][c + 1] = preSum[r + 1][c] + preSum[r][c + 1] -
							preSum[r][c] + matrix[r][c];
				}
			}
		}
		//Adding top left and bottom right and subtracting top right and bottom left
		public int sumRegion2(int row1, int col1, int row2, int col2) {
			int ans = preSum[row1][col1] + preSum[row2 + 1][col2 + 1] -
					preSum[row2 + 1][col1] - preSum[row1][col2 + 1];
			return ans;
		}
	}

	/**
	 * Your NumMatrix object will be instantiated and called as such:
	 * NumMatrix obj = new NumMatrix(matrix);
	 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
	 */

}
