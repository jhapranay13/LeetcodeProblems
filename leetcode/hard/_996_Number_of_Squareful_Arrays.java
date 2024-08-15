package leetcode.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * An array is squareful if the sum of every pair of adjacent elements is a perfect square.
 *
 * Given an integer array nums, return the number of permutations of nums that are squareful.
 *
 * Two permutations perm1 and perm2 are different if there is some index i such that perm1[i] != perm2[i].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,17,8]
 * Output: 2
 * Explanation: [1,8,17] and [17,8,1] are the valid permutations.
 * Example 2:
 *
 * Input: nums = [2,2,2]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 12
 * 0 <= nums[i] <= 10^9
 *
 */

public class _996_Number_of_Squareful_Arrays {
    public int numSquarefulPerms(int[] nums) {
        Arrays.sort(nums);
        recur(nums, 0);
        return ans;
    }
    private int ans = 0;

    private void recur(int[] nums, int index) {

        if (index == nums.length) {
            ans++;
        }
        // Similar to permutation with duplicates
        Set<Integer> used = new HashSet<>();

        for (int i = index; i < nums.length; i++) {

            if (used.contains(nums[i])) {
                continue;
            }
            swap(nums, index, i);

            if (index == 0 || (isPerfectSq(nums[index - 1], nums[index]))) {
                recur(nums, index + 1);
            }
            swap(nums, index, i);
            used.add(nums[i]);
        }
    }

    private void swap(int[] nums,int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    private boolean isPerfectSq(int a, int b) {
        int x = a+b;
        double sqrt = Math.sqrt(x);
        return (sqrt - (int)sqrt) == 0 ?true:false;
    }
}
