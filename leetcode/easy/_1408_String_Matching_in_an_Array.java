package leetcode.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Given an array of string words, return all strings in words that is a substring of another word. You can return the answer in any order.
 *
 * A substring is a contiguous sequence of characters within a string
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["mass","as","hero","superhero"]
 * Output: ["as","hero"]
 * Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
 * ["hero","as"] is also a valid answer.
 * Example 2:
 *
 * Input: words = ["leetcode","et","code"]
 * Output: ["et","code"]
 * Explanation: "et", "code" are substring of "leetcode".
 * Example 3:
 *
 * Input: words = ["blue","green","bu"]
 * Output: []
 * Explanation: No string of words is substring of another string.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 30
 * words[i] contains only lowercase English letters.
 * All the strings of words are unique.
 *
 */

public class _1408_String_Matching_in_an_Array {
    public List<String> stringMatching(String[] words) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            for (int j = 0; j < words.length; j++) {
                if (i != j && words[j].contains(word)) {
                    set.add(word);
                }
            }
        }
        return new ArrayList<>(set);
    }
}
