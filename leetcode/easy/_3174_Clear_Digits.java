package leetcode.easy;

/**
 *
 * You are given a string s.
 *
 * Your task is to remove all digits by doing this operation repeatedly:
 *
 * Delete the first digit and the closest non-digit character to its left.
 * Return the resulting string after removing all digits.
 *
 * Note that the operation cannot be performed on a digit that does not have any non-digit character to its left.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc"
 *
 * Output: "abc"
 *
 * Explanation:
 *
 * There is no digit in the string.
 *
 * Example 2:
 *
 * Input: s = "cb34"
 *
 * Output: ""
 *
 * Explanation:
 *
 * First, we apply the operation on s[2], and s becomes "c4".
 *
 * Then we apply the operation on s[1], and s becomes "".
 *
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists only of lowercase English letters and digits.
 * The input is generated such that it is possible to delete all digits.
 *
 */

public class _3174_Clear_Digits {
    // can be done using stack as well
    public String clearDigits(String s) {
        char[] holder = s.toCharArray();
        int index = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                index--;
            } else {
                holder[index++] = ch;
            }
        }
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < index; i++) {
            str.append(holder[i]);
        }
        return str.toString();
    }
}
