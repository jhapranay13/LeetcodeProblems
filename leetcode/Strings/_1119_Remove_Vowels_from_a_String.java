package leetcode.Strings;

import java.util.Set;

/**
 *
 *
 * Given a string s, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcodeisacommunityforcoders"
 * Output: "ltcdscmmntyfrcdrs"
 * Example 2:
 *
 * Input: s = "aeiou"
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of only lowercase English letters.
 *
 */

public class _1119_Remove_Vowels_from_a_String {
    public String removeVowels(String s) {
        Set<Character> set = Set.of('a', 'e', 'i', 'o', 'u');
        StringBuilder holder = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (!set.contains(ch)) {
                holder.append(ch);
            }
        }
        return holder.toString();
    }
}
