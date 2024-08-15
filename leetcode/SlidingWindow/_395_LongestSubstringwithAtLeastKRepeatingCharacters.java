package leetcode.SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * Given a string s and an integer k, return the length of the longest substring of s such
 * that the frequency of each character in this substring is greater than or equal to k.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 *
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of only lowercase English letters.
 * 1 <= k <= 105
 *
 *
 */

public class _395_LongestSubstringwithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        Set<Character> unique = new HashSet<>();

        for (char ch : s.toCharArray()) {
            unique.add(ch);
        }
        int uniqueCharCount = unique.size();
        int ans = 0;
        // We take the unique count as shrink parameter in order to use the
        //sliding window. So we can calclate for all the unique values and
        //combination
        for (int i = 1; i <= uniqueCharCount; i++) {
            int slow = 0;
            int fast = 0;
            Map<Character, Integer> freqMap = new HashMap<>();
            int formed = 0;

            while (fast < s.length()) {
                char fch = s.charAt(fast);
                int freq = freqMap.getOrDefault(fch, 0);
                freqMap.put(fch, ++freq);

                if (freq == k) {
                    formed++;
                }

                while (freqMap.size() > i && slow < fast) {
                    char sch = s.charAt(slow++);
                    int sfreq = freqMap.get(sch);

                    if (sfreq == k) {
                        formed--;
                    }
                    if (sfreq > 1) {
                        freqMap.put(sch, --sfreq);
                    } else {
                        freqMap.remove(sch);
                    }
                }

                if (freqMap.size() == i && formed == i) {
                    ans = Math.max(ans, fast - slow + 1);
                }
                fast++;
            }
        }
        return ans;
    }
}
