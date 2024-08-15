package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length <= 10^4
 * s1 and s2 consist of lowercase English letters.
 *
 */

public class _567_Permutation_in_String {
    public boolean checkInclusion(String s1, String s2) {

        if (s1.length() > s2.length()) {
            return false;
        }
        int made = 0;
        Map<Character, Integer> charFreq = new HashMap<>();

        for (char ch : s1.toCharArray()) {
            charFreq.put(ch, charFreq.getOrDefault(ch, 0) + 1);
            made++;
        }
        int slow = 0;
        int fast = 0;
        int madeHolder = made;
        Map<Character, Integer> currFreq = new HashMap<>();

        while (fast < s2.length()) {
            char fch = s2.charAt(fast++);

            if (charFreq.containsKey(fch)) {
                currFreq.put(fch, currFreq.getOrDefault(fch, 0) + 1);
                made--;

                while (slow <= fast && currFreq.get(fch) > charFreq.get(fch)) {
                    char sch = s2.charAt(slow++);

                    if (charFreq.containsKey(sch) && currFreq.containsKey(sch)) {

                        if (currFreq.get(sch) == 1) {
                            currFreq.remove(sch);
                        } else {
                            currFreq.put(sch, currFreq.get(sch) - 1);
                        }
                        made++;
                    }
                }

            } else {
                currFreq.clear();
                made = madeHolder;
                slow = fast;
            }

            if (made == 0) {
                return true;
            }
        }
        return made == 0;
    }
}
