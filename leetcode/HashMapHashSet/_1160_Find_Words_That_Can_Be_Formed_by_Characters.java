package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given an array of strings words and a string chars.
 *
 * A string is good if it can be formed by characters from chars (each character can only be used once).
 *
 * Return the sum of lengths of all good strings in words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["cat","bt","hat","tree"], chars = "atach"
 * Output: 6
 * Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
 * Example 2:
 *
 * Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * Output: 10
 * Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * words[i] and chars consist of lowercase English letters.
 *
 */

public class _1160_Find_Words_That_Can_Be_Formed_by_Characters {
    public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> freqMap = new HashMap<>();
        int ans = 0;

        for (char ch : chars.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        for (String word : words) {
            Map<Character, Integer> freq = new HashMap<>();
            boolean contains = true;

            for (char ch : word.toCharArray()) {
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);

                if (!freqMap.containsKey(ch) || freqMap.get(ch) < freq.get(ch)) {
                    contains = false;
                    break;
                }
            }

            if (contains) {
                ans += word.length();
            }
        }
        return ans;
    }
}
