package leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * An n-bit gray code sequence is a sequence of 2n integers where:
 *
 * Every integer is in the inclusive range [0, 2n - 1],
 * The first integer is 0,
 * An integer appears no more than once in the sequence,
 * The binary representation of every pair of adjacent integers differs by exactly one bit, and
 * The binary representation of the first and last integers differs by exactly one bit.
 * Given an integer n, return any valid n-bit gray code sequence.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,3,2]
 * Explanation:
 * The binary representation of [0,1,3,2] is [00,01,11,10].
 * - 00 and 01 differ by one bit
 * - 01 and 11 differ by one bit
 * - 11 and 10 differ by one bit
 * - 10 and 00 differ by one bit
 * [0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
 * - 00 and 10 differ by one bit
 * - 10 and 11 differ by one bit
 * - 11 and 01 differ by one bit
 * - 01 and 00 differ by one bit
 * Example 2:
 *
 * Input: n = 1
 * Output: [0,1]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 16
 *
 */

public class _89_Gray_Code {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        Set<Integer> v = new HashSet<>();
        v.add(0);
        recur(ans, v, n);
        return ans;
    }

    private boolean recur(List<Integer> ans, Set<Integer> v, int n) {
        // 2^ n should be size of the answer
        if (ans.size() == 1 << n) {
            return true;
        }
        int curr = ans.get(ans.size() - 1);

        for (int i = 0; i < n; i++) {
            int next = curr ^ (1 << i);//flipping the one bit

            if (!v.contains(next)) {
                v.add(next);
                ans.add(next);

                if (recur(ans, v, n)) {
                    return true;
                }
                v.remove(next);
                ans.remove(ans.size() - 1);
            }
        }
        return false;
    }
}
