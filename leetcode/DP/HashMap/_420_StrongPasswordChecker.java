package leetcode.DP.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * A password is considered strong if the below conditions are all met:
 *
 * It has at least 6 characters and at most 20 characters.
 * It contains at least one lowercase letter, at least one uppercase letter, and at least one digit.
 * It does not contain three repeating characters in a row (i.e., "...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
 * Given a string password, return the minimum number of steps required to make password strong. if password is already strong, return 0.
 *
 * In one step, you can:
 *
 * Insert one character to password,
 * Delete one character from password, or
 * Replace one character of password with another character.
 *
 *
 * Example 1:
 *
 * Input: password = "a"
 * Output: 5
 * Example 2:
 *
 * Input: password = "aA1"
 * Output: 3
 * Example 3:
 *
 * Input: password = "1337C0d3"
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= password.length <= 50
 * password consists of letters, digits, dot '.' or exclamation mark '!'.
 *
 *
 */

public class _420_StrongPasswordChecker {

    public int strongPasswordChecker(String password) {
        Map<String, Integer> memo = new HashMap<>();
        return recur(password, false, false, false, 0, 0, 0, -1, memo);
    }

    private int recur(String password, boolean hasLower, boolean hasUpper, boolean hasDigit, int length,
                      int repeatLength, int index, int prevIndex, Map<String, Integer> memo) {

        if (length > 20) {
            return Integer.MAX_VALUE / 10;
        }
        //password.length() - index because if index is not at end all the other chars have to be deleted
        if (password.length() == index || length == 20) {

            if (hasLower && hasUpper && hasDigit && length >= 6) {
                return 0 + password.length() - index;
            }
            return Integer.MAX_VALUE / 10;
        }
        String key = hasLower + "," + hasUpper + "," + hasDigit + "," + length + "," + repeatLength + "," +
                index + "," + prevIndex;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        char ch = password.charAt(index);
        int rptLength = repeatLength;

        if (prevIndex > -1 && ch == password.charAt(prevIndex)) {
            rptLength = repeatLength + 1;
        } else {
            rptLength = 1;
        }
        int ans = Integer.MAX_VALUE / 10;

        //Include this if character is  repeated less than 3 times
        if (rptLength < 3) {
            ans = Math.min(ans, recur(password, hasLower || Character.isLowerCase(ch),
                    hasUpper || Character.isUpperCase(ch),
                    hasDigit || Character.isDigit(ch), length + 1,
                    rptLength, index + 1, index, memo));
        }
        //Insert a lower case
        ans = Math.min(ans, 1 +
                recur(password, true, hasUpper, hasDigit, length + 1, 0, index, -1, memo));
        //Insert on upper case
        ans = Math.min(ans, 1 +
                recur(password, hasLower, true, hasDigit, length + 1, 0, index, -1, memo));
        //insert a digit
        ans = Math.min(ans, 1 +
                recur(password, hasLower, hasUpper, true, length + 1, 0, index, -1, memo));

        //update a lower case
        ans = Math.min(ans, 1 +
                recur(password, true, hasUpper, hasDigit, length + 1, 0, index + 1, -1, memo));
        //update on upper case
        ans = Math.min(ans, 1 +
                recur(password, hasLower, true, hasDigit, length + 1, 0, index + 1, -1, memo));
        //update a digit
        ans = Math.min(ans, 1 +
                recur(password, hasLower, hasUpper, true, length + 1, 0, index+ 1, -1, memo));
        //Delete
        ans = Math.min(ans, 1 +
                recur(password, hasLower, hasUpper, hasDigit, length, repeatLength,
                        index+ 1, prevIndex, memo));
        memo.put(key, ans);
        return ans;
    }
    //=============================================================================================
    // Slight optimization
    public int strongPasswordChecker1(String password) {
        Map<String, Integer> memo = new HashMap<>();
        return recur1(password, false, false, false, 0, 0, 0, -1, memo);
    }

    private int recur1(String password, boolean hasLower, boolean hasUpper, boolean hasDigit, int length,
                       int repeatLength, int index, int prevIndex, Map<String, Integer> memo) {

        if (length > 20) {
            return Integer.MAX_VALUE / 10;
        }
        //password.length() - index because if index is not at end all the other chars have to be deleted
        if (password.length() == index || length == 20) {

            if (hasLower && hasUpper && hasDigit && length >= 6) {
                return 0 + password.length() - index;
            }
            return Integer.MAX_VALUE / 10;
        }
        String key = hasLower + "," + hasUpper + "," + hasDigit + "," + length + "," + repeatLength + "," +
                index + "," + prevIndex;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        char ch = password.charAt(index);
        int rptLength = repeatLength;

        if (prevIndex > -1 && ch == password.charAt(prevIndex)) {
            rptLength = repeatLength + 1;
        } else {
            rptLength = 1;
        }
        int ans = Integer.MAX_VALUE / 10;

        //Include this if character is  repeated less than 3 times
        if (rptLength < 3) {
            ans = Math.min(ans, recur1(password, hasLower || Character.isLowerCase(ch),
                    hasUpper || Character.isUpperCase(ch),
                    hasDigit || Character.isDigit(ch), length + 1,
                    rptLength, index + 1, index, memo));
        }
        //Insert a lower case
        //update a lower case
        if (!Character.isLowerCase(password.charAt(index))) {
            ans = Math.min(ans, 1 +
                    recur1(password, true, hasUpper, hasDigit, length + 1, 0, index, -1, memo));
            ans = Math.min(ans, 1 +
                    recur1(password, true, hasUpper, hasDigit, length + 1, 0, index + 1, -1, memo));
        }
        //Insert on upper case
        //update on upper case
        if (!Character.isUpperCase(password.charAt(index))) {
            ans = Math.min(ans, 1 +
                    recur1(password, hasLower, true, hasDigit, length + 1, 0, index, -1, memo));
            ans = Math.min(ans, 1 +
                    recur1(password, hasLower, true, hasDigit, length + 1, 0, index + 1, -1, memo));
        }
        //insert a digit
        //update a digit
        if (!Character.isDigit(password.charAt(index))) {
            ans = Math.min(ans, 1 +
                    recur1(password, hasLower, hasUpper, true, length + 1, 0, index, -1, memo));
            ans = Math.min(ans, 1 +
                    recur1(password, hasLower, hasUpper, true, length + 1, 0, index+ 1, -1, memo));
        }
        //Delete
        ans = Math.min(ans, 1 +
                recur1(password, hasLower, hasUpper, hasDigit, length, repeatLength,
                        index+ 1, prevIndex, memo));
        memo.put(key, ans);
        return ans;
    }
}
