package leetcode.HashMapHashSet;

import java.util.*;

/**
 *
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 *
 * Follow up:
 *
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 *
 */

public class _350_Intersection_of_Two_Arrays_II {
    // The question is common elements in both the arrays
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> freqHolder = new HashMap<>();
        List<Integer> holder = new ArrayList<>();

        for (int num : nums1) {
            freqHolder.put(num, freqHolder.getOrDefault(num, 0) + 1);
        }

        for (int num : nums2) {

            if (freqHolder.containsKey(num)) {
                holder.add(num);

                if (freqHolder.get(num) == 1) {
                    freqHolder.remove(num);
                } else {
                    freqHolder.put(num, freqHolder.getOrDefault(num, 0) - 1);
                }
            }
        }
        int size = holder.size();
        int[] ans = new int[size];

        for (int i = 0; i < size; i++) {
            ans[i] = holder.get(i);
        }
        return ans;
    }
    //=============================================================================================
    // Sorting
    public int[] intersect1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0;
        int index2 = 0;
        List<Integer> holder = new ArrayList<>();

        while (index1 < nums1.length && index2 < nums2.length) {

            if (nums1[index1] == nums2[index2]) {
                holder.add(nums1[index1]);
                index1++;
                index2++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                index1++;
            }
        }
        int size = holder.size();
        int[] ans = new int[size];

        for (int i = 0; i < size; i++) {
            ans[i] = holder.get(i);
        }
        return ans;
    }

}
