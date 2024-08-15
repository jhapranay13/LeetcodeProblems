package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s and an integer k. You should construct k non-empty
 *         palindrome strings using all the characters in s.
 * 
 *         Return True if you can use all the characters in s to construct k
 *         palindrome strings or False otherwise.
 * 
 *         Example 1:
 * 
 *         Input: s = "annabelle", k = 2 Output: true Explanation: You can
 *         construct two palindromes using all characters in s. Some possible
 *         constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
 *         
 *         Example 2:
 * 
 *         Input: s = "leetcode", k = 3 Output: false Explanation: It is
 *         impossible to construct 3 palindromes using all the characters of s.
 *         
 *         Example 3:
 * 
 *         Input: s = "true", k = 4 Output: true Explanation: The only possible
 *         solution is to put each character in a separate string. 
 *         
 *         Example 4:
 * 
 *         Input: s = "yzyzyzyzyzyzyzy", k = 2 Output: true Explanation: Simply
 *         you can put all z's in one string and all y's in the other string.
 *         Both strings will be palindrome. 
 *         
 *         Example 5:
 * 
 *         Input: s = "cr", k = 7 Output: false Explanation: We don't have
 *         enough characters in s to construct 7 palindromes.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 10^5 All characters in s are lower-case English
 *         letters. 1 <= k <= 10^5
 *
 */

public class _1400_ConstructKPalindromicStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean canConstruct(String s, int k) {
		if (s.length() < k) {
			return false;
		}

		if (s.length() == k) {
			return true;
		}
		Map<Character, Integer> freqMap = new HashMap<>();

		for (char ch : s.toCharArray()) {
			freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
		}
		int oddCount = 0;

		for (Map.Entry<Character,Integer> entry : freqMap.entrySet()) {
			if (entry.getValue() % 2 != 0) {
				oddCount++;
			}
		}

		if (oddCount > k) {
			return false;
		}
		return true;
	}
}
