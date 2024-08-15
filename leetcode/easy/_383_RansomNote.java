package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two stings ransomNote and magazine, return true if ransomNote
 *         can be constructed from magazine and false otherwise.
 * 
 *         Each letter in magazine can only be used once in ransomNote.
 * 
 *         Example 1:
 * 
 *         Input: ransomNote = "a", magazine = "b" Output: false 
 *         
 *         Example 2:
 * 
 *         Input: ransomNote = "aa", magazine = "ab" Output: false 
 *         
 *         Example 3:
 * 
 *         Input: ransomNote = "aa", magazine = "aab" Output: true
 * 
 * 
 *         Constraints:
 * 
 *         1 <= ransomNote.length, magazine.length <= 105 ransomNote and
 *         magazine consist of lowercase English letters.
 *
 */

public class _383_RansomNote {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean canConstruct(String ransomNote, String magazine) {
		Map<Character, Integer> magCharFreqMap = new HashMap<>();

		for (char ch : magazine.toCharArray()) {
			int freq = magCharFreqMap.getOrDefault(ch, 0);
			magCharFreqMap.put(ch, ++freq);
		}

		for (char ch : ransomNote.toCharArray()) {

			if (!magCharFreqMap.containsKey(ch)) {
				return false;
			}
			int freq = magCharFreqMap.get(ch);

			if (freq == 1) {
				magCharFreqMap.remove(ch);
			} else {
				magCharFreqMap.put(ch, --freq);
			}
		}
		return true;
	}
}
