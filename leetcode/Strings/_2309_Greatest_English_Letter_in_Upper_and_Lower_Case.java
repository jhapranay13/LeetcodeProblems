package leetcode.Strings;

import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * Given a string of English letters s, return the greatest English letter which occurs as both a lowercase and uppercase letter in s. The returned letter should be in uppercase. If no such letter exists, return an empty string.
 *
 * An English letter b is greater than another letter a if b appears after a in the English alphabet.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "lEeTcOdE"
 * Output: "E"
 * Explanation:
 * The letter 'E' is the only letter to appear in both lower and upper case.
 * Example 2:
 *
 * Input: s = "arRAzFif"
 * Output: "R"
 * Explanation:
 * The letter 'R' is the greatest letter to appear in both lower and upper case.
 * Note that 'A' and 'F' also appear in both lower and upper case, but 'R' is greater than 'F' or 'A'.
 * Example 3:
 *
 * Input: s = "AbCdEfGhIjK"
 * Output: ""
 * Explanation:
 * There is no letter that appears in both lower and upper case.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of lowercase and uppercase English letters.
 *
 *
 */

public class _2309_Greatest_English_Letter_in_Upper_and_Lower_Case {
    public String greatestLetter(String s) {
        Set<Character> holder = new HashSet<>();
        char ansHolder = '\u0000';

        for (char ch : s.toCharArray()) {
            char upper = Character.toUpperCase(ch);
            char lower = Character.toLowerCase(ch);
            holder.add(ch);

            if (holder.contains(upper) && holder.contains(lower)) {

                if (ansHolder == '\u0000' ||
                        upper > ansHolder) {
                    ansHolder = upper;
                }
            }
        }
        return ansHolder == '\u0000' ?  "" : ansHolder + "";
    }
}
