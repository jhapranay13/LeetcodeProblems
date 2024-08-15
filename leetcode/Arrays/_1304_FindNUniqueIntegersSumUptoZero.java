package leetcode.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer n, return any array containing n unique integers
 *         such that they add up to 0.
 * 
 *         Example 1:
 * 
 *         Input: n = 5 Output: [-7,-1,1,3,4] Explanation: These arrays also are
 *         accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
 * 
 *         Example 2:
 * 
 *         Input: n = 3 Output: [-1,0,1]
 * 
 *         Example 3:
 * 
 *         Input: n = 1 Output: [0]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 1000
 *
 */

public class _1304_FindNUniqueIntegersSumUptoZero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] sumZero(int n) {
		int lo = 0;
		int hi = n - 1;
		int ans[] = new int[n];

		while (lo < hi) {
			int num1 = n / 2;
			int num2 = -1 * (n / 2);
			ans[lo++] = num1;
			ans[hi--] = num2;
			n -= 2;
		}
		return ans;
	}
}
