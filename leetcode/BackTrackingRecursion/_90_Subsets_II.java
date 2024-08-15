package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Given an integer array nums that may contain duplicates, return all possible
 * subsets
 *  (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 */

public class _90_Subsets_II {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        recur(nums, ans, 0, new ArrayList<>());
        return ans;
    }

    private void recur(int[] nums, List<List<Integer>> ans, int index, ArrayList<Integer> holder) {

        if (index >= nums.length) {
            ans.add((List<Integer>)holder.clone());
            return;
        }
        holder.add(nums[index]);
        recur(nums, ans, index + 1, holder);
        holder.remove(holder.size() - 1);

        while (index + 1 < nums.length && nums[index] == nums[index + 1]) {
            index++;
        }
        recur(nums, ans, index + 1, holder);
    }
    //=============================================================================================
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        recur1(nums, ans, 0, new ArrayList<>());
        return ans;
    }

    private void recur1(int[] nums, List<List<Integer>> ans, int index, ArrayList<Integer> holder) {
        ans.add((List<Integer>)holder.clone());

        for (int i = index; i < nums.length; i++) {

            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            holder.add(nums[i]);
            recur1(nums, ans, i + 1, holder);
            holder.remove(holder.size() - 1);
        }
    }
}
