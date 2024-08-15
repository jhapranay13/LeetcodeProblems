package leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * You are given a 0-indexed integer array nums consisting of 3 * n elements.
 *
 * You are allowed to remove any subsequence of elements of size exactly n from nums. The remaining 2 * n elements will be divided into two equal parts:
 *
 * The first n elements belonging to the first part and their sum is sumfirst.
 * The next n elements belonging to the second part and their sum is sumsecond.
 * The difference in sums of the two parts is denoted as sumfirst - sumsecond.
 *
 * For example, if sumfirst = 3 and sumsecond = 2, their difference is 1.
 * Similarly, if sumfirst = 2 and sumsecond = 3, their difference is -1.
 * Return the minimum difference possible between the sums of the two parts after the removal of n elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,2]
 * Output: -1
 * Explanation: Here, nums has 3 elements, so n = 1.
 * Thus we have to remove 1 element from nums and divide the array into two equal parts.
 * - If we remove nums[0] = 3, the array will be [1,2]. The difference in sums of the two parts will be 1 - 2 = -1.
 * - If we remove nums[1] = 1, the array will be [3,2]. The difference in sums of the two parts will be 3 - 2 = 1.
 * - If we remove nums[2] = 2, the array will be [3,1]. The difference in sums of the two parts will be 3 - 1 = 2.
 * The minimum difference between sums of the two parts is min(-1,1,2) = -1.
 * Example 2:
 *
 * Input: nums = [7,9,5,8,1,3]
 * Output: 1
 * Explanation: Here n = 2. So we must remove 2 elements and divide the remaining array into two parts containing two elements each.
 * If we remove nums[2] = 5 and nums[3] = 8, the resultant array will be [7,9,1,3]. The difference in sums will be (7+9) - (1+3) = 12.
 * To obtain the minimum difference, we should remove nums[1] = 9 and nums[4] = 1. The resultant array becomes [7,5,8,3]. The difference in sums of the two parts is (7+5) - (8+3) = 1.
 * It can be shown that it is not possible to obtain a difference smaller than 1.
 *
 *
 * Constraints:
 *
 * nums.length == 3 * n
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 */

public class _2163_Minimum_Difference_in_Sums_After_Removal_of_Elements {
    public long minimumDifference(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        });
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int n = nums.length;
        // We want minimum left value or first sum and maximum 2nd sum.
        // So remove max values from sum1 and removing min values from 2nd sum.
        long leftToRight[] = new long[n];
        //if the values is removed after this so minimum sum will atleast be this till n / 3
        for (int i = 0; i < n / 3; i++) {
            leftToRight[i] += (i == 0 ? nums[i] : nums[i] + leftToRight[i - 1]);
            maxHeap.offer(nums[i]);
        }
        //traversing two thirds as the last one third will represent 2nd sum
        for (int i = n / 3; i < (n / 3) * 2; i++) {
            maxHeap.offer(nums[i]);
            leftToRight[i] -= maxHeap.poll();
            leftToRight[i] += nums[i] + leftToRight[i - 1];
        }
        long rightToLeft[] = new long[n];
        // Sum of last n values
        for (int i = nums.length - 1; i >= (n / 3) * 2; i--) {
            rightToLeft[i] += (i == nums.length - 1 ? nums[i] : nums[i] + rightToLeft[i + 1]);
            minHeap.offer(nums[i]);
        }

        for (int i = (n / 3) * 2 - 1; i >= n / 3; i--) {
            minHeap.offer(nums[i]);
            rightToLeft[i] -= minHeap.poll();
            rightToLeft[i] += nums[i] + rightToLeft[i + 1];
        }
        long ans = Long.MAX_VALUE;
        //when left has not removed anything right would have remove n / 3 and so proceeding
        //similarly when left has removed n / 3 nothing would be removed form right
        //So Checking for the overlap which will give the answer
        //For Example for nums
        //nums:[7,9,5,8,1,3]
        //
        //leftToRight:[7,16,12,12,0,0]
        //rightToLeft:[0,0,13,11,4,3]
        // So we have to start with index 1 of left to right and compare it to index + 1 from
        //right to left

        for (int i = n / 3 - 1; i < (n / 3) * 2; i++) {
            ans = Math.min(ans, leftToRight[i] - rightToLeft[i + 1]);
        }
        return ans;
    }
}
