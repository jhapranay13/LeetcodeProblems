package leetcode.easy;

/**
 *
 *
 *
 *You are given a binary string s that contains at least one '1'.
 *
 * You have to rearrange the bits in such a way that the resulting binary number is the maximum odd binary number that can be created from this combination.
 *
 * Return a string representing the maximum odd binary number that can be created from the given combination.
 *
 * Note that the resulting string can have leading zeros.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "010"
 * Output: "001"
 * Explanation: Because there is just one '1', it must be in the last position. So the answer is "001".
 * Example 2:
 *
 * Input: s = "0101"
 * Output: "1001"
 * Explanation: One of the '1's must be in the last position. The maximum number that can be made with the remaining digits is "100". So the answer is "1001".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists only of '0' and '1'.
 * s contains at least one '1'.
 *
 */

public class _2864_Maximum_Odd_Binary_Number {
    public String maximumOddBinaryNumber(String s) {
        int count = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '1') {
                count++;
            }
        }
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            if (count > 1 || (count == 1 && i == s.length() - 1)) {
                ans.append('1');
                count--;
            } else {
                ans.append('0');
            }
        }

        return ans.toString();
    }
}
