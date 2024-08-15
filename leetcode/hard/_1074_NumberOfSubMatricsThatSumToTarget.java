package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a matrix and a target, return the number of non-empty
 *         submatrices that sum to target.
 * 
 *         A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with
 *         x1 <= x <= x2 and y1 <= y <= y2.
 * 
 *         Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are
 *         different if they have some coordinate that is different: for
 *         example, if x1 != x1'.
 * 
 * 
 * 
 *         Example 1:
 * 
 * 
 *         Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0 Output: 4
 *         Explanation: The four 1x1 submatrices that only contain 0. 
 *         
 *         Example 2:
 * 
 *         Input: matrix = [[1,-1],[-1,1]], target = 0 Output: 5 Explanation:
 *         The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2
 *         submatrix. 
 *         
 *         Example 3:
 * 
 *         Input: matrix = [[904]], target = 0 Output: 0
 * 
 * 
 *         Constraints:
 * 
 *         1 <= matrix.length <= 100 1 <= matrix[0].length <= 100 -1000 <=
 *         matrix[i] <= 1000 -10^8 <= target <= 10^8
 *
 */

public class _1074_NumberOfSubMatricsThatSumToTarget {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =======================================================================
	// Version 1
	public int numSubmatrixSumTarget(int[][] matrix, int target) {
		int[][] preSum = new int[matrix.length][matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix[i].length; j++) {

				if (j == 0) {
					preSum[i][j] = matrix[i][j];
				} else {
					preSum[i][j] = matrix[i][j] + preSum[i][j - 1];
				}
			}
		}
		int result = 0;

		for (int i = 0; i < matrix[0].length; i++) {

			for (int j = 0; j <= i; j++) {
				result += getTarget(preSum, j, i, target);
			}
		}
		return result;
	}

	public int getTarget(int[][] preSum, int start, int end, int target) {
		int sum = 0;
		int count = 0;
		Map<Integer, Integer> twoSum = new HashMap<>();
		twoSum.put(0, 1);

		for (int i = 0; i < preSum.length; i++) {

			if (start == 0) {
				sum += preSum[i][end];
			} else {
				sum += preSum[i][end] - preSum[i][start - 1];
			}

			if (twoSum.containsKey(sum - target)) {
				count += twoSum.get(sum - target);
			}
			twoSum.put(sum, twoSum.getOrDefault(sum, 0) + 1);
		}
		return count;
	}

	// ======================================================================
	// Version 2. Using multidimension perSum
	public int numSubmatrixSumTarget1(int[][] matrix, int target) {
		int[][] pSum = new int[matrix.length + 1][matrix[0].length + 1];

		for (int i = 1; i <= matrix.length; i++) {

			for (int j = 1; j <= matrix[0].length; j++) {
				pSum[i][j] = pSum[i - 1][j] + pSum[i][j - 1] - pSum[i - 1][j - 1] + matrix[i - 1][j - 1];
			}
		}
		Map<Integer, Integer> sumCount = new HashMap<>();
		int ans = 0;
		int sum = 0;

		for (int r1 = 1; r1 <= matrix.length; r1++) {

			for (int r2 = r1; r2 <= matrix.length; r2++) {
				sumCount.clear();
				sumCount.put(0, 1);

				for (int col = 1; col <= matrix[0].length; col++) {
					sum = pSum[r2][col] - pSum[r1 - 1][col];
					ans += sumCount.getOrDefault(sum - target, 0);
					sumCount.put(sum, sumCount.getOrDefault(sum, 0) + 1);
				}
			}
		}
		return ans;
	}
}
