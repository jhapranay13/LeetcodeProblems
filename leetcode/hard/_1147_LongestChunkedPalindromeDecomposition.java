package leetcode.hard;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given a string text. You should split it to k substrings
 *         (subtext1, subtext2, ..., subtextk) such that:
 * 
 *         subtexti is a non-empty string. The concatenation of all the
 *         substrings is equal to text (i.e., subtext1 + subtext2 + ... +
 *         subtextk == text). subtexti == subtextk - i + 1 for all valid values
 *         of i (i.e., 1 <= i <= k). Return the largest possible value of k.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: text = "ghiabcdefhelloadamhelloabcdefghi" Output: 7
 *         Explanation: We can split the string on
 *         "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)".
 * 
 *         Example 2:
 * 
 *         Input: text = "merchant" Output: 1 Explanation: We can split the
 *         string on "(merchant)".
 * 
 *         Example 3:
 * 
 *         Input: text = "antaprezatepzapreanta" Output: 11 Explanation: We can
 *         split the string on "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)".
 * 
 * 
 *         Example 4:
 * 
 *         Input: text = "aaa" Output: 3 Explanation: We can split the string on
 *         "(a)(a)(a)".
 * 
 * 
 *         Constraints:
 * 
 *         1 <= text.length <= 1000 text consists only of lowercase English
 *         characters.
 *
 */

public class _1147_LongestChunkedPalindromeDecomposition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int longestDecomposition(String text) {

		if (text == null || text.length() == 0) {
			return 0;
		}
		return rec(text, 0, text.length() - 1);
	}

	private int rec(String str, int lo, int hi) {

		if (lo == hi) {
			return 1;
		}

		if (lo > hi) {
			return 0;
		}
		int ans = 0;

		for (int i = lo + 1, j = hi; i <= j; i++, j--) {
			String str1 = str.substring(lo, i);
			String str2 = str.substring(j, hi + 1);

			if (str1.equals(str2)) {
				return 2 + rec(str, i, j - 1);
			}
		}
		return ans + 1;
	}
}
