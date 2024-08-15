package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a string s and an integer k, return the number of substrings in s of length k with no repeated characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "havefunonleetcode", k = 5
 * Output: 6
 * Explanation: There are 6 substrings they are: 'havef','avefu','vefun','efuno','etcod','tcode'.
 * Example 2:
 *
 * Input: s = "home", k = 5
 * Output: 0
 * Explanation: Notice k can be larger than the length of s. In this case, it is not possible to find any substring.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of lowercase English letters.
 * 1 <= k <= 10^4
 *
 */

public class _1100_Find_K_Length_Substrings_With_No_Repeated_Characters {
    public int numKLenSubstrNoRepeats(String s, int k) {
        Map<Character, Integer> freqMap = new HashMap<>();
        int ans = 0;
        int slow = 0;
        int fast = 0;

        while (fast < s.length()) {
            char ch = s.charAt(fast);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

            while (slow < fast && fast - slow + 1 > freqMap.size() || fast - slow + 1 > k) {
                char sch = s.charAt(slow++);

                if (freqMap.get(sch) == 1) {
                    freqMap.remove(sch);
                } else {
                    freqMap.put(sch, freqMap.get(sch) - 1);
                }
            }

            if (fast - slow + 1 == k) {
                ans++;
            }
            fast++;
        }
        return ans;
    }
}
