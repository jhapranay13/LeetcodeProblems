package leetcode.Sorting;

/**
 *
 * You are given two 0-indexed integer arrays nums1 and nums2, each of size n, and an integer diff. Find the number of pairs (i, j) such that:
 *
 * 0 <= i < j <= n - 1 and
 * nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
 * Return the number of pairs that satisfy the conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
 * Output: 3
 * Explanation:
 * There are 3 pairs that satisfy the conditions:
 * 1. i = 0, j = 1: 3 - 2 <= 2 - 2 + 1. Since i < j and 1 <= 1, this pair satisfies the conditions.
 * 2. i = 0, j = 2: 3 - 5 <= 2 - 1 + 1. Since i < j and -2 <= 2, this pair satisfies the conditions.
 * 3. i = 1, j = 2: 2 - 5 <= 2 - 1 + 1. Since i < j and -3 <= 2, this pair satisfies the conditions.
 * Therefore, we return 3.
 * Example 2:
 *
 * Input: nums1 = [3,-1], nums2 = [-2,2], diff = -1
 * Output: 0
 * Explanation:
 * Since there does not exist any pair that satisfies the conditions, we return 0.
 *
 *
 * Constraints:
 *
 * n == nums1.length == nums2.length
 * 2 <= n <= 10^5
 * -10^4 <= nums1[i], nums2[i] <= 10^4
 * -10^4 <= diff <= 10^4
 *
 */

public class _2426_Number_of_Pairs_Satisfying_Inequality {
    int[] holder;

    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        // nums1[i] - nums2[i] <=  nums1[j] - nums2[j] + diff or
        // nums1[i] - nums2[i] - diff <=  nums1[j] - nums2[j].
        // 0 <= i < j <= n - 1
        int[] nums = new int[nums1.length];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums1[i] - nums2[i];
        }
        holder = new int[nums.length];
        return mergeSort(nums, diff, 0, nums.length - 1);
    }

    private long mergeSort(int[] nums, int diff, int lo, int hi) {

        if (lo >= hi) {
            return 0;
        }
        int pivot = lo + (hi - lo) / 2;
        long count = mergeSort(nums, diff, lo, pivot) + mergeSort(nums, diff, pivot + 1, hi);
        int leftStart = lo;
        int rightStart = pivot + 1;
        //left will always be less than right
        while (leftStart <= pivot) {
            int index = binarySearchJustGreaterThanEqualTo(nums, pivot + 1, hi, nums[leftStart++] - diff);
            if (index != -1) {
                count += hi - index + 1;
            }
        }
        merge(nums, lo, pivot, hi);
        return count;
    }

    private int binarySearchJustGreaterThanEqualTo(int[] nums, int lo, int hi, int target) {
        int ans = -1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (nums[pivot] < target) {
                lo = pivot + 1;
            } else {
                ans = pivot;
                hi = pivot - 1;
            }
        }
        return ans;
    }

    private void merge(int [] nums, int lo, int pivot, int hi) {
        int leftStart = lo;
        int rightStart = pivot + 1;
        int holderStart = lo;

        while (leftStart <= pivot && rightStart <= hi) {

            if (nums[leftStart] < nums[rightStart]) {
                holder[holderStart++] = nums[leftStart++];
            } else if (nums[leftStart] > nums[rightStart]) {
                holder[holderStart++] = nums[rightStart++];
            } else {
                holder[holderStart++] = nums[leftStart++];
            }
        }

        while (leftStart <= pivot) {
            holder[holderStart++] = nums[leftStart++];
        }

        while (rightStart <= hi) {
            holder[holderStart++] = nums[rightStart++];
        }
        holderStart = lo;

        while (holderStart <= hi) {
            nums[holderStart] = holder[holderStart++];
        }
    }
}
