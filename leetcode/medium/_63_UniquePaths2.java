package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and space is marked as 1 and 0 respectively in the grid.

Example 1:

Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right

Example 2:

Input: obstacleGrid = [[0,1],[0,0]]
Output: 1

Constraints:

m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.
 *
 */

public class _63_UniquePaths2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//===================================================================================
	//Top down recursive Approach
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int[][] memo = new int[obstacleGrid.length + 1][obstacleGrid[0].length + 1];
		return recur(obstacleGrid, 0, 0, memo);
	}

	private int[][] dirs = {{1, 0}, {0, 1}};

	private int recur(int[][] grid, int sRow, int sCol, int[][] memo) {

		if (sRow == grid.length || sCol == grid[0].length) {
			return 0;
		}

		if (grid[sRow][sCol] == 1) {
			return 0;
		}

		if (sRow == grid.length - 1 && sCol == grid[0].length - 1) {
			return 1;
		}

		if (memo[sRow][sCol] > 0) {
			return memo[sRow][sCol];
		}
		int paths = 0;

		for (int[] dir : dirs) {
			int nRow = sRow + dir[0];
			int nCol = sCol + dir[1];

			if (nRow < grid.length && nCol < grid[0].length && grid[nRow][nCol] != 1) {
				paths += recur(grid, nRow, nCol, memo);
			}
		}
		return memo[sRow][sCol] = paths;
	}

	//===================================================================================
	//Bottom Up DP Approach
	public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
		int[][] memo = new int[obstacleGrid.length + 1][obstacleGrid[0].length + 1];

		for (int sRow = obstacleGrid.length - 1; sRow >= 0; sRow--) {

			for (int sCol = obstacleGrid[0].length - 1; sCol >= 0; sCol--) {

				if (obstacleGrid[sRow][sCol] == 1) {
					continue;
				}

				if (sRow == obstacleGrid.length - 1 && sCol == obstacleGrid[0].length - 1) {
					memo[sRow][sCol] = 1;
					continue;
				}

				int paths = 0;

				for (int[] dir : dirs) {
					int nRow = sRow + dir[0];
					int nCol = sCol + dir[1];

					if (nRow < obstacleGrid.length && nCol < obstacleGrid[0].length && 
							obstacleGrid[nRow][nCol] != 1) {
						paths += memo[nRow][nCol];
					}
				}
				memo[sRow][sCol] = paths;
			}
		}
		return memo[0][0];
	}
}
