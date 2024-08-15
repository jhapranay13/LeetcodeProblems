package leetcode.Math;

/**
 *
 * For two strings s and t, we say "t divides s" if and only if s = t + ... + t (i.e., t is concatenated with itself one or more times).
 *
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 *
 *
 *
 * Example 1:
 *
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 * Example 2:
 *
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 * Example 3:
 *
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of English uppercase letters.
 *
 */

public class _1071_Greatest_Common_Divisor_of_Strings {
    public String gcdOfStrings(String str1, String str2) {

        for (int i = Math.min(str1.length(), str2.length()); i > 0; i--) {

            if (check(str1, str2, i)) {
                return str1.substring(0, i);
            }
        }
        return "";
    }

    private boolean check(String str1, String str2, int length) {
        int len1 = str1.length();
        int len2 = str2.length();

        if (len1 % length > 0 || len2 % length > 0) {
            return false;
        }
        String base = str1.substring(0, length);
        return str1.replace(base, "").equals("") && str2.replace(base, "").equals("");
    }
}
