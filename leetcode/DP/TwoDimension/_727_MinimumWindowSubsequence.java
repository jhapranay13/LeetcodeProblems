package leetcode.DP.TwoDimension;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Given strings s1 and s2, return the minimum contiguous substring part
 *         of s1, so that s2 is a subsequence of the part.
 * 
 *         If there is no such window in s1 that covers all characters in s2,
 *         return the empty string "". If there are multiple such minimum-length
 *         windows, return the one with the left-most starting index.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s1 = "abcdebdde", s2 = "bde" Output: "bcde" Explanation:
 *         "bcde" is the answer because it occurs before "bdde" which has the
 *         same length. "deb" is not a smaller window because the elements of s2
 *         in the window must occur in order.
 * 
 *         Example 2:
 * 
 *         Input: s1 = "jmeqksfrsdcmsiwvaovztaqenprpvnbstl", s2 = "u" Output: ""
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s1.length <= 2 * 104 1 <= s2.length <= 100 s1 and s2 consist of
 *         lowercase English letters.
 *
 */

public class _727_MinimumWindowSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ======================================================================
	// DP solution
	public String minWindow(String S, String T) {
		int[][] memo = new int[S.length()][T.length()];

		for (int[] mem : memo) {
			Arrays.fill(mem, -1);
		}
		rec(S, T, 0, 0, memo);
		return ans == null ? "" : ans;
	}

	private String ans = null;

	private int rec(String s, String t, int i, int j, int[][] memo) {

		if (j == t.length()) {
			return i;
		}

		if (i == s.length()) {
			return Integer.MAX_VALUE;
		}

		if (memo[i][j] > -1) {
			return memo[i][j];
		}
		int lastIndex = Integer.MAX_VALUE;

		if (s.charAt(i) == t.charAt(j)) {
			lastIndex = rec(s, t, i + 1, j + 1, memo);
		}
		lastIndex = Math.min(lastIndex, rec(s, t, i + 1, j, memo));

		if (lastIndex < Integer.MAX_VALUE && j == 0) {

			if (ans == null) {
				ans = s.substring(i, lastIndex);
			} else if (ans.length() >= lastIndex - i) {
				ans = s.substring(i, lastIndex);
			}
		}
		return memo[i][j] = lastIndex;
	}
	//=====================================================================
	//Bottom up Solution
	public String minWindow2(String s1, String s2) {
		int[][] memo = new int[s1.length() + 1][s2.length() + 1];

		for (int index1 = s1.length(); index1 >= 0; index1--) {

			for (int index2 = s2.length(); index2 >= 0; index2--) {

				if (index2 == s2.length()) {
					memo[index1][index2] = index1;
					continue;
				}

				if (index1 == s1.length()) {
					memo[index1][index2] = Integer.MAX_VALUE;
					continue;
				}
				int lastIndex = Integer.MAX_VALUE;

				if (s1.charAt(index1) == s2.charAt(index2)) {
					lastIndex = memo[index1 + 1][index2 + 1];
				}
				lastIndex = Math.min(lastIndex, memo[index1 + 1][index2]);

				if (index2 == 0 && lastIndex < Integer.MAX_VALUE) {
					if (ans == null) {
						ans = s1.substring(index1, lastIndex);
					} else if (ans.length() >= lastIndex - index1) {
						ans = s1.substring(index1, lastIndex);
					}
				}
				memo[index1][index2] = lastIndex;
			}
		}
		return ans == null ? "" : ans;
	}

	// ======================================================================
	// Sliding Window Solution
	public String minWindow1(String S, String T) {

		if (S == null || T == null || S.length() == 0 || T.length() == 0) {
			return "";
		}
		int fast = 0;
		int slow = 0;
		char chTofind = T.charAt(0);
		char[] tArr = T.toCharArray();
		char[] sArr = S.toCharArray();
		String ans = null;
		int counter = 0;

		while (fast < sArr.length) {
			char ch = sArr[fast];

			if (ch == tArr[counter]) {

				if (counter == 0) {
					slow = fast;
				}
				counter++;
			}

			if (counter == tArr.length) {

				if (ans == null) {
					ans = S.substring(slow, fast + 1);
				} else if (ans.length() > fast - slow + 1) {
					ans = S.substring(slow, fast + 1);
				}
				slow++;
				counter = 0;

				while (slow < sArr.length) {
					char sch = sArr[slow];

					if (sch == chTofind) {
						fast = slow - 1;
						break;
					}
					slow++;
				}
			}
			fast++;
		}
		return ans == null ? "" : ans;
	}
	//=============================================================================================
	//Another Sliding window approach
	public String minWindow3(String s1, String s2) {
		int fast = 0;
		int slow = 0;
		String ans = null;
		int targetIndex = 0;

		while (fast < s1.length()) {

			if (s1.charAt(fast) == s2.charAt(targetIndex)) {

				if (targetIndex == s2.length() - 1) {

					while (s1.charAt(slow) != s2.charAt(0)) {
						slow++;
					}

					if (ans == null) {
						ans = s1.substring(slow, fast + 1);
					} else {
						if (ans.length() > fast - slow + 1) {
							ans = s1.substring(slow, fast + 1);
						}
					}
					slow++;

					while (slow < s1.length() && s1.charAt(slow) != s2.charAt(0)) {
						slow++;
					}
					fast = slow - 1;
					targetIndex = 0;
				} else {
					targetIndex++;
				}
			}
			fast++;
		}
		return ans == null ? "" : ans;
	}
}
