package leetcode.StackAndQueues.Monotonic;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]. The width of such a ramp is j - i.
 *
 * Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [6,0,8,2,1,5]
 * Output: 4
 * Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.
 * Example 2:
 *
 * Input: nums = [9,8,1,0,1,9,4,0,4,1]
 * Output: 7
 * Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 5 * 104
 *
 */

public class _962_Maximum_Width_Ramp {
    //Monotonic
    // get the range towards right in which a particular position is minimum
    public int maxWidthRamp(int[] nums) {
        Deque<Integer> monotonicDecreasing = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {

            while (monotonicDecreasing.isEmpty() || nums[i] < nums[monotonicDecreasing.peek()]){
                monotonicDecreasing.push(i);
            }
        }
        int ans = 0;

        for (int i = nums.length - 1; i >= 0; i--) {

            while (!monotonicDecreasing.isEmpty() && nums[i] >= nums[monotonicDecreasing.peek()]) {
                ans = Math.max(i - monotonicDecreasing.pop(), ans);
            }
        }
        return ans;
    }
    //=============================================================================================
    //Binary Search approach
    // get the range towards right in which a particular position is minimum
    public int maxWidthRamp1(int[] nums) {
        List<Integer> decreasingList = new LinkedList<>();
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {

            if (decreasingList.isEmpty() || nums[i] < nums[decreasingList.get(decreasingList.size() - 1)]) {
                decreasingList.add(i);
            } else {
                int lo = 0;
                int hi = decreasingList.size() - 1;
                int res = hi;

                while (lo <= hi) {
                    int pivot = lo + (hi - lo) / 2;
                    int val = nums[decreasingList.get(pivot)];

                    if (nums[i] >= val) {
                        res = pivot;
                        hi = pivot - 1;
                    } else {
                        lo = pivot + 1;
                    }
                }
                ans = Math.max(ans, i - decreasingList.get(res));
            }
        }
        return ans;
    }
}
