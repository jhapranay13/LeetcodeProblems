package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a m x n matrix grid which is sorted in non-increasing order
 *         both row-wise and column-wise, return the number of negative numbers
 *         in grid.
 * 
 *         Example 1:
 * 
 *         Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 *         Output: 8 Explanation: There are 8 negatives number in the matrix.
 * 
 *         Example 2:
 * 
 *         Input: grid = [[3,2],[1,0]] Output: 0
 * 
 *         Example 3:
 * 
 *         Input: grid = [[1,-1],[-1,-1]] Output: 3
 * 
 *         Example 4:
 * 
 *         Input: grid = [[-1]] Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         m == grid.length n == grid[i].length 1 <= m, n <= 100 -100 <=
 *         grid[i][j] <= 100
 * 
 * 
 *         Follow up: Could you find an O(n + m) solution?
 *
 */

public class _1351_CountNegativeNumberInSortedMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int countNegatives(int[][] grid) {
		int row = grid.length - 1;
		int col = 0;
		int ans = 0;

		while (row >= 0) {

			while (col < grid[0].length && grid[row][col] >= 0) {
				col++;
			}

			if (col < grid[row].length) {
				ans += grid[row].length - col;
			}
			row--;
		}
		return ans;
	}
	//=============================================================================================
	public int countNegatives1(int[][] grid) {
		int count = 0;

		for (int[] row : grid) {
			int index = binarySearch(row);
			count += row.length - index;
		}
		return count;
	}

	private int binarySearch(int[] nums) {
		int lo = 0;

		if (nums[lo] < 0) {
			return lo;
		}
		int hi = nums.length - 1;

		if (nums[hi] >= 0) {
			return nums.length;
		}

		while (lo < hi) {
			int pivot = lo + (hi - lo) / 2;

			if (nums[pivot] >= 0) {
				lo = pivot + 1;
			} else {
				hi = pivot;
			}
		}
		return hi;
	}
}
