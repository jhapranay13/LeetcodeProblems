package leetcode.Tree;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array arr of positive integers, consider all binary trees
 *         such that:
 * 
 *         Each node has either 0 or 2 children; The values of arr correspond to
 *         the values of each leaf in an in-order traversal of the tree. (Recall
 *         that a node is a leaf if and only if it has 0 children.) The value of
 *         each non-leaf node is equal to the product of the largest leaf value
 *         in its left and right subtree respectively. Among all possible binary
 *         trees considered, return the smallest possible sum of the values of
 *         each non-leaf node. It is guaranteed this sum fits into a 32-bit
 *         integer.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: arr = [6,2,4] Output: 32 Explanation: There are two possible
 *         trees. The first has non-leaf node sum 36, and the second has
 *         non-leaf node sum 32.
 * 
 *        			 24 		 24 
 *        			/  \        /  \ 
 *        		   12   4      6    8 
 *                /  \             / \ 
 *               6    2           2   4
 * 
 * 
 *         Constraints:
 * 
 *         2 <= arr.length <= 40 1 <= arr[i] <= 15 It is guaranteed that the
 *         answer fits into a 32-bit signed integer (ie. it is less than 2^31).
 *
 */

public class _1130_MinimumCostTreeFromLeafValues {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int mctFromLeafValues(int[] arr) {
		Integer memo[][][] = new Integer[arr.length][arr.length][2];
		Integer[] ans = recur(arr, 0, arr.length - 1, memo);
		return ans[1];
	}

	private Integer[] recur(int[] arr, int lo, int hi, Integer[][][] memo) {
		if (lo == hi) {
			return new Integer[] {arr[lo], 0};
		}

		if (memo[lo][hi][0] != null ) {
			return memo[lo][hi];
		}
		int nodeVal = Integer.MIN_VALUE;
		int sum = Integer.MAX_VALUE;

		for (int i = lo; i < hi; i++) {
			Integer[] left = recur(arr, lo, i, memo);
			Integer[] right = recur(arr, i + 1, hi, memo);
			int tempNodeVal = left[0] * right[0];
			int tempSum = left[1] + right[1] + tempNodeVal;

			if (sum > tempSum) {
				sum = tempSum;
				nodeVal = Math.max(left[0], right[0]);
			}
		}
		return memo[lo][hi] = new Integer[] {nodeVal, sum};
	}
}
