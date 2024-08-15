package leetcode.easy;

/**
 *
 *
 * You are given an alphanumeric string s. (Alphanumeric string is a string consisting of lowercase English letters and digits).
 *
 * You have to find a permutation of the string where no letter is followed by another letter and no digit is followed by another digit. That is, no two adjacent characters have the same type.
 *
 * Return the reformatted string or return an empty string if it is impossible to reformat the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "a0b1c2"
 * Output: "0a1b2c"
 * Explanation: No two adjacent characters have the same type in "0a1b2c". "a0b1c2", "0a1b2c", "0c2a1b" are also valid permutations.
 * Example 2:
 *
 * Input: s = "leetcode"
 * Output: ""
 * Explanation: "leetcode" has only characters so we cannot separate them by digits.
 * Example 3:
 *
 * Input: s = "1229857369"
 * Output: ""
 * Explanation: "1229857369" has only digits so we cannot separate them by characters.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of only lowercase English letters and/or digits.
 *
 */

public class _1417_Reformat_The_String {
    public String reformat(String s) {
        StringBuilder alphabet = new StringBuilder();
        StringBuilder number = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (Character.isDigit(ch)) {
                number.append(ch);
            } else {
                alphabet.append(ch);
            }
        }

        if (Math.abs(alphabet.length() - number.length()) > 1) {
            return "";
        }
        boolean isFirstGreater = alphabet.length() > number.length() ? true : false;
        StringBuilder ans = new StringBuilder();
        int aIndex = 0;
        int nIndex = 0;

        for (int i = 0; i < s.length(); i++) {

            if (isFirstGreater) {
                ans.append(alphabet.charAt(aIndex++));
            } else {
                ans.append(number.charAt(nIndex++));
            }
            isFirstGreater = !isFirstGreater;
        }
        return ans.toString();
    }
}
