package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a pattern and a string s, find if s follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 *
 *
 *
 * Example 1:
 *
 * Input: pattern = "abba", s = "dog cat cat dog"
 * Output: true
 * Example 2:
 *
 * Input: pattern = "abba", s = "dog cat cat fish"
 * Output: false
 * Example 3:
 *
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= pattern.length <= 300
 * pattern contains only lower-case English letters.
 * 1 <= s.length <= 3000
 * s contains only lowercase English letters and spaces ' '.
 * s does not contain any leading or trailing spaces.
 * All the words in s are separated by a single space.
 *
 */

public class _290_Word_Pattern {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> chToWord = new HashMap<>();
        Map<String, Character> wordToCh = new HashMap<>();

        String[] split = s.split(" ");

        if (split.length != pattern.length()) {
            return false;
        }
        int index = 0;

        while (index < pattern.length()) {
            char ch = pattern.charAt(index);
            String word = split[index];

            if (chToWord.containsKey(ch) && !chToWord.get(ch).equals(split[index])) {
                return false;
            }

            if (wordToCh.containsKey(word) && !wordToCh.get(word).equals(ch))  {
                return false;
            }
            chToWord.put(ch, word);
            wordToCh.put(word, ch);
            index++;
        }
        return true;
    }
}
