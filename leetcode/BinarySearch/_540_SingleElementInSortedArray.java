package leetcode.BinarySearch;

/**
 *
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 *
 * Return the single element that appears only once.
 *
 * Your solution must run in O(log n) time and O(1) space.
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 *
 */

public class _540_SingleElementInSortedArray {
    //=============================================================================================
    //BinarySearch Solution
    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        //logic behind this binary Search is to check which side is even fter checking the duplicate val
        //Whichever side is not even is the side that contains unique val.
        //else the current pivot itself is the answer
        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (pivot < hi && nums[pivot] == nums[pivot + 1]) {

                if((hi - (pivot + 1)) % 2 == 0) {
                    hi = pivot - 1;
                } else {
                    lo = pivot + 2;
                }
            } else if (pivot > lo && nums[pivot] == nums[pivot - 1]) {

                if((hi - (pivot)) % 2 == 0) {
                    hi = pivot - 2;
                } else {
                    lo = pivot + 1;
                }
            } else {
                return nums[pivot];
            }
        }
        return nums[hi];
    }
    //=============================================================================================
    //Normal n solution
    public int singleNonDuplicate2(int[] nums) {
        int ans = 0;

        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}
