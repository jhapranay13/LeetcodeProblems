package leetcode.ForBiginners.SlidingWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given a string s and an integer k. You can choose any
 *         character of the string and change it to any other uppercase English
 *         character. You can perform this operation at most k times.
 * 
 *         Return the length of the longest substring containing the same letter
 *         you can get after performing the above operations.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "ABAB", k = 2 Output: 4 Explanation: Replace the two 'A's
 *         with two 'B's or vice versa. 
 *         
 *         Example 2:
 * 
 *         Input: s = "AABABBA", k = 1 Output: 4 Explanation: Replace the one
 *         'A' in the middle with 'B' and form "AABBBBA". The substring "BBBB"
 *         has the longest repeating letters, which is 4.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 105 s consists of only uppercase English letters. 0
 *         <= k <= s.length
 */

public class _424_LongestRepeatingCharacterReplacement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	public int characterReplacement(String s, int k) {
		Map<Character, Integer> freq = new HashMap<>();
		int slow = 0;
		int fast = 0;
		int ans = 0;
		int maxFreq = 0;

		while (fast < s.length()) {
			char ch = s.charAt(fast);
			int frq = freq.getOrDefault(ch, 0);
			freq.put(ch, ++frq);
			maxFreq = Math.max(maxFreq, frq);
			int currLength = fast - slow + 1;

			while (currLength - maxFreq > k) {
				char sch = s.charAt(slow++);
				int sfrq = freq.get(sch);

				if (sfrq == 1) {
					freq.remove(sch);
				} else {
					freq.put(sch, --sfrq);
				}
				currLength = fast - slow + 1;
			}
			ans = Math.max(ans, fast - slow + 1);
			fast++;
		}
		return ans;
	}

	// =============================================================================
	// Another slow version
	public int characterReplacement1(String s, int k) {
		int fast = 0;
		int slow = 0;
		Map<Character, Integer> freq = new HashMap<>();
		int ans = 0;

		while (fast < s.length()) {
			char ch = s.charAt(fast);
			freq.put(ch, freq.getOrDefault(ch, 0) + 1);
			int max = getMax(freq);
			int currLength = fast - slow + 1;

			if (currLength - max <= k) {
				ans = Math.max(ans, currLength);
			} else {

				while (!(currLength - max <= k)) {
					char sch = s.charAt(slow++);
					int f = freq.get(sch);

					if (f == 1) {
						freq.remove(sch);
					} else {
						freq.put(sch, f - 1);
					}
					max = getMax(freq);
					currLength = fast - slow + 1;
				}
			}
			fast++;
		}
		return ans;
	}

	private int getMax(Map<Character, Integer> freq) {
		Set<Character> keys = freq.keySet();
		int ans = Integer.MIN_VALUE;

		for (char key : keys) {
			ans = Math.max(ans, freq.get(key));
		}
		return ans;
	}
}
