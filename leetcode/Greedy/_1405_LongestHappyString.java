package leetcode.Greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * A string s is called happy if it satisfies the following conditions:
 *
 * s only contains the letters 'a', 'b', and 'c'.
 * s does not contain any of "aaa", "bbb", or "ccc" as a substring.
 * s contains at most a occurrences of the letter 'a'.
 * s contains at most b occurrences of the letter 'b'.
 * s contains at most c occurrences of the letter 'c'.
 * Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 * Explanation: "ccbccacc" would also be a correct answer.
 * Example 2:
 *
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 * Explanation: It is the only correct answer in this case.
 *
 *
 * Constraints:
 *
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 *
 */

public class _1405_LongestHappyString {
    public String longestDiverseString(int a, int b, int c) {
        Map<Character, Integer> freqMap = new HashMap<>();
        PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {
            public int compare(Character x, Character y) {

                if (freqMap.get(x) == freqMap.get(y)) {
                    return x.compareTo(y);
                }
                return freqMap.get(y) - freqMap.get(x);
            }
        });

        if (a > 0) {
            freqMap.put('a', a);
            pq.offer('a');
        }

        if (b > 0) {
            freqMap.put('b', b);
            pq.offer('b');
        }

        if (c > 0) {
            freqMap.put('c', c);
            pq.offer('c');
        }
        StringBuilder ans = new StringBuilder();
        char lastChar = '\u0000';
        int lastCharCount = 0;

        while (!pq.isEmpty()) {
            char ch = pq.poll();

            if (ch == lastChar) {

                if (lastCharCount < 2) {
                    int freq = freqMap.get(ch);

                    if (freq == 1) {
                        freqMap.remove(ch);
                    } else {
                        freqMap.put(ch, --freq);
                        pq.offer(ch);
                    }
                    lastCharCount++;
                    ans.append(ch);
                } else if (!pq.isEmpty()) {
                    char ch2 = pq.poll();
                    pq.offer(ch);
                    ch = ch2;
                    int freq = freqMap.get(ch);

                    if (freq == 1) {
                        freqMap.remove(ch);
                    } else {
                        freqMap.put(ch, --freq);
                        pq.offer(ch);
                    }
                    lastCharCount = 1;
                    lastChar = ch;
                    ans.append(ch);
                } else {
                    break;
                }
            } else {
                int freq = freqMap.get(ch);

                if (freq == 1) {
                    freqMap.remove(ch);
                } else {
                    freqMap.put(ch, --freq);
                    pq.offer(ch);
                }
                lastCharCount = 1;
                lastChar = ch;
                ans.append(ch);
            }
        }
        return ans.toString();
    }
}
