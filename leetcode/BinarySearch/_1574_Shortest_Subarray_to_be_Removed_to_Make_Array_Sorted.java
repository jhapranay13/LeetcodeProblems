package leetcode.BinarySearch;

/**
 *
 * Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
 *
 * Return the length of the shortest subarray to remove.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,10,4,2,3,5]
 * Output: 3
 * Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
 * Another correct solution is to remove the subarray [3,10,4].
 * Example 2:
 *
 * Input: arr = [5,4,3,2,1]
 * Output: 4
 * Explanation: Since the array is strictly decreasing, we can only keep a single element. Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].
 * Example 3:
 *
 * Input: arr = [1,2,3]
 * Output: 0
 * Explanation: The array is already non-decreasing. We do not need to remove any elements.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 0 <= arr[i] <= 10^9
 *
 */

public class _1574_Shortest_Subarray_to_be_Removed_to_Make_Array_Sorted {
    public int findLengthOfShortestSubarray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right && arr[left] <= arr[left + 1]) {
            left++;
        }

        while (left < right && arr[right] >= arr[right - 1]) {
            right--;
        }

        if (left == right) {
            return 0;
        }
        int ans = right;

        for (int i = 0; i <= left; i++) {
            int lo = right;
            int hi = arr.length - 1;
            // Finding elemnt Just greater than or equal to arr[i]. So middle can be removed
            while (lo <= hi) {
                int pivot = lo + (hi - lo) / 2;

                if (arr[i] <= arr[pivot]) {
                    hi = pivot - 1;
                } else {
                    lo = pivot + 1;
                }
            }
            ans = Math.min(ans, lo - i - 1);
        }
        return ans;
    }
}
