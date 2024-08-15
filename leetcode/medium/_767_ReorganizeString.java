package leetcode.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, rearrange the characters of s so that any two
 *         adjacent characters are not the same.
 * 
 *         Return any possible rearrangement of s or return "" if not possible.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "aab" Output: "aba" 
 *         
 *         Example 2:
 * 
 *         Input: s = "aaab" Output: ""
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 500 s consists of lowercase English letters.
 *
 */

public class _767_ReorganizeString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String reorganizeString(String s) {
		Map<Character, Integer> freqMap = new HashMap<>();

		for (char ch : s.toCharArray()) {
			freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
		}
		PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {
			public int compare(Character ch1, Character ch2) {
				return freqMap.get(ch2) - freqMap.get(ch1);
			}
		});
		pq.addAll(freqMap.keySet());
		StringBuilder ans = new StringBuilder();

		while (!pq.isEmpty() && pq.size() >= 2) {
			char ch1 = pq.poll();
			char ch2 = pq.poll();
			ans.append(ch1);
			ans.append(ch2);
			int freq1 = freqMap.get(ch1);
			int freq2 = freqMap.get(ch2);

			if (freq1 == 1) {
				freqMap.remove(ch1);
			} else {
				freqMap.put(ch1, --freq1);
				pq.offer(ch1);
			}

			if (freq2 == 1) {
				freqMap.remove(ch2);
			} else {
				freqMap.put(ch2, --freq2);
				pq.offer(ch2);
			}
		}

		if (!pq.isEmpty()) {
			char ch = pq.poll();
			int freq = freqMap.get(ch);

			if (ans.length() == 0 && freq == 1) {
				return "" + ch;
			} else if (ans.length() > 0 && freq == 1 && ans.charAt(ans.length() - 1) != ch) {
				ans.append(ch);
			} else {
				return "";
			}
		}
		return ans.toString();
	}
}
