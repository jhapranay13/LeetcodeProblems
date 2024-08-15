package leetcode.Strings;

/**
 *
 * Given a binary string s and a positive integer n, return true if the binary representation of all the integers in the range [1, n] are substrings of s, or false otherwise.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "0110", n = 3
 * Output: true
 * Example 2:
 *
 * Input: s = "0110", n = 4
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s[i] is either '0' or '1'.
 * 1 <= n <= 10^9
 *
 */

public class _1016_Binary_String_With_Substrings_Representing_1_To_N {
    public boolean queryString(String s, int n) {

        for (int i = 1; i <= n; i++) {
            String binaryString = Integer.toBinaryString(i);

            if (!s.contains(binaryString)) {
                return false;
            }
        }
        return true;
    }
}
