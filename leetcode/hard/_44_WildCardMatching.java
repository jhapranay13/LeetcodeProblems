package leetcode.hard;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an input string (s) and a pattern (p), implement wildcard
 *         pattern matching with support for '?' and '*' where:
 * 
 *         '?' Matches any single character. '*' Matches any sequence of
 *         characters (including the empty sequence). The matching should cover
 *         the entire input string (not partial).
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "aa", p = "a" Output: false Explanation: "a" does not
 *         match the entire string "aa".
 * 
 *         Example 2:
 * 
 *         Input: s = "aa", p = "*" Output: true Explanation: '*' matches any
 *         sequence.
 * 
 *         Example 3:
 * 
 *         Input: s = "cb", p = "?a" Output: false Explanation: '?' matches 'c',
 *         but the second letter is 'a', which does not match 'b'.
 * 
 *         Example 4:
 * 
 *         Input: s = "adceb", p = "*a*b" Output: true Explanation: The first
 *         '*' matches the empty sequence, while the second '*' matches the
 *         substring "dce".
 * 
 *         Example 5:
 * 
 *         Input: s = "acdcb", p = "a*c?b" Output: false
 * 
 * 
 *         Constraints:
 * 
 *         0 <= s.length, p.length <= 2000 s contains only lowercase English
 *         letters. p contains only lowercase English letters, '?' or '*'.
 *
 */

public class _44_WildCardMatching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ======================================================================
	// Top Down Approach
	public boolean isMatch(String s, String p) {
		Boolean[][] memo = new Boolean[s.length()][p.length()];
		return rec(s, p, 0, 0, memo);
	}

	private boolean rec(String s, String p, int si, int pi, Boolean[][] memo) {

		if (si >= s.length()) {

			if (pi >= p.length()) {
				return true;
			} else if (pi == p.length() - 1 && p.charAt(pi) == '*') {
				return true;
			} else if (pi < p.length() - 1 && p.charAt(pi) == '*') {
				return rec(s, p, si, pi + 1, memo);
			} else {
				return false;
			}
		}

		if (p.length() <= pi) {
			return false;
		}

		if (memo[si][pi] != null) {
			return memo[si][pi];
		}
		boolean flag = false;

		if (si < s.length() && (p.charAt(pi) == '?' || p.charAt(pi) == s.charAt(si))) {
			flag = rec(s, p, si + 1, pi + 1, memo);
		}

		if (p.charAt(pi) == '*') {

			flag = rec(s, p, si + 1, pi + 1, memo);

			if (!flag) {
				flag = rec(s, p, si + 1, pi, memo) || rec(s, p, si, pi + 1, memo);
			}
		}
		return memo[si][pi] = flag;
	}

	// =====================================================================
	// Bottom up approach
	public boolean isMatch1(String s, String p) {
		Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
		memo[s.length()][p.length()] = true;

		for (int si = s.length(); si >= 0; si--) {

			for (int pi = p.length(); pi >= 0; pi--) {

				if (si >= s.length()) {

					if (pi >= p.length()) {
						memo[si][pi] = true;
					} else if (pi == p.length() - 1 && p.charAt(pi) == '*') {
						memo[si][pi] = true;
					} else if (pi < p.length() - 1 && p.charAt(pi) == '*') {
						memo[si][pi] = memo[si][pi + 1];
					} else {
						memo[si][pi] = false;
					}
					continue;
				}

				if (p.length() <= pi) {
					memo[si][pi] = false;
					continue;
				}
				boolean flag = false;

				if (si < s.length() && (p.charAt(pi) == '?' || p.charAt(pi) == s.charAt(si))) {
					flag = memo[si + 1][pi + 1];
				}

				if (p.charAt(pi) == '*') {
					flag = memo[si + 1][pi + 1];

					if (!flag) {
						flag = memo[si + 1][pi] || memo[si][pi + 1];
					}
				}
				memo[si][pi] = flag;
			}
		}
		return memo[0][0];
	}
}
