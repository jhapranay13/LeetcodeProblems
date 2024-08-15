package leetcode.Strings;

/**
 *
 *
 * Given two strings a and b, return the length of the longest uncommon subsequence between a and b. If the longest uncommon subsequence does not exist, return -1.
 *
 * An uncommon subsequence between two strings is a string that is a subsequence of one but not the other.
 *
 * A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
 *
 * For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
 *
 *
 * Example 1:
 *
 * Input: a = "aba", b = "cdc"
 * Output: 3
 * Explanation: One longest uncommon subsequence is "aba" because "aba" is a subsequence of "aba" but not "cdc".
 * Note that "cdc" is also a longest uncommon subsequence.
 * Example 2:
 *
 * Input: a = "aaa", b = "bbb"
 * Output: 3
 * Explanation: The longest uncommon subsequences are "aaa" and "bbb".
 * Example 3:
 *
 * Input: a = "aaa", b = "aaa"
 * Output: -1
 * Explanation: Every subsequence of string a is also a subsequence of string b. Similarly, every subsequence of string b is also a subsequence of string a.
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 100
 * a and b consist of lower-case English letters.
 *
 *
 */

public class _521_Longest_Uncommon_Subsequence_I {
    /**
     a=b. If both the strings are identical, it is obvious that no subsequence will be uncommon. Hence, return -1.

     length(a)=length(b) and a != b . Example: abcabcabc and abdabdabd. In this case we can consider any string i.e. abcabcabc or abdabdabd as a required subsequence, as out of these two strings one string will never be a subsequence of other string. Hence, return length(a)length(a)length(a) or length(b)length(b)length(b).

     length(a) != length(b). Example abcdabcdabcd and abcabcabc. In this case we can consider bigger string as a required subsequence because bigger string can't be a subsequence of smaller string. Hence, return max(length(a),length(b))max(length(a),length(b))max(length(a),length(b)).


     */
    public int findLUSlength(String a, String b) {

        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }
}
