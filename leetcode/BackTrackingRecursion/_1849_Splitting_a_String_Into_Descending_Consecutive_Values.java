package leetcode.BackTrackingRecursion;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * You are given a string s that consists of only digits.
 *
 * Check if we can split s into two or more non-empty substrings such that the numerical values of the substrings are in descending order and the difference between numerical values of every two adjacent substrings is equal to 1.
 *
 * For example, the string s = "0090089" can be split into ["0090", "089"] with numerical values [90,89]. The values are in descending order and adjacent values differ by 1, so this way is valid.
 * Another example, the string s = "001" can be split into ["0", "01"], ["00", "1"], or ["0", "0", "1"]. However all the ways are invalid because they have numerical values [0,1], [0,1], and [0,0,1] respectively, all of which are not in descending order.
 * Return true if it is possible to split s​​​​​​ as described above, or false otherwise.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1234"
 * Output: false
 * Explanation: There is no valid way to split s.
 * Example 2:
 *
 * Input: s = "050043"
 * Output: true
 * Explanation: s can be split into ["05", "004", "3"] with numerical values [5,4,3].
 * The values are in descending order with adjacent values differing by 1.
 * Example 3:
 *
 * Input: s = "9080701"
 * Output: false
 * Explanation: There is no valid way to split s.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * s only consists of digits.
 *
 *
 */

public class _1849_Splitting_a_String_Into_Descending_Consecutive_Values {
    public boolean splitString(String s) {
        Map<String, Boolean> memo = new HashMap<>();
        return recur(s, 0, -1, 0, memo);
    }

    private boolean recur(String s, int split, long prevNum, int index, Map<String, Boolean> memo) {

        if (index == s.length()) {
            return split >= 2;
        }
        String key = split + "|" + prevNum + "|" + index;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        boolean ans = false;

        long num = 0;

        for (int i = index; i < s.length(); i++) {
            num *= 10;
            int currNum = s.charAt(i) - '0';
            num += currNum;

            if ((prevNum == -1 || prevNum - num == 1)
                    && recur(s, split + 1, num, i + 1, memo)) {
                ans = true;
                break;
            }
        }
        memo.put(key, ans);
        return ans;
    }
}
