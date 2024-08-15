package leetcode.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * A string s is called good if there are no two different characters in s that have the same frequency.
 *
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 *
 * The frequency of a character in a string is the number of times it appears in the string.
 * For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: 0
 * Explanation: s is already good.
 * Example 2:
 *
 * Input: s = "aaabbbcc"
 * Output: 2
 * Explanation: You can delete two 'b's resulting in the good string "aaabcc".
 * Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
 * Example 3:
 *
 * Input: s = "ceabaacb"
 * Output: 2
 * Explanation: You can delete both 'c's resulting in the good string "eabaab".
 * Note that we only care about characters that are still in the string at the
 * end (i.e. frequency of 0 is ignored).
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s contains only lowercase English letters.
 *
 */

public class _1647_MinimumDeletionsToMakeCharacterFrequenciesUnique {
    public int minDeletions(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {
            public int compare(Character x, Character y) {
                if (freqMap.get(x) == freqMap.get(y)) {
                    return x.compareTo(y);
                }
                return freqMap.get(y) - freqMap.get(x);
            }
        });

        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        for (char ch : freqMap.keySet()) {
            pq.offer(ch);
        }
        char prev = '\u0000';
        int prevFreq = 0;
        int ans = 0;

        while (!pq.isEmpty()) {

            if (prev == '\u0000') {
                prev = pq.poll();
                prevFreq = freqMap.get(prev);
            } else {
                char ch = pq.poll();
                int currFreq = freqMap.get(ch);

                while (prevFreq <= currFreq && currFreq > 0) {
                    currFreq--;
                    ans++;
                }
                prevFreq = currFreq;
                prev = ch;
            }
        }
        return ans;
    }
}
