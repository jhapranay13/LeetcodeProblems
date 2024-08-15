package leetcode.DP;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         Given an integer n, return the number of structurally unique BST's
 *         (binary search trees) which has exactly n nodes of unique values from
 *         1 to n.
 * 
 *         Example 1:
 * 
 * 		1       1              2           3         3
 *		 \       \            /\          /         /  
 *		 3        2          1  3        2         1
 * 	    /          \                    /           \
 *	   2	        3                  1             2
 * 
 *         Input: n = 3 Output: 5
 * 
 *         Example 2:
 * 
 *         Input: n = 1 Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 19
 *
 */

public class _96_UniqueBinarySearchTrees {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ================================================================================
	// Top Down Approach
	public int numTrees(int n) {
		int[][] memo = new int[n + 1][n + 1];
		return recur(1, n, memo);
	}

	private int recur(int lo, int hi, int[][] memo) {

		if (lo >= hi) {
			return 1;
		}

		if (memo[lo][hi] != 0) {
			return memo[lo][hi];
		}
		int ans = 0;

		// left * right
		for (int i = lo; i <= hi; i++) {
			ans += recur(lo, i - 1, memo) * recur(i + 1, hi, memo);
		}
		return memo[lo][hi] = ans;
	}

	// =================================================================================
	// Bottom up approach
	public int numTrees1(int n) {
		int[][] memo = new int[n + 1][n + 1];

		for (int lo = n; lo >= 1; lo--) {

			for (int hi = 1; hi <= n; hi++) {

				if (lo >= hi) {
					memo[lo][hi] = 1;
					continue;
				}
				int ans = 0;

				// left * right
				for (int i = lo; i <= hi; i++) {
					int left = lo >= i - 1 ? 1 : memo[lo][i - 1];
					int right = i + 1 >= hi ? 1 : memo[i + 1][hi];
					ans += left * right;
				}
				memo[lo][hi] = ans;
			}
		}
		return memo[1][n];
	}

}
