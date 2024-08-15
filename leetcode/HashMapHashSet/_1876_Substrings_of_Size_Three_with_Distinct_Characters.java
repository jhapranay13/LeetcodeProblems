package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * A string is good if there are no repeated characters.
 *
 * Given a string s​​​​​, return the number of good substrings of length three in s​​​​​​.
 *
 * Note that if there are multiple occurrences of the same substring, every occurrence should be counted.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "xyzzaz"
 * Output: 1
 * Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz".
 * The only good substring of length 3 is "xyz".
 * Example 2:
 *
 * Input: s = "aababcabc"
 * Output: 4
 * Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
 * The good substrings are "abc", "bca", "cab", and "abc".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s​​​​​​ consists of lowercase English letters.
 *
 */

public class _1876_Substrings_of_Size_Three_with_Distinct_Characters {
    public int countGoodSubstrings(String s) {
        int ans = 0;
        int slow = 0;
        int fast = 0;
        Map<Character, Integer> freqMap = new HashMap<>();

        while (fast < s.length()) {
            char fch = s.charAt(fast);
            freqMap.put(fch, freqMap.getOrDefault(fch, 0) + 1);

            if (fast - slow + 1 == 3) {

                if (freqMap.size() == 3) {
                    ans++;
                }
                char sch = s.charAt(slow++);

                if (freqMap.get(sch) == 1) {
                    freqMap.remove(sch);
                } else {
                    freqMap.put(sch, freqMap.get(sch) - 1);
                }
            }
            fast++;
        }
        return ans;
    }
}
