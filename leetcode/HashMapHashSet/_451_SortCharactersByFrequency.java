package leetcode.HashMapHashSet;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, sort it in decreasing order based on the frequency
 *         of characters, and return the sorted string.
 * 
 *         Example 1:
 * 
 *         Input: s = "tree" Output: "eert" Explanation: 'e' appears twice while
 *         'r' and 't' both appear once. So 'e' must appear before both 'r' and
 *         't'. Therefore "eetr" is also a valid answer. 
 *         
 *         Example 2:
 * 
 *         Input: s = "cccaaa" Output: "aaaccc" Explanation: Both 'c' and 'a'
 *         appear three times, so "aaaccc" is also a valid answer. Note that
 *         "cacaca" is incorrect, as the same characters must be together.
 *         
 *         Example 3:
 * 
 *         Input: s = "Aabb" Output: "bbAa" Explanation: "bbaA" is also a valid
 *         answer, but "Aabb" is incorrect. Note that 'A' and 'a' are treated as
 *         two different characters.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 5 * 105 s consists of English letters and digits.
 *
 */

public class _451_SortCharactersByFrequency {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String frequencySort(String s) {
		Map<Character, Integer> freqMap = new HashMap<>();

		for (char ch : s.toCharArray()) {
			int freq = freqMap.getOrDefault(ch, 0);
			freqMap.put(ch, ++freq);
		}
		PriorityQueue<Character> pq = new PriorityQueue(new Comparator<Character>() {
			public int compare(Character ch1, Character ch2) {
				return freqMap.get(ch2) - freqMap.get(ch1);
			}
		});

		for (char ch : freqMap.keySet()) {
			pq.offer(ch);
		}
		StringBuilder ans = new StringBuilder();

		while (!pq.isEmpty()) {
			char ch = pq.poll();
			int freq = freqMap.get(ch);

			while (freq-- > 0) {
				ans.append(ch);
			}
		}
		return ans.toString();
	}
}
