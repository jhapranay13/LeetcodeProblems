package leetcode.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given a 0-indexed string s consisting of only lowercase English letters. Return the number of substrings in s that begin and end with the same character.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcba"
 * Output: 7
 * Explanation:
 * The substrings of length 1 that start and end with the same letter are: "a", "b", "c", "b", and "a".
 * The substring of length 3 that starts and ends with the same letter is: "bcb".
 * The substring of length 5 that starts and ends with the same letter is: "abcba".
 * Example 2:
 *
 * Input: s = "abacad"
 * Output: 9
 * Explanation:
 * The substrings of length 1 that start and end with the same letter are: "a", "b", "a", "c", "a", and "d".
 * The substrings of length 3 that start and end with the same letter are: "aba" and "aca".
 * The substring of length 5 that starts and ends with the same letter is: "abaca".
 * Example 3:
 *
 * Input: s = "a"
 * Output: 1
 * Explanation:
 * The substring of length 1 that starts and ends with the same letter is: "a".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists only of lowercase English letters.
 *
 */

public class _2083_Substrings_That_Begin_and_End_With_the_Same_Letter {
    public long numberOfSubstrings(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();

        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        long ans = s.length();

        for (char key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            ans += (long) freq * (freq - 1) / 2; // Artihmetic progression i. t 1 + 2 + 3 +... + n
        }
        return ans;
    }
}
