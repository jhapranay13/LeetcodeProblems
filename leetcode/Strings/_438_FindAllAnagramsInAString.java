package leetcode.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two strings s and p, return an array of all the start indices
 *         of p's anagrams in s. You may return the answer in any order.
 * 
 *         Example 1:
 * 
 *         Input: s = "cbaebabacd", p = "abc" Output: [0,6] Explanation: The
 *         substring with start index = 0 is "cba", which is an anagram of
 *         "abc". The substring with start index = 6 is "bac", which is an
 *         anagram of "abc". 
 *         
 *         Example 2:
 * 
 *         Input: s = "abab", p = "ab" Output: [0,1,2] Explanation: The
 *         substring with start index = 0 is "ab", which is an anagram of "ab".
 *         The substring with start index = 1 is "ba", which is an anagram of
 *         "ab". The substring with start index = 2 is "ab", which is an anagram
 *         of "ab".
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length, p.length <= 3 * 104 s and p consist of lowercase
 *         English letters.
 *
 */

public class _438_FindAllAnagramsInAString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> ans = new ArrayList<>();
		Map<Character, Integer> pFreqMap = new HashMap<>();

		for (Character ch : p.toCharArray()) {
			int freq = pFreqMap.getOrDefault(ch, 0);
			pFreqMap.put(ch, ++freq);
		}
		Map<Character, Integer> sFreqMap = new HashMap<>();
		int slow = 0;
		int fast = 0;

		while (fast < s.length()) {
			char ch = s.charAt(fast);
			int freq = sFreqMap.getOrDefault(ch, 0);
			sFreqMap.put(ch, ++freq);

			if (sFreqMap.equals(pFreqMap)) {
				ans.add(slow);
			}

			while (fast - slow + 1 >= p.length()) {
				char sch = s.charAt(slow);
				int sfreq = sFreqMap.get(sch);

				if (sfreq == 1) {
					sFreqMap.remove(sch);
				} else {
					sFreqMap.put(sch, --sfreq);
				}
				slow++;
			}
			fast++;
		}
		return ans;
	}
}
