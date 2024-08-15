package leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.
 *
 * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].
 *
 * If there is no way to make arr1 strictly increasing, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
 * Output: 1
 * Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
 * Example 2:
 *
 * Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
 * Output: 2
 * Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
 * Example 3:
 *
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
 * Output: -1
 * Explanation: You can't make arr1 strictly increasing.
 *
 *
 * Constraints:
 *
 * 1 <= arr1.length, arr2.length <= 2000
 * 0 <= arr1[i], arr2[i] <= 10^9
 *
 */

public class _1187_Make_Array_Strictly_Increasing {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int endIndex = 1;
        //removing duplicates
        for (int i = 1; i < arr2.length; i++) {

            if (arr2[i] != arr2[i - 1]) {
                arr2[endIndex++] = arr2[i];
            }
        }
        Map<String, Integer> memo = new HashMap<>();
        int ans = recur(arr1, arr2, endIndex, 0, -1, memo);
        return ans >= Integer.MAX_VALUE / 10 ? -1 : ans;
    }

    private int recur(int[] arr1, int[] arr2, int endIndex, int currIndex, int prevNumber, Map<String, Integer> memo) {

        if (currIndex == arr1.length) {
            return 0;
        }
        String key = currIndex + " " + prevNumber;

        if (memo.get(key) != null) {
            return memo.get(key);
        }
        int swap = Integer.MAX_VALUE / 10;
        int notSwap = Integer.MAX_VALUE / 10;

        if (arr1[currIndex] > prevNumber) {
            notSwap = recur(arr1, arr2, endIndex, currIndex + 1, arr1[currIndex], memo);
        }
        int index = binarySearchGreaterThanEqualTo(arr2, endIndex, prevNumber);

        if (index != -1 && arr2[index] == prevNumber) {
            index++;
        }

        if (index != -1 && index < endIndex && arr2[index] > prevNumber) {
            swap = 1 + recur(arr1, arr2, endIndex, currIndex + 1, arr2[index], memo);
        }
        memo.put(key, Math.min(swap, notSwap));
        return Math.min(swap, notSwap);
    }

    private int binarySearchGreaterThanEqualTo(int[]arr2, int endIndex, int prevNumber) {
        int lo = 0;
        int hi = endIndex - 1;
        int ans = -1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (arr2[pivot] >= prevNumber) {
                ans = pivot;
                hi = pivot - 1;
            } else {
                lo = pivot + 1;
            }
        }
        return ans;
    }
}
