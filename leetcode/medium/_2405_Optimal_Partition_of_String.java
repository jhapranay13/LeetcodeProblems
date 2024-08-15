package leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.
 *
 * Return the minimum number of substrings in such a partition.
 *
 * Note that each character should belong to exactly one substring in a partition.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abacaba"
 * Output: 4
 * Explanation:
 * Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
 * It can be shown that 4 is the minimum number of substrings needed.
 * Example 2:
 *
 * Input: s = "ssssss"
 * Output: 6
 * Explanation:
 * The only valid partition is ("s","s","s","s","s","s").
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of only English lowercase letters.
 *
 */

public class _2405_Optimal_Partition_of_String {
    public int partitionString(String s) {
        int ans = 1;
        int index = 0;
        Set<Character> holder = new HashSet<>();

        while (index < s.length()) {
            char ch = s.charAt(index++);
            //If we encounter the duplicate start from there as we want to maximize the length to minimize number of partition
            if (!holder.add(ch)) {
                holder.clear();
                holder.add(ch);
                ans++;
            }
        }
        return ans;
    }
}
