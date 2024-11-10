package leetcode.Strings;


import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find two disjoint palindromic subsequences of s such that the product of their lengths is maximized. The two subsequences are disjoint if they do not both pick a character at the same index.
 *
 * Return the maximum possible product of the lengths of the two palindromic subsequences.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters. A string is palindromic if it reads the same forward and backward.
 *
 *
 *
 * Example 1:
 *                e   t       e
 *              l e e t c o d e c o m
 *                      c   d   c
 * example-1
 * Input: s = "leetcodecom"
 * Output: 9
 * Explanation: An optimal solution is to choose "ete" for the 1st subsequence and "cdc" for the 2nd subsequence.
 * The product of their lengths is: 3 * 3 = 9.
 * Example 2:
 *
 * Input: s = "bb"
 * Output: 1
 * Explanation: An optimal solution is to choose "b" (the first character) for the 1st subsequence and "b" (the second character) for the 2nd subsequence.
 * The product of their lengths is: 1 * 1 = 1.
 * Example 3:
 *
 * Input: s = "accbcaxxcxx"
 * Output: 25
 * Explanation: An optimal solution is to choose "accca" for the 1st subsequence and "xxcxx" for the 2nd subsequence.
 * The product of their lengths is: 5 * 5 = 25.
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 12
 * s consists of lowercase English letters only.
 *
 *
 */

public class _2002_Maximum_Product_of_the_Length_of_Two_Palindromic_Subsequences {

    public int maxProduct(String s) {
        Map<String, Integer> memo = new HashMap<>();
        return recur(s, new StringBuilder(), new StringBuilder(), 0, memo);
    }

    private int recur(String s, StringBuilder str1, StringBuilder str2,
                      int index, Map<String, Integer> memo) {

        if (index == s.length()) {
            int ans = 0;

            if (isPalindrome(str1.toString()) && isPalindrome(str2.toString())) {
                return str1.length() * str2.length();
            }
            return ans;
        }
        String key = str1.toString() + "|" + str2.toString() + "|" + index;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        str1.append(s.charAt(index));
        int firstAdd = recur(s, str1, str2, index + 1, memo);
        str1.deleteCharAt(str1.length() - 1);
        str2.append(s.charAt(index));
        int secondAdd = recur(s, str1, str2, index + 1, memo);
        str2.deleteCharAt(str2.length() - 1);
        int noAdd = recur(s, str1, str2, index + 1, memo);
        int ans = Math.max(firstAdd, Math.max(secondAdd, noAdd));
        memo.put(key, ans);
        return ans;
    }

    private boolean isPalindrome(String str) {

        for (int i = 0 ; i < str.length(); i++) {

            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
