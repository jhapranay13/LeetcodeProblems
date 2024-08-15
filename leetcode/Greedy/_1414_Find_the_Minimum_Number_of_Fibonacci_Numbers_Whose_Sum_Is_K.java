package leetcode.Greedy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an integer k, return the minimum number of Fibonacci numbers whose sum is equal to k. The same Fibonacci number can be used multiple times.
 *
 * The Fibonacci numbers are defined as:
 *
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 for n > 2.
 * It is guaranteed that for the given constraints we can always find such Fibonacci numbers that sum up to k.
 *
 *
 * Example 1:
 *
 * Input: k = 7
 * Output: 2
 * Explanation: The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ...
 * For k = 7 we can use 2 + 5 = 7.
 * Example 2:
 *
 * Input: k = 10
 * Output: 2
 * Explanation: For k = 10 we can use 2 + 8 = 10.
 * Example 3:
 *
 * Input: k = 19
 * Output: 3
 * Explanation: For k = 19 we can use 1 + 5 + 13 = 19.
 *
 *
 * Constraints:
 *
 * 1 <= k <= 10^9
 *
 */

public class _1414_Find_the_Minimum_Number_of_Fibonacci_Numbers_Whose_Sum_Is_K {
    public int findMinFibonacciNumbers(int k) {
        List<Integer> fibNumber = new ArrayList<>();

        int prev = 0;
        int curr = 1;

        while (curr <= k) {
            fibNumber.add(curr);
            int temp = prev + curr;
            prev = curr;
            curr = temp;
        }
        int ans = 0;

        while (k > 0) {
            int index = binarySearchJustLessThanOrEqualTo(fibNumber, k);
            int val = k - fibNumber.get(index);

            if (val >= 0) {
                k -= fibNumber.get(index);
                ans++;
            }
        }
        return ans;
    }

    private int binarySearchJustLessThanOrEqualTo(List<Integer> fibNumber, int target) {
        int ans = 0;
        int lo = 0;
        int hi = fibNumber.size() - 1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (fibNumber.get(pivot) <= target) {
                ans = pivot;
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }
}
