package leetcode.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a string s, return the length of the longest
 * substring
 *  that contains at most two distinct characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "eceba"
 * Output: 3
 * Explanation: The substring is "ece" which its length is 3.
 * Example 2:
 *
 * Input: s = "ccaabbb"
 * Output: 5
 * Explanation: The substring is "aabbb" which its length is 5.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of English letters.
 *
 */

public class _159_Longest_Substring_with_At_Most_Two_Distinct_Characters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        int slow = 0;
        int fast = 0;
        int ans = 0;

        while (fast < s.length()) {
            char ch = s.charAt(fast);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

            while (freqMap.size() > 2) {
                char sch = s.charAt(slow);
                int sfreq = freqMap.get(sch);

                if (sfreq == 1) {
                    freqMap.remove(sch);
                } else {
                    freqMap.put(sch, sfreq - 1);
                }
                slow++;
            }
            ans = Math.max(ans, fast - slow + 1);
            fast++;
        }
        return ans;
    }

}
