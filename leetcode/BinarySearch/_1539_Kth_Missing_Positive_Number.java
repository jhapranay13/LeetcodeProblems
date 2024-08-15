package leetcode.BinarySearch;

/**
 *
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 *
 * Return the kth positive integer that is missing from this array.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 * Example 2:
 *
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 1 <= k <= 1000
 * arr[i] < arr[j] for 1 <= i < j <= arr.length
 *
 *
 * Follow up:
 *
 * Could you solve this problem in less than O(n) complexity?
 */

public class _1539_Kth_Missing_Positive_Number {
    public int findKthPositive(int[] arr, int k) {
        int lo = 0;
        int hi = arr.length - 1;
        int diff = 0;
        int indexJustLessThan = -1;

        while(lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            diff = arr[pivot] - pivot - 1;

            if (diff < k) {
                lo = pivot + 1;
                indexJustLessThan = pivot;
            } else {
                hi = pivot - 1;
            }
        }
        int ans = 0;

        if (indexJustLessThan < 0) {
            ans =  k;
        } else {
            ans = arr[indexJustLessThan] +  k - (arr[indexJustLessThan] - indexJustLessThan - 1);
        }
        return ans;
    }
    //=============================================================================================
    // Another way
    public int findKthPositive1(int[] arr, int k) {
        int lo = 0;
        int hi = arr.length - 1;
        int diff = 0;
        int indexJustLessThan = -1;

        while(lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            diff = arr[pivot] - pivot - 1;

            if (diff < k) {
                lo = pivot + 1;
                indexJustLessThan = pivot;
            } else {
                hi = pivot - 1;
            }
        }
        int ans = 0;
        ans = lo +  k;
        return ans;
    }
}
