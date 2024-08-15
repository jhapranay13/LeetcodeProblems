package leetcode.medium;

/**
 *
 * You are given two strings order and s. All the words of order are unique and were sorted in some custom order previously.
 *
 * Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.
 *
 * Return any permutation of s that satisfies this property.
 *
 *
 *
 * Example 1:
 *
 * Input: order = "cba", s = "abcd"
 * Output: "cbad"
 * Explanation:
 * "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
 * Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.
 * Example 2:
 *
 * Input: order = "cbafg", s = "abcd"
 * Output: "cbad"
 *
 *
 * Constraints:
 *
 * 1 <= order.length <= 26
 * 1 <= s.length <= 200
 * order and s consist of lowercase English letters.
 * All the characters of order are unique.
 *
 */

public class _791_CustomSortString {

    public String customSortString(String order, String s) {
        StringBuilder ans = new StringBuilder();
        int[] count = new int[26];

        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        for (char ch : order.toCharArray()) {

            while (count[ch - 'a']-- > 0) {
                ans.append(ch);
            }
        }

        for (char ch : s.toCharArray()) {

            while (count[ch - 'a']-- > 0) {
                ans.append(ch);
            }
        }
        return ans.toString();
    }
}
