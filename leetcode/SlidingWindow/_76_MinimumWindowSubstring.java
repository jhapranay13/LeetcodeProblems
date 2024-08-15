package leetcode.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two strings s and t of lengths m and n respectively, return the
 *         minimum window substring of s such that every character in t
 *         (including duplicates) is included in the window. If there is no such
 *         substring, return the empty string "".
 * 
 *         The testcases will be generated such that the answer is unique.
 * 
 *         A substring is a contiguous sequence of characters within the string.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "ADOBECODEBANC", t = "ABC" Output: "BANC" Explanation: The
 *         minimum window substring "BANC" includes 'A', 'B', and 'C' from
 *         string t. Example 2:
 * 
 *         Input: s = "a", t = "a" Output: "a" Explanation: The entire string s
 *         is the minimum window. Example 3:
 * 
 *         Input: s = "a", t = "aa" Output: "" Explanation: Both 'a's from t
 *         must be included in the window. Since the largest window of s only
 *         has one 'a', return empty string.
 * 
 * 
 *         Constraints:
 * 
 *         m == s.length n == t.length 1 <= m, n <= 105 s and t consist of
 *         uppercase and lowercase English letters.
 * 
 * 
 *         Follow up: Could you find an algorithm that runs in O(m + n) time?
 *
 */

public class _76_MinimumWindowSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String minWindow(String s, String t) {
		Map<Character, Integer> tfreq = new HashMap<>();
		Map<Character, Integer> sfreq = new HashMap<>();

		for (char ch : t.toCharArray()) {
			tfreq.put(ch, tfreq.getOrDefault(ch, 0) + 1);
		}
		int required = tfreq.size();
		int min = Integer.MAX_VALUE;
		int fast = 0;
		int slow = 0;
		int start = -1;

		while (fast < s.length()) {
			char ch = s.charAt(fast);

			if (tfreq.containsKey(ch)) {
				int val = sfreq.getOrDefault(ch, 0);
				sfreq.put(ch, ++val);
			}

			if (tfreq.containsKey(ch) && sfreq.get(ch).intValue() == tfreq.get(ch).intValue()) {
				required--;
			}

			while (required == 0 && fast >= slow) {
				char sch = s.charAt(slow);

				if (fast - slow < min) {
					min = fast - slow;
					start = slow;
				}

				if (sfreq.containsKey(sch)) {
					int val = sfreq.get(sch);
					sfreq.put(sch, --val);

					if (val < tfreq.get(sch).intValue()) {
						required++;
					}
				}
				slow++;
			}
			fast++;
		}
		return start == -1 ? "" : s.substring(start, min + start + 1);
	}
}
