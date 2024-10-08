package leetcode.Sorting;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * You are given an array target of n integers. From a starting array arr consisting of n 1's, you may perform the following procedure :
 *
 * let x be the sum of all elements currently in your array.
 * choose index i, such that 0 <= i < n and set the value of arr at index i to x.
 * You may repeat this procedure as many times as needed.
 * Return true if it is possible to construct the target array from arr, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: target = [9,3,5]
 * Output: true
 * Explanation: Start with arr = [1, 1, 1]
 * [1, 1, 1], sum = 3 choose index 1
 * [1, 3, 1], sum = 5 choose index 2
 * [1, 3, 5], sum = 9 choose index 0
 * [9, 3, 5] Done
 * Example 2:
 *
 * Input: target = [1,1,1,2]
 * Output: false
 * Explanation: Impossible to create target array from [1,1,1,1].
 * Example 3:
 *
 * Input: target = [8,5]
 * Output: true
 *
 *
 * Constraints:
 *
 * n == target.length
 * 1 <= n <= 5 * 10^4
 * 1 <= target[i] <= 10^9
 *
 */

public class _1354_Construct_Target_Array_With_Multiple_Sums {
    // Working backwords to break down largest element to 1.
    public boolean isPossible(int[] target) {

        if (target.length == 1 && target[0] != 1)
            return false;
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : target) {
            sum += num;
            pq.offer(num);
        }

        while (pq.peek() != 1){
            int val = pq.poll();
            int remainder = sum - val;
            int prevVal = val % remainder;

            if (remainder == 1) {
                return true;
            }

            if (prevVal <= 0 || prevVal >= val) {
                return false;
            }
            pq.offer(prevVal);
            sum = sum - val + prevVal;
        }
        return true;
        // TLE. too many repetition
       /*while (pq.peek() != 1){
            int val = pq.poll();
            int remainder = sum - val;
            int prevVal = val - remainder;

            if (remainder == 1) {
                return true;
            }

            if (prevVal <= 0 || prevVal >= val) {
                return false;
            }
            pq.offer(prevVal);
            sum = sum - val + prevVal;
        }
        return true;*/
    }
}
