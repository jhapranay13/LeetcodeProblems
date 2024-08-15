package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are in the inclusive range.
 *
 * A number x is considered missing if x is in the range [lower, upper] and x is not in nums.
 *
 * Return the smallest sorted list of ranges that cover every missing number exactly. That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.
 *
 * Each range [a,b] in the list should be output as:
 *
 * "a->b" if a != b
 * "a" if a == b
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,3,50,75], lower = 0, upper = 99
 * Output: ["2","4->49","51->74","76->99"]
 * Explanation: The ranges are:
 * [2,2] --> "2"
 * [4,49] --> "4->49"
 * [51,74] --> "51->74"
 * [76,99] --> "76->99"
 * Example 2:
 *
 * Input: nums = [-1], lower = -1, upper = -1
 * Output: []
 * Explanation: There are no missing ranges since there are no missing numbers.
 *
 *
 * Constraints:
 *
 * -109 <= lower <= upper <= 10^9
 * 0 <= nums.length <= 100
 * lower <= nums[i] <= upper
 * All the values of nums are unique.
 *
 */

public class _163_Missing_Ranges {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        int lo = lower - 1;
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i <= nums.length; i++) {
            List<Integer> holder = new ArrayList<>();
            int curr = i == nums.length ? upper + 1 : nums[i];
            checkAndAdd(nums, lo, curr, holder);

            if (curr >= lo) {
                lo = curr;
            }

            if (!holder.isEmpty()) {
                ans.add(holder);
            }
        }
        return ans;
    }

    private void checkAndAdd(int[] nums, int lo, int hi, List<Integer>holder) {
        int l = lo + 1;
        int h = hi - 1;

        if (l < h || l == h) {
            holder.add(l);
            holder.add(h);
        }
    }
    //=============================================================================================
    public List<String> findMissingRanges1(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        int prev = lower - 1;

        for (int i = 0; i <= nums.length; i++) {
            int curr = i == nums.length ? upper + 1 : nums[i];
            add(curr, prev, ans);
            prev = curr;
        }
        return ans;
    }

    private void add(int high, int low, List<String> ans) {
        low = low + 1;
        high = high - 1;

        if (high > low) {
            String key = low + "->" + high;
            ans.add(key);
        } else if (low == high) {
            ans.add(high + "");
        }
    }

}
