package leetcode.medium;

/**
 *
 * Suppose you have n integers labeled 1 through n. A permutation of those n integers perm (1-indexed) is considered a beautiful arrangement if for every i (1 <= i <= n), either of the following is true:
 *
 * perm[i] is divisible by i.
 * i is divisible by perm[i].
 * Given an integer n, return the number of the beautiful arrangements that you can construct.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation:
 * The first beautiful arrangement is [1,2]:
 *     - perm[1] = 1 is divisible by i = 1
 *     - perm[2] = 2 is divisible by i = 2
 * The second beautiful arrangement is [2,1]:
 *     - perm[1] = 2 is divisible by i = 1
 *     - i = 2 is divisible by perm[2] = 1
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 15
 *
 */

public class _526_Beautiful_Arrangement {
    public int countArrangement(int n) {
        int[] nums = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            nums[i] = i;
        }
        return recur(nums, 1);
    }

    private int recur(int[] nums, int index) {

        if (index == nums.length) {
            return 1;
        }
        int ans = 0;

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);

            if (nums[index] % index == 0 || index % nums[index] == 0) {
                ans += recur(nums, index + 1);
            }
            swap(nums, index, i);
        }
        return ans;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
