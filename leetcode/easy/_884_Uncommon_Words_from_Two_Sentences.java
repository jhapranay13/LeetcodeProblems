package leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * A sentence is a string of single-space separated words where each word consists only of lowercase letters.
 *
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 *
 * Given two sentences s1 and s2, return a list of all the uncommon words. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "this apple is sweet", s2 = "this apple is sour"
 * Output: ["sweet","sour"]
 * Example 2:
 *
 * Input: s1 = "apple apple", s2 = "banana"
 * Output: ["banana"]
 *
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length <= 200
 * s1 and s2 consist of lowercase English letters and spaces.
 * s1 and s2 do not have leading or trailing spaces.
 * All the words in s1 and s2 are separated by a single space.
 *
 */

public class _884_Uncommon_Words_from_Two_Sentences {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> freqMap = new HashMap<>();
        String[] split1 = s1.split(" ");
        String[] split2 = s2.split(" ");
        List<String> ans = new ArrayList<>();

        for(String split : split1) {
            freqMap.put(split, freqMap.getOrDefault(split, 0) + 1);
        }

        for (String split : split2) {
            freqMap.put(split, freqMap.getOrDefault(split, 0) + 1);
        }

        for (String split : freqMap.keySet()) {

            if (freqMap.get(split) == 1) {
                ans.add(split);
            }
        }
        return ans.toArray(new String[ans.size()]);
    }
}
