package leetcode.EquationPreviousCurrentNext;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an integer array nums, return the number of reverse pairs in the array.
 *
 * A reverse pair is a pair (i, j) where:
 *
 * 0 <= i < j < nums.length and
 * nums[i] > 2 * nums[j].
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,2,3,1]
 * Output: 2
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1
 * Example 2:
 *
 * Input: nums = [2,4,3,5,1]
 * Output: 3
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 4, nums[4] = 1, 4 > 2 * 1
 * (2, 4) --> nums[2] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 5, nums[4] = 1, 5 > 2 * 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 */

public class _493_Reverse_Pairs {

    //============================================================================================
    // Merge Sort and Binary Search
    int ans = 0;
    long[] holder;

    public int reversePairs(int[] nums) {
        holder = new long[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return ans;
    }

    private void mergeSort(int[] nums, int lo, int hi) {

        if (lo >= hi) {
            return;
        }
        int pivot = lo + (hi - lo) / 2;
        mergeSort(nums, lo, pivot);
        mergeSort(nums, pivot + 1, hi);
        merge(nums, lo, pivot, hi);
    }

    private void merge(int[] nums, int lo, int mid, int hi) {
        // This is declared outside caz this won't change for the othe i
        int j = mid + 1;

        for (int i = lo; i <= mid; i++) {

            while (j <= hi && (long)nums[i] > (long)nums[j] * 2) {
                j++;
            }
            ans += j - (mid + 1);
        }
        int start1 = lo;
        int start2 = mid + 1;
        int start3 = lo;

        while (start1 <= mid && start2 <= hi) {

            if (nums[start1] >= nums[start2]) {
                holder[start3++] = nums[start2++];
            } else {
                holder[start3++] = nums[start1++];
            }
        }

        while (start2 <= hi) {
            holder[start3++] = nums[start2++];
        }

        while (start1 <= mid) {
            holder[start3++] = nums[start1++];
        }
        start3 = lo;

        while (start3 <= hi) {
            nums[start3] = (int)holder[start3++];
        }
    }

    //============================================================================================
    // Merge Sort and Binary Search

    public int reversePairs2(int[] nums) {
        holder = new long[nums.length];
        mergeSort1(nums, 0, nums.length - 1);
        return ans;
    }

    private void mergeSort1(int[] nums, int lo, int hi) {

        if (lo >= hi) {
            return;
        }
        int pivot = lo + (hi - lo) / 2;
        mergeSort1(nums, lo, pivot);
        mergeSort1(nums, pivot + 1, hi);
        merge1(nums, lo, pivot, hi);
    }

    private void merge1(int[] nums, int lo, int mid, int hi) {
        // This is declared outside caz this won't change for the othe i
        int j = mid + 1;

        for (int i = lo; i <= mid; i++) {

            int index = binarySearhLessThan(nums, j, hi, nums[i]);

            if (index != -1) {
                ans += index - (mid);
            }
        }
        int start1 = lo;
        int start2 = mid + 1;
        int start3 = lo;

        while (start1 <= mid && start2 <= hi) {

            if (nums[start1] >= nums[start2]) {
                holder[start3++] = nums[start2++];
            } else {
                holder[start3++] = nums[start1++];
            }
        }

        while (start2 <= hi) {
            holder[start3++] = nums[start2++];
        }

        while (start1 <= mid) {
            holder[start3++] = nums[start1++];
        }
        start3 = lo;

        while (start3 <= hi) {
            nums[start3] = (int)holder[start3++];
        }
    }

    private int binarySearhLessThan(int[] nums, int lo, int hi, int target) {
        int ans = -1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if ((long)nums[pivot] * 2 >= target) {
                hi = pivot - 1;
            } else {
                ans = pivot;
                lo = pivot + 1;
            }
        }
        return ans;
    }

    //=============================================================================================
    // Binary Search Solution
    public int reversePairs3(int[] nums) {
        List<Integer> holder = new ArrayList<>();
        int ans = 0;

        for (int num : nums) {
            int index = binarySearchGreaterThan(holder, (long)num * 2);

            if (index != -1) {
                ans += holder.size() - index;
            }
            int insertPos = binarySearchGreaterThan(holder, (long)num);

            if (insertPos == -1) {
                holder.add(num);
            } else {
                holder.add(insertPos, num);
            }
        }
        return ans;
    }

    private int binarySearchGreaterThan(List<Integer> holder, long target) {
        int ans = -1;
        int lo = 0;
        int hi = holder.size() - 1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (holder.get(pivot) <= target) {
                lo = pivot + 1;
            } else {
                ans = pivot;
                hi = pivot - 1;
            }
        }
        return ans;
    }
}
