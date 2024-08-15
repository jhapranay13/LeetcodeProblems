package leetcode.ForBiginners.DynamicProgramming.LongestCommonSubsequence;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         Given two strings s and t, return the number of distinct subsequences
 *         of s which equals t.
 * 
 *         A string's subsequence is a new string formed from the original
 *         string by deleting some (can be none) of the characters without
 *         disturbing the remaining characters' relative positions. (i.e., "ACE"
 *         is a subsequence of "ABCDE" while "AEC" is not).
 * 
 *         It is guaranteed the answer fits on a 32-bit signed integer.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "rabbbit", t = "rabbit" Output: 3 Explanation: As shown
 *         below, there are 3 ways you can generate "rabbit" from S. raBBbit
 *         raBbBit rabBBit Example 2:
 * 
 *         Input: s = "babgbag", t = "bag" Output: 5 Explanation: As shown
 *         below, there are 5 ways you can generate "bag" from S. BAbGbag
 *         BAbgbaG baBgbAG BabgbAG babgBAG
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length, t.length <= 1000 s and t consist of English letters.
 *
 */

public class _115_DistinctSubsequences {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ============================================================================
	// Top Down TLE
	public int numDistinct(String s, String t) {
		int memo[][] = new int[s.length()][t.length()];
		return recur(s, t, 0, 0, memo);
	}

	private int recur(String s, String t, int sIndex, int tIndex, int[][] memo) {
		if (tIndex == t.length()) {
			return 1;
		}

		if (sIndex == s.length()) {
			return 0;
		}

		if (memo[sIndex][tIndex] > 0) {
			return memo[sIndex][tIndex];
		}
		int ans = 0;

		if (s.charAt(sIndex) != t.charAt(tIndex)) {
			ans = recur(s, t, sIndex + 1, tIndex, memo);
		} else {
			int include = recur(s, t, sIndex + 1, tIndex + 1, memo);
			int exclude = recur(s, t, sIndex + 1, tIndex, memo);
			ans = include + exclude;
		}
		return memo[sIndex][tIndex] = ans;
	}

	// =============================================================================
	// Bottom Up
	public int numDistinct1(String s, String t) {
		int memo[][] = new int[s.length() + 1][t.length() + 1];

		for (int i = 0; i <= s.length(); i++) {
			memo[i][t.length()] = 1;
		}

		for (int sIndex = s.length() - 1; sIndex >= 0; sIndex--) {
			for (int tIndex = t.length() - 1; tIndex >= 0; tIndex--) {
				int ans = 0;

				if (s.charAt(sIndex) != t.charAt(tIndex)) {
					ans = memo[sIndex + 1][tIndex];
				} else {
					int include = memo[sIndex + 1][tIndex + 1];
					int exclude = memo[sIndex + 1][tIndex];
					ans = include + exclude;
				}
				memo[sIndex][tIndex] = ans;
			}
		}
		return memo[0][0];
	}
}
