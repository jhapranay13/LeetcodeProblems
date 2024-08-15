package leetcode.DP.TwoDimension;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an m x n binary matrix filled with 0's and 1's, find the
 *         largest square containing only 1's and return its area.
 *
 *			["1","0","1","0","0"]
 *					 ----------	
 *			["1","0",|"1","1",|"1"]
 *			["1","1",|"1","1",|"1"]
 *					 ---------	
 *			["1","0","0","1","0"]
 *         
 *         Example 1:
 * 
 *         Input: matrix =
 *         [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 *         Output: 4
 *          
 *          Example 2:
 * 
 *         Input: matrix = [["0","1"],["1","0"]] Output: 1 
 *         
 *         Example 3:
 * 
 *         Input: matrix = [["0"]] Output: 0
 * 
 *         Constraints:
 * 
 *         m == matrix.length n == matrix[i].length 1 <= m, n <= 300
 *         matrix[i][j] is '0' or '1'.
 *
 */

public class _221_MaximalSquare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top Down Approach
	public int maximalSquare(char[][] matrix) {
		int[][] memo = new int[matrix.length][matrix[0].length];
		recur(matrix, matrix.length - 1, matrix[0].length - 1, memo);
		return ans * ans;
	}

	private int ans = 0;

	private int recur(char[][] matrix, int row, int col, int[][] memo) {

		if (row < 0 || col < 0) {
			return 0;
		}

		if (memo[row][col] > 0) {
			return memo[row][col];
		}
		int left = recur(matrix, row, col - 1, memo);
		int top = recur(matrix, row - 1, col, memo);
		int diag = recur(matrix, row - 1, col -1, memo);
		int minLength = Math.min(left, Math.min(top, diag));
		minLength += matrix[row][col] == '1' ? 1 : 0; 
		ans = Math.max(ans, minLength);

		if (matrix[row][col] == '0') {
			return 0;
		}
		return memo[row][col] = minLength;
	}
	//=============================================================================
	//Bottom up Approach
	public int maximalSquare1(char[][] matrix) {
		int[][] memo = new int[matrix.length + 1][matrix[0].length + 1];

		for (int row = 1; row <= matrix.length; row++) {

			for (int col = 1; col <= matrix[0].length; col++) {
				int left = memo[row][col - 1];
				int top = memo[row - 1][col];
				int diag = memo[row - 1][col -1];
				int minLength = Math.min(left, Math.min(top, diag));
				minLength += matrix[row - 1][col - 1] == '1' ? 1 : 0; 
				ans = Math.max(ans, minLength);

				if (matrix[row - 1][col - 1] == '0') {
					continue;
				}
				memo[row][col] = minLength;        
			}
		}
		return ans * ans;
	}
}
