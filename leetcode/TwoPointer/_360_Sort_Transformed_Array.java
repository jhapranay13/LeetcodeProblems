package leetcode.TwoPointer;

/**
 *
 * Given a sorted integer array nums and three integers a, b and c, apply a quadratic function of the form f(x) = ax2 + bx + c to each element nums[i] in the array, and return the array in a sorted order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
 * Output: [3,9,15,33]
 * Example 2:
 *
 * Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
 * Output: [-23,-5,1,7]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * -100 <= nums[i], a, b, c <= 100
 * nums is sorted in ascending order.
 *
 *
 * Follow up: Could you solve it in O(n) time?
 *
 */

public class _360_Sort_Transformed_Array {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int lo = 0;
        int hi = nums.length - 1;
        int index = a >= 0 ? hi : lo;
        int[] ans = new int[nums.length];

        while (lo <= hi) {

            if (lo == hi) {
                ans[index] = getVal(nums, lo, a, b, c);
                break;
            } else {
                int hiVal = getVal(nums, hi, a, b, c);
                int loVal = getVal(nums, lo, a, b, c);

                if (a >= 0) {

                    if (loVal >= hiVal) {
                        ans[index] = loVal;
                        lo++;
                    } else {
                        ans[index] = hiVal;
                        hi--;
                    }
                    index--;
                } else {

                    if (loVal <= hiVal) {
                        ans[index] = loVal;
                        lo++;
                    } else {
                        ans[index] = hiVal;
                        hi--;
                    }
                    index++;
                }
            }
        }
        return ans;
    }

    private int getVal(int[] nums, int index, int a, int b, int c) {
        int sum = 0;
        sum = nums[index] * nums[index] * a;
        sum += nums[index] * b;
        sum += c;
        return sum;
    }
}
