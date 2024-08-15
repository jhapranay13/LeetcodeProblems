package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * Given an integer array nums and two integers left and right, return the number of contiguous non-empty subarrays such that the value of the maximum array element in that subarray is in the range [left, right].
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,1,4,3], left = 2, right = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 * Example 2:
 *
 * Input: nums = [2,9,2,5,6], left = 2, right = 8
 * Output: 7
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= left <= right <= 10^9
 *
 */

public class _795_Number_of_Subarrays_with_Bounded_Maximum {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        //calculating left range i. e left count for which nums[i] is max
        int[] leftToRight = new int[nums.length];
        Deque<Integer> monoDecreasing = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {

            while (!monoDecreasing.isEmpty() && nums[monoDecreasing.peek()] <= nums[i]) {
                monoDecreasing.pop();
            }

            if (monoDecreasing.isEmpty()) {
                leftToRight[i] = i + 1;
            } else {
                leftToRight[i] = i - monoDecreasing.peek();
            }
            monoDecreasing.push(i);
        }
        // doing the same from right side
        int[] rightToLeft = new int[nums.length];
        monoDecreasing = new LinkedList<>();

        for (int i = nums.length - 1; i >= 0; i--) {

            while (!monoDecreasing.isEmpty() && nums[monoDecreasing.peek()] < nums[i]) {
                monoDecreasing.pop();
            }

            if (monoDecreasing.isEmpty()) {
                rightToLeft[i] = nums.length - i;
            } else {
                rightToLeft[i] = monoDecreasing.peek() - i;
            }
            monoDecreasing.push(i);
        }
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] >= left && nums[i] <= right) {
                int l = leftToRight[i];
                int r = rightToLeft[i];

                if (l != 0 && r != 0) {
                    ans += l * r;
                } else {
                    ans += l + r;
                }
            }
        }
        return ans;
    }
    //=============================================================================================
    // Sliding window
    public int numSubarrayBoundedMax2(int[] nums, int left, int right) {
        int slow = 0;
        int fast = 0;
        int ans = 0;
        int count = 0;

        while (fast < nums.length) {

            if (nums[fast] >= left && right >= nums[fast]) {
                count = fast - slow + 1;
            } else if (nums[fast] > right){
                slow = fast + 1;
                count = 0;
            }
            // Adding prev count even if nums[fast] > left coz if the max is in between left and right it counts
            ans += count;

            fast++;
        }
        return ans;
    }
}
