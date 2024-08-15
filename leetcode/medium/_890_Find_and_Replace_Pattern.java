package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.
 *
 * A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.
 *
 * Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
 * Example 2:
 *
 * Input: words = ["a","b","c"], pattern = "a"
 * Output: ["a","b","c"]
 *
 *
 * Constraints:
 *
 * 1 <= pattern.length <= 20
 * 1 <= words.length <= 50
 * words[i].length == pattern.length
 * pattern and words[i] are lowercase English letters.
 *
 */

public class _890_Find_and_Replace_Pattern {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();

        for (String word : words) {
            if (match(word, pattern)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private boolean match(String word, String pattern) {
        Map<Character, Character> charToPattern = new HashMap<>();
        Map<Character, Character> patternToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char wch = word.charAt(i);
            char pch = pattern.charAt(i);

            if (!charToPattern.containsKey(wch)) {
                charToPattern.put(wch, pch);
            }

            if (!patternToChar.containsKey(pch)) {
                patternToChar.put(pch, wch);
            }

            if (patternToChar.get(pch) != wch || charToPattern.get(wch) != pch) {
                return false;
            }
        }
        return true;
    }

}
