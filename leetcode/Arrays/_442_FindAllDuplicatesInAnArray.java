package leetcode.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears once or twice, return an array of all the integers that appears twice.
 *
 * You must write an algorithm that runs in O(n) time and uses only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [2,3]
 * Example 2:
 *
 * Input: nums = [1,1,2]
 * Output: [1]
 * Example 3:
 *
 * Input: nums = [1]
 * Output: []
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * Each element in nums appears once or twice.
 *
 */

public class _442_FindAllDuplicatesInAnArray {
    public List<Integer> findDuplicates(int[] nums) {
        //Since 1 <= nums[i] <= n we know that it can be translated to indexes
        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]);
            nums[val - 1] *= -1;
        }
        List<Integer> ans = new ArrayList<>();

        //now if we have positive that means it has been multiplied by -1 twice.
        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]);

            if (nums[val - 1] > 0) {
                ans.add(val);
                nums[val - 1] *= -1; //marking it as visited;
            }
        }
        return ans;
    }
}
