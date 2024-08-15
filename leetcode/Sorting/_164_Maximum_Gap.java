package leetcode.Sorting;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
 *
 * You must write an algorithm that runs in linear time and uses linear extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
 * Example 2:
 *
 * Input: nums = [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 *
 */
// The idea is to create enough bucket for all the elements
public class _164_Maximum_Gap {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        if (min == max) {
            return 0;
        }
        int bucketSize = Math.max(1, (max - min) / n); // (n - 1)
        int size = (max - min) / bucketSize; // + 1
        List<List<Integer>> buckets = new ArrayList<>();

        for (int i = size; i >= 0; i--) {
            buckets.add(new ArrayList<>());
        }

        for (int num : nums) {
            int bucketIndex = (num - min) /  bucketSize;
            buckets.get(bucketIndex).add(num);
        }
        int ans = 0;
        int prev = min;

        for (List<Integer> bucket : buckets) {

            if (bucket.isEmpty()) {
                continue;
            }
            int currLo = bucket.get(0);
            int currHi = bucket.get(0);

            for (int num : bucket) {
                currLo = Math.min(currLo, num);
                currHi = Math.max(currHi, num);
            }
            // Finding difference between current and previous range
            ans = Math.max(ans, currLo - prev);
            ans = Math.max(ans, currHi - currLo);
            prev = currHi;
        }
        return ans;
    }
}
