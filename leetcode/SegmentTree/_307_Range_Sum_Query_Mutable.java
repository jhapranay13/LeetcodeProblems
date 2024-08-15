package leetcode.SegmentTree;

/**
 *
 * Given an integer array nums, handle multiple queries of the following types:
 *
 * Update the value of an element in nums.
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 *
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * void update(int index, int val) Updates the value of nums[index] to be val.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 *
 * Example 1:
 *
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 *
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1, 2, 5]
 * numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 10^4
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * At most 3 * 104 calls will be made to update and sumRange.
 *
 */

public class _307_Range_Sum_Query_Mutable {
    int[] nums;
    int[] cache;
    int n;

    public _307_Range_Sum_Query_Mutable(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        //int calc = (int) Math.ceil(Math.log(n) / Math.log(2));
        //int size = 2 * (int) (Math.pow(2, calc) - 1);
        int size = n * 4; // coz it can also be length * 4. For this n == 1;
        this.cache = new int[size];
        buildTree(0, n - 1, 0);
    }

    private void buildTree(int lo, int hi, int cacheIndex) {

        if (lo == hi) {
            cache[cacheIndex] = nums[lo];
            return;
        }
        int pivot = lo + (hi - lo) / 2;
        buildTree(lo, pivot, 2 * cacheIndex + 1);
        buildTree(pivot + 1, hi, 2 * cacheIndex + 2);
        cache[cacheIndex] = cache[2 * cacheIndex + 1] + cache[2 * cacheIndex + 2];
    }

    public void update(int index, int val) {

        if (index < 0 || index >= n) {
            return;
        }
        int diff = val - nums[index];
        nums[index] = val;
        update(0, n - 1, index, diff, 0);
    }

    private void update(int lo, int hi, int index, int diff, int cacheIndex) {

        if (lo > index || hi < index) {
            return;
        }

        if (lo == index && hi == index) {
            cache[cacheIndex] += diff;
            return;
        }
        int pivot = lo + (hi - lo) / 2;
        update(lo, pivot, index, diff, 2 * cacheIndex + 1);
        update(pivot + 1, hi, index, diff, 2 * cacheIndex + 2);
        cache[cacheIndex] = cache[2 * cacheIndex + 1] + cache[2 * cacheIndex + 2];
    }

    public int sumRange(int left, int right) {

        if (left < 0 || right > n - 1 || left > right) {
            return -1;
        }
        return sumRange(0, n - 1, left, right, 0);
    }

    private int sumRange(int lo, int hi, int qlo, int qhi, int cacheIndex) {

        if (hi < qlo || lo > qhi) {
            return 0;
        }

        if (hi <= qhi && lo >= qlo) {
            return cache[cacheIndex];
        }
        int pivot = lo + (hi - lo) / 2;
        int left = sumRange(lo, pivot, qlo, qhi, 2 * cacheIndex + 1);
        int right = sumRange(pivot + 1, hi,qlo, qhi, 2 * cacheIndex + 2);
        return left + right;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
