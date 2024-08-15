package leetcode.hard;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, return the number of different non-empty
 *         palindromic subsequences in s. Since the answer may be very large,
 *         return it modulo 109 + 7.
 * 
 *         A subsequence of a string is obtained by deleting zero or more
 *         characters from the string.
 * 
 *         A sequence is palindromic if it is equal to the sequence reversed.
 * 
 *         Two sequences a1, a2, ... and b1, b2, ... are different if there is
 *         some i for which ai != bi.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "bccb" Output: 6 Explanation: The 6 different non-empty
 *         palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
 *         Note that 'bcb' is counted only once, even though it occurs twice.
 * 
 * 
 *         Example 2:
 * 
 *         Input: s =
 *         "abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"
 *         Output: 104860361 Explanation: There are 3104860382 different
 *         non-empty palindromic subsequences, which is 104860361 modulo 109 +
 *         7.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 1000 s[i] is either 'a', 'b', 'c', or 'd'.
 *
 */

public class _730_CountDifferentPalindromicSubSequences {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int countPalindromicSubsequences(String S) {

		if (S == null || S.trim().length() == 0) {
			return 0;
		}

		if (S.length() == 1) {
			return 1;
		}
		int memo[][] = new int[S.length() + 1][S.length() + 1];

		for (int i = 0; i <= S.length(); i++) {
			memo[i][i] = 1;
		}
		int ans = rec(S, 0, S.length() - 1, memo);
		return ans;
	}

	private int rec(String s, int lo, int hi, int[][] memo) {

		if (lo == hi) {
			return 1;
		}

		if (lo > hi) {
			return 0;
		}

		if (memo[lo][hi] > 0) {
			return memo[lo][hi];
		}
		long count = 0;

		if (s.charAt(lo) == s.charAt(hi)) {
			/*
                    Since the edge characters are same :
                            a b a
                            i   j
                    the total number of palindromic subsequence obtained from in bwtween characters can
                    individually be bounded between characters at i and j
                    As in this case palindromic sequence by in between character : "b" is 1 ie "b"
                    which can be bounded like : "aba"
                    so in total its prev "b" : 1*2 ie 2;
            */
			count = rec(s, lo + 1, hi - 1, memo) * 2;
			/*
                    we track the characters which are similar to boundary characters i and j
            */
			int left = lo + 1;
			int right = hi - 1;

			while (left <= right && s.charAt(left) != s.charAt(lo)) {
				left++;
			}

			while (left <= right && s.charAt(right) != s.charAt(hi)) {
				right--;
			}

			/*
                        There is one character which is similar. let query be : "abaca"
                                a  b  a  c  a
                                i    l/h    j
                        For this we add 1 for it to make a palindromic subsequence with either of
                        edge characters i.e with i or j ie "aa". "a" and "aaa" is already accounted for
            */
			if (left == right) {
				count += 1;
			}
			/*
                        There are no characters in between which are identical
                                a   b   c   a
                                i   h   l   j
                        we need to add 2 for the edge characters they can contribute in answers like
                        "a" and "aa"
            */
			if (left > right) {
				count += 2;
			}

			/*
                        There are more than one characters in bwtween;
                                a   b   a   a   a   c   a
                                i       l       h       j
                        We have to deduct the number of palindrome in between low and high
                        as its already been calculated earlier @ dp[i][j]*2;
            */
			if (left < right) {
				count -= rec(s, left + 1, right - 1, memo);
			}
		} else {
			/*
                    for query : "abc" first and last "a" and "c" are not same
                    we check for ans from "ab" and "bc"
                                         i->j-1   i+1->j
                                         "a","b"  "b","c"
                    and delete the common from both ranges ie "b" which is i+1->j-1
            */
			count = rec(s, lo + 1, hi, memo) + rec(s, lo, hi - 1, memo) - rec(s, lo + 1, hi - 1, memo);
		}
		return memo[lo][hi] = (int) ((count + 1000000007) % 1000000007);
	}
	//=============================================================================================
	//Bottom up solution
	public int countPalindromicSubsequences1(String s) {
		int memo[][] = new int[s.length() + 1][s.length() + 1];

		for( int i = 0; i <= s.length(); i++ ) {
			memo[ i ][ i ] = 1;
		}

		for (int j = 1; j <= s.length(); j++) {

			for (int lo = 0; lo <= s.length() - j; lo++) {
				int hi = j + lo - 1;

				if (hi == lo) {
					memo[lo][hi] = 1;
					continue;
				}

				if (lo > hi) {
					memo[lo][hi] = 0;
					continue;
				}
				long ans = 0;

				if (s.charAt(lo) == s.charAt(hi)) {
					ans = memo[lo + 1][hi - 1] * 2;

					int l = lo + 1;
					int h = hi - 1;

					while (l < s.length() && s.charAt(l) != s.charAt(lo)) {
						l++;
					}

					while (h >= 0 && s.charAt(hi) != s.charAt(h)) {
						h--;
					}

					if (l == h) {
						ans += 1;
					}

					if (l < h) {
						ans -= memo[l + 1][h - 1];
					}

					if (l > h) {
						ans += 2;
					}

				} else {
					ans = memo[lo + 1][hi] + memo[lo][hi - 1] -
							memo[lo + 1][hi - 1];
				}
				memo[lo][hi] = (int)((ans + 1000000007) % 1000000007);
			}
		}
		int ans = memo[0][s.length() - 1];
		return ans;
	}
}
