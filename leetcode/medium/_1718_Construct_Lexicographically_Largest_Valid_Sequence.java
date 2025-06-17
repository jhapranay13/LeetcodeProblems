package leetcode.medium;


import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer n, find a sequence with elements in the range [1, n] that satisfies all of the following:
 *
 * The integer 1 occurs once in the sequence.
 * Each integer between 2 and n occurs twice in the sequence.
 * For every integer i between 2 and n, the distance between the two occurrences of i is exactly i.
 * The distance between two numbers on the sequence, a[i] and a[j], is the absolute difference of their indices, |j - i|.
 *
 * Return the lexicographically largest sequence. It is guaranteed that under the given constraints, there is always a solution.
 *
 * A sequence a is lexicographically larger than a sequence b (of the same length) if in the first position where a and b differ, sequence a has a number greater than the corresponding number in b. For example, [0,1,9,0] is lexicographically larger than [0,1,5,6] because the first position they differ is at the third number, and 9 is greater than 5.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: [3,1,2,3,2]
 * Explanation: [2,3,2,1,3] is also a valid sequence, but [3,1,2,3,2] is the lexicographically largest valid sequence.
 * Example 2:
 *
 * Input: n = 5
 * Output: [5,3,1,4,3,5,2,4,2]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 */
public class _1718_Construct_Lexicographically_Largest_Valid_Sequence {
    public int[] constructDistancedSequence(int n) {
        int[] ans = new int[n * 2 - 1];
        recur(n, new HashSet<>(), ans, 0);
        return ans;
    }

    private boolean recur(int n, Set<Integer> v, int[] ans, int index) {

        if (v.size() == n || index == ans.length) {
            return true;
        }

        for (int i = n; i >= 1; i--) {

            if (v.contains(i)) {
                continue;
            }
            int nextIndx = -1;

            if (i != 1) {
                nextIndx = index + i;

                if (nextIndx < ans.length) {

                    if (ans[nextIndx] == 0) {
                        ans[nextIndx] = i;
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            v.add(i);
            ans[index] = i;

            if (v.size() == n) {
                return true;
            }
            int anchor = index + 1;

            while (anchor < ans.length && ans[anchor] != 0) {
                anchor++;
            }

            if (recur(n, v, ans, anchor)) {
                return true;
            }
            ans[index] = 0;

            if (nextIndx != -1) {
                ans[nextIndx] = 0;
            }
            v.remove(i);
        }
        return false;
    }
}
