package leetcode.Sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * You may assume the input array always has a valid answer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,5,2,1,6,4]
 * Output: [3,5,1,6,2,4]
 * Explanation: [1,6,2,5,3,4] is also accepted.
 * Example 2:
 *
 * Input: nums = [6,6,5,6,3,8]
 * Output: [6,6,5,6,3,8]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 10^4
 * It is guaranteed that there will be an answer for the given input nums.
 *
 *
 * Follow up: Could you solve the problem in O(n) time complexity?
 *
 */

public class _280_Wiggle_Sort {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length - 1; i++) {
            int temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
            i++;
        }
    }
    //=============================================================================================
    //PriorityQueue Approach
    public void wiggleSort1(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < nums.length; i++) {
            minHeap.offer(nums[i]);
            maxHeap.offer(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i++] = minHeap.poll();

            if (i < nums.length) {
                nums[i] = maxHeap.poll();
            }
        }
    }
}
