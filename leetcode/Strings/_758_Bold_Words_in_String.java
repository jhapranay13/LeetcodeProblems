package leetcode.Strings;

/**
 *
 *
 * Given an array of keywords words and a string s, make all appearances of all keywords words[i] in s bold. Any letters between <b> and </b> tags become bold.
 *
 * Return s after adding the bold tags. The returned string should use the least number of tags possible, and the tags should form a valid combination.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["ab","bc"], s = "aabcd"
 * Output: "a<b>abc</b>d"
 * Explanation: Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.
 * Example 2:
 *
 * Input: words = ["ab","cb"], s = "aabcd"
 * Output: "a<b>ab</b>cd"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * 0 <= words.length <= 50
 * 1 <= words[i].length <= 10
 * s and words[i] consist of lowercase English letters.
 *
 *
 * Note: This question is the same as 616: https://leetcode.com/problems/add-bold-tag-in-string/
 *
 *
 */

public class _758_Bold_Words_in_String {
    public String boldWords(String[] words, String s) {
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
