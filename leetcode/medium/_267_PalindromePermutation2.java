package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, return all the palindromic permutations (without
 *         duplicates) of it.
 * 
 *         You may return the answer in any order. If s has no palindromic
 *         permutation, return an empty list.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "aabb" Output: ["abba","baab"] Example 2:
 * 
 *         Input: s = "abc" Output: []
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 16 s consists of only lowercase English letters.
 *
 */

public class _267_PalindromePermutation2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<String> generatePalindromes(String s) {
		List<String> ans = new ArrayList<>();

		if (s != null) {
			Map<Character, Integer> freqMap = new HashMap<>();

			for (char ch : s.toCharArray()) {
				int freq = freqMap.getOrDefault(ch, 0);
				freqMap.put(ch, ++freq);
			}

			if (canBePalindrome(freqMap)) {
				StringBuilder left = new StringBuilder();
				StringBuilder right = new StringBuilder();
				recur(freqMap, left, right, ans, s.length());
			}
		}
		return ans;
	}

	private void recur(Map<Character, Integer> freqMap, StringBuilder left, 
			StringBuilder right, List<String> ans, int length) {
		if (left.length() + right.length() == length - 1) {

			for (char ch : freqMap.keySet()) {

				if (freqMap.get(ch) == 1) {
					String string = left.toString() + ch + right.toString();
					ans.add(string);
					return;
				}
			}
		}

		if (left.length() + right.length() == length) {
			String string = left.toString() + right.toString();
			ans.add(string);
			return;
		}

		for (char ch : freqMap.keySet()) {
			int freq = freqMap.get(ch);

			if (freq >= 2) {
				left.append(ch);
				right.insert(0, ch);
				freqMap.put(ch, freq - 2);
				recur(freqMap, left, right, ans, length);
				freqMap.put(ch, freq);
				left.deleteCharAt(left.length() - 1);
				right.deleteCharAt(0);
			}
		}
	}

	private boolean canBePalindrome(Map<Character, Integer> freqMap) {
		int oddCount = 0;
		Set<Character> keys = freqMap.keySet();

		for (char key : keys) {

			if (freqMap.get(key) % 2 != 0) {
				oddCount++;
			}
		}
		return oddCount <= 1;
	}
}
