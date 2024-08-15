package leetcode.ForBiginners.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s consisting only of characters a, b and c.
 * 
 *         Return the number of substrings containing at least one occurrence of
 *         all these characters a, b and c.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "abcabc" Output: 10 Explanation: The substrings containing
 *         at least one occurrence of the characters a, b and c are "abc",
 *         "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and
 *         "abc" (again). 
 *         
 *         Example 2:
 * 
 *         Input: s = "aaacb" Output: 3 Explanation: The substrings containing
 *         at least one occurrence of the characters a, b and c are "aaacb",
 *         "aacb" and "acb". 
 *         
 *         Example 3:
 * 
 *         Input: s = "abc" Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         3 <= s.length <= 5 x 10^4 s only consists of a, b or c characters.
 *
 */

public class _1358_NumberOfSubStringContainingAll3Chars {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int numberOfSubstrings(String s) {
		Map<Character, Integer> charMap = new HashMap<>();
		charMap.put('a', 1);
		charMap.put('b', 1);
		charMap.put('c', 1);
		Map<Character, Integer> scharMap = new HashMap<>();
		int slow = 0;
		int fast = 0;
		int count = 0;
		int formed = charMap.size();

		while (fast < s.length()) {
			char fch = s.charAt(fast);

			if (charMap.containsKey(fch)) {
				int freq = scharMap.getOrDefault(fch, 0);
				scharMap.put(fch, ++freq);

				if (freq == charMap.get(fch)) {
					--formed;
				}
			}

			if (formed == 0) {

				while (slow <= fast && formed == 0) {
					count += 1;
					count += s.length() - 1 - fast;
					char sch = s.charAt(slow++);
					int sfreq = scharMap.get(sch);

					if (sfreq == 1) {
						scharMap.remove(sch);

						if (charMap.containsKey(sch)) {
							formed++;
						}
					} else {
						scharMap.put(sch, --sfreq);
					}
				}
			}
			fast++;
		}
		return count;
	}
}
