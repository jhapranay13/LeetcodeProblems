package leetcode.hard;

/**
 *
 * You are given an integer array nums and an integer k.
 *
 * Find the longest subsequence of nums that meets the following requirements:
 *
 * The subsequence is strictly increasing and
 * The difference between adjacent elements in the subsequence is at most k.
 * Return the length of the longest subsequence that meets the requirements.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,1,4,3,4,5,8,15], k = 3
 * Output: 5
 * Explanation:
 * The longest subsequence that meets the requirements is [1,3,4,5,8].
 * The subsequence has a length of 5, so we return 5.
 * Note that the subsequence [1,3,4,5,8,15] does not meet the requirements because 15 - 8 = 7 is larger than 3.
 * Example 2:
 *
 * Input: nums = [7,4,5,1,8,12,4,7], k = 5
 * Output: 4
 * Explanation:
 * The longest subsequence that meets the requirements is [4,5,8,12].
 * The subsequence has a length of 4, so we return 4.
 * Example 3:
 *
 * Input: nums = [1,5], k = 1
 * Output: 1
 * Explanation:
 * The longest subsequence that meets the requirements is [1].
 * The subsequence has a length of 1, so we return 1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i], k <= 10^5
 *
 */

public class _2407_Longest_Increasing_Subsequence_II {
    class SegmentTree {
        int[] arr;
        int[] cache;
        int n;

        public SegmentTree(int[] arr, int n) {
            this.arr = arr;
            this.n = n;
            int calc = (int)Math.ceil(Math.log(n + 2) / Math.log(2));
            int size = 2 * (int)(Math.pow(2, calc) - 1);
            this.cache = new int[size];
        }

        public void update(int index, int value) {

            if (index < 0 || index >= n) {
                return;
            }
            update(0, n - 1, value, index, 0);
        }

        private void update(int lo, int hi, int value, int index, int cacheIndex) {

            if (lo == hi) {
                cache[cacheIndex] = value;
                return;
            }
            int pivot = lo + (hi - lo) / 2;

            if (index <= pivot) {
                update(lo, pivot, value, index, 2 * cacheIndex + 1);
            } else {
                update(pivot + 1, hi, value, index, 2 * cacheIndex + 2);
            }
            cache[cacheIndex] = Math.max(cache[cacheIndex * 2 + 1], cache[cacheIndex * 2 + 2]);
        }

        public int get(int lo, int hi) {

            if (lo < 0 || hi >= n || lo > hi) {
                return -1;
            }
            return get(0, n - 1, lo, hi, 0);
        }

        private int get(int lo, int hi, int qlo, int qhi, int cacheIndex) {

            if (lo > qhi || hi < qlo) {
                return 0;
            }

            if (lo >= qlo && hi <= qhi) {
                return cache[cacheIndex];
            }
            int pivot = lo + (hi - lo) / 2;
            int left = get(lo, pivot, qlo, qhi, 2 * cacheIndex + 1);
            int right = get(pivot + 1, hi, qlo, qhi, 2 * cacheIndex + 2);
            return Math.max(left, right);
        }
    }

    public int lengthOfLIS(int[] nums, int k) {
        int size = 0;

        for (int num : nums) {
            size = Math.max(size, num);
        }
        SegmentTree sgt = new SegmentTree(nums, size + 1);
        int ans = 0;

        for (int num : nums) {
            int lo = Math.max(0, num - k);
            int hi = num - 1;
            int res = sgt.get(lo, hi);
            sgt.update(num, res + 1);
            ans = Math.max(ans, res + 1);
        }
        return ans;
    }
}
