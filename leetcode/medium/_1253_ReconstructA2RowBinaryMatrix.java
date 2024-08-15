package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the following details of a matrix with n columns and 2 rows :
 * 
 *         The matrix is a binary matrix, which means each element in the matrix
 *         can be 0 or 1. The sum of elements of the 0-th(upper) row is given as
 *         upper. The sum of elements of the 1-st(lower) row is given as lower.
 *         The sum of elements in the i-th column(0-indexed) is colsum[i], where
 *         colsum is given as an integer array with length n. Your task is to
 *         reconstruct the matrix with upper, lower and colsum.
 * 
 *         Return it as a 2-D integer array.
 * 
 *         If there are more than one valid solution, any of them will be
 *         accepted.
 * 
 *         If no valid solution exists, return an empty 2-D array.
 * 
 *         Example 1:
 * 
 *         Input: upper = 2, lower = 1, colsum = [1,1,1] Output:
 *         [[1,1,0],[0,0,1]] Explanation: [[1,0,1],[0,1,0]], and
 *         [[0,1,1],[1,0,0]] are also correct answers.
 * 
 *         Example 2:
 * 
 *         Input: upper = 2, lower = 3, colsum = [2,2,1,1] Output: []
 * 
 *         Example 3:
 * 
 *         Input: upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1] Output:
 *         [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= colsum.length <= 10^5 0 <= upper, lower <= colsum.length 0 <=
 *         colsum[i] <= 2
 *
 */

public class _1253_ReconstructA2RowBinaryMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
		List<List<Integer>> ans = new ArrayList<>();
		int[] u = new int[colsum.length];
		int[] d = new int[colsum.length];
		int i = 0;

		for (int val : colsum) {

			if (val != 0) {

				if ((upper <= 0 && lower <= 0)) {
					return ans;
				}

				if (val == 1) {

					if (upper > 0 && upper >= lower) {
						u[i] = 1;
						upper--;
					} else if (lower > 0) {
						d[i] = 1;
						lower--;
					} else {
						return ans;
					}
					i++;
					continue;
				}

				if (val == 2 && upper > 0 && lower > 0) {
					u[i] = 1;
					d[i] = 1;
					upper--;
					lower--;
				} else {
					return ans;
				}
			}
			i++;
		}

		if (upper != 0 || lower != 0) {
			return ans;
		}
		List<Integer> up = new ArrayList<>();
		List<Integer> down = new ArrayList<>();

		for (int in : u) {
			up.add(in);
		}

		for (int in : d) {
			down.add(in);
		}

		if (up.size() > 0) {
			ans.add(up);
		}

		if (down.size() > 0) {
			ans.add(down);
		}
		return ans;
	}
}
