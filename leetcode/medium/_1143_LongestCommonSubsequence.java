package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two strings text1 and text2, return the length of their longest
 *         common subsequence. If there is no common subsequence, return 0.
 * 
 *         A subsequence of a string is a new string generated from the original
 *         string with some characters (can be none) deleted without changing
 *         the relative order of the remaining characters.
 * 
 *         For example, "ace" is a subsequence of "abcde". A common subsequence
 *         of two strings is a subsequence that is common to both strings.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: text1 = "abcde", text2 = "ace" Output: 3 Explanation: The
 *         longest common subsequence is "ace" and its length is 3.
 * 
 *         Example 2:
 * 
 *         Input: text1 = "abc", text2 = "abc" Output: 3 Explanation: The
 *         longest common subsequence is "abc" and its length is 3.
 * 
 *         Example 3:
 * 
 *         Input: text1 = "abc", text2 = "def" Output: 0 Explanation: There is
 *         no such common subsequence, so the result is 0.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= text1.length, text2.length <= 1000 text1 and text2 consist of
 *         only lowercase English characters.
 *
 */

public class _1143_LongestCommonSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Top Down Approach
	public int longestCommonSubsequence(String text1, String text2) {
		int[][] memo = new int[text1.length()][text2.length()];
		int ans = recur(text1, text2, 0, 0, memo);
		return ans;
	}

	private int recur(String text1, String text2, int index1, int index2, int[][] memo) {
		if (index1 == text1.length() || index2 == text2.length()) {
			return 0;
		}

		if (memo[index1][index2] > 0) {
			return memo[index1][index2];
		}
		int ans = 0;

		if (text1.charAt(index1) == text2.charAt(index2)) {
			ans = 1 + recur(text1, text2, index1 + 1, index2 + 1, memo);
		} else {
			ans = Math.max(recur(text1, text2, index1 + 1, index2, memo),
					recur(text1, text2, index1, index2 + 1, memo));
		}
		return memo[index1][index2] = ans;
	}

	// =============================================================================
	// Bottom up Approach
	public int longestCommonSubsequence1(String text1, String text2) {
		int[][] memo = new int[text1.length() + 1][text2.length() + 1];

		for (int index1 = text1.length() - 1; index1 >= 0; index1--) {
			for (int index2 = text2.length() - 1; index2 >= 0; index2--) {
				int ans = 0;

				if (text1.charAt(index1) == text2.charAt(index2)) {
					ans = 1 + memo[index1 + 1][index2 + 1];
				} else {
					ans = Math.max(memo[index1 + 1][index2], memo[index1][index2 + 1]);
				}
				memo[index1][index2] = ans;
			}
		}
		return memo[0][0];
	}
}
