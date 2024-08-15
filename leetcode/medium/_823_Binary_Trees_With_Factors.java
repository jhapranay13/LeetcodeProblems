package leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
 *
 * We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf node's value should be equal to the product of the values of its children.
 *
 * Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,4]
 * Output: 3
 * Explanation: We can make these trees: [2], [4], [4, 2, 2]
 * Example 2:
 *
 * Input: arr = [2,4,5,10]
 * Output: 7
 * Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 1000
 * 2 <= arr[i] <= 10^9
 * All the values of arr are unique.
 *
 */

public class _823_Binary_Trees_With_Factors {
    public int numFactoredBinaryTrees(int[] arr) {
        Set<Integer> container = new HashSet<>();
        int ans = 0;

        for (int curr : arr) {
            container.add(curr);
        }
        Map<Integer, Long> memo = new HashMap<>();

        for (int curr : arr) {
            ans += recur(container, curr, memo);
            ans %= mod;
        }
        return ans;
    }

    private int mod = (int) 1e9 + 7;

    private long recur(Set<Integer> container, int curr, Map<Integer, Long> memo) {

        if (memo.containsKey(curr)) {
            return memo.get(curr);
        }
        long count = 1;

        for (int next : container) {

            if (curr % next != 0) {
                continue;
            }
            int firstNum = next;
            int secondNumber = curr / next;
            // since root is equal to the product of the value of it's children
            if (container.contains(secondNumber)) {
                count += recur(container, firstNum, memo) *
                        recur(container, secondNumber, memo);
                count %= mod;
            }
        }
        memo.put(curr, count);
        return count;
    }
}
