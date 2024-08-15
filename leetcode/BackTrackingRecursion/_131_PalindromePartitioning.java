package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

A palindrome string is a string that reads the same backward as forward.



Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Example 2:

Input: s = "a"
Output: [["a"]]

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
 *
 */

public class _131_PalindromePartitioning {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<String>> partition(String s) {
		List<List<String>> ans = new ArrayList<>();
		ArrayList<String> partial = new ArrayList();
		recur(s, ans, partial, 0);
		return ans;
	}

	private void recur(String s, List<List<String>> ans, ArrayList<String> partial,
			int index) {

		if (index == s.length()) {

			if (partial.size() > 0) {
				ans.add((ArrayList<String>)partial.clone());
			}
			return;
		}

		for (int i = index + 1; i <= s.length(); i++) {
			String curr = s.substring(index, i);

			if (isPalindrome(curr)) {
				partial.add(curr);
				recur(s, ans, partial, i);
				partial.remove(partial.size() - 1);
			}
		}
	}

	private boolean isPalindrome(String str) {
		int lo = 0;
		int hi = str.length() - 1;

		while (lo < hi) {

			if (str.charAt(lo++) != str.charAt(hi--)) {
				return false;
			}
		}
		return true;
	}
}
