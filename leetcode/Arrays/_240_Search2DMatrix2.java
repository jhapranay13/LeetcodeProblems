package leetcode.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Write an efficient algorithm that searches for a target value in an m
 *         x n integer matrix. The matrix has the following properties:
 * 
 *         Integers in each row are sorted in ascending from left to right.
 *         Integers in each column are sorted in ascending from top to bottom.
 * 
 * 
 *         Example 1:
 * 
 * 
 *         Input: matrix =
 *         [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]],
 *         target = 5 Output: true 
 *         
 *         Example 2:
 * 
 * 
 *         Input: matrix =
 *         [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]],
 *         target = 20 Output: false
 * 
 * 
 *         Constraints:
 * 
 *         m == matrix.length n == matrix[i].length 1 <= n, m <= 300 -109 <=
 *         matix[i][j] <= 109 All the integers in each row are sorted in
 *         ascending order. All the integers in each column are sorted in
 *         ascending order. -109 <= target <= 109
 *
 */

public class _240_Search2DMatrix2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Binary Search
	public boolean searchMatrix(int[][] matrix, int target) {

		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		int[] rows = reduceRow(matrix, target);
		int[] cols = reduceCol(matrix, target);

		for (int i = rows[0]; i <= rows[1]; i++) {

			if (binarySearch(matrix, i, cols, target)) {
				return true;
			}
		}
		return false;
	}

	private boolean binarySearch(int[][] matrix, int row, int[] cols, int target) {
		int lo = cols[0];
		int hi = cols[1];

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (matrix[row][pivot] < target) {
				lo = pivot + 1;
			} else if (matrix[row][pivot] > target) {
				hi = pivot - 1;
			} else {
				return true;
			}
		}
		return false;
	}

	private int[] reduceRow(int[][] matrix, int target) {
		int lo = 0;
		int hi = matrix.length - 1;

		while (lo <= hi) {

			if (matrix[lo][0] <= target && matrix[lo][matrix[0].length  -1] >= target) {
				break;
			}
			lo++;
		}

		while (lo < hi) {

			if (matrix[hi][0] <= target && matrix[hi][matrix[0].length - 1] >= target) {
				break;
			}
			hi--;
		}
		return new int[] {lo, hi};
	}

	private int[] reduceCol(int[][] matrix, int target) {
		int lo = 0;
		int hi = matrix[0].length - 1;

		while (lo <= hi) {

			if (matrix[0][lo] <= target && matrix[matrix.length - 1][lo] >= target) {
				break;
			}
			lo++;
		}

		while (lo < hi) {

			if (matrix[0][hi] <= target && matrix[matrix.length - 1][hi] >= target) {
				break;
			}
			hi--;
		}
		return new int[] {lo, hi};
	}
	//==============================================================================
	//Search Space Reduction technique
	//This would work equally well with a pointer initialized to the top-right. 
	//Neither of the other two corners would work, as pruning a row/column might prevent us from achieving the correct answer. 
	public boolean searchMatrix1(int[][] matrix, int target) {
		int row = matrix.length - 1;
		int col = 0;

		while (row >= 0 && col < matrix[0].length) {

			if (matrix[row][col] > target) {
				row--;
			} else if (matrix[row][col] < target) {
				col++;
			} else {
				return true;
			}
		}
		return false;
	}
	//================================================================================
	//Another Approach
	public boolean searchMatrix2(int[][] matrix, int target) {
		int[] rows = getRows1(matrix, target);
		int[] cols = getCols1(matrix, target);

		for (int r = rows[0]; r <= rows[1]; r++) {
			if (binarySearch1(matrix, r, cols, target)) {
				return true;
			}
		}
		return false;
	}

	private boolean binarySearch1(int[][] matrix, int r, int[] cols, int target) {
		int lo = cols[0];
		int hi = cols[1];

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (matrix[r][pivot] == target) {
				return true;
			} else if (matrix[r][pivot] < target) {
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		return false;
	}

	private int[] getCols1(int[][] matrix, int target) {
		int lo = 0;
		int hi = matrix[0].length - 1;
		int[] ans = new int[2];

		while(lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (matrix[0][pivot] <= target && matrix[matrix.length - 1][pivot] >= target) {
				ans[0] = pivot;
				hi = pivot - 1;
			} else if (matrix[matrix.length - 1][pivot] < target) {
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		lo = ans[0];
		hi = matrix[0].length - 1;

		while(lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (matrix[0][pivot] <= target && matrix[matrix.length - 1][pivot] >= target) {
				ans[1] = pivot;
				lo = pivot + 1;
			} else if (matrix[matrix.length - 1][pivot] < target) {
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		return ans;
	}

	private int[] getRows1(int[][] matrix, int target) {
		int lo = 0;
		int hi = matrix.length - 1;
		int [] ans = new int[2];

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (matrix[pivot][0] <= target && matrix[pivot][matrix[0].length - 1] >= target) {
				ans[0] = pivot;
				hi = pivot - 1;
			} else if (matrix[pivot][matrix[0].length - 1] < target) {
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		lo = ans[0];
		hi = matrix.length - 1;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (matrix[pivot][0] <= target && matrix[pivot][matrix[0].length - 1] >= target) {
				ans[1] = pivot;
				lo = pivot + 1;
			} else if (matrix[pivot][matrix[0].length - 1] < target) {
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		return ans;
	}
}
