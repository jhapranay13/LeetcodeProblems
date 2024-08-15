package leetcode.Strings;

/**
 *
 * Given two binary strings a and b, return their sum as a binary string.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 10^4
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 *
 */

public class _67_Add_Binary {
    public String addBinary(String a, String b) {
        char carry = '0';
        int ia = a.length() - 1;
        int ib = b.length() - 1;
        StringBuilder ans = new StringBuilder();

        while (ia >= 0 || ib >= 0) {
            char cha = ia >= 0 ? a.charAt(ia--) : '0';
            char chb = ib >= 0 ? b.charAt(ib--) : '0';

            if (cha == '1' && chb == '1') {

                if (carry == '1') {
                    ans.insert(0, '1');

                } else {
                    carry = '1';
                    ans.insert(0, '0');
                }
            } else if (cha == '1' && chb == '0' || (cha == '0' && chb == '1')) {

                if (carry == '1') {
                    ans.insert(0, '0');

                } else {
                    ans.insert(0, '1');
                }
            } else {

                if (carry == '1') {
                    ans.insert(0, '1');
                    carry = '0';
                } else {
                    ans.insert(0, '0');
                }
            }
        }
        if (carry == '1') {
            ans.insert(0, '1');
        }
        return ans.toString();
    }
}
