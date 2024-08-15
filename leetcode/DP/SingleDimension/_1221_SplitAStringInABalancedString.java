package leetcode.DP.SingleDimension;

/**
 * 
 * @author Pranay Jha
 *
 *         Balanced strings are those that have an equal quantity of 'L' and 'R'
 *         characters.
 * 
 *         Given a balanced string s, split it in the maximum amount of balanced
 *         strings.
 * 
 *         Return the maximum amount of split balanced strings.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "RLRRLLRLRL" Output: 4 Explanation: s can be split into
 *         "RL", "RRLL", "RL", "RL", each substring contains same number of 'L'
 *         and 'R'.
 * 
 *         Example 2:
 * 
 *         Input: s = "RLLLLRRRLR" Output: 3 Explanation: s can be split into
 *         "RL", "LLLRRR", "LR", each substring contains same number of 'L' and
 *         'R'.
 * 
 *         Example 3:
 * 
 *         Input: s = "LLLLRRRR" Output: 1 Explanation: s can be split into
 *         "LLLLRRRR".
 * 
 *         Example 4:
 * 
 *         Input: s = "RLRRRLLRLL" Output: 2 Explanation: s can be split into
 *         "RL", "RRRLLRLL", since each substring contains an equal number of
 *         'L' and 'R'
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 1000 s[i] is either 'L' or 'R'. s is a balanced
 *         string.
 *
 */

public class _1221_SplitAStringInABalancedString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Top down approach
	public int balancedStringSplit(String s) {
		int memo[] = new int[s.length()];
		int ans = recur(s, 0, memo);
		return ans;
	}

	public int recur(String s, int index, int[] memo) {
		if (index == s.length()) {
			return 0;
		}

		if (memo[index] > 0) {
			return memo[index];
		}
		int count = 0;
		int ans = 0;

		for (int i = index; i < s.length(); i++) {
			char ch = s.charAt(i);

			if (ch == 'L') {
				count--;
			} else {
				count++;
			}

			if (count == 0) {
				int temp = 1 + recur(s, i + 1, memo);
				ans = Math.max(ans, temp);
			}
		}
		return memo[index] = ans;
	}

	// =============================================================================
	// Bottom up approach
	public int balancedStringSplit1(String s) {
		int memo[] = new int[s.length() + 1];

		for (int index = s.length(); index >= 0; index--) {
			int count = 0;
			int ans = 0;

			for (int i = index; i < s.length(); i++) {
				char ch = s.charAt(i);

				if (ch == 'L') {
					count--;
				} else {
					count++;
				}

				if (count == 0) {
					int temp = 1 + memo[i + 1];
					ans = Math.max(ans, temp);
				}
			}
			memo[index] = ans;
		}
		return memo[0];
	}
}
