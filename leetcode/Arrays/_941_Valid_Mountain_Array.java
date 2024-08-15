package leetcode.Arrays;

/**
 *
 * Given an array of integers arr, return true if and only if it is a valid mountain array.
 *
 * Recall that arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,1]
 * Output: false
 * Example 2:
 *
 * Input: arr = [3,5,5]
 * Output: false
 * Example 3:
 *
 * Input: arr = [0,3,2,1]
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^4
 *
 */

public class _941_Valid_Mountain_Array {
    public boolean validMountainArray(int[] arr) {

        if (arr.length < 3) {
            return false;
        }
        boolean increasing = false;
        boolean decreasing = false;
        int index = 1;

        while (index < arr.length) {

            if (arr[index] >= arr[index - 1]) {

                if (arr[index] == arr[index -  1]) {
                    return false;
                }
            } else {
                break;
            }
            increasing = true;
            index++;
        }

        while (index < arr.length) {

            if (arr[index] <= arr[index - 1]) {

                if (arr[index] == arr[index -  1]) {
                    return false;
                }
            } else {
                break;
            }
            decreasing = true;
            index++;
        }
        return increasing == true && decreasing == true && index == arr.length;
    }
}
