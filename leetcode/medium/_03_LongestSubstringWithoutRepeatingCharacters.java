package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *Given a string s, find the length of the longest substring without repeating characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Example 4:

Input: s = ""
Output: 0


Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
 */

public class _03_LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int lengthOfLongestSubstringSol1(final String s) {

		if (s == null || s.length() == 0) {
			return 0;
		}
		int slow = 0;
		int fast = 0;
		int maxLength = 0;
		final Map<Character, Integer> cache = new HashMap<>();

		for (; fast < s.length(); fast++) {
			char currChar = s.charAt(fast);

			while (cache.containsKey(currChar) && slow < fast) {
				char slowChar = s.charAt(slow);
				cache.remove(slowChar);
				slow++;
			}
			cache.put(currChar, fast);
			maxLength = Math.max(maxLength, fast - slow + 1);
		}
		return maxLength;
	}

	public int lengthOfLongestSubstringSol2(String s) {

		if( s == null || s.length() == 0 ) {
			return 0;
		}

		if( s.length() == 1 ) {
			return 1;
		}
		int anchor = 0;
		Map< Character, Integer > freq = new HashMap<>();
		int ans = 0;

		for( int i = 0; i < s.length(); i++ ) {
			char ch = s.charAt( i );
			int val = freq.getOrDefault( ch, 0 );
			freq.put( ch, ++val );

			if( val > 1 ) {
				ans = Math.max( ans, i - anchor );

				while( anchor < i ) {
					char ach = s.charAt( anchor++ );
					int aVal = freq.get( ach );

					if( aVal == 2 ) {
						freq.put( ach, --aVal );
						break;
					}
					freq.remove( ach );
				}
			}
		}
		ans = Math.max( ans, s.length() - 1 - anchor + 1 );
		return ans;
	}

	public int lengthOfLongestSubstring(String s) {
		Map<Character, Integer> freqMap = new HashMap<>();
		int slow = 0;
		int fast = 0;
		int longest = 0;

		for (; fast < s.length(); fast++) {
			char fCh = s.charAt(fast);
			int fFreq = freqMap.getOrDefault(fCh, 0);
			freqMap.put(fCh, ++fFreq);

			while (freqMap.get(fCh) == 2 && slow < fast) {
				char sCh = s.charAt(slow++);
				int sFreq = freqMap.get(sCh);
				freqMap.put(sCh, --sFreq);
			}
			longest = Math.max(longest, fast - slow + 1);
		}
		return longest;
	}
}
