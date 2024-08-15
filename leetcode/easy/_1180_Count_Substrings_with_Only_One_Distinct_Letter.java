package leetcode.easy;

/**
 *
 * Given a string s, return the number of substrings that have only one distinct letter.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaaba"
 * Output: 8
 * Explanation: The substrings with one distinct letter are "aaa", "aa", "a", "b".
 * "aaa" occurs 1 time.
 * "aa" occurs 2 times.
 * "a" occurs 4 times.
 * "b" occurs 1 time.
 * So the answer is 1 + 2 + 4 + 1 = 8.
 * Example 2:
 *
 * Input: s = "aaaaaaaaaa"
 * Output: 55
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s[i] consists of only lowercase English letters.
 *
 */

public class _1180_Count_Substrings_with_Only_One_Distinct_Letter {
    public int countLetters(String s) {
        int ans = 0;
        int fast = 0;
        int slow = 0;

        while (fast <= s.length()) {

            if (fast == s.length() || s.charAt(fast) != s.charAt(slow)) {
                int length = fast - slow;
                ans += (length + 1) * length / 2; // Arithmetic Sequence sum
                slow = fast;
            }
            fast++;
        }
        return ans;
    }
}
