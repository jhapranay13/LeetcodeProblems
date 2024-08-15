package leetcode.StackAndQueues;

import java.util.*;

/**
 *
 * Given a string s, return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 *
 *
 * Similar To Problem 316
 */

public class _1081_SmallestSubSequenceOfDistinctCharacters {

    public String smallestSubsequence(String s) {
        Map<Character, Integer> lastPosMap = new HashMap<>();
        int index = 0;

        for (char ch : s.toCharArray()) {
            lastPosMap.put(ch, index++);
        }
        Deque<Character> stack = new LinkedList<>();
        Set<Character> v = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {

            if (!v.add(s.charAt(i))) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > s.charAt(i) &&
                    lastPosMap.get(stack.peek()) > i) {
                v.remove(stack.pop());
            }
            stack.push(s.charAt(i));
        }
        StringBuilder ans = new StringBuilder();

        while (!stack.isEmpty()) {
            ans.insert(0, stack.pop());
        }
        return ans.toString();
    }
}
