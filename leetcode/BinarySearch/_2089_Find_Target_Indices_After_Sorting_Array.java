package leetcode.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * You are given a 0-indexed integer array nums and a target element target.
 *
 * A target index is an index i such that nums[i] == target.
 *
 * Return a list of the target indices of nums after sorting nums in non-decreasing order. If there are no target indices, return an empty list. The returned list must be sorted in increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,5,2,3], target = 2
 * Output: [1,2]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The indices where nums[i] == 2 are 1 and 2.
 * Example 2:
 *
 * Input: nums = [1,2,5,2,3], target = 3
 * Output: [3]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The index where nums[i] == 3 is 3.
 * Example 3:
 *
 * Input: nums = [1,2,5,2,3], target = 5
 * Output: [4]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The index where nums[i] == 5 is 4.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i], target <= 100
 *
 */

public class _2089_Find_Target_Indices_After_Sorting_Array {
    // Can also be done using linear search or an easier version of binary search
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int index = binarySearchLeftMost(nums, target);

        if (index >= nums.length || nums[index] != target) {
            return ans;
        }
        int right = binarySearchRightMost(nums, target);

        while (index <= right) {
            ans.add(index++);
        }
        return ans;
    }

    private int binarySearchRightMost(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (hi - lo == 1) {

                if (nums[hi] == target) {
                    return hi;
                } else {
                    return lo;
                }
            }

            if (nums[pivot] <= target) {
                lo = pivot;
            } else {
                hi = pivot - 1;
            }
        }
        return lo;
    }

    private int binarySearchLeftMost(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (nums[pivot] >= target) {
                hi = pivot;
            } else {
                lo = pivot + 1;
            }
        }
        return lo;
    }
}
