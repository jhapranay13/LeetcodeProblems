package leetcode.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * You are given a string s and an array of strings words. All the strings of words are of the same length.
 *
 * A concatenated substring in s is a substring that contains all the strings of any permutation of words concatenated.
 *
 * For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated substring because it is not the concatenation of any permutation of words.
 * Return the starting indices of all the concatenated substrings in s. You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Since words.length == 2 and words[i].length == 3, the concatenated substring has to be of length 6.
 * The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
 * The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
 * The output order does not matter. Returning [9,0] is fine too.
 * Example 2:
 *
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * Output: []
 * Explanation: Since words.length == 4 and words[i].length == 4, the concatenated substring has to be of length 16.
 * There is no substring of length 16 is s that is equal to the concatenation of any permutation of words.
 * We return an empty array.
 * Example 3:
 *
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * Output: [6,9,12]
 * Explanation: Since words.length == 3 and words[i].length == 3, the concatenated substring has to be of length 9.
 * The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"] which is a permutation of words.
 * The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"] which is a permutation of words.
 * The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"] which is a permutation of words.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * s and words[i] consist of lowercase English letters.
 *
 */

public class _30_Substring_with_Concatenation_of_All_Words {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> freqMap = new HashMap<>();
        int build = 0;

        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            build++;
        }
        int length = words[0].length();
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            slidingWindow(freqMap, length, ans, s, i, build);
        }
        return ans;
    }

    private void slidingWindow(Map<String, Integer> freqMap, int length,
                               List<Integer> ans, String s, int index, int build) {
        int fast = index;
        int slow = index;
        Map<String, Integer> currFreqMap = new HashMap<>();
        int currBuild = 0;

        while (fast < s.length() && fast + length <= s.length()) {
            String tempFast = s.substring(fast, fast + length);

            if (freqMap.containsKey(tempFast)) {
                int freq = currFreqMap.getOrDefault(tempFast, 0);
                freq++;
                currFreqMap.put(tempFast, freq);
                currBuild++;

                if (freq > freqMap.get(tempFast)) {

                    while (currFreqMap.get(tempFast) > freqMap.get(tempFast)) {
                        String tempSlow = s.substring(slow, slow + length);
                        currFreqMap.put(tempSlow, currFreqMap.get(tempSlow) - 1);
                        slow = slow + length;
                        currBuild--;
                    }
                }
            } else {
                currBuild = 0;
                currFreqMap.clear();
                slow = fast + length;
            }

            if (build == currBuild) {
                ans.add(slow);
            }
            fast = fast + length;
        }
    }
}
