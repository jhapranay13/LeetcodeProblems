package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:

[1,2,3],
[4,5,6],
[7,8,9]

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:

Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
 *
 */

public class _54_SpiralMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> ans = new ArrayList<>();

		if (matrix == null || matrix.length == 0) {
			return ans;
		}
		int rLo = 0;
		int rHi = matrix.length - 1;
		int cLo = 0;
		int cHi = matrix[0].length - 1;

		while (rLo <= rHi && cLo <= cHi) {

			for (int c = cLo; c <= cHi; c++) {
				ans.add(matrix[rLo][c] );
			}

			for (int r = rLo + 1; r <= rHi; r++) {
				ans.add(matrix[r][cHi]);
			}

			if (rLo < rHi && cLo < cHi) {

				for (int c = cHi - 1; c > cLo; c--) {
					ans.add(matrix[rHi][c] );
				}

				for (int r = rHi; r > rLo; r--) {
					ans.add(matrix[r][cLo]);
				}
			}
			rLo++;
			rHi--;
			cLo++;
			cHi--;
		}
		return ans;
	}
}
