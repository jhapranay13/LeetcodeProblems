package leetcode.SegmentTree;

/**
 *
 * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
 *
 * Return the answer in an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,1,2,2,3]
 * Output: [4,0,1,1,3]
 * Explanation:
 * For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
 * For nums[1]=1 does not exist any smaller number than it.
 * For nums[2]=2 there exist one smaller number than it (1).
 * For nums[3]=2 there exist one smaller number than it (1).
 * For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
 * Example 2:
 *
 * Input: nums = [6,5,4,8]
 * Output: [2,1,0,3]
 * Example 3:
 *
 * Input: nums = [7,7,7,7]
 * Output: [0,0,0,0]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 *
 */
// Can also be done using counting sorting etc.
public class _1365_How_Many_Numbers_Are_Smaller_Than_the_Current_Number {
    class SegmentTree {
        int[] cache;
        int n;

        public SegmentTree(int n) {
            this.n = n;
            this.cache = new int[n * 4];
        }

        public int get(int qlo, int qhi) {
            return get(0, n - 1, qlo, qhi, 0);
        }

        public int get(int lo, int hi, int qlo, int qhi, int cacheIndex) {

            if (lo >= lo && hi <= qhi) {
                return cache[cacheIndex];
            }

            if (hi < qlo || lo > qhi) {
                return 0;
            }
            int pivot = lo + (hi - lo) / 2;
            int left = get(lo, pivot, qlo, qhi, cacheIndex * 2 + 1);
            int right = get(pivot + 1, hi, qlo, qhi, cacheIndex * 2 + 2);
            return left + right;
        }

        public void update(int index) {
            update(0, n - 1, index, 0);
        }

        public void update(int lo, int hi, int index, int cacheIndex) {

            if (lo == index && hi == index) {
                cache[cacheIndex] += 1;
                return;
            }

            if (lo > hi || lo > index || hi < index) {
                return;
            }
            int pivot = lo + (hi - lo) / 2;
            update(lo , pivot, index, cacheIndex * 2 + 1);
            update(pivot + 1, hi, index, cacheIndex * 2 + 2);
            cache[cacheIndex] = cache[cacheIndex * 2 + 1] + cache[cacheIndex * 2 + 2];
        }
    }
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int max = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }
        SegmentTree sgt = new SegmentTree(max + 1);

        for (int num : nums) {
            sgt.update(num);
        }
        int[] ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            ans[i] = sgt.get(0, nums[i] - 1);
        }
        return ans;
    }
}
