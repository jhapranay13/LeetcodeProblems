package leetcode.hard;

/**
 *
 * A valid number can be split up into these components (in order):
 *
 * A decimal number or an integer.
 * (Optional) An 'e' or 'E', followed by an integer.
 * A decimal number can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One of the following formats:
 * One or more digits, followed by a dot '.'.
 * One or more digits, followed by a dot '.', followed by one or more digits.
 * A dot '.', followed by one or more digits.
 * An integer can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One or more digits.
 * For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].
 *
 * Given a string s, return true if s is a valid number.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "0"
 * Output: true
 * Example 2:
 *
 * Input: s = "e"
 * Output: false
 * Example 3:
 *
 * Input: s = "."
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.
 *
 */

public class _65_ValidNumber {
    public boolean isNumber(String s) {
        int digitPosition = -1;
        int signPosition = -1;
        int decimalPosition = -1;
        int ePosition = -1;
        //The whole idea is to break the number into two parts with e as pivot if it exists.
        //just that we need to take care that after e there are no decimals.

        for ( int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                digitPosition = i;
            } else if (ch == '+' || ch == '-') {

                if (i > 0 && (s.charAt(i - 1) != 'E' && s.charAt(i - 1) != 'e')) {
                    return false;
                }
                signPosition = i;
            } else if (ch == 'e' || ch == 'E') {

                if (digitPosition == -1 || ePosition != -1) {
                    return false;
                }
                ePosition = i;
                digitPosition = -1;
            } else if (ch == '.') {

                if (decimalPosition != -1 || ePosition != -1) {
                    return false;
                }
                decimalPosition = i;
            } else {
                return false;
            }
        }
        return digitPosition != -1;
    }
}
