package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Example 1:

Input: m = 3, n = 7
Output: 28

Example 2:

Input: m = 3, n = 2

Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

Example 3:

Input: m = 7, n = 3
Output: 28

Example 4:

Input: m = 3, n = 3
Output: 6

Constraints:

1 <= m, n <= 100
It's guaranteed that the answer will be less than or equal to 2 * 109.
 *
 */

public class _62_UniquePaths {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//============================================================================
	//Top Down Recursive Approach
	public int uniquePaths(int m, int n) {
		int[][] memo = new int[m][n];
		return recur(0, 0, m - 1, n - 1, memo);
	}

	private int[][] dirs = {{0, 1}, {1, 0}};

	private int recur(int sRow, int sCol, int eRow, int eCol, int[][] memo) {

		if (sRow == eRow && sCol == eCol) {
			return 1;
		}

		if (sRow > eRow || sCol > eCol) {
			return 0;
		}
		int paths = 0;

		if (memo[sRow][sCol] > 0) {
			return memo[sRow][sCol];
		}
		for (int[] dir : dirs) {
			int nRow = sRow + dir[0];
			int nCol = sCol + dir[1];
			paths += recur(nRow, nCol, eRow, eCol, memo);    
		}
		return memo[sRow][sCol] = paths;
	}
	//============================================================================
	//Bottom up Approach

	public int uniquePaths1(int m, int n) {
		int[][] memo = new int[m + 1][n + 1];
		int eRow = m - 1;
		int eCol = n - 1;

		for (int sRow = m - 1; sRow >= 0; sRow--) {

			for (int sCol = n - 1; sCol >= 0; sCol--) {

				if (sRow == eRow && sCol == eCol) {
					memo[sRow][sCol] = 1;
					continue;
				}
				int paths = 0;

				for (int[] dir : dirs) {
					int nRow = sRow + dir[0];
					int nCol = sCol + dir[1];
					paths += memo[nRow][nCol];    
				}
				memo[sRow][sCol] = paths;
			}
		}
		return memo[0][0];
	}
}