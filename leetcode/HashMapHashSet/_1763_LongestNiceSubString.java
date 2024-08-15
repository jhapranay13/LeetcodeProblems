package leetcode.HashMapHashSet;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.
 *
 * Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "YazaAay"
 * Output: "aAa"
 * Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
 * "aAa" is the longest nice substring.
 * Example 2:
 *
 * Input: s = "Bb"
 * Output: "Bb"
 * Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.
 * Example 3:
 *
 * Input: s = "c"
 * Output: ""
 * Explanation: There are no nice substrings.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists of uppercase and lowercase English letters.
 */

public class _1763_LongestNiceSubString {

    public String longestNiceSubstring(String s) {
        int[] pos = recur(s, 0, s.length() -1);
        return s.substring(pos[0], pos[1] + 1);
    }

    private int[] recur(String s, int lo, int hi) {
        int[] temp = new int[] {lo, hi};
        Set<Character> charSet = new HashSet<>();

        for (int i = lo; i <= hi; i++) {
            charSet.add(s.charAt(i));
        }

        for (int i = lo; i <= hi; i++) {
            char ch = s.charAt(i);
            char compliment = Character.isLowerCase(ch) ? Character.toUpperCase(ch) : Character.toLowerCase(ch);

            if (charSet.contains(ch) && !charSet.contains(compliment)) {
                int[] left = recur(s, lo, i - 1);
                int[] right = recur(s, i + 1, hi);

                if (right[1] - right[0] > left[1] - left[0]) {
                    return right;
                } else {
                    return left;
                }
            }
        }

        return temp;
    }
}
