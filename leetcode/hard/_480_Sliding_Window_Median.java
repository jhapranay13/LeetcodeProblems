package leetcode.hard;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 *
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7        3
 *  1  3  -1  -3 [5  3  6] 7        5
 *  1  3  -1  -3  5 [3  6  7]       6
 * Example 2:
 *
 * Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
 * Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 10^5
 * -231 <= nums[i] <= 231 - 1
 *
 */

public class _480_Sliding_Window_Median {
    //Priority Queue Solution
    public double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> minOfMax = new PriorityQueue<>();
        PriorityQueue<Integer> maxOfMin = new PriorityQueue<>((a, b) -> b.compareTo(a));

        for (int i = 0; i < k; i++) {
            addInQueue(nums[i], minOfMax, maxOfMin);
        }
        int index = 0;
        double[] ans = new double[nums.length - k + 1];

        for (int i = k; i <= nums.length; i++) {

            if (k % 2 == 0) {
                ans[index] = ((double)minOfMax.peek() + (double)maxOfMin.peek()) / 2;
            } else {
                ans[index] = (double)minOfMax.peek();
            }

            if (minOfMax.contains(nums[index])) {
                minOfMax.remove(nums[index]);
            } else {
                maxOfMin.remove(nums[index]);
            }
            index++;

            if (i < nums.length) {
                addInQueue(nums[i], minOfMax, maxOfMin);
            }
        }
        return ans;
    }

    private void addInQueue(int num, PriorityQueue<Integer> minOfMax, PriorityQueue<Integer> maxOfMin) {
        maxOfMin.offer(num);
        minOfMax.offer(maxOfMin.poll());

        while (maxOfMin.size() + 1 < minOfMax.size()) {
            maxOfMin.offer(minOfMax.poll());
        }
    }
    //=============================================================================================
    //TreeSet Implementation
    public double[] medianSlidingWindow1(int[] nums, int k) {
        TreeSet<Integer> minOfMax = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : Integer.compare(nums[a], nums[b]));
        TreeSet<Integer> maxOfMin = new TreeSet<Integer>((a, b) -> nums[a] == nums[b] ? a - b : Integer.compare(nums[b], nums[a]));

        for (int i = 0; i < k; i++) {
            addInQueue(i, minOfMax, maxOfMin);
        }
        int index = 0;
        double[] ans = new double[nums.length - k + 1];

        for (int i = k; i <= nums.length; i++) {

            if (k % 2 == 0) {
                ans[index] = ((double)nums[minOfMax.first()] + (double)nums[maxOfMin.first()]) / 2;
            } else {
                ans[index] = (double)nums[minOfMax.first()];
            }

            if (minOfMax.contains(index)) {
                minOfMax.remove(index);
            } else {
                maxOfMin.remove(index);
            }
            index++;

            if (i < nums.length) {
                addInQueue(i, minOfMax, maxOfMin);
            }
        }
        return ans;
    }

    private void addInQueue(int num, TreeSet<Integer> minOfMax, TreeSet<Integer> maxOfMin) {
        maxOfMin.add(num);
        minOfMax.add(maxOfMin.first());
        maxOfMin.remove(maxOfMin.first());

        while (maxOfMin.size() + 1 < minOfMax.size()) {
            maxOfMin.add(minOfMax.first());
            minOfMax.remove(minOfMax.first());
        }
    }
}
