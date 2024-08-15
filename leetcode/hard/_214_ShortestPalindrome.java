package leetcode.hard;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given a string s. You can convert s to a palindrome by adding
 *         characters in front of it.
 * 
 *         Return the shortest palindrome you can find by performing this
 *         transformation.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "aacecaaa" Output: "aaacecaaa" Example 2:
 * 
 *         Input: s = "abcd" Output: "dcbabcd"
 * 
 * 
 *         Constraints:
 * 
 *         0 <= s.length <= 5 * 104 s consists of lowercase English letters
 *         only.
 *
 */

public class _214_ShortestPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String shortestPalindrome(String s) {

		if (s == null || s.length() <= 1) {
			return s;
		}
		char[] arr = s.toCharArray();

		if (isPalindrome(arr)) {
			return s;
		}
		int index = largestPalindrome(arr);
		StringBuilder res = new StringBuilder();
		int curr = arr.length - 1;

		while (index < curr) {
			res.append(arr[curr--]);
		}
		return res.toString() + s;
	}

	public int largestPalindrome(char[] s) {
		int index = -1;
		int size = 0;

		for (int i = 0; i <= s.length / 2; i++) {
			int temp = Math.max(check(s, i, i + 1), check(s, i, i));
			index = Math.max(temp, index);
		}
		return index;
	}

	// Expanding from center
	public int check(char[] s, int i, int j) {
		int maxIndex = j;
		int minIndex = i;

		while (i >= 0 && j < s.length) {

			if (s[i] == s[j]) {
				maxIndex = j;
				minIndex = i;
				i--;
				j++;
			} else {
				return -1;
			}
		}

		if (minIndex != 0) {
			return -1;
		}
		return maxIndex;
	}

	public boolean isPalindrome(char[] s) {
		int lo = 0;
		int hi = s.length - 1;

		while (lo <= hi) {

			if (s[lo++] != s[hi--]) {
				return false;
			}
		}
		return true;
	}
}
