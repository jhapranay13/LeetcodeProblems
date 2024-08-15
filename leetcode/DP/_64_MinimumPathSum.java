package leetcode.DP;

public class _64_MinimumPathSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//===================================================================================
	//Top down recursive approach

	public int minPathSum(int[][] grid) {
		int[][] memo = new int[grid.length + 1][grid[0].length + 1];
		return recur(grid, 0, 0, memo);
	}

	private int[][] dirs = {{0, 1}, {1, 0}};

	private int recur(int[][] grid, int sRow, int sCol, int[][] memo) {

		if (sRow >= grid.length || sCol >= grid[0].length) {
			return Integer.MAX_VALUE;
		}

		if (sRow == grid.length - 1 && sCol == grid[0].length - 1) {
			return grid[sRow][sCol];
		}

		if(memo[sRow][sCol] > 0) {
			return memo[sRow][sCol];
		}
		int sum = Integer.MAX_VALUE;

		for (int[] dir : dirs) {
			int nRow = sRow + dir[0];
			int nCol = sCol + dir[1];
			int tempSum = recur(grid, nRow, nCol, memo);
			sum = Math.min(sum, tempSum);
		}
		return memo[sRow][sCol] = sum == Integer.MAX_VALUE ? sum : grid[sRow][sCol] + sum;
	}
	//===================================================================================
	//Bottom up DP approach

	public int minPathSum1(int[][] grid) {
		int[][] memo = new int[grid.length + 1][grid[0].length + 1];

		for (int c = 0; c < grid[0].length + 1; c++) {
			memo[grid.length][c] = Integer.MAX_VALUE;
		}

		for (int r = 0; r < grid.length + 1; r++) {
			memo[r][grid[0].length] = Integer.MAX_VALUE;
		}

		for (int sRow = grid.length - 1; sRow >= 0; sRow--) {

			for (int sCol = grid[0].length - 1; sCol >= 0; sCol--) {

				if (sRow == grid.length - 1 && sCol == grid[0].length - 1) {
					memo[sRow][sCol] = grid[sRow][sCol];
					continue;
				}
				int sum = Integer.MAX_VALUE;

				for (int[] dir : dirs) {
					int nRow = sRow + dir[0];
					int nCol = sCol + dir[1];
					int tempSum = memo[nRow][nCol];
					sum = Math.min(sum, tempSum);
				}
				memo[sRow][sCol] = sum == Integer.MAX_VALUE ? sum : grid[sRow][sCol] + sum;
			}
		}
		return memo[0][0];
	}
}
