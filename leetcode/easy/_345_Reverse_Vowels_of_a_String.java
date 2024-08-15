package leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * Given a string s, reverse only all the vowels in the string and return it.
 *
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "hello"
 * Output: "holle"
 * Example 2:
 *
 * Input: s = "leetcode"
 * Output: "leotcede"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 10^5
 * s consist of printable ASCII characters.
 *
 */

public class _345_Reverse_Vowels_of_a_String {
    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        int hi = s.length() - 1;
        int lo = 0;
        StringBuilder holder = new StringBuilder(s);

        while (lo < hi) {

            while (lo < hi) {

                if (vowels.contains(s.charAt(lo))) {
                    break;
                }
                lo++;
            }

            while (lo < hi) {

                if (vowels.contains(s.charAt(hi))) {
                    break;
                }
                hi--;
            }

            if (lo < hi) {
                char ch = holder.charAt(lo);
                holder.setCharAt(lo, holder.charAt(hi));
                holder.setCharAt(hi, ch);
                lo++;
                hi--;
            }
        }
        return holder.toString();
    }
}
