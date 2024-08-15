package leetcode.medium;


/**
 * 
 * @author Pranay Jha
 *
 *Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
   ------>
   [1,2,3],|
^->[8,9,4],|
|  [7,6,5] |
   <-------V

Example 1:

Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]

Example 2:

Input: n = 1
Output: [[1]]
 *
 */


public class _59_SpiralMatrix2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];
		int rLo = 0;
		int cLo = 0;
		int rHi = n - 1;
		int cHi = n - 1;
		int num = 1;

		while (rLo <= rHi && cLo <= cHi) {

			for (int c = cLo; c <= cHi; c++) {
				matrix[rLo][c] = num++;
			}

			for (int r = rLo + 1; r <= rHi; r++) {
				matrix[r][cHi] = num++;
			}

			for (int c = cHi - 1; c > cLo; c--) {
				matrix[rHi][c] = num++;
			}

			for (int r = rHi; r > rLo; r--) {
				matrix[r][cLo] = num++;
			}
			rLo++;
			rHi--;
			cLo++;
			cHi--;
		}
		return matrix;
	}
}
