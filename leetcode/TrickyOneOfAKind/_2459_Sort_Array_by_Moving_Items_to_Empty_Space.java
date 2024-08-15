package leetcode.TrickyOneOfAKind;

import java.util.Arrays;

/**
 *
 *
 * You are given an integer array nums of size n containing each element from 0 to n - 1 (inclusive). Each of the elements from 1 to n - 1 represents an item, and the element 0 represents an empty space.
 *
 * In one operation, you can move any item to the empty space. nums is considered to be sorted if the numbers of all the items are in ascending order and the empty space is either at the beginning or at the end of the array.
 *
 * For example, if n = 4, nums is sorted if:
 *
 * nums = [0,1,2,3] or
 * nums = [1,2,3,0]
 * ...and considered to be unsorted otherwise.
 *
 * Return the minimum number of operations needed to sort nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,0,3,1]
 * Output: 3
 * Explanation:
 * - Move item 2 to the empty space. Now, nums = [4,0,2,3,1].
 * - Move item 1 to the empty space. Now, nums = [4,1,2,3,0].
 * - Move item 4 to the empty space. Now, nums = [0,1,2,3,4].
 * It can be proven that 3 is the minimum number of operations needed.
 * Example 2:
 *
 * Input: nums = [1,2,3,4,0]
 * Output: 0
 * Explanation: nums is already sorted so return 0.
 * Example 3:
 *
 * Input: nums = [1,0,2,4,3]
 * Output: 2
 * Explanation:
 * - Move item 2 to the empty space. Now, nums = [1,2,0,4,3].
 * - Move item 3 to the empty space. Now, nums = [1,2,3,4,0].
 * It can be proven that 2 is the minimum number of operations needed.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 2 <= n <= 10^5
 * 0 <= nums[i] < n
 * All the values of nums are unique.
 *
 *
 */

public class _2459_Sort_Array_by_Moving_Items_to_Empty_Space {
    //Can also be done using union find
    // The whole idea is to see make the group which are linked
    // if the group contains zero then the sorting will take
    // [1, 0, 3, 4 ,2] So in this case it will take only one step
    // [1, 2, 4, 3 ,0] in this case since 0 is not part of 4 an 3
    // [1, 2, 0, 3, 4]
    // [1, 2, 3, 0, 4]
    // [1, 2, 3, 4, 0]
    // so for two elemnts with not zero in the group took 3 step
    // So if zero in the group it will take m - 1 step
    // if zero not in the group m + 1 step where m is the number of elemnts
    //in the group
    public int sortArray(int[] nums) {
        int n = nums.length;
        int step1 = 0;
        // for case [1, 2, 3, 4, 0]
        boolean[] v = new boolean[n];
        for (int i = 0; i < nums.length; i++) {

            if (i + 1 == nums[i] || v[i]) {
                continue;
            }

            if (nums[i] == 0 && i == n - 1) {
                continue;
            }
            int countElements = 0;
            boolean isZeroInGroup = false;
            int index = i;

            while (!v[index]) {
                v[index] = true;
                countElements++;

                if (nums[index] == 0) {
                    isZeroInGroup = true;
                    index = n - 1;
                } else {
                    //[1, 3, 2, 4, 0] so if 3 is encountered it should be
                    // at index 2 so next index is v[index] - 1
                    index = nums[index] - 1;
                }
            }
            step1 += countElements;

            if (isZeroInGroup) {
                step1--;
            } else {
                step1++;
            }
        }
        int step2 = 0;
        Arrays.fill(v, false);
        // for case [0, 1, 2, 3, 4]
        for (int i = 0; i < n; i++) {

            if (i == nums[i] || v[i]) {
                continue;
            }
            int countElements = 0;
            boolean isZeroInGroup = false;
            int index = i;

            while (!v[index]) {
                v[index] = true;
                countElements++;

                if (nums[index] == 0) {
                    isZeroInGroup = true;
                    index = 0;
                } else {
                    index = nums[index];
                }
            }
            step2 += countElements;

            if (isZeroInGroup) {
                step2--;
            } else {
                step2++;
            }
        }
        return Math.min(step1, step2);
    }

}
