package leetcode.DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an array of digits digits, return the largest multiple of three that can be formed by concatenating some of the given digits in any order. If there is no answer return an empty string.
 *
 * Since the answer may not fit in an integer data type, return the answer as a string. Note that the returning answer must not contain unnecessary leading zeros.
 *
 *
 *
 * Example 1:
 *
 * Input: digits = [8,1,9]
 * Output: "981"
 * Example 2:
 *
 * Input: digits = [8,6,7,1,0]
 * Output: "8760"
 * Example 3:
 *
 * Input: digits = [1]
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= digits.length <= 10^4
 * 0 <= digits[i] <= 9
 *
 */

public class _1363_Largest_Multiple_of_Three {
    //Map Memo
    // Normal school division. Sort it and then keep dividing by choosing or not choosing
    public String largestMultipleOfThree(int[] digits) {
        Arrays.sort(digits);
        // Handling test case [0, 0, 0,  0, 0....] and so
        int sum = 0;

        for (int i = 0; i < digits.length / 2; i++) {
            int j = digits.length - i - 1;
            int temp = digits[j];
            digits[j] = digits[i];
            digits[i] = temp;
            sum += digits[i] + digits[j];
        }

        if (digits.length % 2 != 0) {
            sum += digits[digits.length / 2];
        }

        if (sum == 0) {
            return "0";
        }
        Map<String, String> memo = new HashMap<>();
        String returnVal = recur(digits, 0, 0, memo);

        if (returnVal == "") {
            return returnVal;
        }

        if (returnVal.charAt(0) == '0') {
            return "0";
        }
        return returnVal;
    }

    private String recur(int[] digits, int remainder, int index, Map<String, String> memo) {

        if (index == digits.length) {

            if (remainder != 0) {
                return "-";
            }
            return "";
        }
        String key = remainder + "||" + index;

        if (memo.get(key) != null) {
            return memo.get(key);
        }
        int num = digits[index];
        int rem = remainder * 10;
        rem += num;
        rem %= 3;
        String include = recur(digits, rem, index + 1, memo);
        String exclude = recur(digits, remainder, index + 1, memo);
        String ans = null;

        if (include == "-" && exclude != "-") {
            ans = exclude;
        }

        if (include != "-" && exclude == "-") {
            ans = num + include;
        }

        if (include == "-" && exclude == "-") {
            ans = "-";
        }

        if (ans == null) {
            // Since will be adding the number in include so length of include will be + 1
            if (include.length() + 1 < exclude.length()) {
                ans = exclude;
            } else {
                ans = num + include;
            }
        }
        memo.put(key, ans);
        return ans;
    }

    //=============================================================================================
    //Array Memo MLE
    public String largestMultipleOfThree1(int[] digits) {
        Arrays.sort(digits);
        // Handling test case [0, 0, 0,  0, 0....] and so
        int sum = 0;

        for (int i = 0; i < digits.length / 2; i++) {
            int j = digits.length - i - 1;
            int temp = digits[j];
            digits[j] = digits[i];
            digits[i] = temp;
            sum += digits[i] + digits[j];
        }

        if (digits.length % 2 != 0) {
            sum += digits[digits.length / 2];
        }

        if (sum == 0) {
            return "0";
        }
        String[][] memo = new String[10][digits.length];
        String returnVal = recur(digits, 0, 0, memo);

        if (returnVal == "") {
            return returnVal;
        }

        if (returnVal.charAt(0) == '0') {
            return "0";
        }
        return returnVal;
    }

    private String recur(int[] digits, int remainder, int index, String[][] memo) {

        if (index == digits.length) {

            if (remainder != 0) {
                return "-";
            }
            return "";
        }

        if (memo[remainder][index] != null) {
            return memo[remainder][index];
        }
        int num = digits[index];
        int rem = remainder * 10;
        rem += num;
        rem %= 3;
        String include = recur(digits, rem, index + 1, memo);
        String exclude = recur(digits, remainder, index + 1, memo);
        String ans = null;

        if (include == "-" && exclude != "-") {
            ans = exclude;
        }

        if (include != "-" && exclude == "-") {
            ans = num + include;
        }

        if (include == "-" && exclude == "-") {
            ans = "-";
        }

        if (ans == null) {
            // Since will be adding the number in include so length of include will be + 1
            if (include.length() + 1 < exclude.length()) {
                ans = exclude;
            } else {
                ans = num + include;
            }
        }
        return memo[remainder][index] = ans;
    }
}
