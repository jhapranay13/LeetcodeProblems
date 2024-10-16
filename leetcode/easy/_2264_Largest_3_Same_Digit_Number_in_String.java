package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given a string num representing a large integer. An integer is good if it meets the following conditions:
 *
 * It is a substring of num with length 3.
 * It consists of only one unique digit.
 * Return the maximum good integer as a string or an empty string "" if no such integer exists.
 *
 * Note:
 *
 * A substring is a contiguous sequence of characters within a string.
 * There may be leading zeroes in num or a good integer.
 *
 *
 * Example 1:
 *
 * Input: num = "6777133339"
 * Output: "777"
 * Explanation: There are two distinct good integers: "777" and "333".
 * "777" is the largest, so we return "777".
 * Example 2:
 *
 * Input: num = "2300019"
 * Output: "000"
 * Explanation: "000" is the only good integer.
 * Example 3:
 *
 * Input: num = "42352338"
 * Output: ""
 * Explanation: No substring of length 3 consists of only one unique digit. Therefore, there are no good integers.
 *
 *
 * Constraints:
 *
 * 3 <= num.length <= 1000
 * num only consists of digits.
 */

public class _2264_Largest_3_Same_Digit_Number_in_String {
    public String largestGoodInteger(String num) {
        Map<Character, Integer> freqMap = new HashMap<>();
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

            if (i >= 2) {

                if (freqMap.size() == 1) {
                    int number = ch - '0';
                    ans = Math.max(ans, number);
                }
                char tempCh = num.charAt(i - 2);

                if (freqMap.get(tempCh) == 1) {
                    freqMap.remove(tempCh);
                } else {
                    freqMap.put(tempCh, freqMap.getOrDefault(tempCh, 0) - 1);
                }
            }
        }
        String returnStr = "";

        if (ans != Integer.MIN_VALUE) {
            returnStr = ans + "" + ans + "" + ans;
        }
        return returnStr;
    }
}
