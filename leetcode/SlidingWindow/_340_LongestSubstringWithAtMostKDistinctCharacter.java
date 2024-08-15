package leetcode.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         Given a string s and an integer k, return the length of the longest
 *         substring of s that contains at most k distinct characters.
 * 
 *         Example 1:
 * 
 *         Input: s = "eceba", k = 2 Output: 3 Explanation: The substring is
 *         "ece" with length 3.
 * 
 *         Example 2:
 * 
 *         Input: s = "aa", k = 1 Output: 2 Explanation: The substring is "aa"
 *         with length 2.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 5 * 104 0 <= k <= 50
 *
 */

public class _340_LongestSubstringWithAtMostKDistinctCharacter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int lengthOfLongestSubstringKDistinct(String s, int k) {

		if (s.length() == k) {
			return s.length();
		}
		int ans = 0;
		int slow = 0;
		int fast = 0;
		Map<Character, Integer> charFreqMap = new HashMap<>();

		while (fast < s.length()) {
			char ch = s.charAt(fast);
			int freq = charFreqMap.getOrDefault(ch, 0);
			charFreqMap.put(ch, ++freq);

			while (charFreqMap.size() > k && slow < fast) {

				if (charFreqMap.size() <= k) {
					ans = Math.max(ans, fast - slow + 1);
					break;
				}
				char sch = s.charAt(slow++);
				int sfreq = charFreqMap.getOrDefault(sch, 0);

				if (sfreq == 1) {
					charFreqMap.remove(sch);
					continue;
				}
				charFreqMap.put(sch, --sfreq);
			}

			if (charFreqMap.size() <= k) {
				ans = Math.max(ans, fast - slow + 1);
			}
			fast++;
		}
		return ans;
	}
}
