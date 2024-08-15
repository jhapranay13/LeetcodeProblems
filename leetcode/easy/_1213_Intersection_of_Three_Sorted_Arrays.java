package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
 * Output: [1,5]
 * Explanation: Only 1 and 5 appeared in the three arrays.
 * Example 2:
 *
 * Input: arr1 = [197,418,523,876,1356], arr2 = [501,880,1593,1710,1870], arr3 = [521,682,1337,1395,1764]
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= arr1.length, arr2.length, arr3.length <= 1000
 * 1 <= arr1[i], arr2[i], arr3[i] <= 2000
 *
 */

public class _1213_Intersection_of_Three_Sorted_Arrays {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int a = 0;
        int b = 0;
        int c = 0;
        List<Integer> ans = new ArrayList<>();

        while (a < arr1.length && b < arr2.length && c < arr3.length) {

            if (arr1[a] == arr2[b] && arr2[b] == arr3[c]) {
                ans.add(arr1[a]);
                a++;
                b++;
                c++;
            } else {

                if (arr1[a] < arr2[b]) {
                    a++;
                } else if (arr2[b] < arr3[c]) {
                    b++;
                } else {
                    c++;
                }
            }
        }
        return ans;
    }
}
