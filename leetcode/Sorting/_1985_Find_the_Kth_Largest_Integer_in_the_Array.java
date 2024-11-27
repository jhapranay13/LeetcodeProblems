package leetcode.Sorting;

import java.util.Arrays;

/**
 *
 *
 * You are given an array of strings nums and an integer k. Each string in nums represents an integer without leading zeros.
 *
 * Return the string that represents the kth largest integer in nums.
 *
 * Note: Duplicate numbers should be counted distinctly. For example, if nums is ["1","2","2"], "2" is the first largest integer, "2" is the second-largest integer, and "1" is the third-largest integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = ["3","6","7","10"], k = 4
 * Output: "3"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["3","6","7","10"].
 * The 4th largest integer in nums is "3".
 * Example 2:
 *
 * Input: nums = ["2","21","12","1"], k = 3
 * Output: "2"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["1","2","12","21"].
 * The 3rd largest integer in nums is "2".
 * Example 3:
 *
 * Input: nums = ["0","0"], k = 2
 * Output: "0"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["0","0"].
 * The 2nd largest integer in nums is "0".
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 104
 * 1 <= nums[i].length <= 100
 * nums[i] consists of only digits.
 * nums[i] will not have any leading zeros.
 *
 */

public class _1985_Find_the_Kth_Largest_Integer_in_the_Array {

    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (str1, str2) -> {
            if (str1.length() == str2.length()) {
                return str1.compareTo(str2);
            }
            return Integer.compare(str1.length(), str2.length());
        });
        return nums[nums.length - k];
    }

    //=============================================================================================
    // Quickselect TLE
    // Can also be done using priority queue or sorting
    public String kthLargestNumber1(String[] nums, int k) {
        return quickSelect(nums, nums.length - k, 0, nums.length - 1);
    }

    /*private String quickSelect(String[] nums, int k, int lo, int hi) {
        int index = find(nums, lo, hi);

        if (index == k) {
            return nums[index];
        } else if (index < k) {
            return quickSelect(nums, k, index + 1, hi);
        } else {
            return quickSelect(nums, k, lo, index - 1);
        }
    }*/

    private String quickSelect(String[] nums, int k, int lo, int hi) {

        while (lo <= hi) {
            int index = find(nums, lo, hi);

            if (index == k) {
                return nums[index];
            } else if (index < k) {
                lo = index + 1;
            } else {
                hi = index - 1;
            }
        }
        return "";
    }

    private int find(String[] nums, int lo, int hi) {
        String pivotVal = nums[hi];
        int index = lo;

        for (int i = lo; i < hi; i++) {

            if(compare(nums[i], pivotVal)) {
                String temp = nums[i];
                nums[i] = nums[index];
                nums[index++] = temp;
            }
        }
        String temp = nums[index];
        nums[index] = nums[hi];
        nums[hi] = temp;

        return index;
    }

    private boolean compare(String str1, String str2) {

        if (str1.length() == str2.length()) {
            return str1.compareTo(str2) < 0 ? true : false;
        }
        return str1.length() < str2.length() ? true : false;
    }
}
