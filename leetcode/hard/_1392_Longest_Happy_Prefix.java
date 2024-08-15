package leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * A string is called a happy prefix if is a non-empty prefix which is also a suffix (excluding itself).
 *
 * Given a string s, return the longest happy prefix of s. Return an empty string "" if no such prefix exists.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "level"
 * Output: "l"
 * Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"), and suffix ("l", "el", "vel", "evel"). The largest prefix which is also suffix is given by "l".
 * Example 2:
 *
 * Input: s = "ababab"
 * Output: "abab"
 * Explanation: "abab" is the largest prefix which is also suffix. They can overlap in the original string.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s contains only lowercase English letters.
 *
 */

public class _1392_Longest_Happy_Prefix {
    // Rabin Karp implementation
    public String longestPrefix(String s) {
        long[] power = new long[s.length()];
        int prime = 113;
        power[s.length() - 1] = 1;

        for (int i = s.length() - 2; i >= 0; i--) {
            power[i] = power[i + 1] * prime;
        }
        long hash = 0;
        Set<Long> hashHolder = new HashSet<>();
        // suffix hash Calculation
        for (int i = s.length() - 1; i >= 0; i--) {
            hash += power[i] * s.charAt(i);
            hashHolder.add(hash);
        }
        hash = 0;
        String ans = "";
        // prefix hash Calculation
        for (int i = 0; i < s.length() - 1; i++) {

            if (i == 0) {
                hash += s.charAt(i);
            } else {
                hash = hash * prime + s.charAt(i);
            }

            if (hashHolder.contains(hash)) {
                ans = s.substring(0, i + 1);
            }
        }

        if (ans.equals(s)) {
            return "";
        }
        return ans;
    }
}
