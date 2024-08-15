package leetcode.Sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 *Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * You may assume the input array always has a valid answer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,1,1,6,4]
 * Output: [1,6,1,5,1,4]
 * Explanation: [1,4,1,5,1,6] is also accepted.
 * Example 2:
 *
 * Input: nums = [1,3,2,2,3,1]
 * Output: [2,3,1,3,1,2]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 5000
 * It is guaranteed that there will be an answer for the given input nums.
 *
 *
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 *
 */

public class _324_Wiggle_Sort_II {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] holder = new int[nums.length];
        int j = nums.length - 1;
        //populating odd indexes
        for (int i = 1; i < nums.length && j > 0; j--, i += 2) {
            holder[i] = nums[j];
        }

        //populating even indexes
        for (int i = 0; i < nums.length && j < nums.length; i += 2, j--) {
            holder[i] = nums[j];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = holder[i];
        }
    }
    //=============================================================================================
    // PriorityQueue
    public void wiggleSort1(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
        }
        int n = nums.length;

        int i = 1;
        int j = n / 2;
        while(j-- > 0) {
            nums[i] = pq.poll();
            i += 2;
        }
        j = n % 2 != 0 ? (n / 2 + 1) : n / 2;
        i = 0;

        while (j--> 0) {
            nums[i] = pq.poll();
            i += 2;
        }
    }
}
