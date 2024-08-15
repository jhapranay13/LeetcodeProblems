package leetcode.easy;

import java.util.*;

/**
 *
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Explanation: [4,9] is also accepted.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 */

public class _349_IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums1) {
            set.add(num);
        }
        List<Integer> ans = new ArrayList<>();

        for (int num : nums2) {
            if (set.contains(num)) {
                set.remove(num);
                ans.add(num);
            }
        }
        int[] ret = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            ret[i] = ans.get(i);
        }
        return ret;
    }
    // ============================================================================================
    //Variation If array is sorted and do it in constant space
    public int[] intersection1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); //Assume they are sorted
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        List<Integer> cache = new ArrayList<>();

        while (i < nums1.length && j < nums2.length) {

            if (i > 0) {
                while (i < nums1.length && nums1[i] == nums1[i - 1]) {
                    i++;
                }
            }

            if (j > 0) {
                while(j < nums2.length && nums2[j] == nums2[j - 1]) {
                    j++;
                }
            }

            if (i == nums1.length || j == nums2.length) {
                break;
            }
            if (nums1[i] == nums2[j]) {
                cache.add(nums1[i]);
                i++;
                j++;
            } else if(nums1[i] > nums2[j]){
                j++;
            } else {
                i++;
            }
        }
        int[] ans = new int[cache.size()];

        for (int k = 0; k < cache.size(); k++) {
            ans[k] = cache.get(k);
        }
        return ans;
    }
}
