package leetcode.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a string s, return true if s is a good string, or false otherwise.
 *
 * A string s is good if all the characters that appear in s have the same number of occurrences (i.e., the same frequency).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abacbc"
 * Output: true
 * Explanation: The characters that appear in s are 'a', 'b', and 'c'. All characters occur 2 times in s.
 * Example 2:
 *
 * Input: s = "aaabb"
 * Output: false
 * Explanation: The characters that appear in s are 'a' and 'b'.
 * 'a' occurs 3 times while 'b' occurs 2 times, which is not the same number of times.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 *
 */

public class _1941_Check_if_All_Characters_Have_Equal_Number_of_Occurrences {
    public boolean areOccurrencesEqual(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();

        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        int freq = -1;

        for (char ch : freqMap.keySet()) {

            if (freq == -1) {
                freq = freqMap.get(ch);
            } else {

                if (freqMap.get(ch) != freq) {
                    return false;
                }
            }
        }
        return true;
    }
}
