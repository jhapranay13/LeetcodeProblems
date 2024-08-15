package leetcode.Sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. Elements that do not appear in arr2 should be placed at the end of arr1 in ascending order.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 * Example 2:
 *
 * Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
 * Output: [22,28,8,6,17,44]
 *
 *
 * Constraints:
 *
 * 1 <= arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * All the elements of arr2 are distinct.
 * Each arr2[i] is in arr1.
 *
 */

public class _1122_Relative_Sort_Array {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int num : arr1) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        int[] ans = new int[arr1.length];
        int indexArr2 = 0;
        int index = 0;

        while (indexArr2 < arr2.length) {
            int val = arr2[indexArr2++];
            int freq = freqMap.get(val);
            freqMap.remove(val);

            while (freq-- > 0) {
                ans[index++] = val;
            }
        }
        int[] leftover = new int[ans.length - index];
        int loIndex = 0;

        for (int num : arr1) {

            if (freqMap.containsKey(num)) {
                int freq = freqMap.get(num);
                freqMap.remove(num);

                while(freq-- > 0) {
                    leftover[loIndex++] = num;
                }
            }
        }
        Arrays.sort(leftover);
        loIndex = 0;

        while (index < ans.length) {
            ans[index++] = leftover[loIndex++];
        }
        return ans;
    }
}
