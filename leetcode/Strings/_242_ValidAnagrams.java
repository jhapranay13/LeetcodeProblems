package leetcode.Strings;

import java.util.Arrays;

/**
 *
 *
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 5 * 104
 * s and t consist of lowercase English letters.
 *
 *
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 *
 */

public class _242_ValidAnagrams {
    //Can also be done using hashMap
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }
        char[] sarr = s.toCharArray();
        char[] tarr = t.toCharArray();
        Arrays.sort(sarr);
        Arrays.sort(tarr);

        for (int i = 0; i < s.length(); i++) {

            if (sarr[i] != tarr[i]) {
                return false;
            }
        }
        return true;
    }
}
