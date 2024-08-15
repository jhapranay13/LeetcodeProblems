package leetcode.ForBiginners.DynamicProgramming.LongestCommonSubsequence;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two strings s1 and s2, return the lowest ASCII sum of deleted
 *         characters to make two strings equal.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s1 = "sea", s2 = "eat" Output: 231 Explanation: Deleting "s"
 *         from "sea" adds the ASCII value of "s" (115) to the sum. Deleting "t"
 *         from "eat" adds 116 to the sum. At the end, both strings are equal,
 *         and 115 + 116 = 231 is the minimum sum possible to achieve this.
 *         Example 2:
 * 
 *         Input: s1 = "delete", s2 = "leet" Output: 403 Explanation: Deleting
 *         "dee" from "delete" to turn the string into "let", adds 100[d] +
 *         101[e] + 101[e] to the sum. Deleting "e" from "leet" adds 101[e] to
 *         the sum. At the end, both strings are equal to "let", and the answer
 *         is 100+101+101+101 = 403. If instead we turned both strings into
 *         "lee" or "eet", we would get answers of 433 or 417, which are higher.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s1.length, s2.length <= 1000 s1 and s2 consist of lowercase
 *         English letters.
 *
 */

public class _712_MinimumAsciiDeleteSumForTwoStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top Down approach
	public int minimumDeleteSum(String s1, String s2) {
		int[][] memo = new int[s1.length()][s2.length()];
		return recur(s1, s2, 0, 0, memo);
	}

	private int recur(String s1, String s2, int index1, int index2, int[][] memo) {
		int sum = Integer.MAX_VALUE;

		if (index1 == s1.length()) {
			sum = 0;

			while (index2 < s2.length()) {
				sum += s2.charAt(index2++);
			}
			return sum;
		}

		if (index2 == s2.length()) {
			sum = 0;

			while (index1 < s1.length()) {
				sum += s1.charAt(index1++);
			}
			return sum;
		}

		if (memo[index1][index2] > 0) {
			return memo[index1][index2];
		}

		if (s1.charAt(index1) == s2.charAt(index2)) {
			sum = Math.min(sum ,recur(s1, s2, index1 + 1, index2 + 1, memo));
		} else {
			sum = Math.min( sum, 
					(Math.min(recur(s1, s2, index1 + 1, index2, memo) + s1.charAt(index1), 
							recur(s1, s2, index1, index2 + 1, memo) + s2.charAt(index2))));
		}
		return memo[index1][index2] = sum;
	}
	//==============================================================================
	//Bottom Up Approach
	public int minimumDeleteSum1(String s1, String s2) {
		int[][] memo = new int[s1.length() + 1][s2.length() + 1];

		for (int index1 = s1.length(); index1 >= 0; index1--) {
			for (int index2 = s2.length(); index2 >= 0; index2--) {
				int sum = Integer.MAX_VALUE;

				if (index1 == s1.length()) {
					sum = 0;
					int i = index2;

					while (i < s2.length()) {
						sum += s2.charAt(i++);
					}
					memo[index1][index2] = sum;
					continue;
				}

				if (index2 == s2.length()) {
					sum = 0;
					int i = index1;

					while (i < s1.length()) {
						sum += s1.charAt(i++);
					}
					memo[index1][index2] = sum;
					continue;
				}

				if (s1.charAt(index1) == s2.charAt(index2)) {
					sum = Math.min(sum ,memo[index1 + 1][index2 + 1]);
				} else {
					sum = Math.min( sum, (Math.min(memo[index1 + 1][index2] +                                             s1.charAt(index1), 
							memo[index1][index2 + 1] + s2.charAt(index2))));
				}
				memo[index1][index2] = sum;
			}
		}
		return memo[0][0];
	}
}
