package leetcode.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * You are given two arrays of integers nums1 and nums2, possibly of different lengths. The values in the arrays are between 1 and 6, inclusive.
 *
 * In one operation, you can change any integer's value in any of the arrays to any value between 1 and 6, inclusive.
 *
 * Return the minimum number of operations required to make the sum of values in nums1 equal to the sum of values in nums2. Return -1​​​​​ if it is not possible to make the sum of the two arrays equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
 * Output: 3
 * Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
 * - Change nums2[0] to 6. nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2].
 * - Change nums1[5] to 1. nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2].
 * - Change nums1[2] to 2. nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2].
 * Example 2:
 *
 * Input: nums1 = [1,1,1,1,1,1,1], nums2 = [6]
 * Output: -1
 * Explanation: There is no way to decrease the sum of nums1 or to increase the sum of nums2 to make them equal.
 * Example 3:
 *
 * Input: nums1 = [6,6], nums2 = [1]
 * Output: 3
 * Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
 * - Change nums1[0] to 2. nums1 = [2,6], nums2 = [1].
 * - Change nums1[1] to 2. nums1 = [2,2], nums2 = [1].
 * - Change nums2[0] to 4. nums1 = [2,2], nums2 = [4].
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 10^5
 * 1 <= nums1[i], nums2[i] <= 6
 *
 */

public class _1775_Equal_Sum_Arrays_With_Minimum_Number_of_Operations {

    public int minOperations(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                return y.compareTo(x);
            }
        });
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int sum1 = 0;

        for (int num : nums1) {
            sum1 += num;
        }
        int sum2 = 0;

        for (int num : nums2) {
            sum2 += num;
        }
        int diff = 0;
        //Idea is to match maximum from heap with bigger sum array with minimum of smaller sum heap
        if (sum1 > sum2) {
            diff = sum1 - sum2;

            for (int num : nums1) {
                maxHeap.offer(num);
            }

            for (int num : nums2) {
                minHeap.offer(num);
            }
        } else {
            diff = sum2 - sum1;

            for (int num : nums2) {
                maxHeap.offer(num);
            }

            for (int num : nums1) {
                minHeap.offer(num);
            }
        }

        if (diff == 0) {
            return 0;
        }
        int ans = 0;

        /*
        while(sum1 < sum2){
            if(maxq.isEmpty() || maxq.peek() - 1 < 6 - minq.peek())
                sum1 += 6 - minq.poll();
            else
                sum2 -= maxq.poll() - 1;
            operations++;
        }
        */

        while (!maxHeap.isEmpty() || !minHeap.isEmpty()) {

            if (diff == 0) {
                break;
            }
            int max = maxHeap.isEmpty() ? -1 : (maxHeap.peek() - 1);
            int min = minHeap.isEmpty() ? -1 : (6 - minHeap.peek());

            if (max > min) {
                diff -= max > diff ? diff : max;
                maxHeap.poll();
            } else {
                diff -= min > diff ? diff : min;
                minHeap.poll();
            }
            ans++;
        }
        return diff == 0 ? ans : -1;
    }
}
