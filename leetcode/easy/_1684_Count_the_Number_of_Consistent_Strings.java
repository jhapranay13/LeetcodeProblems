package leetcode.easy;

/**
 *
 * You are given a string allowed consisting of distinct characters and an array of strings words. A string is consistent if all characters in the string appear in the string allowed.
 *
 * Return the number of consistent strings in the array words.
 *
 *
 *
 * Example 1:
 *
 * Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
 * Output: 2
 * Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.
 * Example 2:
 *
 * Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
 * Output: 7
 * Explanation: All strings are consistent.
 * Example 3:
 *
 * Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
 * Output: 4
 * Explanation: Strings "cc", "acd", "ac", and "d" are consistent.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 104
 * 1 <= allowed.length <= 26
 * 1 <= words[i].length <= 10
 * The characters in allowed are distinct.
 * words[i] and allowed contain only lowercase English letters.
 *
 */

public class _1684_Count_the_Number_of_Consistent_Strings {
    public int countConsistentStrings(String allowed, String[] words) {
        int bitAllowed = 0;

        for (char ch : allowed.toCharArray()) {
            int index = ch - 'a';
            int mask = 1 << index;
            bitAllowed |= mask;
        }
        int ans = 0;

        for (String word : words) {
            boolean isPresent = true;

            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                int mask = 1 << index;
                int res = mask & bitAllowed;

                if (res == 0) {
                    isPresent = false;
                    break;
                }
            }

            if (isPresent) {
                ans++;
            }
        }
        return ans;
    }
}
