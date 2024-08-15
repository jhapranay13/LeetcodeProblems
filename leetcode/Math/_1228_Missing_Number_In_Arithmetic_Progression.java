package leetcode.Math;


/**
 *
 * In some array arr, the values were in arithmetic progression: the values arr[i + 1] - arr[i] are all equal for every 0 <= i < arr.length - 1.
 *
 * A value from arr was removed that was not the first or last value in the array.
 *
 * Given arr, return the removed value.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [5,7,11,13]
 * Output: 9
 * Explanation: The previous array was [5,7,9,11,13].
 * Example 2:
 *
 * Input: arr = [15,13,12]
 * Output: 14
 * Explanation: The previous array was [15,14,13,12].
 *
 *
 * Constraints:
 *
 * 3 <= arr.length <= 1000
 * 0 <= arr[i] <= 10^5
 * The given array is guaranteed to be a valid array.
 *
 */

public class _1228_Missing_Number_In_Arithmetic_Progression {
    public int missingNumber(int[] arr) {
        int n = arr.length;

        // Get the difference `difference`.
        int difference = (arr[arr.length - 1] - arr[0]) / n;

        // The expected element equals the starting element.
        int expected = arr[0];

        for (int val : arr) {
            // Return the expected value that doesn't match val.
            if (val != expected) return expected;

            // Next element will be expected element + `difference`.
            expected += difference;
        }
        return expected;
    }
    //=============================================================================================
    // Binary Search approach
    public int missingNumber1(int[] arr) {
        int difference = (arr[arr.length - 1] - arr[0]) / arr.length; // differnece between each number
        int lo = 0;
        int hi = arr.length - 1;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;
            int valThatShouldBe = arr[0] + pivot * difference;

            if (arr[pivot] == valThatShouldBe) {
                lo = pivot + 1;
            } else {
                hi = pivot;
            }
        }
        //returning after calculating the number
        return arr[0] + difference * lo;
    }
}
