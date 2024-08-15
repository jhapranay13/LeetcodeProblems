package leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Given a string s, find any
 * substring
 *  of length 2 which is also present in the reverse of s.
 *
 * Return true if such a substring exists, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode"
 *
 * Output: true
 *
 * Explanation: Substring "ee" is of length 2 which is also present in reverse(s) == "edocteel".
 *
 * Example 2:
 *
 * Input: s = "abcba"
 *
 * Output: true
 *
 * Explanation: All of the substrings of length 2 "ab", "bc", "cb", "ba" are also present in reverse(s) == "abcba".
 *
 * Example 3:
 *
 * Input: s = "abcd"
 *
 * Output: false
 *
 * Explanation: There is no substring of length 2 in s, which is also present in the reverse of s.
 *
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists only of lowercase English letters.
 *
 */

public class _3083_Existence_of_a_Substring_in_a_String_and_Its_Reverse {
    public boolean isSubstringPresent(String s) {
        int[] pow = new int[2];
        pow[0] = 1;
        pow[1] = 113;
        Set<Long> hashCodesSet = new HashSet<>();

        for (int i = 1; i < s.length(); i++) {

            if (s.charAt(i) == s.charAt(i - 1)) {
                return true;
            }
            long hashVal = rabinKarp(pow, i, s);
            hashCodesSet.add(hashVal);
        }
        StringBuilder revString = new StringBuilder();

        for (char ch : s.toCharArray()) {
            revString.insert(0, ch);
        }

        for (int i = 1; i < revString.length(); i++) {
            long hashVal = rabinKarp(pow, i, revString.toString());

            if (hashCodesSet.contains(hashVal)) {
                return true;
            }
        }
        return false;
    }

    private long rabinKarp(int[] pow, int endIndex, String s) {
        int startIndex = endIndex - 1;
        int anchor = startIndex;
        long hashCode = 0;

        while (startIndex <= endIndex) {
            char ch = s.charAt(startIndex);
            hashCode += (ch - 'a' + 1) * pow[startIndex - anchor];
            startIndex++;
        }
        return hashCode;
    }
}
