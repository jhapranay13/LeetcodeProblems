package leetcode.easy;

/**
 *
 *
 * You are given a 0-indexed string s that has lowercase English letters in its even indices and digits in its odd indices.
 *
 * There is a function shift(c, x), where c is a character and x is a digit, that returns the xth character after c.
 *
 * For example, shift('a', 5) = 'f' and shift('x', 0) = 'x'.
 * For every odd index i, you want to replace the digit s[i] with shift(s[i-1], s[i]).
 *
 * Return s after replacing all digits. It is guaranteed that shift(s[i-1], s[i]) will never exceed 'z'.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "a1c1e1"
 * Output: "abcdef"
 * Explanation: The digits are replaced as follows:
 * - s[1] -> shift('a',1) = 'b'
 * - s[3] -> shift('c',1) = 'd'
 * - s[5] -> shift('e',1) = 'f'
 * Example 2:
 *
 * Input: s = "a1b2c3d4e"
 * Output: "abbdcfdhe"
 * Explanation: The digits are replaced as follows:
 * - s[1] -> shift('a',1) = 'b'
 * - s[3] -> shift('b',2) = 'd'
 * - s[5] -> shift('c',3) = 'f'
 * - s[7] -> shift('d',4) = 'h'
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists only of lowercase English letters and digits.
 * shift(s[i-1], s[i]) <= 'z' for all odd indices i.
 *
 *
 */

public class _1844_Replace_All_Digits_with_Characters {
    public String replaceDigits(String s) {
        StringBuilder holder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            if (i % 2 == 0) {
                holder.append(s.charAt(i));
            } else {
                char tempChar = s.charAt(i - 1);
                int val = s.charAt(i) - '0';
                char newChar = (char)(tempChar + val);
                holder.append(newChar);
            }
        }
        return holder.toString();
    }
}
