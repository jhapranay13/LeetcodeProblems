package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s which consists of lowercase or uppercase letters,
 *         return the length of the longest palindrome that can be built with
 *         those letters.
 * 
 *         Letters are case sensitive, for example, "Aa" is not considered a
 *         palindrome here.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "abccccdd" Output: 7 Explanation: One longest palindrome
 *         that can be built is "dccaccd", whose length is 7. 
 *         
 *         Example 2:
 * 
 *         Input: s = "a" Output: 1 Example 3:
 * 
 *         Input: s = "bb" Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 2000 s consists of lowercase and/or uppercase
 *         English letters only.
 *
 */

public class _409_LongestPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int longestPalindrome(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        
        for (char ch : s.toCharArray()) {
            int freq = freqMap.getOrDefault(ch, 0);
            freqMap.put(ch, ++freq);
        }
        int ans = 0;
        
        for (Map.Entry<Character,Integer> entry : freqMap.entrySet()) {
            ans += entry.getValue() / 2  * 2;
        }
        return ans == s.length() ? ans : ans + 1;
    }
}
