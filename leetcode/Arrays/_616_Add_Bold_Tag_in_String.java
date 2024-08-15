package leetcode.Arrays;

/**
 *
 *
 * You are given a string s and an array of strings words.
 *
 * You should add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in words.
 *
 * If two such substrings overlap, you should wrap them together with only one pair of closed bold-tag.
 * If two substrings wrapped by bold tags are consecutive, you should combine them.
 * Return s after adding the bold tags.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcxyz123", words = ["abc","123"]
 * Output: "<b>abc</b>xyz<b>123</b>"
 * Explanation: The two strings of words are substrings of s as following: "abcxyz123".
 * We add <b> before each substring and </b> after each substring.
 * Example 2:
 *
 * Input: s = "aaabbb", words = ["aa","b"]
 * Output: "<b>aaabbb</b>"
 * Explanation:
 * "aa" appears as a substring two times: "aaabbb" and "aaabbb".
 * "b" appears as a substring three times: "aaabbb", "aaabbb", and "aaabbb".
 * We add <b> before each substring and </b> after each substring: "<b>a<b>a</b>a</b><b>b</b><b>b</b><b>b</b>".
 * Since the first two <b>'s overlap, we merge them: "<b>aaa</b><b>b</b><b>b</b><b>b</b>".
 * Since now the four <b>'s are consecuutive, we merge them: "<b>aaabbb</b>".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * 0 <= words.length <= 100
 * 1 <= words[i].length <= 1000
 * s and words[i] consist of English letters and digits.
 * All the values of words are unique.
 *
 *
 * Note: This question is the same as 758: https://leetcode.com/problems/bold-words-in-string/
 *
 *
 */

public class _616_Add_Bold_Tag_in_String {
    public String addBoldTag(String s, String[] words) {
        int[] mark = new int[s.length() + 1];

        for (String word : words) {
            int index = s.indexOf(word);

            while (index >= 0 && index < s.length()) {
                // Marking start and end
                mark[index]++;
                // Adding end at index + 1 so that if there is an overlap it does not interfare
                mark[index + word.length()]--;

                int temp = s.indexOf(word, index + 1);

                if (temp == -1) {
                    break;
                }
                index = temp;
            }
            // Since index will be one more for the end
        }
        int sum = 0;
        StringBuilder holder = new StringBuilder();

        for (int i = 0; i < mark.length; i++) {
            int curr = sum + mark[i];

            if (curr > 0 && sum == 0) {
                holder.append("<b>");
            }

            if (curr == 0 && sum > 0) {
                holder.append("</b>");
            }

            if (i < s.length()) {
                holder.append(s.charAt(i));
            }
            sum = curr;
        }
        return holder.toString();
    }
}
