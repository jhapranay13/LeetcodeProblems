package leetcode.ForBiginners.DynamicProgramming.Palindrome;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, return the number of palindromic substrings in it.
 * 
 *         A string is a palindrome when it reads the same backward as forward.
 * 
 *         A substring is a contiguous sequence of characters within the string.
 * 
 *         Example 1:
 * 
 *         Input: s = "abc" Output: 3 Explanation: Three palindromic strings:
 *         "a", "b", "c". 
 *         
 *         Example 2:
 * 
 *         Input: s = "aaa" Output: 6 Explanation: Six palindromic strings: "a",
 *         "a", "a", "aa", "aa", "aaa".
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 1000 s consists of lowercase English letters.
 *
 */

public class _647_PalindromicSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top Down Approach
	public int countSubstrings(String s) {
		int[][] memo = new int[s.length()][s.length()];
		return recur(s, 0, s.length() - 1, memo);
	}

	private int recur(String s, int lo, int hi, int[][] memo) {

		if (lo > hi) {
			return 0;
		}

		if (memo[lo][hi] > 0) {
			return memo[lo][hi];
		}
		boolean flag = isPalindrome(s, lo, hi); 
		int ans = flag == true ? 1 : 0;
		// - so as to remove calculating same Palindrome twice
		ans += recur(s, lo + 1, hi, memo) + recur(s, lo, hi - 1, memo) - 
				recur(s, lo + 1, hi - 1, memo);
		return memo[lo][hi] = ans;
	}

	private boolean isPalindrome(String s, int lo, int hi) {

		while (lo < hi) {
			if (s.charAt(lo++) != s.charAt(hi--)) { 
				return false;
			}
		}
		return true;
	}
	//==============================================================================
	//Bottom up approach
	public int countSubstrings1(String s) {
		int[][] memo = new int[s.length() + 1][s.length() + 1];
		//Sliding window DP Pattern
		for (int l = 1; l <= s.length(); l++) {
			for(int lo = 0; lo <= s.length() - l; lo++) {
				int hi = lo + l - 1; 
				int ans = isPalindrome(s, lo, hi) == true ? 1 : 0;

				if (lo < hi) {
					ans += memo[lo + 1][hi] + memo[lo][hi - 1] - 
							memo[lo + 1][hi - 1];
				}
				memo[lo][hi] = ans;
			}
		}
		return memo[0][s.length() - 1];
	}
	//=============================================================================
	//Expand Around Center approach 
	public int countSubstrings2(String s) {
		int ans = 0;

		for (int i = 0; i < s.length(); ++i) {
			// odd-length palindromes, single character center
			ans += countPalindromesAroundCenter(s, i, i);

			// even-length palindromes, consecutive characters center
			ans += countPalindromesAroundCenter(s, i, i + 1);
		}

		return ans;
	}

	private int countPalindromesAroundCenter(String ss, int lo, int hi) {
		int ans = 0;

		while (lo >= 0 && hi < ss.length()) {
			if (ss.charAt(lo) != ss.charAt(hi))
				break;      // the first and last characters don't match!

			// expand around the center
			lo--;
			hi++;

			ans++;
		}

		return ans;
	}
}
