package leetcode.hard;

import java.util.*;

/**
 *
 * You are given two integer arrays nums1 and nums2 of lengths m and n respectively.
 * nums1 and nums2 represent the digits of two numbers. You are also given an integer k.
 *
 * Create the maximum number of length k <= m + n from digits of the two numbers.
 * The relative order of the digits from the same array must be preserved.
 *
 * Return an array of the k digits representing the answer.
 *
 * Example 1:
 *
 * Input: nums1 = [3,4,6,5], nums2 = [9,1,2,5,8,3], k = 5
 * Output: [9,8,6,5,3]
 * Example 2:
 *
 * Input: nums1 = [6,7], nums2 = [6,0,4], k = 5
 * Output: [6,7,6,0,4]
 * Example 3:
 *
 * Input: nums1 = [3,9], nums2 = [8,9], k = 3
 * Output: [9,8,9]
 *
 *
 * Constraints:
 *
 * m == nums1.length
 * n == nums2.length
 * 1 <= m, n <= 500
 * 0 <= nums1[i], nums2[i] <= 9
 * 1 <= k <= m + n
 *
 */

public class _321_CreateMaximumNumber {
    //=============================================================================================
    //Weaving approach will give TLE.
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        Map<String, Long> memo = new HashMap<>();
        long num = recur(nums1, nums2, k, 0, 0, 0, memo);
        int size = 0;
        long n = num;

        while (n != 0) {
            n /= 10;
            size++;
        }
        int[] ans = new int[size];

        for (int s = size - 1; s >= 0; s--) {
            ans[s] = (int)num % 10;
            num /= 10;
        }
        return ans;
    }
    long holder = 0;

    private long recur(int[] nums1, int[] nums2, int k, int x, int y, long p, Map<String, Long> memo) {

        if (k == 0) {
            return p;
        }
        //String key = x + ", " + y + ", " + p;

        //if (memo.containsKey(key)) {
        //  return memo.get(key);
        //}
        long inc1 = 0;
        long inc2 = 0;

        if (x < nums1.length) {
            inc1 = recur(nums1, nums2, k - 1,x + 1, y,p * 10 + nums1[x], memo);
            long skip = recur(nums1, nums2, k, x + 1, y, p, memo);
            inc1 = Math.max(inc1, skip);
        }

        if (y < nums2.length) {
            inc2 = recur(nums1, nums2, k - 1,x, y + 1, p * 10 + nums2[y], memo);
            long skip = recur(nums1, nums2, k, x, y + 1, p, memo);
            inc2 = Math.max(inc2, skip);
        }
        // memo.put(key, Math.max(inc1, inc2));
        return Math.max(inc1, inc2);
    }
    //=============================================================================================
    //Another Weaving approach
    public int[] maxNumber3(int[] nums1, int[] nums2, int k) {
        Map<String, Long> memo = new HashMap<>();
        ans = new int[k];
        int[] p = new int[k];
        recur1(nums1, nums2, k, 0, 0, 0, p);
        return ans;
    }
    int[] ans = null;

    private void recur1(int[] nums1, int[] nums2, int k, int x, int y, int index, int[] p) {

        if (index == ans.length) {

            for (int i = 0; i < p.length; i++) {
                if (p[i] != ans[i]) {

                    if (p[i] > ans[i]) {
                        ans = Arrays.copyOf(p, p.length);
                    }
                    break;
                }
            }
            return;
        }

        if (x < nums1.length) {
            p[index] = nums1[x];
            recur1(nums1, nums2, k, x + 1, y, index + 1, p);
            p[index] = 0;
            recur1(nums1, nums2, k, x + 1, y, index, p);
        }

        if (y < nums2.length) {
            p[index] = nums2[y];
            recur1(nums1, nums2, k, x, y + 1, index + 1, p);
            p[index] = 0;
            recur1(nums1, nums2, k, x, y + 1, index, p);
        }
    }
    //=============================================================================================
    //Monotonic queue Implementation. Passes all Tests
    public int[] maxNumber1(int[] nums1, int[] nums2, int k) {
        int[] ans = null;

        for (int i = 0; i <= k; i++) {
            int[] max1 = getMax(nums1, i);
            int[] max2 = getMax(nums2, k - i);
            int[] temp = merge(max1, max2);

            if (ans == null || ans.length < k) {
                ans = temp;
            } else if (temp.length == k && check(temp, 0, ans, 0)) {
                ans = temp;
            }
        }
        return ans;
    }
    //check to see which number is greater first on the basis of position and then on size
    private boolean check(int[] x, int ix, int[] y, int iy) {

        while (ix < x.length && iy < y.length) {

            if (x[ix] > y[iy]) {
                return true;
            } else if (x[ix] < y[iy]) {
                return false;
            }
            ix++;
            iy++;
        }
        return ix != x.length;
    }

    //Merge logic with a little difference.
    private int[] merge(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        int[] ans = new int[size];
        int lo1 = 0;
        int lo2 = 0;
        int index = 0;

        while (index < size) {
            ans[index++] = check(nums1, lo1, nums2, lo2) ? nums1[lo1++] : nums2[lo2++];
        }
        return ans;
    }

    //Logic to find max number from subsequence. can also be used to find min of size
    private int[] getMax(int[] nums, int size) {

        if (size <= 0 || size > nums.length) {
            return new int[0];
        }
        int[] ans = new int[size];
        Deque<Integer> mono = new LinkedList<>();
        int numToRemove = nums.length - size;

        for (int i = 0; i < nums.length; i++) {

            while (!mono.isEmpty() && nums[mono.peekLast()] < nums[i] && numToRemove > 0) {
                numToRemove--;
                mono.removeLast();
            }
            mono.offerLast(i);
        }
        int index = 0;

        while (index < size) {
            ans[index++] = nums[mono.poll()];
        }
        return ans;
    }
}
