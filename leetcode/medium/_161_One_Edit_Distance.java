package leetcode.medium;

/**
 *
 * Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
 *
 * A string s is said to be one distance apart from a string t if you can:
 *
 * Insert exactly one character into s to get t.
 * Delete exactly one character from s to get t.
 * Replace exactly one character of s with a different character to get t.
 *
 *
 * Example 1:
 *
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 * Example 2:
 *
 * Input: s = "", t = ""
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 *
 *
 * Constraints:
 *
 * 0 <= s.length, t.length <= 10^4
 * s and t consist of lowercase letters, uppercase letters, and digits.
 *
 */

public class _161_One_Edit_Distance {
    public boolean isOneEditDistance(String s, String t) {

        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }

        if (t.length() - s.length() > 1) {
            return false;
        }
        int sindex = 0;
        int tindex = 0;

        while (sindex < s.length()) {

            if (s.charAt(sindex) != t.charAt(tindex)) {
                // we can only delete or ignore both chars
                if (s.length() == t.length()) {
                    return s.substring(sindex + 1).equals(t.substring(tindex + 1));
                } else {
                    //s is smaller as we did at the start
                    return s.substring(sindex).equals(t.substring(tindex + 1));
                }
            }
            sindex++;
            tindex++;
        }
        //if both sre equal to smaller length then smaller. + 1 should be equal to greater
        return sindex + 1 == t.length();
    }
}
