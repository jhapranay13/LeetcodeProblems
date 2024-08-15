package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 *
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * Example 2:
 *
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * Example 3:
 *
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 20
 * s consists of digits only.
 *
 */

public class _93_RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        LinkedList<String> p = new LinkedList<>();
        recur(s, ans, p, 0);
        return ans;
    }

    private void recur(String s, List<String> ans, LinkedList<String> p, int index) {

        if (index == s.length()) {

            if (p.size() == 4) {
                StringBuilder str = new StringBuilder();

                for(int i = 0; i < p.size(); i++) {

                    if (str.length() > 0) {
                        str.append(".");
                    }
                    str.append(p.get(i));
                }
                ans.add(str.toString());
            }
            return;
        }
        StringBuilder str = new StringBuilder();

        for (int i = index; i < s.length() && i < index + 3; i++) {

            if (i != index && s.charAt(index) == '0') {
                break;
            }
            str.append(s.charAt(i));

            if (Integer.parseInt(str.toString()) > 255) {
                break;
            }
            p.add(str.toString());
            recur(s, ans, p, i + 1);
            p.remove(p.size() - 1);
        }
    }
}
