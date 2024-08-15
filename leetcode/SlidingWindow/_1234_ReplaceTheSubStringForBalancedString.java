package leetcode.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given a string containing only 4 kinds of characters 'Q',
 *         'W', 'E' and 'R'.
 * 
 *         A string is said to be balanced if each of its characters appears n/4
 *         times where n is the length of the string.
 * 
 *         Return the minimum length of the substring that can be replaced with
 *         any other string of the same length to make the original string s
 *         balanced.
 * 
 *         Return 0 if the string is already balanced.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "QWER" Output: 0 Explanation: s is already balanced.
 * 
 *         Example 2:
 * 
 *         Input: s = "QQWE" Output: 1 Explanation: We need to replace a 'Q' to
 *         'R', so that "RQWE" (or "QRWE") is balanced.
 * 
 *         Example 3:
 * 
 *         Input: s = "QQQW" Output: 2 Explanation: We can replace the first
 *         "QQ" to "ER".
 * 
 *         Example 4:
 * 
 *         Input: s = "QQQQ" Output: 3 Explanation: We can replace the last 3
 *         'Q' to make s = "QWER".
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 10^5 s.length is a multiple of 4 s contains only
 *         'Q', 'W', 'E' and 'R'.
 *
 */

public class _1234_ReplaceTheSubStringForBalancedString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int balancedString(String s) {
		int maxCount = s.length() / 4;
		Map<Character, Integer> freqMap = new HashMap<>();

		for (char ch : s.toCharArray()) {
			freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
		}

		if (freqMap.size() == 1) {
			for (char ch : freqMap.keySet()) {
				return freqMap.get(ch) - maxCount;
			}
		}
		StringBuilder extraChar = new StringBuilder();
		// Creating Pattern
		for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {

			if (entry.getValue() > maxCount) {
				int freq = entry.getValue();

				while (freq-- > maxCount) {
					extraChar.append(entry.getKey());
				}
			}
		}
		// Find out minimum window substring 
		int minLength = getMinLength(extraChar.toString(), s);
		return minLength;
	}
	//Based on Minimum window Substring problem
	private int getMinLength(String s, String t) {
		Map<Character, Integer> freqMapS = new HashMap<>();

		for (char ch : s.toCharArray()) {
			freqMapS.put(ch, freqMapS.getOrDefault(ch, 0) + 1);
		}
		int formed = freqMapS.size();
		Map<Character, Integer> freqMapT = new HashMap<>();
		int fast = 0;
		int slow = 0;
		int ans = Integer.MAX_VALUE;
		;

		for (; fast < t.length(); fast++) {
			char charF = t.charAt(fast);

			if (freqMapS.containsKey(charF)) {
				freqMapT.put(charF, freqMapT.getOrDefault(charF, 0) + 1);

				if (freqMapT.get(charF) == freqMapS.get(charF)) {
					formed--;
				}
			}

			if (freqMapS.containsKey(charF) && formed == 0) {

				while (slow <= fast && formed == 0) {
					ans = Math.min(ans, fast - slow + 1);

					char sch = t.charAt(slow);

					if (freqMapS.containsKey(sch)) {
						freqMapT.put(sch, freqMapT.get(sch) - 1);
					}

					if (freqMapS.containsKey(sch) && freqMapT.get(sch) < freqMapS.get(sch)) {
						formed++;
					}
					slow++;
				}
			}
		}
		return ans == Integer.MAX_VALUE ? 0 : ans;
	}
}
