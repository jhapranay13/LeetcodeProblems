package leetcode.DP.TwoDimension;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, partition s such that every substring of the
 *         partition is a palindrome.
 * 
 *         Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "aab" Output: 1 Explanation: The palindrome partitioning
 *         ["aa","b"] could be produced using 1 cut. Example 2:
 * 
 *         Input: s = "a" Output: 0 Example 3:
 * 
 *         Input: s = "ab" Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 2000 s consists of lower-case English letters only.
 *
 */

public class _132_PalindromePartitioning2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ============================================================================
	// Top Down Approach version 1
	public int minCut(String s) {
		int[] memo = new int[s.length()];
		Arrays.fill(memo, -1);
		return recur(s, 0, memo);
	}

	private int recur(String s, int index, int[] memo) {

		if (index == s.length() - 1) {
			return 0;
		}

		if (memo[index] > -1) {
			return memo[index];
		}
		int ans = Integer.MAX_VALUE;

		for (int i = index; i < s.length(); i++) {

			if (isPalindrome(s, index, i)) {

				if (i == s.length() - 1) {
					ans = 0;
					break;
				}
				int restOfTheCuts = recur(s, i + 1, memo);
				ans = Math.min(ans, 1 + restOfTheCuts);
			}
		}
		return memo[index] = ans;
	}

	private boolean isPalindrome(String s, int lo, int hi) {

		while (lo <= hi) {
			if (s.charAt(lo++) != s.charAt(hi--)) {
				return false;
			}
		}
		return true;
	}

	// ===========================================================================
	// Bottom up version 1
	public int minCut1(String s) {
		int[] memo = new int[s.length()];
		// Arrays.fill(memo, -1);

		for (int index = s.length() - 2; index >= 0; index--) {
			int ans = Integer.MAX_VALUE;

			for (int i = index; i < s.length(); i++) {

				if (isPalindrome(s, index, i)) {

					if (i == s.length() - 1) {
						ans = 0;
						break;
					}
					int restOfTheCuts = memo[i + 1];
					ans = Math.min(ans, 1 + restOfTheCuts);
				}
			}
			memo[index] = ans;
		}
		return memo[0];
	}

	// ==========================================================================
	// Top Down version 2
	public int minCut3(String s) {
		int[][] memo = new int[s.length()][s.length()];

		for (int[] mem : memo) {
			Arrays.fill(mem, -1);
		}
		return recur(s, 0, s.length() - 1, memo);
	}

	private int recur(String s, int lo, int hi, int[][] memo) {
		if (lo >= hi) {
			return 0;
		}

		if (memo[lo][hi] > -1) {
			return memo[lo][hi];
		}
		// If already a palindrome no need to break
		if (isPalindrome(s, lo, hi)) {
			return 0;
		}
		int ans = Integer.MAX_VALUE;

		for (int i = lo; i <= hi; i++) {
			if (isPalindrome(s, lo, i)) {
				ans = Math.min(ans, 1 + recur(s, i + 1, hi, memo));
			}
		}
		return memo[lo][hi] = ans;
	}
	//============================================================================
	//Bottom up version 2
	public int minCut4(String s) {
		int[] memo = new int[s.length() + 1];

		//for (int[] mem : memo) {
		Arrays.fill(memo, - 1);
		//}
		memo[s.length()] = 0;
		int hi = s.length() - 1;

		for (int lo = s.length() - 1; lo >= 0; lo--) {

			if (isPalindrome(s, lo, hi)) {
				memo[lo] = 0;
				continue;
			}
			int ans = Integer.MAX_VALUE;

			for (int i = lo; i <= hi; i++) {

				if (isPalindrome(s, lo, i)) {
					ans = Math.min(ans, 1 + memo[i + 1]);
				}
			}
			memo[lo] = ans;
		}
		return memo[0];
	}
}
