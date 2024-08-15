package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * An original string, consisting of lowercase English letters, can be encoded by the following steps:
 *
 * Arbitrarily split it into a sequence of some number of non-empty substrings.
 * Arbitrarily choose some elements (possibly none) of the sequence, and replace each with its length (as a numeric string).
 * Concatenate the sequence as the encoded string.
 * For example, one way to encode an original string "abcdefghijklmnop" might be:
 *
 * Split it as a sequence: ["ab", "cdefghijklmn", "o", "p"].
 * Choose the second and third elements to be replaced by their lengths, respectively. The sequence becomes ["ab", "12", "1", "p"].
 * Concatenate the elements of the sequence to get the encoded string: "ab121p".
 * Given two encoded strings s1 and s2, consisting of lowercase English letters and digits 1-9 (inclusive), return true if there exists an original string that could be encoded as both s1 and s2. Otherwise, return false.
 *
 * Note: The test cases are generated such that the number of consecutive digits in s1 and s2 does not exceed 3.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "internationalization", s2 = "i18n"
 * Output: true
 * Explanation: It is possible that "internationalization" was the original string.
 * - "internationalization"
 *   -> Split:       ["internationalization"]
 *   -> Do not replace any element
 *   -> Concatenate:  "internationalization", which is s1.
 * - "internationalization"
 *   -> Split:       ["i", "nternationalizatio", "n"]
 *   -> Replace:     ["i", "18",                 "n"]
 *   -> Concatenate:  "i18n", which is s2
 * Example 2:
 *
 * Input: s1 = "l123e", s2 = "44"
 * Output: true
 * Explanation: It is possible that "leetcode" was the original string.
 * - "leetcode"
 *   -> Split:      ["l", "e", "et", "cod", "e"]
 *   -> Replace:    ["l", "1", "2",  "3",   "e"]
 *   -> Concatenate: "l123e", which is s1.
 * - "leetcode"
 *   -> Split:      ["leet", "code"]
 *   -> Replace:    ["4",    "4"]
 *   -> Concatenate: "44", which is s2.
 * Example 3:
 *
 * Input: s1 = "a5b", s2 = "c5b"
 * Output: false
 * Explanation: It is impossible.
 * - The original string encoded as s1 must start with the letter 'a'.
 * - The original string encoded as s2 must start with the letter 'c'.
 *
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length <= 40
 * s1 and s2 consist of digits 1-9 (inclusive), and lowercase English letters only.
 * The number of consecutive digits in s1 and s2 does not exceed 3.
 *
 */

public class _2060_Check_if_an_Original_String_Exists_Given_Two_Encoded_Strings {
    public boolean possiblyEquals(String s1, String s2) {
        Map<String, Boolean> memo = new HashMap<>();
        return recur(s1, s2, 0, 0, 0, memo);
    }

    private boolean recur(String s1, String s2, int index1, int index2, int diff, Map<String, Boolean> memo) {
        String key = index1 + "|" + index2 + "|" + diff;

        if (index1 == s1.length() && index2 == s2.length()) {

            if (diff == 0) {
                memo.put(key, true);
                return true;
            }
            memo.put(key, false);
            return false;
        }

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (index1 < s1.length() && index2 < s2.length() && diff == 0) {

            if ( s1.charAt(index1) == s2.charAt(index2) && recur(s1, s2, index1 + 1, index2 + 1, diff, memo)) {
                memo.put(key, true);
                return true;
            }
        }

        if (index1 < s1.length() && !Character.isDigit(s1.charAt(index1)) && diff > 0) {

            if (recur(s1, s2, index1 + 1, index2, diff - 1, memo)) {
                memo.put(key, true);
                return true;
            }
        }

        if (index2 < s2.length() && !Character.isDigit(s2.charAt(index2)) && diff < 0) {

            if (recur(s1, s2, index1, index2 + 1, diff + 1, memo)) {
                memo.put(key, true);
                return true;
            }
        }

        if (index1 < s1.length() && Character.isDigit(s1.charAt(index1))) {
            int num = 0;

            for (int i = index1;
                 i < s1.length() && Character.isDigit(s1.charAt(i)) && i < index1 + 3; i++) {
                num *= 10;
                num += s1.charAt(i) - '0';

                if (recur(s1, s2, i + 1, index2, diff - num, memo)) {
                    memo.put(key, true);
                    return true;
                }
            }
        }

        if (index2 < s2.length() && Character.isDigit(s2.charAt(index2))) {
            int num = 0;

            for (int i = index2;
                 i < s2.length() && Character.isDigit(s2.charAt(i)) && i < index2 + 3; i++) {
                num *= 10;
                num += s2.charAt(i) - '0';

                if (recur(s1, s2, index1, i + 1, diff + num, memo)) {
                    memo.put(key, true);
                    return true;
                }
            }
        }
        memo.put(key, false);
        return false;
    }
}
