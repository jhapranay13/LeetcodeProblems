package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,6,7,7]
 * Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * Example 2:
 *
 * Input: nums = [4,4,3,2,1]
 * Output: [[4,4]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 15
 * -100 <= nums[i] <= 100
 *
 */

public class _491_Non_decreasing_Subsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        ArrayList<Integer> holder = new ArrayList<>();
        Set<List<Integer>> ans = new HashSet<>();
        recur(ans, holder, nums, 0);
        return new ArrayList<>(ans);
    }

    private void recur(Set<List<Integer>> ans, ArrayList<Integer> holder, int[] nums, int index) {

        if (holder.size() >= 2) {
            ans.add((List<Integer>) holder.clone());
        }

        if (index == nums.length) {
            return;
        }

        if (holder.isEmpty() || holder.get(holder.size() - 1) <= nums[index]) {
            holder.add(nums[index]);
            recur(ans, holder, nums, index + 1);
            holder.remove(holder.size() - 1);
        }

        recur(ans, holder, nums, index + 1);
    }
}
