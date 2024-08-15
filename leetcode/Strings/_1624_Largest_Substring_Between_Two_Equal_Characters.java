package leetcode.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aa"
 * Output: 0
 * Explanation: The optimal substring here is an empty substring between the two 'a's.
 * Example 2:
 *
 * Input: s = "abca"
 * Output: 2
 * Explanation: The optimal substring here is "bc".
 * Example 3:
 *
 * Input: s = "cbzxy"
 * Output: -1
 * Explanation: There are no characters that appear twice in s.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 300
 * s contains only lowercase English letters.
 *
 */

public class _1624_Largest_Substring_Between_Two_Equal_Characters {
    public int maxLengthBetweenEqualCharacters(String s) {
        Map<Character, Integer> lastposMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            lastposMap.put(ch, i);
        }
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int lastPos = lastposMap.get(ch);
            ans = Math.max(ans, lastPos - i - 1);
        }
        return ans;
    }
}
