package leetcode.Strings;

/**
 * 
 * @author Pranay Jha
 *Given a string s, return the longest palindromic substring in s.

Example 1:

Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:

Input: s = "cbbd"
Output: "bb"

Example 3:

Input: s = "a"
Output: "a"

Example 4:

Input: s = "ac"
Output: "a"


Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters (lower-case and/or upper-case),
 */
public class _04_LongestPalindromicSubstring {

	public static void main(String[] args) {
		_04_LongestPalindromicSubstring obj = new _04_LongestPalindromicSubstring();
		obj.longestPalindrome("bb");
		System.out.println( obj.ans );
	}

//=============================================================================================

	public String longestPalindrome(String s) {

		if (s == null || s.length() == 0) {
			return s;
		}
		boolean[][] memo = new boolean[s.length()][s.length()];

		for (int i = 0; i < s.length(); i++) {
			memo[i][i] = true;
		}

		for (int i = 1; i <= s.length(); i++) {

			for (int lo = 0; lo <= s.length() - i; lo++) {
				int hi = lo + i - 1;

				if (memo[lo][hi] == true) {
					continue;
				}
				boolean flag = false;

				if (s.charAt(lo) == s.charAt(hi)) {

					if ( hi - lo <= 1) {
						flag = true;
					} else if (memo[lo + 1][hi - 1]) {
						flag = true;
					}

					if (flag && ans.length() < hi - lo + 1) {
						ans = s.substring(lo, hi + 1);
					}
				}
				memo[lo][hi] = flag;
			}
		}

		if (ans.length() == 0) {
			ans = s.substring(0, 1);
		}
		return ans;
	}
	private String ans = "";

	private boolean recur(String s, int lo, int hi, boolean memo[][]) {

		if (lo > hi) {
			return false;
		}

		if (memo[lo][hi] == true) {
			return memo[lo][hi];
		}
		boolean flag = false;

		if (s.charAt(lo) == s.charAt(hi)) {

			if ( hi - lo <= 1) {
				flag = true;
			} else if (recur(s, lo + 1, hi - 1, memo)) {
				flag = true;
			}

			if (flag && ans.length() < hi - lo + 1) {
				ans = s.substring(lo, hi + 1);
			}
		}
		recur(s, lo + 1, hi, memo);
		recur(s, lo, hi - 1, memo);
		return memo[lo][hi] = flag;
	}
}
