package leetcode.Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given an integer array nums. In one operation, you can replace any element in nums with any integer.
 *
 * nums is considered continuous if both of the following conditions are fulfilled:
 *
 * All elements in nums are unique.
 * The difference between the maximum element and the minimum element in nums equals nums.length - 1.
 * For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.
 *
 * Return the minimum number of operations to make nums continuous.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,5,3]
 * Output: 0
 * Explanation: nums is already continuous.
 * Example 2:
 *
 * Input: nums = [1,2,3,5,6]
 * Output: 1
 * Explanation: One possible solution is to change the last element to 4.
 * The resulting array is [1,2,3,5,4], which is continuous.
 * Example 3:
 *
 * Input: nums = [1,10,100,1000]
 * Output: 3
 * Explanation: One possible solution is to:
 * - Change the second element to 2.
 * - Change the third element to 3.
 * - Change the fourth element to 4.
 * The resulting array is [1,2,3,4], which is continuous.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 *
 */

public class _2009_Minimum_Number_of_Operations_to_Make_Array_Continuous {
    // Idea is to calculate the unique values within a window
    // So that would divide the array into 2 window which we can
    // use to get the elements left out if the window condition
    // this will give us the answer
    public int minOperations(int[] nums) {
        int slow = 0;
        int fast = 0;
        int ans = nums.length;
        Arrays.sort(nums);
        Map<Integer, Integer> countMap = new HashMap<>();
        int uniqueNum = 0;

        while (fast < nums.length) {
            int prevFreq = countMap.getOrDefault(nums[fast], 0);

            if (prevFreq == 0) {
                uniqueNum++;
            }
            countMap.put(nums[fast], countMap.getOrDefault(nums[fast], 0) + 1);

            while (nums[fast] - nums[slow] > nums.length - 1) {
                int freq = countMap.get(nums[slow]);

                if (freq == 1) {
                    countMap.remove(nums[slow]);
                    uniqueNum--;
                } else {
                    countMap.put(nums[slow], countMap.getOrDefault(nums[slow], 0) - 1);
                }
                slow++;
            }
            ans = Math.min(ans, nums.length - uniqueNum);
            fast++;
        }
        return ans;
    }
}
