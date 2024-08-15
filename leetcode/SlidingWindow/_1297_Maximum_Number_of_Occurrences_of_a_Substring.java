package leetcode.SlidingWindow;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:
 *
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 *
 *
 * Example 1:
 *
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 ocurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
 * Example 2:
 *
 * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * Output: 2
 * Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s consists of only lowercase English letters.
 *
 */

public class _1297_Maximum_Number_of_Occurrences_of_a_Substring {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<Character, Integer> freqMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();
        int fast = 0;
        int slow = 0;

        while (fast < s.length()) {
            char ch = s.charAt(fast);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

            while (freqMap.size() > maxLetters || fast - slow + 1 > maxSize) {
                char sch = s.charAt(slow++);
                freqMap.put(sch, freqMap.getOrDefault(sch, 0) - 1);

                if (freqMap.get(sch) == 0) {
                    freqMap.remove(sch);
                }
            }

            while (freqMap.size() <= maxLetters && fast - slow + 1 >= minSize &&
                    fast - slow + 1 <= maxSize) {

                String str = s.substring(slow, fast + 1);
                countMap.put(str, countMap.getOrDefault(str, 0) + 1);
                char sch = s.charAt(slow);
                freqMap.put(sch, freqMap.getOrDefault(sch, 0) - 1);

                if (freqMap.get(sch) == 0) {
                    freqMap.remove(sch);
                }
                slow++;
            }
            fast++;
        }
        int ans = 0;

        for (String key : countMap.keySet()) {
            ans = Math.max(ans, countMap.get(key));
        }
        return ans;
    }
}
