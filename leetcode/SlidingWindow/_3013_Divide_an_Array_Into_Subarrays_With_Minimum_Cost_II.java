package leetcode.SlidingWindow;


import java.util.TreeSet;

/**
 *
 * You are given a 0-indexed array of integers nums of length n, and two positive integers k and dist.
 *
 * The cost of an array is the value of its first element. For example, the cost of [1,2,3] is 1 while the cost of [3,4,1] is 3.
 *
 * You need to divide nums into k disjoint contiguous
 * subarrays
 * , such that the difference between the starting index of the second subarray and the starting index of the kth subarray should be less than or equal to dist. In other words, if you divide nums into the subarrays nums[0..(i1 - 1)], nums[i1..(i2 - 1)], ..., nums[ik-1..(n - 1)], then ik-1 - i1 <= dist.
 *
 * Return the minimum possible sum of the cost of these subarrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,2,6,4,2], k = 3, dist = 3
 * Output: 5
 * Explanation: The best possible way to divide nums into 3 subarrays is: [1,3], [2,6,4], and [2]. This choice is valid because ik-1 - i1 is 5 - 2 = 3 which is equal to dist. The total cost is nums[0] + nums[2] + nums[5] which is 1 + 2 + 2 = 5.
 * It can be shown that there is no possible way to divide nums into 3 subarrays at a cost lower than 5.
 * Example 2:
 *
 * Input: nums = [10,1,2,2,2,1], k = 4, dist = 3
 * Output: 15
 * Explanation: The best possible way to divide nums into 4 subarrays is: [10], [1], [2], and [2,2,1]. This choice is valid because ik-1 - i1 is 3 - 1 = 2 which is less than dist. The total cost is nums[0] + nums[1] + nums[2] + nums[3] which is 10 + 1 + 2 + 2 = 15.
 * The division [10], [1], [2,2,2], and [1] is not valid, because the difference between ik-1 and i1 is 5 - 1 = 4, which is greater than dist.
 * It can be shown that there is no possible way to divide nums into 4 subarrays at a cost lower than 15.
 * Example 3:
 *
 * Input: nums = [10,8,18,9], k = 3, dist = 1
 * Output: 36
 * Explanation: The best possible way to divide nums into 4 subarrays is: [10], [8], and [18,9]. This choice is valid because ik-1 - i1 is 2 - 1 = 1 which is equal to dist.The total cost is nums[0] + nums[1] + nums[2] which is 10 + 8 + 18 = 36.
 * The division [10], [8,18], and [9] is not valid, because the difference between ik-1 and i1 is 3 - 1 = 2, which is greater than dist.
 * It can be shown that there is no possible way to divide nums into 3 subarrays at a cost lower than 36.
 *
 *
 * Constraints:
 *
 * 3 <= n <= 105
 * 1 <= nums[i] <= 109
 * 3 <= k <= n
 * k - 2 <= dist <= n - 2
 *
 *
 */

public class _3013_Divide_an_Array_Into_Subarrays_With_Minimum_Cost_II {
    // The idea is to save the minimum and maximum
    // and balance it when we remove it as sliding window moves and keep the length as k - 1
    // other wise recursion will go exponential even with memoization.
    // k - 1 because nums[0] will always be included
    public long minimumCost(int[] nums, int k, int dist) {
        TreeSet<Integer> minHeap = new TreeSet<>((index1, index2) ->
                nums[index1] == nums[index2] ? index1 - index2 : nums[index1] - nums[index2]);
        TreeSet<Integer> maxHeap = new TreeSet<>((index1, index2) ->
                nums[index1] == nums[index2] ? index1 - index2 : nums[index2] - nums[index1]);
        long ans = nums[0];
        long total = 0;

        for (int i = 1; i <= dist + 1; i++) {
            maxHeap.add(i);
            total += nums[i];
        }

        while (maxHeap.size() > k - 1) {
            int temp = maxHeap.pollFirst();
            minHeap.add(temp);
            total -= nums[temp];
        }
        long sum = total;

        for (int i = dist + 2; i < nums.length; i++) {
            int start = i - dist - 1;

            if (maxHeap.contains(start)) {
                maxHeap.remove(start);
                total -= nums[start];
            } else {
                minHeap.remove(start);
            }
            minHeap.add(i);
            int temp = maxHeap.pollFirst();
            minHeap.add(temp);
            total -= nums[temp];

            while (maxHeap.size() < k - 1 && !minHeap.isEmpty()) {
                temp = minHeap.pollFirst();
                total += nums[temp];
                maxHeap.add(temp);
            }
            sum = Math.min(sum, total);
        }
        return ans + sum;
    }

    //=============================================================================================
    // Another way
    public long minimumCost1(int[] nums, int k, int dist) {
        long result = Long.MAX_VALUE, sum = 0L;
        int n = nums.length;
        TreeSet<Integer> set1 = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]);
        TreeSet<Integer> set2 = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]);

        for (int i = 1; i < n; i++) {
            set1.add(i);
            sum += nums[i];
            if (set1.size() >= k) {
                int x = set1.pollLast();
                sum -= nums[x];
                set2.add(x);
            }
            if (i - dist > 0) {
                result = Math.min(result, sum);
                int temp = i - dist;
                if (set1.contains(temp)) {
                    set1.remove(temp);
                    sum -= nums[temp];
                    if (set2.size() > 0) {
                        int y = set2.pollFirst();
                        sum += nums[y];
                        set1.add(y);
                    }
                } else {
                    set2.remove(i - dist);
                }
            }
        }
        return result + nums[0];
    }
}
