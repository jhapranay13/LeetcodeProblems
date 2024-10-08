package leetcode.Strings;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 * Example 2:
 *
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 * Example 3:
 *
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 2 <= k <= 10^4
 * s only contains lowercase English letters.
 *
 */

public class _1209_Remove_All_Adjacent_Duplicates_in_String_II {
    public String removeDuplicates(String s, int k) {
        Deque<Integer> charStack = new LinkedList<>();
        Deque<Integer> countStack = new LinkedList();

        for (int i = 0; i < s.length(); i++) {
            int count = 0;

            if (!charStack.isEmpty() && s.charAt(charStack.peek()) == s.charAt(i)) {
                count = countStack.peek();
            }
            count++;

            if (count == k) {
                while (!charStack.isEmpty() && s.charAt(charStack.peek()) == s.charAt(i)) {
                    charStack.pop();
                    countStack.pop();
                }
            } else {
                charStack.push(i);
                countStack.push(count);
            }
        }
        StringBuilder ans = new StringBuilder();

        while (!charStack.isEmpty()) {
            ans.insert(0, s.charAt(charStack.poll()));
        }
        return ans.toString();
    }
}
