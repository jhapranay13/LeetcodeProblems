package leetcode.Arrays;

/**
 *
 * Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,2,6,6,6,6,7,10]
 * Output: 6
 * Example 2:
 *
 * Input: arr = [1,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 *
 */

public class _1287_Element_Appearing_More_Than_25percent_In_Sorted_Array {
    public int findSpecialInteger(int[] arr) {
        int limit = arr.length / 4;
        int count = 0;
        int prev = arr[0];

        for (int num : arr) {

            if (num == prev) {
                count++;
            } else {
                prev = num;
                count = 1;
            }

            if (count > limit) {
                return num;
            }
        }
        return -1;
    }
}
