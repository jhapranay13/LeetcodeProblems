package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer n, return the count of all numbers with unique
 *         digits, x, where 0 <= x < 10n.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: n = 2 Output: 91 Explanation: The answer should be the total
 *         numbers in the range of 0 <= x < 100, excluding
 *         11,22,33,44,55,66,77,88,99 Example 2:
 * 
 *         Input: n = 0 Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         0 <= n <= 8
 *
 */

public class _357_CountNumbersWithUniqueDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int countNumbersWithUniqueDigits(int n) {
		return recursion(n, 0, new boolean[10]);
	}

	int recursion(int n, int curr, boolean[] vis) {
		if (curr == n)
			return 1;
		int res = 1;
		for (int i = (curr == 0 ? 1 : 0); i <= 9; i++) {
			if (vis[i])
				continue;
			vis[i] = true;
			res += recursion(n, curr + 1, vis);
			vis[i] = false;
		}
		return res;
	}
}
