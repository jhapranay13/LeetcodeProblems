package leetcode.DP;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an n x n array of integers matrix, return the minimum sum of
 *         any falling path through matrix.
 * 
 *         A falling path starts at any element in the first row and chooses the
 *         element in the next row that is either directly below or diagonally
 *         left/right. Specifically, the next element from position (row, col)
 *         will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: matrix = [[2,1,3],[6,5,4],[7,8,9]] Output: 13 Explanation:
 *         There are two falling paths with a minimum sum underlined below:
 *         [[2,1,3], [[2,1,3], [6,5,4], [6,5,4], [7,8,9]] [7,8,9]] Example 2:
 * 
 *         Input: matrix = [[-19,57],[-40,-5]] Output: -59 Explanation: The
 *         falling path with a minimum sum is underlined below: [[-19,57],
 *         [-40,-5]] Example 3:
 * 
 *         Input: matrix = [[-48]] Output: -48
 * 
 * 
 *         Constraints:
 * 
 *         n == matrix.length n == matrix[i].length 1 <= n <= 100 -100 <=
 *         matrix[i][j] <= 100
 *
 */

public class _931_MinimumFallingPathSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Top Down approach
	public int minFallingPathSum(int[][] matrix) {
		int ans = Integer.MAX_VALUE;
		int[][] memo = new int[matrix.length][matrix[0].length];

		for (int[] mem : memo) {
			Arrays.fill(mem, Integer.MAX_VALUE);
		}

		for (int rcol = 0; rcol < matrix[0].length; rcol++) {

			ans = Math.min(ans, recur(matrix, 0, rcol, memo));
		}
		return ans;
	}

	private int recur(int[][] matrix, int r, int c, int[][] memo) {

		if (r < 0 || c < 0 || r == matrix.length || c == matrix[0].length) {
			return Integer.MAX_VALUE;
		}

		if (memo[r][c] < Integer.MAX_VALUE) {
			return memo[r][c];
		}
		int sum = matrix[r][c];

		int temp = Math.min(recur(matrix, r + 1, c, memo),
				Math.min(recur(matrix, r + 1, c + 1, memo),
						recur(matrix, r + 1, c - 1, memo)));
		temp = temp == Integer.MAX_VALUE ? 0 : temp;
		return memo[r][c] = sum + temp;
	}
	// ==========================================================================
	// Bottom Up approach
	public int minFallingPathSum1(int[][] a) {
		int min = Integer.MAX_VALUE;
		int memo[][] = new int[a.length][a[0].length];

		for (int j = a[0].length - 1; j >= 0; j--) {

			for (int r = 0; r < a.length; r++) {

				for (int c = j; c < a[r].length; c++) {
					int currVal = a[r][c];
					int one = (r - 1 < 0 || c - 1 < 0) ? Integer.MAX_VALUE : memo[r - 1][c - 1];
					int two = (r - 1 < 0) ? Integer.MAX_VALUE : memo[r - 1][c];
					int three = (r - 1 < 0 || c + 1 == a[0].length) ? Integer.MAX_VALUE : memo[r - 1][c + 1];
					int temp = Math.min(one, Math.min(two, three));

					if (temp == Integer.MAX_VALUE) {
						temp = 0;
					}
					memo[r][c] = temp + currVal;
				}
			}
		}
		int ans = Integer.MAX_VALUE;

		for (int c = 0; c < memo[0].length; c++) {
			ans = Math.min(ans, memo[memo.length - 1][c]);
		}
		return ans;
	}
}
