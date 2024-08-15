package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 *
 *
 * Constraints:
 *
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 10^4
 * arr is sorted in ascending order.
 * -104 <= arr[i], x <= 10^4
 *
 */

public class _658_Find_K_Closest_Elements {
    // Can also be done using sorting
    //Collections.sort(sortedArr, (num1, num2) -> Math.abs(num1 - x) - Math.abs(num2 - x));
    // Find closest element to x and expand from it
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (arr[pivot] >= x) {
                hi = pivot;
            } else {
                lo = pivot + 1;
            }
        }
        lo = hi;

        while(hi - lo - 1 < k) {

            if (lo == -1) {
                hi++;
            } else {

                if (hi == arr.length || Math.abs(arr[lo] - x) <= Math.abs(arr[hi] - x)) {
                    lo--;
                } else {
                    hi++;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();

        for (int i = lo + 1; i < hi; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }
    //=============================================================================================
    //Find the left most element and then left + k
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - k;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (x - arr[pivot] <= arr[pivot + k] - x) {
                hi = pivot;
            } else {
                lo = pivot + 1;
            }
        }
        List<Integer> ans = new ArrayList<>();

        for (int i = hi; i < hi + k; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }
}
