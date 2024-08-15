package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Given two strings first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.
 *
 * Return an array of all the words third for each occurrence of "first second third".
 *
 *
 *
 * Example 1:
 *
 * Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
 * Output: ["girl","student"]
 * Example 2:
 *
 * Input: text = "we will we will rock you", first = "we", second = "will"
 * Output: ["we","rock"]
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 1000
 * text consists of lowercase English letters and spaces.
 * All the words in text a separated by a single space.
 * 1 <= first.length, second.length <= 10
 * first and second consist of lowercase English letters.
 *
 *
 */

public class _1078_Occurrences_After_Bigram {
    public String[] findOcurrences(String text, String first, String second) {
        List<String> holder = new ArrayList<>();
        String[] split = text.split(" ");

        for (int i = 0; i < split.length; i++) {

            if (split[i].equals(first)) {

                if (i + 1 < split.length && split[i + 1].equals(second)) {

                    if (i + 2 < split.length) {
                        holder.add(split[i + 2]);
                    }
                }
            }
        }
        String[] ans = new String[holder.size()];

        for (int i = 0; i < holder.size(); i++) {
            ans[i] = holder.get(i);
        }
        return ans;
    }
}
