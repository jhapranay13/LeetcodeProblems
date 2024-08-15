package leetcode.BinarySearch;

import java.util.Arrays;

/**
 *
 * Given two integer arrays arr1 and arr2, and the integer d, return the distance value between the two arrays.
 *
 * The distance value is defined as the number of elements arr1[i] such that there is not any element arr2[j] where |arr1[i]-arr2[j]| <= d.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
 * Output: 2
 * Explanation:
 * For arr1[0]=4 we have:
 * |4-10|=6 > d=2
 * |4-9|=5 > d=2
 * |4-1|=3 > d=2
 * |4-8|=4 > d=2
 * For arr1[1]=5 we have:
 * |5-10|=5 > d=2
 * |5-9|=4 > d=2
 * |5-1|=4 > d=2
 * |5-8|=3 > d=2
 * For arr1[2]=8 we have:
 * |8-10|=2 <= d=2
 * |8-9|=1 <= d=2
 * |8-1|=7 > d=2
 * |8-8|=0 <= d=2
 * Example 2:
 *
 * Input: arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
 * Output: 2
 * Example 3:
 *
 * Input: arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= arr1.length, arr2.length <= 500
 * -1000 <= arr1[i], arr2[j] <= 1000
 * 0 <= d <= 100
 *
 */

public class _1385_Find_the_Distance_Value_Between_Two_Arrays {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int ans = 0;

        for (int i = 0; i < arr1.length; i++) {
            int target1 = arr1[i] - d;
            int target2 = arr1[i] + d;

            if (binarySearchRange(arr2, target1, target2)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean binarySearchRange(int[] arr, int start, int end) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (start <= arr[pivot] && arr[pivot] <= end) {
                return false;
            }

            if (arr[pivot] < start) {
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return true;
    }
}
