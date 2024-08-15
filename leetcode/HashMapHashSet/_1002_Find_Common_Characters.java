package leetcode.HashMapHashSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["bella","label","roller"]
 * Output: ["e","l","l"]
 * Example 2:
 *
 * Input: words = ["cool","lock","cook"]
 * Output: ["c","o"]
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of lowercase English letters.
 *
 */

public class _1002_Find_Common_Characters {
    public List<String> commonChars(String[] words) {
        Map<Character, Integer> freq = new HashMap<>();

        for (char ch : words[0].toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        for (int i = 1; i < words.length; i++) {
            Map<Character, Integer> currFreq = new HashMap<>();

            for (char ch : words[i].toCharArray()) {

                if (freq.containsKey(ch)) {
                    currFreq.put(ch, currFreq.getOrDefault(ch, 0) + 1);

                    if (freq.get(ch) == 1) {
                        freq.remove(ch);
                    } else {
                        freq.put(ch, freq.get(ch) - 1);
                    }
                }
            }
            freq = currFreq;
        }
        List<String> ans = new ArrayList<>();

        for (char ch : freq.keySet()) {
            int count = freq.get(ch);

            while (count-- > 0) {
                ans.add("" + ch);
            }
        }
        return ans;
    }
}
